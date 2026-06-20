# Backend-часть сервиса по изучению иностранных слов WorDrop

Репозиторий содержит исходный код backend-части веб-приложения **Wordrop**, предназначенного для изучения иностранных слов и ведения статистики обучения. Приложение построено по архитектуре REST API с бессессионной (Stateless) авторизацией на основе JWT-токенов.

---

## Связанные репозитории
* **Frontend-часть сервиса по изучению иностранных слов WorDrop:** [WorDrop-Frontend](https://github.com/kal1van1ch/WorDrop-Frontend)

---

## Технологический стек

### Основные компоненты
| Технология / Фреймворк | Версия | Назначение |
| - | - | - |
| Java | 21 (LTS) | Основной язык разработки приложения |
| Spring Boot | 4.0.6 | Базовый фреймворк для быстрой конфигурации и запуска |
| PostgreSQL | latest | Реляционная база данных для хранения пользователей и статистики |
| Maven | 3.x | Инструмент автоматизации сборки проекта |

### Модули Spring Framework
| Модуль | Описание и назначение в проекте |
| - | - |
| Spring Web | Создание REST-контроллеров, обработка HTTP-запросов (GET, POST, DELETE) и CORS конфигурация. |
| Spring Security | Защита эндпоинтов, фильтрация неавторизованных запросов и интеграция JWT. |
| Spring Data JPA | Взаимодействие с PostgreSQL без написания сырого SQL (ORM на базе Hibernate). |
| Spring Core & AOP | Управление компонентами через Dependency Injection и обеспечение транзакционности (`@Transactional`). |

### Сторонние библиотеки
| Библиотека | Версия | Назначение |
| - | - | - |
| JJWT (API, Impl, Jackson) | 0.11.5 | Генерация, парсинг и валидация токенов JSON Web Token. |
| Project Lombok | Встроенная | Автоматическая генерация геттеров, сеттеров и конструкторов через аннотации. |

---

## Развёртывание базы данных в Docker

Для локальной разработки база данных PostgreSQL запускается в изолированном Docker-контейнере.

### Требования перед запуском
- Установленный Docker / Docker Desktop.
- Установленный пакет JDK 21.

### Команда для запуска
```bash
docker run --name wordrop-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=your_password -e POSTGRES_DB=vocabulary_db -p 5432:5432 -d postgres:latest
```

### Пояснение параметров
1. `--name wordrop-db` — задаёт текстовое имя для контейнера. С его помощью возможно управление контейнером (Пример: `docker stop wordrop-db`).
2. `-e POSTGRES_USER=postgres` — устанавливает переменную окружения для имени администратора базы данных.
3. `-e POSTGRES_PASSWORD=your_password` — задаёт пароль для указанного пользователя. `Важно: этот же пароль должен быть прописан в вашем application.properties`.
4. `-e POSTGRES_DB=vocabulary_db` — указывает Docker автоматически создать пустую базу данных с этим именем при первом старте контейнера.
5. `-p 5432:5432` — пробрасывает порт. Первое 5432 — это порт на хосте, второе 5432 — порт внутри контейнера.
6. `-d` — запускает контейнер в фоновом режиме (консоль останется свободной для дальнейшей работы).
7. `postgres:latest` — указывает имя официального образа на Docker Hub и его тег (последняя стабильная версия), на основе которого будет создан контейнер.

### Настройка `application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/vocabulary_db
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```


