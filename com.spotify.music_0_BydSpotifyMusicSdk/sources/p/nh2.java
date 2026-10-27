package p;

import android.database.Cursor;
import android.os.CancellationSignal;
import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public interface nh2 extends Closeable {
    Cursor F0(sh2 sh2Var);

    th2 H(String str);

    boolean S();

    boolean e0();

    boolean isOpen();

    void j0();

    void k0();

    void m();

    void n();

    Cursor o(sh2 sh2Var, CancellationSignal cancellationSignal);

    void w(String str);
}
