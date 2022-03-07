package com.example.intentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button moveBtn;
    private EditText etText;
    private String str;
    private ImageView icBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moveBtn = findViewById(R.id.btn_move);
        etText = findViewById(R.id.et_text);
        icBtn = findViewById(R.id.icBtn);

        icBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "호호", Toast.LENGTH_SHORT).show();
            }
        });
        moveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                str = String.valueOf(etText.getText());
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("str", str);
                startActivity(intent);  //액티비티 이동
            }
        });
    }
}