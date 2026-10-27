package b.a.a.i.n;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static void a(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }
}
