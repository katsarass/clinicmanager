# ClinicManager — Clinic Management System in Java

A menu-driven Java console application for managing doctors, patients, medical examinations, and appointments. The project demonstrates object-oriented design, collection-based data management, and persistence using local text files.

## Features

- **Doctors:** add doctors, list records, view individual details, and display their active appointments.
- **Patients:** add patients, list records, and view individual details.
- **Examinations:** create imaging, microbiological, and specialized examinations; list exams alphabetically; view exam details and related active appointments.
- **Appointments:** book examinations, view all appointments, filter active appointments by patient or date, and cancel appointments.
- **Daily capacity:** check the maximum number of active appointments allowed for each examination on a given date.
- **Pricing:** calculate examination costs with category-specific surcharges for fast results.
- **Statistics:** calculate revenue totals by patient, examination, and examination category using active appointments.
- **Persistence:** load records at startup and save changes when exiting through the main menu.

## Technologies

- Java and the Java standard library
- Object-oriented programming: abstraction, inheritance, encapsulation, and polymorphism
- `HashMap` collections for ID-based record storage
- `ArrayList` and `Comparator` for examination sorting
- Text-file input/output for persistence
- `Scanner` for interactive console input

No external libraries or database server are required.

## Project structure

| Path | Purpose |
| --- | --- |
| `src/Main.java` | Application entry point, console menus, booking workflows, and statistics |
| `src/ClinicManager.java` | Record storage, capacity checks, cancellation, and data loading/saving |
| `src/Person.java` | Abstract base class for shared personal details |
| `src/Doctor.java` | Doctor details, specialty, and experience |
| `src/Patient.java` | Patient details and email address |
| `src/Exam.java` | Abstract examination class and its three concrete subclasses |
| `src/Appointment.java` | Appointment details, identifiers, and active/canceled status |
| `data/doctors.txt` | Saved doctor records |
| `data/patients.txt` | Saved patient records |
| `data/exams.txt` | Saved examination records |
| `data/appointments.txt` | Saved appointment records |
| `.gitignore` | Exclusions for compiled classes and local development files |

## Object-oriented design

`Doctor` and `Patient` extend the abstract `Person` class, sharing identifiers, names, and phone numbers.

The abstract `Exam` class defines common examination data and the `getCost(boolean fastResults)` method. Its subclasses implement category-specific pricing:

| Examination class | Additional information | Fast-results surcharge |
| --- | --- | --- |
| `ImagingExamination` | Machine type | 10% |
| `MicrobiologicalExamination` | Sample type | 20% |
| `SpecializedExamination` | Specialty | 30% |

An `Appointment` links a patient to an examination and stores the date, fast-results preference, and status. Cancellation marks a record as inactive rather than removing it. Canceled appointments do not consume daily capacity or contribute to revenue totals.

## Getting started

Install a Java Development Kit (JDK) with `javac` and `java` available in your terminal. The source uses Java 8 language features.

Open a terminal in the project root—the folder containing `src/` and `data/`—and compile:

```bash
javac src/*.java
```

Run the application from that same folder:

```bash
java -cp src Main
```

The relative `data/` paths are resolved from the current working directory. Keep that directory present and writable. Compiled `.class` files are excluded by the existing `.gitignore`.

## Using the application

| Main menu option | Section |
| --- | --- |
| `1` | Doctors |
| `2` | Patients |
| `3` | Exams |
| `4` | Appointments |
| `5` | Statistics |
| `0` | Save all data and exit |

To book an appointment:

1. Select **Appointments**, then option **1** to create a booking.
2. Enter an existing patient ID and examination ID.
3. Enter a date in `DD:MM:YYYY` format, such as `15:10:2026`.
4. If the examination has reached its daily capacity, select another date.
5. Enter `Yes` or `No` for fast results.
6. Return to the main menu and select **0** to save changes and exit.

## Data persistence

Records are stored as comma-separated values in four `.txt` files inside `data/`. Related records are linked by their identifiers.

When all four files exist, the application loads them at startup. If any required file is missing, it generates built-in initial records and writes the complete initial dataset. Keep a backup before removing any data file, because this initialization can overwrite the remaining files.

Regular session changes are saved when **0 — Exit** is selected from the main menu. Closing the terminal directly may discard unsaved changes.

## Current scope

- The interface is terminal-based.
- Appointment availability is tracked by examination and date, without individual time slots.
- Date input is checked against the `DD:MM:YYYY` pattern; actual calendar dates are not validated.
- Revenue statistics sum the costs of active bookings, including fast-results surcharges; they do not track completed payments.
- The text-file format does not escape commas inside field values.
