package com.senac.banco.services;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class RealtimeDatabase extends Firebase {    
	public RealtimeDatabase() throws IOException {
	    super();
	}
	
	public DatabaseReference getDatabaseReference(String path) {
		return getDatabaseInstance().getReference(path);
	}
	
	public DatabaseReference getDatabaseReference() {
		return getDatabaseInstance().getReference();
	}
	
	public void setValue(DatabaseReference ref, Object value) {
		ref.setValueAsync(value);
	}
	
	public void setValue(String path, Object value) {
	    setValue(getDatabaseReference(path), value);
	}
	
	public Object getValue(DatabaseReference ref) throws InterruptedException {
        final Object[] result = new Object[1];
        
        // create a latch to wait for the value to be retrieved
        // a latch is used to ensure that the value is retrieved before proceeding
        // it syncs the main thread with the value retrieval thread
        final CountDownLatch latch = new CountDownLatch(1);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                result[0] = dataSnapshot.getValue();
                latch.countDown(); // release the latch
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error: " + databaseError.getMessage());
                latch.countDown(); // release the latch
            }
        });

        latch.await();

        return result[0];
    }
	
	public Object getValue(String path) throws InterruptedException {
		return getValue(getDatabaseReference(path));
	}

}
