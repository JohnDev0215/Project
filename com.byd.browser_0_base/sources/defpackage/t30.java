package defpackage;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.widget.RemoteViews;
import androidx.browser.customtabs.CustomTabsSessionToken;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* JADX INFO: compiled from: CustomTabIntentDataProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class t30 extends oj {
    public static final sh N = new sh("CCTAutoTranslate", "allow_all_first_parties", false);
    public static final nu2 O = new nu2("CCTAutoTranslate", "package_names_allowlist", "com.google.android.googlequicksearchbox");
    public static final nu2 P = new nu2("CCTResizableForThirdParties", "default_policy", "use-denylist");
    public static final nu2 Q = new nu2("CCTResizableForThirdParties", "denylist_entries", "");
    public static final nu2 R = new nu2("CCTResizableForThirdParties", "allowlist_entries", "");
    public final PendingIntent A;
    public final PendingIntent B;
    public final boolean C;
    public final String D;
    public final String E;
    public final int F;
    public final int[] G;
    public final i30 H;
    public final int I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final int f97J;
    public final int K;
    public final int L;
    public final boolean M;
    public final Intent a;
    public final CustomTabsSessionToken b;
    public final boolean c;
    public final Intent d;
    public final Bundle e;
    public final int f;
    public final int g;
    public final String h;
    public final boolean i;
    public final boolean j;
    public final boolean k;
    public final int l;
    public final ArrayList m;
    public final zf3 n;
    public String o;
    public final boolean p;
    public final boolean q;
    public ArrayList r;
    public final za3 s;
    public boolean u;
    public final RemoteViews x;
    public final int y;
    public final int[] z;
    public final ArrayList t = new ArrayList();
    public final ArrayList v = new ArrayList(1);
    public final ArrayList w = new ArrayList(2);

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Can't wrap try/catch for region: R(56:0|2|(1:4)|5|(1:9)|(3:11|(4:14|(1:380)(5:378|17|(1:19)(1:20)|21|381)|379|12)|377)|22|(4:25|(3:384|27|390)(3:383|28|(3:386|30|389)(3:385|31|388))|387|23)|382|32|(3:34|(1:39)(1:38)|(1:41))|(1:43)(1:44)|45|(3:48|(4:51|(1:394)(2:55|392)|56|49)|391)|57|(2:59|(1:61)(2:62|(1:64)(1:65)))(2:66|(2:68|(1:70)(1:71))(4:72|(1:77)(1:76)|78|(1:80)(1:81)))|82|(1:84)(1:85)|86|(1:94)(4:375|89|(1:91)(2:92|93)|374)|95|(2:372|96)|99|(1:101)|102|(1:104)(1:105)|(1:107)(1:108)|109|(28:111|(1:113)|116|(25:118|(1:120)|123|SW:124|135|370|136|139|(3:141|(8:149|(1:154)(1:153)|155|(1:160)(1:159)|(1:165)(1:164)|(1:170)(1:169)|(1:176)(1:175)|(0)(3:179|(4:(1:182)(1:183)|(1:185)(1:186)|(1:189)|190)(1:191)|192))(1:193)|194)(0)|195|(1:198)|199|(2:202|(1:204))|205|(1:208)|209|(1:217)(2:211|(1:213)(2:214|(1:216)(0)))|218|(1:220)(1:221)|222|(1:224)(1:225)|226|(1:230)|231|(1:396)(80:234|(1:236)|237|(1:239)|240|(1:242)|(1:244)|245|(1:247)|248|(1:250)|251|(1:253)|254|(1:256)|257|(1:259)|(1:261)|(1:263)|(1:265)|(1:267)|268|(1:272)|273|(1:275)|276|(1:278)|279|(1:281)|(1:283)|(1:285)|(1:287)|288|(1:290)|291|(1:293)|294|(1:296)|297|(1:301)|302|(1:304)|305|(1:308)|(1:310)|(1:312)|(1:314)|(1:316)|(1:318)|(1:320)|321|(1:323)|324|(1:326)|327|(1:329)|330|(1:332)|(1:334)|335|(1:337)|(1:339)|(1:341)|342|(1:344)|345|(1:347)|348|(1:350)|351|(1:353)|(1:355)|356|(1:358)|359|(1:361)|362|(1:364)|365|(2:367|368)(1:395)))(1:121)|122|123|SW:124|135|370|136|139|(0)(0)|195|(0)|199|(0)|205|(0)|209|(0)(0)|218|(0)(0)|222|(0)(0)|226|(2:228|230)(0)|231|(0)(0))(1:114)|115|116|(0)(0)|122|123|SW:124|135|370|136|139|(0)(0)|195|(0)|199|(0)|205|(0)|209|(0)(0)|218|(0)(0)|222|(0)(0)|226|(0)(0)|231|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x0320, code lost:
    
        android.util.Log.e("cr_IntentUtils", "getIntArrayExtra failed on intent " + r36);
        r4 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:118:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x030a  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0310  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x03e2  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x03f2  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0401  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0418  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0437  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x045e  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x046e  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0470  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x047f  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0481  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0491  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0494  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x04a1  */
    /* JADX WARN: Removed duplicated region for block: B:396:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public t30(int r34, android.content.Context r35, android.content.Intent r36) {
        /*
            Method dump skipped, instruction units count: 1678
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.t30.<init>(int, android.content.Context, android.content.Intent):void");
    }

    public static String e0(Intent intent, CustomTabsSessionToken customTabsSessionToken) {
        String strD = CustomTabsConnection.e().d(customTabsSessionToken);
        if (!TextUtils.isEmpty(strD)) {
            return strD;
        }
        String strR = q11.r(intent, "org.chromium.chrome.browser.calling_activity_package");
        if (TextUtils.isEmpty(strR)) {
            return null;
        }
        return strR;
    }

    public static boolean f0(String str) {
        if (str == null) {
            return false;
        }
        String strC = P.c();
        if (strC.equals("use-allowlist")) {
            return g0(str, R.c());
        }
        if (strC.equals("use-denylist")) {
            return !g0(str, Q.c());
        }
        return false;
    }

    public static boolean g0(String str, String str2) {
        if (str != null && !TextUtils.isEmpty(str2)) {
            for (String str3 : str2.split("\\|")) {
                if (str.equals(str3)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean h0(Intent intent, CustomTabsSessionToken customTabsSessionToken) {
        if (q11.g(intent)) {
            return true;
        }
        String strE0 = e0(intent, customTabsSessionToken);
        CustomTabsConnection.e().getClass();
        if (strE0 == null) {
            return false;
        }
        zi0.b.b();
        return false;
    }

    public static void i0(int i) {
        oj2.U(i, 5, "CustomTabs.ShareOptionLocation");
    }

    @Override // defpackage.oj
    public final bk2 A() {
        Bundle bundle = (Bundle) q11.n(this.a, "androidx.browser.trusted.extra.SHARE_DATA");
        if (bundle == null) {
            return null;
        }
        try {
            return new bk2(bundle.getString("androidx.browser.trusted.sharing.KEY_TITLE"), bundle.getString("androidx.browser.trusted.sharing.KEY_TEXT"), bundle.getParcelableArrayList("androidx.browser.trusted.sharing.KEY_URIS"));
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // defpackage.oj
    public final gl2 B() {
        Bundle bundleJ = q11.j(this.a, "androidx.browser.trusted.extra.SHARE_TARGET");
        if (bundleJ == null) {
            return null;
        }
        try {
            return gl2.a(bundleJ);
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // defpackage.oj
    public final int C() {
        int iL = q11.l(this.a, "androidx.browser.customtabs.extra.EXTRA_ACTIVITY_SIDE_SHEET_POSITION", 0);
        if (iL == 0) {
            return 2;
        }
        return iL;
    }

    @Override // defpackage.oj
    public final int D() {
        int iL = q11.l(this.a, "androidx.browser.customtabs.extra.EXTRA_ACTIVITY_SIDE_SHEET_SLIDE_IN_BEHAVIOR", 0);
        if (iL == 0) {
            return 2;
        }
        return iL;
    }

    @Override // defpackage.oj
    public final int E() {
        return this.g;
    }

    @Override // defpackage.oj
    public final String F() {
        return W() ? this.E : this.D;
    }

    @Override // defpackage.oj
    public final List<String> G() {
        return this.m;
    }

    @Override // defpackage.oj
    public final int H() {
        int intExtra = this.a.getIntExtra("androidx.browser.trusted.extra.DISCLOSURE_VERSION", -1);
        if (intExtra == 0 || intExtra == 1) {
            return intExtra;
        }
        return -1;
    }

    @Override // defpackage.oj
    public final zf3 I() {
        return this.n;
    }

    @Override // defpackage.oj
    public final int J() {
        return this.f;
    }

    @Override // defpackage.oj
    public final String K() {
        if (this.o == null) {
            String strO = b.o(this.a);
            if (J() == 1) {
                String str = this.h;
                if (!TextUtils.isEmpty(str) && "file".equals(Uri.parse(str).getScheme())) {
                    strO = str;
                }
            }
            this.o = strO;
        }
        return this.o;
    }

    @Override // defpackage.oj
    public final boolean N() {
        return this.C;
    }

    @Override // defpackage.oj
    public final boolean O() {
        if (Q()) {
            return true;
        }
        if (bq.o.a()) {
            if (s() > 0) {
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.oj
    public final boolean P() {
        return this.M;
    }

    @Override // defpackage.oj
    public final boolean Q() {
        return r() > 0;
    }

    @Override // defpackage.oj
    @Deprecated
    public final boolean R() {
        return this.c;
    }

    @Override // defpackage.oj
    public final boolean V() {
        return (this.e == null || j() == null) ? false : true;
    }

    @Override // defpackage.oj
    public final boolean W() {
        if (this.E != null) {
            return !bq.e.a() ? false : (!this.c || !N.c()) ? g0(j(), O.c()) : true;
        }
        return false;
    }

    @Override // defpackage.oj
    public final boolean X() {
        return this.i;
    }

    @Override // defpackage.oj
    public final boolean Y() {
        return this.p;
    }

    @Override // defpackage.oj
    public final boolean Z() {
        return !this.k;
    }

    @Override // defpackage.oj
    public final boolean a() {
        return this.q;
    }

    @Override // defpackage.oj
    public final boolean a0() {
        return this.u;
    }

    @Override // defpackage.oj
    public final int b() {
        return this.I;
    }

    @Override // defpackage.oj
    public final boolean b0() {
        return !this.j;
    }

    @Override // defpackage.oj
    public final int c() {
        return this.y;
    }

    @Override // defpackage.oj
    public final boolean c0() {
        return (J() == 1) && ((ArrayList) v()).isEmpty();
    }

    @Override // defpackage.oj
    public final int d() {
        return this.l;
    }

    @Override // defpackage.oj
    public final boolean d0() {
        return q11.i(this.a, "androix.browser.customtabs.extra.EXTRA_ACTIVITY_SIDE_SHEET_ENABLE_MAXIMIZATION", false);
    }

    @Override // defpackage.oj
    public final List<m20> e() {
        return this.r;
    }

    @Override // defpackage.oj
    public final int f() {
        if (V()) {
            return this.e.getInt("android:activity.animEnterRes");
        }
        return 0;
    }

    @Override // defpackage.oj
    public final int g() {
        if (!V() || Q()) {
            return 0;
        }
        return this.e.getInt("android:activity.animExitRes");
    }

    @Override // defpackage.oj
    public final RemoteViews h() {
        return this.x;
    }

    @Override // defpackage.oj
    public final int[] i() {
        int[] iArr = this.z;
        if (iArr == null) {
            return null;
        }
        return (int[]) iArr.clone();
    }

    @Override // defpackage.oj
    public final String j() {
        return e0(this.a, this.b);
    }

    @Override // defpackage.oj
    public final Drawable k() {
        return this.s;
    }

    @Override // defpackage.oj
    public final int l() {
        if (bq.r.a()) {
            return q11.l(this.a, "androidx.browser.customtabs.extra.CLOSE_BUTTON_POSITION", 0);
        }
        return 0;
    }

    @Override // defpackage.oj
    public final cv m() {
        return this.H;
    }

    @Override // defpackage.oj
    public final List<m20> n() {
        return this.w;
    }

    @Override // defpackage.oj
    public final List<m20> o() {
        return this.v;
    }

    @Override // defpackage.oj
    public final int p() {
        return this.F;
    }

    @Override // defpackage.oj
    public final int[] q() {
        return this.G;
    }

    @Override // defpackage.oj
    public final int r() {
        boolean z = bq.n.a() && f0(j());
        if (this.c || z) {
            return this.f97J;
        }
        return 0;
    }

    @Override // defpackage.oj
    public final int s() {
        if (!bq.o.a()) {
            return 0;
        }
        boolean z = bq.p.a() && f0(j());
        if (this.c || z) {
            return this.K;
        }
        return 0;
    }

    @Override // defpackage.oj
    public final Intent t() {
        return this.a;
    }

    @Override // defpackage.oj
    public final Intent u() {
        return this.d;
    }

    @Override // defpackage.oj
    public final List<String> v() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.t.iterator();
        while (it.hasNext()) {
            arrayList.add((String) ((Pair) it.next()).first);
        }
        return arrayList;
    }

    @Override // defpackage.oj
    public final int w() {
        return this.L;
    }

    @Override // defpackage.oj
    public final PendingIntent x() {
        return this.A;
    }

    @Override // defpackage.oj
    public final PendingIntent y() {
        return this.B;
    }

    @Override // defpackage.oj
    public final CustomTabsSessionToken z() {
        return this.b;
    }
}
