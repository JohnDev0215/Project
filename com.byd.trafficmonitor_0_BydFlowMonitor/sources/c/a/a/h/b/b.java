package c.a.a.h.b;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: BaseViewHolder.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends RecyclerView.c0 {
    public final SparseArray<View> t;
    public a u;

    public b(View view, a aVar) {
        super(view);
        N(aVar);
        this.t = new SparseArray<>();
    }

    public <T extends View> T M(int i2) {
        T t = (T) this.t.get(i2);
        if (t != null) {
            return t;
        }
        T t2 = (T) this.f1185b.findViewById(i2);
        this.t.put(i2, t2);
        return t2;
    }

    public final void N(a aVar) {
        this.u = aVar;
    }

    public b O(int i2, Drawable drawable) {
        ((ImageView) M(i2)).setImageDrawable(drawable);
        return this;
    }

    public b P(int i2, CharSequence charSequence) {
        TextView textView = (TextView) M(i2);
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            textView.setText(charSequence);
        }
        return this;
    }
}
