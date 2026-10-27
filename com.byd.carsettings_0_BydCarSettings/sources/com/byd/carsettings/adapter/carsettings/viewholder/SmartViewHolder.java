package com.byd.carsettings.adapter.carsettings.viewholder;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;

/* JADX INFO: loaded from: classes2.dex */
public class SmartViewHolder extends RecyclerView.ViewHolder {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f2670a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BydSwitch f2671b;

    public SmartViewHolder(@NonNull View view) {
        super(view);
        this.f2670a = (TextView) view.findViewById(R.id.item_tv);
        this.f2671b = (BydSwitch) view.findViewById(R.id.item_switch);
    }
}
