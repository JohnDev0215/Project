package com.google.android.setupdesign.items;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.setupdesign.R$id;
import com.google.android.setupdesign.R$layout;
import com.google.android.setupdesign.R$styleable;
import com.google.android.setupdesign.view.CheckableLinearLayout;

/* JADX INFO: loaded from: classes.dex */
public class ExpandableSwitchItem extends SwitchItem implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    private final AccessibilityDelegateCompat accessibilityDelegate;
    private CharSequence collapsedSummary;
    private CharSequence expandedSummary;
    private boolean isExpanded;

    public ExpandableSwitchItem() {
        this.isExpanded = false;
        this.accessibilityDelegate = new AccessibilityDelegateCompat() { // from class: com.google.android.setupdesign.items.ExpandableSwitchItem.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat;
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                if (ExpandableSwitchItem.this.isExpanded()) {
                    accessibilityActionCompat = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE;
                } else {
                    accessibilityActionCompat = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND;
                }
                accessibilityNodeInfoCompat.addAction(accessibilityActionCompat);
            }

            @Override // androidx.core.view.AccessibilityDelegateCompat
            public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                if (i == 262144 || i == 524288) {
                    ExpandableSwitchItem.this.setExpanded(!r2.isExpanded());
                    return true;
                }
                return super.performAccessibilityAction(view, i, bundle);
            }
        };
        setIconGravity(48);
    }

    public ExpandableSwitchItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isExpanded = false;
        this.accessibilityDelegate = new AccessibilityDelegateCompat() { // from class: com.google.android.setupdesign.items.ExpandableSwitchItem.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat;
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                if (ExpandableSwitchItem.this.isExpanded()) {
                    accessibilityActionCompat = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE;
                } else {
                    accessibilityActionCompat = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND;
                }
                accessibilityNodeInfoCompat.addAction(accessibilityActionCompat);
            }

            @Override // androidx.core.view.AccessibilityDelegateCompat
            public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                if (i == 262144 || i == 524288) {
                    ExpandableSwitchItem.this.setExpanded(!r2.isExpanded());
                    return true;
                }
                return super.performAccessibilityAction(view, i, bundle);
            }
        };
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudExpandableSwitchItem);
        this.collapsedSummary = typedArrayObtainStyledAttributes.getText(R$styleable.SudExpandableSwitchItem_sudCollapsedSummary);
        this.expandedSummary = typedArrayObtainStyledAttributes.getText(R$styleable.SudExpandableSwitchItem_sudExpandedSummary);
        setIconGravity(typedArrayObtainStyledAttributes.getInt(R$styleable.SudItem_sudIconGravity, 48));
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // com.google.android.setupdesign.items.SwitchItem, com.google.android.setupdesign.items.Item
    protected int getDefaultLayoutResource() {
        return R$layout.sud_items_expandable_switch;
    }

    @Override // com.google.android.setupdesign.items.Item
    public CharSequence getSummary() {
        return this.isExpanded ? getExpandedSummary() : getCollapsedSummary();
    }

    public boolean isExpanded() {
        return this.isExpanded;
    }

    public void setExpanded(boolean z) {
        if (this.isExpanded == z) {
            return;
        }
        this.isExpanded = z;
        notifyItemChanged();
    }

    public CharSequence getCollapsedSummary() {
        return this.collapsedSummary;
    }

    public CharSequence getExpandedSummary() {
        return this.expandedSummary;
    }

    @Override // com.google.android.setupdesign.items.SwitchItem, com.google.android.setupdesign.items.Item, com.google.android.setupdesign.items.IItem
    public void onBindView(View view) {
        super.onBindView(view);
        View viewFindViewById = view.findViewById(R$id.sud_items_expandable_switch_content);
        viewFindViewById.setOnClickListener(this);
        if (viewFindViewById instanceof CheckableLinearLayout) {
            CheckableLinearLayout checkableLinearLayout = (CheckableLinearLayout) viewFindViewById;
            checkableLinearLayout.setChecked(isExpanded());
            ViewCompat.setAccessibilityLiveRegion(checkableLinearLayout, isExpanded() ? 1 : 0);
            ViewCompat.setAccessibilityDelegate(checkableLinearLayout, this.accessibilityDelegate);
        }
        tintCompoundDrawables(view);
        view.setFocusable(false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        setExpanded(!isExpanded());
    }

    private void tintCompoundDrawables(View view) {
        TypedArray typedArrayObtainStyledAttributes = view.getContext().obtainStyledAttributes(new int[]{R.attr.textColorPrimary});
        ColorStateList colorStateList = typedArrayObtainStyledAttributes.getColorStateList(0);
        typedArrayObtainStyledAttributes.recycle();
        if (colorStateList != null) {
            TextView textView = (TextView) view.findViewById(R$id.sud_items_title);
            for (Drawable drawable : textView.getCompoundDrawables()) {
                if (drawable != null) {
                    drawable.setColorFilter(colorStateList.getDefaultColor(), PorterDuff.Mode.SRC_IN);
                }
            }
            if (Build.VERSION.SDK_INT >= 17) {
                for (Drawable drawable2 : textView.getCompoundDrawablesRelative()) {
                    if (drawable2 != null) {
                        drawable2.setColorFilter(colorStateList.getDefaultColor(), PorterDuff.Mode.SRC_IN);
                    }
                }
            }
        }
    }
}
