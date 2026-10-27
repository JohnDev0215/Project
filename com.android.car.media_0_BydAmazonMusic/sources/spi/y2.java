package spi;

import androidx.core.widget.ContentLoadingProgressBar;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class y2 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ ContentLoadingProgressBar b;

    public /* synthetic */ y2(ContentLoadingProgressBar contentLoadingProgressBar, int i) {
        this.a = i;
        this.b = contentLoadingProgressBar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                this.b.showOnUiThread();
                break;
            case 1:
                this.b.hideOnUiThread();
                break;
            case 2:
                this.b.lambda$new$0();
                break;
            default:
                this.b.lambda$new$1();
                break;
        }
    }
}
