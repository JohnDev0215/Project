package defpackage;

import android.graphics.Bitmap;
import android.text.TextUtils;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.offlinepages.d;
import org.chromium.content_public.browser.LoadUrlParams;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class t02 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Callback f;

    public /* synthetic */ t02(Callback callback, int i) {
        this.e = i;
        this.f = callback;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        Callback callback = this.f;
        switch (i) {
            case 0:
                callback.onResult((Bitmap) obj);
                break;
            case 1:
                int[] iArr = (int[]) obj;
                callback.onResult(new tq3(iArr[0], iArr[1]));
                break;
            case 2:
                d.c cVar = d.a;
                callback.onResult((LoadUrlParams) obj);
                break;
            case 3:
                callback.onResult(Arrays.asList((String[]) obj));
                break;
            case 4:
                xl2 xl2Var = (xl2) obj;
                if (xl2Var == null || TextUtils.isEmpty(xl2Var.o.d)) {
                    callback.onResult(null);
                } else {
                    callback.onResult(xl2Var.o.d);
                }
                break;
            case 5:
                byte[] bArr = (byte[]) obj;
                callback.onResult(bArr != null ? ByteBuffer.wrap(bArr) : null);
                break;
            case 6:
                callback.onResult((Bitmap) obj);
                break;
            case 7:
                callback.onResult((Integer) obj);
                break;
            case 8:
                callback.onResult(new ArrayList(Arrays.asList((String[]) obj)));
                break;
            case 9:
                od odVar = (od) obj;
                callback.onResult(odVar != null ? new nd(odVar, Bitmap.Config.ARGB_8888) : null);
                break;
            default:
                byte[] bArr2 = (byte[]) obj;
                if (bArr2 == null || bArr2.length == 0) {
                    callback.onResult(null);
                } else {
                    callback.onResult(new od(bArr2));
                }
                break;
        }
    }
}
