package n4;

import android.content.Context;
import android.graphics.Bitmap;
import h4.InterfaceC1359a;

/* JADX INFO: loaded from: classes.dex */
public abstract class e implements e4.l {
    @Override // e4.l
    public final g4.x a(Context context, g4.x xVar, int i7, int i10) {
        if (!A4.q.i(i7, i10)) {
            throw new IllegalArgumentException("Cannot apply transformation on width: " + i7 + " or height: " + i10 + " less than or equal to zero and not Target.SIZE_ORIGINAL");
        }
        InterfaceC1359a interfaceC1359a = com.bumptech.glide.b.a(context).l;
        Bitmap bitmap = (Bitmap) xVar.get();
        if (i7 == Integer.MIN_VALUE) {
            i7 = bitmap.getWidth();
        }
        if (i10 == Integer.MIN_VALUE) {
            i10 = bitmap.getHeight();
        }
        Bitmap bitmapC = c(interfaceC1359a, bitmap, i7, i10);
        return bitmap.equals(bitmapC) ? xVar : C1958d.c(bitmapC, interfaceC1359a);
    }

    public abstract Bitmap c(InterfaceC1359a interfaceC1359a, Bitmap bitmap, int i7, int i10);
}
