package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.SystemClock;
import android.view.View;
import org.chromium.base.TraceEvent;

/* JADX INFO: renamed from: aP, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ViewOnLayoutChangeListenerC0493aP extends YO implements View.OnLayoutChangeListener {
    public final View a;
    public Bitmap c;
    public long f;
    public final Rect b = new Rect();
    public Rect d = new Rect();
    public float e = 1.0f;

    public ViewOnLayoutChangeListenerC0493aP(View view) {
        this.a = view;
        view.addOnLayoutChangeListener(this);
        this.b.set(0, 0, this.a.getWidth(), this.a.getHeight());
    }

    @Override // defpackage.QO
    public Rect a() {
        return this.d;
    }

    @Override // defpackage.QO
    public final C0549bP b() {
        return null;
    }

    @Override // defpackage.QO
    public Bitmap c() {
        if (this.f > 0) {
            It.c("ViewResourceAdapter.GetBitmapInterval", SystemClock.elapsedRealtime() - this.f, 1L, 3600000L, 50);
        }
        TraceEvent.x("ViewResourceAdapter:getBitmap", null);
        int width = (int) (this.a.getWidth() * this.e);
        int height = (int) (this.a.getHeight() * this.e);
        boolean z = width == 0 || height == 0;
        if (z) {
            width = 1;
            height = 1;
        }
        Bitmap bitmap = this.c;
        if (bitmap != null && (bitmap.getWidth() != width || this.c.getHeight() != height)) {
            this.c.recycle();
            this.c = null;
        }
        if (this.c == null) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            this.c = bitmapCreateBitmap;
            bitmapCreateBitmap.setHasAlpha(true);
            this.d.set(0, 0, this.a.getWidth(), this.a.getHeight());
            this.b.set(this.d);
        }
        if (!z) {
            Canvas canvas = new Canvas(this.c);
            this.b.isEmpty();
            if (!this.b.isEmpty()) {
                canvas.clipRect(this.b);
            }
            canvas.save();
            float f = this.e;
            canvas.scale(f, f);
            this.a.draw(canvas);
            canvas.restore();
        } else {
            this.c.setPixel(0, 0, 0);
        }
        this.b.setEmpty();
        TraceEvent.A("ViewResourceAdapter:getBitmap");
        this.f = SystemClock.elapsedRealtime();
        return this.c;
    }

    @Override // defpackage.QO
    public long d() {
        return SO.a(null);
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = i3 - i;
        int i10 = i4 - i2;
        int i11 = i8 - i6;
        if (i9 == i7 - i5 && i10 == i11) {
            return;
        }
        this.b.set(0, 0, i9, i10);
    }
}
