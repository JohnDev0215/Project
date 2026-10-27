package spi;

import com.android.car.ui.widget.CarUiTextViewImpl;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class v1 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ CarUiTextViewImpl b;

    public /* synthetic */ v1(CarUiTextViewImpl carUiTextViewImpl, int i) {
        this.a = i;
        this.b = carUiTextViewImpl;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0002. Please report as an issue. */
    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
        }
        CarUiTextViewImpl.a(this.b);
    }
}
