package e;

/* JADX INFO: compiled from: EventListener.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f3623a = new a();

    /* JADX INFO: compiled from: EventListener.java */
    public class a extends p {
    }

    /* JADX INFO: compiled from: EventListener.java */
    public class b implements c {
        public b() {
        }

        @Override // e.p.c
        public p a(e eVar) {
            return p.this;
        }
    }

    /* JADX INFO: compiled from: EventListener.java */
    public interface c {
        p a(e eVar);
    }

    public static c a(p pVar) {
        return pVar.new b();
    }
}
