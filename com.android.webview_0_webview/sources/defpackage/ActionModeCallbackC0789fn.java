package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import com.android.webview.R;
import java.util.Objects;
import org.chromium.android_webview.AwContents;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: renamed from: fn, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ActionModeCallbackC0789fn implements ActionMode.Callback {
    public final AwContents s;
    public final BD t;
    public int u;

    public ActionModeCallbackC0789fn(Context context, AwContents awContents, WebContents webContents) {
        this.s = awContents;
        SelectionPopupControllerImpl selectionPopupControllerImplT = SelectionPopupControllerImpl.t(webContents);
        Objects.requireNonNull(selectionPopupControllerImplT);
        this.t = selectionPopupControllerImplT;
        selectionPopupControllerImplT.F = 0;
    }

    public final int a(int i) {
        boolean z;
        if (i == 2) {
            Intent intent = new Intent("android.intent.action.WEB_SEARCH");
            intent.putExtra("new_search", true);
            z = !Zs.b(intent, R.anim.abc_fade_in).isEmpty();
        } else {
            z = true;
        }
        if (z) {
            if ((this.s.S.m() & i) != i) {
                return i;
            }
        }
        return 0;
    }

    @Override // android.view.ActionMode.Callback
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        if (!this.t.g()) {
            return true;
        }
        if (menuItem.getGroupId() != AbstractC0371Um.W3) {
            return this.t.h(actionMode, menuItem);
        }
        Intent intent = menuItem.getIntent();
        Jt.a("MobileActionMode.ProcessTextIntent");
        String strI = SelectionPopupControllerImpl.I(((SelectionPopupControllerImpl) this.t).M, 1000);
        if (!TextUtils.isEmpty(strI)) {
            intent.putExtra("android.intent.extra.PROCESS_TEXT", strI);
            try {
                AwContents awContents = this.s;
                Objects.requireNonNull(awContents);
                if (Build.VERSION.SDK_INT == 23) {
                    intent.putExtra("android.intent.extra.PROCESS_TEXT_READONLY", true);
                }
                if (Is.a(awContents.w) == null) {
                    awContents.w.startActivity(intent);
                } else {
                    awContents.v0.b.c(intent, 100);
                }
            } catch (ActivityNotFoundException unused) {
            }
        }
        return true;
    }

    @Override // android.view.ActionMode.Callback
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        int iA = a(1) | a(2) | a(4);
        if (iA != this.u) {
            ((SelectionPopupControllerImpl) this.t).F = iA;
            this.u = iA;
        }
        this.t.i(actionMode, menu);
        return true;
    }

    @Override // android.view.ActionMode.Callback
    public void onDestroyActionMode(ActionMode actionMode) {
        SelectionPopupControllerImpl selectionPopupControllerImpl = (SelectionPopupControllerImpl) this.t;
        selectionPopupControllerImpl.E = null;
        if (selectionPopupControllerImpl.L) {
            selectionPopupControllerImpl.p();
        }
    }

    @Override // android.view.ActionMode.Callback
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        this.t.k(actionMode, menu);
        return true;
    }
}
