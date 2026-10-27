package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.view.ContextThemeWrapper;

/* JADX INFO: compiled from: NightModeUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pn1 {
    public static ContextThemeWrapper a(int i, Context context, boolean z) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
        Configuration configuration = new Configuration();
        configuration.fontScale = 0.0f;
        configuration.uiMode = (z ? 32 : 16) | (configuration.uiMode & (-49));
        contextThemeWrapper.applyOverrideConfiguration(configuration);
        return contextThemeWrapper;
    }
}
