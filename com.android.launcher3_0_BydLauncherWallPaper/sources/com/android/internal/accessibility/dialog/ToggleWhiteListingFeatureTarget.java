package com.android.internal.accessibility.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import com.android.internal.R;
import com.android.internal.accessibility.dialog.TargetAdapter;

/* JADX INFO: loaded from: classes4.dex */
class ToggleWhiteListingFeatureTarget extends AccessibilityTarget {
    ToggleWhiteListingFeatureTarget(Context context, int i, boolean z, String str, CharSequence charSequence, Drawable drawable, String str2) {
        super(context, i, 2, z, str, charSequence, drawable, str2);
    }

    @Override // com.android.internal.accessibility.dialog.AccessibilityTarget, com.android.internal.accessibility.dialog.TargetOperations
    public void updateActionItem(TargetAdapter.ViewHolder viewHolder, int i) {
        super.updateActionItem(viewHolder, i);
        viewHolder.mStatusView.setVisibility(i == 1 ? 8 : 0);
        viewHolder.mStatusView.setText(getContext().getString(isFeatureEnabled() ? R.string.accessibility_shortcut_menu_item_status_on : R.string.accessibility_shortcut_menu_item_status_off));
    }

    private boolean isFeatureEnabled() {
        return Settings.Secure.getInt(getContext().getContentResolver(), getKey(), 0) == 1;
    }
}
