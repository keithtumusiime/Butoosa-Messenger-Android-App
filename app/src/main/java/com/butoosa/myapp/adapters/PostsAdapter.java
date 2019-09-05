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
import com.butoosa.myapp.model.Posts;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.myViewHolder>{
    private Context mContext;
    private List<Posts> mData;
    Dialog myDialog;
    RequestOptions option;
    //private static final String TAG = RecyclerViewAdapter.class.getSimpleName();

    public PostsAdapter(Context context, List<Posts> mData) {
        this.mContext = context;
        this.mData = mData;

        //request option from glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        View view ;


        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.post_row,viewGroup,false) ;
        final myViewHolder viewHolder = new myViewHolder(view) ;
        /*viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
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
        });*/

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        myViewHolder.tvAuthor.setText(mData.get(i).getPost_author());
        myViewHolder.tv_share.setText("Posted On " +mData.get(i).getPost_date());
        myViewHolder.tv_body.setText(mData.get(i).getPost_body());
        myViewHolder.tv_likes.setText(mData.get(i).getNo_of_likes()+" Likes");
        myViewHolder.tv_comments.setText(mData.get(i).getNo_of_comments()+" Comments");


        //load image from the internet and set it.
        Glide.with(mContext).load(mData.get(i).getPost_img_url()).apply(option).into(myViewHolder.img_thumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvAuthor, tv_share, tv_body,tv_likes, tv_comments;
        ImageView img_thumbnail,post_thumbnail;
        LinearLayout view_container;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            view_container = (LinearLayout) itemView.findViewById(R.id.item_post);
            tvAuthor = itemView.findViewById(R.id.a_name);
            tv_share = itemView.findViewById(R.id.a_share);
            tv_body = itemView.findViewById(R.id.a_body);
            tv_likes = itemView.findViewById(R.id.no_of_likes);
            tv_comments = itemView.findViewById(R.id.no_of_comments);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);
            post_thumbnail = itemView.findViewById(R.id.post_thumbnail);
        }
        @Override
        public void onClick(View view) {

        }

    }
}
