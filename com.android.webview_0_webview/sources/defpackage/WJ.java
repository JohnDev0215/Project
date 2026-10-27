package defpackage;

import org.chromium.support_lib_boundary.WebSettingsBoundaryInterface;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class WJ extends RuntimeException {
    public final int s;

    public WJ(int i) {
        this.s = i;
    }

    @Override // java.lang.Throwable
    public String toString() {
        String str;
        StringBuilder sbF = Z7.f("MojoResult(");
        sbF.append(this.s);
        sbF.append("): ");
        switch (this.s) {
            case WebSettingsBoundaryInterface.ForceDarkBehavior.FORCE_DARK_ONLY /* 0 */:
                str = "OK";
                break;
            case 1:
                str = "CANCELLED";
                break;
            case 2:
            default:
                str = "UNKNOWN";
                break;
            case 3:
                str = "INVALID_ARGUMENT";
                break;
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                str = "DEADLINE_EXCEEDED";
                break;
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                str = "NOT_FOUND";
                break;
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                str = "ALREADY_EXISTS";
                break;
            case 7:
                str = "PERMISSION_DENIED";
                break;
            case 8:
                str = "RESOURCE_EXHAUSTED";
                break;
            case 9:
                str = "FAILED_PRECONDITION";
                break;
            case 10:
                str = "ABORTED";
                break;
            case 11:
                str = "OUT_OF_RANGE";
                break;
            case 12:
                str = "UNIMPLEMENTED";
                break;
            case 13:
                str = "INTERNAL";
                break;
            case 14:
                str = "UNAVAILABLE";
                break;
            case 15:
                str = "DATA_LOSS";
                break;
            case 16:
                str = "BUSY";
                break;
            case 17:
                str = "SHOULD_WAIT";
                break;
        }
        sbF.append(str);
        return sbF.toString();
    }
}
