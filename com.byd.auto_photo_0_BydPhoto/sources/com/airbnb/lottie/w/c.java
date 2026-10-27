package com.airbnb.lottie.w;

import android.util.Log;
import com.airbnb.lottie.i;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class c implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Set<String> f966a = new HashSet();

    @Override // com.airbnb.lottie.i
    public void a(String str, Throwable th) {
        Set<String> set = f966a;
        if (set.contains(str)) {
            return;
        }
        Log.w("LOTTIE", str, th);
        set.add(str);
    }

    @Override // com.airbnb.lottie.i
    public void b(String str) {
        a(str, null);
    }

    @Override // com.airbnb.lottie.i
    public void c(String str) {
        d(str, null);
    }

    public void d(String str, Throwable th) {
        if (com.airbnb.lottie.c.f657a) {
            Log.d("LOTTIE", str, th);
        }
    }
}
