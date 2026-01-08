# JavaFX Client Scheduling Application

A comprehensive desktop scheduling application built with JavaFX, enabling businesses to manage customers, appointments, and generate detailed reports. Features secure authentication, multi-language support, and seamless database integration.

## 📋 Project Overview

This project is a full-featured client relationship management (CRM) and scheduling application designed for managing customer information, appointments, and generating analytical reports. Built with JavaFX for a modern GUI, it demonstrates enterprise-level design patterns including DAO architecture, MVC (Model-View-Controller), and utility-driven development.

## 🎯 Key Features

### User Management
- **Secure Login System**: User authentication with credentials validation
- **Role-Based Access**: Support for multiple user roles with appropriate permissions
- **User Activity Tracking**: Logging of user actions for audit purposes

### Customer Management
- **CRUD Operations**: Create, read, update, and delete customer records
- **Customer Database**: Stores comprehensive customer information
- **Division & Country Support**: Organize customers by geographic location

### Appointment Scheduling
- **Full Appointment Management**: Add, view, update, and delete appointments
- **Conflict Detection**: Prevent overlapping appointment bookings
- **Time Zone Support**: Automatic time zone conversion and handling
- **Appointment Alerts**: Notifications for upcoming appointments

### Reporting & Analytics
- **Customer Reports**: Detailed customer information summaries
- **Appointment Reports**: Total appointment counts and scheduling analytics
- **User Schedule Reports**: Individual user appointment calendars
- **Contact Reports**: Contact information by appointment type
- **Monthly Analysis**: Appointment statistics filtered by month

### Internationalization
- **Multi-Language Support**: English and French language options
- **Locale Awareness**: Automatic detection of system locale
- **Localized UI**: All user-facing text translatable

## 🏗️ Architecture

### Project Structure

```
JavaClientSchedulingJFX/
├── src/
│   ├── DAO/                 # Data Access Objects
│   │   ├── JDBC.java       # Database connection management
│   │   ├── UserLoginDB.java
│   │   ├── UserDB.java
│   │   ├── DBcustomers.java
│   │   ├── DBappointments.java
│   │   ├── DBcontacts.java
│   │   ├── DBcountries.java
│   │   └── DBfldivisions.java
│   ├── controller/         # JavaFX Controllers (MVC)
│   │   ├── LoginController.java
│   │   ├── MainScreenController.java
│   │   ├── CustomerScreenController.java
│   │   ├── AppointmentScreenController.java
│   │   ├── ReportMainScreenController.java
│   │   ├── ReportContactsController.java
│   │   ├── ReportUserApptController.java
│   │   └── ReportTotalApptController.java
│   ├── model/              # Data Models
│   │   ├── Main.java       # Application entry point
│   │   ├── User.java
│   │   ├── Customer.java
│   │   ├── Appointments.java
│   │   ├── Contacts.java
│   │   ├── Countries.java
│   │   ├── FLDivisions.java
│   │   ├── ReportByMonth.java
│   │   ├── lang_en.properties   # English translations
│   │   └── lang_fr.properties   # French translations
│   ├── utilities/          # Helper Classes
│   │   ├── Navigation.java
│   │   ├── AlertsUtility.java
│   │   ├── TimeConversions.java
│   │   ├── dbQuery.java
│   │   ├── AlertsLambdaInterface.java
│   │   └── MessagePrintLambdaInterface.java
│   └── view/               # FXML Files (UI Definitions)
└── (FXML files for each screen)
```

### Design Patterns

- **DAO Pattern**: Separates data access logic from business logic
- **MVC Architecture**: Clean separation between Model, View, and Controller
- **Singleton Pattern**: Database connection management
- **Lambda Expressions**: Used for alerts and event handling
- **Observable Lists**: JavaFX collections for real-time UI updates

## 🔧 Technologies & Dependencies

### Core Technologies
- **Java**: JDK 11.0.11
- **JavaFX**: 11.0.2 (GUI Framework)
- **MySQL**: Database backend
- **IntelliJ IDEA**: 2021.1.1 Community Edition

### Key Libraries
- **MySQL Connector**: 8.0.25 (JDBC driver)
- **JavaFX Controls**: UI components
- **Java Collections Framework**: Data structures
- **Java Time API**: Date/time handling and time zones

## 🚀 Getting Started

### Prerequisites

