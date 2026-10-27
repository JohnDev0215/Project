package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class V0 {
    public final Context a;
    public final View b;
    public final TextView c;
    public final WindowManager.LayoutParams d = new WindowManager.LayoutParams();
    public final Rect e = new Rect();
    public final int[] f = new int[2];
    public final int[] g = new int[2];

    public V0(Context context) {
        this.a = context;
        View viewInflate = LayoutInflater.from(context).inflate(AbstractC0425Xm.B, (ViewGroup) null);
        this.b = viewInflate;
        this.c = (TextView) viewInflate.findViewById(AbstractC0371Um.q2);
        this.d.setTitle(V0.class.getSimpleName());
        this.d.packageName = this.a.getPackageName();
        WindowManager.LayoutParams layoutParams = this.d;
        layoutParams.type = 1002;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = AbstractC0622cn.h;
        layoutParams.flags = 24;
    }

    public void a() {
        if (this.b.getParent() != null) {
            ((WindowManager) this.a.getSystemService("window")).removeView(this.b);
        }
    }
}
