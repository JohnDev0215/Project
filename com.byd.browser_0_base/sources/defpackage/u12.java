package defpackage;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Size;
import java.lang.reflect.Array;
import java.util.HashSet;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;
import org.chromium.base.UnguessableToken;
import org.chromium.components.paintpreview.player.PlayerCompositorDelegateImpl;

/* JADX INFO: compiled from: PlayerFrameBitmapState.java */
/* JADX INFO: loaded from: classes3.dex */
public final class u12 {
    public final UnguessableToken a;
    public final Size b;
    public final float c;
    public Bitmap[][] d;
    public a[][] e;
    public boolean[][] f;
    public final boolean[][] g;
    public final org.chromium.components.paintpreview.player.a h;
    public final v12 i;
    public HashSet j = new HashSet();

    /* JADX INFO: compiled from: PlayerFrameBitmapState.java */
    public class a implements Callback<Bitmap> {
        public final int e;
        public final int f;
        public int g;

        public a(int i, int i2) {
            this.e = i;
            this.f = i2;
        }

        @Override // org.chromium.base.Callback
        public final void onResult(Bitmap bitmap) {
            a[][] aVarArr;
            boolean[][] zArr;
            Bitmap bitmap2 = bitmap;
            TraceEvent.q("BitmapRequestHandler.onResult", null);
            int i = this.e;
            int i2 = this.f;
            u12 u12Var = u12.this;
            if (bitmap2 == null) {
                u12.b(u12Var, i, i2);
                a[][] aVarArr2 = u12Var.e;
                if (aVarArr2 != null) {
                    aVarArr2[i][i2] = null;
                }
                TraceEvent.C("BitmapRequestHandler.onResult");
                return;
            }
            Bitmap[][] bitmapArr = u12Var.d;
            if (bitmapArr != null && (aVarArr = u12Var.e) != null && (zArr = u12Var.f) != null && aVarArr[i][i2] != null && zArr[i][i2]) {
                bitmapArr[i][i2] = bitmap2;
                u12.a(u12Var);
                u12.b(u12Var, i, i2);
                u12Var.e[i][i2] = null;
                TraceEvent.C("BitmapRequestHandler.onResult");
                return;
            }
            bitmap2.recycle();
            u12.a(u12Var);
            u12.b(u12Var, i, i2);
            a[][] aVarArr3 = u12Var.e;
            if (aVarArr3 != null) {
                aVarArr3[i][i2] = null;
            }
            TraceEvent.C("BitmapRequestHandler.onResult");
        }
    }

    public u12(UnguessableToken unguessableToken, int i, int i2, float f, Size size, org.chromium.components.paintpreview.player.a aVar, v12 v12Var) {
        this.a = unguessableToken;
        this.b = new Size(i, i2);
        this.c = f;
        this.h = aVar;
        this.i = v12Var;
        int iMax = (int) Math.max(1.0d, Math.ceil((size.getHeight() * f) / i2));
        int iMax2 = (int) Math.max(1.0d, Math.ceil((size.getWidth() * f) / i));
        this.d = (Bitmap[][]) Array.newInstance((Class<?>) Bitmap.class, iMax, iMax2);
        this.e = (a[][]) Array.newInstance((Class<?>) a.class, iMax, iMax2);
        Class cls = Boolean.TYPE;
        this.f = (boolean[][]) Array.newInstance((Class<?>) cls, iMax, iMax2);
        this.g = (boolean[][]) Array.newInstance((Class<?>) cls, iMax, iMax2);
    }

    public static void a(u12 u12Var) {
        if (u12Var.d == null || u12Var.f == null) {
            return;
        }
        TraceEvent.q("PlayerFrameBitmapState.deleteUnrequiredBitmaps", null);
        for (int i = 0; i < u12Var.d.length; i++) {
            int i2 = 0;
            while (true) {
                Bitmap[] bitmapArr = u12Var.d[i];
                if (i2 < bitmapArr.length) {
                    Bitmap bitmap = bitmapArr[i2];
                    if (!u12Var.f[i][i2] && bitmap != null) {
                        bitmap.recycle();
                        u12Var.d[i][i2] = null;
                    }
                    i2++;
                }
            }
        }
        TraceEvent.C("PlayerFrameBitmapState.deleteUnrequiredBitmaps");
    }

    public static void b(u12 u12Var, int i, int i2) {
        Bitmap[][] bitmapArr = u12Var.d;
        if (bitmapArr == null) {
            return;
        }
        HashSet hashSet = u12Var.j;
        if (hashSet != null) {
            hashSet.remove(Integer.valueOf((i * bitmapArr.length) + i2));
            if (!u12Var.j.isEmpty()) {
                return;
            } else {
                u12Var.j = null;
            }
        }
        u12Var.i.b(u12Var);
    }

    public final void c() {
        this.f = null;
        this.e = null;
        for (int i = 0; i < this.d.length; i++) {
            int i2 = 0;
            while (true) {
                Bitmap[] bitmapArr = this.d[i];
                if (i2 < bitmapArr.length) {
                    Bitmap bitmap = bitmapArr[i2];
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                    i2++;
                }
            }
        }
        this.d = null;
    }

    public final boolean d(int i, int i2) {
        boolean[][] zArr = this.f;
        if (zArr == null) {
            return false;
        }
        zArr[i][i2] = true;
        a[][] aVarArr = this.e;
        boolean[][] zArr2 = this.g;
        if (aVarArr != null && aVarArr[i][i2] != null) {
            boolean z = zArr2[i][i2];
            return false;
        }
        Bitmap[][] bitmapArr = this.d;
        if (bitmapArr == null || aVarArr == null || bitmapArr[i][i2] != null || aVarArr[i][i2] != null) {
            return false;
        }
        Size size = this.b;
        int height = size.getHeight() * i;
        int width = size.getWidth() * i2;
        boolean z2 = zArr2[i][i2];
        a aVar = new a(i, i2);
        this.e[i][i2] = aVar;
        int iA = ((PlayerCompositorDelegateImpl) this.h).a(this.a, new Rect(width, height, size.getWidth() + width, size.getHeight() + height), this.c, aVar, new ve3(22, aVar));
        a aVar2 = this.e[i][i2];
        if (aVar2 != null) {
            aVar2.g = iA;
        }
        return true;
    }
}
