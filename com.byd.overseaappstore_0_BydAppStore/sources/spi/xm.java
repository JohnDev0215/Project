package spi;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class xm implements bn<Uri, Bitmap> {
    public final dn a;
    public final w1 b;

    public xm(dn dnVar, w1 w1Var) {
        this.a = dnVar;
        this.b = w1Var;
    }

    @Override // spi.bn
    public final boolean a(@NonNull Uri uri, @NonNull lk lkVar) throws IOException {
        return "android.resource".equals(uri.getScheme());
    }

    @Override // spi.bn
    @Nullable
    public final wm<Bitmap> b(@NonNull Uri uri, int i, int i2, @NonNull lk lkVar) throws IOException {
        wm<Drawable> wmVarB = this.a.b(uri, i, i2, lkVar);
        if (wmVarB == null) {
            return null;
        }
        return v7.a(this.b, (Drawable) ((u7) wmVarB).get(), i, i2);
    }
}
