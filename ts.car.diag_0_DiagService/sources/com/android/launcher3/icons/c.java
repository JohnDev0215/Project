package com.android.launcher3.icons;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Bitmap f1300b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Bitmap f1301a;

    /* JADX INFO: compiled from: caradapter-bug */
    public interface a {
        default c a(Bitmap bitmap, int i, b bVar) {
            return c.b(bitmap, i);
        }
    }

    static {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
        f1300b = bitmapCreateBitmap;
        a(bitmapCreateBitmap);
    }

    public c(Bitmap bitmap, int i) {
        this.f1301a = bitmap;
    }

    public static c a(@NonNull Bitmap bitmap) {
        return b(bitmap, 0);
    }

    public static c b(@NonNull Bitmap bitmap, int i) {
        return new c(bitmap, i);
    }
}
