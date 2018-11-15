package qin.com.coodinatorqin.tablayout;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import qin.com.coodinatorqin.R;

public class MyViewAdapter extends RecyclerView.Adapter<MyViewAdapter.MyHolder>{

    private Context context;
    private List<String> datas;

    public MyViewAdapter(Context context, List<String> datas) {
        this.context=context;
        this.datas=datas;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.item_tablayout,parent,false));
    }

    @Override
    public int getItemCount() {
        return datas!=null?datas.size():0;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.tv.setText(datas.get(position));
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,SecondTablayoutActivity.class));
            }
        });
    }

    public static class MyHolder  extends RecyclerView.ViewHolder {
        TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.tv);
        }
    }

}
