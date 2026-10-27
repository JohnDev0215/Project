package defpackage;

import android.util.Log;
import defpackage.jk2;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: ShareImageFileUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class mk2 implements Runnable {
    public FileOutputStream e;
    public File f;
    public final /* synthetic */ String g;
    public final /* synthetic */ boolean h = true;
    public final /* synthetic */ String i;
    public final /* synthetic */ jk2.d j;
    public final /* synthetic */ Callback k;
    public final /* synthetic */ Callback l;

    public mk2(String str, String str2, jk2.d dVar, qt qtVar, n62 n62Var) {
        this.g = str;
        this.i = str2;
        this.j = dVar;
        this.k = qtVar;
        this.l = n62Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Callback callback = this.l;
        try {
            File fileA = jk2.a(this.g, this.i, this.h);
            this.f = fileA;
            if (fileA == null || !fileA.exists()) {
                Log.w("cr_share", "Share failed -- Unable to create or write to destination file.");
                a8.s(this.e);
                callback.onResult(null);
            } else {
                FileOutputStream fileOutputStream = new FileOutputStream(this.f);
                this.e = fileOutputStream;
                this.j.s(fileOutputStream, new sl0(this, 5, this.k, callback));
            }
        } catch (IOException unused) {
            a8.s(this.e);
            callback.onResult(null);
        }
    }
}
