package defpackage;

import android.text.style.ClickableSpan;
import android.view.MenuItem;
import org.chromium.ui.widget.TextViewWithClickableSpans;

/* JADX INFO: renamed from: rP, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class MenuItemOnMenuItemClickListenerC1437rP implements MenuItem.OnMenuItemClickListener {
    public final /* synthetic */ ClickableSpan a;
    public final /* synthetic */ TextViewWithClickableSpans b;

    public MenuItemOnMenuItemClickListenerC1437rP(TextViewWithClickableSpans textViewWithClickableSpans, ClickableSpan clickableSpan) {
        this.b = textViewWithClickableSpans;
        this.a = clickableSpan;
    }

    @Override // android.view.MenuItem.OnMenuItemClickListener
    public boolean onMenuItemClick(MenuItem menuItem) {
        this.a.onClick(this.b);
        return true;
    }
}
