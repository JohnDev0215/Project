package c;

import android.content.Context;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: compiled from: ContextAwareHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Set<b> f3560a = new CopyOnWriteArraySet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private volatile Context f3561b;

    public void a(b bVar) {
        if (this.f3561b != null) {
            bVar.a(this.f3561b);
        }
        this.f3560a.add(bVar);
    }

    public void b() {
        this.f3561b = null;
    }

    public void c(Context context) {
        this.f3561b = context;
        Iterator<b> it = this.f3560a.iterator();
        while (it.hasNext()) {
            it.next().a(context);
        }
    }
}
