package defpackage;

import android.text.Editable;
import android.view.accessibility.AccessibilityEvent;

/* JADX INFO: compiled from: AutocompleteEditTextModelBase.java */
/* JADX INFO: loaded from: classes2.dex */
public interface ia {
    void append(CharSequence charSequence);

    void b();

    Editable getEditableText();

    int getHighlightColor();

    String getKeyboardPackageName();

    int getSelectionEnd();

    int getSelectionStart();

    Editable getText();

    boolean isFocused();

    void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent);

    void setCursorVisible(boolean z);
}
