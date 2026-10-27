package defpackage;

import android.content.Context;
import defpackage.no2;

/* JADX INFO: compiled from: SkinPrefixBuildInLoader.java */
/* JADX INFO: loaded from: classes3.dex */
public final class gp2 implements no2.b {
    @Override // no2.b
    public final String a(int i, Context context, String str) {
        StringBuilder sbK = C0173a.k(str, "_");
        sbK.append(context.getResources().getResourceEntryName(i));
        return sbK.toString();
    }

    @Override // no2.b
    public final String b(Context context, String str) {
        po2.d().g(context.getResources(), context.getPackageName(), str, this);
        return str;
    }

    @Override // no2.b
    public final int getType() {
        return 2;
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
