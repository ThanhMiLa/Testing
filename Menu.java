package Com;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static final Scanner sc = new Scanner(System.in);
    public <E> int int_getChoice(ArrayList<E> options){
        for(int i = 0; i < options.size(); i++){
            System.out.println((i + 1) + ": " + options.get(i));
        }
        System.out.print("Please choose an option 1...N : ");
        return Integer.parseInt(sc.nextLine());
    }

    public <E> E ref_getChoice(ArrayList<E> options){
        int response;
        int N = options.size();
        do{
            response = int_getChoice(options);
        }while (response < 1 || response > N);
        return options.get(response - 1);
    }

}
