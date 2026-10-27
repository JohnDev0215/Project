package O0;

import android.text.StaticLayout;

/* JADX INFO: loaded from: classes.dex */
public abstract class r {
    public static final boolean a(StaticLayout staticLayout) {
        return staticLayout.isFallbackLineSpacingEnabled();
    }

    public static final void b(StaticLayout.Builder builder, int i7, int i10) {
        builder.setLineBreakConfig(G1.c.f().setLineBreakStyle(i7).setLineBreakWordStyle(i10).build());
    }
}
