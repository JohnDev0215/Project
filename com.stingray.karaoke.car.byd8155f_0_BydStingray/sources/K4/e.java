package k4;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class e implements com.bumptech.glide.load.data.e {
    public final Resources.Theme l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Resources f18187m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f18188n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f18189p;

    public e(Resources.Theme theme, Resources resources, P6.a aVar, int i7) {
        this.l = theme;
        this.f18187m = resources;
        this.f18188n = aVar;
        this.o = i7;
    }

    @Override // com.bumptech.glide.load.data.e
    public final Class a() {
        switch (((P6.a) this.f18188n).l) {
            case 4:
                return AssetFileDescriptor.class;
            case 5:
                return Drawable.class;
            default:
                return InputStream.class;
        }
    }

    @Override // com.bumptech.glide.load.data.e
    public final void b() {
        Object obj = this.f18189p;
        if (obj != null) {
            try {
                switch (((P6.a) this.f18188n).l) {
                    case 4:
                        ((AssetFileDescriptor) obj).close();
                        break;
                    case 5:
                        break;
                    default:
                        ((InputStream) obj).close();
                        break;
                }
            } catch (IOException unused) {
            }
        }
    }

    @Override // com.bumptech.glide.load.data.e
    public final void cancel() {
    }

    @Override // com.bumptech.glide.load.data.e
    public final int e() {
        return 1;
    }

    @Override // com.bumptech.glide.load.data.e
    public final void f(com.bumptech.glide.f fVar, com.bumptech.glide.load.data.d dVar) {
        Object objOpenRawResourceFd;
        try {
            Object obj = this.f18188n;
            Resources.Theme theme = this.l;
            Resources resources = this.f18187m;
            int i7 = this.o;
            P6.a aVar = (P6.a) obj;
            switch (aVar.l) {
                case 4:
                    objOpenRawResourceFd = resources.openRawResourceFd(i7);
                    break;
                case 5:
                    Context context = aVar.f5412m;
                    objOpenRawResourceFd = n7.z.h(context, context, i7, theme);
                    break;
                default:
                    objOpenRawResourceFd = resources.openRawResource(i7);
                    break;
            }
            this.f18189p = objOpenRawResourceFd;
            dVar.d(objOpenRawResourceFd);
        } catch (Resources.NotFoundException e10) {
            dVar.c(e10);
        }
    }
}
