package com.inderjs.chllng.kisancontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContactsInfoPage extends AppCompatActivity {

    private TextView nameTv, phoneTv;
    private Button Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_info_page);

        Intent intent = getIntent();

        final String Name = intent.getStringExtra("Name");
        final String Phone = intent.getStringExtra("Phone");


        nameTv = (TextView)findViewById(R.id.namePage);
        phoneTv = (TextView)findViewById(R.id.phonePage);

        Btn = (Button)findViewById(R.id.btnPage);

        nameTv.setText(Name);
        phoneTv.setText(Phone);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent SendMessagePage = new Intent(ContactsInfoPage.this, ComposeMessage.class);

                SendMessagePage.putExtra("Phone",Phone);
                SendMessagePage.putExtra("Name",Name);

                startActivity(SendMessagePage);
            }
        });
    }
}
