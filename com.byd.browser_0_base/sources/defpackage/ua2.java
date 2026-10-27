package defpackage;

import android.view.View;
import org.chromium.chrome.browser.download.home.rename.RenameDialogCustomView;

/* JADX INFO: compiled from: RenameDialogCustomView.java */
/* JADX INFO: loaded from: classes.dex */
public final class ua2 implements View.OnFocusChangeListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ int f = 0;
    public final /* synthetic */ RenameDialogCustomView g;

    public ua2(RenameDialogCustomView renameDialogCustomView, int i) {
        this.g = renameDialogCustomView;
        this.e = i;
    }

    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z) {
        int i;
        if (z) {
            int i2 = this.e;
            RenameDialogCustomView renameDialogCustomView = this.g;
            if (i2 <= 0 || (i = this.f) > i2 || i2 >= renameDialogCustomView.getTargetName().length() - 1) {
                renameDialogCustomView.f.selectAll();
            } else {
                renameDialogCustomView.f.setSelection(i, i2);
            }
        }
    }
}
