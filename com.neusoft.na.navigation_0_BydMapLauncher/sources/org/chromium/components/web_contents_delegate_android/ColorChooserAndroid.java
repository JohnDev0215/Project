package org.chromium.components.web_contents_delegate_android;

import android.content.Context;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.browser.ContentViewCore;
import org.chromium.ui.ColorPickerDialog;
import org.chromium.ui.ColorSuggestion;
import org.chromium.ui.OnColorChangedListener;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("web_contents_delegate_android")
public class ColorChooserAndroid {
    private final ColorPickerDialog mDialog;
    private final long mNativeColorChooserAndroid;

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeOnColorChosen(long j, int i);

    private ColorChooserAndroid(long j, Context context, int i, ColorSuggestion[] colorSuggestionArr) {
        OnColorChangedListener onColorChangedListener = new OnColorChangedListener() { // from class: org.chromium.components.web_contents_delegate_android.ColorChooserAndroid.1
            @Override // org.chromium.ui.OnColorChangedListener
            public void onColorChanged(int i2) {
                ColorChooserAndroid.this.mDialog.dismiss();
                ColorChooserAndroid colorChooserAndroid = ColorChooserAndroid.this;
                colorChooserAndroid.nativeOnColorChosen(colorChooserAndroid.mNativeColorChooserAndroid, i2);
            }
        };
        this.mNativeColorChooserAndroid = j;
        this.mDialog = new ColorPickerDialog(context, onColorChangedListener, i, colorSuggestionArr);
    }

    private void openColorChooser() {
        this.mDialog.show();
    }

    public void closeColorChooser() {
        this.mDialog.dismiss();
    }

    public static ColorChooserAndroid createColorChooserAndroid(long j, ContentViewCore contentViewCore, int i, ColorSuggestion[] colorSuggestionArr) {
        if (contentViewCore.getWindowAndroid() == null) {
            return null;
        }
        Context context = contentViewCore.getWindowAndroid().getContext().get();
        if (WindowAndroid.activityFromContext(context) == null) {
            return null;
        }
        ColorChooserAndroid colorChooserAndroid = new ColorChooserAndroid(j, context, i, colorSuggestionArr);
        colorChooserAndroid.openColorChooser();
        return colorChooserAndroid;
    }

    private static ColorSuggestion[] createColorSuggestionArray(int i) {
        return new ColorSuggestion[i];
    }

    private static void addToColorSuggestionArray(ColorSuggestion[] colorSuggestionArr, int i, int i2, String str) {
        colorSuggestionArr[i] = new ColorSuggestion(i2, str);
    }
}
