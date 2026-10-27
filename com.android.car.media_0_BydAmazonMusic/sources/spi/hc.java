package spi;

import com.android.car.apps.common.UxrButton;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class hc implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ UxrButton b;

    public /* synthetic */ hc(UxrButton uxrButton, int i) {
        this.a = i;
        this.b = uxrButton;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                UxrButton uxrButton = this.b;
                int[] iArr = UxrButton.h;
                uxrButton.refreshDrawableState();
                break;
            default:
                UxrButton uxrButton2 = this.b;
                int[] iArr2 = UxrButton.h;
                uxrButton2.refreshDrawableState();
                break;
        }
    }
}
