import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollSystem {
    public static void main(String[] args) {
        PayrollSystem payrollSystem = new PayrollSystem();
        payrollSystem.addEmployee(new Employee(1, "Likhitha Satyavarapu", "Manager", 50000, "Vijay Satyavarapu",
                "28/11/2001", "satyavarapulicky@gmail.com", "1234567890"));
        payrollSystem.addEmployee(new Employee(2, "Sowmya Billa", "Developer", 60000, "Prasad Billa", "12/01/2002",
                "sowmyabilla@gmail.com", "9876543210"));
        payrollSystem.addEmployee(new Employee(3, "Bhagya Boduri", "Designer", 45000, "Raju Boduri", "16/12/2001",
                "bhagyaboduri@gmail.com", "4567891230"));
        PayrollSystemUI ui = new PayrollSystemUI(payrollSystem);
    }
}

class Employee {
    private int id;
    private String name;
    private String position;
    private double salary;
    private String fatherName;
    private String dateOfBirth;
    private String email;
    private String phoneNumber;

    public Employee(int id, String name, String position, double salary, String fatherName, String dateOfBirth,
            String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.fatherName = fatherName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Position: " + position + ", Salary: " + salary +
                ", Father's Name: " + fatherName + ", Date of Birth: " + dateOfBirth + ", Email: " + email +
                ", Phone Number: " + phoneNumber;
    }
}

class Salary {
    public static double calculateSalary(Employee employee, double bonus, double taxRate) {
        double totalSalary = employee.getSalary() + bonus;
        return totalSalary - (totalSalary * taxRate);
    }
}

class PayrollSystem {
    private List<Employee> employees;

    public PayrollSystem() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee findEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }
}

class PayrollSystemUI {
    private PayrollSystem payrollSystem;
    private JFrame frame;
    private JTextArea employeeTextArea;
    private NumberFormat currencyFormat;

    public PayrollSystemUI(PayrollSystem payrollSystem) {
        this.payrollSystem = payrollSystem;
        currencyFormat = NumberFormat.getCurrencyInstance();

        frame = new JFrame("Employee Payroll System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayEmployeeRecords();
            }
        });
        panel.add(refreshButton, BorderLayout.NORTH);

        JButton editButton = new JButton("Edit Employee");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editEmployee();
            }
        });
        panel.add(editButton, BorderLayout.SOUTH);

        employeeTextArea = new JTextArea();
        employeeTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(employeeTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void displayEmployeeRecords() {
        StringBuilder sb = new StringBuilder();
        sb.append("Employee Records:\n");
        for (Employee employee : payrollSystem.getEmployees()) {
            sb.append(employee.toString()).append("\n");
        }
        employeeTextArea.setText(sb.toString());
    }

    private void editEmployee() {
        String idStr = JOptionPane.showInputDialog(frame, "Enter Employee ID:");
        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            Employee employee = payrollSystem.findEmployeeById(id);
            if (employee != null) {
                String salaryStr = JOptionPane.showInputDialog(frame,
                        "Enter New Salary for " + employee.getName() + ":");
                if (salaryStr != null && !salaryStr.isEmpty()) {
                    double newSalary = Double.parseDouble(salaryStr);
                    employee.setSalary(newSalary);
                    displayEmployeeRecords();
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Employee not found.");
            }
        }
    }
}
