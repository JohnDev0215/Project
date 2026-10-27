package d.a.a.f.c.a.a;

import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: SkinItem.java */
/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public View f4655a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f4656b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<l> f4657c = new ArrayList();

    public void a() {
        if (d.a.a.f.b.c.a.a(this.f4657c)) {
            return;
        }
        Iterator<l> it = this.f4657c.iterator();
        while (it.hasNext()) {
            it.next().a(this.f4655a);
        }
    }

    public void b() {
        if (d.a.a.f.b.c.a.a(this.f4657c)) {
            return;
        }
        this.f4657c.clear();
    }

    public String toString() {
        return "SkinItem [view=" + this.f4655a.getClass().getSimpleName() + ", attrs=" + this.f4657c + "]";
    }
}
