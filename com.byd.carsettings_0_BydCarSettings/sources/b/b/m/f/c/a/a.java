package b.b.m.f.c.a;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: DragItemCallback.java */
/* JADX INFO: loaded from: classes2.dex */
public interface a {
    void drag(int i, int i2);

    void dragCompleted(RecyclerView.ViewHolder viewHolder);

    default void dragStart(RecyclerView.ViewHolder viewHolder) {
    }

    void removeItem(int i);
}
