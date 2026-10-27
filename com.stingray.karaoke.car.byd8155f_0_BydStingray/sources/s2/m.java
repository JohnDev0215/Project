package s2;

import android.media.MediaCodec;
import h2.AbstractC1343A;

/* JADX INFO: loaded from: classes.dex */
public class m extends m2.e {
    public final int l;

    public m(IllegalStateException illegalStateException, n nVar) {
        StringBuilder sb = new StringBuilder("Decoder failed: ");
        sb.append(nVar == null ? null : nVar.f22095a);
        super(sb.toString(), illegalStateException);
        boolean z6 = illegalStateException instanceof MediaCodec.CodecException;
        this.l = AbstractC1343A.f15195a >= 23 ? z6 ? ((MediaCodec.CodecException) illegalStateException).getErrorCode() : 0 : AbstractC1343A.s(z6 ? ((MediaCodec.CodecException) illegalStateException).getDiagnosticInfo() : null);
    }
}
