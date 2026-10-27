package b.a.d.e.a.a;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: DragItemCallback.java */
/* JADX INFO: loaded from: classes2.dex */
public interface a {
    void a(int i, int i2);

    void b(RecyclerView.ViewHolder viewHolder);

    default void c(RecyclerView.ViewHolder viewHolder) {
    }

    void removeItem(int i);
}
