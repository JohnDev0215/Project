package b.a.d;

import android.content.Context;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: compiled from: ContextAwareHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Set<b> f1421a = new CopyOnWriteArraySet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile Context f1422b;

    public void a(b bVar) {
        if (this.f1422b != null) {
            bVar.a(this.f1422b);
        }
        this.f1421a.add(bVar);
    }

    public void b() {
        this.f1422b = null;
    }

    public void c(Context context) {
        this.f1422b = context;
        Iterator<b> it = this.f1421a.iterator();
        while (it.hasNext()) {
            it.next().a(context);
        }
    }

    public Context d() {
        return this.f1422b;
    }

    public void e(b bVar) {
        this.f1421a.remove(bVar);
    }
}
