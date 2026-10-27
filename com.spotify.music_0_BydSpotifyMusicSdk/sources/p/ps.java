package p;

import android.widget.CompoundButton;
import com.google.android.material.chip.ChipGroup;

/* JADX INFO: loaded from: classes.dex */
public final class ps implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ ChipGroup a;

    public ps(ChipGroup chipGroup) {
        this.a = chipGroup;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        ChipGroup chipGroup = this.a;
        if (chipGroup.t) {
            return;
        }
        if (chipGroup.getCheckedChipIds().isEmpty() && chipGroup.f33p) {
            chipGroup.c(compoundButton.getId(), true);
            chipGroup.s = compoundButton.getId();
            return;
        }
        int id = compoundButton.getId();
        if (!z) {
            if (chipGroup.s == id) {
                chipGroup.setCheckedId(-1);
            }
        } else {
            int i = chipGroup.s;
            if (i != -1 && i != id && chipGroup.o) {
                chipGroup.c(i, false);
            }
            chipGroup.setCheckedId(id);
        }
    }
}
