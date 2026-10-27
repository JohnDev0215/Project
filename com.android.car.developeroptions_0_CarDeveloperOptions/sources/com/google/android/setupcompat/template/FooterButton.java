package com.google.android.setupcompat.template;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.PersistableBundle;
import android.util.AttributeSet;
import android.view.View;
import com.android.car.developeroptions.intelligence.LogProto$SettingsLog;
import com.google.android.setupcompat.R$styleable;
import com.google.android.setupcompat.logging.CustomEvent;

/* JADX INFO: loaded from: classes.dex */
public final class FooterButton implements View.OnClickListener {
    private OnButtonEventListener buttonListener;
    private final int buttonType;
    private int clickCount;
    private boolean enabled;
    private View.OnClickListener onClickListener;
    private View.OnClickListener onClickListenerWhenDisabled;
    private CharSequence text;
    private int theme;
    private int visibility;

    interface OnButtonEventListener {
        void onEnabledChanged(boolean z);

        void onTextChanged(CharSequence charSequence);

        void onVisibilityChanged(int i);
    }

    public FooterButton(Context context, AttributeSet attributeSet) {
        this.enabled = true;
        this.visibility = 0;
        this.clickCount = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SucFooterButton);
        this.text = typedArrayObtainStyledAttributes.getString(R$styleable.SucFooterButton_android_text);
        this.onClickListener = null;
        int i = typedArrayObtainStyledAttributes.getInt(R$styleable.SucFooterButton_sucButtonType, 0);
        getButtonTypeValue(i);
        this.buttonType = i;
        this.theme = typedArrayObtainStyledAttributes.getResourceId(R$styleable.SucFooterButton_android_theme, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    private FooterButton(CharSequence charSequence, View.OnClickListener onClickListener, int i, int i2) {
        this.enabled = true;
        this.visibility = 0;
        this.clickCount = 0;
        this.text = charSequence;
        this.onClickListener = onClickListener;
        this.buttonType = i;
        this.theme = i2;
    }

    public CharSequence getText() {
        return this.text;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public View.OnClickListener getOnClickListenerWhenDisabled() {
        return this.onClickListenerWhenDisabled;
    }

    public int getButtonType() {
        return this.buttonType;
    }

    public int getTheme() {
        return this.theme;
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
        OnButtonEventListener onButtonEventListener = this.buttonListener;
        if (onButtonEventListener != null) {
            onButtonEventListener.onEnabledChanged(z);
        }
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setVisibility(int i) {
        this.visibility = i;
        OnButtonEventListener onButtonEventListener = this.buttonListener;
        if (onButtonEventListener != null) {
            onButtonEventListener.onVisibilityChanged(i);
        }
    }

    public int getVisibility() {
        return this.visibility;
    }

    public void setText(Context context, int i) {
        setText(context.getText(i));
    }

    public void setText(CharSequence charSequence) {
        this.text = charSequence;
        OnButtonEventListener onButtonEventListener = this.buttonListener;
        if (onButtonEventListener != null) {
            onButtonEventListener.onTextChanged(charSequence);
        }
    }

    void setOnButtonEventListener(OnButtonEventListener onButtonEventListener) {
        if (onButtonEventListener != null) {
            this.buttonListener = onButtonEventListener;
            return;
        }
        throw new NullPointerException("Event listener of footer button may not be null.");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        View.OnClickListener onClickListener = this.onClickListener;
        if (onClickListener != null) {
            this.clickCount++;
            onClickListener.onClick(view);
        }
    }

    private int getButtonTypeValue(int i) {
        if (i < 0 || i > 8) {
            throw new IllegalArgumentException("Not a ButtonType");
        }
        return i;
    }

    private String getButtonTypeName() {
        switch (this.buttonType) {
            case 1:
                return "ADD_ANOTHER";
            case 2:
                return "CANCEL";
            case 3:
                return "CLEAR";
            case 4:
                return "DONE";
            case 5:
                return "NEXT";
            case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                return "OPT_IN";
            case 7:
                return "SKIP";
            case 8:
                return "STOP";
            default:
                return "OTHER";
        }
    }

    @TargetApi(29)
    public PersistableBundle getMetrics(String str) {
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString(str + "_text", CustomEvent.trimsStringOverMaxLength(getText().toString()));
        persistableBundle.putString(str + "_type", getButtonTypeName());
        persistableBundle.putInt(str + "_onClickCount", this.clickCount);
        return persistableBundle;
    }

    public static class Builder {
        private final Context context;
        private String text = "";
        private View.OnClickListener onClickListener = null;
        private int buttonType = 0;
        private int theme = 0;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setText(int i) {
            this.text = this.context.getString(i);
            return this;
        }

        public Builder setListener(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
            return this;
        }

        public Builder setButtonType(int i) {
            this.buttonType = i;
            return this;
        }

        public Builder setTheme(int i) {
            this.theme = i;
            return this;
        }

        public FooterButton build() {
            return new FooterButton(this.text, this.onClickListener, this.buttonType, this.theme);
        }
    }
}
