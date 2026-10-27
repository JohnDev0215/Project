package com.byd.vrassistant.bean;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public interface VrText {
    int getTextColor(Context context, boolean z);

    default String getText(Context context, VrContent vrContent) {
        return vrContent.getInput();
    }

    default String getResponse(Context context, VrContent vrContent) {
        return vrContent.getResponse();
    }
}
