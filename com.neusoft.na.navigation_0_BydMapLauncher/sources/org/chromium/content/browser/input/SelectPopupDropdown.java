package org.chromium.content.browser.input;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import java.util.List;
import org.chromium.content.browser.ContentViewCore;
import org.chromium.content.browser.RenderCoordinates;
import org.chromium.ui.DropdownAdapter;
import org.chromium.ui.DropdownPopupWindow;

/* JADX INFO: loaded from: classes3.dex */
public class SelectPopupDropdown implements SelectPopup {
    private final ContentViewCore mContentViewCore;
    private final Context mContext;
    private final DropdownPopupWindow mDropdownPopupWindow;
    private boolean mSelectionNotified;

    public SelectPopupDropdown(ContentViewCore contentViewCore, List<SelectPopupItem> list, Rect rect, int[] iArr, boolean z) {
        this.mContentViewCore = contentViewCore;
        Context context = contentViewCore.getContext();
        this.mContext = context;
        DropdownPopupWindow dropdownPopupWindow = new DropdownPopupWindow(context, contentViewCore.getViewAndroidDelegate());
        this.mDropdownPopupWindow = dropdownPopupWindow;
        dropdownPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: org.chromium.content.browser.input.SelectPopupDropdown.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                SelectPopupDropdown.this.notifySelection(new int[]{i});
                SelectPopupDropdown.this.hide(false);
            }
        });
        dropdownPopupWindow.setInitialSelection(iArr.length > 0 ? iArr[0] : -1);
        dropdownPopupWindow.setAdapter(new DropdownAdapter(context, list, null));
        dropdownPopupWindow.setRtl(z);
        RenderCoordinates renderCoordinates = contentViewCore.getRenderCoordinates();
        float fFromPixToDip = renderCoordinates.fromPixToDip(renderCoordinates.fromLocalCssToPix(rect.left));
        float fFromPixToDip2 = renderCoordinates.fromPixToDip(renderCoordinates.fromLocalCssToPix(rect.top));
        dropdownPopupWindow.setAnchorRect(fFromPixToDip, fFromPixToDip2, renderCoordinates.fromPixToDip(renderCoordinates.fromLocalCssToPix(rect.right)) - fFromPixToDip, renderCoordinates.fromPixToDip(renderCoordinates.fromLocalCssToPix(rect.bottom)) - fFromPixToDip2);
        dropdownPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: org.chromium.content.browser.input.SelectPopupDropdown.2
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                SelectPopupDropdown.this.notifySelection(null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifySelection(int[] iArr) {
        if (this.mSelectionNotified) {
            return;
        }
        this.mContentViewCore.selectPopupMenuItems(iArr);
        this.mSelectionNotified = true;
    }

    @Override // org.chromium.content.browser.input.SelectPopup
    public void show() {
        this.mDropdownPopupWindow.postShow();
    }

    @Override // org.chromium.content.browser.input.SelectPopup
    public void hide(boolean z) {
        if (z) {
            this.mDropdownPopupWindow.dismiss();
            notifySelection(null);
        } else {
            this.mSelectionNotified = true;
            this.mDropdownPopupWindow.dismiss();
        }
    }
}
