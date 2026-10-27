package defpackage;

import android.view.ActionMode;
import android.view.View;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;

/* JADX INFO: renamed from: az, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0522az {
    public static ActionMode a(View view, SelectionPopupControllerImpl selectionPopupControllerImpl, ActionMode.Callback callback) {
        return view.startActionMode(new KC(selectionPopupControllerImpl, callback), 1);
    }
}
