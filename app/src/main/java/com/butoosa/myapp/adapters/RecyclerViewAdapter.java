package com.butoosa.myapp.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.butoosa.myapp.Member;
import com.butoosa.myapp.Members;
import com.butoosa.myapp.R;
import com.butoosa.myapp.model.Anime;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myViewHolder>{
    private Context mContext;
    private List<Anime> mData;
    Dialog myDialog;
    RequestOptions option;
    //private static final String TAG = RecyclerViewAdapter.class.getSimpleName();

    public RecyclerViewAdapter(Context context, List<Anime> mData) {
        this.mContext = context;
        this.mData = mData;

        //request option from glide
         option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        /*View view;
        //LayoutInflater inflater = LayoutInflater.from(mContext);
        view = LayoutInflater.from(mContext).inflate(R.layout.anime_row_item, viewGroup, false);

        final myViewHolder viewHolder = new myViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"Item Clicked "+ String.valueOf(viewHolder.getAdapterPosition()),Toast.LENGTH_LONG).show();
            }
        });


        return viewHolder;*/


        View view ;


        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.anime_row_item,viewGroup,false) ;
        final myViewHolder viewHolder = new myViewHolder(view) ;
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, Member.class);
                i.putExtra("name",mData.get(viewHolder.getAdapterPosition()).getName());
                i.putExtra("course",mData.get(viewHolder.getAdapterPosition()).getCourse());
                i.putExtra("contact",mData.get(viewHolder.getAdapterPosition()).getContact());
                i.putExtra("sid",mData.get(viewHolder.getAdapterPosition()).getStudentID());
                i.putExtra("email",mData.get(viewHolder.getAdapterPosition()).getEmail());
                i.putExtra("anime_img",mData.get(viewHolder.getAdapterPosition()).getImg_url());

                mContext.startActivity(i);

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        myViewHolder.tvName.setText(mData.get(i).getName());
        myViewHolder.tv_course.setText(mData.get(i).getCourse());
        myViewHolder.tv_contact.setText(mData.get(i).getContact());

        //load image from the internet and set it.
        Glide.with(mContext).load(mData.get(i).getImg_url()).apply(option).into(myViewHolder.img_thumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName, tv_contact, tv_course,tv_email;
        ImageView img_thumbnail;
        LinearLayout view_container;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            view_container = (LinearLayout) itemView.findViewById(R.id.item_user);
            tvName = itemView.findViewById(R.id.a_name);
            tv_contact = itemView.findViewById(R.id.a_contact);
            tv_course = itemView.findViewById(R.id.a_course);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);
        }
        @Override
        public void onClick(View view) {

        }
        
    }
}
