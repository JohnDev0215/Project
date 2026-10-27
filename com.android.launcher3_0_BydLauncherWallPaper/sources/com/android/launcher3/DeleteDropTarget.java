package com.android.launcher3;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.android.launcher3.DropTarget;
import com.android.launcher3.dragndrop.DragOptions;
import com.android.launcher3.logging.LoggerUtils;
import com.android.launcher3.model.ModelWriter;
import com.android.launcher3.userevent.nano.LauncherLogProto;
import com.android.launcher3.views.Snackbar;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class DeleteDropTarget extends ButtonDropTarget {
    private int mControlType;

    @Override // com.android.launcher3.ButtonDropTarget
    public int getAccessibilityAction() {
        return R.id.action_remove;
    }

    @Override // com.android.launcher3.ButtonDropTarget
    protected boolean supportsDrop(ItemInfo info) {
        return true;
    }

    public DeleteDropTarget(Context context) {
        super(context);
        this.mControlType = 0;
    }

    public DeleteDropTarget(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        this.mControlType = 0;
    }

    public DeleteDropTarget(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mControlType = 0;
    }

    @Override // com.android.launcher3.ButtonDropTarget, android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mHoverColor = getResources().getColor(R.color.delete_target_hover_tint);
        setDrawable(R.drawable.ic_remove_shadow);
    }

    @Override // com.android.launcher3.ButtonDropTarget, com.android.launcher3.dragndrop.DragController.DragListener
    public void onDragStart(DropTarget.DragObject dragObject, DragOptions options) {
        super.onDragStart(dragObject, options);
        setTextBasedOnDragSource(dragObject.dragInfo);
        setControlTypeBasedOnDragSource(dragObject.dragInfo);
    }

    @Override // com.android.launcher3.ButtonDropTarget
    public boolean supportsAccessibilityDrop(ItemInfo info, View view) {
        return info instanceof WorkspaceItemInfo ? info.screenId >= 0 : (info instanceof LauncherAppWidgetInfo) || (info instanceof FolderInfo);
    }

    private void setTextBasedOnDragSource(ItemInfo item) {
        if (TextUtils.isEmpty(this.mText)) {
            return;
        }
        this.mText = getResources().getString(canRemove(item) ? R.string.remove_drop_target_label : 17039360);
        setContentDescription(this.mText);
        requestLayout();
    }

    private boolean canRemove(ItemInfo item) {
        return item.id != -1;
    }

    private void setControlTypeBasedOnDragSource(ItemInfo item) {
        this.mControlType = item.id != -1 ? 5 : 14;
    }

    @Override // com.android.launcher3.ButtonDropTarget, com.android.launcher3.DropTarget
    public void onDrop(DropTarget.DragObject d, DragOptions options) {
        if (canRemove(d.dragInfo)) {
            this.mLauncher.getModelWriter().prepareToUndoDelete();
        }
        super.onDrop(d, options);
    }

    @Override // com.android.launcher3.ButtonDropTarget
    public void completeDrop(DropTarget.DragObject d) {
        ItemInfo itemInfo = d.dragInfo;
        if (canRemove(itemInfo)) {
            final int currentPage = this.mLauncher.getWorkspace().getCurrentPage();
            onAccessibilityDrop(null, itemInfo);
            final ModelWriter modelWriter = this.mLauncher.getModelWriter();
            Runnable runnable = new Runnable() { // from class: com.android.launcher3.-$$Lambda$DeleteDropTarget$qHwQUZZln9mxJ69_QwqBetVcXjA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$completeDrop$0$DeleteDropTarget(modelWriter, currentPage);
                }
            };
            Launcher launcher = this.mLauncher;
            Objects.requireNonNull(modelWriter);
            Snackbar.show(launcher, R.string.item_removed, R.string.undo, new Runnable() { // from class: com.android.launcher3.-$$Lambda$BAg_e45R3M9tQSdEPNB0D9sEJL4
                @Override // java.lang.Runnable
                public final void run() {
                    modelWriter.commitDelete();
                }
            }, runnable);
        }
    }

    public /* synthetic */ void lambda$completeDrop$0$DeleteDropTarget(ModelWriter modelWriter, int i) {
        modelWriter.abortDelete(i);
        this.mLauncher.getUserEventDispatcher().logActionOnControl(0, 20);
    }

    @Override // com.android.launcher3.ButtonDropTarget
    public void onAccessibilityDrop(View view, ItemInfo item) {
        this.mLauncher.removeItem(view, item, true);
        this.mLauncher.getWorkspace().stripEmptyScreens();
        this.mLauncher.getDragLayer().announceForAccessibility(getContext().getString(R.string.item_removed));
    }

    @Override // com.android.launcher3.ButtonDropTarget
    public LauncherLogProto.Target getDropTargetForLogging() {
        LauncherLogProto.Target targetNewTarget = LoggerUtils.newTarget(2);
        targetNewTarget.controlType = this.mControlType;
        return targetNewTarget;
    }
}
