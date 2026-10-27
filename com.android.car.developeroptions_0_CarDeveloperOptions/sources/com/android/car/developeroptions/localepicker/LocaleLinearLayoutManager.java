package com.android.car.developeroptions.localepicker;

import android.content.Context;
import android.view.View;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class LocaleLinearLayoutManager extends LinearLayoutManager {
    private final AccessibilityNodeInfoCompat.AccessibilityActionCompat mActionMoveBottom;
    private final AccessibilityNodeInfoCompat.AccessibilityActionCompat mActionMoveDown;
    private final AccessibilityNodeInfoCompat.AccessibilityActionCompat mActionMoveTop;
    private final AccessibilityNodeInfoCompat.AccessibilityActionCompat mActionMoveUp;
    private final AccessibilityNodeInfoCompat.AccessibilityActionCompat mActionRemove;
    private final LocaleDragAndDropAdapter mAdapter;
    private final Context mContext;

    public LocaleLinearLayoutManager(Context context, LocaleDragAndDropAdapter localeDragAndDropAdapter) {
        super(context);
        this.mContext = context;
        this.mAdapter = localeDragAndDropAdapter;
        this.mActionMoveUp = new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.action_drag_move_up, this.mContext.getString(R.string.action_drag_label_move_up));
        this.mActionMoveDown = new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.action_drag_move_down, this.mContext.getString(R.string.action_drag_label_move_down));
        this.mActionMoveTop = new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.action_drag_move_top, this.mContext.getString(R.string.action_drag_label_move_top));
        this.mActionMoveBottom = new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.action_drag_move_bottom, this.mContext.getString(R.string.action_drag_label_move_bottom));
        this.mActionRemove = new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.action_drag_remove, this.mContext.getString(R.string.action_drag_label_remove));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfoForItem(recycler, state, view, accessibilityNodeInfoCompat);
        int itemCount = getItemCount();
        int position = getPosition(view);
        StringBuilder sb = new StringBuilder();
        int i = position + 1;
        sb.append(i);
        sb.append(", ");
        sb.append((Object) ((LocaleDragCell) view).getCheckbox().getContentDescription());
        accessibilityNodeInfoCompat.setContentDescription(sb.toString());
        if (this.mAdapter.isRemoveMode()) {
            return;
        }
        if (position > 0) {
            accessibilityNodeInfoCompat.addAction(this.mActionMoveUp);
            accessibilityNodeInfoCompat.addAction(this.mActionMoveTop);
        }
        if (i < itemCount) {
            accessibilityNodeInfoCompat.addAction(this.mActionMoveDown);
            accessibilityNodeInfoCompat.addAction(this.mActionMoveBottom);
        }
        if (itemCount > 1) {
            accessibilityNodeInfoCompat.addAction(this.mActionRemove);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0041  */
    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean performAccessibilityActionForItem(androidx.recyclerview.widget.RecyclerView.Recycler r5, androidx.recyclerview.widget.RecyclerView.State r6, android.view.View r7, int r8, android.os.Bundle r9) {
        /*
            r4 = this;
            int r0 = r4.getItemCount()
            int r1 = r4.getPosition(r7)
            r2 = 0
            r3 = 1
            switch(r8) {
                case 2131361860: goto L36;
                case 2131361861: goto L2c;
                case 2131361862: goto L24;
                case 2131361863: goto L1a;
                case 2131361864: goto L12;
                default: goto Ld;
            }
        Ld:
            boolean r4 = super.performAccessibilityActionForItem(r5, r6, r7, r8, r9)
            return r4
        L12:
            if (r0 <= r3) goto L3f
            com.android.car.developeroptions.localepicker.LocaleDragAndDropAdapter r5 = r4.mAdapter
            r5.removeItem(r1)
            goto L3e
        L1a:
            if (r1 <= 0) goto L3f
            com.android.car.developeroptions.localepicker.LocaleDragAndDropAdapter r5 = r4.mAdapter
            int r6 = r1 + (-1)
            r5.onItemMove(r1, r6)
            goto L3e
        L24:
            if (r1 == 0) goto L3f
            com.android.car.developeroptions.localepicker.LocaleDragAndDropAdapter r5 = r4.mAdapter
            r5.onItemMove(r1, r2)
            goto L3e
        L2c:
            int r5 = r1 + 1
            if (r5 >= r0) goto L3f
            com.android.car.developeroptions.localepicker.LocaleDragAndDropAdapter r6 = r4.mAdapter
            r6.onItemMove(r1, r5)
            goto L3e
        L36:
            int r0 = r0 - r3
            if (r1 == r0) goto L3f
            com.android.car.developeroptions.localepicker.LocaleDragAndDropAdapter r5 = r4.mAdapter
            r5.onItemMove(r1, r0)
        L3e:
            r2 = r3
        L3f:
            if (r2 == 0) goto L46
            com.android.car.developeroptions.localepicker.LocaleDragAndDropAdapter r4 = r4.mAdapter
            r4.doTheUpdate()
        L46:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.localepicker.LocaleLinearLayoutManager.performAccessibilityActionForItem(androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State, android.view.View, int, android.os.Bundle):boolean");
    }
}
