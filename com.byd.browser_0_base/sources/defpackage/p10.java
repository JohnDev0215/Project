package defpackage;

import android.view.View;
import org.chromium.chrome.browser.creator.CreatorToolbarView;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p10 implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Runnable f;

    public /* synthetic */ p10(int i, Runnable runnable) {
        this.e = i;
        this.f = runnable;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.e;
        Runnable runnable = this.f;
        switch (i) {
            case 0:
                int i2 = CreatorToolbarView.k;
                runnable.run();
                break;
            case 1:
                runnable.run();
                break;
            case 2:
                runnable.run();
                break;
            case 3:
                runnable.run();
                break;
            case 4:
                runnable.run();
                break;
            case 5:
                runnable.run();
                break;
            case 6:
                runnable.run();
                break;
            case 7:
                runnable.run();
                break;
            default:
                runnable.run();
                break;
        }
    }
}
