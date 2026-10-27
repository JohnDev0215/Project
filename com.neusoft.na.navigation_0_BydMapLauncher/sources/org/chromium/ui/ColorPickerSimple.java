package org.chromium.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.core.view.ViewCompat;
import org.chromium.ui.ColorSuggestionListAdapter;

/* JADX INFO: loaded from: classes3.dex */
public class ColorPickerSimple extends ListView implements ColorSuggestionListAdapter.OnColorSuggestionClickListener {
    private static final int[] DEFAULT_COLORS = {-65536, -16711681, -16776961, -16711936, -65281, -256, ViewCompat.MEASURED_STATE_MASK, -1};
    private static final int[] DEFAULT_COLOR_LABEL_IDS = {R.string.color_picker_button_red, R.string.color_picker_button_cyan, R.string.color_picker_button_blue, R.string.color_picker_button_green, R.string.color_picker_button_magenta, R.string.color_picker_button_yellow, R.string.color_picker_button_black, R.string.color_picker_button_white};
    private OnColorChangedListener mOnColorChangedListener;

    public ColorPickerSimple(Context context) {
        super(context);
    }

    public ColorPickerSimple(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ColorPickerSimple(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void init(ColorSuggestion[] colorSuggestionArr, OnColorChangedListener onColorChangedListener) {
        this.mOnColorChangedListener = onColorChangedListener;
        if (colorSuggestionArr == null) {
            int length = DEFAULT_COLORS.length;
            ColorSuggestion[] colorSuggestionArr2 = new ColorSuggestion[length];
            for (int i = 0; i < length; i++) {
                colorSuggestionArr2[i] = new ColorSuggestion(DEFAULT_COLORS[i], getContext().getString(DEFAULT_COLOR_LABEL_IDS[i]));
            }
            colorSuggestionArr = colorSuggestionArr2;
        }
        ColorSuggestionListAdapter colorSuggestionListAdapter = new ColorSuggestionListAdapter(getContext(), colorSuggestionArr);
        colorSuggestionListAdapter.setOnColorSuggestionClickListener(this);
        setAdapter((ListAdapter) colorSuggestionListAdapter);
    }

    @Override // org.chromium.ui.ColorSuggestionListAdapter.OnColorSuggestionClickListener
    public void onColorSuggestionClick(ColorSuggestion colorSuggestion) {
        this.mOnColorChangedListener.onColorChanged(colorSuggestion.mColor);
    }
}
