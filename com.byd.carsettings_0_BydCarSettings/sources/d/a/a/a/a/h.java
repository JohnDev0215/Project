package d.a.a.a.a;

import android.widget.ScrollView;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: OverScrollDecoratorHelper.java */
/* JADX INFO: loaded from: classes3.dex */
public class h {
    public static b a(ScrollView scrollView) {
        return new i(new d.a.a.a.a.j.c(scrollView));
    }

    public static b b(RecyclerView recyclerView, int i) {
        if (i == 0) {
            return new i(new d.a.a.a.a.j.b(recyclerView));
        }
        if (i == 1) {
            return new a(new d.a.a.a.a.j.b(recyclerView));
        }
        throw new IllegalArgumentException("orientation");
    }
}