1. **Java Development Kit (JDK)**
   - Version: 11.0.11 or later
   - [Download JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

2. **JavaFX SDK**
   - Version: 11.0.2 or later
   - [Download JavaFX](https://gluonhq.com/products/javafx/)

3. **MySQL Server**
   - Version: 8.0.25 or later
   - Database setup with required schema

4. **MySQL Connector/J**
   - Version: 8.0.25 (included in project classpath)

### Installation & Setup

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Travis-Code/JavaClientSchedulingProject.git
   cd JavaClientSchedulingProject
   ```

2. **Configure Database**
   - Create MySQL database and import schema
   - Update database connection details in `DAO/JDBC.java`
   - Default connection settings can be modified in the JDBC class

3. **Set up JavaFX in IDE**
   - In IntelliJ IDEA:
     - Project Structure → Libraries → Add JavaFX SDK
     - Run Configuration → VM Options: `--module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml`

4. **Build & Run**
   - Open project in IntelliJ IDEA
   - Run `model.Main` class to launch the application

## 🔐 Authentication

### Default Credentials

For testing and demonstration:
- **Username**: `test`
- **Password**: `test`

Credentials can be modified through the database.

## 📊 Using the Application

### 1. Login Screen
- Enter username and password
- Application automatically detects system timezone
- User locale determines interface language (English/French)
- Login activity is logged to file for audit purposes

### 2. Main Screen
- Navigation hub to all major features
- Menu options for Customers, Appointments, and Reports

### 3. Customer Management
- **View Customers**: Browse complete customer database
- **Add Customer**: Create new customer records
- **Update Customer**: Modify existing customer information
- **Delete Customer**: Remove customer records

### 4. Appointment Management
- **Schedule Appointments**: Book appointments with automatic conflict detection
- **View Appointments**: See all scheduled appointments
- **Modify Appointments**: Update appointment details
- **Cancel Appointments**: Delete appointment records
- **Timezone Handling**: All times automatically converted to user's timezone

### 5. Reports
- **Customer Report**: Complete customer information summary
- **Appointments by Month**: View appointments filtered by month
- **User Schedule**: See individual user's appointment calendar
- **Contact Report**: Appointments grouped by contact type

## 💾 Database Schema

### Core Tables
- **Users**: User accounts and authentication
- **Customers**: Customer information and contact details
- **Appointments**: Scheduled appointments with times
- **Contacts**: Contact information
- **Countries**: Geographic location data
- **First_Level_Divisions**: State/province divisions

## 🌍 Localization

The application supports multiple languages through property files:
- **English** (`lang_en.properties`)
- **French** (`lang_fr.properties`)

Language automatically switches based on system locale.

## 📝 Key Classes Overview

### DAO Classes
- **JDBC.java**: Connection pool and database initialization
- **DBappointments.java**: Appointment CRUD operations
- **DBcustomers.java**: Customer data management
- **UserLoginDB.java**: User authentication queries

### Controller Classes
- **LoginController.java**: Authentication and login workflow
- **CustomerScreenController.java**: Customer management UI logic
- **AppointmentScreenController.java**: Appointment scheduling logic
- **ReportMainScreenController.java**: Report selection and navigation

### Utility Classes
- **Navigation.java**: Screen switching and window management
- **AlertsUtility.java**: User alert dialogs and notifications
- **TimeConversions.java**: Timezone conversion utilities

## 🧪 Testing

- Create test user account in database
- Use default test credentials (username: test, password: test)
- Test CRUD operations on customers and appointments
- Verify timezone conversions with different system locales
- Validate appointment conflict detection

## 📈 Code Statistics

- **Total Files**: 30+ Java classes
- **Total Lines of Code**: ~2,800 lines
- **Architecture**: MVC with DAO pattern
- **GUI Framework**: JavaFX with FXML

## 📄 License

This project uses the appropriate license as defined in the repository. See LICENSE file for details.

## 👤 Author

**Travis Hipolito**

- GitHub: [@Travis-Code](https://github.com/Travis-Code)

## 📚 Learning Outcomes

This project demonstrates:
- **GUI Development**: Advanced JavaFX application design
- **Database Integration**: JDBC and connection management
- **Design Patterns**: DAO, MVC, Singleton patterns
- **Java Features**: Lambda expressions, streams, date/time API
- **Enterprise Skills**: Logging, user authentication, multi-language support
- **Data Validation**: Input validation and business logic

## 🛠️ Future Enhancements

Potential improvements for future versions:
- Export reports to PDF or Excel
- Email notifications for appointments
- Customer communication history
- Recurring appointments
- Advanced search and filtering
- Performance optimizations

---

*This is a professional-grade scheduling application demonstrating enterprise Java development practices and GUI design patterns.*
