package org.chromium.chrome.browser.omnibox.status;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;
import defpackage.ao3;
import defpackage.l10;
import org.chromium.chrome.R$id;

/* JADX INFO: loaded from: classes2.dex */
public class StatusIconView extends LinearLayout {
    public static final /* synthetic */ int g = 0;
    public View e;
    public Space f;

    public StatusIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int getIconVisibility() {
        return this.e.getVisibility();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = findViewById(R$id.location_bar_status_icon_frame);
        this.f = (Space) findViewById(R$id.location_bar_status_icon_holding_space);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        if (getIconVisibility() != i) {
            boolean zIsLayoutRequested = isLayoutRequested();
            this.e.setVisibility(i);
            ao3.e(this, "StatusIconView setVisibility");
            if (zIsLayoutRequested && getHandler() != null) {
                getHandler().post(new l10(29, this));
            }
        }
        this.f.setVisibility(i == 8 ? 0 : 8);
    }
}
