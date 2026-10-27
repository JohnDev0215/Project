package defpackage;

import org.chromium.components.browser_ui.modaldialog.ModalDialogView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Gw extends Lw {
    public final /* synthetic */ Hw a;

    public Gw(Hw hw, Fw fw) {
        this.a = hw;
    }

    @Override // defpackage.LO
    public void a(Object obj, Object obj2, Object obj3) {
        JO jo = (JO) obj;
        ModalDialogView modalDialogView = (ModalDialogView) obj2;
        InterfaceC1821yO interfaceC1821yO = (InterfaceC1821yO) obj3;
        HO ho = AbstractC1711wO.m;
        if (ho == interfaceC1821yO) {
            this.a.d.setCanceledOnTouchOutside(jo.b(ho));
        } else {
            super.b(jo, modalDialogView, interfaceC1821yO);
        }
    }
}
