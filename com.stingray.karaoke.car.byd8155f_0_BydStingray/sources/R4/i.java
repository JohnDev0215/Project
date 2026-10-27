package R4;

import F1.H;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

/* JADX INFO: loaded from: classes.dex */
public final class i implements ViewGroup.OnHierarchyChangeListener {
    public ViewGroup.OnHierarchyChangeListener l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ ChipGroup f7490m;

    public i(ChipGroup chipGroup) {
        this.f7490m = chipGroup;
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public final void onChildViewAdded(View view, View view2) {
        ChipGroup chipGroup = this.f7490m;
        if (view == chipGroup && (view2 instanceof Chip)) {
            if (view2.getId() == -1) {
                int[] iArr = H.f2303a;
                view2.setId(View.generateViewId());
            }
            Chip chip = (Chip) view2;
            if (chip.isChecked()) {
                ((ChipGroup) view).b(chip.getId());
            }
            chip.setOnCheckedChangeListenerInternal(chipGroup.f12839t);
        }
        ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.l;
        if (onHierarchyChangeListener != null) {
            onHierarchyChangeListener.onChildViewAdded(view, view2);
        }
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public final void onChildViewRemoved(View view, View view2) {
        if (view == this.f7490m && (view2 instanceof Chip)) {
            ((Chip) view2).setOnCheckedChangeListenerInternal(null);
        }
        ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.l;
        if (onHierarchyChangeListener != null) {
            onHierarchyChangeListener.onChildViewRemoved(view, view2);
        }
    }
}
