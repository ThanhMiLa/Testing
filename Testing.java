import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Testing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, String> map = new HashMap<>();
        map.put("admin123", "12345");
        map.put("ngocthanh", "78910");
        map.put("fpt", "danang");

        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String s = sc.nextLine();

        if(map.containsKey(username) == true){
            if(map.get(username).equals(s)){
                System.out.println("Login successfully");
            }else{
                System.out.println("Password failed");
            }
        }else {
            System.out.println("Username failed");
        }
    }
}