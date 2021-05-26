package payroll;
import java.util.*;

/**
 * May 18th, 2021
 * ICS3U1
 * @author Bonny C
 * This is a that will take all your employee and work information and generates your payroll
 */

public class PayRoll {
    public static void main(String[] args) {
        
        Scanner reader = new Scanner(System.in);
        
        // Get the employee's personal information 
        System.out.println("-------------- AY University Employee Payroll Generator --------------");
        System.out.print("Enter your employee number: ");
        int employeeNumber = reader.nextInt();
        System.out.print("Enter first name: ");
        String firstName = reader.next();
        System.out.print("Enter surname: ");
        String surname = reader.next();
        System.out.print("Enter your department of work: ");
        String department = reader.next();
        System.out.print("Enter number of hours worked: ");
        double hoursWork = Double.parseDouble(reader.next());
        System.out.print("Enter employee type code (Lecturer = L/l)(Regular worker = R/r): ");
        String typeCode = reader.next();
        double grossSalary = 0;
        double lectureBonus = 0;
        
        // Loop to check for the type of employee
        while (!typeCode.equals("L") && !typeCode.equals("l") && !typeCode.equals("R") && !typeCode.equals("r")) {
            System.out.print("Please enter a valid type code: ");
            typeCode = reader.next();
        }
        
        // Lecturer 
        if (typeCode.equals("L") || typeCode.equals("l")) {
            // Calculates the employee's good teaching bonus based on all of their students average   	
            double sum = 0;
            double average;
            // Get input of each students mark to be stored and calculated in an array
            System.out.print("Enter total number of students that you teach: ");
            int totalStudents = reader.nextInt(); 
            double[] classAverage = new double[totalStudents]; // Array
            System.out.println("Enter the current marks for each student: "); 
            for (int i = 0; i < totalStudents; i++) { 
                classAverage[i] = reader.nextDouble();
                sum = sum + classAverage[i];
                average = sum / totalStudents;
                lectureBonus = average * 10;
            }
            
            // Lecturer degree calculations
            System.out.print("Enter your qualification code ('MASTER' or 'BACHELOR'): ");
            String qualification = reader.next();
            while (!qualification.equals("MASTER") && !qualification.equals("BACHELOR")) {
                System.out.print("Please enter a valid qualification code: ");
                qualification = reader.next();
            }
            
            // Calculates salary based on the lecturer's degree
            double perHourL, allowance;
            if (qualification.equals("MASTER")) { 
                perHourL = 475;
                allowance = 2500; 
            } else {
                perHourL = 225;
                allowance = 1250;
            }
            grossSalary = hoursWork * perHourL + allowance + lectureBonus;
            
        // The other regular workers
        } else if (typeCode.equals("R") || typeCode.equals("r")) {
            // Calculates salary based on individual employee's salary 
            System.out.print("Enter your earnings per hour: ");
            double perHourR = Double.parseDouble(reader.next());
            if (hoursWork < 160) {
                grossSalary = perHourR * hoursWork;
            } 
        }
        
        // Taxes based on the amount of earnings 
        double incomeTax, surcharge;
        if (grossSalary <= 5000) {
            incomeTax = 0;
            surcharge = 0;
        } else {
            incomeTax = 0.25 * grossSalary;
            if (grossSalary > 499.99) {
                surcharge = 33;
            } else {
                surcharge = 19.2;
            }
        }
        double netPay = grossSalary - incomeTax - surcharge;
        
        // Outputs generated payroll 
        System.out.println("\n-------------- Payroll --------------");
        System.out.println("Employee number: " + employeeNumber);
        System.out.println("Full name: " + firstName + " " + surname);
        System.out.println("Department: " + department);
        if (typeCode.equals("L") || typeCode.equals("l")) {
            System.out.println("Position: Lecturer");
            System.out.println("Good lecturer bonus pay: $" + String.format("%.2f", lectureBonus));
        } else {
            System.out.println("Position: Regular worker");
        }
        System.out.println("Hours worked: " + hoursWork + " hours");
        System.out.println("Gross salary $" + String.format("%.2f", grossSalary));
        System.out.println("Taxes and fees: $" + String.format("%.2f", incomeTax + surcharge));
        System.out.println("Net pay: $" + String.format("%.2f", netPay));
    }

}
