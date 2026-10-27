package android.graphics;

import android.graphics.PorterDuff;

/* JADX INFO: loaded from: classes.dex */
public class PorterDuffXfermode extends Xfermode {
    public PorterDuffXfermode(PorterDuff.Mode mode) {
        this.porterDuffMode = mode.nativeInt;
    }
}
