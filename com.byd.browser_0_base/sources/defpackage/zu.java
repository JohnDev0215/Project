package defpackage;

import android.view.View;
import org.chromium.components.embedder_support.R$id;
import org.chromium.components.embedder_support.delegate.ColorPickerAdvanced;

/* JADX INFO: compiled from: ColorPickerDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zu implements View.OnClickListener {
    public final /* synthetic */ av e;

    public zu(av avVar) {
        this.e = avVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        av avVar = this.e;
        avVar.f.setVisibility(8);
        avVar.findViewById(R$id.color_picker_simple).setVisibility(8);
        ColorPickerAdvanced colorPickerAdvanced = avVar.e;
        colorPickerAdvanced.setVisibility(0);
        colorPickerAdvanced.setListener(avVar);
        colorPickerAdvanced.setColor(avVar.j);
    }
}
