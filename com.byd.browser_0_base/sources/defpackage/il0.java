package defpackage;

import J.N;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: FeedPositionUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class il0 {
    public static boolean a() {
        sk skVar = bq.a;
        String strMMltG$kc = N.MMltG$kc("FeedPositionAndroid", "feed_active_targeting");
        if (strMMltG$kc == null) {
            return true;
        }
        int iD = SharedPreferencesManager.getInstance().d(2, "Chrome.Segmentation.FeedActiveUser");
        oj2.U(iD, 3, "NewTabPage.FeedPositionSegmentationResult");
        return !strMMltG$kc.equals("active") ? !strMMltG$kc.equals("non-active") || iD == 2 : iD == 1;
    }

    public static boolean b() {
        sk skVar = bq.a;
        return N.M6bsIDpc("FeedPositionAndroid", "pull_up_feed", false) && a();
    }

    public static boolean c() {
        sk skVar = bq.a;
        return N.M6bsIDpc("FeedPositionAndroid", "push_down_feed_large", false) && a();
    }

    public static boolean d() {
        sk skVar = bq.a;
        return N.M6bsIDpc("FeedPositionAndroid", "push_down_feed_small", false) && a();
    }
}
