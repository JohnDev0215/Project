package org.chromium.components.autofill.prefeditor;

import android.R;
import android.app.Activity;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import com.google.android.material.textfield.TextInputLayout;
import defpackage.bg0;
import defpackage.gg0;
import defpackage.hg0;
import defpackage.ig0;
import defpackage.pf0;
import defpackage.qf0;
import defpackage.xf0;
import defpackage.yf0;
import java.util.ArrayList;
import org.chromium.components.autofill.R$id;
import org.chromium.components.autofill.R$layout;
import org.chromium.components.autofill.prefeditor.EditorTextField;

/* JADX INFO: loaded from: classes2.dex */
public class EditorTextField extends FrameLayout implements yf0, View.OnClickListener {
    public static final /* synthetic */ int j = 0;
    public final xf0 e;
    public final TextInputLayout f;
    public final AutoCompleteTextView g;
    public final View h;
    public boolean i;

    public EditorTextField(Activity activity, xf0 xf0Var, pf0 pf0Var, qf0 qf0Var, TextWatcher textWatcher, boolean z) {
        super(activity);
        this.e = xf0Var;
        LayoutInflater.from(activity).inflate(R$layout.payments_request_editor_textview, (ViewGroup) this, true);
        TextInputLayout textInputLayout = (TextInputLayout) findViewById(R$id.text_input_layout);
        this.f = textInputLayout;
        CharSequence charSequence = xf0Var.m;
        if ((!TextUtils.isEmpty(xf0Var.i)) && z) {
            charSequence = ((Object) charSequence) + "*";
        }
        textInputLayout.setHint(charSequence);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) textInputLayout.findViewById(R$id.text_view);
        this.g = autoCompleteTextView;
        autoCompleteTextView.setText(xf0Var.n);
        autoCompleteTextView.setContentDescription(charSequence);
        autoCompleteTextView.setOnEditorActionListener(pf0Var);
        autoCompleteTextView.setOnKeyListener(new View.OnKeyListener() { // from class: fg0
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                int i2 = EditorTextField.j;
                if (i != 23 || keyEvent.getAction() != 1) {
                    return false;
                }
                InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method");
                inputMethodManager.viewClicked(view);
                inputMethodManager.showSoftInput(view, 0);
                return true;
            }
        });
        View viewFindViewById = findViewById(R$id.icons_layer);
        this.h = viewFindViewById;
        viewFindViewById.addOnLayoutChangeListener(new gg0(this));
        autoCompleteTextView.setOnFocusChangeListener(new hg0(this));
        autoCompleteTextView.addTextChangedListener(new ig0(this, xf0Var));
        ArrayList arrayList = xf0Var.f;
        if (arrayList != null && !arrayList.isEmpty()) {
            autoCompleteTextView.setAdapter(new ArrayAdapter(getContext(), R.layout.simple_spinner_dropdown_item, xf0Var.f));
            autoCompleteTextView.setThreshold(0);
        }
        ArrayList arrayList2 = new ArrayList();
        if (qf0Var != null) {
            arrayList2.add(qf0Var);
        }
        if (xf0Var.r != 0) {
            arrayList2.add(new InputFilter.LengthFilter(xf0Var.r));
            textInputLayout.setCounterMaxLength(xf0Var.r);
        }
        InputFilter[] inputFilterArr = new InputFilter[arrayList2.size()];
        arrayList2.toArray(inputFilterArr);
        autoCompleteTextView.setFilters(inputFilterArr);
        if (textWatcher != null) {
            autoCompleteTextView.addTextChangedListener(textWatcher);
            textWatcher.afterTextChanged(autoCompleteTextView.getText());
        }
        switch (xf0Var.a) {
            case 1:
            case 7:
                autoCompleteTextView.setInputType(3);
                break;
            case 2:
                autoCompleteTextView.setInputType(33);
                break;
            case 3:
                autoCompleteTextView.setInputType(139377);
                break;
            case 4:
                autoCompleteTextView.setInputType(8289);
                break;
            case 5:
            case 6:
                autoCompleteTextView.setInputType(4209);
                break;
            case 8:
                autoCompleteTextView.setInputType(2);
                break;
            case 9:
                autoCompleteTextView.setInputType(129);
                textInputLayout.setPasswordVisibilityToggleEnabled(true);
                break;
            case 10:
                autoCompleteTextView.setInputType(18);
                textInputLayout.setPasswordVisibilityToggleEnabled(true);
                break;
            default:
                autoCompleteTextView.setInputType(8305);
                break;
        }
    }

    @Override // defpackage.yf0
    public final void a() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup != null) {
            viewGroup.requestChildFocus(this, this);
        }
        requestFocus();
        sendAccessibilityEvent(8);
    }

    @Override // defpackage.yf0
    public final boolean b() {
        return !TextUtils.isEmpty(this.e.i);
    }

    @Override // defpackage.yf0
    public final void c(boolean z) {
        this.f.setError(z ? this.e.l : null);
    }

    public AutoCompleteTextView getEditText() {
        return this.g;
    }

    public xf0 getFieldModel() {
        return this.e;
    }

    @Override // defpackage.yf0
    public final boolean isValid() {
        return this.e.d();
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.e.getClass();
        throw null;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            float y = this.g.getY() + this.f.getY() + r2.getHeight();
            this.h.setTranslationY((y - r0.getHeight()) - r0.getTop());
        }
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    public static void setEditorObserverForTest(bg0 bg0Var) {
    }
}
