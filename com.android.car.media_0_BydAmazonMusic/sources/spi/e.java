package spi;

import android.view.ViewGroup;
import spi.pc;

/* JADX INFO: compiled from: AdapterOEMV1.java */
/* JADX INFO: loaded from: classes2.dex */
public interface e<V extends pc> {
    void bindViewHolder(V v, int i);

    V createViewHolder(ViewGroup viewGroup, int i);

    int getItemCount();

    long getItemId(int i);

    int getItemViewType(int i);

    boolean hasStableIds();

    void onAttachedToRecyclerView(p9 p9Var);

    void onDetachedFromRecyclerView(p9 p9Var);

    boolean onFailedToRecycleView(V v);

    void onViewAttachedToWindow(V v);

    void onViewDetachedFromWindow(V v);

    void onViewRecycled(V v);
}
