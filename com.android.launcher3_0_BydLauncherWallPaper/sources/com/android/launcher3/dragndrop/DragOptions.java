package com.android.launcher3.dragndrop;

import android.graphics.Point;
import com.android.launcher3.DropTarget;

/* JADX INFO: loaded from: classes4.dex */
public class DragOptions {
    public boolean isFlingToDelete;
    public boolean isAccessibleDrag = false;
    public Point systemDndStartPoint = null;
    public PreDragCondition preDragCondition = null;
    public float intrinsicIconScaleFactor = 1.0f;

    public interface PreDragCondition {
        void onPreDragEnd(DropTarget.DragObject dragObject, boolean dragStarted);

        void onPreDragStart(DropTarget.DragObject dragObject);

        boolean shouldStartDrag(double distanceDragged);
    }
}
