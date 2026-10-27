package defpackage;

import android.view.accessibility.CaptioningManager;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: renamed from: rA, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1422rA extends CaptioningManager.CaptioningChangeListener {
    public final /* synthetic */ C1477sA a;

    public C1422rA(C1477sA c1477sA, AbstractC1368qA abstractC1368qA) {
        this.a = c1477sA;
    }

    @Override // android.view.accessibility.CaptioningManager.CaptioningChangeListener
    public void onEnabledChanged(boolean z) {
        C1258oA c1258oA = this.a.b;
        c1258oA.a = z;
        c1258oA.d();
    }

    @Override // android.view.accessibility.CaptioningManager.CaptioningChangeListener
    public void onFontScaleChanged(float f) {
        this.a.b.e(f);
    }

    @Override // android.view.accessibility.CaptioningManager.CaptioningChangeListener
    public void onLocaleChanged(Locale locale) {
        Objects.requireNonNull(this.a.b);
    }

    @Override // android.view.accessibility.CaptioningManager.CaptioningChangeListener
    public void onUserStyleChanged(CaptioningManager.CaptionStyle captionStyle) {
        this.a.b.f(this.a.d(captionStyle));
    }
}
