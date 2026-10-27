package org.chromium.components.browser_ui.contacts_picker;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: PickerCategoryView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g extends RecyclerView.g {
    public final /* synthetic */ PickerCategoryView a;

    public g(PickerCategoryView pickerCategoryView) {
        this.a = pickerCategoryView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public final void a() {
        PickerCategoryView pickerCategoryView = this.a;
        TopView topView = pickerCategoryView.j;
        boolean z = true;
        if (topView != null) {
            int i = (topView.j.getVisibility() == 0 && topView.j.isSelected()) ? 1 : 0;
            if (topView.k.getVisibility() == 0 && topView.k.isSelected()) {
                i++;
            }
            if (topView.l.getVisibility() == 0 && topView.l.isSelected()) {
                i++;
            }
            if (topView.m.getVisibility() == 0 && topView.m.isSelected()) {
                i++;
            }
            if (topView.n.getVisibility() == 0 && topView.n.isSelected()) {
                i++;
            }
            if (i <= 0) {
                z = false;
            }
        }
        pickerCategoryView.i.setFilterChipsSelected(z);
    }
}
