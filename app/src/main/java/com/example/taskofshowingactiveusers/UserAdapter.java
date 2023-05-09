package com.example.taskofshowingactiveusers;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private final List<User> userList;

    public UserAdapter(ActiveUsersActivity activeUsersActivity, List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        /*holder.textViewUserId.setText("User ID: " + user.getId());*/
        holder.textViewUserId.setText(user.getUsername());
        holder.textViewIsActive.setText(user.isStatus() ? "Active" : "Inactive");

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUserId;
        TextView textViewIsActive;



        public UserViewHolder(View itemView) {
            super(itemView);
            textViewUserId = itemView.findViewById(R.id.textViewUsername);
            textViewIsActive = itemView.findViewById(R.id.textViewActiveStatus);

        }
    }
}
