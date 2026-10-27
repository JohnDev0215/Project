package org.chromium.chrome.browser.feed.webfeed;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import defpackage.bz;
import defpackage.cr3;
import defpackage.er3;
import defpackage.h7;
import defpackage.jo;
import defpackage.xq3;
import defpackage.z8;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.widget.chips.ChipView;
import org.chromium.ui.widget.LoadingView;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class WebFeedMainMenuItem extends FrameLayout {
    public static final /* synthetic */ int s = 0;
    public Context e;
    public GURL f;
    public Tab g;
    public String h;
    public h7 i;
    public Class<?> j;
    public ChipView k;
    public ChipView l;
    public ChipView m;
    public ImageView n;
    public TextView o;
    public byte[] p;
    public xq3 q;
    public i r;

    public WebFeedMainMenuItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = context;
    }

    public final void a(WebFeedBridge$WebFeedMetadata webFeedBridge$WebFeedMetadata) throws Throwable {
        int i = 0;
        int i2 = webFeedBridge$WebFeedMetadata == null ? 0 : webFeedBridge$WebFeedMetadata.d;
        if (i2 == 0 || i2 == 2) {
            cr3 cr3Var = new cr3(this, i);
            ChipView chipView = this.k;
            if (chipView == null) {
                cr3Var.run();
                return;
            }
            er3 er3Var = new er3(chipView, cr3Var);
            LoadingView loadingView = chipView.h;
            loadingView.a(er3Var);
            loadingView.c();
            return;
        }
        if (i2 != 1) {
            if (i2 == 4) {
                d(this.l, this.e.getText(R$string.menu_following));
                return;
            } else {
                if (i2 == 3) {
                    d(this.m, this.e.getText(R$string.menu_follow));
                    return;
                }
                return;
            }
        }
        z8 z8Var = new z8(this, 16, webFeedBridge$WebFeedMetadata);
        ChipView chipView2 = this.k;
        if (chipView2 == null) {
            z8Var.run();
            return;
        }
        er3 er3Var2 = new er3(chipView2, z8Var);
        LoadingView loadingView2 = chipView2.h;
        loadingView2.a(er3Var2);
        loadingView2.c();
    }

    public final void b() {
        try {
            Intent intent = new Intent(this.e, this.j);
            byte[] bArr = this.p;
            if (bArr != null) {
                intent.putExtra("CREATOR_WEB_FEED_ID", bArr);
            }
            intent.putExtra("CREATOR_URL", this.f.j());
            intent.putExtra("CREATOR_ENTRY_POINT", 0);
            intent.putExtra("CREATOR_FOLLOWING", this.k == this.l);
            this.e.startActivity(intent);
        } catch (Exception e) {
            e.toString();
        }
    }

    public final void c(ChipView chipView, CharSequence charSequence, int i, View.OnClickListener onClickListener) {
        chipView.getPrimaryTextView().setText(charSequence);
        chipView.a(i, true);
        chipView.setOnClickListener(onClickListener);
        chipView.setEnabled(!this.g.m());
        chipView.setVisibility(0);
    }

    public final void d(ChipView chipView, CharSequence charSequence) {
        this.k = chipView;
        if (chipView.getVisibility() == 8) {
            chipView.getPrimaryTextView().setText(charSequence);
            chipView.setEnabled(false);
            chipView.setVisibility(4);
            a aVar = new a(chipView);
            jo joVar = new jo(chipView);
            LoadingView loadingView = chipView.h;
            loadingView.a(joVar);
            loadingView.a(aVar);
            loadingView.e();
        }
        postDelayed(new cr3(this, 1), 400L);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.n = (ImageView) findViewById(R$id.icon);
        this.l = (ChipView) findViewById(R$id.following_chip_view);
        this.m = (ChipView) findViewById(R$id.follow_chip_view);
        this.o = (TextView) findViewById(R$id.menu_item_text);
        ColorStateList colorStateListB = bz.b(this.e, R$color.default_text_color_accent1_tint_list);
        this.l.getPrimaryTextView().setTextColor(colorStateListB);
        this.m.getPrimaryTextView().setTextColor(colorStateListB);
        this.m.setBackgroundTintList(bz.b(this.e, R$color.menu_footer_chip_background_list));
    }

    public void setContextForTest(Context context) {
        this.e = context;
    }

    public class a implements LoadingView.c {
        public boolean e;
        public final /* synthetic */ ChipView f;

        public a(ChipView chipView) {
            this.f = chipView;
        }

        @Override // org.chromium.ui.widget.LoadingView.c
        public final void V0() {
            if (this.e) {
                return;
            }
            this.e = true;
            this.f.setVisibility(0);
        }

        @Override // org.chromium.ui.widget.LoadingView.c
        public final void M() {
        }
    }
}
