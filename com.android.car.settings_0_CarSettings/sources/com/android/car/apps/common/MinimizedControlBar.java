package com.android.car.apps.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.car.apps.common.util.ViewUtils;

/* JADX INFO: loaded from: classes5.dex */
public class MinimizedControlBar extends ConstraintLayout implements CarControlBar {
    private static final int NUM_COLUMNS = 3;
    protected ImageView mContentTile;
    private final View[] mFixedViews;
    private FrameLayout[] mSlots;
    protected TextView mSubtitle;
    protected TextView mTitle;
    private View[] mViews;

    public MinimizedControlBar(Context context) {
        this(context, null, 0);
    }

    public MinimizedControlBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MinimizedControlBar(Context context, AttributeSet attrs, int defStyleAttrs) {
        this(context, attrs, defStyleAttrs, R.layout.minimized_control_bar);
    }

    protected MinimizedControlBar(Context context, AttributeSet attrs, int defStyleAttrs, int layoutId) {
        super(context, attrs, defStyleAttrs);
        this.mFixedViews = new View[3];
        init(context, layoutId);
    }

    private void init(Context context, int layoutId) {
        inflate(context, layoutId, this);
        this.mViews = new View[3];
        this.mTitle = (TextView) findViewById(R.id.minimized_control_bar_title);
        this.mSubtitle = (TextView) findViewById(R.id.minimized_control_bar_subtitle);
        this.mContentTile = (ImageView) findViewById(R.id.minimized_control_bar_content_tile);
        FrameLayout[] frameLayoutArr = new FrameLayout[3];
        this.mSlots = frameLayoutArr;
        frameLayoutArr[0] = (FrameLayout) findViewById(R.id.minimized_control_bar_left_slot);
        this.mSlots[1] = (FrameLayout) findViewById(R.id.minimized_control_bar_main_slot);
        this.mSlots[2] = (FrameLayout) findViewById(R.id.minimized_control_bar_right_slot);
    }

    @Override // com.android.car.apps.common.CarControlBar
    public void setView(View view, int slotPosition) {
        this.mFixedViews[slotPosition] = view;
        updateViewsLayout();
    }

    @Override // com.android.car.apps.common.CarControlBar
    public void setViews(View[] views) {
        this.mViews = views;
        updateViewsLayout();
    }

    @Override // com.android.car.apps.common.CarControlBar
    public ImageButton createIconButton(Drawable icon) {
        return createIconButton(icon, R.layout.control_bar_button);
    }

    @Override // com.android.car.apps.common.CarControlBar
    public ImageButton createIconButton(Drawable icon, int viewId) {
        LayoutInflater inflater = LayoutInflater.from(this.mSlots[0].getContext());
        ImageButton button = (ImageButton) inflater.inflate(viewId, (ViewGroup) this.mSlots[0], false);
        button.setImageDrawable(icon);
        return button;
    }

    private void updateViewsLayout() {
        int viewIndex = 0;
        for (int i = 0; i < 3; i++) {
            View viewToUse = null;
            View[] viewArr = this.mFixedViews;
            if (viewArr[i] != null) {
                viewToUse = viewArr[i];
            } else {
                View[] viewArr2 = this.mViews;
                if (viewArr2 != null && viewIndex < viewArr2.length) {
                    viewToUse = viewArr2[viewIndex];
                    viewIndex++;
                }
            }
            ViewUtils.setView(viewToUse, this.mSlots[CarControlBar.getSlotIndex(i, 3)]);
        }
    }
}
