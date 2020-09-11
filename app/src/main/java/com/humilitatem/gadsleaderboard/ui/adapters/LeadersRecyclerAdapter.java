package com.humilitatem.gadsleaderboard.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.humilitatem.gadsleaderboard.R;
import com.humilitatem.gadsleaderboard.model.Learner;

import java.util.List;

public class LeadersRecyclerAdapter extends RecyclerView.Adapter<LeadersRecyclerAdapter.ViewHolder> {

    private final List<Learner> mLearners;
    private Context mContext;

    public LeadersRecyclerAdapter(List<Learner> learners, Context context) {
        mLearners = learners;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_learning_leader, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mLearnerName.setText(mLearners.get(position).getName());
        holder.mLearnerDetails.setText(getLearnersDetails(position));
        Glide.with(mContext)
                .load(mLearners.get(position).getBadgeUrl())
                .into(holder.mLearnerBadge);
//        holder.mLearnerBadge.setText(mLearners.get(position).getBadge());
    }

    private CharSequence getLearnersDetails(int position) {
        CharSequence learnerDetails;
        if(mLearners.get(position).getScore() == null){
           learnerDetails = mLearners.get(position).getHours()+ " learning hours, "
                   + mLearners.get(position).getCountry();
        }else {
            learnerDetails = mLearners.get(position).getScore() + " skill IQ Score, "
                    + mLearners.get(position).getCountry();
        }
        return learnerDetails;
    }

    @Override
    public int getItemCount() {
        return mLearners.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mLearnerName;
        public final TextView mLearnerDetails;
        public final ImageView mLearnerBadge;
        public Learner mLearner;

        public ViewHolder(View itemView) {
            super(itemView);
            mLearnerName = itemView.findViewById(R.id.learnerName);
            mLearnerDetails = itemView.findViewById(R.id.learnerDetails);
            mLearnerBadge = itemView.findViewById(R.id.learnerBadge);
        }
    }
}
