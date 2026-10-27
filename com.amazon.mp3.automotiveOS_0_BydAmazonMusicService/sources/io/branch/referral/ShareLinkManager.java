package io.branch.referral;

import android.graphics.Color;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes7.dex */
class ShareLinkManager {
    AnimatedDialog shareDlg_;
    private final int BG_COLOR_ENABLED = Color.argb(60, 17, 4, 56);
    private final int BG_COLOR_DISABLED = Color.argb(20, 17, 4, 56);
    private boolean isShareInProgress_ = false;
    private int shareDialogThemeID_ = -1;
    private int iconSize_ = 50;
    final int padding = 5;
    final int leftMargin = 100;
    private List<String> includeInShareSheet = new ArrayList();
    private List<String> excludeFromShareSheet = new ArrayList();

    ShareLinkManager() {
    }

    public void cancelShareLinkDialog(boolean z) {
        AnimatedDialog animatedDialog = this.shareDlg_;
        if (animatedDialog == null || !animatedDialog.isShowing()) {
            return;
        }
        if (z) {
            this.shareDlg_.cancel();
        } else {
            this.shareDlg_.dismiss();
        }
    }
}
