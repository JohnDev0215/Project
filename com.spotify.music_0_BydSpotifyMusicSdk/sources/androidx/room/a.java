package androidx.room;

import java.util.concurrent.atomic.AtomicBoolean;
import p.i52;
import p.py0;
import p.th2;
import p.v03;
import p.v81;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public final i52 a;
    public final AtomicBoolean b;
    public final v81 c;

    public a(i52 i52Var) {
        v03.j(i52Var, "database");
        this.a = i52Var;
        this.b = new AtomicBoolean(false);
        this.c = kotlin.a.c(new py0() { // from class: androidx.room.SharedSQLiteStatement$stmt$2
            {
                super(0);
            }

            @Override // p.py0
            public final Object invoke() {
                a aVar = this.this$0;
                return aVar.a.d(aVar.b());
            }
        });
    }

    public final th2 a() {
        i52 i52Var = this.a;
        i52Var.a();
        return this.b.compareAndSet(false, true) ? (th2) this.c.getValue() : i52Var.d(b());
    }

    public abstract String b();

    public final void c(th2 th2Var) {
        v03.j(th2Var, "statement");
        if (th2Var == ((th2) this.c.getValue())) {
            this.b.set(false);
        }
    }
}
