package com.example.uitls;

public class HealthCare {
    public static BMIStatus calculate_bmi(double height, double weight)
    {
        double BMI=weight/Math.pow(height,2);
        String des="";
        if (BMI<18.5){
            des="Gầy trơ xương";
        }
        else if(BMI<23)
        {
            des="Bình thường";
        }
        else {
            des="Mập";
        }
        BMIStatus bmiStatus=new BMIStatus(BMI,des);
        return bmiStatus;
    }
}
