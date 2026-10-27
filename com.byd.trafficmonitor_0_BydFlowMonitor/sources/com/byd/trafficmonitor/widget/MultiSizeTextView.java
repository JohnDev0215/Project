package com.byd.trafficmonitor.widget;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.bydux.api.R;

/* JADX INFO: loaded from: classes.dex */
public class MultiSizeTextView extends AppCompatTextView {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4440f;

    public MultiSizeTextView(Context context) {
        this(context, null);
    }

    public final CharSequence f(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence) || getContext() == null) {
            return charSequence;
        }
        Context context = getContext();
        SpannableString spannableString = new SpannableString(charSequence);
        this.f4440f = Math.max(this.f4440f, 2);
        int length = charSequence.length() - this.f4440f;
        spannableString.setSpan(new ForegroundColorSpan(context.getColor(R.color.flow_sub_title_color)), 0, length, 17);
        spannableString.setSpan(new ForegroundColorSpan(context.getColor(R.color.flow_sub_title_content_color)), length, charSequence.length(), 34);
        spannableString.setSpan(new RelativeSizeSpan(0.5555556f), length, charSequence.length(), 34);
        return spannableString;
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        super.setText(f(charSequence), bufferType);
    }

    public MultiSizeTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MultiSizeTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
