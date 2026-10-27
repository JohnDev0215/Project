package p;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.material.chip.Chip;
import com.spotify.music.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class ms extends nl0 {
    public final /* synthetic */ Chip q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ms(Chip chip, Chip chip2) {
        super(chip2);
        this.q = chip;
    }

    @Override // p.nl0
    public final void l(ArrayList arrayList) {
        boolean z = false;
        arrayList.add(0);
        Rect rect = Chip.D;
        Chip chip = this.q;
        if (chip.c()) {
            os osVar = chip.m;
            if (osVar != null && osVar.S) {
                z = true;
            }
            if (!z || chip.f32p == null) {
                return;
            }
            arrayList.add(1);
        }
    }

    @Override // p.nl0
    public final void o(int i, c3 c3Var) {
        AccessibilityNodeInfo accessibilityNodeInfo = c3Var.a;
        if (i != 1) {
            accessibilityNodeInfo.setContentDescription("");
            accessibilityNodeInfo.setBoundsInParent(Chip.D);
            return;
        }
        Chip chip = this.q;
        CharSequence closeIconContentDescription = chip.getCloseIconContentDescription();
        if (closeIconContentDescription != null) {
            accessibilityNodeInfo.setContentDescription(closeIconContentDescription);
        } else {
            CharSequence text = chip.getText();
            Context context = chip.getContext();
            Object[] objArr = new Object[1];
            objArr[0] = TextUtils.isEmpty(text) ? "" : text;
            accessibilityNodeInfo.setContentDescription(context.getString(R.string.mtrl_chip_close_icon_content_description, objArr).trim());
        }
        accessibilityNodeInfo.setBoundsInParent(chip.getCloseIconTouchBoundsInt());
        c3Var.b(w2.e);
        accessibilityNodeInfo.setEnabled(chip.isEnabled());
    }
}
