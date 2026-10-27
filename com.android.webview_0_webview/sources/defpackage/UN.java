package defpackage;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import java.io.IOException;
import org.chromium.base.ContentUriUtils;
import org.chromium.ui.base.SelectFileDialog;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class UN extends Su {
    public Boolean h;
    public WindowAndroid i;
    public InterfaceC0492aO j;
    public final /* synthetic */ SelectFileDialog k;

    public UN(SelectFileDialog selectFileDialog, Boolean bool, WindowAndroid windowAndroid, InterfaceC0492aO interfaceC0492aO) {
        this.k = selectFileDialog;
        this.h = bool;
        this.i = windowAndroid;
        this.j = interfaceC0492aO;
    }

    @Override // defpackage.Su
    public Object c() {
        try {
            return ContentUriUtils.b(SelectFileDialog.c(this.k, Is.a));
        } catch (IOException e) {
            Ts.a("SelectFileDialog", "Cannot retrieve content uri from file", e);
            return null;
        }
    }

    @Override // defpackage.Su
    public void n(Object obj) {
        Uri uri = (Uri) obj;
        SelectFileDialog selectFileDialog = this.k;
        selectFileDialog.e = uri;
        if (uri == null) {
            if (selectFileDialog.e() || this.h.booleanValue()) {
                this.k.l();
                return;
            } else {
                this.k.j(null);
                return;
            }
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.setFlags(3);
        intent.putExtra("output", this.k.e);
        C0683dt c0683dtX = C0683dt.x();
        try {
            intent.setClipData(ClipData.newUri(Is.a.getContentResolver(), "images", this.k.e));
            c0683dtX.close();
            if (this.h.booleanValue()) {
                this.i.X(intent, this.j, Integer.valueOf(AbstractC0566bn.K1));
            } else {
                this.k.j(intent);
            }
        } catch (Throwable th) {
            try {
                c0683dtX.close();
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
            }
            throw th;
        }
    }
}
