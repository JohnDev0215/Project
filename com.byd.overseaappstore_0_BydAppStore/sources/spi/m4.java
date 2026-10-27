package spi;

import androidx.core.widget.ContentLoadingProgressBar;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class m4 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ ContentLoadingProgressBar b;

    public /* synthetic */ m4(ContentLoadingProgressBar contentLoadingProgressBar, int i) {
        this.a = i;
        this.b = contentLoadingProgressBar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                this.b.showOnUiThread();
                break;
            default:
                this.b.lambda$new$1();
                break;
        }
    }
}
