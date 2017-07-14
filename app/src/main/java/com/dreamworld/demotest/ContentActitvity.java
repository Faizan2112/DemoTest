package com.dreamworld.demotest;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
// provide permission to red contcts in manifest file
public class ContentActitvity extends AppCompatActivity {

    private static final String TAG = "ContentProviderDemo";
    private TextView textView;
    private String[] mColumnProjection = new String[]
            {
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
                    ContactsContract.Contacts.CONTACT_STATUS,
                    ContactsContract.Contacts.HAS_PHONE_NUMBER
            };
    private String mSelection = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;
    private String[] mSelectionArguments = new String[]{"Anil"};
    private String mOrderBy = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_actitvity);
      textView = (TextView)findViewById(R.id.cDisplay_contact);
        ContentResolver contentResolver = getContentResolver();

        // content uri = query telling which table we are quering
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                mColumnProjection,
                null,
                null,
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
    }

