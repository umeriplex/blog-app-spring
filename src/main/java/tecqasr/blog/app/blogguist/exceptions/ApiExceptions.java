package tecqasr.blog.app.blogguist.exceptions;

public class ApiExceptions extends RuntimeException{
    public ApiExceptions(String message){
        super(message);
    }

    public ApiExceptions() {
        super();
    }
}
