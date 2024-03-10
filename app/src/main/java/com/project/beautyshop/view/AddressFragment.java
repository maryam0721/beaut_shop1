package com.project.beautyshop.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.project.beautyshop.dialog.DialogAddress;
import com.project.beautyshop.R;
import com.project.beautyshop.adapter.AddressAdapter;
import com.project.beautyshop.database.DatabaseShop;
import com.project.beautyshop.database.PersonDao;
import com.project.beautyshop.listener.UpdateList;
import com.project.beautyshop.model.Person;


import java.util.List;

public class AddressFragment extends Fragment implements UpdateList {
    DialogAddress dialogAddress;
    PersonDao personDao;
    Button btnNew;
    RecyclerView recyclerViewPerson;
    public static AddressAdapter addressAdapter;
    List<Person> personList;
    TextView txtEmptyAddress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setup();
        try {

            checkAddress();
            recyclerViewPerson.setAdapter(addressAdapter);
            recyclerViewPerson.setLayoutManager(new LinearLayoutManager(getActivity()));

            //Remove Item Whit TouchHelper--------
            ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback
                    (0, ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    Person person = addressAdapter.getPersonList().get(viewHolder.getAbsoluteAdapterPosition());
                    removeItemAddress(person);
                    checkAddress();

                }
            });
            touchHelper.attachToRecyclerView(recyclerViewPerson);

            btnNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogAddress = new DialogAddress(getActivity(),personDao);
                }
            });
        }catch (Exception exception){
            Log.d("2323", exception.getMessage());
        }
    }

    public void setup() {
        personDao = DatabaseShop.getAppDataBase(getActivity()).personDao();
        //FindViewById--------
        btnNew = getActivity().findViewById(R.id.btnNew);
        recyclerViewPerson = getActivity().findViewById(R.id.recyclerViewPerson);
        txtEmptyAddress = getActivity().findViewById(R.id.txtEmptyAddress);
        //Lists---------------
        personList = personDao.getAllPerson();
        //initialize----------
        addressAdapter = new AddressAdapter(getActivity(), personList,this);
    }
    public void removeItemAddress(Person person) {
        personDao.deletePerson(person);
        addressAdapter.updateList(personDao.getAllPerson());
    }
    public void checkAddress() {
        if (personDao.getAllPerson().size()==0) {
//            recyclerViewPerson.setVisibility(View.INVISIBLE);
            txtEmptyAddress.setVisibility(View.VISIBLE);
        } else {
//            recyclerViewPerson.setVisibility(View.VISIBLE);
            txtEmptyAddress.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void updateList() {
        checkAddress();
    }
}
