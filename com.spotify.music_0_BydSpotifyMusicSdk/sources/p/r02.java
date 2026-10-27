package p;

import android.app.Application;
import android.content.Context;
import java.io.File;
import java.util.HashSet;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class r02 {
    public final HashSet a;
    public final yn b;
    public final z90 c;

    public r02() {
        yn ynVar = new yn(13);
        z90 z90Var = new z90(12);
        this.a = new HashSet();
        this.b = ynVar;
        this.c = z90Var;
    }

    public static void d(String str, Object... objArr) {
        String.format(Locale.US, str, objArr);
    }

    public final File a(Context context, String str, String str2) {
        this.b.getClass();
        String strN = yn.n(str);
        if (str2 == null || str2.length() == 0) {
            return new File(context.getDir("lib", 0), strN);
        }
        return new File(context.getDir("lib", 0), strN + "." + str2);
    }

    public final void b(Application application, String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Given library is either null or empty");
        }
        d("Beginning load of %s...", str);
        c(application, str, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x0126, code lost:
    
        ((java.util.zip.ZipFile) r2).close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(android.content.Context r20, java.lang.String r21, java.lang.String r22) {
        /*
            Method dump skipped, instruction units count: 448
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.r02.c(android.content.Context, java.lang.String, java.lang.String):void");
    }
}
