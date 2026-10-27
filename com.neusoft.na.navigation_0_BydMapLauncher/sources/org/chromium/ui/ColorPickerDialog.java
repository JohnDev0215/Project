package org.chromium.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/* JADX INFO: loaded from: classes3.dex */
public class ColorPickerDialog extends AlertDialog implements OnColorChangedListener {
    private final ColorPickerAdvanced mAdvancedColorPicker;
    private int mCurrentColor;
    private final View mCurrentColorView;
    private final int mInitialColor;
    private final OnColorChangedListener mListener;
    private final Button mMoreButton;
    private final ColorPickerSimple mSimpleColorPicker;

    public ColorPickerDialog(Context context, OnColorChangedListener onColorChangedListener, int i, ColorSuggestion[] colorSuggestionArr) {
        super(context, 0);
        this.mListener = onColorChangedListener;
        this.mInitialColor = i;
        this.mCurrentColor = i;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        View viewInflate = layoutInflater.inflate(R.layout.color_picker_dialog_title, (ViewGroup) null);
        setCustomTitle(viewInflate);
        this.mCurrentColorView = viewInflate.findViewById(R.id.selected_color_view);
        ((TextView) viewInflate.findViewById(R.id.title)).setText(R.string.color_picker_dialog_title);
        setButton(-1, context.getString(R.string.color_picker_button_set), new DialogInterface.OnClickListener() { // from class: org.chromium.ui.ColorPickerDialog.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                ColorPickerDialog colorPickerDialog = ColorPickerDialog.this;
                colorPickerDialog.tryNotifyColorSet(colorPickerDialog.mCurrentColor);
            }
        });
        setButton(-2, context.getString(R.string.color_picker_button_cancel), new DialogInterface.OnClickListener() { // from class: org.chromium.ui.ColorPickerDialog.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                ColorPickerDialog colorPickerDialog = ColorPickerDialog.this;
                colorPickerDialog.tryNotifyColorSet(colorPickerDialog.mInitialColor);
            }
        });
        setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: org.chromium.ui.ColorPickerDialog.3
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                ColorPickerDialog colorPickerDialog = ColorPickerDialog.this;
                colorPickerDialog.tryNotifyColorSet(colorPickerDialog.mInitialColor);
            }
        });
        View viewInflate2 = layoutInflater.inflate(R.layout.color_picker_dialog_content, (ViewGroup) null);
        setView(viewInflate2);
        Button button = (Button) viewInflate2.findViewById(R.id.more_colors_button);
        this.mMoreButton = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: org.chromium.ui.ColorPickerDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ColorPickerDialog.this.showAdvancedView();
            }
        });
        ColorPickerAdvanced colorPickerAdvanced = (ColorPickerAdvanced) viewInflate2.findViewById(R.id.color_picker_advanced);
        this.mAdvancedColorPicker = colorPickerAdvanced;
        colorPickerAdvanced.setVisibility(8);
        ColorPickerSimple colorPickerSimple = (ColorPickerSimple) viewInflate2.findViewById(R.id.color_picker_simple);
        this.mSimpleColorPicker = colorPickerSimple;
        colorPickerSimple.init(colorSuggestionArr, this);
        updateCurrentColor(i);
    }

    @Override // org.chromium.ui.OnColorChangedListener
    public void onColorChanged(int i) {
        updateCurrentColor(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAdvancedView() {
        findViewById(R.id.more_colors_button_border).setVisibility(8);
        findViewById(R.id.color_picker_simple).setVisibility(8);
        this.mAdvancedColorPicker.setVisibility(0);
        this.mAdvancedColorPicker.setListener(this);
        this.mAdvancedColorPicker.setColor(this.mCurrentColor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryNotifyColorSet(int i) {
        OnColorChangedListener onColorChangedListener = this.mListener;
        if (onColorChangedListener != null) {
            onColorChangedListener.onColorChanged(i);
        }
    }

    private void updateCurrentColor(int i) {
        this.mCurrentColor = i;
        View view = this.mCurrentColorView;
        if (view != null) {
            view.setBackgroundColor(i);
        }
    }
}
