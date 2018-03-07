package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private FP_DB db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        initialize ();
    }

    private void initialize() {
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        db = new FP_DB(this);

    }

    public void gotoHomeLogin(View view) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        User user = db.getUser(username);

        if (user == null)
        {
            // TODO: message...
        }
        else
        {
            if (user.getUsername().equals("admin")&&user.getPassword().equals("admin"))
            {
                // TODO: open admin page
            }
            else {
                if (password.equals(user.getPassword())) {
                    Toast.makeText(this, "You have successfully logged in!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Wrong username or password!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
