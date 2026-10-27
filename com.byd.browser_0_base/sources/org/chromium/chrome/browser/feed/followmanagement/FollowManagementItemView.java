package org.chromium.chrome.browser.feed.followmanagement;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.nd3;
import org.chromium.chrome.R$id;

/* JADX INFO: loaded from: classes.dex */
public class FollowManagementItemView extends LinearLayout {
    public static final /* synthetic */ int j = 0;
    public TextView e;
    public TextView f;
    public TextView g;
    public ImageView h;
    public CheckBox i;

    public FollowManagementItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (TextView) findViewById(R$id.follow_management_item_text);
        this.f = (TextView) findViewById(R$id.follow_management_item_url);
        this.g = (TextView) findViewById(R$id.follow_management_item_status);
        this.h = (ImageView) findViewById(R$id.follow_management_favicon);
        this.i = (CheckBox) findViewById(R$id.follow_management_subscribed_checkbox);
    }

    public void setCheckboxClickListener(Runnable runnable) {
        this.i.setOnClickListener(new nd3(4, runnable));
    }

    public void setCheckboxEnabled(boolean z) {
        this.i.setClickable(z);
        this.i.setEnabled(z);
    }

    public void setFavicon(Bitmap bitmap) {
        this.h.setImageBitmap(bitmap);
    }

    public void setStatus(String str) {
        this.g.setText(str);
        if (TextUtils.isEmpty(str)) {
            this.g.setVisibility(8);
        } else {
            this.g.setVisibility(0);
        }
    }

    public void setSubscribed(boolean z) {
        this.i.setChecked(z);
    }

    public void setTitle(String str) {
        this.e.setText(str);
        this.i.setContentDescription(str);
    }

    public void setUrl(String str) {
        this.f.setText(str);
    }
}
