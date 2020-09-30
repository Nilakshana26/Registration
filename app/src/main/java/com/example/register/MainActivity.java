package com.example.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText e1,e2,e3;
    Button register;
    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DatabaseHelper(this);
        awesomeValidation =new AwesomeValidation(ValidationStyle.BASIC);


        e1=(EditText)findViewById(R.id.email);

        e2=(EditText)findViewById(R.id.password);
        e3=(EditText)findViewById(R.id.cpass);

        register=findViewById(R.id.register);

        awesomeValidation.addValidation(MainActivity.this,R.id.email, android.util.Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        awesomeValidation.addValidation(MainActivity.this,R.id.pass,".{6}",R.string.invalid_password);
        awesomeValidation.addValidation(MainActivity.this,R.id.cpass,R.id.pass,R.string.invalid_confirmpassword);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()) {
                    String s1 = e1.getText().toString();
                    String s2 = e2.getText().toString();
                    String s3 = e3.getText().toString();



                if(s1.equals("")||s2.equals("")||s3.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(s2.equals(s3)){
                        Boolean chkemail=db.chkemail(s1);
                        if(chkemail==true){
                            Boolean insert= db.insert(s1,s2);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(),"Registered Sucessfully",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Email already exits",Toast.LENGTH_SHORT).show();
                        }

                    }}
                }
            }
        });
    }
}