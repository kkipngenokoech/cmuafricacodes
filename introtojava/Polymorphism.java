abstract class Employee {
    private String name;
    private String address;
    private int number;
    public Employee(String name, String address, int number) {
        System.out.println("Constructing an Employee");
        this.name = name;
        this.address = address;
        this.number = number;
    }
    public double computePay() {
        System.out.println("Inside Employee computePay");
        return 0.0;
    }
    public void mailCheck() {
        System.out.println("Mailing a check to " + this.name + " " + this.address);
    }
    public String toString() {
        return name + " " + address + " " + number;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String newAddress) {
        address = newAddress;
    }
    public int getNumber() {
        return number;
    }
}

class Manager extends Employee {
    private double salary;
    public Manager(String name, String address, int number, double salary) {
        super(name, address, number);
        setSalary(salary);
    }
    public void mailCheck() {
        System.out.println("Within mailCheck of Salary class ");
        System.out.println("Mailing check to " + getName() + " with salary " + salary);
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double newSalary) {
        if (newSalary >= 0.0) {
            salary = newSalary;
        }
    }
    public double computePay() {
        System.out.println("Computing salary pay for " + getName());
        return salary / 52;
    }
}

class Subordinate extends Employee {
    private double hourlyRate;
    private int hoursWorked;
    public Subordinate(String name, String address, int number, double hourlyRate, int hoursWorked) {
        super(name, address, number);
        setHourlyRate(hourlyRate);
        setHoursWorked(hoursWorked);
    }
    public void mailCheck() {
        System.out.println("Within mailCheck of Hourly class ");
        System.out.println("Mailing check to " + getName() + " with hourly rate " + hourlyRate + " and hours worked " + hoursWorked);
    }
    public double getHourlyRate() {
        return hourlyRate;
    }
    public void setHourlyRate(double newRate) {
        if (newRate >= 0.0) {
            hourlyRate = newRate;
        }
    }
    public int getHoursWorked() {
        return hoursWorked;
    }
    public void setHoursWorked(int newHours) {
        if (newHours >= 0) {
            hoursWorked = newHours;
        }
    }
    public double computePay() {
        System.out.println("Computing hourly pay for " + getName());
        return hourlyRate * hoursWorked;
    }
}


public class Polymorphism {
    public static void main(String[] args) {
        Employee e = new Manager("John", "123 Main St", 123, 50000);
        e.mailCheck();
        System.out.println("Pay: " + e.computePay());
        e = new Subordinate("Jane", "456 Elm St", 456, 10.0, 40);
        e.mailCheck();
        System.out.println("Pay: " + e.computePay());
    }
}