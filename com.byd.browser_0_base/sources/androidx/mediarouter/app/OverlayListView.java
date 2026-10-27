package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import android.widget.ListView;
import defpackage.C0173a;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes.dex */
public final class OverlayListView extends ListView {
    public final ArrayList e;

    public static class a {
        public final BitmapDrawable a;
        public final Rect c;
        public Interpolator d;
        public long e;
        public final Rect f;
        public int g;
        public long j;
        public boolean k;
        public boolean l;
        public InterfaceC0012a m;
        public float b = 1.0f;
        public float h = 1.0f;
        public float i = 1.0f;

        /* JADX INFO: renamed from: androidx.mediarouter.app.OverlayListView$a$a, reason: collision with other inner class name */
        public interface InterfaceC0012a {
        }

        public a(BitmapDrawable bitmapDrawable, Rect rect) {
            this.a = bitmapDrawable;
            this.f = rect;
            Rect rect2 = new Rect(rect);
            this.c = rect2;
            if (bitmapDrawable != null) {
                bitmapDrawable.setAlpha((int) (this.b * 255.0f));
                bitmapDrawable.setBounds(rect2);
            }
        }
    }

    public OverlayListView(Context context) {
        super(context);
        this.e = new ArrayList();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        boolean z;
        super.onDraw(canvas);
        ArrayList arrayList = this.e;
        if (arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                BitmapDrawable bitmapDrawable = aVar.a;
                if (bitmapDrawable != null) {
                    bitmapDrawable.draw(canvas);
                }
                long drawingTime = getDrawingTime();
                if (aVar.l) {
                    z = false;
                } else {
                    float fMax = aVar.k ? Math.max(0.0f, Math.min(1.0f, (drawingTime - aVar.j) / aVar.e)) : 0.0f;
                    Interpolator interpolator = aVar.d;
                    float interpolation = interpolator == null ? fMax : interpolator.getInterpolation(fMax);
                    int i = (int) (aVar.g * interpolation);
                    Rect rect = aVar.f;
                    int i2 = rect.top + i;
                    Rect rect2 = aVar.c;
                    rect2.top = i2;
                    rect2.bottom = rect.bottom + i;
                    float f = aVar.h;
                    float f2 = C0173a.f(aVar.i, f, interpolation, f);
                    aVar.b = f2;
                    BitmapDrawable bitmapDrawable2 = aVar.a;
                    if (bitmapDrawable2 != null) {
                        bitmapDrawable2.setAlpha((int) (f2 * 255.0f));
                        bitmapDrawable2.setBounds(rect2);
                    }
                    if (aVar.k && fMax >= 1.0f) {
                        aVar.l = true;
                        a.InterfaceC0012a interfaceC0012a = aVar.m;
                        if (interfaceC0012a != null) {
                            androidx.mediarouter.app.a aVar2 = (androidx.mediarouter.app.a) interfaceC0012a;
                            MediaRouteControllerDialog mediaRouteControllerDialog = aVar2.b;
                            mediaRouteControllerDialog.N.remove(aVar2.a);
                            mediaRouteControllerDialog.f14J.notifyDataSetChanged();
                        }
                    }
                    z = !aVar.l;
                }
                if (!z) {
                    it.remove();
                }
            }
        }
    }

    public OverlayListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new ArrayList();
    }

    public OverlayListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new ArrayList();
    }
}
