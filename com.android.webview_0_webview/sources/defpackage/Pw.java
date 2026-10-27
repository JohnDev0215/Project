package defpackage;

import android.view.View;
import org.chromium.components.browser_ui.modaldialog.ModalDialogView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Pw extends Lw {
    public final /* synthetic */ Qw a;

    public Pw(Qw qw, Mw mw) {
        this.a = qw;
    }

    @Override // defpackage.LO
    public void a(Object obj, Object obj2, Object obj3) {
        JO jo = (JO) obj;
        ModalDialogView modalDialogView = (ModalDialogView) obj2;
        InterfaceC1821yO interfaceC1821yO = (InterfaceC1821yO) obj3;
        HO ho = AbstractC1711wO.m;
        if (ho != interfaceC1821yO) {
            super.b(jo, modalDialogView, interfaceC1821yO);
        } else if (jo.b(ho)) {
            this.a.d.setOnClickListener(new View.OnClickListener(this) { // from class: Ow
                public final Pw s;

                {
                    this.s = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    this.s.a.c(5);
                }
            });
        } else {
            this.a.d.setOnClickListener(null);
        }
    }
}
