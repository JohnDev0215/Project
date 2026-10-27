package p;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Trace;
import com.google.firebase.FirebaseCommonRegistrar;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class f91 implements aw {
    public final /* synthetic */ int c;
    public final /* synthetic */ String e;
    public final /* synthetic */ Object k;

    public /* synthetic */ f91(int i, Object obj, String str) {
        this.c = i;
        this.e = str;
        this.k = obj;
    }

    @Override // p.aw
    public final Object a(jm2 jm2Var) {
        int i = this.c;
        Object obj = this.k;
        String str = this.e;
        switch (i) {
            case 0:
                Context context = (Context) jm2Var.a(Context.class);
                int i2 = ((iy2) ((g91) obj)).c;
                String strValueOf = "";
                switch (i2) {
                    case 12:
                        ApplicationInfo applicationInfo = context.getApplicationInfo();
                        if (applicationInfo != null) {
                            strValueOf = String.valueOf(applicationInfo.targetSdkVersion);
                        }
                        break;
                    case 13:
                        ApplicationInfo applicationInfo2 = context.getApplicationInfo();
                        if (applicationInfo2 != null) {
                            strValueOf = String.valueOf(applicationInfo2.minSdkVersion);
                        }
                        break;
                    case 14:
                        if (context.getPackageManager().hasSystemFeature("android.hardware.type.television")) {
                            strValueOf = "tv";
                        } else if (context.getPackageManager().hasSystemFeature("android.hardware.type.watch")) {
                            strValueOf = "watch";
                        } else if (context.getPackageManager().hasSystemFeature("android.hardware.type.automotive")) {
                            strValueOf = "auto";
                        } else if (context.getPackageManager().hasSystemFeature("android.hardware.type.embedded")) {
                            strValueOf = "embedded";
                        }
                        break;
                    default:
                        String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
                        if (installerPackageName != null) {
                            strValueOf = FirebaseCommonRegistrar.a(installerPackageName);
                        }
                        break;
                }
                return new ei(str, strValueOf);
            default:
                mv mvVar = (mv) obj;
                try {
                    Trace.beginSection(str);
                    return mvVar.f.a(jm2Var);
                } finally {
                    Trace.endSection();
                }
        }
    }
}
