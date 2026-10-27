package defpackage;

import java.util.ArrayList;
import org.chromium.chrome.R$style;
import org.chromium.components.browser_ui.photo_picker.h;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: ProcessInitializationHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class s52 extends vd1 {
    @Override // defpackage.vd1
    public final h G(WindowAndroid windowAndroid, y02 y02Var, boolean z, ArrayList arrayList) {
        h hVar = new h(windowAndroid, windowAndroid.i.get().getContentResolver(), y02Var, z, arrayList);
        hVar.getWindow().getAttributes().windowAnimations = R$style.PickerDialogAnimation;
        hVar.show();
        return hVar;
    }
}
