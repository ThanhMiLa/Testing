package Com;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class BrandList {
    // 1.Attribute
    private ArrayList<Brand> brandArrayList = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    // 2. Constructor
    public BrandList() {

    }

    public BrandList(ArrayList<Brand> brandArrayList) {
        this.brandArrayList = brandArrayList;
    }

    // 3. Getter and Setter

    public ArrayList<Brand> getBrandArrayList() { return brandArrayList; }

    public void setBrandArrayList(ArrayList<Brand> brandArrayList) {
        this.brandArrayList = brandArrayList;
    }

    // 4 Methods
    public boolean loadFromFile(String fileName){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = reader.readLine()) != null)
            {
                String[] listInfor = line.split("\\s*,\\s*|\\s*:\\s*");
                Brand brand = new Brand(listInfor[0], listInfor[1], listInfor[2],
                                        Double.parseDouble(listInfor[3]));
                brandArrayList.add(brand);
            }
            reader.close();
        }catch (IOException exception){
            System.out.println(exception);
            return false;
        }
        return true;
    }

    public boolean saveToFile(String fileName){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for(Brand brand : brandArrayList){
                writer.write(String.format("%s, %s, %s: %.3f%n",brand.getBrandID(), brand.getBrandName(),
                        brand.getSoundBrand(), brand.getPrice()));
            }
            writer.close();
        } catch (IOException exception) {
            System.out.println(exception);
            return false;
        }
        return true;
    }

    public int searchID(String bID) {
        return IntStream.range(0, brandArrayList.size())
                .filter(i -> brandArrayList.get(i).getBrandID().equals(bID))
                .findFirst()
                .orElse(-1);
    }

    public Brand getUserChoice(){
        Menu menu = new Menu();
        return (Brand) menu.ref_getChoice(brandArrayList);
    }

    public void addBrand(){
        String id;
        String brandName;
        String soundBrand;
        double price;
        int index;
        do{
            System.out.print("Enter ID: "); id = sc.nextLine();
            index = searchID(id);
            if(index != -1){
                System.out.println("This id already exists, please try again");
            }
        }while(index != -1);

        do{
            System.out.print("Enter brand name: "); brandName = sc.nextLine();
            if(brandName.isEmpty()){
                System.out.println("Invalid brand name, please try again");
            }
        }while (brandName.isEmpty());

        do{
            System.out.print("Enter sound brand: "); soundBrand = sc.nextLine();
            if(soundBrand.isEmpty()){
                System.out.println("Invalid sound brand, please try again");
            }
        }while(soundBrand.isEmpty());

        do{
            System.out.print("Enter Price: "); price = Double.parseDouble(sc.nextLine());
            if(price <= 0){
                System.out.println("Invalid price, please try again (price > 0)");
            }
        }while(price <= 0);

        Brand brand = new Brand(id, brandName, soundBrand, price);
        brandArrayList.add(brand);
    }

    public void updateBrand(){
        System.out.print("Enter brand ID: ");
        String brandID = sc.nextLine();
        int pos = searchID(brandID);
        if(pos < 0){
            System.out.println("Not Found");
            return;
        }

        String brandName;
        String soundBrand;
        double price;
        do{
            System.out.print("Enter brand name: "); brandName = sc.nextLine();
            if(brandName.isEmpty()){
                System.out.println("Invalid brand name, please try again");
            }
        }while (brandName.isEmpty());

        do{
            System.out.print("Enter sound brand: "); soundBrand = sc.nextLine();
            if(soundBrand.isEmpty()){
                System.out.println("Invalid sound brand, please try again");
            }
        }while(soundBrand.isEmpty());

        do{
            System.out.print("Enter Price: "); price = Double.parseDouble(sc.nextLine());
            if(price <= 0){
                System.out.println("Invalid price, please try again (price > 0)");
            }
        }while(price <= 0);

        Brand brand = brandArrayList.get(pos);
        brand.setBrandName(brandName);
        brand.setSoundBrand(soundBrand);
        brand.setPrice(price);
    }

    public void listBrands(){
        for(Brand brand : brandArrayList){
            System.out.println(brand);
        }
    }

}
