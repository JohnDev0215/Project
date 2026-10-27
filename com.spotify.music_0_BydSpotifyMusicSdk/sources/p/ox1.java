package p;

import androidx.work.impl.WorkDatabase;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ox1 implements Callable {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object k;
    public final /* synthetic */ Object l;

    public /* synthetic */ ox1(Object obj, Object obj2, Object obj3, int i) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
        this.l = obj3;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        int i = this.c;
        Object obj = this.l;
        Object obj2 = this.k;
        Object obj3 = this.e;
        switch (i) {
            case 0:
                String str = (String) obj;
                WorkDatabase workDatabase = ((qx1) obj3).e;
                ((ArrayList) obj2).addAll(((f02) workDatabase.w()).b(str));
                return workDatabase.v().h(str);
            default:
                x80 x80Var = (x80) obj3;
                x80Var.getClass();
                return x80Var.c.submit(new ua((Callable) obj2, 18, (oh1) obj));
        }
    }
}
