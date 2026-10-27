package org.chromium.chrome.features.tasks;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.chromium.chrome.R$id;

/* JADX INFO: loaded from: classes2.dex */
class SingleTabView extends LinearLayout {
    public ImageView e;
    public TextView f;

    public SingleTabView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (ImageView) findViewById(R$id.tab_favicon_view);
        this.f = (TextView) findViewById(R$id.tab_title_view);
    }
}
