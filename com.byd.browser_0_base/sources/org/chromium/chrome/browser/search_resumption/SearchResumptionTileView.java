package org.chromium.chrome.browser.search_resumption;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import defpackage.bz;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class SearchResumptionTileView extends RelativeLayout {
    public GURL e;
    public TextView f;

    public SearchResumptionTileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void a(int i, int i2) {
        if (i == 0) {
            setBackground(bz.c(getContext(), R.drawable.search_resumption_module_background_top));
        } else if (i == i2 - 1) {
            setBackground(bz.c(getContext(), R.drawable.search_resumption_module_background_bottom));
        }
    }

    public String getTextForTesting() {
        return this.f.getText().toString();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.f = (TextView) findViewById(R.id.tile_content);
    }
}
