package androidx.core.net;

import android.content.IntentFilter;
import android.net.Uri;
import android.provider.SettingsStringUtil;
import android.telecom.PhoneAccount;
import com.android.internal.accessibility.common.ShortcutConstants;
import com.android.internal.transition.EpicenterTranslateClipReveal;

/* JADX INFO: loaded from: classes3.dex */
public final class UriCompat {
    private UriCompat() {
    }

    public static String toSafeString(Uri uri) {
        String scheme = uri.getScheme();
        String schemeSpecificPart = uri.getSchemeSpecificPart();
        if (scheme != null) {
            if (scheme.equalsIgnoreCase(PhoneAccount.SCHEME_TEL) || scheme.equalsIgnoreCase("sip") || scheme.equalsIgnoreCase("sms") || scheme.equalsIgnoreCase("smsto") || scheme.equalsIgnoreCase("mailto") || scheme.equalsIgnoreCase("nfc")) {
                StringBuilder sb = new StringBuilder(64);
                sb.append(scheme);
                sb.append(ShortcutConstants.SERVICES_SEPARATOR);
                if (schemeSpecificPart != null) {
                    for (int i = 0; i < schemeSpecificPart.length(); i++) {
                        char cCharAt = schemeSpecificPart.charAt(i);
                        if (cCharAt == '-' || cCharAt == '@' || cCharAt == '.') {
                            sb.append(cCharAt);
                        } else {
                            sb.append(EpicenterTranslateClipReveal.StateProperty.TARGET_X);
                        }
                    }
                }
                return sb.toString();
            }
            if (scheme.equalsIgnoreCase(IntentFilter.SCHEME_HTTP) || scheme.equalsIgnoreCase(IntentFilter.SCHEME_HTTPS) || scheme.equalsIgnoreCase("ftp") || scheme.equalsIgnoreCase("rtsp")) {
                schemeSpecificPart = "//" + (uri.getHost() != null ? uri.getHost() : "") + (uri.getPort() != -1 ? SettingsStringUtil.DELIMITER + uri.getPort() : "") + "/...";
            }
        }
        StringBuilder sb2 = new StringBuilder(64);
        if (scheme != null) {
            sb2.append(scheme);
            sb2.append(ShortcutConstants.SERVICES_SEPARATOR);
        }
        if (schemeSpecificPart != null) {
            sb2.append(schemeSpecificPart);
        }
        return sb2.toString();
    }
}
