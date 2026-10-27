package defpackage;

import J.N;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.ResultReceiver;
import android.os.StrictMode;
import android.util.Size;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.io.File;
import org.chromium.base.Callback;
import org.chromium.base.PathUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.password_manager.PasswordSettingsUpdaterDispatcherBridge;
import org.chromium.chrome.browser.password_manager.PasswordSettingsUpdaterReceiverBridge;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class uy2 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;
    public final /* synthetic */ Object i;

    public /* synthetic */ uy2(i01 i01Var, View view, int i, ResultReceiver resultReceiver) {
        this.e = 2;
        this.g = i01Var;
        this.h = view;
        this.f = i;
        this.i = resultReceiver;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Bitmap bitmapDecodeFile;
        int i = this.f;
        int i2 = this.e;
        Object obj = this.i;
        Object obj2 = this.h;
        Object obj3 = this.g;
        switch (i2) {
            case 0:
                TabContentManager tabContentManager = (TabContentManager) obj3;
                Size size = (Size) obj2;
                Callback callback = (Callback) obj;
                b32 b32Var = TabContentManager.j;
                tabContentManager.getClass();
                File file = new File(PathUtils.getThumbnailCacheDirectory(), i + ".jpeg");
                if (!file.isFile()) {
                    bitmapDecodeFile = null;
                } else if (size == null || size.getWidth() <= 0 || size.getHeight() <= 0) {
                    bitmapDecodeFile = BitmapFactory.decodeFile(file.getPath());
                } else {
                    String path = file.getPath();
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    int i3 = 1;
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(path, options);
                    int i4 = options.outHeight;
                    int i5 = options.outWidth;
                    if (i4 > size.getHeight() || i5 > size.getWidth()) {
                        int i6 = i4 / 2;
                        int i7 = i5 / 2;
                        while (i6 / i3 >= size.getHeight() && i7 / i3 >= size.getWidth()) {
                            i3 *= 2;
                        }
                    }
                    options.inSampleSize = i3;
                    options.inJustDecodeBounds = false;
                    bitmapDecodeFile = BitmapFactory.decodeFile(path, options);
                }
                PostTask.c(rh3.c, new vy2(tabContentManager, i, bitmapDecodeFile, callback, 0));
                return;
            case 1:
                Exception exc = (Exception) obj2;
                PasswordSettingsUpdaterReceiverBridge passwordSettingsUpdaterReceiverBridge = ((PasswordSettingsUpdaterDispatcherBridge) obj3).a;
                passwordSettingsUpdaterReceiverBridge.getClass();
                Object obj4 = ThreadUtils.a;
                ((z7) obj).Z(exc);
                if (passwordSettingsUpdaterReceiverBridge.a == 0) {
                    return;
                }
                if (exc instanceof vx1) {
                    ((vx1) exc).getClass();
                }
                N.MGCWMfe3(passwordSettingsUpdaterReceiverBridge.a, i, 0, 0);
                return;
            default:
                i01 i01Var = (i01) obj3;
                View view = (View) obj2;
                ResultReceiver resultReceiver = (ResultReceiver) obj;
                if (i01Var.d(view)) {
                    StrictMode.ThreadPolicy threadPolicyAllowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
                    try {
                        InputMethodManager inputMethodManagerJ = i01Var.j();
                        if (inputMethodManagerJ != null) {
                            inputMethodManagerJ.showSoftInput(view, i, resultReceiver);
                            break;
                        }
                        return;
                    } finally {
                        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
                    }
                }
                return;
        }
    }

    public /* synthetic */ uy2(Object obj, int i, Object obj2, Object obj3, int i2) {
        this.e = i2;
        this.g = obj;
        this.f = i;
        this.h = obj2;
        this.i = obj3;
    }
}
