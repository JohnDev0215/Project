package com.android.launcher3.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.IntProperty;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.launcher3.DropTarget;
import com.android.launcher3.Insettable;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.R;
import com.android.launcher3.ResourceUtils;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.model.WidgetItem;
import com.android.launcher3.userevent.nano.LauncherLogProto;
import com.android.launcher3.util.PackageUserKey;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class WidgetsBottomSheet extends BaseWidgetSheet implements Insettable {
    private static final int DEFAULT_CLOSE_DURATION = 200;
    private static final IntProperty<View> PADDING_BOTTOM = new IntProperty<View>("paddingBottom") { // from class: com.android.launcher3.widget.WidgetsBottomSheet.1
        @Override // android.util.IntProperty
        public void setValue(View view, int paddingBottom) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), paddingBottom);
        }

        @Override // android.util.Property
        public Integer get(View view) {
            return Integer.valueOf(view.getPaddingBottom());
        }
    };
    private Rect mInsets;
    private ItemInfo mOriginalItemInfo;

    @Override // com.android.launcher3.widget.BaseWidgetSheet
    protected int getElementsRowCount() {
        return 1;
    }

    @Override // com.android.launcher3.AbstractFloatingView
    protected boolean isOfType(int type) {
        return (type & 4) != 0;
    }

    @Override // com.android.launcher3.widget.BaseWidgetSheet, com.android.launcher3.logging.StatsLogUtils.LogContainerProvider
    public /* bridge */ /* synthetic */ void fillInLogContainerData(View v, ItemInfo info, LauncherLogProto.Target target, LauncherLogProto.Target targetParent) {
        super.fillInLogContainerData(v, info, target, targetParent);
    }

    @Override // com.android.launcher3.widget.BaseWidgetSheet, com.android.launcher3.AbstractFloatingView
    public /* bridge */ /* synthetic */ int getLogContainerType() {
        return super.getLogContainerType();
    }

    @Override // com.android.launcher3.widget.BaseWidgetSheet, com.android.launcher3.DragSource
    public /* bridge */ /* synthetic */ void onDropCompleted(View target, DropTarget.DragObject d, boolean success) {
        super.onDropCompleted(target, d, success);
    }

    @Override // com.android.launcher3.widget.BaseWidgetSheet, android.view.View.OnLongClickListener
    public /* bridge */ /* synthetic */ boolean onLongClick(View v) {
        return super.onLongClick(v);
    }

    public WidgetsBottomSheet(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WidgetsBottomSheet(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        this.mInsets = new Rect();
        this.mContent = this;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        setTranslationShift(this.mTranslationShift);
    }

    public void populateAndShow(ItemInfo itemInfo) {
        this.mOriginalItemInfo = itemInfo;
        ((TextView) findViewById(R.id.title)).setText(getContext().getString(R.string.widgets_bottom_sheet_title, this.mOriginalItemInfo.title));
        onWidgetsBound();
        attachToContainer();
        this.mIsOpen = false;
        animateOpen();
    }

    @Override // com.android.launcher3.popup.PopupDataProvider.PopupDataChangeListener
    public void onWidgetsBound() {
        List<WidgetItem> widgetsForPackageUser = this.mLauncher.getPopupDataProvider().getWidgetsForPackageUser(new PackageUserKey(this.mOriginalItemInfo.getTargetComponent().getPackageName(), this.mOriginalItemInfo.user));
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.widgets);
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(R.id.widgets_cell_list);
        viewGroup2.removeAllViews();
        for (int i = 0; i < widgetsForPackageUser.size(); i++) {
            WidgetCell widgetCellAddItemCell = addItemCell(viewGroup2);
            widgetCellAddItemCell.applyFromCellItem(widgetsForPackageUser.get(i), LauncherAppState.getInstance(this.mLauncher).getWidgetCache());
            widgetCellAddItemCell.ensurePreview();
            widgetCellAddItemCell.setVisibility(0);
            if (i < widgetsForPackageUser.size() - 1) {
                addDivider(viewGroup2);
            }
        }
        if (widgetsForPackageUser.size() == 1) {
            ((LinearLayout.LayoutParams) viewGroup.getLayoutParams()).gravity = 1;
            return;
        }
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.widget_list_divider, viewGroup, false);
        viewInflate.getLayoutParams().width = ResourceUtils.pxFromDp(16.0f, getResources().getDisplayMetrics());
        viewGroup2.addView(viewInflate, 0);
    }

    private void addDivider(ViewGroup parent) {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_list_divider, parent, true);
    }

    protected WidgetCell addItemCell(ViewGroup parent) {
        WidgetCell widgetCell = (WidgetCell) LayoutInflater.from(getContext()).inflate(R.layout.widget_cell, parent, false);
        widgetCell.setOnClickListener(this);
        widgetCell.setOnLongClickListener(this);
        widgetCell.setAnimatePreview(false);
        parent.addView(widgetCell);
        return widgetCell;
    }

    private void animateOpen() {
        if (this.mIsOpen || this.mOpenCloseAnimator.isRunning()) {
            return;
        }
        this.mIsOpen = true;
        setupNavBarColor();
        this.mOpenCloseAnimator.setValues(PropertyValuesHolder.ofFloat(TRANSLATION_SHIFT, 0.0f));
        this.mOpenCloseAnimator.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        this.mOpenCloseAnimator.start();
    }

    @Override // com.android.launcher3.AbstractFloatingView
    protected void handleClose(boolean animate) {
        handleClose(animate, 200L);
    }

    @Override // com.android.launcher3.Insettable
    public void setInsets(Rect insets) {
        int i = insets.left - this.mInsets.left;
        int i2 = insets.right - this.mInsets.right;
        int i3 = insets.bottom - this.mInsets.bottom;
        this.mInsets.set(insets);
        setPadding(i, getPaddingTop(), i2, i3);
    }

    @Override // com.android.launcher3.AbstractFloatingView
    protected Pair<View, String> getAccessibilityTarget() {
        return Pair.create(findViewById(R.id.title), getContext().getString(this.mIsOpen ? R.string.widgets_list : R.string.widgets_list_closed));
    }

    @Override // com.android.launcher3.AbstractFloatingView
    public Animator createHintCloseAnim(float distanceToMove) {
        return ObjectAnimator.ofInt(this, PADDING_BOTTOM, (int) (distanceToMove + this.mInsets.bottom));
    }
}
