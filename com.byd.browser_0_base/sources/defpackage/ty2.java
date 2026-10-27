package defpackage;

import android.graphics.Bitmap;
import java.util.Arrays;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tasks.tab_management.h0;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ty2 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Callback f;

    public /* synthetic */ ty2(Callback callback, int i) {
        this.e = i;
        this.f = callback;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        Callback callback = this.f;
        switch (i) {
            case 0:
                Bitmap bitmap = (Bitmap) obj;
                b32 b32Var = TabContentManager.j;
                if (bitmap != null) {
                    callback.onResult(bitmap);
                }
                break;
            case 1:
                callback.onResult((Bitmap) obj);
                break;
            case 2:
                PostTask.c(rh3.a, new l91(callback, 20, (List) obj));
                break;
            case 3:
                callback.onResult(Arrays.asList((Bitmap) obj));
                break;
            default:
                callback.onResult(((h0.c) obj).a);
                break;
        }
    }
}
