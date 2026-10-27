package K3;

import java.util.LinkedHashMap;
import n7.z;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f3576a;

    public n(o oVar) {
        this.f3576a = z.z(oVar.l);
    }

    public n(int i7) {
        switch (i7) {
            case 2:
                this.f3576a = new LinkedHashMap(0, 0.75f, true);
                break;
            default:
                this.f3576a = new LinkedHashMap();
                break;
        }
    }
}
