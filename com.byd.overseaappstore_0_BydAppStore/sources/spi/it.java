package spi;

import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.ContentLoadingProgressBar;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class it implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ it(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                ((Toolbar) this.b).invalidateMenu();
                break;
            default:
                ((ContentLoadingProgressBar) this.b).hideOnUiThread();
                break;
        }
    }
}
