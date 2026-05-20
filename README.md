# Pertemuan 11

Aplikasi web Spring Boot untuk autentikasi user dan dashboard profil. User bisa melakukan registrasi, login, lalu melihat halaman home yang menampilkan data akun dan profil yang tersimpan di PostgreSQL.

## Fitur

- Register user baru dengan `username`, `password`, `nama`, dan `alamat`
- Login dan logout memakai Spring Security
- Dashboard setelah login yang menampilkan data user dan profil
- Penyimpanan data ke PostgreSQL memakai Spring Data JPA
- Template UI memakai Thymeleaf

## Stack

- Java 25
- Spring Boot
- Spring Web MVC
- Spring Security
- Spring Data JPA
- Thymeleaf
- PostgreSQL
- Lombok
- Docker dan Docker Compose

## Struktur Project

```text
src/main/java/com/deploy11/pertemuan11/
├── Pertemuan11Application.java
├── controller/
│   ├── AuthController.java
│   └── HomeController.java
├── model/
│   ├── Profile.java
│   ├── User.java
│   └── dto/RegisterRequest.java
├── repository/
│   ├── ProfileRepository.java
│   └── UserRepository.java
├── security/
│   └── SecurityConfig.java
└── service/
	└── AuthService.java

src/main/resources/
├── application.properties
└── templates/
	├── home.html
	├── login.html
	└── register.html
```

## Cara Membuat Ulang dari Nol di IntelliJ IDEA

### 1. Buat project baru

Di IntelliJ IDEA, buat project baru dengan opsi:

- Project type: Maven
- Language: Java
- Spring Boot: aktif
- Java version: 25 atau versi yang kompatibel
- Group: `com.deploy11`
- Artifact: `pertemuan11`

### 2. Tambahkan dependency

Tambahkan dependency berikut ke `pom.xml`:

- `spring-boot-starter-webmvc`
- `spring-boot-starter-thymeleaf`
- `spring-boot-starter-security`
- `spring-boot-starter-data-jpa`
- `thymeleaf-extras-springsecurity6`
- `postgresql`
- `lombok`

Untuk testing, tambahkan starter test yang sesuai jika diperlukan.

### 3. Buat package dan class utama

Buat class utama:

- `Pertemuan11Application.java`

Isi minimalnya hanya anotasi `@SpringBootApplication` dan method `main`.

### 4. Buat model data

Buat entity berikut:

- `User`
- `Profile`

Relasinya adalah one-to-one:

- satu `User` punya satu `Profile`
- satu `Profile` milik satu `User`

Tambahkan juga DTO:

- `RegisterRequest`

DTO ini dipakai untuk menampung data input dari form register.

### 5. Buat repository

Buat repository untuk akses database:

- `UserRepository`
- `ProfileRepository`

Repository ini dipakai untuk menyimpan dan mencari data user.

### 6. Buat service autentikasi

Buat `AuthService` untuk:

- register user baru
- encode password dengan BCrypt
- ambil user yang sedang login

### 7. Buat security config

Buat `SecurityConfig` untuk:

- membuka akses ke `/login` dan `/register`
- mengamankan endpoint lain agar hanya user login yang bisa akses
- mengatur form login custom
- mengatur logout
- memakai `BCryptPasswordEncoder`

### 8. Buat controller

Buat controller berikut:

- `AuthController` untuk halaman login dan register
- `HomeController` untuk dashboard setelah login

### 9. Buat template HTML

Buat template di `src/main/resources/templates`:

- `login.html`
- `register.html`
- `home.html`

Template ini memakai Thymeleaf dan Tailwind CSS dari CDN.

### 10. Atur database

Isi `application.properties` dengan konfigurasi PostgreSQL, misalnya:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/praktikum_db
spring.datasource.username=praktikum_user
spring.datasource.password=abcde

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

## Menjalankan Aplikasi

### Jalankan lewat IntelliJ

1. Pastikan PostgreSQL sudah aktif.
2. Jalankan class `Pertemuan11Application`.
3. Buka browser ke `http://localhost:8080` atau port yang dikonfigurasi.

### Jalankan lewat Maven Wrapper

```bash
./mvnw spring-boot:run
```

### Jalankan dengan Docker Compose

```bash
sudo docker compose up
```

`docker-compose.yml` pada project ini menyiapkan:

- container PostgreSQL bernama `db_mahasiswa`
- container aplikasi Spring Boot yang terhubung ke database tersebut

## Alur Aplikasi

1. User membuka halaman register.
2. User mengisi username, password, nama, dan alamat.
3. Data disimpan ke PostgreSQL.
4. User login memakai username dan password.
5. Setelah login berhasil, user diarahkan ke dashboard `/home`.
6. Dashboard menampilkan data user dan profil.

## Catatan

- Password disimpan dalam bentuk hash BCrypt, bukan plain text.
- Aplikasi ini cocok sebagai contoh project autentikasi dasar dengan Spring Boot.
- Jika ingin dipindahkan ke environment lain, sesuaikan URL database, username, dan password di `application.properties` atau environment variable Docker.


