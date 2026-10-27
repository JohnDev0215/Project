package p;

import android.database.sqlite.SQLiteDatabase;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public abstract class j52 {
    public final int a;

    public static void b(String str) {
        if (ng2.b0(str, ":memory:", true)) {
            return;
        }
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = v03.n(str.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                } else {
                    length--;
                }
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        if (str.subSequence(i, length + 1).toString().length() == 0) {
            return;
        }
        try {
            SQLiteDatabase.deleteDatabase(new File(str));
        } catch (Exception unused) {
        }
    }

    public abstract void a(androidx.sqlite.db.framework.b bVar);

    public abstract void c(androidx.sqlite.db.framework.b bVar);

    public abstract void d(androidx.sqlite.db.framework.b bVar);

    public abstract void e(androidx.sqlite.db.framework.b bVar, int i, int i2);

    public abstract void f(androidx.sqlite.db.framework.b bVar);

    public abstract void g(androidx.sqlite.db.framework.b bVar, int i, int i2);

    public abstract k52 h(androidx.sqlite.db.framework.b bVar);
}
