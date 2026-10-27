package n0;

import android.graphics.Bitmap;

/* JADX INFO: renamed from: n0.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1877f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Bitmap f19431a;

    public C1877f(Bitmap bitmap) {
        this.f19431a = bitmap;
    }

    public final int a() {
        Bitmap.Config config = this.f19431a.getConfig();
        if (config == Bitmap.Config.ALPHA_8) {
            return 1;
        }
        if (config == Bitmap.Config.RGB_565) {
            return 2;
        }
        if (config != Bitmap.Config.ARGB_4444) {
            if (config == Bitmap.Config.RGBA_F16) {
                return 3;
            }
            if (config == Bitmap.Config.HARDWARE) {
                return 4;
            }
        }
        return 0;
    }
}
