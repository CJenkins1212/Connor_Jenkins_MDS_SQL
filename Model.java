import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*The Model class stores the variables that the other classes use to perform their functions
it has no input or output by itself.
*/
class Model {
    //Initializes assorted objects
    static List<Object> People = new ArrayList<>();
    ArrayList<Object> Person = new ArrayList<>();
    List<Object> Hold;
    int real = 0;
    int check = 0;
    int loc = 0;
    String namL;
    String typL;
    Scanner myObj = new Scanner(System.in);
    String namC;
    String typC;
    int ageC;
    float prCh;
    float heiC;
    float weiC;
    String ageE;
    String prEd;
    String heiE;
    String weiE;
    String NaDe;
    static float bmi;
}