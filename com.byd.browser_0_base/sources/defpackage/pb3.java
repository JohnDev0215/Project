package defpackage;

import android.window.OnBackInvokedCallback;
import androidx.appcompat.widget.a;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class pb3 implements OnBackInvokedCallback {
    public final /* synthetic */ Runnable a;

    public /* synthetic */ pb3(a aVar) {
        this.a = aVar;
    }

    @Override // android.window.OnBackInvokedCallback
    public final void onBackInvoked() {
        this.a.run();
    }
}
