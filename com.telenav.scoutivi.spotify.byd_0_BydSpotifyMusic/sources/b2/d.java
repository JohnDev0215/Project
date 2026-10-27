package b2;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import n1.l;
import q1.v;

/* JADX INFO: compiled from: GifDrawableEncoder.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements l<c> {
    @Override // n1.l
    public n1.c a(n1.i iVar) {
        return n1.c.SOURCE;
    }

    @Override // n1.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public boolean b(v<c> vVar, File file, n1.i iVar) throws Throwable {
        try {
            k2.a.e(vVar.get().c(), file);
            return true;
        } catch (IOException e7) {
            if (Log.isLoggable("GifEncoder", 5)) {
                Log.w("GifEncoder", "Failed to encode GIF drawable data", e7);
            }
            return false;
        }
    }
}
