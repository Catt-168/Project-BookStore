# Spring Boot Bookstore Project

This project is a Spring Boot application that simulates a bookstore, providing RESTful APIs for managing various aspects of the business, including authentication, authors, books, customers, genres, orders, and publishers.

---

## Endpoints

The API is organized around the following resource categories, with standard CRUD (Create, Read, Update, Delete) operations available where applicable.

### Authentication

* `/auth`
    * **POST /register**: Register a new user.
    * **POST /login**: Authenticate a user and receive an authentication token.
    * **POST /refresh**: Generate gefresh token.

### Author Management

* `/authors`
    * **GET /**: Retrieve a list of all authors.
    * **GET /{id}**: Retrieve details of a specific author by ID.
    * **POST /**: Create a new author.
    * **PUT /{id}**: Update an existing author.
    * **DELETE /{id}**: Delete an author.

### Book Management

* `/books`
    * **GET /**: Retrieve a list of all books.
    * **GET /by-genre/{genreId}**: Retrieve list of books by genre ID.
    * **GET /{id}**: Retrieve details of a specific book by ID.
    * **POST /**: Create a new book.
    * **POST /editor-pick**: Retrive books by selected (3) genre IDs.
    * **PUT /{id}**: Update an existing book.
    * **DELETE /{id}**: Delete a book.

### Customer Management

* `/customers`
    * **GET /**: Retrieve a list of all customers.
    * **GET /{id}**: Retrieve details of a specific customer by ID.
    * **GET /first**: Check wheter a user is logged in for first time.
    * **GET /getCustomer**: Retrieve customer by name
    * **POST /**: Create a new customer.
    * **POST /pref**: Added Book Preferences.
    * **POST /fav**: Select favorite books.
    * **PUT /update**: Update an existing customer.
    * **DELETE /{id}**: Delete a customer.

### Genre Management

* `/genres`
    * **GET /**: Retrieve a list of all genres.
    * **GET /{id}**: Retrieve details of a specific genre by ID.
    * **POST /**: Create a new genre.
    * **PUT /{id}**: Update an existing genre.
    * **DELETE /{id}**: Delete a genre.

### Order Management

* `/orders`
    * **GET /**: Retrieve a list of all orders.
    * **GET /{id}**: Retrieve details of a specific order by ID.
    * **GET /byCustomer**: Retrive orders by sepcific customer.
    * **POST /**: Create a new order.
    * **PUT /{id}**: Update an existing order.
    * **DELETE /{id}**: Delete an order.

### Publisher Management

* `/publishers`
    * **GET /**: Retrieve a list of all publishers.
    * **GET /{id}**: Retrieve details of a specific publisher by ID.
    * **POST /**: Create a new publisher.
    * **PUT /{id}**: Update an existing publisher.
    * **DELETE /{id}**: Delete a publisher.

---

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

* Java 17 or higher
* Maven 3.6+
* PostgreSQL

### Installation

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/Catt-168/Project-BookStore.git
    ```
2.  **Navigate to the project directory:**
    ```bash
    cd project-bookstore
    ```
3. **Change the DB Config in application.yml as your needs**

4.  **Build the project with Maven:**
    ```bash
    mvn clean install
    ```
5.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```
    

The application will typically run on `http://localhost:8080` by default.

---

## Usage

Once the application is running, you can use tools like Postman. Remember to include any necessary authentication tokens for protected endpoints.
If you are having problems running on your machine, mail to [satpaingthu168@gmail.com].
I am also trying to dockerize the app.
