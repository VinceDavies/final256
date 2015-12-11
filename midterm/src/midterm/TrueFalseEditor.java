package midterm;
import java.beans.PropertyEditorSupport;


public class TrueFalseEditor  extends PropertyEditorSupport {
    private boolean bool;
    private String textBool=null;
    public TrueFalseEditor () {
       bool=true;
    }
   
    public String[] getTags() {
        return null;
     }
   
    public void setAsText(String value) {
    	if (bool){
    	   value="True";
    	   textBool=value;
    	}
       if(!bool){
    	   value="False";
    	   textBool=value;
       }
 
    }
   
    public String getAsText() {
       return textBool;
    }
}