package org.chromium.chrome.browser.feed.sections;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.tabs.TabLayout;
import org.chromium.chrome.R$id;

/* JADX INFO: loaded from: classes.dex */
public class StickySectionHeaderView extends SectionHeaderView {
    public View m;

    public StickySectionHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // org.chromium.chrome.browser.feed.sections.SectionHeaderView
    public final void d(int i) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        marginLayoutParams.setMargins(marginLayoutParams.leftMargin, i, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
    }

    @Override // org.chromium.chrome.browser.feed.sections.SectionHeaderView
    public void setStickyHeaderOptionsPanel(View view) {
        View view2 = this.m;
        if (view2 != null) {
            removeView(view2);
        }
        addView(view, indexOfChild(findViewById(R$id.sticky_header_hairline)));
        this.m = view;
    }

    @Override // org.chromium.chrome.browser.feed.sections.SectionHeaderView
    public void setStickyHeaderVisible(boolean z) {
        TabLayout tabLayout = (TabLayout) findViewById(R$id.tab_list_view);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            a(tabLayout.k(i));
        }
        setVisibility(z ? 0 : 8);
    }

    @Override // org.chromium.chrome.browser.feed.sections.SectionHeaderView
    public void setOptionsPanel(View view) {
    }
}
