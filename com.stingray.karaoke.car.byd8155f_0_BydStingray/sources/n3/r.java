package n3;

import androidx.lifecycle.f0;
import androidx.lifecycle.k0;
import java.util.Iterator;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class r extends f0 {
    public static final C1952q c = new C1952q();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f19890b = new LinkedHashMap();

    @Override // androidx.lifecycle.f0
    public final void d() {
        LinkedHashMap linkedHashMap = this.f19890b;
        Iterator it = linkedHashMap.values().iterator();
        while (it.hasNext()) {
            ((k0) it.next()).a();
        }
        linkedHashMap.clear();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("NavControllerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} ViewModelStores (");
        Iterator it = this.f19890b.keySet().iterator();
        while (it.hasNext()) {
            sb.append((String) it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        String string = sb.toString();
        A7.m.e(string, "sb.toString()");
        return string;
    }
}
