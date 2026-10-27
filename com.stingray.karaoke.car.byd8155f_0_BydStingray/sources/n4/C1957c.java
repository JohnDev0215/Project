package n4;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.util.Log;
import d4.C1071d;
import h4.InterfaceC1359a;
import java.io.IOException;

/* JADX INFO: renamed from: n4.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1957c implements e4.j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f19934a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC1359a f19935b;

    public C1957c() {
        this.f19934a = 0;
        this.f19935b = new Y4.e(22);
    }

    @Override // e4.j
    public final g4.x a(Object obj, int i7, int i10, e4.h hVar) {
        switch (this.f19934a) {
            case 0:
                return c((ImageDecoder.Source) obj, i7, i10, hVar);
            default:
                return C1958d.c(((C1071d) obj).b(), this.f19935b);
        }
    }

    @Override // e4.j
    public final /* bridge */ /* synthetic */ boolean b(Object obj, e4.h hVar) {
        switch (this.f19934a) {
            case 0:
                break;
            default:
                break;
        }
        return true;
    }

    public C1958d c(ImageDecoder.Source source, int i7, int i10, e4.h hVar) throws IOException {
        Bitmap bitmapDecodeBitmap = ImageDecoder.decodeBitmap(source, new m4.b(i7, i10, hVar));
        if (Log.isLoggable("BitmapImageDecoder", 2)) {
            Log.v("BitmapImageDecoder", "Decoded [" + bitmapDecodeBitmap.getWidth() + "x" + bitmapDecodeBitmap.getHeight() + "] for [" + i7 + "x" + i10 + "]");
        }
        return new C1958d(bitmapDecodeBitmap, (Y4.e) this.f19935b);
    }

    public C1957c(InterfaceC1359a interfaceC1359a) {
        this.f19934a = 1;
        this.f19935b = interfaceC1359a;
    }
}
