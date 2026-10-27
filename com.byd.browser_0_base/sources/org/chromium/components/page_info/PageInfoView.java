package org.chromium.components.page_info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.iu1;
import defpackage.y70;

/* JADX INFO: loaded from: classes3.dex */
public class PageInfoView extends FrameLayout implements View.OnClickListener {
    public LinearLayout e;
    public PageInfoRowView f;
    public PageInfoRowView g;
    public PageInfoRowView h;
    public Button i;
    public Runnable j;

    public static class a {
        public boolean a = true;
        public y70 b;
        public iu1 c;
    }

    public PageInfoView(Context context, a aVar) {
        super(context);
        LayoutInflater.from(context).inflate(R$layout.page_info, (ViewGroup) this, true);
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.page_info_row_wrapper);
        this.e = linearLayout;
        a(linearLayout, null, true);
        this.f = (PageInfoRowView) findViewById(R$id.page_info_connection_row);
        this.g = (PageInfoRowView) findViewById(R$id.page_info_permissions_row);
        this.h = (PageInfoRowView) findViewById(R$id.page_info_cookies_row);
        this.j = aVar.c;
        Button button = (Button) findViewById(R$id.page_info_forget_site_button);
        this.i = button;
        a(button, null, false);
        a((TextView) findViewById(R$id.page_info_lite_mode_https_image_compression_message), null, false);
        a((Button) findViewById(R$id.page_info_open_online_button), aVar.b, aVar.a);
    }

    public final void a(View view, y70 y70Var, boolean z) {
        view.setVisibility(z ? 0 : 8);
        view.setTag(R$id.page_info_click_callback, y70Var);
        if (y70Var == null) {
            return;
        }
        view.setOnClickListener(this);
    }

    public PageInfoRowView getConnectionRowView() {
        return this.f;
    }

    public PageInfoRowView getCookiesRowView() {
        return this.h;
    }

    public Button getForgetSiteButton() {
        return this.i;
    }

    public PageInfoRowView getPermissionsRowView() {
        return this.g;
    }

    public ViewGroup getRowWrapper() {
        return this.e;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Object tag = view.getTag(R$id.page_info_click_callback);
        if (tag instanceof Runnable) {
            ((Runnable) tag).run();
        } else {
            throw new IllegalStateException("Unable to find click callback for view: " + view);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.j.run();
    }
}
