package spi;

import android.view.View;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.core.widget.ContentLoadingProgressBar;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class i3 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ View b;

    public /* synthetic */ i3(View view, int i) {
        this.a = i;
        this.b = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                ((Carousel) this.b).lambda$updateItems$0();
                break;
            default:
                ((ContentLoadingProgressBar) this.b).lambda$new$0();
                break;
        }
    }
}
