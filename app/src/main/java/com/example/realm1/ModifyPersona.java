package com.example.realm1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class ModifyPersona extends AppCompatActivity {
    EditText name,surname,gender,age;
    Button btn_del;
    TextView dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_persona);

        dni = findViewById(R.id.modify_dni);
        name = findViewById(R.id.modify_name);
        surname = findViewById(R.id.modify_surname);
        age = findViewById(R.id.modify_age);
        gender = findViewById(R.id.modify_gender);
        btn_del = findViewById(R.id.btn_modify);

        Intent intent = getIntent();
        Integer age2 = intent.getIntExtra("age",2000);
        final String str_dni = intent.getStringExtra("dni");

        dni.setText(str_dni);
        name.setText(intent.getStringExtra("name"));
        surname.setText(intent.getStringExtra("surname"));
        age.setText(String.valueOf(age2));
        gender.setText(intent.getStringExtra("gender"));

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults<Persona> personas = realm.where(Persona.class).equalTo("dni",str_dni).findAll();
                        personas.setValue("name",name.getText().toString());
                        personas.setValue("surname",surname.getText().toString());
                        personas.setValue("age",Integer.parseInt(age.getText().toString()));
                        personas.setValue("gender",gender.getText().toString());
                    }
                });
                finish();
            }
        });
    }
}
