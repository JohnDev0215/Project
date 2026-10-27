package b.d.a.a.j0;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.timepicker.TimePickerView;

/* JADX INFO: loaded from: classes.dex */
public class d implements MaterialButtonToggleGroup.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ TimePickerView f1959a;

    public d(TimePickerView timePickerView) {
        this.f1959a = timePickerView;
    }

    @Override // com.google.android.material.button.MaterialButtonToggleGroup.e
    public void a(MaterialButtonToggleGroup materialButtonToggleGroup, int i, boolean z) {
        int i2 = i == b.d.a.a.f.material_clock_period_pm_button ? 1 : 0;
        TimePickerView.c cVar = this.f1959a.y;
        if (cVar == null || !z) {
            return;
        }
        cVar.a(i2);
    }
}
