package defpackage;

import android.widget.PopupWindow;
import org.chromium.content.browser.input.e;

/* JADX INFO: compiled from: SelectPopupDropdown.java */
/* JADX INFO: loaded from: classes3.dex */
public final class xh2 implements PopupWindow.OnDismissListener {
    public final /* synthetic */ e e;

    public xh2(e eVar) {
        this.e = eVar;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        this.e.c(null);
    }
}
