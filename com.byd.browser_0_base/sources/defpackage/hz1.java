package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.components.browser_ui.widget.FadingShadowView;

/* JADX INFO: compiled from: PaymentHandlerToolbarView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hz1 {
    public final int a;
    public final View b;
    public final TextView c;
    public final TextView d;
    public final ProgressBar e;
    public final ImageView f;
    public final View g;

    public hz1(Context context) {
        this.a = context.getResources().getDimensionPixelSize(R$dimen.sheet_tab_toolbar_height);
        context.getResources().getDimensionPixelSize(R$dimen.action_bar_shadow_height);
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.sheet_tab_toolbar, (ViewGroup) null);
        this.b = viewInflate;
        this.c = (TextView) viewInflate.findViewById(R$id.origin);
        this.d = (TextView) viewInflate.findViewById(R$id.title);
        this.e = (ProgressBar) viewInflate.findViewById(R$id.progress_bar);
        this.f = (ImageView) viewInflate.findViewById(R$id.security_icon);
        this.g = viewInflate.findViewById(R$id.close);
        viewInflate.findViewById(R$id.open_in_new_tab).setVisibility(8);
        viewInflate.findViewById(R$id.favicon).setVisibility(8);
        ((FadingShadowView) viewInflate.findViewById(R$id.shadow)).a(context.getColor(R$color.toolbar_shadow_color));
    }
}
