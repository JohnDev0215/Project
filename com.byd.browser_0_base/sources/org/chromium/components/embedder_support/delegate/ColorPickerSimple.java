package org.chromium.components.embedder_support.delegate;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
import defpackage.bs1;
import org.chromium.components.embedder_support.R$string;
import org.chromium.components.embedder_support.delegate.a;

/* JADX INFO: loaded from: classes2.dex */
public class ColorPickerSimple extends ListView implements a.InterfaceC0131a {
    public static final int[] g = {-65536, -16711681, -16776961, -16711936, -65281, -256, -16777216, -1};
    public static final int[] h = {R$string.color_picker_button_red, R$string.color_picker_button_cyan, R$string.color_picker_button_blue, R$string.color_picker_button_green, R$string.color_picker_button_magenta, R$string.color_picker_button_yellow, R$string.color_picker_button_black, R$string.color_picker_button_white};
    public bs1 e;
    public a f;

    public ColorPickerSimple(Context context) {
        super(context);
    }

    public ColorPickerSimple(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ColorPickerSimple(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
