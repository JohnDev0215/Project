package com.android.launcher3.widget;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;
import com.android.launcher3.DragSource;
import com.android.launcher3.DropTarget;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.dragndrop.DragOptions;
import com.android.launcher3.icons.GraphicsUtils;
import com.android.launcher3.logging.LoggerUtils;
import com.android.launcher3.popup.PopupDataProvider;
import com.android.launcher3.touch.ItemLongClickListener;
import com.android.launcher3.uioverrides.WallpaperColorInfo;
import com.android.launcher3.userevent.nano.LauncherLogProto;
import com.android.launcher3.util.SystemUiController;
import com.android.launcher3.util.Themes;
import com.android.launcher3.views.AbstractSlideInView;
import com.android.launcher3.views.BaseDragLayer;

/* JADX INFO: loaded from: classes4.dex */
abstract class BaseWidgetSheet extends AbstractSlideInView implements View.OnClickListener, View.OnLongClickListener, DragSource, PopupDataProvider.PopupDataChangeListener {
    protected final View mColorScrim;
    private Toast mWidgetInstructionToast;

    protected abstract int getElementsRowCount();

    @Override // com.android.launcher3.AbstractFloatingView
    public int getLogContainerType() {
        return 5;
    }

    @Override // com.android.launcher3.DragSource
    public void onDropCompleted(View target, DropTarget.DragObject d, boolean success) {
    }

    public BaseWidgetSheet(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mColorScrim = createColorScrim(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mLauncher.getPopupDataProvider().setChangeListener(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mLauncher.getPopupDataProvider().setChangeListener(null);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View v) {
        Toast toast = this.mWidgetInstructionToast;
        if (toast != null) {
            toast.cancel();
        }
        Toast toastMakeText = Toast.makeText(getContext(), Utilities.wrapForTts(getContext().getText(R.string.long_press_widget_to_add), getContext().getString(R.string.long_accessible_way_to_add)), 0);
        this.mWidgetInstructionToast = toastMakeText;
        toastMakeText.show();
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View v) {
        if (!ItemLongClickListener.canStartDrag(this.mLauncher)) {
            return false;
        }
        if (v instanceof WidgetCell) {
            return beginDraggingWidget((WidgetCell) v);
        }
        return true;
    }

    protected void attachToContainer() {
        getPopupContainer().addView(this.mColorScrim);
        getPopupContainer().addView(this);
    }

    @Override // com.android.launcher3.views.AbstractSlideInView
    protected void setTranslationShift(float translationShift) {
        super.setTranslationShift(translationShift);
        this.mColorScrim.setAlpha(1.0f - this.mTranslationShift);
    }

    private boolean beginDraggingWidget(WidgetCell v) {
        WidgetImageView widgetView = v.getWidgetView();
        if (widgetView.getBitmap() == null) {
            return false;
        }
        int[] iArr = new int[2];
        getPopupContainer().getLocationInDragLayer(widgetView, iArr);
        new PendingItemDragHelper(v).startDrag(widgetView.getBitmapBounds(), widgetView.getBitmap().getWidth(), widgetView.getWidth(), new Point(iArr[0], iArr[1]), this, new DragOptions());
        close(true);
        return true;
    }

    @Override // com.android.launcher3.views.AbstractSlideInView
    protected void onCloseComplete() {
        super.onCloseComplete();
        getPopupContainer().removeView(this.mColorScrim);
        clearNavBarColor();
    }

    protected void clearNavBarColor() {
        getSystemUiController().updateUiState(2, 0);
    }

    protected void setupNavBarColor() {
        getSystemUiController().updateUiState(2, Themes.getAttrBoolean(getContext(), R.attr.isMainColorDark) ? 2 : 1);
    }

    @Override // com.android.launcher3.logging.StatsLogUtils.LogContainerProvider
    public void fillInLogContainerData(View v, ItemInfo info, LauncherLogProto.Target target, LauncherLogProto.Target targetParent) {
        targetParent.containerType = 5;
        targetParent.cardinality = getElementsRowCount();
    }

    @Override // com.android.launcher3.AbstractFloatingView
    public final void logActionCommand(int command) {
        LauncherLogProto.Target targetNewContainerTarget = LoggerUtils.newContainerTarget(getLogContainerType());
        targetNewContainerTarget.cardinality = getElementsRowCount();
        this.mLauncher.getUserEventDispatcher().logActionCommand(command, targetNewContainerTarget);
    }

    protected SystemUiController getSystemUiController() {
        return this.mLauncher.getSystemUiController();
    }

    private static View createColorScrim(Context context) {
        View view = new View(context);
        view.forceHasOverlappingRendering(false);
        WallpaperColorInfo wallpaperColorInfo = WallpaperColorInfo.getInstance(context);
        view.setBackgroundColor(GraphicsUtils.setColorAlphaBound(wallpaperColorInfo.getSecondaryColor(), context.getResources().getInteger(R.integer.extracted_color_gradient_alpha)));
        BaseDragLayer.LayoutParams layoutParams = new BaseDragLayer.LayoutParams(-1, -1);
        layoutParams.ignoreInsets = true;
        view.setLayoutParams(layoutParams);
        return view;
    }
}
