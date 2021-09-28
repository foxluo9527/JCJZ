package com.jcjz.one.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jcjz.one.R;
import com.jcjz.one.data.Bill;

import java.util.ArrayList;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder> {
    private Context context;
    private BillActionListener listener;
    private ArrayList<Bill> bills;

    public interface BillActionListener {
        void onBillSEdit(int position);

        void onBillDelete(int position);
    }

    public void setBillActionListener(BillActionListener listener) {
        this.listener = listener;
    }

    public BillAdapter(Context context, ArrayList<Bill> bills) {
        this.context = context;
        this.bills = bills;
    }

    @NonNull
    @Override
    public BillAdapter.BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BillViewHolder(LayoutInflater.from(context).inflate(R.layout.item_bill, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return bills.size();
    }

    class BillViewHolder extends RecyclerView.ViewHolder {
        View edit, del;
        TextView name, price, date;

        public BillViewHolder(@NonNull View itemView) {
            super(itemView);
            edit = itemView.findViewById(R.id.tv_edit);
            del = itemView.findViewById(R.id.tv_delete);
            name = itemView.findViewById(R.id.tv_name);
            price = itemView.findViewById(R.id.tv_price);
            date = itemView.findViewById(R.id.tv_date);
        }
    }
}
