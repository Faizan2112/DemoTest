package com.dreamworld.demotest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class IntentActivtiy extends AppCompatActivity implements View.OnClickListener {
Button mWebview, mCall , mEmail , mGallery , mCamera ;
ImageView mShowImage ;
    private String mImageUrlString = "https://static.pexels.com/photos/17679/pexels-photo.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_activtiy);
        mWebview = (Button) findViewById(R.id.i_web_view);
        mCall = (Button) findViewById(R.id.i_call);
        mEmail = (Button) findViewById(R.id.i_send_email);
        mGallery = (Button) findViewById(R.id.i_pick_image);
        mCamera = (Button) findViewById(R.id.i_camera_image);
        mShowImage = (ImageView) findViewById(R.id.i_show_image);


        mWebview.setOnClickListener(this);
        mCall.setOnClickListener(this);
        mEmail.setOnClickListener(this);
        mGallery.setOnClickListener(this);
        mCamera.setOnClickListener(this);
        mShowImage.setOnClickListener(this);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar_cyclic);
     //   Uri uri = new Uri.fromFile("/storage/emulated/0/Download/followme/foldername77.png");

        Glide.with(this).
                load(mImageUrlString)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .crossFade(1000)
                .error(R.drawable.share)
                .into(mShowImage);

     /*   Glide.with(this)
               // .load(Uri.parse("/storage/emulated/0/Download/followme/foldername77.png"))
               // .load("http://www.planwallpaper.com/wallpaper-hd#static/images/b807c2282ab0a491bd5c5c1051c6d312_rP0kQjJ.jpg")
                .load(mImageUrlString)
                .fitCenter() // Image scale type
                .crossFade()
                .override(800,500) // Resize image
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.share)
                .into(mShowImage);
    }*/
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            case R.id.i_web_view :
                webView();
                break;

            case R.id.i_call :
                call();
                break;

            case R.id.i_send_email :
                email();
                break;

            case R.id.i_pick_image :
                gallery();
                break;

            case R.id.i_camera_image :
               camera();

                break;




        }
    }

    private void webView() {
        String url = "http://www.google.com";
        Intent web = new Intent(Intent.ACTION_VIEW);
        web.setData(Uri.parse(url));
        startActivity(web);

    }

    private void call() {
        String no = "9897576279";
        Intent call = new Intent(Intent.ACTION_CALL);
        call.setData(Uri.parse(no));

    }

    private void email() {
        String email = "faizandream21@gmail.com";/* Your email address here */
                String subject = "demo email"; /* Your subject here */
                String body = "body";/* Your body here */
                String chooserTitle = "helloooo";
                Intent iEmail = new Intent(Intent.ACTION_SENDTO ,Uri.parse("mail:"+email ));
                    iEmail.putExtra(Intent.EXTRA_SUBJECT,subject);
                    iEmail.putExtra(Intent.EXTRA_TEXT,body);
                     startActivity(Intent.createChooser(iEmail,"Choosseeeeerrr"));



    }
    private void gallery() {
    }
    private void camera() {
    }
}

