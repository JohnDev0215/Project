package org.chromium.chrome.browser.password_edit_dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import org.chromium.base.Callback;
import org.chromium.chrome.R$id;

/* JADX INFO: loaded from: classes2.dex */
class UsernameSelectionConfirmationView extends PasswordEditDialogView implements AdapterView.OnItemSelectedListener {
    public Spinner f;
    public Callback<Integer> g;

    public UsernameSelectionConfirmationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // org.chromium.chrome.browser.password_edit_dialog.PasswordEditDialogView, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        Spinner spinner = (Spinner) findViewById(R$id.usernames_spinner);
        this.f = spinner;
        spinner.setOnItemSelectedListener(this);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        Callback<Integer> callback = this.g;
        if (callback != null) {
            callback.onResult(Integer.valueOf(i));
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView<?> adapterView) {
    }
}
