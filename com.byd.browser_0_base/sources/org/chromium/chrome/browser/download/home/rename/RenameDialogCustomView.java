package org.chromium.chrome.browser.download.home.rename;

import J.N;
import android.content.Context;
import android.graphics.PorterDuff;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.ScrollView;
import android.widget.TextView;
import defpackage.l10;
import defpackage.ua2;
import org.chromium.base.Callback;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.components.browser_ui.widget.text.AlertDialogEditText;

/* JADX INFO: loaded from: classes.dex */
public class RenameDialogCustomView extends ScrollView {
    public TextView e;
    public AlertDialogEditText f;
    public Callback<Boolean> g;

    public RenameDialogCustomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void setEditText(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f.setText(str);
        }
        this.f.clearFocus();
        this.f.setOnFocusChangeListener(new ua2(this, str.length() - N.MatdI239(str).length()));
        post(new l10(10, this));
    }

    private void setEditTextStyle(boolean z) {
        if (z) {
            this.f.getBackground().setColorFilter(getContext().getColor(R$color.default_red), PorterDuff.Mode.SRC_IN);
        } else {
            this.f.getBackground().clearColorFilter();
        }
    }

    private void setErrorMessageVisibility(boolean z) {
        this.e.setTextColor(getContext().getColor(R$color.default_text_color_error));
        this.e.setVisibility(z ? 0 : 8);
    }

    public final void a(String str) {
        setEditText(str);
        setEditTextStyle(false);
        setErrorMessageVisibility(false);
    }

    public final void b(int i, String str) {
        if (i == 0) {
            return;
        }
        setEditText(str);
        setEditTextStyle(true);
        setErrorMessageVisibility(true);
        if (i == 1) {
            this.e.setText(R$string.rename_failure_name_conflict);
            return;
        }
        if (i == 2) {
            this.e.setText(R$string.rename_failure_name_too_long);
        } else if (i == 3) {
            this.e.setText(R$string.rename_failure_name_invalid);
        } else {
            if (i != 4) {
                return;
            }
            this.e.setText(R$string.rename_failure_unavailable);
        }
    }

    public String getTargetName() {
        return this.f.getText().toString();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (TextView) findViewById(R$id.error_message);
        AlertDialogEditText alertDialogEditText = (AlertDialogEditText) findViewById(R$id.file_name);
        this.f = alertDialogEditText;
        alertDialogEditText.addTextChangedListener(new a());
    }

    public void setEmptyInputObserver(Callback<Boolean> callback) {
        this.g = callback;
    }

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
            RenameDialogCustomView renameDialogCustomView = RenameDialogCustomView.this;
            Callback<Boolean> callback = renameDialogCustomView.g;
            if (callback == null) {
                return;
            }
            callback.onResult(Boolean.valueOf(renameDialogCustomView.getTargetName().isEmpty()));
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }
}
