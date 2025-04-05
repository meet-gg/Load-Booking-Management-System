# 🚛 Load & Booking Management System - API Documentation

This is the complete API documentation for the **Load & Booking Management System** built using Spring Boot and PostgreSQL. It includes details for both Load and Booking modules.

---

## 📌 Base URL

```
https://localhost:8080/api
```

> localhost for testing.

---

## 📁 Table of Contents
1. [Load API Endpoints](#load-api-endpoints)
2. [Booking API Endpoints](#booking-api-endpoints)
4. [Validations](#✅validations)
5. [Technologies Used](#technologies-used)
6. [Running Locally](#running-locally)

---

## Load API Endpoints

### 1. Create Load
- **URL**: `/load/createLoad`
- **Method**: `POST`
- **Description**: Creates a new load entry (status default to POSTED when a load is created).
- **Request Body**:
```json
{
  "shipperId": "shipper123",
  "facility": {
    "loadingPoint": "Surat",
    "unloadingPoint": "Mumbai",
    "loadingDate": "2025-04-01T10:00:00",
    "unloadingDate": "2025-04-02T15:00:00"
  },
  "productType": "Chemicals",
  "truckType": "Flatbed",
  "noOfTrucks": 3,
  "weight": 15.5,
  "comment": "Handle with care"
}
```
- **Response**:
```json
{
  "loadId": "uuid",
  "shipperId": "shipper123",
  "facility": {
    "loadingPoint": "Surat",
    "unloadingPoint": "Mumbai",
    "loadingDate": "2025-04-01T10:00:00",
    "unloadingDate": "2025-04-02T15:00:00"
  },
  "productType": "Chemicals",
  "truckType": "Flatbed",
  "noOfTrucks": 3,
  "weight": 15.5,
  "comment": "Handle with care",
  "status": "POSTED"
}
```

### 2. Get Load by ID
- **URL**: `/load/getLoadById/{loadId}`
- **Method**: `GET`
- **Description**: Retrieve load using its ID.
- **Response**:
```json
{
  "loadId": "uuid",
  "shipperId": "shipper123",
  "facility": {
    "loadingPoint": "Surat",
    "unloadingPoint": "Mumbai",
    "loadingDate": "2025-04-01T10:00:00",
    "unloadingDate": "2025-04-02T15:00:00"
  },
  "productType": "Chemicals",
  "truckType": "Flatbed",
  "noOfTrucks": 3,
  "weight": 15.5,
  "comment": "Handle with care",
  "status": "POSTED"
}
```

### 3. Filter Loads
- **URL**: `/load/filter`
- **Method**: `GET`
- **Description**: Filters loads using optional query parameters (Use multiple option also for filtering).
- **Query Params**:
  - `shipperId`
  - `truckType`
  - `productType`
  - `status` (POSTED | BOOKED | CANCELLED)
- **Response**:
```json
[
  {
    "loadId": "uuid",
    "shipperId": "shipper123",
    "productType": "Chemicals",
    "truckType": "Flatbed",
    "status": "POSTED"
  }
]
```
---
![Sample Api](https://github.com/meet-gg/images/blob/main/Screenshot%202025-04-05%20150048.png)
---

### 4. Update Load by ID
- **URL**: `/load/updateLoadById/{loadId}`
- **Method**: `PUT`
- **Description**: Updates a load.
- **Request Body**:
```json
{
  "shipperId": "shipper123",
  "facility": {
    "loadingPoint": "Surat",
    "unloadingPoint": "Mumbai",
    "loadingDate": "2025-04-01T10:00:00",
    "unloadingDate": "2025-04-02T15:00:00"
  },
  "productType": "Chemicals",
  "truckType": "Flatbed",
  "noOfTrucks": 3,
  "weight": 15.5,
  "comment": "Handle with care"
}
```
- **Response**:
```json
{
  "loadId": "uuid",
  "shipperId": "shipper123",
  "facility": {
    "loadingPoint": "Surat",
    "unloadingPoint": "Mumbai",
    "loadingDate": "2025-04-01T10:00:00",
    "unloadingDate": "2025-04-02T15:00:00"
  },
  "productType": "Chemicals",
  "truckType": "Flatbed",
  "noOfTrucks": 3,
  "weight": 15.5,
  "comment": "Handle with care",
  "status": "POSTED"
}
```

### 5. Delete Load by ID
- **URL**: `/load/deleteLoadById/{loadId}`
- **Method**: `DELETE`
- **Description**: Deletes the specified load.
- **Response**:
```json
{
  "message": "Load deleted successfully"
}
```

### 6. Get All Loads
- **URL**: `/load/getAllLoad`
- **Method**: `GET`
- **Description**: Returns all loads.
- **Response**:
```json
[
  {
  "loadId": "uuid",
  "shipperId": "shipper123",
  "facility": {
    "loadingPoint": "Surat",
    "unloadingPoint": "Mumbai",
    "loadingDate": "2025-04-01T10:00:00",
    "unloadingDate": "2025-04-02T15:00:00"
  },
  "productType": "Chemicals",
  "truckType": "Flatbed",
  "noOfTrucks": 3,
  "weight": 15.5,
  "comment": "Handle with care",
  "status": "POSTED"
}
]
```

### 7. Get Bookings by Load ID
- **URL**: `/load/getBookingByLoadId/{loadId}`
- **Method**: `GET`
- **Description**: Fetch all bookings under a particular load.
- **Response**:
```json
[
  {
   "id": "cceb6f3d-6738-48dd-a7e6-e14e9b775d25",
   "loadId": "a8f2cb99-4da0-499a-904a-e4450dcae705",
   "transporterId": "TRANS001",
   "proposedRate": 1800.0,
   "comment": "Need urgent delivery",
   "status": "ACCEPTED",
   "requestedAt": "2025-04-04T11:24:05.163+00:00"
  }
]
```
---
![Sample Api](https://github.com/meet-gg/images/blob/main/Screenshot%202025-04-05%20150156.png)
---
---

## Booking API Endpoints

### 1. Create Booking
- **URL**: `/booking/createBooking`
- **Method**: `POST`
- **Description**: Create a new booking (bydefault status is PENDING).
- **Request Body**:
```json
{
  "loadId": "uuid",
  "transporterId": "trans123",
  "proposedRate": 1200.0,
  "comment": "Please confirm"
}
```  
- **Response**:
```json
{
  "id": "2cc3e7f0-fcb4-4d70-9bcc-78f49d9a7579",
  "loadId": "a8f2cb99-4da0-499a-904a-e4450dcae705",
  "transporterId": "TRANS001",
  "proposedRate": 15000.0,
  "comment": "Need urgent delivery",
  "status": "PENDING",
  "requestedAt": "2025-04-05T09:09:45.320+00:00"
}
```

### 2. Get Booking by ID
- **URL**: `/booking/getBookingById/{bookingId}`
- **Method**: `GET`
- **Description**: Retrieve booking using its ID.
- **Response**:
```json
{
  "id": "uuid",
  "loadId": "uuid",
  "transporterId": "trans123",
  "proposedRate": 1200.0,
  "comment": "Please confirm",
  "status": "PENDING",
  "requestedAt": "2025-04-04T11:24:05.163+00:00"
}
```

### 3. Update Booking by ID
- **URL**: `/booking/updateBookingById/{bookingId}`
- **Method**: `PUT`
- **Description**: Update booking details by ID.
- **Request Body**:
```json
{
  "loadId": "uuid",
  "transporterId": "TRANS001",
  "proposedRate": 15000.0,
  "comment": "Need urgent delivery"
}
``` 
- **Response**:
```json
{
  "id": "2cc3e7f0-fcb4-4d70-9bcc-78f49d9a7579",
  "loadId": "a8f2cb99-4da0-499a-904a-e4450dcae705",
  "transporterId": "TRANS001",
  "proposedRate": 15000.0,
  "comment": "Need urgent delivery",
  "status": "PENDING",
  "requestedAt": "2025-04-05T09:09:45.320+00:00"
}
```

### 4. Delete Booking by ID
- **URL**: `/booking/deleteBookingById/{bookingId}`
- **Method**: `DELETE`
- **Description**: Deletes the specified booking.
- **Response**:
```json
{
  "message": "Booking deleted successfully"
}
```

### 5. Filter Bookings
- **URL**: `/booking/filter`
- **Method**: `GET`
- **Description**: Filters bookings using optional query parameters (Use also multile option for filtering).
- **Query Params**:
  - `shipperId`
  - `transporterId`
  - `truckType`
  - `productType`
  - `status` (PENDING | ACCEPTED | REJECTED)
- **Response**:
```json
[
  {
    "bookingId": "uuid",
    "loadId": "uuid",
    "transporterId": "trans123",
    "proposedRate": 1200.0,
    "comment": "Please confirm",
    "status": "PENDING"
  }
]
```
---
![Sample Api](https://github.com/meet-gg/images/blob/main/Screenshot%202025-04-05%20150328.png)
---

### 6. Accept Booking
- **URL**: `/booking/acceptBooking/{bookingId}`
- **Method**: `GET`
- **Description**: Accepts a booking by ID (Status update to ACCEPTED).
- **Response**:
```json
{
  "id": "2cc3e7f0-fcb4-4d70-9bcc-78f49d9a7579",
  "loadId": "a8f2cb99-4da0-499a-904a-e4450dcae705",
  "transporterId": "TRANS001",
  "proposedRate": 15000.0,
  "comment": "Need urgent delivery",
  "status": "ACCEPTED",
  "requestedAt": "2025-04-05T09:09:45.320+00:00"
}
```

## ✅ Validations

### LoadRequestDTO:
- `shipperId`: must not be blank
- `facility`:
  - `loadingPoint` : must not be blank
  - `unloadingPoint` : must not be blank
  - `loadingDate` : present or future
  - `unloadingDate` : future
- `productType`: must not be blank
- `truckType`: must not be blank
- `noOfTrucks`: must be a positive integer
- `weight`: must be a non-negative double
- `comment`: must not be blank

### BookingRequestDTO:
- `loadId`: must not be null
- `transporterId`: must not be blank
- `proposedRate`: must be a non-negative double
- `comment`: must not be blank

---

## Technologies Used
- Java 17
- Spring Boot 3
- PostgreSQL
- Lombok
- Hibernate
- Jakarta Validation (Bean Validation)
- Swagger (Springdoc OpenAPI)

---

## Running Locally

1. Clone the repository:
```bash
git clone https://github.com/your-username/load-booking-system.git
```

2. Configure `application.properties` with your DB credentials.

3. Build and run:
```bash
./mvnw spring-boot:run
```
4. Access Swagger UI at:
```bash
http://localhost:8080/swagger-ui/index.html
```

Use this link instend of https://petstore.swagger.io/v2/swagger.json in swagger 
```bash
http://localhost:8080/v3/api-docs
```

---

## Note
- Use **Postman** for sending API requests and testing responses.
- Ensure your database is running before starting the server.
- All UUIDs must be in proper format.
- Swagger UI helps you visualize and interact with your API easily.

