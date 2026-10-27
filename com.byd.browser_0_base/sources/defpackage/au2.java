package defpackage;

import android.content.Context;
import android.view.View;
import org.chromium.chrome.browser.omnibox.status.StatusView;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: StatusView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class au2 implements View.OnLongClickListener {
    public final /* synthetic */ StatusView e;

    public au2(StatusView statusView) {
        this.e = statusView;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        StatusView statusView = this.e;
        if (statusView.r == 0) {
            return false;
        }
        Context context = statusView.getContext();
        return Toast.f(context, view, context.getResources().getString(statusView.r));
    }
}
