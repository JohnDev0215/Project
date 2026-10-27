package org.chromium.chrome.browser.omnibox;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import org.chromium.chrome.browser.omnibox.status.StatusView;

/* JADX INFO: compiled from: LocationBarCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a implements TextWatcher {
    public final /* synthetic */ UrlBarApi26 e;
    public final /* synthetic */ StatusView f;

    public a(UrlBarApi26 urlBarApi26, StatusView statusView) {
        this.e = urlBarApi26;
        this.f = statusView;
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        boolean zIsEmpty = TextUtils.isEmpty(charSequence);
        StatusView statusView = this.f;
        if (!zIsEmpty || this.e.isFocusable()) {
            statusView.setVisibility(0);
        } else {
            statusView.setVisibility(4);
        }
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
