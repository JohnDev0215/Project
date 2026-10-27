package R4;

import android.widget.CompoundButton;
import com.google.android.material.chip.ChipGroup;

/* JADX INFO: loaded from: classes.dex */
public final class f implements CompoundButton.OnCheckedChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ChipGroup f7489a;

    public f(ChipGroup chipGroup) {
        this.f7489a = chipGroup;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
        ChipGroup chipGroup = this.f7489a;
        if (chipGroup.f12842w) {
            return;
        }
        if (chipGroup.getCheckedChipIds().isEmpty() && chipGroup.f12838s) {
            chipGroup.c(compoundButton.getId(), true);
            chipGroup.f12841v = compoundButton.getId();
            return;
        }
        int id = compoundButton.getId();
        if (!z6) {
            if (chipGroup.f12841v == id) {
                chipGroup.setCheckedId(-1);
            }
        } else {
            int i7 = chipGroup.f12841v;
            if (i7 != -1 && i7 != id && chipGroup.f12837r) {
                chipGroup.c(i7, false);
            }
            chipGroup.setCheckedId(id);
        }
    }
}
