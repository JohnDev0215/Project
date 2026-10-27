package spi;

import androidx.activity.ComponentActivity;
import androidx.activity.ComponentDialog;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class v3 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ v3(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                ((ComponentActivity) this.b).invalidateMenu();
                break;
            default:
                ComponentDialog.m0onBackPressedDispatcher$lambda1((ComponentDialog) this.b);
                break;
        }
    }
}
