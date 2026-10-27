package q.rorbin.verticaltablayout.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import q.rorbin.badgeview.QBadgeView;

/* JADX INFO: loaded from: classes5.dex */
public class TabBadgeView extends QBadgeView {
    private TabBadgeView(Context context) {
        super(context);
    }

    public static TabBadgeView bindTab(TabView tabView) {
        TabBadgeView tabBadgeView;
        int i = 0;
        while (true) {
            if (i >= tabView.getChildCount()) {
                tabBadgeView = null;
                break;
            }
            View childAt = tabView.getChildAt(i);
            if (childAt != null && (childAt instanceof TabBadgeView)) {
                tabBadgeView = (TabBadgeView) childAt;
                break;
            }
            i++;
        }
        if (tabBadgeView == null) {
            tabBadgeView = new TabBadgeView(tabView.getContext());
            tabView.addView(tabBadgeView, new FrameLayout.LayoutParams(-1, -1));
        }
        tabBadgeView.mTargetView = tabView;
        return tabBadgeView;
    }

    @Override // q.rorbin.badgeview.QBadgeView
    protected void screenFromWindow(boolean z) {
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }
        if (z) {
            this.mActivityRoot.addView(this, new FrameLayout.LayoutParams(-1, -1));
        } else if (this.mTargetView instanceof TabView) {
            ((TabView) this.mTargetView).addView(this, new FrameLayout.LayoutParams(-1, -1));
        } else {
            bindTarget(this.mTargetView);
        }
    }
}
