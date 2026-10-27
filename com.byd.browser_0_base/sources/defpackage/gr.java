package defpackage;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import org.chromium.base.Callback;
import org.chromium.chrome.R$string;
import org.chromium.components.browser_ui.share.a;
import org.chromium.ui.base.Clipboard;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class gr implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ ir f;

    public /* synthetic */ gr(ir irVar, int i) {
        this.e = i;
        this.f = irVar;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        ir irVar = this.f;
        switch (i) {
            case 0:
                Activity activity = irVar.a;
                ClipboardManager clipboardManager = (ClipboardManager) activity.getSystemService("clipboard");
                a aVar = irVar.e;
                clipboardManager.setPrimaryClip(ClipData.newPlainText(aVar.b, aVar.a()));
                Toast.c(activity, R$string.text_copied, 0).e();
                break;
            case 1:
                a aVar2 = irVar.e;
                if (!aVar2.g.isEmpty()) {
                    Clipboard.getInstance().f(aVar2.g.get(0));
                    Toast.c(irVar.a, R$string.gif_copied, 0).e();
                }
                break;
            default:
                new ki2(irVar.a, irVar.b, irVar.i, irVar.e.b, irVar.d, irVar.k).a();
                break;
        }
    }
}
