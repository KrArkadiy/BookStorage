# BookStorage

Передо мной стояла задача по реализации консольного CRUD приложения, которое взаимодействует с БД и позволяет выполнять все CRUD операции над сущностями:
<p>Writer(id, name, List<Post> posts)</p>
<p>Post(id, content, Long created, Long updated, List<Label> labels)</p>
<p>Label(id, name)</p>
<p>PostStatus (enum ACTIVE, DELETED)</p>
<hr>

Слои:
<p>model - POJO классы</p>
<p>repository - классы, реализующие доступ к текстовым файлам</p>
<p>controller - обработка запросов от пользователя</p>
<p>view - все данные, необходимые для работы с консолью</p>

<hr>
Требования к приложению:
<ol>
  <li>Придерживаться шаблона MVC (пакеты model, repository, controller, view)</li>
  <li>Для миграции БД использовать Liquibase</li>
  <li>Сервисный слой приложения должен быть покрыт юнит тестами (junit + mockito)</li>
  <li>Для импорта библиотек использовать Maven</li>
</ol>

Для подключения к БД необходимо предварительно указать Ваши пароль, имя пользователя и название БД в классе DatabaseConnection.

<hr>

Технологии: JavaSE8+, MySQL, JDBC, Maven, Liquibase, JUnit, Mockito.
