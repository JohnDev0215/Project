package defpackage;

import android.util.SparseArray;
import java.util.concurrent.ExecutionException;
import org.chromium.base.TraceEvent;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class XO extends UO {
    public final SparseArray c;
    public final WO d;

    public XO(int i, TO to, WO wo) {
        super(i, to);
        this.c = new SparseArray();
        this.d = wo;
    }

    @Override // defpackage.UO
    public void a(int i) {
        VO vo = (VO) this.c.get(i);
        if (vo == null || vo.b(false)) {
            e(d(i), i);
            return;
        }
        try {
            e((QO) vo.h(), i);
        } catch (InterruptedException unused) {
            b(i, null);
        } catch (ExecutionException unused2) {
            b(i, null);
        }
    }

    @Override // defpackage.UO
    public void c(int i) {
        if (this.c.get(i) != null) {
            return;
        }
        VO vo = new VO(this, i);
        vo.d(Su.g);
        this.c.put(i, vo);
    }

    public final QO d(int i) {
        try {
            TraceEvent.x("AsyncPreloadResourceLoader.createResource", null);
            return this.d.a(i);
        } finally {
            TraceEvent.A("AsyncPreloadResourceLoader.createResource");
        }
    }

    public final void e(QO qo, int i) {
        b(i, qo);
        this.c.remove(i);
    }
}
