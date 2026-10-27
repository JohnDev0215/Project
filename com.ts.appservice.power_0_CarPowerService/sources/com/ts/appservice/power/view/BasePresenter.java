package com.ts.appservice.power.view;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class BasePresenter {
    protected Context mContext;
    protected PowerViewControler mPop;

    public BasePresenter(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mPop = PowerViewControler.getInstance(applicationContext);
    }

    public void onViewAdded() {
    }

    public void onDismiss() {
    }
}
