package b.u.a;

import android.database.Cursor;
import android.os.CancellationSignal;
import java.io.Closeable;

/* JADX INFO: compiled from: SupportSQLiteDatabase.java */
/* JADX INFO: loaded from: classes.dex */
public interface b extends Closeable {
    Cursor I(d dVar, CancellationSignal cancellationSignal);

    boolean K();

    void e();

    void f();

    boolean isOpen();

    void r();

    Cursor v(d dVar);
}
