package G4;

import A9.k;
import H4.e;
import H4.h;
import H4.j;
import H4.m;
import a.AbstractC0904a;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import m7.i;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class b extends Drawable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f2667a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f2668b;
    public final E4.b c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final E4.b f2669e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final List f2670f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final List f2671g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final e f2672h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final m f2673i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Bitmap f2674j;

    /* JADX WARN: Removed duplicated region for block: B:116:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x074a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:404:0x07f3  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x0813  */
    /* JADX WARN: Removed duplicated region for block: B:449:0x0870  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:462:0x088f  */
    /* JADX WARN: Removed duplicated region for block: B:647:0x089e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public b(D4.d r44, G4.c r45, java.nio.charset.Charset r46) throws I7.a {
        /*
            Method dump skipped, instruction units count: 3015
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: G4.b.<init>(D4.d, G4.c, java.nio.charset.Charset):void");
    }

    public static boolean f(b bVar, int i7, int i10) {
        int i11;
        int i12;
        ArrayList<i> arrayList;
        c cVar = bVar.f2667a;
        boolean z6 = cVar.f2679g.f1189b instanceof D4.b;
        int i13 = bVar.d;
        if (!z6 && ((arrayList = bVar.f2668b) == null || !arrayList.isEmpty())) {
            for (i iVar : arrayList) {
                int iIntValue = ((Number) iVar.l).intValue() - 2;
                int iIntValue2 = ((Number) iVar.l).intValue() + 3;
                int i14 = i7 - i13;
                if (iIntValue <= i14 && i14 < iIntValue2) {
                    Number number = (Number) iVar.f19259m;
                    int iIntValue3 = number.intValue() - 2;
                    int iIntValue4 = number.intValue() + 3;
                    int i15 = i10 - i13;
                    if (iIntValue3 <= i15 && i15 < iIntValue4) {
                        return true;
                    }
                }
            }
        }
        int i16 = i7 - i13;
        if (-1 <= i16 && i16 < 8 && -1 <= (i12 = i10 - i13) && i12 < 8) {
            return true;
        }
        E4.b bVar2 = bVar.f2669e;
        if (-1 <= i16 && i16 < 8) {
            int i17 = bVar2.f1748b;
            int i18 = i17 - 8;
            int i19 = i17 + 1;
            int i20 = i10 + i13;
            if (i18 <= i20 && i20 < i19) {
                return true;
            }
        }
        int i21 = bVar2.f1748b;
        int i22 = i21 - 8;
        int i23 = i21 + 1;
        int i24 = i7 + i13;
        if (i22 <= i24 && i24 < i23 && -1 <= (i11 = i10 - i13) && i11 < 8) {
            return true;
        }
        cVar.getClass();
        return false;
    }

    public final Paint a() {
        c cVar = this.f2667a;
        AbstractC0904a.K(cVar.d.f2808b);
        AbstractC0904a.K(cVar.f2678f.c);
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setAlpha(C7.a.W(AbstractC2673a.u(cVar.f2679g.d, 0.0f, 1.0f) * 255));
        return paint;
    }

    public final void b(Canvas canvas, float f10, Path path, Paint paint) {
        Path path2;
        int i7 = -1;
        for (i iVar : this.f2670f) {
            if (this.f2667a.c.f2823e) {
                i7++;
                path2 = new Path(path);
                float f11 = i7 != 0 ? i7 != 1 ? i7 != 2 ? 180.0f : 90.0f : -90.0f : 0.0f;
                float f12 = (3 * f10) / 2;
                Matrix matrix = new Matrix();
                matrix.setRotate(f11, f12, f12);
                path2.transform(matrix);
            } else {
                path2 = path;
            }
            float fFloatValue = ((Number) iVar.l).floatValue() * f10;
            float fFloatValue2 = ((Number) iVar.f19259m).floatValue() * f10;
            int iSave = canvas.save();
            canvas.translate(fFloatValue, fFloatValue2);
            try {
                canvas.drawPath(path2, paint);
            } finally {
                canvas.restoreToCount(iSave);
            }
        }
    }

    public final void c(Canvas canvas, float f10, Path path, Paint paint) {
        Path path2;
        int i7 = -1;
        for (i iVar : this.f2671g) {
            float fFloatValue = ((Number) iVar.l).floatValue() * f10;
            float fFloatValue2 = ((Number) iVar.f19259m).floatValue() * f10;
            int iSave = canvas.save();
            canvas.translate(fFloatValue, fFloatValue2);
            try {
                if (this.f2667a.c.f2823e) {
                    i7++;
                    path2 = new Path(path);
                    float f11 = i7 != 0 ? i7 != 1 ? i7 != 2 ? 180.0f : 90.0f : -90.0f : 0.0f;
                    float f12 = (7 * f10) / 2;
                    Matrix matrix = new Matrix();
                    matrix.setRotate(f11, f12, f12);
                    path2.transform(matrix);
                } else {
                    path2 = path;
                }
                canvas.drawPath(path2, paint);
                canvas.restoreToCount(iSave);
            } catch (Throwable th) {
                canvas.restoreToCount(iSave);
                throw th;
            }
        }
    }

    public final void d(Canvas canvas, float f10) {
        D4.c cVar = this.f2667a.f2679g.f1188a;
        if (cVar.equals(D4.b.f1181a)) {
            return;
        }
        if (!cVar.equals(D4.a.f1180a)) {
            throw new k();
        }
        Path pathA = H4.c.f2797b.a(9 * f10, F4.c.f2412i);
        Paint paintA = a();
        for (i iVar : this.f2671g) {
            int iSave = canvas.save();
            canvas.translate((((Number) iVar.l).intValue() - 1) * f10, (((Number) iVar.f19259m).intValue() - 1) * f10);
            try {
                canvas.drawPath(pathA, paintA);
            } finally {
                canvas.restoreToCount(iSave);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        A7.m.f(canvas, "canvas");
        Bitmap bitmap = this.f2674j;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        }
    }

    public final void e(Canvas canvas, float f10) {
        c cVar = this.f2667a;
        D4.c cVar2 = cVar.f2679g.f1189b;
        if (cVar2.equals(D4.b.f1181a)) {
            return;
        }
        if (!cVar2.equals(D4.a.f1180a)) {
            throw new k();
        }
        a aVar = a.f2666a;
        H4.c cVar3 = H4.c.f2797b;
        F4.c cVar4 = F4.c.f2412i;
        Path pathA = aVar.a(5 * f10, cVar4);
        Path pathA2 = cVar3.a(f10, cVar4);
        Paint paintA = a();
        Path pathA3 = cVar3.a(3 * f10, cVar4);
        Matrix matrix = new Matrix();
        matrix.setTranslate(f10, f10);
        pathA3.transform(matrix);
        Path path = new Path(pathA);
        path.op(pathA3, Path.Op.UNION);
        Iterator it = this.f2668b.iterator();
        while (it.hasNext()) {
            int iIntValue = ((Number) ((i) it.next()).l).intValue();
            int i7 = this.d;
            float f11 = ((iIntValue + i7) - 2) * f10;
            float fIntValue = ((((Number) r6.f19259m).intValue() + i7) - 2) * f10;
            int iSave = canvas.save();
            canvas.translate(f11, fIntValue);
            try {
                canvas.drawPath(path, paintA);
                j jVar = cVar.d;
                H4.i iVar = jVar.d;
                if (!(iVar instanceof h)) {
                    canvas.drawPath(pathA, iVar.a());
                }
                float f12 = 2 * f10;
                iSave = canvas.save();
                canvas.translate(f12, f12);
                try {
                    if (!(jVar.d instanceof h)) {
                        canvas.drawPath(pathA2, jVar.c.a());
                    }
                    canvas.restoreToCount(iSave);
                } finally {
                    canvas.restoreToCount(iSave);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i7) {
    }

    @Override // android.graphics.drawable.Drawable
    public final void setBounds(Rect rect) {
        A7.m.f(rect, "bounds");
        setBounds(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0375 A[PHI: r5
  0x0375: PHI (r5v19 F4.c) = (r5v18 F4.c), (r5v27 F4.c), (r5v28 F4.c) binds: [B:141:0x0390, B:138:0x0383, B:136:0x0366] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0392  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0116 A[PHI: r19
  0x0116: PHI (r19v14 int) = (r19v12 int), (r19v15 int) binds: [B:20:0x0114, B:14:0x00e5] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setBounds(int r56, int r57, int r58, int r59) {
        /*
            Method dump skipped, instruction units count: 1811
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: G4.b.setBounds(int, int, int, int):void");
    }
}
