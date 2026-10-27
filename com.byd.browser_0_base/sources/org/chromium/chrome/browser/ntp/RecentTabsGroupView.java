package org.chromium.chrome.browser.ntp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.LevelListDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import defpackage.za3;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ntp.ForeignSessionHelper;

/* JADX INFO: loaded from: classes2.dex */
public class RecentTabsGroupView extends RelativeLayout {
    public RecentTabsGroupView e;
    public ImageView f;
    public TextView g;
    public TextView h;

    public RecentTabsGroupView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void setGroupViewHeight(boolean z) {
        this.e.setMinimumHeight(getResources().getDimensionPixelOffset(z ? R$dimen.recent_tabs_foreign_session_group_item_height : R$dimen.recent_tabs_default_group_item_height));
    }

    public final void a(boolean z) {
        this.f.setContentDescription(getResources().getString(z ? R$string.accessibility_collapse_section_header : R$string.accessibility_expand_section_header));
        this.f.getDrawable().setLevel(z ? 1 : 0);
    }

    public final void b(ForeignSessionHelper.ForeignSession foreignSession, boolean z) {
        this.g.setText(foreignSession.b);
        this.h.setVisibility(0);
        TextView textView = this.h;
        long jCurrentTimeMillis = System.currentTimeMillis() - foreignSession.c;
        if (jCurrentTimeMillis < 0) {
            jCurrentTimeMillis = 0;
        }
        int i = (int) (jCurrentTimeMillis / 86400000);
        int i2 = (int) (jCurrentTimeMillis / 3600000);
        int i3 = (int) (jCurrentTimeMillis / 60000);
        Resources resources = getResources();
        textView.setText(getResources().getString(R$string.ntp_recent_tabs_last_synced, ((long) i) > 0 ? resources.getQuantityString(R$plurals.n_days_ago, i, Integer.valueOf(i)) : ((long) i2) > 0 ? resources.getQuantityString(R$plurals.n_hours_ago, i2, Integer.valueOf(i2)) : ((long) i3) > 0 ? resources.getQuantityString(R$plurals.n_minutes_ago, i3, Integer.valueOf(i3)) : resources.getString(R$string.just_now)));
        setGroupViewHeight(true);
        a(z);
    }

    public final void c(boolean z) {
        this.g.setText(R$string.ntp_recent_tabs_sync_promo_title);
        this.h.setVisibility(8);
        setGroupViewHeight(false);
        a(z);
    }

    public final void d(boolean z) {
        this.g.setText(R$string.recently_closed);
        this.h.setVisibility(8);
        setGroupViewHeight(false);
        a(z);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (RecentTabsGroupView) getRootView().findViewById(R$id.recent_tabs_group_view);
        this.h = (TextView) findViewById(R$id.time_label);
        this.g = (TextView) findViewById(R$id.device_label);
        this.f = (ImageView) findViewById(R$id.expand_collapse_icon);
        LevelListDrawable levelListDrawable = new LevelListDrawable();
        levelListDrawable.addLevel(0, 0, za3.a(getContext(), R$drawable.ic_expand_more_black_24dp));
        levelListDrawable.addLevel(1, 1, za3.a(getContext(), R$drawable.ic_expand_less_black_24dp));
        this.f.setImageDrawable(levelListDrawable);
    }
}
