package org.chromium.chrome.browser.bookmarks;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import com.google.android.material.textfield.TextInputLayout;
import defpackage.bk3;
import org.chromium.chrome.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class BookmarkTextInputLayout extends TextInputLayout {
    public final String E0;
    public final String F0;

    public BookmarkTextInputLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BookmarkTextInputLayout);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.BookmarkTextInputLayout_emptyErrorMessage, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.BookmarkTextInputLayout_urlErrorMessage, 0);
        if (resourceId != 0) {
            this.E0 = context.getResources().getString(resourceId);
        }
        if (resourceId2 != 0) {
            this.F0 = context.getResources().getString(resourceId2);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public String getTrimmedText() {
        return getEditText().getText().toString().trim();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        getEditText().addTextChangedListener(new a());
    }

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
            BookmarkTextInputLayout bookmarkTextInputLayout = BookmarkTextInputLayout.this;
            if (TextUtils.isEmpty(bookmarkTextInputLayout.getTrimmedText())) {
                String str = bookmarkTextInputLayout.E0;
                if (str != null) {
                    bookmarkTextInputLayout.setError(str);
                    return;
                }
                return;
            }
            String str2 = bookmarkTextInputLayout.F0;
            if (str2 == null || bk3.o(bookmarkTextInputLayout.getTrimmedText())) {
                bookmarkTextInputLayout.setError(null);
            } else {
                bookmarkTextInputLayout.setError(str2);
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
