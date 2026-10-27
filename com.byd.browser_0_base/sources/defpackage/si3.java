package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: UninitializedMessageException.java */
/* JADX INFO: loaded from: classes.dex */
public final class si3 extends RuntimeException {
    private static final long serialVersionUID = -7466929953374883507L;
    public final List<String> e;

    public si3() {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public si3(ArrayList arrayList) {
        StringBuilder sb = new StringBuilder("Message missing required fields: ");
        Iterator it = arrayList.iterator();
        boolean z = true;
        while (it.hasNext()) {
            String str = (String) it.next();
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(str);
        }
        super(sb.toString());
        this.e = arrayList;
    }
}
