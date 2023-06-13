package uz.jasurbekruzimov.medicalsupport;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class SignIn extends AppCompatActivity {

    TextInputEditText phoneNumber, password;
    TextView signUpPage;
    Button signInBtn;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        phoneNumber = findViewById(R.id.phoneEditTexId);
        password = findViewById(R.id.passwordEditTextId);
        signUpPage = findViewById(R.id.SingUpPage);
        signInBtn = findViewById(R.id.signInBtn);


        signInBtn.setOnClickListener(v -> {
                Intent intent = new Intent(SignIn.this, HomePage.class);
                startActivity(intent);
        });


        signUpPage.setOnClickListener(v -> {
            startActivity(new Intent(SignIn.this, SignUp.class));
        });


    }


}
