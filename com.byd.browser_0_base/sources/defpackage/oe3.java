package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: TouchToFillViewHolder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class oe3 extends RecyclerView.a0 {
    public final j.a<PropertyModel, View, v62> y;

    public oe3(RecyclerView recyclerView, int i, j.a aVar) {
        super(LayoutInflater.from(recyclerView.getContext()).inflate(i, (ViewGroup) recyclerView, false));
        this.y = aVar;
    }
}
