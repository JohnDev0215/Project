package p;

import com.spotify.automotive.partnervoice.PartnerVoiceSearchSlotValueType;
import com.spotify.connectivity.productstate.RxProductState;

/* JADX INFO: loaded from: classes.dex */
public final class is1 {
    public final PartnerVoiceSearchSlotValueType a;
    public final String b;

    public is1(PartnerVoiceSearchSlotValueType partnerVoiceSearchSlotValueType, String str) {
        v03.j(partnerVoiceSearchSlotValueType, RxProductState.Keys.KEY_TYPE);
        this.a = partnerVoiceSearchSlotValueType;
        this.b = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof is1)) {
            return false;
        }
        is1 is1Var = (is1) obj;
        return this.a == is1Var.a && v03.b(this.b, is1Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SlotValue(type=");
        sb.append(this.a);
        sb.append(", value=");
        return hf2.d(sb, this.b, ')');
    }
}
