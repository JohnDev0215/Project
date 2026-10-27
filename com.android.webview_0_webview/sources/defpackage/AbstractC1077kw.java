package defpackage;

import android.content.res.Resources;
import java.util.Objects;
import org.chromium.build.BuildHooksAndroidImpl;

/* JADX INFO: renamed from: kw, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1077kw {
    public static AbstractC1077kw a;

    public static AbstractC1077kw a() {
        if (a == null) {
            try {
                a = (AbstractC1077kw) Class.forName(System.currentTimeMillis() > 0 ? "org.chromium.build.BuildHooksAndroidImpl" : "").newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return a;
    }

    public static int b(Resources resources, String str, String str2, String str3) {
        Objects.requireNonNull((BuildHooksAndroidImpl) a());
        return resources.getIdentifier(str, str2, str3);
    }

    public static boolean c() {
        Objects.requireNonNull((BuildHooksAndroidImpl) a());
        return false;
    }
}
