package com.byd.carsettings.adapter.carsettings.viewholder;

import android.view.View;
import android.widget.RadioButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.carsettings.R;

/* JADX INFO: loaded from: classes2.dex */
public class LightViewHolder extends RecyclerView.ViewHolder {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public RadioButton f2669a;

    public LightViewHolder(@NonNull View view) {
        super(view);
        this.f2669a = (RadioButton) view.findViewById(R.id.light_radio_button);
    }
}
