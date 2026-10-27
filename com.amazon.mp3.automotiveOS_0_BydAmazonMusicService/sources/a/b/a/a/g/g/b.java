package a.b.a.a.g.g;

import a.b.a.a.g.c;
import a.b.a.a.g.g.a;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.view.View;
import com.netinsight.sye.syeClient.generated.enums.cEA708.EdgeStyle;
import com.netinsight.sye.syeClient.generated.enums.cEA708.FontStyle;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @JvmField
    public static final Typeface f67a;

    @JvmField
    public static final Typeface b;

    @JvmField
    public static final Typeface c;

    @JvmField
    public static final Typeface d;

    @JvmField
    public static final Typeface e;

    @JvmField
    public static final Typeface f;

    @JvmField
    public static final Typeface g;

    @JvmField
    public static final Typeface h;

    @JvmField
    public static final Typeface i;
    public static final Typeface j;
    public static final Typeface k;
    public static Typeface l;
    public static final Typeface m;
    public static final Typeface n;
    public static final Typeface o;
    public static Typeface p;
    public static final Typeface q;
    public static final Typeface r;
    public final TextPaint s;
    public final Paint t;

    public static final class a extends ThreadLocal<Rect> {
        @Override // java.lang.ThreadLocal
        public Rect initialValue() {
            return new Rect();
        }
    }

    static {
        Typeface typefaceCreate = Typeface.create("monospace", 0);
        Intrinsics.checkExpressionValueIsNotNull(typefaceCreate, "Typeface.create(\"monospace\", Typeface.NORMAL)");
        f67a = typefaceCreate;
        Typeface typefaceCreate2 = Typeface.create("serif-monospace", 0);
        Intrinsics.checkExpressionValueIsNotNull(typefaceCreate2, "Typeface.create(\"serif-m…ospace\", Typeface.NORMAL)");
        b = typefaceCreate2;
        Typeface typefaceCreate3 = Typeface.create("serif", 0);
        Intrinsics.checkExpressionValueIsNotNull(typefaceCreate3, "Typeface.create(\"serif\", Typeface.NORMAL)");
        c = typefaceCreate3;
        Typeface typefaceCreate4 = Typeface.create("monospace", 0);
        Intrinsics.checkExpressionValueIsNotNull(typefaceCreate4, "Typeface.create(\"monospace\", Typeface.NORMAL)");
        d = typefaceCreate4;
        Typeface typefaceCreate5 = Typeface.create("sans-serif", 0);
        Intrinsics.checkExpressionValueIsNotNull(typefaceCreate5, "Typeface.create(\"sans-serif\", Typeface.NORMAL)");
        e = typefaceCreate5;
        Typeface typefaceCreate6 = Typeface.create("casual", 0);
        Intrinsics.checkExpressionValueIsNotNull(typefaceCreate6, "Typeface.create(\"casual\", Typeface.NORMAL)");
        f = typefaceCreate6;
        Typeface typefaceCreate7 = Typeface.create("cursive", 0);
        Intrinsics.checkExpressionValueIsNotNull(typefaceCreate7, "Typeface.create(\"cursive\", Typeface.NORMAL)");
        g = typefaceCreate7;
        Typeface typefaceCreate8 = Typeface.create("sans-serif-smallcaps", 0);
        Intrinsics.checkExpressionValueIsNotNull(typefaceCreate8, "Typeface.create(\"sans-se…llcaps\", Typeface.NORMAL)");
        h = typefaceCreate8;
        Typeface typeface = Typeface.DEFAULT;
        Intrinsics.checkExpressionValueIsNotNull(typeface, "Typeface.DEFAULT");
        i = typeface;
        Typeface typefaceCreate9 = Typeface.create("monospace", 2);
        Intrinsics.checkExpressionValueIsNotNull(typefaceCreate9, "Typeface.create(\"monospace\", Typeface.ITALIC)");
        j = typefaceCreate9;
        Typeface typefaceCreate10 = Typeface.create("serif-monospace", 2);
        Intrinsics.checkExpressionValueIsNotNull(typefaceCreate10, "Typeface.create(\"serif-m…ospace\", Typeface.ITALIC)");
        k = typefaceCreate10;
        Typeface typefaceCreate11 = Typeface.create("serif", 2);
        Intrinsics.checkExpressionValueIsNotNull(typefaceCreate11, "Typeface.create(\"serif\", Typeface.ITALIC)");
        l = typefaceCreate11;
        Typeface typefaceCreate12 = Typeface.create("monospace", 2);
        Intrinsics.checkExpressionValueIsNotNull(typefaceCreate12, "Typeface.create(\"monospace\", Typeface.ITALIC)");
        m = typefaceCreate12;
        n = Typeface.create("sans-serif", 2);
        Typeface typefaceCreate13 = Typeface.create("casual", 2);
        Intrinsics.checkExpressionValueIsNotNull(typefaceCreate13, "Typeface.create(\"casual\", Typeface.ITALIC)");
        o = typefaceCreate13;
        Typeface typefaceCreate14 = Typeface.create("cursive", 2);
        Intrinsics.checkExpressionValueIsNotNull(typefaceCreate14, "Typeface.create(\"cursive\", Typeface.ITALIC)");
        p = typefaceCreate14;
        Typeface typefaceCreate15 = Typeface.create("sans-serif-smallcaps", 2);
        Intrinsics.checkExpressionValueIsNotNull(typefaceCreate15, "Typeface.create(\"sans-se…llcaps\", Typeface.ITALIC)");
        q = typefaceCreate15;
        Typeface typefaceCreate16 = Typeface.create(Typeface.DEFAULT, 2);
        Intrinsics.checkExpressionValueIsNotNull(typefaceCreate16, "Typeface.create(Typeface.DEFAULT, Typeface.ITALIC)");
        r = typefaceCreate16;
    }

    public b(View view) {
        new a();
        view.setLayerType(1, null);
        Paint paint = new Paint();
        this.t = paint;
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        TextPaint textPaint = new TextPaint();
        this.s = textPaint;
        textPaint.setAntiAlias(true);
        textPaint.setSubpixelText(true);
    }

    public final Typeface a(FontStyle fontStyle, boolean z) {
        switch (fontStyle) {
            case Default:
                return z ? j : f67a;
            case MonospacedSerifs:
                return z ? k : b;
            case ProportionalSerifs:
                return z ? l : c;
            case Monospaced:
                return z ? m : d;
            case Proportional:
                Typeface typeface = z ? n : e;
                Intrinsics.checkExpressionValueIsNotNull(typeface, "if (italic) TYPEFACE_PRO…lse TYPEFACE_PROPORTIONAL");
                return typeface;
            case Casual:
                return z ? o : f;
            case Cursive:
                return z ? p : g;
            case SmallCaps:
                return z ? q : h;
            case CustomFont:
                return z ? r : i;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0406  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(java.util.List<a.b.a.a.g.g.a.C0010a.b> r27, android.graphics.Canvas r28) {
        /*
            Method dump skipped, instruction units count: 1118
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.a.a.g.g.b.a(java.util.List, android.graphics.Canvas):void");
    }

    public final void a(a.C0010a.d dVar, Canvas canvas, int i2, int i3) {
        this.s.setColor(dVar.g);
        this.s.setStyle(Paint.Style.FILL);
        this.s.setMaskFilter(null);
        this.s.setShader(null);
        this.s.clearShadowLayer();
        EdgeStyle edgeStyle = dVar.i;
        if (edgeStyle == EdgeStyle.LeftDropShadow) {
            this.s.setShadowLayer(2.0f, -2.0f, 2.0f, Color.argb(128, 0, 0, 0));
        } else if (edgeStyle == EdgeStyle.RightDropShadow) {
            this.s.setShadowLayer(2.0f, 2.0f, 2.0f, Color.argb(128, 0, 0, 0));
        }
        canvas.drawText(dVar.f, i2, i3, this.s);
    }
}
