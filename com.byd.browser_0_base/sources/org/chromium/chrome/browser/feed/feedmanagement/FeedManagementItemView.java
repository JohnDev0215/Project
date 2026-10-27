package org.chromium.chrome.browser.feed.feedmanagement;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.chromium.chrome.R$id;

/* JADX INFO: loaded from: classes.dex */
public class FeedManagementItemView extends LinearLayout {
    public TextView e;
    public TextView f;

    public FeedManagementItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (TextView) findViewById(R$id.feed_management_menu_item_text);
        this.f = (TextView) findViewById(R$id.feed_management_menu_item_description);
    }

    public void setDescription(String str) {
        this.f.setText(str);
    }

    public void setTitle(String str) {
        this.e.setText(str);
    }
}
