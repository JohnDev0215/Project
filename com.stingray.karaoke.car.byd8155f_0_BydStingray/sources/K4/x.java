package k4;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import com.stingray.drivemodedetector.R;
import e2.C1208p;
import java.io.InputStream;
import m3.J;
import n4.C1958d;

/* JADX INFO: loaded from: classes.dex */
public final class x implements r, J, s4.a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Resources f18221m;

    public /* synthetic */ x(Resources resources, int i7) {
        this.l = i7;
        this.f18221m = resources;
    }

    @Override // k4.r
    public q A(w wVar) {
        switch (this.l) {
            case 0:
                return new C1684b(this.f18221m, wVar.b(Uri.class, AssetFileDescriptor.class));
            case 1:
                return new C1684b(this.f18221m, wVar.b(Uri.class, InputStream.class));
            default:
                return new C1684b(this.f18221m, C1682A.f18174b);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String a(e2.C1208p r7) {
        /*
            r6 = this;
            java.lang.String r0 = r7.d
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            java.lang.String r2 = ""
            if (r1 != 0) goto L32
            java.lang.String r1 = "und"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L13
            goto L32
        L13:
            java.util.Locale r0 = java.util.Locale.forLanguageTag(r0)
            int r1 = h2.AbstractC1343A.f15195a
            r3 = 24
            if (r1 < r3) goto L24
            java.util.Locale$Category r1 = java.util.Locale.Category.DISPLAY
            java.util.Locale r1 = java.util.Locale.getDefault(r1)
            goto L28
        L24:
            java.util.Locale r1 = java.util.Locale.getDefault()
        L28:
            java.lang.String r0 = r0.getDisplayName(r1)
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 == 0) goto L34
        L32:
            r0 = r2
            goto L55
        L34:
            r3 = 1
            r4 = 0
            int r3 = r0.offsetByCodePoints(r4, r3)     // Catch: java.lang.IndexOutOfBoundsException -> L55
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.IndexOutOfBoundsException -> L55
            r5.<init>()     // Catch: java.lang.IndexOutOfBoundsException -> L55
            java.lang.String r4 = r0.substring(r4, r3)     // Catch: java.lang.IndexOutOfBoundsException -> L55
            java.lang.String r1 = r4.toUpperCase(r1)     // Catch: java.lang.IndexOutOfBoundsException -> L55
            r5.append(r1)     // Catch: java.lang.IndexOutOfBoundsException -> L55
            java.lang.String r1 = r0.substring(r3)     // Catch: java.lang.IndexOutOfBoundsException -> L55
            r5.append(r1)     // Catch: java.lang.IndexOutOfBoundsException -> L55
            java.lang.String r0 = r5.toString()     // Catch: java.lang.IndexOutOfBoundsException -> L55
        L55:
            java.lang.String r1 = r6.b(r7)
            java.lang.String[] r0 = new java.lang.String[]{r0, r1}
            java.lang.String r6 = r6.d(r0)
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 == 0) goto L72
            java.lang.String r6 = r7.f14307b
            boolean r7 = android.text.TextUtils.isEmpty(r6)
            if (r7 == 0) goto L70
            goto L71
        L70:
            r2 = r6
        L71:
            r6 = r2
        L72:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: k4.x.a(e2.p):java.lang.String");
    }

    public String b(C1208p c1208p) {
        int i7 = c1208p.f14309f & 2;
        Resources resources = this.f18221m;
        String string = i7 != 0 ? resources.getString(R.string.exo_track_role_alternate) : "";
        int i10 = c1208p.f14309f;
        if ((i10 & 4) != 0) {
            string = d(string, resources.getString(R.string.exo_track_role_supplementary));
        }
        if ((i10 & 8) != 0) {
            string = d(string, resources.getString(R.string.exo_track_role_commentary));
        }
        return (i10 & 1088) != 0 ? d(string, resources.getString(R.string.exo_track_role_closed_captions)) : string;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String c(e2.C1208p r15) {
        /*
            Method dump skipped, instruction units count: 311
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: k4.x.c(e2.p):java.lang.String");
    }

    public String d(String... strArr) {
        String string = "";
        for (String str : strArr) {
            if (str.length() > 0) {
                string = TextUtils.isEmpty(string) ? str : this.f18221m.getString(R.string.exo_item_list, string, str);
            }
        }
        return string;
    }

    @Override // s4.a
    public g4.x f(g4.x xVar, e4.h hVar) {
        if (xVar == null) {
            return null;
        }
        return new C1958d(this.f18221m, xVar);
    }

    public x(Resources resources) {
        this.l = 3;
        resources.getClass();
        this.f18221m = resources;
    }
}
