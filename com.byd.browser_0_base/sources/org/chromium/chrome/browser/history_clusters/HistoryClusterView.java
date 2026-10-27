package org.chromium.chrome.browser.history_clusters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import defpackage.ii2;
import defpackage.lb0;
import org.chromium.chrome.R$style;
import org.chromium.components.browser_ui.styles.R$attr;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableItemView;

/* JADX INFO: loaded from: classes.dex */
class HistoryClusterView extends SelectableItemView<HistoryCluster> {
    public static final /* synthetic */ int y = 0;

    public class a extends View.AccessibilityDelegate {
        public a() {
        }

        @Override // android.view.View.AccessibilityDelegate
        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            accessibilityNodeInfo.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
            accessibilityNodeInfo.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_EXPAND);
            accessibilityNodeInfo.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_COLLAPSE);
            HistoryClusterView.this.getClass();
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
        }
    }

    public HistoryClusterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemView
    public final ColorStateList getDefaultStartIconTint() {
        return ColorStateList.valueOf(ii2.g(getContext(), R$attr.colorOnSurfaceVariant));
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemView, org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public final void onFinishInflate() {
        super.onFinishInflate();
        new lb0(getContext(), R$style.HorizontalDivider).a(this, generateDefaultLayoutParams());
        this.r.setVisibility(8);
        this.r.setImportantForAccessibility(2);
        this.r.setOnTouchListener(new View.OnTouchListener() { // from class: org.chromium.chrome.browser.history_clusters.a
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                int i = HistoryClusterView.y;
                return this.e.onTouchEvent(motionEvent);
            }
        });
        setAccessibilityDelegate(new a());
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase
    public final void m() {
    }
}
