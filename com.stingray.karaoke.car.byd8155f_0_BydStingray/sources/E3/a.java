package E3;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import java.io.File;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class a implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1721a;

    public /* synthetic */ a(int i7) {
        this.f1721a = i7;
    }

    @Override // E3.f
    public final g a(Object obj, K3.m mVar) {
        switch (this.f1721a) {
            case 0:
                Uri uri = (Uri) obj;
                if (N3.d.c(uri)) {
                    return new b(uri, mVar, 0);
                }
                return null;
            case 1:
                return new c((Bitmap) obj, mVar, 0);
            case 2:
                return new c((ByteBuffer) obj, mVar, 1);
            case 3:
                Uri uri2 = (Uri) obj;
                if (A7.m.a(uri2.getScheme(), "content")) {
                    return new b(uri2, mVar, 1);
                }
                return null;
            case 4:
                return new c((Drawable) obj, mVar, 2);
            case 5:
                return new h((File) obj);
            default:
                Uri uri3 = (Uri) obj;
                if (A7.m.a(uri3.getScheme(), "android.resource")) {
                    return new b(uri3, mVar, 2);
                }
                return null;
        }
    }
}
