package q3;

import android.content.res.AssetManager;
import android.os.Build;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.concurrent.Executor;

/* JADX INFO: renamed from: q3.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2174a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Executor f21224a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC2177d f21225b;
    public final byte[] c;
    public final File d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f21226e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f21227f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public C2175b[] f21228g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public byte[] f21229h;

    public C2174a(AssetManager assetManager, Executor executor, InterfaceC2177d interfaceC2177d, String str, File file) {
        this.f21224a = executor;
        this.f21225b = interfaceC2177d;
        this.f21226e = str;
        this.d = file;
        int i7 = Build.VERSION.SDK_INT;
        this.c = i7 >= 31 ? AbstractC2178e.d : (i7 == 29 || i7 == 30) ? AbstractC2178e.f21239e : null;
    }

    public final FileInputStream a(AssetManager assetManager, String str) {
        try {
            return assetManager.openFd(str).createInputStream();
        } catch (FileNotFoundException e10) {
            String message = e10.getMessage();
            if (message != null && message.contains("compressed")) {
                this.f21225b.s();
            }
            return null;
        }
    }

    public final void b(int i7, Serializable serializable) {
        this.f21224a.execute(new h.k(this, i7, serializable, 6));
    }
}
