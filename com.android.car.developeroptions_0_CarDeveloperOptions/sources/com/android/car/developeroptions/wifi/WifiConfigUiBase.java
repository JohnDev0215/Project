package com.android.car.developeroptions.wifi;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;

/* JADX INFO: loaded from: classes.dex */
public interface WifiConfigUiBase {
    void dispatchSubmit();

    Context getContext();

    Button getForgetButton();

    LayoutInflater getLayoutInflater();

    Button getSubmitButton();

    void setCancelButton(CharSequence charSequence);

    void setForgetButton(CharSequence charSequence);

    void setSubmitButton(CharSequence charSequence);

    void setTitle(int i);

    void setTitle(CharSequence charSequence);
}
