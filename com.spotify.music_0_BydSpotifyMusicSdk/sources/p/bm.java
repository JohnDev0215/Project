package p;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import coil.decode.DataSource;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class bm implements qt0 {
    public final /* synthetic */ int a;
    public final br1 b;
    public final Object c;

    public /* synthetic */ bm(Object obj, br1 br1Var, int i) {
        this.a = i;
        this.c = obj;
        this.b = br1Var;
    }

    @Override // p.qt0
    public final Object a(c00 c00Var) {
        int i = this.a;
        br1 br1Var = this.b;
        Object obj = this.c;
        switch (i) {
            case 0:
                return new bb0(new BitmapDrawable(br1Var.a.getResources(), (Bitmap) obj), false, DataSource.e);
            case 1:
                try {
                    fo foVar = new fo();
                    foVar.write((ByteBuffer) obj);
                    ((ByteBuffer) obj).position(0);
                    return new fe2(ln1.g(foVar, br1Var.a), null, DataSource.e);
                } catch (Throwable th) {
                    ((ByteBuffer) obj).position(0);
                    throw th;
                }
            default:
                Drawable bitmapDrawable = (Drawable) obj;
                Bitmap.Config[] configArr = i.a;
                boolean z = (bitmapDrawable instanceof VectorDrawable) || (bitmapDrawable instanceof vs2);
                if (z) {
                    bitmapDrawable = new BitmapDrawable(br1Var.a.getResources(), ln1.d(bitmapDrawable, br1Var.b, br1Var.d, br1Var.e, br1Var.f));
                }
                return new bb0(bitmapDrawable, z, DataSource.e);
        }
    }
}
