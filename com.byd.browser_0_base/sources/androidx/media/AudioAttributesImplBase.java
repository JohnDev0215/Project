package androidx.media;

import defpackage.gq2;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class AudioAttributesImplBase implements AudioAttributesImpl {
    public int a = 0;
    public int b = 0;
    public int c = 0;
    public int d = -1;

    @Override // androidx.media.AudioAttributesImpl
    public final int a() {
        int i = this.d;
        return i != -1 ? i : AudioAttributesCompat.b(this.c, this.a);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesImplBase)) {
            return false;
        }
        AudioAttributesImplBase audioAttributesImplBase = (AudioAttributesImplBase) obj;
        if (this.b != audioAttributesImplBase.b) {
            return false;
        }
        int i = this.c;
        int i2 = audioAttributesImplBase.c;
        int iA = audioAttributesImplBase.a();
        if (iA == 6) {
            i2 |= 4;
        } else if (iA == 7) {
            i2 |= 1;
        }
        return i == (i2 & 273) && this.a == audioAttributesImplBase.a && this.d == audioAttributesImplBase.d;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.a), Integer.valueOf(this.d)});
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
        int i = this.a;
        int i2 = AudioAttributesCompat.b;
        switch (i) {
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
                strE = gq2.e("unknown usage ", i);
                break;
            case 16:
                strE = "USAGE_ASSISTANT";
                break;
        }
        sb.append(strE);
        sb.append(" content=");
        sb.append(this.b);
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.c).toUpperCase());
        return sb.toString();
    }
}
