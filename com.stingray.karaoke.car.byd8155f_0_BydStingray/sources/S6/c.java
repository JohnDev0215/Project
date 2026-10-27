package S6;

import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f8070a;

    public c(int i7) {
        switch (i7) {
            case 1:
                this.f8070a = new HashMap();
                break;
            case 2:
                this.f8070a = new HashMap(3);
                break;
            case 3:
                this.f8070a = new HashMap();
                break;
            case 4:
                this.f8070a = new HashMap();
                new HashMap();
                break;
            case 5:
                this.f8070a = new HashMap();
                break;
            default:
                this.f8070a = new HashMap(3);
                break;
        }
    }

    public void a(Class cls, f fVar) {
        this.f8070a.put(cls, fVar);
    }

    public void b(Class cls, h hVar) {
        this.f8070a.put(cls, hVar);
    }
}
