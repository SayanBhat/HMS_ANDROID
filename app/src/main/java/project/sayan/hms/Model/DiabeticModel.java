package project.sayan.hms.Model;

public class DiabeticModel{

    public  double glucose;
    public double bloodPressure ;
    public double bmi;
    public double insulin ;
    public double age ;

    public double decisionResult ;
    public double logisticProbability ;

    public final static String GLUCOSE="glucose";
    public final static  String BLOOD_PRESSURE="bloodPressure" ;
    public final static  String BMI="bmi";
    public final static  String INSULIN="insulin" ;
    public final static String AGE="age" ;
    public final static  String DECISION_RESULT="decisionResult" ;
    public final static String LOGISTIC_PROBABILITY="logisticProbability" ;




    public double getGlucose() {
        return glucose;
    }

    public void setGlucose(double glucose) {
        this.glucose = glucose;
    }

    public double getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(double bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getInsulin() {
        return insulin;
    }

    public void setInsulin(double insulin) {
        this.insulin = insulin;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
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

}
