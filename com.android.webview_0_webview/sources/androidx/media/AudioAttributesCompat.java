package androidx.media;

import android.util.SparseIntArray;
import defpackage.C0571bs;
import defpackage.X4;
import defpackage.Y7;
import defpackage.Z7;
import org.chromium.support_lib_boundary.WebSettingsBoundaryInterface;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AudioAttributesCompat implements Y7 {
    public static final SparseIntArray b;
    public X4 a;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        b = sparseIntArray;
        sparseIntArray.put(5, 1);
        b.put(6, 2);
        b.put(7, 2);
        b.put(8, 1);
        b.put(9, 1);
        b.put(10, 1);
    }

    public static int a(boolean z, int i, int i2) {
        if ((i & 1) == 1) {
            return z ? 1 : 7;
        }
        if ((i & 4) == 4) {
            return z ? 0 : 6;
        }
        switch (i2) {
            case WebSettingsBoundaryInterface.ForceDarkBehavior.FORCE_DARK_ONLY /* 0 */:
                return z ? Integer.MIN_VALUE : 3;
            case 1:
            case 12:
            case 14:
            case 16:
                return 3;
            case 2:
                return 0;
            case 3:
                return z ? 0 : 8;
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                return 4;
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
            case 7:
            case 8:
            case 9:
            case 10:
                return 5;
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                return 2;
            case 11:
                return 10;
            case 13:
                return 1;
            case 15:
            default:
                if (!z) {
                    return 3;
                }
                throw new IllegalArgumentException("Unknown usage value " + i2 + " in audio attributes");
        }
    }

    public static String b(int i) {
        switch (i) {
            case WebSettingsBoundaryInterface.ForceDarkBehavior.FORCE_DARK_ONLY /* 0 */:
                return "USAGE_UNKNOWN";
            case 1:
                return "USAGE_MEDIA";
            case 2:
                return "USAGE_VOICE_COMMUNICATION";
            case 3:
                return "USAGE_VOICE_COMMUNICATION_SIGNALLING";
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                return "USAGE_ALARM";
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                return "USAGE_NOTIFICATION";
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                return "USAGE_NOTIFICATION_RINGTONE";
            case 7:
                return "USAGE_NOTIFICATION_COMMUNICATION_REQUEST";
            case 8:
                return "USAGE_NOTIFICATION_COMMUNICATION_INSTANT";
            case 9:
                return "USAGE_NOTIFICATION_COMMUNICATION_DELAYED";
            case 10:
                return "USAGE_NOTIFICATION_EVENT";
            case 11:
                return "USAGE_ASSISTANCE_ACCESSIBILITY";
            case 12:
                return "USAGE_ASSISTANCE_NAVIGATION_GUIDANCE";
            case 13:
                return "USAGE_ASSISTANCE_SONIFICATION";
            case 14:
                return "USAGE_GAME";
            case 15:
            default:
                return Z7.a("unknown usage ", i);
            case 16:
                return "USAGE_ASSISTANT";
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesCompat)) {
            return false;
        }
        AudioAttributesCompat audioAttributesCompat = (AudioAttributesCompat) obj;
        X4 x4 = this.a;
        return x4 == null ? audioAttributesCompat.a == null : x4.equals(audioAttributesCompat.a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return this.a.toString();
    }
}
