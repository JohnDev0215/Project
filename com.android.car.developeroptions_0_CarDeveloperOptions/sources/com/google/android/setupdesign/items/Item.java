package com.google.android.setupdesign.items;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.setupdesign.R$id;
import com.google.android.setupdesign.R$layout;
import com.google.android.setupdesign.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class Item extends AbstractItem {
    private boolean enabled;
    private Drawable icon;
    private int iconGravity;
    private int iconTint;
    private int layoutRes;
    private CharSequence summary;
    private CharSequence title;
    private boolean visible;

    public Item() {
        this.enabled = true;
        this.visible = true;
        this.iconTint = 0;
        this.iconGravity = 16;
        this.layoutRes = getDefaultLayoutResource();
    }

    public Item(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.enabled = true;
        this.visible = true;
        this.iconTint = 0;
        this.iconGravity = 16;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudItem);
        this.enabled = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SudItem_android_enabled, true);
        this.icon = typedArrayObtainStyledAttributes.getDrawable(R$styleable.SudItem_android_icon);
        this.title = typedArrayObtainStyledAttributes.getText(R$styleable.SudItem_android_title);
        this.summary = typedArrayObtainStyledAttributes.getText(R$styleable.SudItem_android_summary);
        this.layoutRes = typedArrayObtainStyledAttributes.getResourceId(R$styleable.SudItem_android_layout, getDefaultLayoutResource());
        this.visible = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SudItem_android_visible, true);
        this.iconTint = typedArrayObtainStyledAttributes.getColor(R$styleable.SudItem_sudIconTint, 0);
        this.iconGravity = typedArrayObtainStyledAttributes.getInt(R$styleable.SudItem_sudIconGravity, 16);
        typedArrayObtainStyledAttributes.recycle();
    }

    protected int getDefaultLayoutResource() {
        return R$layout.sud_items_default;
    }

    @Override // com.google.android.setupdesign.items.AbstractItem, com.google.android.setupdesign.items.ItemHierarchy
    public int getCount() {
        return isVisible() ? 1 : 0;
    }

    @Override // com.google.android.setupdesign.items.IItem
    public boolean isEnabled() {
        return this.enabled;
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public void setIconGravity(int i) {
        this.iconGravity = i;
    }

    @Override // com.google.android.setupdesign.items.IItem
    public int getLayoutResource() {
        return this.layoutRes;
    }

    public CharSequence getSummary() {
        return this.summary;
    }

    public CharSequence getTitle() {
        return this.title;
    }

    public boolean isVisible() {
        return this.visible;
    }

    @Override // com.google.android.setupdesign.items.AbstractItemHierarchy
    public int getViewId() {
        return getId();
    }

    public void onBindView(View view) {
        ((TextView) view.findViewById(R$id.sud_items_title)).setText(getTitle());
        TextView textView = (TextView) view.findViewById(R$id.sud_items_summary);
        CharSequence summary = getSummary();
        if (summary != null && summary.length() > 0) {
            textView.setText(summary);
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
        View viewFindViewById = view.findViewById(R$id.sud_items_icon_container);
        Drawable icon = getIcon();
        if (icon != null) {
            ImageView imageView = (ImageView) view.findViewById(R$id.sud_items_icon);
            imageView.setImageDrawable(null);
            onMergeIconStateAndLevels(imageView, icon);
            imageView.setImageDrawable(icon);
            int i = this.iconTint;
            if (i != 0) {
                imageView.setColorFilter(i);
            } else {
                imageView.clearColorFilter();
            }
            ViewGroup.LayoutParams layoutParams = viewFindViewById.getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                ((LinearLayout.LayoutParams) layoutParams).gravity = this.iconGravity;
            }
            viewFindViewById.setVisibility(0);
        } else {
            viewFindViewById.setVisibility(8);
        }
        view.setId(getViewId());
    }

    protected void onMergeIconStateAndLevels(ImageView imageView, Drawable drawable) {
        imageView.setImageState(drawable.getState(), false);
        imageView.setImageLevel(drawable.getLevel());
    }
}
