package defpackage;

import com.google.android.gms.common.Feature;
import java.util.Arrays;

/* JADX INFO: renamed from: Yc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0433Yc {
    public final C0289Qc a;
    public final Feature b;

    public C0433Yc(C0289Qc c0289Qc, Feature feature, C0362Ud c0362Ud) {
        this.a = c0289Qc;
        this.b = feature;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0433Yc)) {
            C0433Yc c0433Yc = (C0433Yc) obj;
            if (AbstractC0893hf.a(this.a, c0433Yc.a) && AbstractC0893hf.a(this.b, c0433Yc.b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, this.b});
    }

    public final String toString() {
        C0837gf c0837gfB = AbstractC0893hf.b(this);
        c0837gfB.a("key", this.a);
        c0837gfB.a("feature", this.b);
        return c0837gfB.toString();
    }
}
