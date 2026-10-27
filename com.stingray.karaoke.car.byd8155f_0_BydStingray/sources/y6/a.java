package y6;

import A7.m;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import h2.AbstractC1347d;
import w6.C2604a;

/* JADX INFO: loaded from: classes.dex */
public final class a extends View {
    public x6.c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C2604a f24129m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f24130n;
    public RectF o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f24131p;

    public final x6.c getLyricsConfigs() {
        x6.c cVar = this.l;
        if (cVar != null) {
            return cVar;
        }
        m.j("lyricsConfigs");
        throw null;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        m.f(canvas, "canvas");
        float width = (getWidth() * this.f24130n) / 2;
        float width2 = getWidth() / 2.0f;
        float f10 = width2 - width;
        float f11 = width2 + width;
        float f12 = this.f24131p;
        float fA = f12 <= 0.0f ? f10 : f12 < 1.0f ? AbstractC1347d.a(f11, f10, f12, f10) : f11;
        RectF rectF = this.o;
        rectF.set(f10, 0.0f, f11, getHeight());
        C2604a c2604a = this.f24129m;
        if (c2604a == null) {
            m.j("breakPaint");
            throw null;
        }
        canvas.drawRect(rectF, c2604a.f23338a);
        rectF.set(f10, 0.0f, fA, getHeight());
        C2604a c2604a2 = this.f24129m;
        if (c2604a2 != null) {
            canvas.drawRect(rectF, c2604a2.f23339b);
        } else {
            m.j("breakPaint");
            throw null;
        }
    }

    public final void setBreakPaint(int i7) {
        x6.f fVar = getLyricsConfigs().c;
        Paint paint = new Paint();
        paint.setColor(i7);
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        Paint paint2 = new Paint();
        paint2.setColor(i7);
        paint2.setStyle(style);
        paint2.setAlpha((int) (0.4f * 255));
        this.f24129m = new C2604a(paint2, paint);
    }

    public final void setLyricsConfigs(x6.c cVar) {
        m.f(cVar, "<set-?>");
        this.l = cVar;
    }
}
