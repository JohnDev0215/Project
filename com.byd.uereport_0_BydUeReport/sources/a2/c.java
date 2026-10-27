package a2;

import android.util.Log;
import java.util.HashSet;
import java.util.Set;
import o1.j;

/* JADX INFO: compiled from: LogcatLogger.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Set<String> f56a = new HashSet();

    @Override // o1.j
    public void a(String str, Throwable th) {
        Set<String> set = f56a;
        if (set.contains(str)) {
            return;
        }
        Log.w("LOTTIE", str, th);
        set.add(str);
    }

    @Override // o1.j
    public void b(String str) {
        a(str, null);
    }

    @Override // o1.j
    public void c(String str, Throwable th) {
        if (o1.d.f6733a) {
            Log.d("LOTTIE", str, th);
        }
    }

    @Override // o1.j
    public void d(String str) {
        e(str, null);
    }

    public void e(String str, Throwable th) {
        if (o1.d.f6733a) {
            Log.d("LOTTIE", str, th);
        }
    }
}
