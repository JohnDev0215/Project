package spi;

import android.hardware.bydauto.audio.tsimpl.LocalListenerHelper;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.fragment.app.strictmode.Violation;
import androidx.lifecycle.DispatchQueue;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class xg implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ xg(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                ((LocalListenerHelper) this.b).lambda$asyncNotifyListener$0((Consumer) this.c);
                break;
            case 1:
                FragmentStrictMode.m20handlePolicyViolation$lambda0((FragmentStrictMode.Policy) this.b, (Violation) this.c);
                break;
            default:
                DispatchQueue.m23dispatchAndEnqueue$lambda2$lambda1((DispatchQueue) this.b, (Runnable) this.c);
                break;
        }
    }
}
