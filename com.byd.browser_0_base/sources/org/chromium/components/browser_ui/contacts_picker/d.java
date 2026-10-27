package org.chromium.components.browser_ui.contacts_picker;

import androidx.appcompat.app.AlertDialog;
import defpackage.c12;
import defpackage.hy;
import org.chromium.chrome.browser.contacts_picker.ChromePickerAdapter;
import org.chromium.components.browser_ui.contacts_picker.ContactsPickerToolbar;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: ContactsPickerDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d extends AlertDialog implements ContactsPickerToolbar.a {
    public d(WindowAndroid windowAndroid, ChromePickerAdapter chromePickerAdapter, hy hyVar, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, String str) {
        super(windowAndroid.i.get(), R$style.ThemeOverlay_BrowserUI_Fullscreen);
        PickerCategoryView pickerCategoryView = new PickerCategoryView(windowAndroid, chromePickerAdapter, z, z2, z3, z4, z5, z6, str, this);
        pickerCategoryView.f = this;
        pickerCategoryView.h = hyVar;
        setOnCancelListener(new c12(0, pickerCategoryView));
        pickerCategoryView.k.o();
        h(pickerCategoryView);
    }
}
