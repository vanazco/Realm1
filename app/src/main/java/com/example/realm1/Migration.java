package com.example.realm1;

import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class Migration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();


        /* Version 0
        class Persona
        @PrimaryKey
        private String dni;
        private String name;
        private String surname;
        private int age;
        private String gender;

        Version 1
        class Persona

        @PrimaryKey
        private String dni;

        @Required @Index
        private String fullName;
        private int age;
        private String gender;
         */

        if(oldVersion == 0){
            Log.d("Migration", "actualizant a la versio 1");

            RealmObjectSchema personaSchema = schema.get("Persona");

            personaSchema
                    .addField("fullName", String.class, FieldAttribute.REQUIRED)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            obj.set("fullName",obj.getString("name")+ " " + obj.getString("surname"));
                        }
                    })
                    .addIndex("fullName")
                    .removeField("name")
                    .removeField("surname");
            oldVersion++;
        }
    }
}
