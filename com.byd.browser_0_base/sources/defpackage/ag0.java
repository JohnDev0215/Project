package defpackage;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;

/* JADX INFO: compiled from: EditorInfoCompat.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"PrivateConstructorForUtilityClass"})
public final class ag0 {
    public static void a(EditorInfo editorInfo, CharSequence charSequence) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 30) {
            editorInfo.setInitialSurroundingSubText(charSequence, 0);
            return;
        }
        charSequence.getClass();
        if (i >= 30) {
            editorInfo.setInitialSurroundingSubText(charSequence, 0);
            return;
        }
        int i2 = editorInfo.initialSelStart;
        int i3 = editorInfo.initialSelEnd;
        int i4 = i2 > i3 ? i3 + 0 : i2 + 0;
        int i5 = i2 > i3 ? i2 - 0 : i3 + 0;
        int length = charSequence.length();
        if (i4 < 0 || i5 > length) {
            b(editorInfo, null, 0, 0);
            return;
        }
        int i6 = editorInfo.inputType & 4095;
        if (i6 == 129 || i6 == 225 || i6 == 18) {
            b(editorInfo, null, 0, 0);
            return;
        }
        if (length <= 2048) {
            b(editorInfo, charSequence, i4, i5);
            return;
        }
        int i7 = i5 - i4;
        int i8 = i7 > 1024 ? 0 : i7;
        int i9 = 2048 - i8;
        int iMin = Math.min(charSequence.length() - i5, i9 - Math.min(i4, (int) (((double) i9) * 0.8d)));
        int iMin2 = Math.min(i4, i9 - iMin);
        int i10 = i4 - iMin2;
        if (Character.isLowSurrogate(charSequence.charAt(i10))) {
            i10++;
            iMin2--;
        }
        if (Character.isHighSurrogate(charSequence.charAt((i5 + iMin) - 1))) {
            iMin--;
        }
        CharSequence charSequenceConcat = i8 != i7 ? TextUtils.concat(charSequence.subSequence(i10, i10 + iMin2), charSequence.subSequence(i5, iMin + i5)) : charSequence.subSequence(i10, iMin2 + i8 + iMin + i10);
        int i11 = iMin2 + 0;
        b(editorInfo, charSequenceConcat, i11, i8 + i11);
    }

    public static void b(EditorInfo editorInfo, CharSequence charSequence, int i, int i2) {
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        editorInfo.extras.putCharSequence("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SURROUNDING_TEXT", charSequence != null ? new SpannableStringBuilder(charSequence) : null);
        editorInfo.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_HEAD", i);
        editorInfo.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_END", i2);
    }
}
