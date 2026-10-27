package com.google.android.gms.cast;

import android.annotation.TargetApi;
import android.app.Presentation;
import android.content.Context;
import android.view.Display;
import android.view.Window;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@TargetApi(19)
@Deprecated
public abstract class CastPresentation extends Presentation {
    public CastPresentation(@NonNull Context context, @NonNull Display display) {
        super(context, display);
        zza();
    }

    private final void zza() {
        Window window = getWindow();
        if (window != null) {
            window.setType(2030);
            window.addFlags(268435456);
            window.addFlags(16777216);
            window.addFlags(1024);
        }
    }

    public CastPresentation(@NonNull Context context, @NonNull Display display, int i) {
        super(context, display, i);
        zza();
    }
}
