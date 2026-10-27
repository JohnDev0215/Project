package androidx.sqlite.db.framework;

import android.content.Context;
import java.io.File;
import p.hk0;
import p.j52;
import p.nh2;
import p.py0;
import p.qh2;
import p.v03;
import p.v81;

/* JADX INFO: loaded from: classes.dex */
public final class d implements qh2 {
    public final Context c;
    public final String e;
    public final j52 k;
    public final boolean l;
    public final boolean m;
    public final v81 n;
    public boolean o;

    public d(Context context, String str, j52 j52Var, boolean z, boolean z2) {
        v03.j(context, "context");
        v03.j(j52Var, "callback");
        this.c = context;
        this.e = str;
        this.k = j52Var;
        this.l = z;
        this.m = z2;
        this.n = kotlin.a.c(new py0() { // from class: androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$lazyDelegate$1
            {
                super(0);
            }

            @Override // p.py0
            public final Object invoke() {
                c cVar;
                d dVar = this.this$0;
                if (dVar.e == null || !dVar.l) {
                    d dVar2 = this.this$0;
                    cVar = new c(dVar2.c, dVar2.e, new hk0(14, null), dVar2.k, dVar2.m);
                } else {
                    Context context2 = this.this$0.c;
                    v03.j(context2, "context");
                    File noBackupFilesDir = context2.getNoBackupFilesDir();
                    v03.i(noBackupFilesDir, "context.noBackupFilesDir");
                    File file = new File(noBackupFilesDir, this.this$0.e);
                    Context context3 = this.this$0.c;
                    String absolutePath = file.getAbsolutePath();
                    hk0 hk0Var = new hk0(14, null);
                    d dVar3 = this.this$0;
                    cVar = new c(context3, absolutePath, hk0Var, dVar3.k, dVar3.m);
                }
                cVar.setWriteAheadLoggingEnabled(this.this$0.o);
                return cVar;
            }
        });
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        v81 v81Var = this.n;
        if (v81Var.isInitialized()) {
            ((c) v81Var.getValue()).close();
        }
    }

    @Override // p.qh2
    public final void setWriteAheadLoggingEnabled(boolean z) {
        v81 v81Var = this.n;
        if (v81Var.isInitialized()) {
            c cVar = (c) v81Var.getValue();
            v03.j(cVar, "sQLiteOpenHelper");
            cVar.setWriteAheadLoggingEnabled(z);
        }
        this.o = z;
    }

    @Override // p.qh2
    public final nh2 u0() {
        return ((c) this.n.getValue()).b(true);
    }
}
