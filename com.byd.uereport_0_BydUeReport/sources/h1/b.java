package h1;

import android.database.Cursor;
import android.database.SQLException;
import android.os.CancellationSignal;
import android.util.Pair;
import java.io.Closeable;
import java.util.List;

/* JADX INFO: compiled from: SupportSQLiteDatabase.java */
/* JADX INFO: loaded from: classes.dex */
public interface b extends Closeable {
    Cursor J(e eVar, CancellationSignal cancellationSignal);

    boolean K();

    void W();

    String a();

    void c();

    void d();

    List<Pair<String, String>> h();

    boolean isOpen();

    Cursor j0(String str);

    void k(String str) throws SQLException;

    f u(String str);

    Cursor y(e eVar);
}
