package c.a.d.q;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: OverScrollDecoratorHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class i {
    public static b a(RecyclerView recyclerView, int i2) {
        if (i2 == 0) {
            return new k(new j(recyclerView));
        }
        if (i2 == 1) {
            return new a(new j(recyclerView));
        }
        throw new IllegalArgumentException("orientation");
    }
}
