package project.sayan.hms.Model;

/**
 * Created by Sayan on 3/21/2018.
 */

public class LiverAttributesModel {


    public String AGE="Age";
    public String GENDER="Gender"; //Male = 1 ,Female = 2
    public String TOTAL_BILIRUBIN="Total_Bilirubin";
    public String DIRECT_BILIRUBIN ="Direct_Bilirubin";
    public String ALKALINE_PHOSPHOTASE="Alkaline_Phosphotase";
    public String ALAMINE_AMINOTRANSFERASE ="Alamine_Aminotransferase";
    public String ASPARTATE_AMINOTRANSFERASE ="Aspartate_Aminotransferase";
    public String TOTAL_PROTIENS ="Total_Protiens";
    public String ALBUMIN ="Albumin";
    public String ALBUMIN_AND_GLOBULIN_RATIO= "Albumin_and_Globulin_Ratio";
    public String DATASET ="Dataset";
    public final static  String DECISION_RESULT="decisionResult" ;
    public final static String LOGISTIC_PROBABILITY="logisticProbability" ;






    private double Age;
    private double Gender ; //Male = 1 ,Female = 2 
    private double Total_Bilirubin ;
    private double Direct_Bilirubin ;
    private double Alkaline_Phosphotase ;
    private double Alamine_Aminotransferase ;
    private double Aspartate_Aminotransferase ;
    private double Total_Protiens ;
    private double Albumin;
    private double Albumin_and_Globulin_Ratio ;
    private double Dataset ;

    private double decisionResult ;
    private double logisticProbability;

    public double getAge() {
        return Age;
    }

    public void setAge(double age) {
        Age = age;
    }

    public double getGender() {
        return Gender;
    }

    public void setGender(double gender) {
        Gender = gender;
    }

    public double getTotal_Bilirubin() {
        return Total_Bilirubin;
    }

    public void setTotal_Bilirubin(double total_Bilirubin) {
        Total_Bilirubin = total_Bilirubin;
    }

    public double getDirect_Bilirubin() {
        return Direct_Bilirubin;
    }

    public void setDirect_Bilirubin(double direct_Bilirubin) {
        Direct_Bilirubin = direct_Bilirubin;
    }

    public double getAlkaline_Phosphotase() {
        return Alkaline_Phosphotase;
    }

    public void setAlkaline_Phosphotase(double alkaline_Phosphotase) {
        Alkaline_Phosphotase = alkaline_Phosphotase;
    }

    public double getAlamine_Aminotransferase() {
        return Alamine_Aminotransferase;
    }

    public void setAlamine_Aminotransferase(double alamine_Aminotransferase) {
        Alamine_Aminotransferase = alamine_Aminotransferase;
    }

    public double getAspartate_Aminotransferase() {
        return Aspartate_Aminotransferase;
    }

    public void setAspartate_Aminotransferase(double aspartate_Aminotransferase) {
        Aspartate_Aminotransferase = aspartate_Aminotransferase;
    }

    public double getTotal_Protiens() {
        return Total_Protiens;
    }

    public void setTotal_Protiens(double total_Protiens) {
        Total_Protiens = total_Protiens;
    }

    public double getAlbumin() {
        return Albumin;
    }

    public void setAlbumin(double albumin) {
        Albumin = albumin;
    }

    public double getAlbumin_and_Globulin_Ratio() {
        return Albumin_and_Globulin_Ratio;
    }

    public void setAlbumin_and_Globulin_Ratio(double albumin_and_Globulin_Ratio) {
        Albumin_and_Globulin_Ratio = albumin_and_Globulin_Ratio;
    }

    public double getDataset() {
        return Dataset;
    }

    public void setDataset(double dataset) {
        Dataset = dataset;
    }

    public double getDecisionResult() {
        return decisionResult;
    }

    public void setDecisionResult(double decisionResult) {
        this.decisionResult = decisionResult;
    }

    public double getLogisticProbability() {
        return logisticProbability;
    }

    public void setLogisticProbability(double logisticProbability) {
        this.logisticProbability = logisticProbability;
    }
;
}
