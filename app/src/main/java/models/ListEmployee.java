package models;

import java.util.ArrayList;
import java.util.List;


public class ListEmployee {
    private List<Employee> employees;

    public ListEmployee() {
        employees=new ArrayList<>();
    }

    public List<Employee> getEmployees() {
        return employees;
    }
    public void generate_sample_dataset()
    {
        Employee e1=new Employee();
        e1.setName("John");
        e1.setEmail("join@gmail.com");
        e1.setUsername("john");
        e1.setPassword("123");
        employees.add(e1);

        Employee e2=new Employee();
        e2.setName("Peter");
        e2.setEmail("peter@gmail.com");
        e2.setUsername("peter");
        e2.setPassword("456");
        employees.add(e2);

        Employee e3=new Employee();
        e3.setName("Tom");
        e3.setEmail("tom@gmail.com");
        e3.setUsername("tom");
        e3.setPassword("789");
        employees.add(e3);
    }
}
