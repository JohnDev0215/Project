package n3;

import android.app.Activity;
import android.content.Context;
import java.util.Iterator;
import kotlin.Metadata;

/* JADX INFO: renamed from: n3.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
@N("activity")
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Ln3/c;", "Ln3/O;", "Ln3/a;", "navigation-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class C1938c extends O {
    public final Activity c;

    public C1938c(Context context) {
        Object next;
        A7.m.f(context, "context");
        Iterator it = P8.m.d0(context, C1937b.f19846n).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((Context) next) instanceof Activity) {
                    break;
                }
            }
        }
        this.c = (Activity) next;
    }

    @Override // n3.O
    public final y a() {
        return new C1936a(this);
    }

    @Override // n3.O
    public final y c(y yVar) {
        throw new IllegalStateException(M5.b.h(new StringBuilder("Destination "), ((C1936a) yVar).f19917q, " does not have an Intent set.").toString());
    }

    @Override // n3.O
    public final boolean f() {
        Activity activity = this.c;
        if (activity == null) {
            return false;
        }
        activity.finish();
        return true;
    }
}
