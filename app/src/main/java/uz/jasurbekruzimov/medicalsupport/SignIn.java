package uz.jasurbekruzimov.medicalsupport;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uz.jasurbekruzimov.medicalsupport.api.ApiClient;
import uz.jasurbekruzimov.medicalsupport.api.ApiInterface;
import uz.jasurbekruzimov.medicalsupport.api.LoginResponse;

public class SignIn extends AppCompatActivity {

    TextInputEditText phoneNumber, password;
    TextView signUpPage;
    Button signInBtn;
    ProgressBar progressBar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        phoneNumber = findViewById(R.id.phoneLoginId);
        password = findViewById(R.id.passwordLoginId);
        signUpPage = findViewById(R.id.SingUpPage);
        signInBtn = findViewById(R.id.signInBtn);
        progressBar = findViewById(R.id.progress_bar);


        signInBtn.setOnClickListener(v -> {
            //Telefon raqam va parolni olish
            String phone = Objects.requireNonNull(phoneNumber.getText()).toString().trim();
            String pass = Objects.requireNonNull(password.getText()).toString().trim();

            //Telefon raqam va parolni tekshirish, agar bo'sh bo'lsa xabar chiqarish
            if (phone.isEmpty() || phone.length() < 13) {
                phoneNumber.setError("Telefon raqamni to'liq kiriting");
                phoneNumber.requestFocus();
                return;
            }
            if (pass.isEmpty() || pass.length() < 6) {
                password.setError("Parol kamida 6 ta belgidan iborat bo'lishi kerak");
                password.requestFocus();
                return;
            }

            //Serverga so'rov yuborish uchun LoginApi interfeysini olish
            ApiInterface loginApi = ApiClient.getLoginApi();

            //Serverga so'rov yuborish uchun Call obyektini yaratish
            Call<LoginResponse> call = loginApi.loginWithCredentials(phone, pass);
            progressBar.setIndeterminate(true);
            //Asinxron so'rovni boshlash
            //So'rovni asinxron tarzda bajarish va javobni qabul qilish
            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    //Javob muvaffaqiyatli bo'lsa, LoginResponse obyektini olish
                    if (response.isSuccessful()) {
                        LoginResponse loginResponse = response.body();

                        //LoginResponse obyektidan muvaffaqiyatli bo'lganligi va xabarini olish
                        boolean success = loginResponse.isSuccess();
                        String message = loginResponse.getMessage();

                        //Agar muvaffaqiyatli bo'lsa, ilovaga kirish uchun yangi oyna ochish
                        if (success) {
                            //Yangi oynaga o'tish uchun Intent yaratish
                            Intent intent = new Intent(SignIn.this, HomePage.class);

                            //Yangi oynani boshlash
                            startActivity(intent);

                            //Joriy oynani tugatish
                            finish();
                        }

                        //Agar muvaffaqiyatsiz bo'lsa, xabar chiqarish
                        else {
                            Toast.makeText(SignIn.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    //Javob muvaffaqiyatsiz bo'lsa, xatolik chiqarish
                    else {
                        Toast.makeText(SignIn.this, getString(R.string.server_error), Toast.LENGTH_SHORT).show();
                    }
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    //So'rovda xatolik bo'lsa, xatolik chiqarish
                    Toast.makeText(SignIn.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });


        signUpPage.setOnClickListener(v -> {
            startActivity(new Intent(SignIn.this, SignUp.class));
        });


    }


}
