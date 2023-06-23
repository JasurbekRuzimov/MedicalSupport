package uz.jasurbekruzimov.medicalsupport;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class FAQ_activity extends AppCompatActivity {
LinearLayout backHome;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        backHome=findViewById(R.id.backHome);
        backHome.setOnClickListener(v -> {
            finish();
        });




    }
}