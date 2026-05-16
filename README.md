# Courier Management MVC

Legacy Spring Web MVC application used by the courier platform for registration, profile management, route master data, and backend-controlled profile image upload.

## Tech Stack
- Java
- Spring Web MVC
- Hibernate
- MySQL
- JSP
- Cloudinary

## What This Project Handles
- User registration
- Employee registration
- User profile fetch and update
- Employee profile fetch and update
- Route lookup for origin and destination data
- Order route validation
- Profile image upload through the MVC backend

## Default Local Runtime
- App context: `http://localhost:8080/courier_management2`
- Database: `courier_managment`

## Important Endpoints
- `POST /signup`
- `POST /employee`
- `GET /login/{email}`
- `POST /update/{mail}`
- `GET /getemployeedetails/{email}`
- `POST /employeeupdate/{email}`
- `GET /origindata`
- `GET /destinationdata/{input}`
- `GET /checkorigin/{input}/{input2}`
- `POST /profile-image/{mail}`
- `POST /employee-profile-image/{email}`

## Setup
1. Configure MySQL and create the `courier_managment` database.
2. Review datasource and environment-specific values used by the app.
3. Add Cloudinary credentials to the local ignored file:

```properties
cloudinary.cloud_name=
cloudinary.api_key=
cloudinary.api_secret=
cloudinary.profile_folder=profiles
```

Expected file locations:
- `secreats.properties`
- `src/main/resources/secreats.properties`

## Run
This project is packaged as a WAR, so the normal local flow is:

```bash
mvn clean package
```

Deploy the generated WAR to a local servlet container such as Tomcat and access it under the `courier_management2` context.

## Notes
- This project works alongside the Spring Boot API and the React frontend.
- The React app calls this project using `VITE_MVC_API_URL=http://localhost:8080/courier_management2`.
- The Cloudinary secrets file is intentionally ignored from Git.
