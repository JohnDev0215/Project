package e7;

import android.widget.ScrollView;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: OverScrollDecoratorHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public class h {
    public static b a(ScrollView scrollView) {
        return new i(new f7.c(scrollView));
    }

    public static b b(RecyclerView recyclerView, int i8) {
        if (i8 == 0) {
            return new i(new f7.b(recyclerView));
        }
        if (i8 == 1) {
            return new a(new f7.b(recyclerView));
        }
        throw new IllegalArgumentException("orientation");
    }
}
