package com.dreamworld.demotest;

import android.Manifest;
import android.content.CursorLoader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.design.widget.Snackbar;

// provide permission to red contcts in manifest file
public class ContentActitvity extends AppCompatActivity implements View.OnClickListener, android.app.LoaderManager.LoaderCallbacks<Cursor> {
    private static final int READ_CONT_CODE =12 ;
    Button mLoadData;
    private static final String TAG = "ContentProviderDemo";
    private TextView textView;
    private String[] mColumnProjection = new String[]
            {
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
                    ContactsContract.Contacts.CONTACT_STATUS,
                    ContactsContract.Contacts.HAS_PHONE_NUMBER
            };
    private String mSelection = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + "= 'Bhai' "; // if we replace it by ? then it  take from slection Agrumnet
    private String[] mSelectionArguments = new String[]{"Anil"};
    private String mOrderBy = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;
    private boolean firstTimeLoadData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_actitvity);
        textView = (TextView) findViewById(R.id.c_Display_data);
        mLoadData = (Button) findViewById(R.id.c_load_data);
        mLoadData.setOnClickListener(this);

        // this action is performend in main thread threfore if task is long runnug then we need loader for background

        //checkReadPerm();
        /*
        // with out using asynk task
        ContentResolver contentResolver = getContentResolver();

        // content uri = query telling which table we are quering
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                mColumnProjection,
                null,// or use like where clasue mSelection
                null,// or use like where eqal condition  mSelectionArguments
                null);

                if(cursor!=null && cursor.getCount()>0) {
                    StringBuilder stringBuilderQueryResult = new StringBuilder("");
                    while (cursor.moveToNext()) {

                        stringBuilderQueryResult.append(cursor.getString(0) + "," + cursor.getString(1) + "," + cursor.getString(2));

                    }
                    textView.setText(stringBuilderQueryResult.toString());
                }else
                    {

                        textView.setText("no contact uri");
                    }




                }
*/


    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.c_load_data:

                loadContacts();
                break;

        }
    }

    private void loadContacts() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            if (firstTimeLoadData == false) {
                // third parameter is class whic is implenting loadercall back
                // we can have many loader in
                getLoaderManager().initLoader(1, null, this);
                firstTimeLoadData = true;
            } else {
                getLoaderManager().restartLoader(1, null, this);
            }

        }else {

            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CONTACTS))
            {
                 Snackbar.make(findViewById(android.R.id.content),"Need permission for loading data",Snackbar.LENGTH_INDEFINITE).setAction("enable", new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         ActivityCompat.requestPermissions(ContentActitvity.this,new String[]{Manifest.permission.READ_CONTACTS},READ_CONT_CODE);

                     }
                 }).show();

                 }else
                {
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},READ_CONT_CODE);

                }

        }



    }

    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id == 1) {
// it will initialize the worker thread in background
            return new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI, mColumnProjection, mSelection, null, null);

        }
        return null;
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor cursor) {
// here we have to display data on main thread
        if (cursor != null && cursor.getCount() > 0) {
            StringBuilder stringBuilderQueryResult = new StringBuilder("");
            while (cursor.moveToNext()) {

                stringBuilderQueryResult.append(cursor.getString(0) + "," + cursor.getString(1) + "," + cursor.getString(2));

            }
            textView.setText(stringBuilderQueryResult.toString());
        } else {

            textView.setText("no contact uri");
        }

    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {

    }



  /*  private void checkReadPerm() {

    if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS==PackageManager.PERMISSION_GRANTED)
        {


        }else{
        //requestRuntimePermissions(this,);
        }*/
}

