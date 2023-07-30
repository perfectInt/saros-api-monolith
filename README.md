# SAROS API
### В данном разделе представлена документация по работе с API.

## Быстрые ссылки
#### 1. [Модели](#модели)
- [User](#1-user-данные-о-пользователе)
- [News](#2-news-модель-самой-новости)
- [NewsView](#2-news-модель-самой-новости)
- [LoginDto](#4-logindto-для-авторизации)
- [Product](#5-product-модель-товара)
- [Image](#6-image-модель-картинки)
- [ProductView](#7-productview-для-удобного-представления-моделей-на-фронтенде)
#### 2. [Эндпоинты](#эндпоинты)
- [Auth's endpoints](#1-auths-endpoints)
- [Product's endpoints](#2-products-endpoints)
- [Image's endpoints](#3-images-endpoints)
- [News' endpoints](#4-news-endpoints)

## Модели
### 1. User (Данные о пользователе)
- id: Long
- username: String
- password: String
- email: String
### 2. News (Модель самой новости)
- id: Long
- title: String
- description: String
- newsDate: LocalDateTime
### 3. NewsView (Для удобного представления времени создания новости)
- id: Long
- title: String
- description: String
- newsDate: LocalDateTime (in **dd.MM.yyyy HH:mm:ss** format)
### 4. LoginDto (Для авторизации)
- username: String
- password: String
### 5. Product (Модель товара)
- id: Long
- title: String
- description: String
- category: String (пока что)
- price: Double (надо подумать о перемене на Integer)
- images: List<Image>
- previewImageId: Long
- dateOfCreation: LocalDateTime
### 6. Image (Модель картинки)
- id: Long
- name: String
- originalFileName: String
- size: Long
- contentType: String
- isPreviewImage: Boolean
- bytes: Byte[]
- product: Product
### 7. ProductView (для удобного представления моделей на фронтенде)
- id: Long
- title: String
- description: String
- category: String (пока что)
- price: Double (надо подумать о перемене на Integer)
- imagesIds: List<Long>
- previewImageId: Long

## Эндпоинты
### 1. Auth's endpoints
- **POST:** /api/v1/auth/register (Доступно всем)   
  **Регистрация пользователя.**  
  Принимает на вход данные в формате *application/json*  
  Передаваемый тип: UserCredential userCredential (обязательно)   
  Возвращаемый тип: String (пока что просто возвращает строку что пользователь добавлен)  
  Планирую добавить роли юзеров и сделать этот эндпоинт закрытым, чтобы только админы могли регистрировать новых пользователей (но пока он открыт)  
  Пример:
    ```
    User was added to the system
    ```
- **POST:** /api/v1/auth/login (Доступно всем)  
  **Аутентификация пользователя.**  
  Принимает на вход данные в формате *application/json*  
  Передаваемый тип: AuthRequestDto authRequestDto (обязательно)  
  Возвращаемый тип: String
  Пример:
    ```
    eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJydWxpazIyOCIsImlhdCI6MTY5MDQ4ODkzMiwiZXhwIjoxNjkwNDkwNzMyfQ.qTSjBOA70ToLH3dJNL0nTWco8R4fOJbATgtURnw0OuI
    ```
### 2. Product's endpoints
- **GET:** /api/v1/products (Доступно всем)  
  **Просмотр товаров.**  
  Возвращаемый тип: ProductView[]  
  Данные возвращаются в формате *application/json*  
  Request parameters:
    - page: Long (страница для товаров, на странице по 9 товаров) (по желанию)
    - category: String (категория товара)  (по желанию)
  ```
  [
    {
      "id": 6,
      "title": "another product",
      "description": "Just a description",
      "category": "Для прекрасной половины человечества",
      "price": 14490.9,
      "imagesIds": [
        7,
        8,
        9
      ],
      "previewImageId": 7
    },
    {
      "id": 2,
      "title": "test",
      "description": "Just a description",
      "category": "Браслеты",
      "price": 14490.9,
      "imagesIds": [
        3,
        4,
        5
      ],
      "previewImageId": 3
    },
    {
      "id": 10,
      "title": "test",
      "description": "Just a description",
      "category": "Для мужика",
      "price": 14490.9,
      "imagesIds": [
        11,
        12,
        13
      ],
      "previewImageId": 11
    }
  ]
  ```
- **GET:** /api/v1/product/{id} (Доступно всем)  
  **Просмотр конкретного товара по его айди.**  
  Возвращаемый тип: ProductView  
  Данные возвращаются в формате *application/json*  
  Path variable:
    -  id: Long
  ```
  {
    "id": 2,
    "title": "test",
    "description": "Just a description",
    "category": "Браслеты",
    "price": 14490.9,
    "imagesIds": [
      3,
      4,
      5
    ],
    "previewImageId": 3
  }
  ```
- **POST:** /api/v1/product/create (Доступно пока что только авторизованным пользователям)
  **Создание нового товара.**  
  Возвращаемый тип: Product   
  Данные возвращаются в формате *application/json*  
  Request parameters:
    - title: String
    - category: String
    - images[]: MultipartFile[]
- **DELETE:** /api/v1/product/delete/{id} (Доступно пока что только авторизованным пользователям)  
  **Удаление существующего товара по его айди.**  
  Path variable:
    -  id: Long
### 3. Image's endpoints
- **GET:** /api/v1/image/{id} (Доступно всем)  
  **Получение картинки по айди.**  
  Получение картинки из базы данных, путем определения айди в пути  
  Path variable:
    -  id: Long
### 4. News' endpoints
- **GET:** /api/v1/news (Доступно всем)  
  **Просмотр новостей.**  
  Возвращаемый тип: NewsView[]   
  Данные возвращаются в формате *application/json*  
  Request parameters:
    - page: Long (страница для товаров, на странице по 9 товаров) (по желанию)  
      Пример:
  ```
  [
    {
        "id": 3,
        "title": "test title",
        "description": "test description",
        "newsDate": "28.07.2023 01:31:19"
    },
    {
        "id": 2,
        "title": "test title",
        "description": "test description",
        "newsDate": "28.07.2023 01:31:04"
    },
    {
        "id": 1,
        "title": "test title",
        "description": "test description",
        "newsDate": "27.07.2023 23:39:48"
    }
  ]
  ```
- **GET:** /api/v1/news/{id} (Доступно всем)  
  **Просмотр конкретной новости по ее айди.**  
  Возвращаемый тип: NewsView  
  Данные возвращаются в формате *application/json*  
  Path variable:
    -  id: Long  
       Пример:
  ```
  {
      "id": 1,
      "title": "test title",
      "description": "test description",
      "newsDate": "27.07.2023 23:39:48"
  }
  ```
- **POST:** /api/v1/news/create (Доступно пока что только авторизованным пользователям)
  **Размещение новости.**  
  Возвращаемый тип: News   
  Данные возвращаются в формате *application/json*  
  Принимает на вход данные в формате *application/json*  
  Передаваемый тип: News news (обязательно)
- **PUT:** /api/v1/news/update (Доступно пока что только авторизованным пользователям)
  **Обновление новости.**  
  Принимает на вход данные в формате *application/json*  
  Передаваемый тип: News news (обязательно)
- **DELETE:** /api/v1/news/delete/{id} (Доступно пока что только авторизованным пользователям)  
  **Удаление существующей новости по ее айди.**  
  Path variable:
    -  id: Long  
  
