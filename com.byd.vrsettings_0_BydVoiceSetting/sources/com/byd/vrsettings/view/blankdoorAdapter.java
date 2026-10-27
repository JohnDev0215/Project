package com.byd.vrsettings.view;

import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.vrsettings.R;
import com.byd.vrsettings.utils.PrivacyPolicyModel;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class blankdoorAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<PrivacyPolicyModel> modelList;

    public blankdoorAdapter(List<PrivacyPolicyModel> list) {
        this.modelList = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView isAgreeText;
        TextView timeText;

        public ViewHolder(View view) {
            super(view);
            this.timeText = (TextView) view.findViewById(R.id.timeText);
            this.isAgreeText = (TextView) view.findViewById(R.id.isAgreeText);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_blankdoor, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        PrivacyPolicyModel privacyPolicyModel = this.modelList.get(i);
        viewHolder.timeText.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) new java.sql.Date(Long.valueOf(privacyPolicyModel.getTime()).longValue())));
        viewHolder.isAgreeText.setText(privacyPolicyModel.getIsAgree().equals("true") ? "agree" : "disagree");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.modelList.size();
    }
}
