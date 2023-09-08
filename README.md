# MVC Application

This project is an MVC (Model-View-Controller) application that includes authentication and user registration functionalities. It consists of HTML (or JSP) pages and three servlets: `AuthenticationServlet`, `RegistrationServlet`, and `UserServlet`.

## Features

1. **Authentication Page (index.html or index.jsp):**

   - Provides an authentication form for users to enter their login and password.
   - Includes a link to register a new user.

2. **Registration Page (registration.html or registration.jsp):**

   - Contains a registration form with fields for user information: name, age, email, login, and password.
   
3. **Menu Page (menu.html or menu.jsp):**

   - Offers links to various actions, including accessing the user's personal account.

4. **Servlets:**

   - **AuthenticationServlet:**

     - Checks if a user with the provided login/password exists in the database.
     - If a match is found, it redirects to the menu page.
     - If not, it returns an error message.

   - **RegistrationServlet:**

     - Accepts registration requests from the registration form.
     - Checks if a user with the provided login already exists.
     - If the login is unique, it adds the user to the database.
     - If not, it returns an error message.

   - **UserServlet:**

     - Displays user information, including name, age, email, login, and password.
     - Provides the option to edit at least one field.

5. **Field validation**
    - Implemented in the forms to ensure data integrity and user-friendly input.
