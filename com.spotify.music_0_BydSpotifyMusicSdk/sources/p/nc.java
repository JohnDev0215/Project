package p;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import java.io.File;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class nc implements pt0 {
    public final /* synthetic */ int a;

    public /* synthetic */ nc(int i) {
        this.a = i;
    }

    @Override // p.pt0
    public final qt0 a(Object obj, br1 br1Var, l31 l31Var) {
        switch (this.a) {
            case 0:
                return b((Uri) obj, br1Var);
            case 1:
                return new bm((Bitmap) obj, br1Var, 0);
            case 2:
                return new bm((ByteBuffer) obj, br1Var, 1);
            case 3:
                return b((Uri) obj, br1Var);
            case 4:
                return new bm((Drawable) obj, br1Var, 2);
            case 5:
                return new bu0((File) obj);
            default:
                return b((Uri) obj, br1Var);
        }
    }

    public final qt0 b(Uri uri, br1 br1Var) {
        switch (this.a) {
            case 0:
                if (i.e(uri)) {
                    return new oc(uri, br1Var, 0);
                }
                return null;
            case 3:
                if (v03.b(uri.getScheme(), "content")) {
                    return new oc(uri, br1Var, 1);
                }
                return null;
            default:
                if (v03.b(uri.getScheme(), "android.resource")) {
                    return new j42(uri, br1Var);
                }
                return null;
        }
    }
}
