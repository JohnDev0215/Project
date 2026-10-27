package y6;

import A7.m;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import t6.C2441b;

/* JADX INFO: loaded from: classes.dex */
public final class b extends View {
    public x6.c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C2441b f24132m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Paint f24133n;
    public x6.a o;

    public final void a(float f10) {
        Log.i("changeOpacity", "changeOpacity : from " + getAlpha() + " to " + f10);
        setAlpha(f10);
    }

    public final void b(float f10) {
        Log.i("drawCredits", "drawCredits changeOpacity : from " + getAlpha() + " to " + f10);
        a(f10);
        invalidate();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        m.f(canvas, "canvas");
        if (this.f24132m != null) {
            x6.a aVar = this.o;
            if (aVar == null) {
                m.j("lineData");
                throw null;
            }
            if (aVar.f23786a > 0.0f) {
                float height = getHeight();
                C2441b c2441b = this.f24132m;
                if (c2441b == null) {
                    m.j("kmlCredits");
                    throw null;
                }
                float size = c2441b.f22476b.size();
                x6.a aVar2 = this.o;
                if (aVar2 == null) {
                    m.j("lineData");
                    throw null;
                }
                float f10 = aVar2.f23786a;
                float f11 = 2;
                float f12 = (height - (size * f10)) / f11;
                if (this.l == null) {
                    m.j("lyricsConfigs");
                    throw null;
                }
                float f13 = (f10 / f11) + f12 + r7.d.f23803g;
                C2441b c2441b2 = this.f24132m;
                if (c2441b2 == null) {
                    m.j("kmlCredits");
                    throw null;
                }
                for (String str : c2441b2.f22476b) {
                    float width = getWidth() / 2;
                    Paint paint = this.f24133n;
                    if (paint == null) {
                        m.j("colorPaint");
                        throw null;
                    }
                    canvas.drawText(str, width, f13, paint);
                    x6.a aVar3 = this.o;
                    if (aVar3 == null) {
                        m.j("lineData");
                        throw null;
                    }
                    f13 += aVar3.f23786a;
                }
            }
        }
    }

    public final void setCredits(C2441b c2441b) {
        m.f(c2441b, "kmlCredits");
        this.f24132m = c2441b;
    }

    public final void setKaraokePaint(Paint paint) {
        m.f(paint, "paint");
        this.f24133n = paint;
    }

    public final void setLineData(x6.a aVar) {
        m.f(aVar, "lineData");
        this.o = aVar;
        invalidate();
    }
}
