package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class k10 {
    public final List a;

    public k10(List list) {
        this.a = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k10)) {
            return false;
        }
        k10 k10Var = (k10) obj;
        k10Var.getClass();
        return v03.b(null, null) && v03.b(this.a, k10Var.a);
    }

    public final int hashCode() {
        int iHashCode = ((Integer.hashCode(30000) * 29791) + 1) * 31;
        List list = this.a;
        return iHashCode + (list == null ? 0 : list.hashCode());
    }

    public final String toString() {
        return hf2.e(new StringBuilder("CoreNativePlayerOptions(dealerPingTime=30000, enableClientToken=false, encryptedClientToken=null, canPlay=true, supportedContentTypes="), this.a, ')');
    }
}
