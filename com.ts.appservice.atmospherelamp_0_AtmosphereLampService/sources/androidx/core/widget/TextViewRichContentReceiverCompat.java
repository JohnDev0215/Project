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
    public boolean onReceive(TextView textView, ClipData clip, int source, int flags) {
        CharSequence paste;
        if (source == 1 && !supports(clip.getDescription())) {
            return false;
        }
        Editable editable = (Editable) textView.getText();
        Context context = textView.getContext();
        boolean didFirst = false;
        for (int i = 0; i < clip.getItemCount(); i++) {
            if ((flags & 1) != 0) {
                CharSequence paste2 = clip.getItemAt(i).coerceToText(context);
                paste = paste2 instanceof Spanned ? paste2.toString() : paste2;
            } else if (Build.VERSION.SDK_INT >= 16) {
                paste = clip.getItemAt(i).coerceToStyledText(context);
            } else {
                paste = clip.getItemAt(i).coerceToText(context);
            }
            if (paste != null) {
                if (!didFirst) {
                    int selStart = Selection.getSelectionStart(editable);
                    int selEnd = Selection.getSelectionEnd(editable);
                    int start = Math.max(0, Math.min(selStart, selEnd));
                    int end = Math.max(0, Math.max(selStart, selEnd));
                    Selection.setSelection(editable, end);
                    editable.replace(start, end, paste);
                    didFirst = true;
                } else {
                    editable.insert(Selection.getSelectionEnd(editable), "\n");
                    editable.insert(Selection.getSelectionEnd(editable), paste);
                }
            }
        }
        return didFirst;
    }
}
