package uz.jasurbekruzimov.medicalsupport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uz.jasurbekruzimov.medicalsupport.api.ApiInterface;


public class SignUp extends AppCompatActivity {

    TextInputEditText phone, password, confirmPassword;
    TextView signIn, signIn1;
    Button signUpBtn;
    private ApiInterface apiInterface;

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
            if (Objects.requireNonNull(phone.getText()).toString().trim().isEmpty() || phone.getText().toString().trim().length() < 13) {
                phone.setError("Telefon raqam kiriting");
            } else if (Objects.requireNonNull(password.getText()).toString().trim().isEmpty() || password.getText().toString().trim().length() < 6) {
                password.setError("Parolni kiriting");
            } else if (Objects.requireNonNull(confirmPassword.getText()).toString().trim().isEmpty() || confirmPassword.getText().toString().trim().length() < 6) {
                confirmPassword.setError("Parolni tasdiqlang");
            } else if (!Objects.requireNonNull(password.getText()).toString().equals(confirmPassword.getText().toString().trim())) {
                confirmPassword.setError("Parol mos kelmadi");
            }
            else {
                Intent intent = new Intent(SignUp.this, VerifySMS_Code.class);
                startActivity(intent);
            }
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://google.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            ApiInterface.SignUpResponse signUpResponse = new ApiInterface.SignUpResponse();
            signUpResponse.setSuccess(true);
            signUpResponse.setMessage("Muvaffaqiyatli ro'yxatdan o'tdingiz");

            Call<ApiInterface.SignUpResponse> call = (Call<ApiInterface.SignUpResponse>) retrofit.create(ApiInterface.SignUpResponse.class);

            call.enqueue(new Callback<ApiInterface.SignUpResponse>() {
                @Override
                public void onResponse(@NonNull Call<ApiInterface.SignUpResponse> call, @NonNull Response<ApiInterface.SignUpResponse> response) {

                    if (response.isSuccessful()) {
                        ApiInterface.SignUpResponse signUpResponse = response.body();
                        assert signUpResponse != null;
                        boolean success = signUpResponse.isSuccess();
                        String message = signUpResponse.getMessage();


                        if (success) {
                            Intent intent = new Intent(SignUp.this, VerifySMS_Code.class);
                            intent.putExtra("phone", phone.getText().toString());
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(SignUp.this, message, Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(SignUp.this, "Server bilan bog'lanishda xatolik", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(@NonNull Call<ApiInterface.SignUpResponse> call, Throwable t) {

                    Toast.makeText(SignUp.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        });


    }
}