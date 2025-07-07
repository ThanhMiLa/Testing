package Com;

public class Car implements Comparable<Car> {
    // 1. Attribute
    private String carID;
    private Brand brand;
    private String color;
    private String frameID;
    private String engineID;

    // 2. Constructor
    public Car() {
    }

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    // 3. Getter and Setter


    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFrameID() {
        return frameID;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    // 4. Methods


    @Override
    public String toString() {
        return "<" + this.carID + ", " + this.brand.getBrandID() + ", "
                + this.color + ", " + this.frameID + ", " + this.engineID + ">";
    }

    public String screenString(){
        return "<" + this.brand + "\n" +
                this.carID + ", " + this.color + ", " + this.frameID + ", " + this.engineID + ">";
    }

    @Override
    public int compareTo(Car c) {
        int d = this.brand.getBrandName().compareTo(c.brand.getBrandName());
        if(d != 0)
            return d;
        else{
            return this.carID.compareTo(c.carID);
        }
    }
}
