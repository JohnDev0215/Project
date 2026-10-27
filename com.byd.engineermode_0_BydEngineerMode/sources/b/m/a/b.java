package b.m.a;

import android.database.Cursor;
import android.database.SQLException;
import android.os.CancellationSignal;
import android.util.Pair;
import java.io.Closeable;
import java.util.List;

/* JADX INFO: compiled from: SupportSQLiteDatabase.java */
/* JADX INFO: loaded from: classes.dex */
public interface b extends Closeable {
    void a();

    void b();

    boolean c();

    List<Pair<String, String>> d();

    void e(String str) throws SQLException;

    void h();

    f j(String str);

    Cursor k(e eVar);

    Cursor q(String str);

    String r();

    Cursor s(e eVar, CancellationSignal cancellationSignal);

    boolean t();
}
