package defpackage;

import J.N;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Size;
import android.view.View;
import defpackage.u12;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.UnguessableToken;
import org.chromium.components.paintpreview.player.PlayerCompositorDelegateImpl;
import org.chromium.components.paintpreview.player.a;
import org.chromium.components.paintpreview.player.b;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: PlayerFrameMediator.java */
/* JADX INFO: loaded from: classes3.dex */
public final class y12 implements d22, z12 {
    public final UnguessableToken a;
    public final Size b;
    public final PropertyModel g;
    public final a h;
    public final e22 i;
    public boolean j;
    public float l;
    public float m;
    public final Matrix n;
    public final Point o;
    public final v12 p;
    public final f22 q;
    public Runnable r;
    public final ArrayList c = new ArrayList();
    public final ArrayList d = new ArrayList();
    public final ArrayList e = new ArrayList();
    public final ArrayList f = new ArrayList();
    public final Rect k = new Rect();

    public y12(PropertyModel propertyModel, PlayerCompositorDelegateImpl playerCompositorDelegateImpl, f22 f22Var, UnguessableToken unguessableToken, Size size, int i, int i2, float f, es3 es3Var) {
        Matrix matrix = new Matrix();
        this.n = matrix;
        this.o = new Point();
        this.g = propertyModel;
        propertyModel.p(a22.g, matrix);
        this.h = playerCompositorDelegateImpl;
        this.q = f22Var;
        e22 e22Var = new e22();
        this.i = e22Var;
        this.j = false;
        this.l = f;
        this.a = unguessableToken;
        this.b = size;
        this.p = new v12(unguessableToken, e22Var, size, playerCompositorDelegateImpl, this);
        e22Var.b.postTranslate(i, i2);
        e22Var.f(this.l);
        this.r = es3Var;
    }

    public final void a() {
        j(true);
        int i = 0;
        while (true) {
            ArrayList arrayList = this.c;
            if (i >= arrayList.size()) {
                return;
            }
            if (((View) arrayList.get(i)).getVisibility() == 0) {
                ((y12) this.e.get(i)).a();
            }
            i++;
        }
    }

    public final void b() {
        Point point = this.o;
        Point point2 = this.i.d;
        point.set(point2.x, point2.y);
        v12 v12Var = this.p;
        if (v12Var.b != null) {
            u12 u12Var = v12Var.a;
            if (u12Var != null) {
                u12Var.c();
                v12Var.a = null;
            }
            u12 u12Var2 = v12Var.b;
            if (u12Var2 != null) {
                u12Var2.f = null;
                long j = ((PlayerCompositorDelegateImpl) u12Var2.h).b;
                if (j != 0) {
                    N.M$$iPM7t(j);
                }
            }
        }
        for (int i = 0; i < this.c.size(); i++) {
            ((y12) this.e.get(i)).b();
        }
    }

