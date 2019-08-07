package com.android.phaseonechallengetwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayDeals extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Deal> dealListArray = new ArrayList<>();
    private DealAdapter dealAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_deals);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_deals);
        dealAdapter = new DealAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference().child("Database").child("Deals");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                Iterable<DataSnapshot> dealList = dataSnapshot.getChildren();
                for(DataSnapshot oneDeal : dealList)
                {
                    Deal deal = new Deal();
                    deal = oneDeal.getValue(Deal.class);
                    dealListArray.add(deal);
                }

                dealAdapter.setDealList(dealListArray);
                recyclerView.setAdapter(dealAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
