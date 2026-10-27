package defpackage;

import android.widget.PopupMenu;
import org.chromium.ui.widget.TextViewWithClickableSpans;

/* JADX INFO: renamed from: sP, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1492sP implements PopupMenu.OnDismissListener {
    public final /* synthetic */ TextViewWithClickableSpans a;

    public C1492sP(TextViewWithClickableSpans textViewWithClickableSpans) {
        this.a = textViewWithClickableSpans;
    }

    @Override // android.widget.PopupMenu.OnDismissListener
    public void onDismiss(PopupMenu popupMenu) {
        this.a.t = null;
    }
}
