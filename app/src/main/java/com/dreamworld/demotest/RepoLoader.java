package com.dreamworld.demotest;

import android.content.Context;
import android.support.v4.content.Loader;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.List;

/**
 * Created by faizan on 22/07/2017.
 */

public class RepoLoader {//extends Loader<List<String>> {

 /*   private List<String> cachedData;

    public static final String TAG = "repoloader";
    private RequestQueue queue;

    public RepoLoader(Context context) {
        super(context);
        queue = Volley.get(getContext()).getRequestQueue();
    }

    @Override
    protected void onStartLoading() {
        if (cachedData == null) {
            forceLoad();
        } else {
            super.deliverResult(cachedData);

        }
    }


    @Override
    protected void onStopLoading() {
        super.onStopLoading();
    }

    @Override
    protected void onForceLoad() {

        //StringRequest stringRequest = new StringRequest()
        RequestListString requestListString = new RequestListStrings(new Response.Listener<List<String>>()) {
            public void onResponse(List<String> response) {
           deliverResult(response);

            },new Response.ErrorListener()

            {
                public void onErrorResponse (VolleyError error)
                {


                }
            });
    requestListString.setTag(TAG);

    queue.add(requestListStrings);

        }
    }

    @Override
    protected void onReset() {
        queue.cancelAll(TAG);
    }

    @Override
    protected void onAbandon() {
        super.onAbandon();
    }

    public void deliverResult(List<String> data) {
        cachedData = data;
        super.deliverResult(data);
    }*/
}
