package androidx.media;

import android.hardware.bydauto.ac.tsimpl.a;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class AudioAttributesImplBase implements AudioAttributesImpl {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f12016a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f12017b = 0;
    public int c = 0;
    public int d = -1;

    public final boolean equals(Object obj) {
        int i7;
        if (!(obj instanceof AudioAttributesImplBase)) {
            return false;
        }
        AudioAttributesImplBase audioAttributesImplBase = (AudioAttributesImplBase) obj;
        if (this.f12017b == audioAttributesImplBase.f12017b) {
            int i10 = this.c;
            int i11 = audioAttributesImplBase.c;
            int i12 = audioAttributesImplBase.d;
            if (i12 == -1) {
                int i13 = audioAttributesImplBase.f12016a;
                int i14 = AudioAttributesCompat.f12012b;
                if ((i11 & 1) != 1) {
                    if ((i11 & 4) != 4) {
                        switch (i13) {
                            case 2:
                                i7 = 0;
                                break;
                            case 3:
                                i7 = 8;
                                break;
                            case 4:
                                i7 = 4;
                                break;
                            case 5:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                                i7 = 5;
                                break;
                            case 6:
                                i7 = 2;
                                break;
                            case 11:
                                i7 = 10;
                                break;
                            case 12:
                            default:
                                i7 = 3;
                                break;
                            case 13:
                                i7 = 1;
                                break;
                        }
                    } else {
                        i7 = 6;
                    }
                } else {
                    i7 = 7;
                }
            } else {
                i7 = i12;
            }
            if (i7 == 6) {
                i11 |= 4;
            } else if (i7 == 7) {
                i11 |= 1;
            }
            if (i10 == (i11 & 273) && this.f12016a == audioAttributesImplBase.f12016a && this.d == i12) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f12017b), Integer.valueOf(this.c), Integer.valueOf(this.f12016a), Integer.valueOf(this.d)});
    }

    public final String toString() {
        String strE;
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (this.d != -1) {
            sb.append(" stream=");
            sb.append(this.d);
            sb.append(" derived");
        }
        sb.append(" usage=");
        int i7 = this.f12016a;
        int i10 = AudioAttributesCompat.f12012b;
        switch (i7) {
            case 0:
                strE = "USAGE_UNKNOWN";
                break;
            case 1:
                strE = "USAGE_MEDIA";
                break;
            case 2:
                strE = "USAGE_VOICE_COMMUNICATION";
                break;
            case 3:
                strE = "USAGE_VOICE_COMMUNICATION_SIGNALLING";
                break;
            case 4:
                strE = "USAGE_ALARM";
                break;
            case 5:
                strE = "USAGE_NOTIFICATION";
                break;
            case 6:
                strE = "USAGE_NOTIFICATION_RINGTONE";
                break;
            case 7:
                strE = "USAGE_NOTIFICATION_COMMUNICATION_REQUEST";
                break;
            case 8:
                strE = "USAGE_NOTIFICATION_COMMUNICATION_INSTANT";
                break;
            case 9:
                strE = "USAGE_NOTIFICATION_COMMUNICATION_DELAYED";
                break;
            case 10:
                strE = "USAGE_NOTIFICATION_EVENT";
                break;
            case 11:
                strE = "USAGE_ASSISTANCE_ACCESSIBILITY";
                break;
            case 12:
                strE = "USAGE_ASSISTANCE_NAVIGATION_GUIDANCE";
                break;
            case 13:
                strE = "USAGE_ASSISTANCE_SONIFICATION";
                break;
            case 14:
                strE = "USAGE_GAME";
                break;
            case 15:
            default:
                strE = a.e(i7, "unknown usage ");
                break;
            case 16:
                strE = "USAGE_ASSISTANT";
                break;
        }
        sb.append(strE);
        sb.append(" content=");
        sb.append(this.f12017b);
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.c).toUpperCase());
        return sb.toString();
    }
}
