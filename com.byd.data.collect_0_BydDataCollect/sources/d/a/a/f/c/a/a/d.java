package d.a.a.f.c.a.a;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.bydux.api.common.views.ctrl.BydLoading;

/* JADX INFO: compiled from: AttrBydLoading.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends l {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f4643e = "byd_loading_textColor";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final String f4644f = "byd_loading_background";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f4645g;

    public d(String str) {
        this.f4645g = str;
    }

    @Override // d.a.a.f.c.a.a.l
    public void a(View view) {
        if (view instanceof BydLoading) {
            BydLoading bydLoading = (BydLoading) view;
            if ("color".equals(this.f4654d)) {
                int iJ = d.a.a.f.c.a.c.c.n().j(this.f4652b);
                if ("byd_loading_textColor".equals(this.f4645g)) {
                    bydLoading.setLoadingTextColor(iJ);
                    return;
                }
                return;
            }
            if ("drawable".equals(this.f4654d) || "mipmap".equals(this.f4654d)) {
                Drawable drawableL = "drawable".equals(this.f4654d) ? d.a.a.f.c.a.c.c.n().l(this.f4652b) : d.a.a.f.c.a.c.c.n().o(this.f4652b);
                if ("byd_loading_background".equals(this.f4645g)) {
                    bydLoading.setLoadingBackground(drawableL);
                }
            }
        }
    }
}
