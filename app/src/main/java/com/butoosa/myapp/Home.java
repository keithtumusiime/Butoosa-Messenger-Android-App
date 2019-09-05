package com.butoosa.myapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.butoosa.myapp.adapters.PostsAdapter;
import com.butoosa.myapp.adapters.RecyclerViewAdapter;
import com.butoosa.myapp.model.Anime;
import com.butoosa.myapp.model.Posts;
import com.butoosa.myapp.model.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.getIntent;

public class Home extends Fragment {

    TextView Name,SID;
    private Profile data;
    private final String json_url = "https://butoosa.online/androidApp/home.php";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Posts> postsList;
    private RecyclerView recyclerView;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        postsList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_posts);


        //RecyclerViewAdapter viewAdapter = new RecyclerViewAdapter();
        jsonReq();
        return view;
    }

    private void jsonReq() {
        request = new JsonArrayRequest(json_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject object = null;
                for (int i =0; i < response.length(); i++){
                    try {
                        object = response.getJSONObject(i);
                        Posts anime = new Posts();
                        anime.setPost_author(object.getString("author"));
                        anime.setPost_id(object.getString("id"));
                        anime.setPost_body(object.getString("body"));
                        anime.setPost_date(object.getString("date"));
                        anime.setPost_img_url(object.getString("photo"));
                        anime.setPost_sid(object.getString("sid"));
                        anime.setPost_image(object.getString("image"));
                        anime.setNo_of_likes(object.getString("likes"));
                        anime.setNo_of_comments(object.getString("comments"));

                        postsList.add(anime);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setUpRecyclerView(postsList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(this.getActivity());
        requestQueue.add(request);
    }

    private void setUpRecyclerView(List<Posts> postsLst) {
        PostsAdapter viewAdapter = new PostsAdapter(this.getActivity(),postsLst);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(viewAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dashboard");
    }
}
