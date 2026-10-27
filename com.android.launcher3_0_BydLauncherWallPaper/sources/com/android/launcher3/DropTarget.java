package com.android.launcher3;

import android.graphics.Rect;
import com.android.launcher3.accessibility.DragViewStateAnnouncer;
import com.android.launcher3.dragndrop.DragOptions;
import com.android.launcher3.dragndrop.DragView;

/* JADX INFO: loaded from: classes4.dex */
public interface DropTarget {
    boolean acceptDrop(DragObject dragObject);

    void getHitRectRelativeToDragLayer(Rect outRect);

    boolean isDropEnabled();

    void onDragEnter(DragObject dragObject);

    void onDragExit(DragObject dragObject);

    void onDragOver(DragObject dragObject);

    void onDrop(DragObject dragObject, DragOptions options);

    void prepareAccessibilityDrop();

    public static class DragObject {
        public boolean accessibleDrag;
        public DragViewStateAnnouncer stateAnnouncer;
        public int x = -1;
        public int y = -1;
        public int xOffset = -1;
        public int yOffset = -1;
        public boolean dragComplete = false;
        public DragView dragView = null;
        public ItemInfo dragInfo = null;
        public ItemInfo originalDragInfo = null;
        public DragSource dragSource = null;
        public boolean cancelled = false;
        public boolean deferDragViewCleanupPostAnimation = true;

        public final float[] getVisualCenter(float[] recycle) {
            if (recycle == null) {
                recycle = new float[2];
            }
            Rect dragRegion = this.dragView.getDragRegion();
            int i = (this.x - this.xOffset) - dragRegion.left;
            int i2 = (this.y - this.yOffset) - dragRegion.top;
            recycle[0] = i + (dragRegion.width() / 2);
            recycle[1] = i2 + (dragRegion.height() / 2);
            return recycle;
        }
    }
}
