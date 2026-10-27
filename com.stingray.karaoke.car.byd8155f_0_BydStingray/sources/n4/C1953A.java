package n4;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import java.io.File;
import p4.C2103b;

/* JADX INFO: renamed from: n4.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1953A implements e4.j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f19924a;

    public /* synthetic */ C1953A(int i7) {
        this.f19924a = i7;
    }

    @Override // e4.j
    public final g4.x a(Object obj, int i7, int i10, e4.h hVar) {
        switch (this.f19924a) {
            case 0:
                return new z((Bitmap) obj, 0);
            case 1:
                Drawable drawable = (Drawable) obj;
                if (drawable != null) {
                    return new C2103b(drawable, 0);
                }
                return null;
            default:
                return new z((File) obj);
        }
    }

    @Override // e4.j
    public final /* bridge */ /* synthetic */ boolean b(Object obj, e4.h hVar) {
        switch (this.f19924a) {
            case 0:
                break;
            case 1:
                break;
            default:
                break;
        }
        return true;
    }
}
