package b.b.g.d;

import b.b.g.n.q.u;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: RearViewMirrorStudy.java */
/* JADX INFO: loaded from: classes2.dex */
public class n extends l {
    @Override // b.b.g.d.l
    public boolean a() {
        return c();
    }

    public int b() {
        int iK0 = u.I().k0();
        BydLogHelper.logD(n.class.getSimpleName(), "getRearViewInnerMode  :  ,value " + iK0);
        int iW0 = b.b.g.n.q.m.p0().W0();
        BydLogHelper.logD(n.class.getSimpleName(), "getRearviewMirrorIsTurnedState  :  ,state " + iW0);
        if (iW0 == 1 || iW0 == 2) {
            if (iK0 == 0) {
                return 1;
            }
            if (iK0 == 1) {
            }
        } else if (iK0 != 0 && iK0 == 1) {
            return 2;
        }
        return 0;
    }

    public boolean c() {
        int iB = b();
        return iB == 1 || iB == 2;
    }

    public boolean d() {
        return false;
    }
}
