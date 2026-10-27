package n0;

import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: compiled from: ViewBindingAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class f {
    private static int a(float f7) {
        int i7 = (int) (0.5f + f7);
        if (i7 != 0) {
            return i7;
        }
        if (f7 == 0.0f) {
            return 0;
        }
        return f7 > 0.0f ? 1 : -1;
    }

    public static void b(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    public static void c(View view, View.OnClickListener onClickListener, boolean z6) {
        view.setOnClickListener(onClickListener);
        view.setClickable(z6);
    }

    public static void d(View view, float f7) {
        view.setPadding(view.getPaddingLeft(), a(f7), view.getPaddingRight(), view.getPaddingBottom());
    }
}
