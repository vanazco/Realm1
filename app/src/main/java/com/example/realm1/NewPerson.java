package com.example.realm1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

public class NewPerson extends AppCompatActivity {
    EditText dni,name,gender,age;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person);

        dni = findViewById(R.id.new_dni);
        name = findViewById(R.id.new_name);
        age = findViewById(R.id.new_age);
        gender = findViewById(R.id.new_gender);
        btn_add = findViewById(R.id.btn_add);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dni.getText().toString().isEmpty()){
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    Persona persona = realm.createObject(Persona.class,dni.getText().toString());
                    persona.setFullName(name.getText().toString());
                    persona.setAge(Integer.parseInt(age.getText().toString()));
                    persona.setGender(gender.getText().toString());
                    realm.commitTransaction();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Falta el ID!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
