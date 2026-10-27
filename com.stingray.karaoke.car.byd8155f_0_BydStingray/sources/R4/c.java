package R4;

import android.graphics.Rect;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.material.chip.Chip;
import com.stingray.drivemodedetector.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class c extends L1.a {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Chip f7428q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(Chip chip, Chip chip2) {
        super(chip2);
        this.f7428q = chip;
    }

    @Override // L1.a
    public final void m(ArrayList arrayList) {
        boolean z6 = false;
        arrayList.add(0);
        Rect rect = Chip.f12817F;
        Chip chip = this.f7428q;
        if (chip.d()) {
            e eVar = chip.f12824p;
            if (eVar != null && eVar.f7456T) {
                z6 = true;
            }
            if (!z6 || chip.f12827s == null) {
                return;
            }
            arrayList.add(1);
        }
    }

    @Override // L1.a
    public final void p(int i7, G1.e eVar) {
        AccessibilityNodeInfo accessibilityNodeInfo = eVar.f2646a;
        if (i7 != 1) {
            accessibilityNodeInfo.setContentDescription("");
            accessibilityNodeInfo.setBoundsInParent(Chip.f12817F);
            return;
        }
        Chip chip = this.f7428q;
        CharSequence closeIconContentDescription = chip.getCloseIconContentDescription();
        if (closeIconContentDescription != null) {
            accessibilityNodeInfo.setContentDescription(closeIconContentDescription);
        } else {
            CharSequence text = chip.getText();
            accessibilityNodeInfo.setContentDescription(chip.getContext().getString(R.string.mtrl_chip_close_icon_content_description, TextUtils.isEmpty(text) ? "" : text).trim());
        }
        accessibilityNodeInfo.setBoundsInParent(chip.getCloseIconTouchBoundsInt());
        eVar.b(G1.d.f2634e);
        accessibilityNodeInfo.setEnabled(chip.isEnabled());
    }
}
