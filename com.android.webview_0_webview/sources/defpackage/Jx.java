package defpackage;

import android.graphics.RectF;
import java.util.Objects;
import org.chromium.ui.base.LocalizationUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Jx implements Comparable {
    public float s;
    public float t;
    public final /* synthetic */ Kx u;

    public Jx(Kx kx, float f, float f2) {
        this.u = kx;
        this.s = f;
        this.t = f2;
    }

    public float a() {
        return (this.s + this.t) * 0.5f;
    }

    public RectF b() {
        Kx kx = this.u;
        Objects.requireNonNull(kx);
        RectF rectF = new RectF(LocalizationUtils.isLayoutRtl() ? 0 : kx.getWidth() - kx.z, this.s, r0 + this.u.z, this.t);
        rectF.inset(2.0f, 0.5f);
        rectF.offset(LocalizationUtils.isLayoutRtl() ? -0.5f : 0.5f, 0.0f);
        return rectF;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return Float.compare(a(), ((Jx) obj).a());
    }
}
