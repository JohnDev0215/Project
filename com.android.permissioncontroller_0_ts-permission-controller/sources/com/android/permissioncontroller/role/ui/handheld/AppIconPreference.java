package com.android.permissioncontroller.role.ui.handheld;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.permissioncontroller.R;

/* JADX INFO: loaded from: classes8.dex */
public class AppIconPreference extends Preference {
    private Mixin mMixin;

    AppIconPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    AppIconPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    AppIconPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    AppIconPreference(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.mMixin = new Mixin(getContext());
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        this.mMixin.onBindViewHolder(holder);
    }

    public static class Mixin {
        private int mIconSize;

        public Mixin(Context context) {
            this.mIconSize = context.getResources().getDimensionPixelSize(R.dimen.secondary_app_icon_size);
        }

        public void onBindViewHolder(PreferenceViewHolder holder) {
            View iconView = holder.findViewById(android.R.id.icon);
            ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            boolean changed = false;
            int i = layoutParams.width;
            int i2 = this.mIconSize;
            if (i != i2) {
                layoutParams.width = i2;
                changed = true;
            }
            int i3 = layoutParams.height;
            int i4 = this.mIconSize;
            if (i3 != i4) {
                layoutParams.height = i4;
                changed = true;
            }
            if (changed) {
                iconView.requestLayout();
            }
        }
    }
}
