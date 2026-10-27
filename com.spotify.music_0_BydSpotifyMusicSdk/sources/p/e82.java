package p;

import android.os.Bundle;
import com.spotify.automotive.partnervoice.PartnerVoiceSearchSlotValueType;

/* JADX INFO: loaded from: classes.dex */
public final class e82 {
    public static final String d = new String();
    public final u72 a;
    public final py0 b;
    public final m c;

    public e82(u72 u72Var, py0 py0Var, m mVar) {
        this.a = u72Var;
        this.b = py0Var;
        this.c = mVar;
    }

    public static is1 a(Bundle bundle, String str, PartnerVoiceSearchSlotValueType partnerVoiceSearchSlotValueType) {
        String string = bundle.getString(str);
        if (string == null) {
            return null;
        }
        if (string.length() <= 0) {
            string = null;
        }
        if (string != null) {
            return new is1(partnerVoiceSearchSlotValueType, string);
        }
        return null;
    }
}
