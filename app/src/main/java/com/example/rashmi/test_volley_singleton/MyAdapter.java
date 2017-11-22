package com.example.rashmi.test_volley_singleton;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by QCS2015 on 22-11-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<DataProvider> mList;
    private Context mctxt;
    private LayoutInflater inflater;

    public MyAdapter(List<DataProvider> mList, Context mctxt) {
        this.mList = mList;
        this.mctxt = mctxt;
       inflater=(LayoutInflater)mctxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.item_list,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        DataProvider data = mList.get(position);
        holder.title.setText(data.getDp_title());
        holder.content.setText(data.getDp_content());
        holder.imageView.setImageUrl(data.getDp_image(),NetworkController.getInstance(mctxt).getImageLoader());
        holder.ratingbar.setProgress(data.getDp_ratebar());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView content,title;
        private ProgressBar ratingbar;
        private NetworkImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.tv_text1);
            content=(TextView)itemView.findViewById(R.id.content_view);
            ratingbar=(ProgressBar)itemView.findViewById(R.id.ratingbar_view);
            imageView=(NetworkImageView)itemView.findViewById(R.id.niv_image);
                ratingbar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mctxt,"Rated by user: " + mList.get(getAdapterPosition()).getDp_ratebar(),Toast.LENGTH_SHORT).show();
                    }
                });


        }
    }
}
