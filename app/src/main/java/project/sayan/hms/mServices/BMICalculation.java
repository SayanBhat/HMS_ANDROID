package project.sayan.hms.mServices;

/**
 * Created by Sayan Bhattacharya on 10/18/2017.
 */

public class BMICalculation {

    private double weight,height;
    private double bmiResult;
    private String message ;
    private String textCategory;

    private static final int Very_Severely_Underweight=1;
    private static final int Severely_Underweight=2;
    private static final int Underweight=3;
    private static final int NORMAL=4;
    private static final int Overweight=5;
    private static final int Moderately_Obese=6;
    private static final int Severely_Obese =7;
    private static final int Very_Moderately_Obese=8;


    private static final String sVery_Severely_Underweight="Very Severely Underweight";
    private static final String sSeverely_Underweight="Severely Underweight";
    private static final String sUnderweight="Underweight";
    private static final String sNORMAL="Normal/Healthy";
    private static final String sOverweight="Overweight";
    private static final String sModerately_Obese="Moderately Obese";
    private static final String sSeverely_Obese ="Severely Obese";
    private static final String sVery_Moderately_Obese="Very Moderately Obese";


    public void setweight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
        this.height= height/100;
    }

    public double getBmiResult() {
        return bmiResult;
    }

    public void calcBMI(){
        bmiResult= weight /(height * height);
        getCategory(bmiResult);
    }

    private void setMessage(int category) {
        if(category>=1 && category <=3){
            this.message="Your weight is less than it ideally should be. See your doctor or health professional and discuss whether you may need to aim at gaining weight. Being underweight may be associated with lack of some vitamins and minerals that can affect important body functions, such as your immune response to infection and fertility or it can lead to a multitude of health issues from heart disease to bone problems. ";
        }
        else if(category==4){
            this.message="Your BMI is in the ideal range. But that doesn't mean that you become complacent! Just keep doing what you have been. Go for a balanced diet, exercise regularly and enjoy life!";
        }
        else if(category==5 || category ==6){
            this.message="Your weight appears to be a bit above the ideal range. You should consider losing a few kilograms. You might like to talk to your doctor about whether you need to set yourself a new target for a healthy weight. \n" +
                    "\n" +
                    "To lose weight, you will generally need to decrease the amount of energy (food) you take in by eating fewer kilojoules as part of a healthy diet; and to make it easier still, increase the amount of energy you use by doing more physical activity. If you are at all concerned or have any health problems, check with your doctor before you start any new exercise programs or eating plans. And do not be disheartened or de-motivated if progress is slow as you try to get your weight within a healthy range. ";
        }
        else if(category>6){
            this.message="You currently weigh more than is ideal. This puts your health at risk and is of increasing concern as you get older. It is generally recommended that you take action to reduce your weight and BMI for the sake of a healthier future. \n" +
                    "\n" +
                    "You can talk to your doctor about your BMI and discuss an appropriate and healthy weight for you. Being a healthy weight has important benefits, not only on how you feel, but also because being overweight or obese can lead to a multitude of health issues from heart disease, bone and joint problems as well as increase your risk of some cancers, sleep apnoea and type 2 diabetes. To lose weight, you will generally need to decrease the amount of energy (food) you take in by eating fewer kilojoules as part of a healthy diet; and to make it easier still, increase the amount of energy you use by doing more physical activity. ";
        }
    }

    public String getMessage() {
        return message;
    }

    private int  getCategory(double bmiResult) {
        int category = 0;
        if(bmiResult<15) {
            category = Very_Severely_Underweight;
            setMessage(category);
            setTextCategory(sVery_Severely_Underweight);
        }
        else if(bmiResult >= 15.0 && bmiResult < 16) {
            category = Severely_Underweight;
            setMessage(category);
            setTextCategory(sSeverely_Underweight);
        }
        else if(bmiResult >= 16 && bmiResult < 18.5) {
            category = Underweight;
            setMessage(category);
            setTextCategory(sUnderweight);
        }
        else if(bmiResult >= 18.5 && bmiResult < 25) {
            category = NORMAL;
            setMessage(category);
            setTextCategory(sNORMAL);
        }
        else if(bmiResult >= 25 && bmiResult < 30) {
            category = Overweight;
            setMessage(category);
            setTextCategory(sOverweight);
        }
        else if(bmiResult >= 30 && bmiResult < 35) {
            category = Moderately_Obese;
            setMessage(category);
            setTextCategory(sModerately_Obese);
        }
        else if(bmiResult >= 35 && bmiResult <40) {
            category = Severely_Obese;
            setMessage(category);
            setTextCategory(sSeverely_Obese);
        }
        else if(bmiResult >= 40) {
            category = Very_Moderately_Obese;
            setMessage(category);
            setTextCategory(sVery_Moderately_Obese);
        }

        return category;
    }

    public String getTextCategory() {
        return textCategory;
    }

    public void setTextCategory(String textCategory) {
        this.textCategory = textCategory;
    }
}
