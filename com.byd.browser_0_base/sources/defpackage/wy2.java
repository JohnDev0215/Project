package defpackage;

import android.graphics.Bitmap;
import android.util.Pair;
import defpackage.kp1;
import java.util.LinkedList;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.contextmenu.ContextMenuNativeDelegateImpl;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class wy2 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Callback f;

    public /* synthetic */ wy2(Callback callback, int i) {
        this.e = i;
        this.f = callback;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = 4;
        int i2 = this.e;
        Callback callback = this.f;
        switch (i2) {
            case 0:
                Bitmap bitmap = (Bitmap) obj;
                if (bitmap != null) {
                    b32 b32Var = TabContentManager.j;
                    oj2.U(1, 4, "GridTabSwitcher.ThumbnailFetchingResult");
                } else {
                    b32 b32Var2 = TabContentManager.j;
                    oj2.U(2, 4, "GridTabSwitcher.ThumbnailFetchingResult");
                }
                callback.onResult(bitmap);
                break;
            case 1:
                ContextMenuNativeDelegateImpl.ImageCallbackResult imageCallbackResult = (ContextMenuNativeDelegateImpl.ImageCallbackResult) obj;
                jk2.c(imageCallbackResult.a, imageCallbackResult.b, callback);
                break;
            case 2:
                Pair pair = (Pair) obj;
                boolean zBooleanValue = ((Boolean) pair.first).booleanValue();
                String str = (String) pair.second;
                if (zBooleanValue || str == null) {
                    callback.onResult(Boolean.valueOf(zBooleanValue));
                } else {
                    callback.onResult(Boolean.FALSE);
                }
                break;
            case 3:
                Integer num = (Integer) obj;
                int iIntValue = num.intValue();
                if (iIntValue == 1) {
                    i = 0;
                } else if (iIntValue == 2) {
                    i = 1;
                } else if (iIntValue == 8) {
                    i = 3;
                } else if (iIntValue != 9) {
                    i = 2;
                }
                kp1.a.a.getClass();
                oj2.U(i, 12, "Mobile.SystemNotification.Permission.RationaleResult");
                callback.onResult(Integer.valueOf(num.intValue() == 1 ? 0 : 1));
                break;
            case 4:
                callback.onResult(((in2) obj) == null ? new LinkedList() : null);
                break;
            default:
                callback.onResult((Pair) obj);
                break;
        }
    }
}
