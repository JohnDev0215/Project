package com.neusoft.na.onecore;

import android.util.Log;
import com.neusoft.na.ehp.Ehp;

/* JADX INFO: loaded from: classes2.dex */
public class EhpProvider {
    public static Ehp getInstance() {
        Log.d("app_CarService", " new Ehp()");
        return new Ehp();
    }
}
