package d5;

import c5.InterfaceC1031h;
import java.io.Serializable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class X implements InterfaceC1031h, Serializable {
    public final int l;

    public X() {
        AbstractC1095q.e(2, "expectedValuesPerKey");
        this.l = 2;
    }

    @Override // c5.InterfaceC1031h
    public final Object get() {
        return new ArrayList(this.l);
    }
}
