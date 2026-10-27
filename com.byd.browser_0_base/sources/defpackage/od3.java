package defpackage;

import android.view.View;
import org.chromium.chrome.browser.content_creation.notes.top_bar.TopBarView;
import org.chromium.chrome.browser.creator.CreatorProfileView;
import org.chromium.chrome.browser.password_manager.PasswordManagerDialogView;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class od3 implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Runnable f;

    public /* synthetic */ od3(int i, Runnable runnable) {
        this.e = i;
        this.f = runnable;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.e;
        Runnable runnable = this.f;
        switch (i) {
            case 0:
                int i2 = TopBarView.e;
                runnable.run();
                break;
            case 1:
                int i3 = CreatorProfileView.i;
                runnable.run();
                break;
            case 2:
                if (runnable != null) {
                    runnable.run();
                }
                break;
            case 3:
                runnable.run();
                break;
            case 4:
                int i4 = PasswordManagerDialogView.j;
                runnable.run();
                break;
            default:
                runnable.run();
                break;
        }
    }
}
