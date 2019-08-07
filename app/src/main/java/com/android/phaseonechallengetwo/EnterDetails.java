package com.android.phaseonechallengetwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EnterDetails extends AppCompatActivity {

    EditText areaTv, destiantionTv, amountTv;
    String area , destination;
    int amount ;
    Button selectImage;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        areaTv = (EditText) findViewById(R.id.editText_area);
        destiantionTv = (EditText) findViewById(R.id.editText_destination);
        amountTv = (EditText) findViewById(R.id.editText_amount);
        selectImage = (Button) findViewById(R.id.button_select);

        imageView = (ImageView) findViewById(R.id.imageView);



        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestRead();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            writeToFirebase();
        }

        return super.onOptionsItemSelected(item);
    }

    private void writeToFirebase()
    {
        area = areaTv.getText().toString();
        amount = Integer.parseInt(amountTv.getText().toString());
        destination = destiantionTv.getText().toString();

        Deal deal = new Deal(area,amount,destination," ");
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Database").child("Deals");
        databaseReference.push().setValue(deal).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(task.isSuccessful())
                {
                    Log.d("Adding","Adding deal was succesful");
                    Intent intent = new Intent(EnterDetails.this,DisplayDeals.class);
                    startActivity(intent);
                }
                else
                {
                    Log.d("Adding","This was a fail");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        try {

            if (requestCode == 1) {

                if (resultCode == RESULT_OK)
                {
                    final Uri imageUri = data.getData();
                    imageView.setImageURI(imageUri);

                }}}
                catch (Exception e)
                {
                    Log.d("Error","There is an error ");
                }



    }

    public void startingUpTheCameraPicker()
    {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }
    public void requestRead()
    {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
//             In this code the user is requesting for permissions
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }
        else
        {
//                In this case the permissions have been offered already
            startingUpTheCameraPicker();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if (requestCode == 1)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
//                In this case the permission has been granted and the user can start the camera picker
                startingUpTheCameraPicker();
            } else {
                // Permission Denied
                Log.d("Permisiios","No permissions granted ");
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
