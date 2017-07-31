package com.dreamworld.demotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.dreamworld.demotest.Config.names;
import static com.dreamworld.demotest.Config.urls;
import static com.dreamworld.demotest.Config.viewtype;

public class MultiViewActivity extends AppCompatActivity {
    public static final String GET_URL = "http://faizandream21.000webhostapp.com/PhotoUploadWithText/getviewType.php";
    private Config mConfigFile;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_view);
     /*   ArrayList<Model> list= new ArrayList<>();
        list.add(new Model(Model.TEXT_TYPE,"Hello. This is the Text-only View Type. Nice to meet you",0));
        list.add(new Model(Model.IMAGE_TYPE,"Hi. I display a cool image too besides the omnipresent TextView.",R.drawable.wtc));
        list.add(new Model(Model.AUDIO_TYPE,"Hey. Pressing the FAB button will playback an audio file on loop.",R.raw.sound));
        list.add(new Model(Model.IMAGE_TYPE,"Hi again. Another cool image here. Which one is better?",R.drawable.snow));
*/
  //      MultiViewAdapter adapter = new MultiViewAdapter(list,this);
       
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
       
     useVolley();
    }

    private void useVolley() {
        StringRequest sr = new StringRequest(Request.Method.GET, GET_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                parseJson(response);
                Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "" + error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
        RequestQueue lRequestQueue = Volley.newRequestQueue(this);
        lRequestQueue.add(sr);




    }

    private void parseJson(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray array = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            mConfigFile = new Config(array.length());

            for (int i = 0; i <= array.length(); i++) {
                JSONObject fetchedData = array.getJSONObject(i);
                Config.viewtype[i] = getViewType(fetchedData);
                names[i] = getName(fetchedData);
                Config.urls[i] = getURL(fetchedData);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<Model> models = new ArrayList<>();
        for(int i= 0; i<urls.length;i++ )
        {
            Model m = new Model();
            m.setName(names[i]);
            m.setUrl(urls[i]);
            m.setType(viewtype[i]);
           models.add(m);

        }
        MultiViewAdapter adapter = new MultiViewAdapter(models,this);
        mRecyclerView.setAdapter(adapter);


    }

    private int getViewType(JSONObject fetchedData) {
        int viewtype = 0;
        try {
            viewtype = fetchedData.getInt("viewtype");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return viewtype;
    }

    private String getName(JSONObject fetchedData) {
        String name = null;
        try {
            name = fetchedData.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return name;
    }
    private String getURL(JSONObject fetchedData) {
        String url = null;
        try {
            url = fetchedData.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return url;
    }

}
