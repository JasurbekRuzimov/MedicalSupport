package uz.jasurbekruzimov.medicalsupport;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class SignUp extends AppCompatActivity {

    TextInputEditText phone, password, confirmPassword;
    TextView signIn, signIn1;
    Button signUpBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        phone = findViewById(R.id.phoneEditTexId);
        password = findViewById(R.id.passwordEditTextId);
        confirmPassword = findViewById(R.id.confirmPasswordEditTextId);
        signIn = findViewById(R.id.alreadyHaveAnAccount);
        signIn1 = findViewById(R.id.alreadyHaveAnAccount1);
        signUpBtn = findViewById(R.id.signUpBtn);


        signIn.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this, SignIn.class);
            startActivity(intent);
        });
        signIn1.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this, SignIn.class);
            startActivity(intent);
        });


        signUpBtn.setOnClickListener(v -> {
            if (Objects.requireNonNull(phone.getText()).toString().trim().isEmpty()) {
                phone.setError("Telefo raqam kiriting");
            } else if (Objects.requireNonNull(password.getText()).toString().trim().isEmpty()) {
                password.setError("Parolni kiriting");
            } else if (Objects.requireNonNull(confirmPassword.getText()).toString().trim().isEmpty()) {
                confirmPassword.setError("Parolni tasdiqlang");
            } else if (!Objects.requireNonNull(password.getText()).toString().equals(confirmPassword.getText().toString().trim())) {
                confirmPassword.setError("Parol mos kelmadi");
            } else {
                Intent intent = new Intent(SignUp.this, VerifySMS_Code.class);
                startActivity(intent);
            }

        });
//            @GET("api/v1/auth/register")
//            Call<SignUpResponse> signUpResponseCall = ApiClient.getUserService().userSignUp(new SignUpRequest(phone.getText().toString().trim(), password.getText().toString().trim(), confirmPassword.getText().toString().trim()));
//            signUpResponseCall.enqueue(new Callback<SignUpResponse>() {
//                @Override
//                public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
//                    if (response.isSuccessful()) {
//                        Toast.makeText(SignUp.this, "Success", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(SignUp.this, "Error", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<SignUpResponse> call, Throwable t) {
//                    Toast.makeText(SignUp.this, "Error", Toast.LENGTH_SHORT).show();
//                }
//            });


    }
}