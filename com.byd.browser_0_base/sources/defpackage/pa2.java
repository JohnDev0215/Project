package defpackage;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;

/* JADX INFO: compiled from: RemoteViewsWithNightModeInflater.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pa2 extends ContextWrapper {
    public final Context a;

    public pa2(Context context, Context context2) {
        super(context);
        this.a = context2;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final String getPackageName() {
        return this.a.getPackageName();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Resources getResources() {
        return this.a.getResources();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Resources.Theme getTheme() {
        return this.a.getTheme();
    }
}
