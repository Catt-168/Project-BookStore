# Spring Boot Bookstore Project

This project is a Spring Boot application that simulates a bookstore, providing RESTful APIs for managing various aspects of the business, including authentication, authors, books, customers, genres, orders, and publishers.

---

## Endpoints

The API is organized around the following resource categories, with standard CRUD (Create, Read, Update, Delete) operations available where applicable.

### Authentication

* `/api/auth`
    * **POST /register**: Register a new user.
    * **POST /login**: Authenticate a user and receive an authentication token.

### Author Management

* `/api/authors`
    * **GET /**: Retrieve a list of all authors.
    * **GET /{id}**: Retrieve details of a specific author by ID.
    * **POST /**: Create a new author.
    * **PUT /{id}**: Update an existing author.
    * **DELETE /{id}**: Delete an author.

### Book Management

* `/api/books`
    * **GET /**: Retrieve a list of all books.
    * **GET /{id}**: Retrieve details of a specific book by ID.
    * **POST /**: Create a new book.
    * **PUT /{id}**: Update an existing book.
    * **DELETE /{id}**: Delete a book.

### Customer Management

* `/api/customers`
    * **GET /**: Retrieve a list of all customers.
    * **GET /{id}**: Retrieve details of a specific customer by ID.
    * **POST /**: Create a new customer.
    * **PUT /{id}**: Update an existing customer.
    * **DELETE /{id}**: Delete a customer.

### Genre Management

* `/api/genres`
    * **GET /**: Retrieve a list of all genres.
    * **GET /{id}**: Retrieve details of a specific genre by ID.
    * **POST /**: Create a new genre.
    * **PUT /{id}**: Update an existing genre.
    * **DELETE /{id}**: Delete a genre.

### Order Management

* `/api/orders`
    * **GET /**: Retrieve a list of all orders.
    * **GET /{id}**: Retrieve details of a specific order by ID.
    * **POST /**: Create a new order.
    * **PUT /{id}**: Update an existing order.
    * **DELETE /{id}**: Delete an order.

### Publisher Management

* `/api/publishers`
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
    git clone [https://github.com/your-username/bookstore-project.git](https://github.com/your-username/bookstore-project.git)
    ```
2.  **Navigate to the project directory:**
    ```bash
    cd bookstore-project
    ```
3.  **Build the project with Maven:**
    ```bash
    mvn clean install
    ```
4.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

The application will typically run on `http://localhost:8080` by default.

---

## Usage

Once the application is running, you can use tools like Postman, Insomnia, or curl to interact with the API endpoints. Remember to include any necessary authentication tokens for protected endpoints.
