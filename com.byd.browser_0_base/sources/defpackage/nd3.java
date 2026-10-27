package defpackage;

import android.view.View;
import org.chromium.chrome.browser.content_creation.notes.top_bar.TopBarView;
import org.chromium.chrome.browser.creator.CreatorProfileView;
import org.chromium.chrome.browser.creator.CreatorToolbarView;
import org.chromium.chrome.browser.feed.followmanagement.FollowManagementItemView;
import org.chromium.chrome.browser.password_manager.PasswordManagerDialogView;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class nd3 implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Runnable f;

    public /* synthetic */ nd3(int i, Runnable runnable) {
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
                int i4 = CreatorToolbarView.k;
                runnable.run();
                break;
            case 3:
                runnable.run();
                break;
            case 4:
                int i5 = FollowManagementItemView.j;
                runnable.run();
                break;
            case 5:
                if (runnable != null) {
                    runnable.run();
                }
                break;
            case 6:
                runnable.run();
                break;
            case 7:
                int i6 = PasswordManagerDialogView.j;
                runnable.run();
                break;
            case 8:
                runnable.run();
                break;
            default:
                runnable.run();
                break;
        }
    }
}
