package f;

import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: CookieJar.java */
/* JADX INFO: loaded from: classes.dex */
public interface q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final q f5492a = new a();

    /* JADX INFO: compiled from: CookieJar.java */
    class a implements q {
        a() {
        }

        @Override // f.q
        public List<p> a(x xVar) {
            return Collections.emptyList();
        }

        @Override // f.q
        public void b(x xVar, List<p> list) {
        }
    }

    List<p> a(x xVar);

    void b(x xVar, List<p> list);
}
