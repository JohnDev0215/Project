package org.chromium.content.browser.input;

import android.text.Editable;
import android.text.Selection;
import android.util.StringBuilderPrinter;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.EditorInfo;
import java.util.Locale;
import org.chromium.base.ThreadUtils;

/* JADX INFO: loaded from: classes3.dex */
public class ImeUtils {
    public static void computeEditorInfo(int i, int i2, int i3, int i4, EditorInfo editorInfo) {
        editorInfo.imeOptions = 301989888;
        editorInfo.inputType = 161;
        if ((i2 & 2) != 0) {
            editorInfo.inputType |= 524288;
        }
        if (i == 1) {
            editorInfo.imeOptions |= 2;
            if ((i2 & 8) == 0) {
                editorInfo.inputType = 32768 | editorInfo.inputType;
            }
        } else if (i == 14 || i == 15) {
            editorInfo.inputType |= 131072;
            if ((i2 & 8) == 0) {
                editorInfo.inputType = 32768 | editorInfo.inputType;
            }
            editorInfo.imeOptions |= 1;
        } else if (i == 2) {
            editorInfo.inputType = 225;
            editorInfo.imeOptions |= 2;
        } else if (i == 3) {
            editorInfo.imeOptions = 3 | editorInfo.imeOptions;
        } else if (i == 7) {
            editorInfo.inputType = 17;
            editorInfo.imeOptions |= 2;
        } else if (i == 4) {
            editorInfo.inputType = 209;
            editorInfo.imeOptions |= 2;
        } else if (i == 6) {
            editorInfo.inputType = 3;
            editorInfo.imeOptions |= 5;
        } else if (i == 5) {
            editorInfo.inputType = 8194;
            editorInfo.imeOptions |= 5;
        }
        if ((i2 & 128) != 0) {
            editorInfo.inputType |= 4096;
        } else if ((i2 & 256) != 0) {
            editorInfo.inputType |= 8192;
        } else if ((i2 & 512) != 0) {
            editorInfo.inputType |= 16384;
        }
        if (i == 15) {
            editorInfo.inputType |= 16384;
        }
        editorInfo.initialSelStart = i3;
        editorInfo.initialSelEnd = i4;
    }

    static String getEditorInfoDebugString(EditorInfo editorInfo) {
        StringBuilder sb = new StringBuilder();
        editorInfo.dump(new StringBuilderPrinter(sb), "");
        return sb.toString();
    }

    static String getEditableDebugString(Editable editable) {
        return String.format(Locale.US, "Editable {[%s] SEL[%d %d] COM[%d %d]}", editable.toString(), Integer.valueOf(Selection.getSelectionStart(editable)), Integer.valueOf(Selection.getSelectionEnd(editable)), Integer.valueOf(BaseInputConnection.getComposingSpanStart(editable)), Integer.valueOf(BaseInputConnection.getComposingSpanEnd(editable)));
    }

    static String getCorrectionInfoDebugString(CorrectionInfo correctionInfo) {
        return correctionInfo.toString();
    }

    static void checkCondition(boolean z) {
        if (!z) {
            throw new AssertionError();
        }
    }

    static void checkCondition(String str, boolean z) {
        if (!z) {
            throw new AssertionError(str);
        }
    }

    static void checkOnUiThread() {
        checkCondition("Should be on UI thread.", ThreadUtils.runningOnUiThread());
    }
}
