package com.google.android.setupdesign.items;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.android.setupdesign.R$layout;
import com.google.android.setupdesign.R$style;
import com.google.android.setupdesign.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class ButtonItem extends AbstractItem implements View.OnClickListener {
    private Button button;
    private boolean enabled;
    private OnClickListener listener;
    private CharSequence text;
    private int theme;

    public interface OnClickListener {
        void onClick(ButtonItem buttonItem);
    }

    @Override // com.google.android.setupdesign.items.AbstractItem, com.google.android.setupdesign.items.ItemHierarchy
    public int getCount() {
        return 0;
    }

    @Override // com.google.android.setupdesign.items.IItem
    public int getLayoutResource() {
        return 0;
    }

    public ButtonItem() {
        this.enabled = true;
        this.theme = R$style.SudButtonItem;
    }

    public ButtonItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.enabled = true;
        this.theme = R$style.SudButtonItem;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudButtonItem);
        this.enabled = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SudButtonItem_android_enabled, true);
        this.text = typedArrayObtainStyledAttributes.getText(R$styleable.SudButtonItem_android_text);
        this.theme = typedArrayObtainStyledAttributes.getResourceId(R$styleable.SudButtonItem_android_theme, R$style.SudButtonItem);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // com.google.android.setupdesign.items.IItem
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override // com.google.android.setupdesign.items.IItem
    public final void onBindView(View view) {
        throw new UnsupportedOperationException("Cannot bind to ButtonItem's view");
    }

    protected Button createButton(ViewGroup viewGroup) {
        Button button = this.button;
        if (button == null) {
            Context context = viewGroup.getContext();
            if (this.theme != 0) {
                context = new ContextThemeWrapper(context, this.theme);
            }
            Button buttonCreateButton = createButton(context);
            this.button = buttonCreateButton;
            buttonCreateButton.setOnClickListener(this);
        } else if (button.getParent() instanceof ViewGroup) {
            ((ViewGroup) this.button.getParent()).removeView(this.button);
        }
        this.button.setEnabled(this.enabled);
        this.button.setText(this.text);
        this.button.setId(getViewId());
        return this.button;
    }

    @SuppressLint({"InflateParams"})
    private Button createButton(Context context) {
        return (Button) LayoutInflater.from(context).inflate(R$layout.sud_button, (ViewGroup) null, false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnClickListener onClickListener = this.listener;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }
}
