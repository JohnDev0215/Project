package androidx.autofill.inline.common;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class TextViewStyle extends ViewStyle {
    @Override // androidx.autofill.inline.common.ViewStyle, androidx.autofill.inline.common.BundledStyle
    protected String getStyleKey() {
        return "text_view_style";
    }

    public TextViewStyle(Bundle bundle) {
        super(bundle);
    }

    public void applyStyleOnTextViewIfValid(TextView textView) {
        if (isValid()) {
            super.applyStyleOnViewIfValid(textView);
            if (this.mBundle.containsKey("text_color")) {
                textView.setTextColor(this.mBundle.getInt("text_color"));
            }
            if (this.mBundle.containsKey("text_size")) {
                textView.setTextSize(this.mBundle.containsKey("text_size_unit") ? this.mBundle.getInt("text_size_unit") : 2, this.mBundle.getFloat("text_size"));
            }
            if (this.mBundle.containsKey("text_font_family")) {
                String string = this.mBundle.getString("text_font_family");
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                textView.setTypeface(Typeface.create(string, this.mBundle.getInt("text_font_style")));
            }
        }
    }
}
