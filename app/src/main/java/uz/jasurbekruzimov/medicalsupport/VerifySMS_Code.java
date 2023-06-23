package uz.jasurbekruzimov.medicalsupport;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uz.jasurbekruzimov.medicalsupport.api.ApiInterface;

public class VerifySMS_Code extends AppCompatActivity {
    ImageView back;
    EditText smsCode1 , smsCode2 , smsCode3 , smsCode4 , smsCode5 ;
    LinearLayout resendCode;
    Button verifyButton;


    private String phone;
     private Retrofit retrofit;
     private ApiInterface apiInterface;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_sms_code);


        TextView numberPhone = findViewById(R.id.numberMobile);
//        numberPhone.setText(String.format(
//                "+998 %s", getIntent().getStringExtra("phone")
//        ));

        smsCode1 = findViewById(R.id.firstNumberId1);
        smsCode2 = findViewById(R.id.secondNumberId2);
        smsCode3 = findViewById(R.id.thirdNumberId3);
        smsCode4 = findViewById(R.id.fourthNumberId4);
        smsCode5 = findViewById(R.id.fifthNumberId5);

        //Retrofit obyektini yaratish va API interfeysini olish
        retrofit = new Retrofit.Builder()
                .baseUrl("https://example.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);



        setupCodeInputs();


        verifyButton = findViewById(R.id.VerifySMSCodeId);

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SMS kodini olish
                String code = smsCode1.getText().toString().trim() +
                        smsCode2.getText().toString().trim() +
                        smsCode3.getText().toString().trim() +
                        smsCode4.getText().toString().trim() +
                        smsCode5.getText().toString().trim();

                //SMS kodini tekshirish, agar bo'sh bo'lsa xabar chiqarish

                if (smsCode1.getText().toString().isEmpty()){
                    smsCode1.setError("");
                }
                if (smsCode2.getText().toString().trim().isEmpty()){
                    smsCode2.setError("");
                }
                if (smsCode3.getText().toString().isEmpty()){
                    smsCode3.setError("");
                }
                if (smsCode4.getText().toString().isEmpty()){
                    smsCode4.setError("");
                }
                if (smsCode5.getText().toString().isEmpty()){
                    smsCode5.setError("");
                }


                //Serverga so'rov yuborish uchun Call obyektini yaratish
                Call<ApiInterface.VerifyResponse> call = apiInterface.verify(phone, code);

                //So'rovni asinxron tarzda bajarish va javobni qabul qilish
                call.enqueue(new Callback<ApiInterface.VerifyResponse>() {
                    @Override
                    public void onResponse(Call<ApiInterface.VerifyResponse> call, Response<ApiInterface.VerifyResponse> response) {
                        //Javob muvaffaqiyatli bo'lsa, VerifyResponse obyektini olish
                        if (response.isSuccessful()) {
                            ApiInterface.VerifyResponse verifyResponse = response.body();

                            //VerifyResponse obyektidan muvaffaqiyatli bo'lganligi va xabarini olish
                            boolean success = verifyResponse.isSuccess();
                            String message = verifyResponse.getMessage();

                            //Agar muvaffaqiyatli bo'lsa, ilovaga kirish uchun yangi oyna ochish
                            if (success) {
                                //Yangi oynaga o'tish uchun Intent yaratish
                                Intent intent = new Intent(VerifySMS_Code.this, MainActivity.class);

                                //Yangi oynani boshlash
                                startActivity(intent);

                                //Joriy oynani tugatish
                                finish();
                            }

                            //Agar muvaffaqiyatsiz bo'lsa, xabar chiqarish
                            else {
                                Toast.makeText(VerifySMS_Code.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }

                        //Javob muvaffaqiyatsiz bo'lsa, xatolik chiqarish
                        else {
                            Toast.makeText(VerifySMS_Code.this, "Xatolik yuz berdi", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiInterface.VerifyResponse> call, Throwable t) {
                        //So'rovda xatolik bo'lsa, xatolik chiqarish
                        Toast.makeText(VerifySMS_Code.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        back = findViewById(R.id.backId);
        back.setOnClickListener(v -> {
            onBackPressed();
            finishActivity(1);
        });

        resendCode = findViewById(R.id.ResendSMSCodeId);
        resendCode.setOnClickListener(v -> {
           onBackPressed();
        });
    }

    private void setupCodeInputs(){
        smsCode1.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(smsCode1.getText().toString().length() == 1){
                    smsCode2.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(smsCode1.getText().toString().length() == 1){
                    smsCode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}

        });

        smsCode2.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(smsCode2.getText().toString().length() == 1){
                    smsCode3.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(smsCode2.getText().toString().length() == 1){
                    smsCode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}

        });

        smsCode3.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(smsCode3.getText().toString().length() == 1){
                    smsCode4.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(smsCode3.getText().toString().length() == 1){
                    smsCode4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}

        });

        smsCode4.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(smsCode4.getText().toString().length() == 1){
                    smsCode5.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(smsCode4.getText().toString().length() == 1){
                    smsCode5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}

        });
    }

}
