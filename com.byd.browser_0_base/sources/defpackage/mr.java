package defpackage;

import android.os.Handler;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: ChromeSurveyController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class mr {
    public y13 a;
    public Handler b;
    public Tab c;
    public final String d;
    public final String e;
    public final q2 f;
    public final vg1 g;

    /* JADX INFO: compiled from: ChromeSurveyController.java */
    public static class a extends i9<Boolean> {
        public final mr h;
        public final y13 i;

        public a(mr mrVar, a23 a23Var) {
            this.h = mrVar;
            this.i = a23Var;
        }

        /* JADX WARN: Removed duplicated region for block: B:36:0x00ad  */
        @Override // defpackage.i9
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Boolean b() {
            /*
                r8 = this;
                boolean r0 = defpackage.mr.a()
                if (r0 != 0) goto La
                java.lang.Boolean r8 = java.lang.Boolean.FALSE
                goto Lb2
            La:
                iv r0 = defpackage.iv.e()
                java.lang.String r1 = "force-enable-chrome-survey"
                boolean r0 = r0.g(r1)
                mr r8 = r8.h
                if (r0 == 0) goto L23
                r8.getClass()
                r8 = 2
                defpackage.mr.b(r8)
                java.lang.Boolean r8 = java.lang.Boolean.TRUE
                goto Lb2
            L23:
                r8.getClass()
                org.chromium.chrome.browser.preferences.SharedPreferencesManager r0 = org.chromium.chrome.browser.preferences.SharedPreferencesManager.getInstance()
                java.lang.String r1 = r8.d
                r2 = -1
                long r0 = r0.e(r2, r1)
                int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                r1 = 1
                r2 = 0
                if (r0 == 0) goto L3d
                defpackage.mr.b(r2)
                r0 = r1
                goto L3e
            L3d:
                r0 = r2
            L3e:
                if (r0 != 0) goto Lad
                sk r0 = defpackage.bq.a
                java.lang.String r0 = "max-download-attempts"
                java.lang.String r3 = "ChromeSurveyNextAndroid"
                int r0 = J.N.M37SqSAy(r3, r0, r2)
                org.chromium.chrome.browser.preferences.SharedPreferencesManager r4 = org.chromium.chrome.browser.preferences.SharedPreferencesManager.getInstance()
                java.lang.String r8 = r8.e
                int r8 = r4.d(r2, r8)
                if (r0 <= 0) goto L5b
                if (r8 >= r0) goto L59
                goto L5b
            L59:
                r8 = r2
                goto L5c
            L5b:
                r8 = r1
            L5c:
                if (r8 == 0) goto Lad
                boolean r8 = defpackage.z7.p
                if (r8 == 0) goto L68
                r8 = 8
                defpackage.mr.b(r8)
                goto La9
            L68:
                org.chromium.chrome.browser.preferences.SharedPreferencesManager r8 = org.chromium.chrome.browser.preferences.SharedPreferencesManager.getInstance()
                r0 = -1
                java.lang.String r4 = "last_rolled_for_chrome_survey_key"
                int r5 = r8.d(r0, r4)
                java.lang.Object r6 = org.chromium.base.ThreadUtils.a
                java.util.Calendar r6 = java.util.Calendar.getInstance()
                r7 = 6
                int r6 = r6.get(r7)
                if (r5 != r6) goto L85
                r8 = 3
                defpackage.mr.b(r8)
                goto La9
            L85:
                java.lang.String r5 = "max-number"
                int r3 = J.N.M37SqSAy(r3, r5, r0)
                if (r3 != r0) goto L92
                r8 = 4
                defpackage.mr.b(r8)
                goto La9
            L92:
                r8.m(r6, r4)
                java.util.Random r8 = new java.util.Random
                r8.<init>()
                int r8 = r8.nextInt(r3)
                if (r8 != 0) goto La5
                defpackage.mr.b(r7)
                r8 = r1
                goto Laa
            La5:
                r8 = 5
                defpackage.mr.b(r8)
            La9:
                r8 = r2
            Laa:
                if (r8 == 0) goto Lad
                goto Lae
            Lad:
                r1 = r2
            Lae:
                java.lang.Boolean r8 = java.lang.Boolean.valueOf(r1)
            Lb2:
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: mr.a.b():java.lang.Object");
        }

        @Override // defpackage.i9
        public final void l(Boolean bool) {
            if (bool.booleanValue()) {
                mr mrVar = this.h;
                mrVar.getClass();
                mrVar.b = new Handler();
                mrVar.a = this.i;
                n3.a0().getClass();
                SharedPreferencesManager.getInstance().c(mrVar.e);
            }
        }
    }

    public mr(String str, r2 r2Var, ChromeTabbedActivity chromeTabbedActivity, vg1 vg1Var) {
        this.d = ar.b.b(str);
        this.e = ar.a.b(str);
        this.f = r2Var;
        this.g = vg1Var;
    }

    public static boolean a() {
        return v42.g().a();
    }

    public static void b(int i) {
        oj2.U(i, 9, "Android.Survey.SurveyFilteringResults");
    }
}
