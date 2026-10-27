package defpackage;

import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileManager;

/* JADX INFO: compiled from: FeatureNotificationUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class vk0 {
    public static String a(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "IPH_FeatureNotificationGuideIncognitoTabHelpBubble" : "IPH_FeatureNotificationGuideVoiceSearchHelpBubble" : "IPH_FeatureNotificationGuideNTPSuggestionCardHelpBubble" : "IPH_FeatureNotificationGuideIncognitoTabHelpBubble" : "IPH_FeatureNotificationGuideSignInHelpBubble" : "IPH_FeatureNotificationGuideDefaultBrowserPromo";
    }

    public static void b(int i, Runnable runnable) {
        if (ProfileManager.b) {
            oj2.t(Profile.d()).d(a(i), runnable);
        }
    }

    public static void c(int i) {
        if (ProfileManager.b) {
            oj2.t(Profile.d()).a(a(i));
        }
    }
}
