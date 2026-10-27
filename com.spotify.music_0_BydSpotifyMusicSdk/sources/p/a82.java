package p;

import com.spotify.automotive.partnervoice.PartnerVoiceSearchIntent;

/* JADX INFO: loaded from: classes.dex */
public final class a82 extends he1 {
    public final String k;
    public final String l;
    public final String m;
    public final PartnerVoiceSearchIntent n;

    public a82(String str, String str2, String str3, PartnerVoiceSearchIntent partnerVoiceSearchIntent) {
        v03.j(str, "utterance");
        v03.j(str2, "provider");
        v03.j(str3, "language");
        v03.j(partnerVoiceSearchIntent, "intent");
        this.k = str;
        this.l = str2;
        this.m = str3;
        this.n = partnerVoiceSearchIntent;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a82)) {
            return false;
        }
        a82 a82Var = (a82) obj;
        return v03.b(this.k, a82Var.k) && v03.b(this.l, a82Var.l) && v03.b(this.m, a82Var.m) && this.n == a82Var.n;
    }

    public final int hashCode() {
        return this.n.hashCode() + hf2.b(this.m, hf2.b(this.l, this.k.hashCode() * 31, 31), 31);
    }

    public final String toString() {
        return "HasAllRequiredExtras(utterance=" + this.k + ", provider=" + this.l + ", language=" + this.m + ", intent=" + this.n + ')';
    }
}
