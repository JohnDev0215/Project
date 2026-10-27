package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.TextView;
import defpackage.pm;
import defpackage.xa;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class RadioButtonWithEditText extends RadioButtonWithDescription {
    public static final /* synthetic */ int m = 0;
    public EditText k;
    public final ArrayList l;

    public interface b {
        void a(CharSequence charSequence);
    }

    public RadioButtonWithEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = new ArrayList();
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescription
    public final void a(AttributeSet attributeSet) {
        super.a(attributeSet);
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R$styleable.RadioButtonWithEditText, 0, 0);
        String string = typedArrayObtainStyledAttributes.getString(R$styleable.RadioButtonWithEditText_android_hint);
        if (string != null) {
            setHint(string);
        }
        setInputType(typedArrayObtainStyledAttributes.getInt(R$styleable.RadioButtonWithEditText_android_inputType, 1));
        typedArrayObtainStyledAttributes.recycle();
    }

    public void addTextChangeListener(b bVar) {
        this.l.add(bVar);
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescription
    public final void c() {
        super.c();
        EditText editText = (EditText) getPrimaryTextView();
        this.k = editText;
        editText.addTextChangedListener(new a());
        this.k.setOnEditorActionListener(new pm(1, this));
        this.k.setOnFocusChangeListener(new xa(2, this));
    }

    public EditText getEditTextForTests() {
        return this.k;
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescription
    public int getLayoutResource() {
        return R$layout.radio_button_with_edit_text;
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescription
    public TextView getPrimaryTextView() {
        return (TextView) findViewById(R$id.edit_text);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setLabeledBy(this.k);
    }

    public void removeTextChangeListener(b bVar) {
        this.l.remove(bVar);
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescription
    public void setChecked(boolean z) {
        super.setChecked(z);
        this.k.clearFocus();
    }

    public void setHint(CharSequence charSequence) {
        this.k.setHint(charSequence);
    }

    public void setInputType(int i) {
        this.k.setInputType(i);
    }

    public void setHint(int i) {
        this.k.setHint(i);
    }

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
            ArrayList arrayList = RadioButtonWithEditText.this.l;
            if (arrayList == null) {
                return;
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((b) it.next()).a(editable);
            }
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }
}
