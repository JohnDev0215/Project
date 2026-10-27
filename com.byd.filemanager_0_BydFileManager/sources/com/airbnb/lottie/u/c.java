package com.airbnb.lottie.u;

import android.util.Log;
import com.airbnb.lottie.i;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: LogcatLogger.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Set<String> f2805a = new HashSet();

    @Override // com.airbnb.lottie.i
    public void a(String str) {
        e(str, null);
    }

    @Override // com.airbnb.lottie.i
    public void b(String str, Throwable th) {
        Set<String> set = f2805a;
        if (set.contains(str)) {
            return;
        }
        Log.w("LOTTIE", str, th);
        set.add(str);
    }

    @Override // com.airbnb.lottie.i
    public void c(String str) {
        b(str, null);
    }

    @Override // com.airbnb.lottie.i
    public void d(String str, Throwable th) {
        if (com.airbnb.lottie.c.f2434a) {
            Log.d("LOTTIE", str, th);
        }
    }

    public void e(String str, Throwable th) {
        if (com.airbnb.lottie.c.f2434a) {
            Log.d("LOTTIE", str, th);
        }
    }
}
