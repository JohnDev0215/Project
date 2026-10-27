package defpackage;

import android.view.accessibility.CaptioningManager;
import java.util.Objects;

/* JADX INFO: renamed from: sA, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1477sA implements InterfaceC1587uA {
    public static C1477sA d;
    public final CaptioningManager.CaptioningChangeListener a = new C1422rA(this, null);
    public final C1258oA b = new C1258oA();
    public final CaptioningManager c = (CaptioningManager) Is.a.getSystemService("captioning");

    @Override // defpackage.InterfaceC1587uA
    public void a(InterfaceC1532tA interfaceC1532tA) {
        if (!this.b.b()) {
            this.c.addCaptioningChangeListener(this.a);
            e();
        }
        this.b.i.put(interfaceC1532tA, null);
        this.b.c(interfaceC1532tA);
    }

    @Override // defpackage.InterfaceC1587uA
    public void b(InterfaceC1532tA interfaceC1532tA) {
        if (!this.b.b()) {
            e();
        }
        this.b.c(interfaceC1532tA);
    }

    @Override // defpackage.InterfaceC1587uA
    public void c(InterfaceC1532tA interfaceC1532tA) {
        this.b.i.remove(interfaceC1532tA);
        if (this.b.b()) {
            return;
        }
        this.c.removeCaptioningChangeListener(this.a);
    }

    public final C1313pA d(CaptioningManager.CaptionStyle captionStyle) {
        if (captionStyle == null) {
            return new C1313pA(null, null, null, null, null, null);
        }
        return new C1313pA(captionStyle.hasBackgroundColor() ? Integer.valueOf(captionStyle.backgroundColor) : null, captionStyle.hasEdgeColor() ? Integer.valueOf(captionStyle.edgeColor) : null, captionStyle.hasEdgeType() ? Integer.valueOf(captionStyle.edgeType) : null, captionStyle.hasForegroundColor() ? Integer.valueOf(captionStyle.foregroundColor) : null, captionStyle.hasWindowColor() ? Integer.valueOf(captionStyle.windowColor) : null, captionStyle.getTypeface());
    }

    public final void e() {
        C1258oA c1258oA = this.b;
        c1258oA.a = this.c.isEnabled();
        c1258oA.d();
        this.b.e(this.c.getFontScale());
        C1258oA c1258oA2 = this.b;
        this.c.getLocale();
        Objects.requireNonNull(c1258oA2);
        this.b.f(d(this.c.getUserStyle()));
    }
}
