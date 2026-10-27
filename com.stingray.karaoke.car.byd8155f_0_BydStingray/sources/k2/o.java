package k2;

import c5.InterfaceC1028e;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class o implements InterfaceC1028e {
    public final /* synthetic */ int l;

    @Override // c5.InterfaceC1028e
    public final boolean apply(Object obj) {
        switch (this.l) {
            case 0:
                if (((Map.Entry) obj).getKey() != null) {
                }
                break;
            default:
                if (((String) obj) != null) {
                }
                break;
        }
        return false;
    }
}
