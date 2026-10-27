package E3;

import B3.o;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import java.nio.ByteBuffer;
import q7.InterfaceC2189c;
import r9.C2319i;
import w3.AbstractC2598a;

/* JADX INFO: loaded from: classes.dex */
public final class c implements g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1724a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final K3.m f1725b;
    public final Object c;

    public /* synthetic */ c(Object obj, K3.m mVar, int i7) {
        this.f1724a = i7;
        this.c = obj;
        this.f1725b = mVar;
    }

    @Override // E3.g
    public final Object a(InterfaceC2189c interfaceC2189c) {
        K3.m mVar = this.f1725b;
        Object obj = this.c;
        switch (this.f1724a) {
            case 0:
                return new d(new BitmapDrawable(mVar.f3565a.getResources(), (Bitmap) obj), false, B3.f.f624m);
            case 1:
                ByteBuffer byteBuffer = (ByteBuffer) obj;
                try {
                    C2319i c2319i = new C2319i();
                    c2319i.write(byteBuffer);
                    byteBuffer.position(0);
                    Context context = mVar.f3565a;
                    return new m(new o(c2319i, null), null, B3.f.f624m);
                } catch (Throwable th) {
                    byteBuffer.position(0);
                    throw th;
                }
            default:
                Drawable bitmapDrawable = (Drawable) obj;
                Bitmap.Config config = N3.d.f4575a;
                boolean z6 = (bitmapDrawable instanceof VectorDrawable) || (bitmapDrawable instanceof AbstractC2598a);
                if (z6) {
                    bitmapDrawable = new BitmapDrawable(mVar.f3565a.getResources(), l9.l.G(bitmapDrawable, mVar.f3566b, mVar.d, mVar.f3567e, mVar.f3568f));
                }
                return new d(bitmapDrawable, z6, B3.f.f624m);
        }
    }
}
