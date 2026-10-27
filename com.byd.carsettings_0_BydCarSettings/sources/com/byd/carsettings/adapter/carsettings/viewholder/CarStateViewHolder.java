package com.byd.carsettings.adapter.carsettings.viewholder;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.carsettings.R;

/* JADX INFO: loaded from: classes2.dex */
public class CarStateViewHolder extends RecyclerView.ViewHolder {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f2667a;

    public CarStateViewHolder(@NonNull View view) {
        super(view);
        this.f2667a = (TextView) view.findViewById(R.id.wade_protect_car_state_tv);
    }
}
