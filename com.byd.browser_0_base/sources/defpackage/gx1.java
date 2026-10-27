package defpackage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.password_manager.PasswordGenerationDialogCustomView;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: PasswordGenerationDialogCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class gx1 {
    public final bi1 a;
    public final ix1 b = new ix1();
    public final PasswordGenerationDialogCustomView c;
    public PropertyModel d;

    public gx1(WindowAndroid windowAndroid) {
        this.a = windowAndroid.n();
        this.c = (PasswordGenerationDialogCustomView) LayoutInflater.from((Activity) windowAndroid.k().get()).inflate(R$layout.password_generation_dialog, (ViewGroup) null);
    }
}
