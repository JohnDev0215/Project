package androidx.appcompat.content.res;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.ResourceManagerInternal;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
public final class AppCompatResources {
    private AppCompatResources() {
    }

    public static Drawable a(Context context, int i) {
        return ResourceManagerInternal.b().e(context, i);
    }
}
