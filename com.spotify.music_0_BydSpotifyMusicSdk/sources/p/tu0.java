package p;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import p.gu0;
import p.l40;
import p.ri;
import p.tu0;

/* JADX INFO: loaded from: classes.dex */
public final class tu0 implements j30 {
    public final l40 a;
    public final boolean b;
    public String c;

    public tu0(l40 l40Var, boolean z) {
        this.a = l40Var;
        this.b = z;
    }

    @Override // p.j30
    public final im1 a(String str) {
        return new oh1(21, this.a.b(str));
    }

    @Override // p.j30
    public final boolean b() {
        String str = this.c;
        return str != null && d(str);
    }

    /* JADX WARN: Type inference failed for: r7v0, types: [com.google.firebase.crashlytics.ndk.a] */
    @Override // p.j30
    public final synchronized void c(final String str, final String str2, final long j, final ri riVar) {
        this.c = str;
        ?? r7 = new Object() { // from class: com.google.firebase.crashlytics.ndk.a
            public final void a() throws Throwable {
                String str3 = str2;
                long j2 = j;
                ri riVar2 = riVar;
                tu0 tu0Var = this.a;
                tu0Var.getClass();
                Log.isLoggable("FirebaseCrashlytics", 3);
                l40 l40Var = tu0Var.a;
                gu0 gu0Var = l40Var.c;
                String str4 = str;
                try {
                    String canonicalPath = gu0Var.j(str4).getCanonicalPath();
                    if (((JniNativeApi) l40Var.b).b(l40Var.a.getAssets(), canonicalPath)) {
                        l40Var.d(str4, str3, j2);
                        l40Var.e(str4, riVar2.a);
                        l40Var.h(str4, riVar2.b);
                        l40Var.f(str4, riVar2.c);
                    }
                } catch (IOException e) {
                    Log.e("FirebaseCrashlytics", "Error initializing Crashlytics NDK", e);
                }
            }
        };
        if (this.b) {
            r7.a();
        }
    }

    @Override // p.j30
    public final boolean d(String str) {
        File file;
        ww1 ww1Var = this.a.b(str).a;
        return ww1Var != null && (((file = (File) ww1Var.e) != null && file.exists()) || ((m30) ww1Var.k) != null);
    }
}
