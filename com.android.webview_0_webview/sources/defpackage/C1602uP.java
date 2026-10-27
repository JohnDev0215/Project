package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;
import java.util.Objects;
import org.chromium.base.SysUtils;

/* JADX INFO: renamed from: uP, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1602uP {
    public Toast a;
    public ViewGroup b;

    public C1602uP(Context context, Toast toast) {
        this.a = toast;
        if (SysUtils.isLowEndDevice()) {
            this.b = new FrameLayout(new C1547tP(this, context));
            View view = toast.getView();
            ViewGroup viewGroup = this.b;
            if (viewGroup != null) {
                viewGroup.removeAllViews();
                if (view != null) {
                    this.b.addView(view, -2, -2);
                    this.a.setView(this.b);
                } else {
                    this.a.setView(null);
                }
            } else {
                this.a.setView(view);
            }
        }
        Toast toast2 = this.a;
        toast2.setGravity(toast2.getGravity(), this.a.getXOffset(), this.a.getYOffset() + 0);
    }

    public static C1602uP a(Context context, CharSequence charSequence, int i) {
        Objects.requireNonNull(C1657vP.a());
        return new C1602uP(context, Toast.makeText(context, charSequence, i));
    }
}
