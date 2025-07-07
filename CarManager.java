package Com;

import java.util.ArrayList;
import java.util.Scanner;

public class CarManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> options = new ArrayList<>();
        options.add("1. List all brands");
        options.add("2. Add a new brand");
        options.add("3. Search a brand based on it's ID");
        options.add("4. Update a brand");
        options.add("5. Save brands to the file, named brands.txt");
        options.add("6. List all cars in ascending order of brands names");
        options.add("7. List cars based on a part of an input brand name");
        options.add("8. Add a car");
        options.add("9. Remove a car based on its ID");
        options.add("10. Update a car based on its ID");
        options.add("11. Save cars to file");
        options.add("12. Exit Program");

        BrandList brandList = new BrandList();
        boolean checkBrandLoadFile = brandList.loadFromFile("brands.txt");
        if(checkBrandLoadFile)
            System.out.println("Brand list load form file successfully");
        else
            System.out.println("Brand list load form file not successfully");

        CarList carList = new CarList(brandList);
        boolean checkCarLoadFile = carList.loadFromFile("cars.txt");
        if(checkCarLoadFile)
            System.out.println("Car list load from file successfully");
        else
            System.out.println("Car list load form file not successfully");


        int choice;
        Menu menu = new Menu();
        do{
            choice = menu.int_getChoice(options);
            switch (choice)
            {
                case 1:
                    brandList.listBrands();
                    break;

                case 2:
                    brandList.addBrand();
                    break;

                case 3:
                    System.out.print("Enter brand ID: ");
                    String bID = sc.nextLine();
                    int pos = brandList.searchID(bID);
                    if(pos != -1){
                        System.out.println(brandList.getBrandArrayList().get(pos));
                    }else{
                        System.out.println("Not Found");
                    }
                    break;

                case 4:
                    brandList.updateBrand();
                    break;

                case 5:
                    boolean checkBrandSaveFile = brandList.saveToFile("brands.txt");
                    if(checkBrandSaveFile)
                        System.out.println("Brand list save to file successfully");
                    else
                        System.out.println("Brand list save to file not successfully");
                    break;

                case 6:
                    carList.listCar();
                    break;

                case 7:
                    carList.printBasedBrandName();
                    break;

                case 8:
                    carList.addCar();
                    break;

                case 9:
                    boolean checkRemoveCar = carList.removeCar();
                    if(checkRemoveCar)
                        System.out.println("Remove successfully");
                    else
                        System.out.println("Remove not successfully");
                    break;

                case 10:
                    boolean checkUpdateCar = carList.updateCar();
                    if(checkUpdateCar)
                        System.out.println("Update successfully");
                    else
                        System.out.println("Update not successfully");
                    break;

                case 11:
                    boolean checkCarSaveFile = carList.saveToFile("cars.txt");
                    if(checkCarSaveFile)
                        System.out.println("Car list save to file successfully");
                    else
                        System.out.println("Car list save to file not successfully");
                    break;

                case 12:
                    System.out.println("Exit program");
                    break;

                default:
                    System.out.println("Invalid your choice, please try again");
            }
        }while (choice != 12);
    }
}
