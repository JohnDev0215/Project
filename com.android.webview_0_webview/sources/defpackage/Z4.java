package defpackage;

import androidx.media.AudioAttributesCompat;
import java.util.Arrays;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Z4 implements X4 {
    public int a = 0;
    public int b = 0;
    public int c = 0;
    public int d = -1;

    public boolean equals(Object obj) {
        if (!(obj instanceof Z4)) {
            return false;
        }
        Z4 z4 = (Z4) obj;
        if (this.b != z4.b) {
            return false;
        }
        int i = this.c;
        int i2 = z4.c;
        int iA = z4.d;
        if (iA == -1) {
            iA = AudioAttributesCompat.a(false, i2, z4.a);
        }
        if (iA == 6) {
            i2 |= 4;
        } else if (iA == 7) {
            i2 |= 1;
        }
        return i == (i2 & 273) && this.a == z4.a && this.d == z4.d;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.a), Integer.valueOf(this.d)});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (this.d != -1) {
            sb.append(" stream=");
            sb.append(this.d);
            sb.append(" derived");
        }
        sb.append(" usage=");
        sb.append(AudioAttributesCompat.b(this.a));
        sb.append(" content=");
        sb.append(this.b);
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.c).toUpperCase());
        return sb.toString();
    }
}
