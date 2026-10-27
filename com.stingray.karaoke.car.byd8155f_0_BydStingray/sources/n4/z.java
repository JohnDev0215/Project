package n4;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class z implements g4.x {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f19974m;

    public /* synthetic */ z(Object obj, int i7) {
        this.l = i7;
        this.f19974m = obj;
    }

    private final void a() {
    }

    private final void c() {
    }

    private final void f() {
    }

    @Override // g4.x
    public final int b() {
        switch (this.l) {
            case 0:
                return A4.q.c((Bitmap) this.f19974m);
            case 1:
                return ((byte[]) this.f19974m).length;
            case 2:
                AnimatedImageDrawable animatedImageDrawable = (AnimatedImageDrawable) this.f19974m;
                return A4.q.d(Bitmap.Config.ARGB_8888) * animatedImageDrawable.getIntrinsicHeight() * animatedImageDrawable.getIntrinsicWidth() * 2;
            default:
                return 1;
        }
    }

    @Override // g4.x
    public final Class d() {
        switch (this.l) {
            case 0:
                return Bitmap.class;
            case 1:
                return byte[].class;
            case 2:
                return Drawable.class;
            default:
                return ((File) this.f19974m).getClass();
        }
    }

    @Override // g4.x
    public final void e() {
        switch (this.l) {
            case 2:
                AnimatedImageDrawable animatedImageDrawable = (AnimatedImageDrawable) this.f19974m;
                animatedImageDrawable.stop();
                animatedImageDrawable.clearAnimationCallbacks();
                break;
        }
    }

    @Override // g4.x
    public final Object get() {
        switch (this.l) {
            case 0:
                return (Bitmap) this.f19974m;
            case 1:
                return (byte[]) this.f19974m;
            case 2:
                return (AnimatedImageDrawable) this.f19974m;
            default:
                return (File) this.f19974m;
        }
    }

    public z(byte[] bArr) {
        this.l = 1;
        A4.h.c(bArr, "Argument must not be null");
        this.f19974m = bArr;
    }

    public z(File file) {
        this.l = 3;
        A4.h.c(file, "Argument must not be null");
        this.f19974m = file;
    }
}
