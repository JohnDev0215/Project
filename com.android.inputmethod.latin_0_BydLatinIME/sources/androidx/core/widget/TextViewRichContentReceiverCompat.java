package androidx.core.widget;

import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.Selection;
import android.text.Spanned;
import android.widget.TextView;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class TextViewRichContentReceiverCompat extends RichContentReceiverCompat<TextView> {
    private static final Set<String> MIME_TYPES_ALL_TEXT = Collections.singleton("text/*");

    @Override // androidx.core.widget.RichContentReceiverCompat
    public Set<String> getSupportedMimeTypes() {
        return MIME_TYPES_ALL_TEXT;
    }

    @Override // androidx.core.widget.RichContentReceiverCompat
    public boolean onReceive(TextView textView, ClipData clipData, int i, int i2) {
        CharSequence charSequenceCoerceToText;
        if (i == 1 && !supports(clipData.getDescription())) {
            return false;
        }
        Editable editable = (Editable) textView.getText();
        Context context = textView.getContext();
        boolean z = false;
        for (int i3 = 0; i3 < clipData.getItemCount(); i3++) {
            if ((i2 & 1) != 0) {
                charSequenceCoerceToText = clipData.getItemAt(i3).coerceToText(context);
                if (charSequenceCoerceToText instanceof Spanned) {
                    charSequenceCoerceToText = charSequenceCoerceToText.toString();
                }
            } else if (Build.VERSION.SDK_INT >= 16) {
                charSequenceCoerceToText = clipData.getItemAt(i3).coerceToStyledText(context);
            } else {
                charSequenceCoerceToText = clipData.getItemAt(i3).coerceToText(context);
            }
            if (charSequenceCoerceToText != null) {
                if (!z) {
                    int selectionStart = Selection.getSelectionStart(editable);
                    int selectionEnd = Selection.getSelectionEnd(editable);
                    int iMax = Math.max(0, Math.min(selectionStart, selectionEnd));
                    int iMax2 = Math.max(0, Math.max(selectionStart, selectionEnd));
                    Selection.setSelection(editable, iMax2);
                    editable.replace(iMax, iMax2, charSequenceCoerceToText);
                    z = true;
                } else {
                    editable.insert(Selection.getSelectionEnd(editable), "\n");
                    editable.insert(Selection.getSelectionEnd(editable), charSequenceCoerceToText);
                }
            }
        }
        return z;
    }
}
