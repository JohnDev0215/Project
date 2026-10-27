package com.byd.vrsettings.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

/* JADX INFO: loaded from: classes.dex */
public class AppTextView extends AppCompatTextView implements LanguageView {
    String ANDROIDXML;
    private int arrResId;
    private int arrResIndex;
    private int hintId;
    private int textId;

    public AppTextView(Context context) {
        super(context);
        this.ANDROIDXML = "http://schemas.android.com/apk/res/android";
        init(context, null);
    }

    public AppTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.ANDROIDXML = "http://schemas.android.com/apk/res/android";
        init(context, attributeSet);
    }

    public AppTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.ANDROIDXML = "http://schemas.android.com/apk/res/android";
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            String attributeValue = attributeSet.getAttributeValue(this.ANDROIDXML, "text");
            if (attributeValue != null && attributeValue.length() >= 2 && attributeValue.startsWith("@")) {
                this.textId = Integer.parseInt(attributeValue.substring(1, attributeValue.length()));
            }
            String attributeValue2 = attributeSet.getAttributeValue(this.ANDROIDXML, "hint");
            if (attributeValue2 == null || attributeValue2.length() < 2) {
                return;
            }
            this.hintId = Integer.parseInt(attributeValue2.substring(1, attributeValue2.length()));
        }
    }

    @Override // com.byd.vrsettings.view.LanguageView
    public void setTextById(int i) {
        this.textId = i;
        setText(i);
    }

    @Override // com.byd.vrsettings.view.LanguageView
    public void setTextWithString(String str) {
        this.textId = 0;
        setText(str);
    }

    @Override // com.byd.vrsettings.view.LanguageView
    public void setTextByArrayAndIndex(int i, int i2) {
        this.arrResId = i;
        this.arrResIndex = i2;
        setText(getContext().getResources().getStringArray(i)[i2]);
    }

    @Override // com.byd.vrsettings.view.LanguageView
    public void reLoadLanguage() {
        try {
            int i = this.textId;
            if (i > 0) {
                setText(i);
            } else if (this.arrResId > 0) {
                setText(getContext().getResources().getStringArray(this.arrResId)[this.arrResIndex]);
            }
            int i2 = this.hintId;
            if (i2 > 0) {
                setHint(i2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
