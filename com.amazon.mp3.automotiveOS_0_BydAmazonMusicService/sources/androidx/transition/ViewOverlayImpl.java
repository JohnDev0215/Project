package androidx.transition;

import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes2.dex */
interface ViewOverlayImpl {
    void add(@NonNull Drawable drawable);

    void clear();

    void remove(@NonNull Drawable drawable);
}
