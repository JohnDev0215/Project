package defpackage;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.CancellationSignal;
import android.os.OperationCanceledException;

/* JADX INFO: compiled from: ContentResolverCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class ty {
    public static Cursor a(ContentResolver contentResolver, am amVar) throws Exception {
        CancellationSignal cancellationSignal;
        CancellationSignal cancellationSignal2;
        if (amVar != null) {
            try {
                synchronized (amVar) {
                    if (amVar.c == null) {
                        CancellationSignal cancellationSignal3 = new CancellationSignal();
                        amVar.c = cancellationSignal3;
                        if (amVar.a) {
                            cancellationSignal3.cancel();
                        }
                    }
                    cancellationSignal = amVar.c;
                }
                cancellationSignal2 = cancellationSignal;
            } catch (Exception e) {
                if (e instanceof OperationCanceledException) {
                    throw new us1();
                }
                throw e;
            }
        } else {
            cancellationSignal2 = null;
        }
        return contentResolver.query(null, null, null, null, null, cancellationSignal2);
    }
}
