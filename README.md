# BookStorage

Необходимо реализовать консольное CRUD приложение, которое взаимодействует с БД и позволяет выполнять все CRUD операции над сущностями:
Writer(id, name, List<Post> posts)
Post(id, content, Long created, Long updated, List<Label> labels)
Label(id, name)
PostStatus (enum ACTIVE, DELETED)

model - POJO классы
repository - классы, реализующие доступ к текстовым файлам
controller - обработка запросов от пользователя
view - все данные, необходимые для работы с консолью
