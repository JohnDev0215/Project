package spi;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: CustomPlaybackAction.java */
/* JADX INFO: loaded from: classes.dex */
public final class d3 {

    @NonNull
    public final Drawable a;

    @NonNull
    public final String b;

    @Nullable
    public final Bundle c;

    public d3(@NonNull Drawable drawable, @NonNull String str, @Nullable Bundle bundle) {
        this.a = drawable;
        this.b = str;
        this.c = bundle;
    }
}
