package defpackage;

import org.chromium.support_lib_boundary.WebSettingsBoundaryInterface;

/* JADX INFO: renamed from: Dc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0056Dc {
    public static String a(int i) {
        switch (i) {
            case -1:
                return "SUCCESS_CACHE";
            case WebSettingsBoundaryInterface.ForceDarkBehavior.FORCE_DARK_ONLY /* 0 */:
                return "SUCCESS";
            case 1:
            case 9:
            case 11:
            case 12:
            default:
                StringBuilder sb = new StringBuilder(32);
                sb.append("unknown status code: ");
                sb.append(i);
                return sb.toString();
            case 2:
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            case 3:
                return "SERVICE_DISABLED";
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                return "SIGN_IN_REQUIRED";
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                return "INVALID_ACCOUNT";
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                return "RESOLUTION_REQUIRED";
            case 7:
                return "NETWORK_ERROR";
            case 8:
                return "INTERNAL_ERROR";
            case 10:
                return "DEVELOPER_ERROR";
            case 13:
                return "ERROR";
            case 14:
                return "INTERRUPTED";
            case 15:
                return "TIMEOUT";
            case 16:
                return "CANCELED";
            case 17:
                return "API_NOT_CONNECTED";
            case 18:
                return "DEAD_CLIENT";
        }
    }
}
