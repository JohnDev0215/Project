package org.chromium.chrome.browser.password_edit_dialog;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.R$id;

/* JADX INFO: loaded from: classes2.dex */
class PasswordEditDialogWithDetailsView extends PasswordEditDialogView {
    public static final /* synthetic */ int m = 0;
    public AutoCompleteTextView f;
    public TextInputLayout g;
    public TextInputEditText h;
    public TextInputLayout i;
    public Callback<String> j;
    public Callback<String> k;
    public List<String> l;

    public PasswordEditDialogWithDetailsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // org.chromium.chrome.browser.password_edit_dialog.PasswordEditDialogView
    public final void a(String str) {
        if (this.h.getText().toString().equals(str)) {
            return;
        }
        this.h.setText(str);
    }

    @Override // org.chromium.chrome.browser.password_edit_dialog.PasswordEditDialogView
    public final void b(Callback<String> callback) {
        this.k = callback;
    }

    @Override // org.chromium.chrome.browser.password_edit_dialog.PasswordEditDialogView
    public final void c(String str) {
        this.i.setError(str);
    }

    public final void d(String str) {
        List<String> list = this.l;
        if (list != null && (list.size() > 1 || (this.l.size() == 1 && !this.l.get(0).equals(str)))) {
            this.g.setEndIconVisible(true);
        } else {
            this.g.setEndIconVisible(false);
            this.f.dismissDropDown();
        }
    }

    @Override // org.chromium.chrome.browser.password_edit_dialog.PasswordEditDialogView, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.f = (AutoCompleteTextView) findViewById(R$id.username_view);
        TextInputLayout textInputLayout = (TextInputLayout) findViewById(R$id.username_input_layout);
        this.g = textInputLayout;
        textInputLayout.setEndIconOnClickListener(new View.OnClickListener() { // from class: org.chromium.chrome.browser.password_edit_dialog.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.e.f.showDropDown();
            }
        });
        this.f.addTextChangedListener(new a());
        TextInputEditText textInputEditText = (TextInputEditText) findViewById(R$id.password);
        this.h = textInputEditText;
        textInputEditText.setInputType(131201);
        this.h.addTextChangedListener(new b());
        this.i = (TextInputLayout) findViewById(R$id.password_text_input_layout);
    }

    public class b implements TextWatcher {
        public b() {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            Callback<String> callback = PasswordEditDialogWithDetailsView.this.k;
            if (callback == null) {
                return;
            }
            callback.onResult(charSequence.toString());
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
            String string = editable.toString();
            int i = PasswordEditDialogWithDetailsView.m;
            PasswordEditDialogWithDetailsView.this.d(string);
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            Callback<String> callback = PasswordEditDialogWithDetailsView.this.j;
            if (callback == null) {
                return;
            }
            callback.onResult(charSequence.toString());
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }
}
