package org.chromium.chrome.browser.gesturenav;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import org.chromium.chrome.R$id;

/* JADX INFO: loaded from: classes.dex */
public class NavigationSheetView extends RelativeLayout {
    public ListView e;

    public NavigationSheetView(Context context) {
        this(context, null);
    }

    public int getVerticalScrollOffset() {
        View childAt = this.e.getChildAt(0);
        if (childAt == null) {
            return 0;
        }
        return -(childAt.getTop() - this.e.getPaddingTop());
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (ListView) findViewById(R$id.navigation_entries);
    }

    public NavigationSheetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
