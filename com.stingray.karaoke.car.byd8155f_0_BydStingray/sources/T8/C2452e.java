package t8;

import F8.AbstractC0287w;
import Q7.InterfaceC0671y;
import java.util.Arrays;

/* JADX INFO: renamed from: t8.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2452e extends n {
    @Override // t8.g
    public final AbstractC0287w a(InterfaceC0671y interfaceC0671y) {
        A7.m.f(interfaceC0671y, "module");
        N7.i iVarL = interfaceC0671y.l();
        iVarL.getClass();
        return iVarL.r(N7.k.CHAR);
    }

    @Override // t8.g
    public final String toString() {
        String strValueOf;
        Object obj = this.f22499a;
        Integer numValueOf = Integer.valueOf(((Character) obj).charValue());
        char cCharValue = ((Character) obj).charValue();
        if (cCharValue == '\b') {
            strValueOf = "\\b";
        } else if (cCharValue == '\t') {
            strValueOf = "\\t";
        } else if (cCharValue == '\n') {
            strValueOf = "\\n";
        } else if (cCharValue == '\f') {
            strValueOf = "\\f";
        } else if (cCharValue == '\r') {
            strValueOf = "\\r";
        } else {
            byte type = (byte) Character.getType(cCharValue);
            strValueOf = (type == 0 || type == 13 || type == 14 || type == 15 || type == 16 || type == 18 || type == 19) ? "?" : String.valueOf(cCharValue);
        }
        return String.format("\\u%04X ('%s')", Arrays.copyOf(new Object[]{numValueOf, strValueOf}, 2));
    }
}
