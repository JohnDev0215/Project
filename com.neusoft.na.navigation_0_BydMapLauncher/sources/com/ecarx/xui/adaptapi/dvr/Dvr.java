package com.ecarx.xui.adaptapi.dvr;

import android.content.Context;
import com.ecarx.xui.adaptapi.dvr.ota.IOta;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Dvr extends AdaptAPI {
    private Context context;

    public abstract IOperation getOperation();

    public abstract IOta getOta();

    public abstract ISettings getSettings();

    public Dvr(Context context) {
        this.context = context;
    }

    public static Dvr create(Context context) {
        return new DvrImp(context);
    }
}
