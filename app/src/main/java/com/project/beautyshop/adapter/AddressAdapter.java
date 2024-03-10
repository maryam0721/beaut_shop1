package com.project.beautyshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.beautyshop.R;
import com.project.beautyshop.listener.UpdateList;
import com.project.beautyshop.model.Person;
import com.project.beautyshop.model.Product;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.DialogBasketViewHolder> {
    Context context;
    List<Person> personList;
    UpdateList updateList;

    public AddressAdapter(Context context, List<Person> personList,UpdateList updateList) {
        this.context = context;
        this.personList = personList;
        this.updateList = updateList;
    }


    @NonNull
    @Override
    public DialogBasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_person, parent, false);
        return new DialogBasketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DialogBasketViewHolder holder, int position) {
        Person person = personList.get(position);
        holder.txtNameAddress.setText(person.getName());
        holder.txtFamilyAddress.setText(person.getFamily());
        holder.txtAddress.setText(person.getAdress());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    class DialogBasketViewHolder extends RecyclerView.ViewHolder {

        TextView txtNameAddress;
        TextView txtFamilyAddress;
        TextView txtAddress;
        public DialogBasketViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameAddress = itemView.findViewById(R.id.txtNameAddress);
            txtFamilyAddress = itemView.findViewById(R.id.txtFamilyAddress);
            txtAddress = itemView.findViewById(R.id.txtAddress);
        }
    }
    public void updateList(List<Person> personList){
        this.personList = personList;
        updateList.updateList();
        this.notifyDataSetChanged();
    }
    public List<Person> getPersonList() {
        return personList;
    }
}
