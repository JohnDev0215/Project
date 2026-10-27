package p;

import com.spotify.eventsender.eventsender.SequenceNumberProvider$SequenceNumberType;
import java.util.HashMap;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes.dex */
public final class mb2 {
    public final bk0 a;
    public final long b = 1;
    public final HashMap c = new HashMap();

    public mb2(bk0 bk0Var) {
        this.a = bk0Var;
    }

    public final long a(String str, byte[] bArr, SequenceNumberProvider$SequenceNumberType sequenceNumberProvider$SequenceNumberType) {
        v03.j(str, "eventName");
        v03.j(bArr, "sequenceId");
        int iOrdinal = sequenceNumberProvider$SequenceNumberType.ordinal();
        long j = this.b;
        if (iOrdinal != 0) {
            if (iOrdinal != 1) {
                throw new NoWhenBranchMatchedException();
            }
            String strConcat = str.concat(new String(bArr, cs.a));
            HashMap map = this.c;
            Object objValueOf = map.get(strConcat);
            if (objValueOf == null) {
                objValueOf = Long.valueOf(j);
            }
            long jLongValue = ((Number) objValueOf).longValue();
            map.put(strConcat, Long.valueOf(1 + jLongValue));
            return jLongValue;
        }
        bk0 bk0Var = this.a;
        long jZ = bk0Var.z(str, bArr);
        if (jZ != 0) {
            j = jZ;
        }
        ak0 ak0Var = new ak0();
        ak0Var.a = str;
        ak0Var.c = 1 + j;
        ak0Var.b = bArr;
        bk0Var.E(ak0Var);
        return j;
    }
}
