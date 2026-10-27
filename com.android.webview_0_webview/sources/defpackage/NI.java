package defpackage;

import java.util.Objects;
import org.chromium.mojo.system.ResultAnd;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class NI implements InterfaceC0600cK {
    public final /* synthetic */ OI a;

    public NI(OI oi, MI mi) {
        this.a = oi;
    }

    @Override // defpackage.InterfaceC0600cK
    public void a(int i) {
        ResultAnd resultAndI;
        OI oi = this.a;
        Objects.requireNonNull(oi);
        if (i != 0) {
            oi.A(new WJ(i));
            return;
        }
        do {
            try {
                resultAndI = OI.I(oi.t, oi.v);
            } catch (WJ e) {
                oi.A(e);
                return;
            }
        } while (((Boolean) resultAndI.b).booleanValue());
        if (resultAndI.a != 17) {
            oi.A(new WJ(resultAndI.a));
        }
    }
}
