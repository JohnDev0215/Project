package defpackage;

import android.util.Pair;
import java.util.function.Consumer;
import org.chromium.base.Callback;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.permissions.AndroidPermissionDelegate;
import org.chromium.ui.permissions.PermissionCallback;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class im0 implements Consumer {
    public final /* synthetic */ int a;
    public final /* synthetic */ WindowAndroid b;
    public final /* synthetic */ Callback c;
    public final /* synthetic */ AndroidPermissionDelegate d;

    public /* synthetic */ im0(WindowAndroid windowAndroid, Callback callback, AndroidPermissionDelegate androidPermissionDelegate, int i) {
        this.a = i;
        this.b = windowAndroid;
        this.c = callback;
        this.d = androidPermissionDelegate;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.a;
        AndroidPermissionDelegate androidPermissionDelegate = this.d;
        final Callback callback = this.c;
        final WindowAndroid windowAndroid = this.b;
        switch (i) {
            case 0:
                final PropertyModel propertyModel = (PropertyModel) obj;
                final int i2 = 0;
                androidPermissionDelegate.b(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, new PermissionCallback() { // from class: jm0
                    @Override // org.chromium.ui.permissions.PermissionCallback
                    public final void s(int[] iArr, String[] strArr) {
                        int i3 = i2;
                        Callback callback2 = callback;
                        PropertyModel propertyModel2 = propertyModel;
                        boolean z = false;
                        WindowAndroid windowAndroid2 = windowAndroid;
                        switch (i3) {
                            case 0:
                                bi1 bi1VarN = windowAndroid2.n();
                                if (bi1VarN != null && propertyModel2 != null) {
                                    bi1VarN.b(1, propertyModel2);
                                }
                                if (iArr.length > 0 && iArr[0] == 0) {
                                    z = true;
                                }
                                callback2.onResult(Pair.create(Boolean.valueOf(z), null));
                                break;
                            default:
                                bi1 bi1VarN2 = windowAndroid2.n();
                                if (bi1VarN2 != null && propertyModel2 != null) {
                                    bi1VarN2.b(1, propertyModel2);
                                }
                                if (iArr.length > 0 && iArr[0] == 0) {
                                    z = true;
                                }
                                callback2.onResult(Boolean.valueOf(z));
                                break;
                        }
                    }
                });
                break;
            default:
                final PropertyModel propertyModel2 = (PropertyModel) obj;
                final int i3 = 1;
                androidPermissionDelegate.b(new String[]{"android.permission.CAMERA"}, new PermissionCallback() { // from class: jm0
                    @Override // org.chromium.ui.permissions.PermissionCallback
                    public final void s(int[] iArr, String[] strArr) {
                        int i32 = i3;
                        Callback callback2 = callback;
                        PropertyModel propertyModel22 = propertyModel2;
                        boolean z = false;
                        WindowAndroid windowAndroid2 = windowAndroid;
                        switch (i32) {
                            case 0:
                                bi1 bi1VarN = windowAndroid2.n();
                                if (bi1VarN != null && propertyModel22 != null) {
                                    bi1VarN.b(1, propertyModel22);
                                }
                                if (iArr.length > 0 && iArr[0] == 0) {
                                    z = true;
                                }
                                callback2.onResult(Pair.create(Boolean.valueOf(z), null));
                                break;
                            default:
                                bi1 bi1VarN2 = windowAndroid2.n();
                                if (bi1VarN2 != null && propertyModel22 != null) {
                                    bi1VarN2.b(1, propertyModel22);
                                }
                                if (iArr.length > 0 && iArr[0] == 0) {
                                    z = true;
                                }
                                callback2.onResult(Boolean.valueOf(z));
                                break;
                        }
                    }
                });
                break;
        }
    }
}
