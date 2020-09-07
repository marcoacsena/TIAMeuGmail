package com.example.meugmail.activity;

import android.os.Bundle;

import com.example.meugmail.R;
import com.example.meugmail.adapter.MailAdapter;
import com.example.meugmail.model.EmailData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvEmails;
    MailAdapter mailAdapter;
    List<EmailData> mEmailData = new ArrayList<>();

    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Meu Gmail");
        setSupportActionBar(toolbar);

        initComponents();
        criarEmail();

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.VERTICAL, false);
        rvEmails.addItemDecoration(new DividerItemDecoration(MainActivity.this,
                DividerItemDecoration.VERTICAL));
        rvEmails.setLayoutManager(mLinearLayoutManager);
        mailAdapter = new MailAdapter(MainActivity.this, mEmailData);
        rvEmails.setAdapter(mailAdapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initComponents() {

        rvEmails = findViewById(R.id.rvEmails);
        fab = findViewById(R.id.fab);
    }


    private void criarEmail() {
        EmailData mEmail = new EmailData("Marco Sena", "Weekend adventure",
                "Let's go fishing with John and others. We will do some barbecue and have soo much fun",
                "10:42am");
        mEmailData.add(mEmail);
        mEmail = new EmailData("Facebook", "James, you have 1 new notification",
                "A lot has happened on Facebook since",
                "16:04pm");
        mEmailData.add(mEmail);
        mEmail = new EmailData("Google+", "Top suggested Google+ pages for you",
                "Top suggested Google+ pages for you",
                "18:44pm");
        mEmailData.add(mEmail);
        mEmail = new EmailData("Twitter", "Follow T-Mobile, Samsung Mobile U",
                "James, some people you may know",
                "20:04pm");
        mEmailData.add(mEmail);
        mEmail = new EmailData("Pinterest Weekly", "Pins you’ll love!",
                "Have you seen these Pins yet? Pinterest",
                "09:04am");
        mEmailData.add(mEmail);
        mEmail = new EmailData("Luciane Benetti", "Don't forget!",
                "Don't forget kiss me every night before you sleep!",
                "01:04am");
        mEmailData.add(mEmail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}