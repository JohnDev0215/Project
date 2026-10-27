package defpackage;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: compiled from: SkinPreference.java */
/* JADX INFO: loaded from: classes3.dex */
public final class fp2 {
    public static fp2 c;
    public final SharedPreferences a;
    public final SharedPreferences.Editor b;

    public fp2(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("meta-data", 0);
        this.a = sharedPreferences;
        this.b = sharedPreferences.edit();
    }
}
