package defpackage;

import android.media.AudioAttributes;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Y4 implements X4 {
    public AudioAttributes a;
    public int b = -1;

    public boolean equals(Object obj) {
        if (obj instanceof Y4) {
            return this.a.equals(((Y4) obj).a);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        StringBuilder sbF = Z7.f("AudioAttributesCompat: audioattributes=");
        sbF.append(this.a);
        return sbF.toString();
    }
}
