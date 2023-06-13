package uz.jasurbekruzimov.medicalsupport;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class VerifySMS_Code extends AppCompatActivity {
    ImageView back;
    EditText smsCode1 , smsCode2 , smsCode3 , smsCode4 , smsCode5 ;
    LinearLayout resendCode;
    Button verifyCode;

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


        setupCodeInputs();


        verifyCode = findViewById(R.id.VerifySMSCodeId);

        verifyCode.setOnClickListener(v -> {

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
            else{
                Intent intent = new Intent(VerifySMS_Code.this, HomePage.class);
                startActivity(intent);
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
