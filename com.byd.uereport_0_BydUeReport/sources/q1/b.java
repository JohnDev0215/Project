package q1;

import android.graphics.Path;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CompoundTrimPathContent.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<s> f7255a = new ArrayList();

    public void a(s sVar) {
        this.f7255a.add(sVar);
    }

    public void b(Path path) {
        for (int size = this.f7255a.size() - 1; size >= 0; size--) {
            a2.h.b(path, this.f7255a.get(size));
        }
    }
}
