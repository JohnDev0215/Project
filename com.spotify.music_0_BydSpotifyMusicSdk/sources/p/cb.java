package p;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.spotify.contexts.ApplicationAndroid;

/* JADX INFO: loaded from: classes.dex */
public final class cb implements di0 {
    public final wc1 a;
    public final int b;
    public final String c;

    public cb(Context context, wc1 wc1Var) {
        PackageInfo packageInfo;
        this.b = 0;
        this.c = "";
        this.a = wc1Var;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            ((u11) this.a).y("Error in retrieving package information.", e);
            packageInfo = null;
        }
        if (packageInfo != null) {
            this.b = packageInfo.versionCode;
            String str = packageInfo.versionName;
            this.c = str == null ? this.c : str;
        }
    }

    @Override // p.di0
    public final String a() {
        return "context_application_android";
    }

    @Override // p.di0
    public final com.google.protobuf.i getData() {
        bb bbVarG = ApplicationAndroid.g();
        bbVarG.d(this.b);
        bbVarG.e(this.c);
        return bbVarG.build();
    }
}
