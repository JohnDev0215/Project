package defpackage;

import android.app.Activity;
import java.util.ArrayList;
import org.chromium.chrome.browser.feedback.FamilyInfoFeedbackSource;
import org.chromium.chrome.browser.feedback.ProcessIdFeedbackSource;
import org.chromium.chrome.browser.feedback.c;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ChromeFeedbackCollector.java */
/* JADX INFO: loaded from: classes.dex */
public final class cq extends em0<a> {

    /* JADX INFO: compiled from: ChromeFeedbackCollector.java */
    public static class a {
        public final Profile a;
        public final String b;
        public final String c;

        public a(String str, String str2, Profile profile) {
            this.a = profile;
            this.b = str;
            this.c = str2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public cq(android.app.Activity r3, org.chromium.chrome.browser.feedback.ScreenshotTask r4, cq.a r5, org.chromium.base.Callback r6, org.chromium.chrome.browser.profiles.Profile r7) {
        /*
            Method dump skipped, instruction units count: 233
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cq.<init>(android.app.Activity, org.chromium.chrome.browser.feedback.ScreenshotTask, cq$a, org.chromium.base.Callback, org.chromium.chrome.browser.profiles.Profile):void");
    }

    public final ArrayList b(Object obj) {
        ArrayList arrayList = new ArrayList();
        Profile profile = ((a) obj).a;
        arrayList.add(new c(profile));
        arrayList.add(new ey2());
        arrayList.add(new ProcessIdFeedbackSource());
        if (!profile.h()) {
            arrayList.add(new FamilyInfoFeedbackSource(profile));
        }
        return arrayList;
    }

    public final ArrayList c(Activity activity, Object obj) {
        a aVar = (a) obj;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new i90());
        String str = aVar.b;
        arrayList.add(new fm0(str, 1));
        Profile profile = aVar.a;
        arrayList.add(new zk3(profile));
        arrayList.add(new pu0(profile));
        arrayList.add(new fm0());
        arrayList.add(new dw0());
        arrayList.add(new c02());
        arrayList.add(new fm0(aVar.c, 0));
        arrayList.add(new x9(profile, activity, new GURL(str)));
        return arrayList;
    }
}
