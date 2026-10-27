package defpackage;

import J.N;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.mojo.system.ResultAnd;
import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: renamed from: jK, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0990jK extends AbstractC0935iK implements VJ {
    public C0990jK(CoreImpl coreImpl, int i) {
        super(coreImpl, i);
    }

    @Override // defpackage.VJ
    public void N(ByteBuffer byteBuffer, List list, UJ uj) {
        ByteBuffer byteBufferA;
        CoreImpl coreImpl = this.t;
        Objects.requireNonNull(coreImpl);
        if (list == null || list.isEmpty()) {
            byteBufferA = null;
        } else {
            byteBufferA = coreImpl.a(list.size() * 4);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                OJ oj = (OJ) it.next();
                byteBufferA.putInt(oj.a() ? ((AbstractC0935iK) oj).s : 0);
            }
            byteBufferA.position(0);
        }
        int iMkun8eIV = N.Mkun8eIV(coreImpl, this.s, byteBuffer, byteBuffer == null ? 0 : byteBuffer.limit(), byteBufferA, uj.a);
        if (iMkun8eIV != 0) {
            throw new WJ(iMkun8eIV);
        }
        if (list != null) {
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                OJ oj2 = (OJ) it2.next();
                if (oj2.a()) {
                    ((AbstractC0935iK) oj2).s = 0;
                }
            }
        }
    }

    @Override // defpackage.VJ
    public VJ v() {
        return new C0990jK(this);
    }

    @Override // defpackage.VJ
    public ResultAnd y(SJ sj) {
        CoreImpl coreImpl = this.t;
        Objects.requireNonNull(coreImpl);
        ResultAnd resultAnd = (ResultAnd) N.MEHdfwD0(coreImpl, this.s, sj.a);
        int i = resultAnd.a;
        if (i != 0 && i != 17) {
            throw new WJ(resultAnd.a);
        }
        TJ tj = (TJ) resultAnd.b;
        int[] iArr = tj.b;
        if (iArr == null || iArr.length == 0) {
            tj.c = new ArrayList(0);
        } else {
            tj.c = new ArrayList(iArr.length);
            for (int i2 : iArr) {
                tj.c.add(new C1102lK(coreImpl, i2));
            }
        }
        return resultAnd;
    }

    public C0990jK(AbstractC0935iK abstractC0935iK) {
        super(abstractC0935iK);
    }
}
