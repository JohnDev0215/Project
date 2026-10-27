package org.chromium.chrome.browser.keyboard_accessory.tab_layout_component;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes.dex */
class KeyboardAccessoryTabLayoutView extends TabLayout {
    public KeyboardAccessoryTabLayoutView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setTabSelectionAdapter(TabLayout.d dVar) {
        this.L.clear();
        addOnTabSelectedListener(dVar);
    }
}
