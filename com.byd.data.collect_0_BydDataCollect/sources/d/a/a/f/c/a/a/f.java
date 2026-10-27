package d.a.a.f.c.a.a;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

/* JADX INFO: compiled from: AttrDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends l {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f4646e = "drawableLeft";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final String f4647f = "drawableRight";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final String f4648g = "drawableTop";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final String f4649h = "drawableBottom";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private String f4650i;

    public f(String str) {
        this.f4650i = str;
    }

    @Override // d.a.a.f.c.a.a.l
    public void a(View view) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            if ("drawable".equals(this.f4654d) || "mipmap".equals(this.f4654d)) {
                Drawable drawableL = "drawable".equals(this.f4654d) ? d.a.a.f.c.a.c.c.n().l(this.f4652b) : d.a.a.f.c.a.c.c.n().o(this.f4652b);
                Drawable[] compoundDrawables = textView.getCompoundDrawables();
                if ("drawableLeft".equals(this.f4650i)) {
                    d.a.a.f.b.b.a.j("drawable left id=" + this.f4652b);
                    textView.setCompoundDrawablesWithIntrinsicBounds(drawableL, compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
                    return;
                }
                if ("drawableRight".equals(this.f4650i)) {
                    textView.setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1], drawableL, compoundDrawables[3]);
                } else if ("drawableTop".equals(this.f4650i)) {
                    textView.setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], drawableL, compoundDrawables[2], compoundDrawables[3]);
                } else if ("drawableBottom".equals(this.f4650i)) {
                    textView.setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], drawableL);
                }
            }
        }
    }
}
