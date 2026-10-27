package defpackage;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Bitmap;
import org.chromium.base.Callback;
import org.chromium.chrome.R$string;
import org.chromium.components.browser_ui.share.a;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class fr implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ ir f;

    public /* synthetic */ fr(ir irVar, int i) {
        this.e = i;
        this.f = irVar;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        ir irVar = this.f;
        switch (i) {
            case 0:
                a aVar = irVar.e;
                if (!aVar.g.isEmpty()) {
                    new lk2(irVar.a, aVar.g.get(0), new fr(irVar, 2)).c(i9.e);
                    break;
                }
                break;
            case 1:
                Activity activity = irVar.a;
                ClipboardManager clipboardManager = (ClipboardManager) activity.getSystemService("clipboard");
                a aVar2 = irVar.e;
                clipboardManager.setPrimaryClip(ClipData.newPlainText(aVar2.b, aVar2.e));
                Toast.c(activity, R$string.link_copied, 0).e();
                break;
            default:
                new ud2(irVar.a, (Bitmap) obj, R$string.save_image_filename_prefix, null, irVar.e.a).a();
                break;
        }
    }
}
