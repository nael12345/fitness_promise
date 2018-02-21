package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConPassword;
    private Button btnSignup;
    private FP_DB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        inlitialize ();
    }

    private void inlitialize() {

        etName = (EditText) findViewById(R.id.etName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConPassword = (EditText) findViewById(R.id.etConPassword);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        db = new FP_DB(this);

    }

    public void gotohome(View view) {

    }

    public void addUser(View view) {
        String name = etName.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConPassword.getText().toString();
        if (name.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
        {
            Toast.makeText(this, "Some fields are empty!!", Toast.LENGTH_LONG).show();

        }
        else{
            if(password.equals(confirmPassword))
            {
                if(db.getUser(username) != null)
                {
                    Toast.makeText(this, "Username already exists!!", Toast.LENGTH_LONG).show();

                }
                else {
                    User user = new User();
                    user.setName(name);
                    user.setUsername(username);
                    user.setPassword(password);
                    db.addUser(user);

                }

                // TODO: Give a message of succesfull user addition to database or move to main page
                //
            }
            else
                Toast.makeText(this, "Passwords do not match!!", Toast.LENGTH_LONG).show();

        }


    }

}
