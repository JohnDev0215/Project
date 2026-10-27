package com.airbnb.lottie.u;

import android.util.Log;
import com.airbnb.lottie.i;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: LogcatLogger.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Set<String> f3185a = new HashSet();

    @Override // com.airbnb.lottie.i
    public void a(String str) {
        d(str, null);
    }

    @Override // com.airbnb.lottie.i
    public void b(String str, Throwable th) {
        Set<String> set = f3185a;
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

    public void d(String str, Throwable th) {
        if (com.airbnb.lottie.c.f2820a) {
            Log.d("LOTTIE", str, th);
        }
    }
}
