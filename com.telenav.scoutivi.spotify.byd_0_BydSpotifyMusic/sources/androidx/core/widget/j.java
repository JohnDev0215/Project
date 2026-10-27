package androidx.core.widget;

import android.content.ClipData;
import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import i0.q;

/* JADX INFO: compiled from: TextViewOnReceiveContentListener.java */
/* JADX INFO: loaded from: classes.dex */
public final class j implements q {

    /* JADX INFO: compiled from: TextViewOnReceiveContentListener.java */
    private static final class a {
        static CharSequence a(Context context, ClipData.Item item, int i7) {
            if ((i7 & 1) == 0) {
                return item.coerceToStyledText(context);
            }
            CharSequence charSequenceCoerceToText = item.coerceToText(context);
            return charSequenceCoerceToText instanceof Spanned ? charSequenceCoerceToText.toString() : charSequenceCoerceToText;
        }
    }

    private static CharSequence b(ClipData clipData, Context context, int i7) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        for (int i8 = 0; i8 < clipData.getItemCount(); i8++) {
            CharSequence charSequenceC = c(context, clipData.getItemAt(i8), i7);
            if (charSequenceC != null) {
                spannableStringBuilder.append(charSequenceC);
            }
        }
        return spannableStringBuilder;
    }

    private static CharSequence c(Context context, ClipData.Item item, int i7) {
        return a.a(context, item, i7);
    }

    private static void d(TextView textView, i0.c cVar) {
        e((Editable) textView.getText(), b(cVar.b(), textView.getContext(), cVar.c()));
    }

    private static void e(Editable editable, CharSequence charSequence) {
        int selectionStart = Selection.getSelectionStart(editable);
        int selectionEnd = Selection.getSelectionEnd(editable);
        int iMax = Math.max(0, Math.min(selectionStart, selectionEnd));
        int iMax2 = Math.max(0, Math.max(selectionStart, selectionEnd));
        Selection.setSelection(editable, iMax2);
        editable.replace(iMax, iMax2, charSequence);
    }

    @Override // i0.q
    public i0.c a(View view, i0.c cVar) {
        if (Log.isLoggable("ReceiveContent", 3)) {
            Log.d("ReceiveContent", "onReceive: " + cVar);
        }
        int iD = cVar.d();
        if (iD == 2) {
            return cVar;
        }
        if (iD == 3) {
            d((TextView) view, cVar);
            return null;
        }
        ClipData clipDataB = cVar.b();
        int iC = cVar.c();
        TextView textView = (TextView) view;
        Editable editable = (Editable) textView.getText();
        Context context = textView.getContext();
        boolean z6 = false;
        for (int i7 = 0; i7 < clipDataB.getItemCount(); i7++) {
            CharSequence charSequenceC = c(context, clipDataB.getItemAt(i7), iC);
            if (charSequenceC != null) {
                if (z6) {
                    editable.insert(Selection.getSelectionEnd(editable), "\n");
                    editable.insert(Selection.getSelectionEnd(editable), charSequenceC);
                } else {
                    e(editable, charSequenceC);
                    z6 = true;
                }
            }
        }
        return null;
    }
}
