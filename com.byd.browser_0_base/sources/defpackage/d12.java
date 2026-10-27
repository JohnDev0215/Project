package defpackage;

import android.content.DialogInterface;
import java.util.ArrayList;
import org.chromium.components.browser_ui.photo_picker.PickerCategoryView;

/* JADX INFO: compiled from: PickerCategoryView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d12 implements DialogInterface.OnCancelListener {
    public final /* synthetic */ PickerCategoryView e;

    public d12(PickerCategoryView pickerCategoryView) {
        this.e = pickerCategoryView;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        ArrayList arrayList = PickerCategoryView.I;
        this.e.d(0, null, 0);
    }
}
