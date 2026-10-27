package k4;

import android.content.Context;
import android.net.Uri;
import f4.C1256a;
import f4.C1257b;
import z4.C2768d;

/* JADX INFO: loaded from: classes.dex */
public final class m implements q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18201a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f18202b;

    public m(Context context, int i7) {
        this.f18201a = i7;
        switch (i7) {
            case 1:
                this.f18202b = context.getApplicationContext();
                break;
            case 2:
                this.f18202b = context.getApplicationContext();
                break;
            default:
                this.f18202b = context;
                break;
        }
    }

    @Override // k4.q
    public final p a(Object obj, int i7, int i10, e4.h hVar) {
        Long l;
        switch (this.f18201a) {
            case 0:
                Uri uri = (Uri) obj;
                return new p(new C2768d(uri), new l(this.f18202b, 0, uri));
            case 1:
                Uri uri2 = (Uri) obj;
                if (i7 == Integer.MIN_VALUE || i10 == Integer.MIN_VALUE || i7 > 512 || i10 > 384) {
                    return null;
                }
                C2768d c2768d = new C2768d(uri2);
                Context context = this.f18202b;
                return new p(c2768d, C1257b.c(context, uri2, new C1256a(context.getContentResolver(), 0)));
            default:
                Uri uri3 = (Uri) obj;
                if (i7 == Integer.MIN_VALUE || i10 == Integer.MIN_VALUE || i7 > 512 || i10 > 384 || (l = (Long) hVar.c(n4.D.d)) == null || l.longValue() != -1) {
                    return null;
                }
                C2768d c2768d2 = new C2768d(uri3);
                Context context2 = this.f18202b;
                return new p(c2768d2, C1257b.c(context2, uri3, new C1256a(context2.getContentResolver(), 1)));
        }
    }

    @Override // k4.q
    public final boolean b(Object obj) {
        switch (this.f18201a) {
            case 0:
                return com.bumptech.glide.d.F((Uri) obj);
            case 1:
                Uri uri = (Uri) obj;
                return com.bumptech.glide.d.F(uri) && !uri.getPathSegments().contains("video");
            default:
                Uri uri2 = (Uri) obj;
                return com.bumptech.glide.d.F(uri2) && uri2.getPathSegments().contains("video");
        }
    }
}
