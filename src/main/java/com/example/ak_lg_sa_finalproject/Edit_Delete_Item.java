package com.example.ak_lg_sa_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class Edit_Delete_Item extends AppCompatActivity {

    private EditText first_name, last_name, phone, email, note;
    private Button btnDelete, btnUpdate;
    int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_delete_item);

        // getting reference to EditText widgets
        first_name = (EditText) findViewById(R.id.etFName);
        last_name = (EditText) findViewById(R.id.etLName);
        phone = (EditText) findViewById(R.id.etPhone);
        email = (EditText) findViewById(R.id.etEmail);
        note = (EditText) findViewById(R.id.etNote);

        // getting reference to buttons
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        //Setting EditTexts values from the intent's extras
        Intent intent = getIntent();
        ID = intent.getExtras().getInt("ID");
        first_name.setText(intent.getExtras().getString("FirstName"));
        last_name.setText(intent.getExtras().getString("LastName"));
        phone.setText(intent.getExtras().getString("Phone"));
        email.setText(intent.getExtras().getString("Email"));
        note.setText(intent.getExtras().getString("Note"));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating an instance of DbOperations and calling updateRecord method
                DbOperations dataBaseHelper = new DbOperations(Edit_Delete_Item.this);
                dataBaseHelper.updateRecord(ID, first_name.getText().toString(),
                        last_name.getText().toString(), phone.getText().toString(),
                        email.getText().toString(), note.getText().toString());

                Toast.makeText(Edit_Delete_Item.this, "Contact Updated..",
                                                                    Toast.LENGTH_SHORT).show();

                // launching Contacts activity.
                Intent i = new Intent(Edit_Delete_Item.this, ContactsActivity.class);
                startActivity(i);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating an instance of DbOperations and calling deleteRecord method
                DbOperations dataBaseHelper = new DbOperations(Edit_Delete_Item.this);
                dataBaseHelper.deleteRecord(ID);

                Toast.makeText(Edit_Delete_Item.this, "Contact Deleted..",
                        Toast.LENGTH_SHORT).show();

                // launching Contacts activity.
                Intent i = new Intent(Edit_Delete_Item.this, ContactsActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.item2:
                startActivity(new Intent(this, ContactsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}