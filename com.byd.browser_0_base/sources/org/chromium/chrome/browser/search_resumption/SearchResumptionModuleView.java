package org.chromium.chrome.browser.search_resumption;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import defpackage.wd2;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.ui.R$drawable;

/* JADX INFO: loaded from: classes2.dex */
public class SearchResumptionModuleView extends LinearLayout {
    public View e;
    public ImageView f;
    public SearchResumptionTileContainerView g;

    public SearchResumptionModuleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void a(boolean z, boolean z2) {
        if (z2 || this.g.c() != z) {
            if (z) {
                this.f.setImageResource(R$drawable.ic_expand_less_black_24dp);
            } else {
                this.f.setImageResource(R$drawable.ic_expand_more_black_24dp);
            }
            String string = getContext().getResources().getString(z ? R.string.accessibility_expanded : R.string.accessibility_collapsed);
            String string2 = getContext().getResources().getString(R.string.search_resumption_module_subtitle);
            this.e.setContentDescription(string2 + " " + string);
        }
        if (this.g.c() == z) {
            return;
        }
        this.g.b(z, !z2);
    }

    public final void b() {
        SearchResumptionTileContainerView searchResumptionTileContainerView = this.g;
        for (int i = 0; i < searchResumptionTileContainerView.getChildCount(); i++) {
            ((SearchResumptionTileView) searchResumptionTileContainerView.getChildAt(i)).setOnClickListener(null);
        }
        searchResumptionTileContainerView.removeAllViews();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = findViewById(R.id.search_resumption_module_header);
        this.g = (SearchResumptionTileContainerView) findViewById(R.id.search_resumption_module_tiles_container);
        this.f = (ImageView) findViewById(R.id.header_option);
        a(!SharedPreferencesManager.getInstance().readBoolean("Chrome.SearchResumptionModule.Collapse", false), true);
    }

    public void setExpandCollapseCallback(Callback<Boolean> callback) {
        this.e.setOnClickListener(new wd2(this, 7, callback));
    }
}
