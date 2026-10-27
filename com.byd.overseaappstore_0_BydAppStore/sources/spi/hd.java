package spi;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class hd implements bn<bd, Bitmap> {
    public final w1 a;

    public hd(w1 w1Var) {
        this.a = w1Var;
    }

    @Override // spi.bn
    public final /* bridge */ /* synthetic */ boolean a(@NonNull bd bdVar, @NonNull lk lkVar) throws IOException {
        return true;
    }

    @Override // spi.bn
    public final wm<Bitmap> b(@NonNull bd bdVar, int i, int i2, @NonNull lk lkVar) throws IOException {
        return y1.b(bdVar.a(), this.a);
    }
}
