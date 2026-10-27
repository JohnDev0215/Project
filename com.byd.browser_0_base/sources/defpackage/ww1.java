package defpackage;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import org.chromium.base.Callback;
import org.chromium.chrome.R$string;

/* JADX INFO: compiled from: PasswordAccessReauthenticationHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ww1 {
    public final Context a;
    public final FragmentManager b;
    public Callback<Boolean> c;

    public ww1(FragmentActivity fragmentActivity, FragmentManager fragmentManager) {
        this.a = fragmentActivity;
        this.b = fragmentManager;
    }

    public final void a(Callback callback, int i) {
        if (n3.o(0)) {
            oj2.U(2, 3, "PasswordManager.ReauthToAccessPasswordInSettings");
            callback.onResult(Boolean.TRUE);
            return;
        }
        this.c = callback;
        FragmentManager fragmentManager = this.b;
        if (i == 0) {
            n3.H(R$string.lockscreen_description_view, fragmentManager);
        } else if (i == 1) {
            n3.H(R$string.lockscreen_description_edit, fragmentManager);
        } else {
            if (i != 2) {
                return;
            }
            n3.H(R$string.lockscreen_description_copy, fragmentManager);
        }
    }
}
