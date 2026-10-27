package com.android.permissioncontroller.role.ui.handheld;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.role.utils.UiUtils;

/* JADX INFO: loaded from: classes8.dex */
public class FooterPreference extends Preference {
    private static final int ICON_LAYOUT_PADDING_VERTICAL_DP = 16;

    FooterPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    FooterPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    FooterPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FooterPreference(Context context) {
        super(context);
        init();
    }

    private void init() {
        setIcon(R.drawable.ic_info_outline);
        setSelectable(false);
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        holder.setDividerAllowedAbove(true);
        View iconFrame = holder.findViewById(R.id.icon_frame);
        if (iconFrame == null) {
            iconFrame = holder.findViewById(16908350);
        }
        LinearLayout.LayoutParams iconFrameLayoutParams = (LinearLayout.LayoutParams) iconFrame.getLayoutParams();
        iconFrameLayoutParams.gravity = 48;
        iconFrame.setLayoutParams(iconFrameLayoutParams);
        int iconFramePaddingVertical = UiUtils.dpToPxSize(16.0f, iconFrame.getContext());
        iconFrame.setPaddingRelative(iconFrame.getPaddingStart(), iconFramePaddingVertical, iconFrame.getPaddingEnd(), iconFramePaddingVertical);
    }
}
