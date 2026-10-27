package defpackage;

import android.content.Context;
import defpackage.no2;

/* JADX INFO: compiled from: SkinBuildInLoader.java */
/* JADX INFO: loaded from: classes3.dex */
public final class eo2 implements no2.b {
    @Override // no2.b
    public final String a(int i, Context context, String str) {
        return context.getResources().getResourceEntryName(i) + "_" + str;
    }

    @Override // no2.b
    public final String b(Context context, String str) {
        po2.d().g(context.getResources(), context.getPackageName(), str, this);
        return str;
    }

    @Override // no2.b
    public final int getType() {
        return 1;
    }

    @Override // no2.b
    public final void c() {
    }

    @Override // no2.b
    public final void d() {
    }

    @Override // no2.b
    public final void e() {
    }
}
