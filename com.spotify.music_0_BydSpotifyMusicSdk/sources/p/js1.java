package p;

import android.os.Bundle;
import com.spotify.automotive.partnervoice.PartnerVoiceSearchIntent;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class js1 implements f82 {
    public final String a;
    public final Bundle b;
    public final String c;
    public final String d;
    public final String e;
    public final PartnerVoiceSearchIntent f;
    public final List g;

    public js1(String str, Bundle bundle, String str2, String str3, String str4, PartnerVoiceSearchIntent partnerVoiceSearchIntent, ArrayList arrayList) {
        v03.j(str, "query");
        v03.j(str2, "utterance");
        v03.j(str3, "provider");
        v03.j(str4, "language");
        v03.j(partnerVoiceSearchIntent, "intent");
        this.a = str;
        this.b = bundle;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = partnerVoiceSearchIntent;
        this.g = arrayList;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof js1)) {
            return false;
        }
        js1 js1Var = (js1) obj;
        return v03.b(this.a, js1Var.a) && v03.b(this.b, js1Var.b) && v03.b(this.c, js1Var.c) && v03.b(this.d, js1Var.d) && v03.b(this.e, js1Var.e) && this.f == js1Var.f && v03.b(this.g, js1Var.g);
    }

    public final int hashCode() {
        return this.g.hashCode() + ((this.f.hashCode() + hf2.b(this.e, hf2.b(this.d, hf2.b(this.c, (this.b.hashCode() + (this.a.hashCode() * 31)) * 31, 31), 31), 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PartnerVoice(query=");
        sb.append(this.a);
        sb.append(", extras=");
        sb.append(this.b);
        sb.append(", utterance=");
        sb.append(this.c);
        sb.append(", provider=");
        sb.append(this.d);
        sb.append(", language=");
        sb.append(this.e);
        sb.append(", intent=");
        sb.append(this.f);
        sb.append(", slotValues=");
        return hf2.e(sb, this.g, ')');
    }
}
