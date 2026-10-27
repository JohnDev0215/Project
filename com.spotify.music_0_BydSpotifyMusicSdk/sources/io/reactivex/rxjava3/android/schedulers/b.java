package io.reactivex.rxjava3.android.schedulers;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {
    public static final f a = new f(new Handler(Looper.getMainLooper()));
}
