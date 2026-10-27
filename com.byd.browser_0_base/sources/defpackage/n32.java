package defpackage;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;
import androidx.preference.R$id;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: PreferenceViewHolder.java */
/* JADX INFO: loaded from: classes.dex */
public final class n32 extends RecyclerView.a0 {
    public final SparseArray<View> A;
    public boolean B;
    public boolean C;
    public final Drawable y;
    public final ColorStateList z;

    public n32(View view) {
        super(view);
        SparseArray<View> sparseArray = new SparseArray<>(4);
        this.A = sparseArray;
        TextView textView = (TextView) view.findViewById(R.id.title);
        sparseArray.put(R.id.title, textView);
        sparseArray.put(R.id.summary, view.findViewById(R.id.summary));
        sparseArray.put(R.id.icon, view.findViewById(R.id.icon));
        int i = R$id.icon_frame;
        sparseArray.put(i, view.findViewById(i));
        sparseArray.put(R.id.icon_frame, view.findViewById(R.id.icon_frame));
        this.y = view.getBackground();
        if (textView != null) {
            this.z = textView.getTextColors();
        }
    }

    public final View u(int i) {
        SparseArray<View> sparseArray = this.A;
        View view = sparseArray.get(i);
        if (view != null) {
            return view;
        }
        View viewFindViewById = this.e.findViewById(i);
        if (viewFindViewById != null) {
            sparseArray.put(i, viewFindViewById);
        }
        return viewFindViewById;
    }
}
