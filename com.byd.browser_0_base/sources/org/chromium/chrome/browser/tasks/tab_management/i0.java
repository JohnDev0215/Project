package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.n3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tasks.tab_management.j0;

/* JADX INFO: compiled from: TabListMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i0 extends View.AccessibilityDelegate {
    public final /* synthetic */ j0.m a;
    public final /* synthetic */ j0 b;

    public i0(j0 j0Var, j0.m mVar) {
        this.b = j0Var;
        this.a = mVar;
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        TabListRecyclerView tabListRecyclerView = (TabListRecyclerView) this.a;
        tabListRecyclerView.getClass();
        ArrayList arrayList = new ArrayList();
        int I = RecyclerView.I(view);
        if (I != -1) {
            int i = ((GridLayoutManager) tabListRecyclerView.getLayoutManager()).F;
            Context context = tabListRecyclerView.getContext();
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction = new AccessibilityNodeInfo.AccessibilityAction(R$id.move_tab_left, context.getString(R$string.accessibility_tab_movement_left));
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction2 = new AccessibilityNodeInfo.AccessibilityAction(R$id.move_tab_right, context.getString(R$string.accessibility_tab_movement_right));
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction3 = new AccessibilityNodeInfo.AccessibilityAction(R$id.move_tab_up, context.getString(R$string.accessibility_tab_movement_up));
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction4 = new AccessibilityNodeInfo.AccessibilityAction(R$id.move_tab_down, context.getString(R$string.accessibility_tab_movement_down));
            arrayList.addAll(new ArrayList(Arrays.asList(accessibilityAction, accessibilityAction2, accessibilityAction3, accessibilityAction4)));
            int i2 = I % i;
            if (i2 == 0) {
                arrayList.remove(accessibilityAction);
            } else if (i2 == i - 1) {
                arrayList.remove(accessibilityAction2);
            }
            if (I < i) {
                arrayList.remove(accessibilityAction3);
            }
            if (tabListRecyclerView.o0() - I <= i) {
                arrayList.remove(accessibilityAction4);
            }
            if (I == tabListRecyclerView.o0() - 1) {
                arrayList.remove(accessibilityAction2);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            accessibilityNodeInfo.addAction((AccessibilityNodeInfo.AccessibilityAction) it.next());
        }
    }

    @Override // android.view.View.AccessibilityDelegate
    public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        TabListRecyclerView tabListRecyclerView = (TabListRecyclerView) this.a;
        tabListRecyclerView.getClass();
        int i2 = R$id.move_tab_left;
        if (!(i == i2 || i == R$id.move_tab_right || i == R$id.move_tab_up || i == R$id.move_tab_down)) {
            return super.performAccessibilityAction(view, i, bundle);
        }
        tabListRecyclerView.getClass();
        int I = RecyclerView.I(view);
        int i3 = ((GridLayoutManager) tabListRecyclerView.getLayoutManager()).F;
        Pair pair = new Pair(Integer.valueOf(I), Integer.valueOf(i == i2 ? I - 1 : i == R$id.move_tab_right ? I + 1 : i == R$id.move_tab_up ? I - i3 : i == R$id.move_tab_down ? i3 + I : -1));
        int iIntValue = ((Integer) pair.first).intValue();
        int iIntValue2 = ((Integer) pair.second).intValue();
        j0 j0Var = this.b;
        if (!j0Var.i(iIntValue) || !j0Var.i(iIntValue2)) {
            return false;
        }
        j0Var.f.w(iIntValue, iIntValue2);
        n3.X0("TabGrid.AccessibilityDelegate.Reordered");
        return true;
    }
}
