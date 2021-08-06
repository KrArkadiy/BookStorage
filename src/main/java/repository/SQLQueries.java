package repository;

public enum SQLQueries {

    GETLABELBYID("SELECT id,name FROM Label WHERE id = %s"),
    GETALLLABELS("SELECT * FROM Label"),
    SAVELABEL("INSERT INTO Label (id, name) VALUES (%s,"),
    UPDATELABEL("SELECT * FROM Label WHERE id = %s"),
    DELETELABELBYID("DELETE FROM Label WHERE id = %s"),
    GETPOSTBYID("SELECT * FROM Post LEFT JOIN Label ON Post.id = Label.id WHERE post.id = %s"),
    GETALLPOST("SELECT * FROM Post LEFT JOIN Label ON Post.label_id = Label.id ORDER BY Post.id ASC"),
    UPDATEPOST("SELECT * FROM Post LEFT JOIN Label ON Post.lable_id = Label.id WHERE post.id = %s"),
    SAVEPOST("INSERT INTO Post (id, content, created, updated, lable_id) VALUES (%s"),
    DELETEPOSTBYID("DELETE FROM Post WHERE post.id =  %s"),
    GETWRITERBYID("SELECT * FROM Writer LEFT JOIN Post ON Writer.post_id = Post.id JOIN Label ON " +
            "Post.lable_id = Label.id WHERE writer.id =  %s"),
    GETALLWRITERS("SELECT * FROM Writer LEFT JOIN Post ON Writer.post_id = Post.id JOIN Label ON Post.lable_id = Label.id"),
    UPDATEWRITER("SELECT * FROM Writer LEFT JOIN Post ON Writer.post_id = Post.id JOIN Label ON Post.lable_id = Label.id WHERE writer.id = %s"),
    SAVEWRITER("INSERT INTO Writer (id, name, post_id) VALUES (%s"),
    DELETEWRITERBYID("DELETE FROM Writer WHERE writer.id =  %s");


    private String query;

    SQLQueries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
