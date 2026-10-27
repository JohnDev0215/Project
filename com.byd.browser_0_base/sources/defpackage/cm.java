package defpackage;

import android.view.accessibility.CaptioningManager;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/* JADX INFO: compiled from: CaptioningBridge.java */
/* JADX INFO: loaded from: classes3.dex */
public final class cm extends CaptioningManager.CaptioningChangeListener {
    public static cm c;
    public final dm a = new dm();
    public final CaptioningManager b = (CaptioningManager) xz.a.getSystemService("captioning");

    public static em a(CaptioningManager.CaptionStyle captionStyle) {
        if (captionStyle == null) {
            return new em(null, null, null, null, null);
        }
        Integer numValueOf = captionStyle.hasBackgroundColor() ? Integer.valueOf(captionStyle.backgroundColor) : null;
        Integer numValueOf2 = captionStyle.hasEdgeColor() ? Integer.valueOf(captionStyle.edgeColor) : null;
        Integer numValueOf3 = captionStyle.hasEdgeType() ? Integer.valueOf(captionStyle.edgeType) : null;
        Integer numValueOf4 = captionStyle.hasForegroundColor() ? Integer.valueOf(captionStyle.foregroundColor) : null;
        captionStyle.hasWindowColor();
        return new em(numValueOf, numValueOf2, numValueOf3, numValueOf4, captionStyle.getTypeface());
    }

    public final void b() {
        CaptioningManager captioningManager = this.b;
        boolean zIsEnabled = captioningManager.isEnabled();
        dm dmVar = this.a;
        dmVar.a = zIsEnabled;
        dmVar.c();
        dmVar.h = new DecimalFormat("#%", new DecimalFormatSymbols(Locale.US)).format(captioningManager.getFontScale());
        dmVar.c();
        captioningManager.getLocale();
        dmVar.d(a(captioningManager.getUserStyle()));
    }

    @Override // android.view.accessibility.CaptioningManager.CaptioningChangeListener
    public final void onEnabledChanged(boolean z) {
        dm dmVar = this.a;
        dmVar.a = z;
        dmVar.c();
    }

    @Override // android.view.accessibility.CaptioningManager.CaptioningChangeListener
    public final void onFontScaleChanged(float f) {
        dm dmVar = this.a;
        dmVar.getClass();
        dmVar.h = new DecimalFormat("#%", new DecimalFormatSymbols(Locale.US)).format(f);
        dmVar.c();
    }

    @Override // android.view.accessibility.CaptioningManager.CaptioningChangeListener
    public final void onLocaleChanged(Locale locale) {
        this.a.getClass();
    }

    @Override // android.view.accessibility.CaptioningManager.CaptioningChangeListener
    public final void onUserStyleChanged(CaptioningManager.CaptionStyle captionStyle) {
        this.a.d(a(captionStyle));
    }
}
