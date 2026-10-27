package defpackage;

import J.N;
import android.content.Context;
import android.net.Uri;
import org.chromium.base.ContentUriUtils;
import org.chromium.ui.base.SelectFileDialog;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class VN extends Su {
    public String[] h;
    public final Context i;
    public final boolean j;
    public final Uri[] k;
    public final /* synthetic */ SelectFileDialog l;

    public VN(SelectFileDialog selectFileDialog, Context context, boolean z, Uri[] uriArr) {
        this.l = selectFileDialog;
        this.i = context;
        this.j = z;
        this.k = uriArr;
    }

    @Override // defpackage.Su
    public Object c() {
        Uri[] uriArr = this.k;
        this.h = new String[uriArr.length];
        String[] strArr = new String[uriArr.length];
        for (int i = 0; i < this.k.length; i++) {
            try {
                if ("file".equals(this.k[i].getScheme())) {
                    this.h[i] = this.k[i].getSchemeSpecificPart();
                } else {
                    this.h[i] = this.k[i].toString();
                }
                strArr[i] = ContentUriUtils.c(this.k[i], this.i, "_display_name");
            } catch (SecurityException unused) {
                Ts.f("SelectFileDialog", "Unable to extract results from the content provider", new Object[0]);
                return null;
            }
        }
        return strArr;
    }

    @Override // defpackage.Su
    public void n(Object obj) {
        String[] strArr = (String[]) obj;
        if (strArr == null) {
            this.l.l();
            return;
        }
        if (this.j) {
            SelectFileDialog selectFileDialog = this.l;
            long j = selectFileDialog.a;
            String[] strArr2 = this.h;
            if (selectFileDialog.h()) {
                selectFileDialog.n(strArr2.length);
            }
            N.Mx1807vz(j, selectFileDialog, strArr2, strArr);
            return;
        }
        SelectFileDialog selectFileDialog2 = this.l;
        long j2 = selectFileDialog2.a;
        String str = this.h[0];
        String str2 = strArr[0];
        if (selectFileDialog2.h()) {
            selectFileDialog2.n(1);
        }
        N.MBeWYy2V(j2, selectFileDialog2, str, str2);
    }
}
