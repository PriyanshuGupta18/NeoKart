package com.example.neokart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.neokart.R;
import com.example.neokart.models.AddressModel;

import java.util.ArrayList;
import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    Context context;
    public List<AddressModel> addressModelList;
    SelectedAddress selectedAddress;

    private RadioButton selectedRadioBtn;

    public AddressAdapter(Context context, ArrayList<AddressModel> addressModelList, SelectedAddress selectedAddress) {
        this.context = context;
        this.selectedAddress = selectedAddress;
        this.addressModelList = addressModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.address.setText(addressModelList.get(holder.getAdapterPosition()).getUserAddress());
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(AddressModel address:addressModelList)
                {
                    address.setSelected(false);
                }
                addressModelList.get(holder.getAdapterPosition()).setSelected(true);

                if(selectedRadioBtn!=null)
                {
                    selectedRadioBtn.setChecked(false);
                }
                selectedRadioBtn=(RadioButton) view;
                selectedRadioBtn.setChecked(true);
                selectedAddress.setAddress(addressModelList.get(holder.getAdapterPosition()).getUserAddress());
            }
        });

    }

    @Override
    public int getItemCount() {
        return addressModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView address;
        RadioButton radioButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            address=itemView.findViewById(R.id.address_add);
            radioButton=itemView.findViewById(R.id.select_address);
        }
    }

    public interface SelectedAddress{
        void setAddress(String address);
    }
}
