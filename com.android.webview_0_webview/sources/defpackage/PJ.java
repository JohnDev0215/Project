package defpackage;

import java.nio.ByteBuffer;
import java.util.List;
import org.chromium.mojo.system.ResultAnd;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class PJ implements InterfaceC0544bK, VJ, OJ, MJ, InterfaceC0488aK {
    public static final PJ s = new PJ();

    @Override // defpackage.OJ
    public InterfaceC0544bK H() {
        return this;
    }

    @Override // defpackage.InterfaceC0544bK
    public InterfaceC0488aK J() {
        return this;
    }

    @Override // defpackage.VJ
    public void N(ByteBuffer byteBuffer, List list, UJ uj) {
        throw new WJ(3);
    }

    @Override // defpackage.OJ
    public boolean a() {
        return false;
    }

    @Override // defpackage.OJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // defpackage.InterfaceC0488aK
    public ByteBuffer d(long j, long j2, ZJ zj) {
        throw new WJ(3);
    }

    @Override // defpackage.InterfaceC0544bK
    public MJ i() {
        return this;
    }

    @Override // defpackage.InterfaceC0544bK
    public VJ j() {
        return this;
    }

    @Override // defpackage.OJ
    public int t() {
        return 0;
    }

    @Override // defpackage.VJ
    public VJ v() {
        return this;
    }

    @Override // defpackage.VJ
    public ResultAnd y(SJ sj) {
        throw new WJ(3);
    }

    @Override // defpackage.OJ
    public LJ z() {
        return null;
    }
}
