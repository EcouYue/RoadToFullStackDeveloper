package com.example.umood.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.umood.FeedActivity;
import com.example.umood.MainActivity;
import com.example.umood.MoodHistory;
import com.example.umood.R;
import com.example.umood.User;
import com.example.umood.addMoodInfo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NotificationsFragment extends Fragment {
    User user;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference docref;
    private CollectionReference collectionReference = db.collection("users");
    private static final String TAG = "qian-profile";
    MainActivity activity;

    Intent intent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "0");

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        activity = (MainActivity) getActivity();
        user = activity.getUser();
        docref = collectionReference.document(user.getUsername());
        Log.d(TAG, "1");
        ImageView imageView = root.findViewById(R.id.avater);
        imageView.setImageResource(R.drawable.zeldaflat);



        Button history =  root.findViewById(R.id.button_history);


        Button chart =  root.findViewById(R.id.button_chart);

        Button feed =  root.findViewById(R.id.button_feed);
        Button setting =  root.findViewById(R.id.button_setting);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(activity, MoodHistory.class);
                intent.putExtra("User",user );
                startActivity(intent);
            }
        });

        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(activity, FeedActivity.class);
                intent.putExtra("User",user );
                startActivity(intent);
            }
        });

        return root;
    }


}
