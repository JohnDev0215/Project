package androidx.preference;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class f extends RecyclerView.A {

    @Nullable
    private Drawable t;
    private ColorStateList u;
    private final SparseArray<View> v;
    private boolean w;
    private boolean x;

    f(View view) {
        super(view);
        this.v = new SparseArray<>(4);
        TextView textView = (TextView) view.findViewById(R.id.title);
        this.v.put(R.id.title, textView);
        this.v.put(R.id.summary, view.findViewById(R.id.summary));
        this.v.put(R.id.icon, view.findViewById(R.id.icon));
        SparseArray<View> sparseArray = this.v;
        int i = R$id.icon_frame;
        sparseArray.put(i, view.findViewById(i));
        this.v.put(R.id.icon_frame, view.findViewById(R.id.icon_frame));
        this.t = view.getBackground();
        if (textView != null) {
            this.u = textView.getTextColors();
        }
    }

    public View M(@IdRes int i) {
        View view = this.v.get(i);
        if (view != null) {
            return view;
        }
        View viewFindViewById = this.f1092a.findViewById(i);
        if (viewFindViewById != null) {
            this.v.put(i, viewFindViewById);
        }
        return viewFindViewById;
    }

    public boolean N() {
        return this.w;
    }

    public boolean O() {
        return this.x;
    }

    void P() {
        Drawable background = this.f1092a.getBackground();
        Drawable drawable = this.t;
        if (background != drawable) {
            ViewCompat.N(this.f1092a, drawable);
        }
        TextView textView = (TextView) M(R.id.title);
        if (textView == null || this.u == null || textView.getTextColors().equals(this.u)) {
            return;
        }
        textView.setTextColor(this.u);
    }

    public void Q(boolean z) {
        this.w = z;
    }

    public void R(boolean z) {
        this.x = z;
    }
}
