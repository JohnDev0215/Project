package defpackage;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import org.chromium.components.embedder_support.R$id;
import org.chromium.components.embedder_support.R$layout;
import org.chromium.components.embedder_support.R$string;
import org.chromium.components.embedder_support.delegate.ColorChooserAndroid;
import org.chromium.components.embedder_support.delegate.ColorPickerAdvanced;
import org.chromium.components.embedder_support.delegate.ColorPickerSimple;
import org.chromium.components.embedder_support.delegate.ColorSuggestion;
import org.chromium.components.embedder_support.delegate.a;

/* JADX INFO: compiled from: ColorPickerDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class av extends AlertDialog implements bs1 {
    public final ColorPickerAdvanced e;
    public final Button f;
    public final View g;
    public final bs1 h;
    public final int i;
    public int j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public av(Context context, ColorChooserAndroid.a aVar, int i, ColorSuggestion[] colorSuggestionArr) {
        super(context, 0);
        this.h = aVar;
        this.i = i;
        this.j = i;
        View viewJ = z7.J(R$layout.color_picker_dialog_title, context);
        setCustomTitle(viewJ);
        this.g = viewJ.findViewById(R$id.selected_color_view);
        ((TextView) viewJ.findViewById(R$id.title)).setText(R$string.color_picker_dialog_title);
        setButton(-1, context.getString(R$string.color_picker_button_set), new wu(this));
        setButton(-2, context.getString(R$string.color_picker_button_cancel), new xu(this));
        setOnCancelListener(new yu(this));
        View viewJ2 = z7.J(R$layout.color_picker_dialog_content, context);
        setView(viewJ2);
        Button button = (Button) viewJ2.findViewById(R$id.more_colors_button);
        this.f = button;
        button.setOnClickListener(new zu(this));
        ColorPickerAdvanced colorPickerAdvanced = (ColorPickerAdvanced) viewJ2.findViewById(R$id.color_picker_advanced);
        this.e = colorPickerAdvanced;
        colorPickerAdvanced.setVisibility(8);
        ColorPickerSimple colorPickerSimple = (ColorPickerSimple) viewJ2.findViewById(R$id.color_picker_simple);
        colorPickerSimple.e = this;
        if (colorSuggestionArr == null) {
            colorSuggestionArr = new ColorSuggestion[8];
            for (int i2 = 0; i2 < 8; i2++) {
                colorSuggestionArr[i2] = new ColorSuggestion(ColorPickerSimple.g[i2], colorPickerSimple.getContext().getString(ColorPickerSimple.h[i2]));
            }
        }
        a aVar2 = new a(colorPickerSimple.getContext(), colorSuggestionArr);
        colorPickerSimple.f = aVar2;
        aVar2.setOnColorSuggestionClickListener(colorPickerSimple);
        colorPickerSimple.setAdapter((ListAdapter) colorPickerSimple.f);
        colorPickerSimple.setAccessibilityDelegate(new bv());
        int i3 = this.i;
        this.j = i3;
        View view = this.g;
        if (view != null) {
            view.setBackgroundColor(i3);
        }
    }

    @Override // defpackage.bs1
    public final void a(int i) {
        this.j = i;
        View view = this.g;
        if (view != null) {
            view.setBackgroundColor(i);
        }
    }
}
