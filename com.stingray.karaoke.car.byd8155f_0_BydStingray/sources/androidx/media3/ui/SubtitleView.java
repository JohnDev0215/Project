package androidx.media3.ui;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.CaptioningManager;
import android.widget.FrameLayout;
import g2.C1288a;
import g2.C1289b;
import g2.f;
import h6.n;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import m3.C1803c;
import m3.C1804d;
import m3.H;
import m3.N;

/* JADX INFO: loaded from: classes.dex */
public final class SubtitleView extends FrameLayout {
    public List l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C1804d f12051m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f12052n;
    public float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f12053p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f12054q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f12055r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public H f12056s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public View f12057t;

    public SubtitleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = Collections.emptyList();
        this.f12051m = C1804d.f19012g;
        this.f12052n = 0.0533f;
        this.o = 0.08f;
        this.f12053p = true;
        this.f12054q = true;
        C1803c c1803c = new C1803c(context);
        this.f12056s = c1803c;
        this.f12057t = c1803c;
        addView(c1803c);
        this.f12055r = 1;
    }

    private List<C1289b> getCuesWithStylingPreferencesApplied() {
        if (this.f12053p && this.f12054q) {
            return this.l;
        }
        ArrayList arrayList = new ArrayList(this.l.size());
        for (int i7 = 0; i7 < this.l.size(); i7++) {
            C1288a c1288aA = ((C1289b) this.l.get(i7)).a();
            if (!this.f12053p) {
                c1288aA.f14766n = false;
                CharSequence charSequence = c1288aA.f14756a;
                if (charSequence instanceof Spanned) {
                    if (!(charSequence instanceof Spannable)) {
                        c1288aA.f14756a = SpannableString.valueOf(charSequence);
                    }
                    CharSequence charSequence2 = c1288aA.f14756a;
                    charSequence2.getClass();
                    Spannable spannable = (Spannable) charSequence2;
                    for (Object obj : spannable.getSpans(0, spannable.length(), Object.class)) {
                        if (!(obj instanceof f)) {
                            spannable.removeSpan(obj);
                        }
                    }
                }
                n.r(c1288aA);
            } else if (!this.f12054q) {
                n.r(c1288aA);
            }
            arrayList.add(c1288aA.a());
        }
        return arrayList;
    }

    private float getUserCaptionFontScale() {
        CaptioningManager captioningManager;
        if (isInEditMode() || (captioningManager = (CaptioningManager) getContext().getSystemService("captioning")) == null || !captioningManager.isEnabled()) {
            return 1.0f;
        }
        return captioningManager.getFontScale();
    }

    private C1804d getUserCaptionStyle() {
        CaptioningManager captioningManager;
        boolean zIsInEditMode = isInEditMode();
        C1804d c1804d = C1804d.f19012g;
        if (zIsInEditMode || (captioningManager = (CaptioningManager) getContext().getSystemService("captioning")) == null || !captioningManager.isEnabled()) {
            return c1804d;
        }
        CaptioningManager.CaptionStyle userStyle = captioningManager.getUserStyle();
        return new C1804d(userStyle.hasForegroundColor() ? userStyle.foregroundColor : -1, userStyle.hasBackgroundColor() ? userStyle.backgroundColor : -16777216, userStyle.hasWindowColor() ? userStyle.windowColor : 0, userStyle.hasEdgeType() ? userStyle.edgeType : 0, userStyle.hasEdgeColor() ? userStyle.edgeColor : -1, userStyle.getTypeface());
    }

    private <T extends View & H> void setView(T t10) {
        removeView(this.f12057t);
        View view = this.f12057t;
        if (view instanceof N) {
            ((N) view).f19003m.destroy();
        }
        this.f12057t = t10;
        this.f12056s = t10;
        addView(t10);
    }

    public final void a() {
        setStyle(getUserCaptionStyle());
    }

    public final void b() {
        setFractionalTextSize(getUserCaptionFontScale() * 0.0533f);
    }

    public final void c() {
        this.f12056s.a(getCuesWithStylingPreferencesApplied(), this.f12051m, this.f12052n, this.o);
    }

    public void setApplyEmbeddedFontSizes(boolean z6) {
        this.f12054q = z6;
        c();
    }

    public void setApplyEmbeddedStyles(boolean z6) {
        this.f12053p = z6;
        c();
    }

    public void setBottomPaddingFraction(float f10) {
        this.o = f10;
        c();
    }

    public void setCues(List<C1289b> list) {
        if (list == null) {
            list = Collections.emptyList();
        }
        this.l = list;
        c();
    }

    public void setFractionalTextSize(float f10) {
        this.f12052n = f10;
        c();
    }

    public void setStyle(C1804d c1804d) {
        this.f12051m = c1804d;
        c();
    }

    public void setViewType(int i7) {
        if (this.f12055r == i7) {
            return;
        }
        if (i7 == 1) {
            setView(new C1803c(getContext()));
        } else {
            if (i7 != 2) {
                throw new IllegalArgumentException();
            }
            setView(new N(getContext()));
        }
        this.f12055r = i7;
    }
}
