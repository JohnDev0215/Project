package com.android.car.developeroptions.utils;

import android.content.Context;
import androidx.preference.PreferenceViewHolder;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.setupdesign.DividerItemDecoration;

/* JADX INFO: loaded from: classes.dex */
public class SettingsDividerItemDecoration extends DividerItemDecoration {
    public SettingsDividerItemDecoration(Context context) {
        super(context);
    }

    @Override // com.google.android.setupdesign.DividerItemDecoration
    protected boolean isDividerAllowedAbove(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof PreferenceViewHolder) {
            return ((PreferenceViewHolder) viewHolder).isDividerAllowedAbove();
        }
        return super.isDividerAllowedAbove(viewHolder);
    }

    @Override // com.google.android.setupdesign.DividerItemDecoration
    protected boolean isDividerAllowedBelow(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof PreferenceViewHolder) {
            return ((PreferenceViewHolder) viewHolder).isDividerAllowedBelow();
        }
        return super.isDividerAllowedBelow(viewHolder);
    }
}
