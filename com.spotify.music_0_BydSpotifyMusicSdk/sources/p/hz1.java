package p;

import android.database.sqlite.SQLiteProgram;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class hz1 implements rh2 {
    public final /* synthetic */ int c = 1;
    public final Object e;

    public hz1(SQLiteProgram sQLiteProgram) {
        v03.j(sQLiteProgram, "delegate");
        this.e = sQLiteProgram;
    }

    @Override // p.rh2
    public final void B0(byte[] bArr, int i) {
        switch (this.c) {
            case 0:
                v03.j(bArr, "value");
                c(i, bArr);
                break;
            default:
                v03.j(bArr, "value");
                ((SQLiteProgram) this.e).bindBlob(i, bArr);
                break;
        }
    }

    @Override // p.rh2
    public final void K(int i) {
        switch (this.c) {
            case 0:
                c(i, null);
                break;
            default:
                ((SQLiteProgram) this.e).bindNull(i);
                break;
        }
    }

    public final void b(int i, double d) {
        switch (this.c) {
            case 0:
                c(i, Double.valueOf(d));
                break;
            default:
                ((SQLiteProgram) this.e).bindDouble(i, d);
                break;
        }
    }

    public final void c(int i, Object obj) {
        int size;
        int i2 = i - 1;
        Object obj2 = this.e;
        if (i2 >= ((List) obj2).size() && (size = ((List) obj2).size()) <= i2) {
            while (true) {
                ((List) obj2).add(null);
                if (size == i2) {
                    break;
                } else {
                    size++;
                }
            }
        }
        ((List) obj2).set(i2, obj);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        switch (this.c) {
            case 0:
                break;
            default:
                ((SQLiteProgram) this.e).close();
                break;
        }
    }

    @Override // p.rh2
    public final void h0(int i, long j) {
        switch (this.c) {
            case 0:
                c(i, Long.valueOf(j));
                break;
            default:
                ((SQLiteProgram) this.e).bindLong(i, j);
                break;
        }
    }

    @Override // p.rh2
    public final void x(int i, String str) {
        switch (this.c) {
            case 0:
                v03.j(str, "value");
                c(i, str);
                break;
            default:
                v03.j(str, "value");
                ((SQLiteProgram) this.e).bindString(i, str);
                break;
        }
    }
}
