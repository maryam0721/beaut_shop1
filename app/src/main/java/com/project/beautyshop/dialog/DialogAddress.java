package com.project.beautyshop.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.project.beautyshop.R;
import com.project.beautyshop.database.DatabaseShop;
import com.project.beautyshop.database.PersonDao;
import com.project.beautyshop.model.Person;
import com.project.beautyshop.view.AddressFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DialogAddress {
    Context context;
    PersonDao personDao;
    Dialog dialog;
    TextInputEditText textInputEditTextName;
    TextInputEditText textInputEditTextFamily;
    TextInputEditText textInputEditTextAddress;
    TextInputEditText textInputEditTextCity;
    Spinner spinnerCities;
    List<String> citiesName;
    ArrayAdapter<String> arrayAdapterCities;
    Button btnAddPerson;
    String name, family, address, city;
    Person person;

    public DialogAddress(Context context,PersonDao personDao) {
        this.context = context;
        this.personDao = personDao;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_dialog_address);
        setupDialogAddress();
        spinnerCities.setAdapter(arrayAdapterCities);
//        dialog.setCancelable(false);
        dialog.show();
        spinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city = citiesName.get(position);
                textInputEditTextCity.setText(city);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnAddPerson.setOnClickListener(v -> {
            getData();
            if (!name.equals("") && !family.equals("") && !address.equals("")) {
                addPerson();
            } else {
                Toast.makeText(context, "لطفا تمامی فیلد ها را پر کنید", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setupDialogAddress() {
        personDao = DatabaseShop.getAppDataBase(context).personDao();
        //FindViewById
        textInputEditTextName = dialog.findViewById(R.id.textInputEditTextName);
        textInputEditTextFamily = dialog.findViewById(R.id.textInputEditTextFamily);
        textInputEditTextAddress = dialog.findViewById(R.id.textInputEditTextAddress);
        textInputEditTextCity = dialog.findViewById(R.id.textInputEditTextCity);
        spinnerCities = dialog.findViewById(R.id.spinnerCities);
        btnAddPerson = dialog.findViewById(R.id.btnAddPerson);
        //Lists------
        citiesName = new ArrayList<>(Arrays.asList("شیراز", "بوشهر", "تهران", "رشت", "اصفهان", "بندر عباس"));
        arrayAdapterCities = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, citiesName);
    }
    public void addPerson() {
        getData();
        person = new Person(name, family, city, address);
        personDao.addPerson(person);
        AddressFragment.addressAdapter.updateList(personDao.getAllPerson());
        Toast.makeText(context, "ثبت شد", Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }

    public void getData() {
        name = textInputEditTextName.getText().toString();
        family = textInputEditTextFamily.getText().toString();
        address = textInputEditTextAddress.getText().toString();
        city = textInputEditTextCity.getText().toString();
    }
}
