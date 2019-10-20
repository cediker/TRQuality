package com.example.trquality;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JobInformation extends AppCompatActivity {
    //declares variables
    String search;
    TableLayout infoTable;
    ArrayList<String> allTestResults = new ArrayList<>();
    ArrayList<TestingResults> resultArray = new ArrayList<>();
    TextView jobNumber;
    TextView roll;
    TextView productionStandard;
    TextView QCDate;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_information);
        //assigns value to variables
        infoTable = findViewById(R.id.infoTable);
        jobNumber = findViewById(R.id.jobNumber);
        roll = findViewById(R.id.roll);
        button= findViewById(R.id.button);
        productionStandard = findViewById(R.id.productionStandard);
        QCDate = findViewById(R.id.QCDate);
        search = MainActivity.search;

        //declares on click listener
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(JobInformation.this, MainActivity.class);
                startActivity(i);
            }
        });

        //declares new connectMySQL
        ConnectMySql connectMySql = new ConnectMySql();
        connectMySql.execute("");



        }

        private class ConnectMySql extends AsyncTask<String, Void, ArrayList<String>> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();


            }
            //runs code in background
            @Override
            protected ArrayList<String> doInBackground(String... params) {
                try {
                    //declares String array and splits search element using ':'
                    String[] parts = search.split(":");
                    //gets the two split Strings
                    String jobNumberSearch = parts[0]; // 004
                    String rollNumberSearch = parts[1];
                    Class.forName("com.mysql.jdbc.Driver");
                    //SQL connection statement
                    Connection con = DriverManager.getConnection("jdbc:mysql://104.155.111.28:3306/QualityControl", "root", "Tullis200");
                    System.out.println("Database Connection Success");
                    //declares statement and runs create statement method
                    Statement st = con.createStatement();
                    //executes SQL query
                    ResultSet rs = st.executeQuery("SELECT * FROM TestResult where JobNumber = '"+ jobNumberSearch +"' AND Roll = '"+ rollNumberSearch +"';");

                    //loops through results and adds them to the resultArray
                    while (rs.next()) {
                        resultArray.add(getNextTestResult(rs));
                    }
                } catch (Exception se) {
                    System.out.println(se);
                }

                return allTestResults;
            }
            //runs code after the 'run in background' section
            @Override
            protected void onPostExecute(ArrayList<String> result) {
                //calls buildTable method
               buildTable();
            }
        }


    private TestingResults getNextTestResult(ResultSet rs) {
        TestingResults thisTestResult = null;
        try {
            //declares a new TestResults object
            thisTestResult = new TestingResults();
            //assigns elements to the new test result
            thisTestResult.setJobNumber (rs.getString("jobNumber"));
            thisTestResult.setRoll(rs.getString("roll"));
            thisTestResult.setProductionStandard(rs.getString("productionStandard"));
            thisTestResult.setDateTimeSubmit(rs.getString("DateTimeSubmit"));
            thisTestResult.setTestMethod(rs.getString("testMethod"));
            thisTestResult.setTestResult(rs.getString("testResult"));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }

        return thisTestResult;
    }

    //the buildTable method
    public void buildTable(){
        infoTable.setGravity(Gravity.CENTER);
        //sets text
        jobNumber.setText(resultArray.get(1).getJobNumber());
        roll.setText(resultArray.get(1).getRoll());
        productionStandard.setText(resultArray.get(1).getProductionStandard());
        QCDate.setText(resultArray.get(1).getDateTimeSubmit());
        //builds table of results
        for (int i = 0; i < resultArray.size(); i++) {
            TableRow tr = new TableRow(this);
            tr.setPadding(10,10,10,30);
            TextView tv = new TextView(this);
            TextView tv2 = new TextView(this);

            tv.setWidth(750);
            tv2.setWidth(500);

            tv.setText(resultArray.get(i).getTestMethod());
            tv2.setText(resultArray.get(i).getTestResult());
            tr.addView(tv);
            tr.addView(tv2);
            infoTable.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        }
    }



    }



