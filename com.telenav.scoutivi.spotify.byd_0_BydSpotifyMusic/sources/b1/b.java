package b1;

import android.database.Cursor;
import android.util.Pair;
import java.io.Closeable;
import java.util.List;

/* JADX INFO: compiled from: SupportSQLiteDatabase.java */
/* JADX INFO: loaded from: classes.dex */
public interface b extends Closeable {
    Cursor O(String str);

    String R();

    boolean W();

    void f();

    void g();

    Cursor h(e eVar);

    boolean isOpen();

    List<Pair<String, String>> k();

    void o(String str);

    void u();

    f w(String str);
}