    public final void c(int i, int i2, boolean z) {
        gc3 gc3Var;
        e22 e22Var = this.i;
        float fB = e22Var.b();
        float fC = z ? 0.0f : e22Var.c();
        float fD = z ? 0.0f : e22Var.d();
        int iRound = Math.round((fC + i) / fB);
        int iRound2 = Math.round((fD + i2) / fB);
        long j = ((PlayerCompositorDelegateImpl) this.h).b;
        GURL gurl = null;
        if (j != 0) {
            String strMqJDIMXF = N.MqJDIMXF(j, this.a, iRound, iRound2);
            if (!TextUtils.isEmpty(strMqJDIMXF)) {
                gurl = new GURL(strMqJDIMXF);
            }
        }
        f22 f22Var = this.q;
        if (gurl != null && (gc3Var = f22Var.b) != null) {
            ((b.InterfaceC0137b) gc3Var.e).g(gurl);
            HashMap map = k22.a;
            n3.X0("PaintPreview.Player.LinkClicked");
            return;
        }
        v72 v72Var = f22Var.c;
        if (v72Var != null) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            List list = (List) v72Var.b;
            list.add(Long.valueOf(jCurrentTimeMillis));
            int size = list.size() - 1;
            while (true) {
                if (size <= 0) {
                    break;
                }
                int i3 = size - 1;
                if (((Long) list.get(size)).longValue() - ((Long) list.get(i3)).longValue() > 2000) {
                    list.subList(0, size).clear();
                    break;
                }
                size = i3;
            }
            if (list.size() == 3) {
                ((Runnable) v72Var.a).run();
                list.clear();
            }
        }
        HashMap map2 = k22.a;
        n3.X0("PaintPreview.Player.UnconsumedTap");
    }

    public final void d(float f, Matrix matrix) {
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        Matrix matrix2 = this.n;
        matrix2.setValues(fArr);
        Matrix matrix3 = new Matrix();
        int i = 0;
        matrix3.setScale(fArr[0], fArr[4]);
        while (true) {
            ArrayList arrayList = this.c;
            if (i >= arrayList.size()) {
                this.g.p(a22.g, matrix2);
                return;
            }
            if (((View) arrayList.get(i)).getVisibility() == 0) {
                y12 y12Var = (y12) this.e.get(i);
                y12Var.getClass();
                if (!matrix3.isIdentity()) {
                    e22 e22Var = y12Var.i;
                    float fB = f / e22Var.b();
                    PropertyModel.r<Point> rVar = a22.c;
                    Point point = y12Var.o;
                    y12Var.g.p(rVar, new Point(Math.round(point.x / fB), Math.round(point.y / fB)));
                    y12Var.i(e22Var.e(y12Var.j), f);
                }
                y12Var.d(f, matrix3);
            }
            i++;
        }
    }

    public final void e(float f) {
        this.l = f;
        ArrayList arrayList = this.c;
        if (arrayList == null) {
            return;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            ((y12) this.e.get(i)).e(this.l);
        }
    }

    public final void f(int i, int i2, int i3, int i4) {
        e22 e22Var = this.i;
        e22Var.c.set(i, i2, i3, i4);
        e22Var.d.set(i, i2);
        if (this.j ? true ^ e22Var.c.isEmpty() : true) {
            return;
        }
        v12 v12Var = this.p;
        u12 u12Var = v12Var.a;
        if (u12Var != null) {
            u12Var.c();
            v12Var.a = null;
        }
        u12 u12Var2 = v12Var.b;
        if (u12Var2 != null) {
            u12Var2.c();
            v12Var.b = null;
        }
    }

    public final void g(float f) {
        e22 e22Var = this.i;
        float fB = f / e22Var.b();
        e22Var.f(f);
        e22Var.g(e22Var.c() * fB, e22Var.d() * fB);
        for (int i = 0; i < this.c.size(); i++) {
            ((y12) this.e.get(i)).g(f);
        }
    }

    public final void h(Size size) {
        if (this.j) {
            int width = size.getWidth();
            int height = size.getHeight();
            e22 e22Var = this.i;
            e22Var.getClass();
            e22Var.f = new Size(Math.min(width, 2500), Math.min(height, 2500));
            e22Var.e = true;
        }
        for (int i = 0; i < this.c.size(); i++) {
            ((y12) this.e.get(i)).h(size);
        }
    }

    public final void i(Rect rect, float f) {
        Point point = this.i.d;
        int i = 0;
        while (true) {
            ArrayList arrayList = this.d;
            int size = arrayList.size();
            ArrayList arrayList2 = this.f;
            ArrayList arrayList3 = this.c;
            if (i >= size) {
                PropertyModel.r<List<Rect>> rVar = a22.f;
                PropertyModel propertyModel = this.g;
                propertyModel.p(rVar, arrayList2);
                propertyModel.p(a22.e, arrayList3);
                return;
            }
            Rect rect2 = (Rect) arrayList2.get(i);
            Rect rect3 = (Rect) arrayList.get(i);
            rect2.set((int) (rect3.left * f), (int) (rect3.top * f), (int) (rect3.right * f), (int) (rect3.bottom * f));
            Rect rect4 = this.k;
            rect4.set(rect2);
            boolean zIntersect = rect4.intersect(rect);
            ArrayList arrayList4 = this.e;
            if (zIntersect) {
                int i2 = rect4.left - rect2.left;
                int i3 = rect4.top - rect2.top;
                ((y12) arrayList4.get(i)).f(i2, i3, rect4.width() + i2, rect4.height() + i3);
                int i4 = (point.x + rect2.left) - rect.left;
                int i5 = (point.y + rect2.top) - rect.top;
                rect2.set(i4, i5, rect2.width() + i4, rect2.height() + i5);
                ((View) arrayList3.get(i)).setVisibility(0);
            } else {
                ((View) arrayList3.get(i)).setVisibility(8);
                ((y12) arrayList4.get(i)).f(0, 0, 0, 0);
                rect2.set(0, 0, 0, 0);
            }
            i++;
        }
    }

    public final void j(boolean z) {
        boolean[][] zArr;
        HashSet hashSet;
        e22 e22Var = this.i;
        float fB = e22Var.b();
        if (fB == 0.0f || e22Var.a.getWidth() == 0 || e22Var.a.getHeight() == 0) {
            return;
        }
        v12 v12Var = this.p;
        u12 u12VarA = v12Var.a(z);
        if (u12VarA.f == null && u12VarA.d != null) {
            return;
        }
        Rect rectE = e22Var.e(this.j);
        i(rectE, fB);
        boolean z2 = u12VarA == v12Var.b;
        Size size = u12VarA.b;
        if (z2) {
            PropertyModel.r<Size> rVar = a22.b;
            PropertyModel propertyModel = this.g;
            propertyModel.p(rVar, size);
            propertyModel.p(a22.c, e22Var.d);
            propertyModel.p(a22.d, rectE);
        }
        if (rectE.isEmpty() || (zArr = u12VarA.f) == null || u12VarA.d == null || zArr.length == 0 || zArr[0].length == 0) {
            return;
        }
        TraceEvent.q("PlayerFrameBitmapState.requestBitmapForRect", null);
        boolean[][] zArr2 = u12VarA.g;
        if (zArr2 != null && u12VarA.f != null) {
            TraceEvent.q("PlayerFrameBitmapState.clearBeforeRequest", null);
            for (int i = 0; i < zArr2.length; i++) {
                int i2 = 0;
                while (true) {
                    boolean[] zArr3 = zArr2[i];
                    if (i2 < zArr3.length) {
                        zArr3[i2] = false;
                        u12VarA.f[i][i2] = false;
                        i2++;
                    }
                }
            }
            TraceEvent.C("PlayerFrameBitmapState.clearBeforeRequest");
        }
        int iMax = Math.max(0, (int) Math.floor(((double) rectE.top) / ((double) size.getHeight())));
        int iMin = Math.min(u12VarA.f.length, (int) Math.ceil(((double) rectE.bottom) / ((double) size.getHeight())));
        int iMax2 = Math.max(0, (int) Math.floor(((double) rectE.left) / ((double) size.getWidth())));
        int iMin2 = Math.min(u12VarA.f[0].length, (int) Math.ceil(((double) rectE.right) / ((double) size.getWidth())));
        for (int i3 = iMax2; i3 < iMin2; i3++) {
            for (int i4 = iMax; i4 < iMin; i4++) {
                zArr2[i4][i3] = true;
                if (u12VarA.d(i4, i3) && (hashSet = u12VarA.j) != null) {
                    hashSet.add(Integer.valueOf((u12VarA.d.length * i4) + i3));
                }
            }
        }
        ve1 ve1Var = ve1.i;
        ve1Var.getClass();
        Object obj = ThreadUtils.a;
        if (ve1Var.b < 1) {
            while (iMax2 < iMin2) {
                for (int i5 = iMax; i5 < iMin; i5++) {
                    if (u12VarA.d != null) {
                        if (i5 > 0) {
                            u12VarA.d(i5 - 1, iMax2);
                        }
                        if (i5 < u12VarA.d.length - 1) {
                            u12VarA.d(i5 + 1, iMax2);
                        }
                        if (iMax2 > 0) {
                            u12VarA.d(i5, iMax2 - 1);
                        }
                        if (iMax2 < u12VarA.d[i5].length - 1) {
                            u12VarA.d(i5, iMax2 + 1);
                        }
                    }
                }
                iMax2++;
            }
        }
        if (u12VarA.e != null && u12VarA.f != null) {
            TraceEvent.q("PlayerFrameBitmapState.cancelUnrequiredPendingRequests", null);
            for (int i6 = 0; i6 < u12VarA.e.length; i6++) {
                int i7 = 0;
                while (true) {
                    u12.a[] aVarArr = u12VarA.e[i6];
                    if (i7 < aVarArr.length) {
                        u12.a aVar = aVarArr[i7];
                        if (aVar != null && !u12VarA.f[i6][i7]) {
                            TraceEvent.q("BitmapRequestHandler.cancel", null);
                            a aVar2 = u12.this.h;
                            int i8 = aVar.g;
                            long j = ((PlayerCompositorDelegateImpl) aVar2).b;
                            boolean zMNwIEnLr = j == 0 ? false : N.MNwIEnLr(j, i8);
                            TraceEvent.C("BitmapRequestHandler.cancel");
                            if (zMNwIEnLr) {
                                u12VarA.e[i6][i7] = null;
                            }
                        }
                        i7++;
                    }
                }
            }
            TraceEvent.C("PlayerFrameBitmapState.cancelUnrequiredPendingRequests");
        }
        TraceEvent.C("PlayerFrameBitmapState.requestBitmapForRect");
    }
}
