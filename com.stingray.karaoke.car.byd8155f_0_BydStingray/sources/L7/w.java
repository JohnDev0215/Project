package L7;

import java.util.Arrays;
import n7.AbstractC1996k;

/* JADX INFO: loaded from: classes.dex */
public final class w extends x {
    @Override // L7.e
    public final Object q(Object[] objArr) {
        l9.d.H(this, objArr);
        Object obj = objArr[0];
        Object[] objArrI = objArr.length <= 1 ? new Object[0] : AbstractC1996k.I(objArr, 1, objArr.length);
        return this.f4066a.invoke(obj, Arrays.copyOf(objArrI, objArrI.length));
    }
}
