package Com;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarList {
    // 1. Attribute
    private BrandList brandList;
    private ArrayList<Car> carArrayList = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    // 2. Constructor
    public CarList() {
    }

    public CarList(BrandList brandList) {
        this.brandList = brandList;
    }

    // 3. Getter and Setter
    public BrandList getBrandList() {
        return brandList;
    }

    public void setBrandList(BrandList brandList) {
        this.brandList = brandList;
    }

    // 4. Methods
    public boolean loadFromFile(String fileName){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = reader.readLine()) != null)
            {
                String[] listInfor = line.split("\\s*,\\s*");
                int pos = brandList.searchID(listInfor[1]);
                Brand brand = brandList.getBrandArrayList().get(pos);
                Car car = new Car(listInfor[0], brand, listInfor[2], listInfor[3], listInfor[4]);
                carArrayList.add(car);
            }
            reader.close();
        }catch (IOException exception){
            System.out.println(exception);
            return false;
        }
        return true;
    }

    public boolean saveToFile(String filename){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for(Car car : carArrayList){
                writer.write(String.format("%s, %s, %s, %s, %s%n", car.getCarID(), car.getBrand().getBrandID(),
                                    car.getColor(), car.getEngineID(), car.getEngineID()));
            }
            writer.close();
        }catch (IOException exception){
            System.out.println(exception);
            return false;
        }
        return true;
    }

    public int searchID(String carID){
        for(int i = 0; i < carArrayList.size(); i++){
            if(carArrayList.get(i).getCarID().equals(carID)){
                return i;
            }
        }
        return -1;
    }

    public int searchFrame(String fID){
        for(int i = 0; i < carArrayList.size(); i++){
            if(carArrayList.get(i).getFrameID().equals(fID)){
                return i;
            }
        }
        return -1;
    }

    public int searchEngine(String eID){
        for(int i = 0; i < carArrayList.size(); i++){
            if(carArrayList.get(i).getEngineID().equals(eID)){
                return i;
            }
        }
        return -1;
    }

    public void addCar(){
        Menu menu = new Menu();
        String id;
        String color;
        String frameID;
        String engineID;
        int index;

        do{
            System.out.print("Enter car ID: ");
            id = sc.nextLine();
            index = searchID(id);
            if(index != -1){
                System.out.println("This id already exists, please try again");
            }
        }while (index != -1);

        Brand brand = (Brand) menu.ref_getChoice(brandList.getBrandArrayList());

        do{
            System.out.print("Enter color: ");
            color = sc.nextLine();
            if(color.isEmpty()){
                System.out.println("Invalid color, please try again");
            }
        }while (color.isEmpty());

        boolean flag1 = false;
        do{
            System.out.print("Enter frame ID: ");
            frameID = sc.nextLine();
            String regex = "^F\\d{4}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(frameID);
            if(!matcher.matches()){
                System.out.println("Invalid frame ID, please try again");
                flag1 = false;
            }else{
                flag1 = true;
            }
        }while(!flag1);

        boolean flag2 = false;
        do{
            System.out.print("Enter engine ID: ");
            engineID = sc.nextLine();
            String regex = "^E\\d{4}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(engineID);
            if(!matcher.matches()){
                System.out.println("Invalid engine ID, please try again");
                flag2 = false;
            }else{
                flag2 = true;
            }
        }while(!flag2);

        Car car = new Car(id, brand, color, frameID, engineID);
        carArrayList.add(car);
    }

    public void printBasedBrandName(){
        System.out.print("Enter a partof brand name: ");
        String aPartOfBrandName = sc.nextLine();
        boolean check = false;
        for(Car car : carArrayList){
            if(car.getBrand().getBrandName().contains(aPartOfBrandName)){
                System.out.println(car.screenString());
                check = true;
            }
        }
        if(!check){
            System.out.println("No car is detected");
        }
    }

    public boolean removeCar(){
        System.out.print("Enter car ID: ");
        String removedID = sc.nextLine();
        int pos = searchID(removedID);
        if(pos == -1){
            System.out.println("Not Found");
            return false;
        }
        carArrayList.remove(pos);
        return true;
    }

    public boolean updateCar(){
        System.out.print("Enter car ID: ");
        String updateID = sc.nextLine();
        int pos = searchID(updateID);
        if(pos == -1){
            System.out.println("Not Found");
            return false;
        }

        Menu menu = new Menu();
        Brand brand = (Brand) menu.ref_getChoice(brandList.getBrandArrayList());

        String color;
        String frameID;
        String engineID;

        do{
            System.out.print("Enter color: ");
            color = sc.nextLine();
            if(color.isEmpty()){
                System.out.println("Invalid color, please try again");
            }
        }while (color.isEmpty());

        boolean flag1 = false;
        do{
            System.out.print("Enter frame ID: ");
            frameID = sc.nextLine();
            String regex = "^F\\d{4}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(frameID);
            if(!matcher.matches()){
                System.out.println("Invalid frame ID, please try again");
                flag1 = false;
            }else{
                flag1 = true;
            }
        }while(!flag1);

        boolean flag2 = false;
        do{
            System.out.print("Enter engine ID: ");
            engineID = sc.nextLine();
            String regex = "^E\\d{4}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(engineID);
            if(!matcher.matches()){
                System.out.println("Invalid engine ID, please try again");
                flag2 = false;
            }else{
                flag2 = true;
            }
        }while(!flag2);

        Car car = carArrayList.get(pos);
        car.setBrand(brand);
        car.setColor(color);
        car.setFrameID(frameID);
        car.setEngineID(engineID);
        return true;
    }

    public void listCar(){
        Collections.sort(carArrayList, (o1, o2) -> o1.compareTo(o2));
        for(Car car : carArrayList){
            System.out.println(car.screenString());
        }
    }
}
