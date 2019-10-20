package com.example.trquality;

import java.io.Serializable;

    /**
     * @author Chrissie Ediker
     */
    public class TestingResults implements Serializable {
        //jobNumber getter
        public String getJobNumber() {
            return jobNumber;
        }
        //jobNumber setter
        public void setJobNumber(String jobNumber) {
            this.jobNumber = jobNumber;
        }

        // declares private variable jobNumber (accessible only within its own class)
        private String jobNumber;

        //roll getter
        public String getRoll() {
            return roll;
        }

        //roll setter
        public void setRoll(String roll) {
            this.roll = roll;
        }

        // declares private variable roll (accessible only within its own class)
        private String roll;

        //productionStandard getter
        public String getProductionStandard() {
            return productionStandard;
        }

        //productionStandardSetter
        public void setProductionStandard(String productionStandard) {
            this.productionStandard = productionStandard;
        }

        // declares private variable productionStandard (accessible only within its own class)
        private String productionStandard;

        //DateTimeSubmit getter
        public String getDateTimeSubmit() {
            return dateTimeSubmit;
        }

        //DateTimeSubmit setter
        public void setDateTimeSubmit(String dateTimeSubmit) {
            this.dateTimeSubmit = dateTimeSubmit;
        }

        // declares private variable dateTimeSubmit (accessible only within its own class)
        private String dateTimeSubmit;

        //TestMethod getter
        public String getTestMethod() {
            return testMethod;
        }

        //TestMethod setter
        public void setTestMethod(String testMethod) {
            this.testMethod = testMethod;
        }

        // declares private variable testMethod (accessible only within its own class)
        private String testMethod;

        //testResult getter
        public String getTestResult() {
            return testResult;
        }

        //testResult setter
        public void setTestResult(String testResult) {
            this.testResult = testResult;
        }

        // declares private variable testResult (accessible only within its own class)
        private String testResult;



    }





