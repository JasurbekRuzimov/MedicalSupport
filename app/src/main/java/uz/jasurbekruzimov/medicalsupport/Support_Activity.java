package uz.jasurbekruzimov.medicalsupport;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Support_Activity extends AppCompatActivity {

    LinearLayout backHome;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        backHome = findViewById(R.id.backHome11);
        backHome.setOnClickListener(v -> finish());



    }
}