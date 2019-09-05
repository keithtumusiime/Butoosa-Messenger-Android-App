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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.butoosa.myapp.adapters.RecyclerViewAdapter;
import com.butoosa.myapp.model.Anime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Members extends Fragment {
    private final String json_url = "https://butoosa.online/androidApp/members.php?page=1";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List <Anime> animeList;
    private RecyclerView recyclerView;

    public Members() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_members, container, false);
        animeList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler);


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
                        Anime anime = new Anime();
                        anime.setName(object.getString("name"));
                        anime.setEmail(object.getString("email"));
                        anime.setContact(object.getString("contact"));
                        anime.setCourse(object.getString("course"));
                        anime.setImg_url(object.getString("photo"));
                        anime.setStudentID(object.getString("studentID"));
                        anime.setFaculty(object.getString("faculty"));

                        animeList.add(anime);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setUpRecyclerView(animeList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(this.getActivity());
        requestQueue.add(request);
    }

    private void setUpRecyclerView(List<Anime> animeLst) {
        RecyclerViewAdapter viewAdapter = new RecyclerViewAdapter(this.getActivity(),animeLst);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(viewAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Association Members");
    }

}
