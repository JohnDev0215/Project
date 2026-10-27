package spi;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ScrollBar.java */
/* JADX INFO: loaded from: classes2.dex */
public interface u9 {
    void adapterChanged(RecyclerView.Adapter adapter);

    void initialize(Context context, RecyclerView recyclerView, View view);

    boolean isAtEnd();

    void requestLayout();

    void setHighlightThumb(boolean z);

    void setPadding(int i, int i2);
}
