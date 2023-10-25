package tecqasr.blog.app.blogguist.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends  RuntimeException{
    String ObjectName;
    String fieldName;
    long fieldValue;

    public ResourceNotFoundException(String ObjectName, String fieldName, long fieldValue){
        super(String.format("%s not found with %s: %s", ObjectName, fieldName, fieldValue));
        this.ObjectName = ObjectName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
