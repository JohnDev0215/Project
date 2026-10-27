package defpackage;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import org.chromium.chrome.browser.bookmarks.BookMarkSelectableListToolbar;

/* JADX INFO: compiled from: BookMarkSelectableListToolbar.java */
/* JADX INFO: loaded from: classes.dex */
public final class mf implements TextWatcher {
    public final /* synthetic */ BookMarkSelectableListToolbar e;

    public mf(BookMarkSelectableListToolbar bookMarkSelectableListToolbar) {
        this.e = bookMarkSelectableListToolbar;
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        BookMarkSelectableListToolbar bookMarkSelectableListToolbar = this.e;
        bookMarkSelectableListToolbar.h0.setVisibility(TextUtils.isEmpty(charSequence) ? 4 : 0);
        if (bookMarkSelectableListToolbar.B()) {
            bookMarkSelectableListToolbar.i0.r(charSequence.toString());
        }
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
