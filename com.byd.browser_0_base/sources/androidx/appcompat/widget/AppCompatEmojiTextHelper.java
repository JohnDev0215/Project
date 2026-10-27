package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.R;
import defpackage.qg0;

/* JADX INFO: loaded from: classes.dex */
class AppCompatEmojiTextHelper {
    public final TextView a;
    public final qg0 b;

    public AppCompatEmojiTextHelper(TextView textView) {
        this.a = textView;
        this.b = new qg0(textView);
    }

    public final InputFilter[] a(InputFilter[] inputFilterArr) {
        return this.b.a.a(inputFilterArr);
    }

    public final void b(AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = this.a.getContext().obtainStyledAttributes(attributeSet, R.styleable.AppCompatTextView, i, 0);
        try {
            int i2 = R.styleable.AppCompatTextView_emojiCompatEnabled;
            boolean z = typedArrayObtainStyledAttributes.hasValue(i2) ? typedArrayObtainStyledAttributes.getBoolean(i2, true) : true;
            typedArrayObtainStyledAttributes.recycle();
            d(z);
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    public final void c(boolean z) {
        this.b.a.c(z);
    }

    public final void d(boolean z) {
        this.b.a.d(z);
    }
}
