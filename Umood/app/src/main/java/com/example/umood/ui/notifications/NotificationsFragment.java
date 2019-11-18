package com.example.umood.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.umood.ChartActivity;
import com.example.umood.FeedActivity;
import com.example.umood.LoginActivity;
import com.example.umood.MainActivity;
import com.example.umood.MoodHistory;
import com.example.umood.MoodList;
import com.example.umood.R;
import com.example.umood.SettingActivity;
import com.example.umood.User;
import com.example.umood.R.drawable;


public class NotificationsFragment extends Fragment {
    private User user;
    private static final String TAG = "qian-profile";
    private MainActivity activity;

    private Intent intent;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        activity = (MainActivity) getActivity();
        final MoodList moodEventList = activity.getMoodEventList();
        user = activity.getUser();

        //ImageView imageView = root.findViewById(R.id.profile_pic);
        ImageView imageView = root.findViewById(R.id.avatar);



        // Text view Setting
        TextView usernameView = root.findViewById(R.id.username);
        TextView followingNumber = root.findViewById(R.id.following_number);
        TextView followerNumber = root.findViewById(R.id.follower_number);
        TextView postNumber =  root.findViewById(R.id.postNumber);

        usernameView.setText(user.getUsername());
        followerNumber.setText(String.valueOf(activity.getFollowerUserList().size()) );
        followingNumber.setText(String.valueOf(activity.getFollowingUserList().size()));
        postNumber.setText(String.valueOf(user.getMoodHistory().size()));

        Button history =  root.findViewById(R.id.button_history);
        Button chart =  root.findViewById(R.id.button_chart);
        Button feed =  root.findViewById(R.id.button_feed);
        Button setting =  root.findViewById(R.id.button_setting);
        Button logout = root.findViewById(R.id.logout);

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
                intent.putExtra("User",user);
                intent.putExtra("EventList",moodEventList);
                startActivity(intent);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(activity, SettingActivity.class);
                startActivity(intent);

            }
        });

        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(activity, ChartActivity.class);
                startActivity(intent);


            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
            }
        });


        return root;
    }
    @Override
    public void onResume() {
        super.onResume();
        activity.update();
    }

    @Override
    public void onPause() {
        super.onPause();
        activity.update();
    }


}
