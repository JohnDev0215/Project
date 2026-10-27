package com.google.android.setupdesign.template;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.android.setupcompat.internal.TemplateLayout;
import com.google.android.setupcompat.template.Mixin;
import com.google.android.setupdesign.R$styleable;
import com.google.android.setupdesign.items.ItemAdapter;
import com.google.android.setupdesign.items.ItemGroup;
import com.google.android.setupdesign.items.ItemInflater;
import com.google.android.setupdesign.util.DrawableLayoutDirectionHelper;

/* JADX INFO: loaded from: classes.dex */
public class ListMixin implements Mixin {
    private Drawable defaultDivider;
    private Drawable divider;
    private int dividerInsetEnd;
    private int dividerInsetStart;
    private ListView listView;
    private final TemplateLayout templateLayout;

    public ListMixin(TemplateLayout templateLayout, AttributeSet attributeSet, int i) {
        this.templateLayout = templateLayout;
        Context context = templateLayout.getContext();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudListMixin, i, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.SudListMixin_android_entries, 0);
        if (resourceId != 0) {
            setAdapter(new ItemAdapter((ItemGroup) new ItemInflater(context).inflate(resourceId)));
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SudListMixin_sudDividerInset, -1);
        if (dimensionPixelSize != -1) {
            setDividerInset(dimensionPixelSize);
        } else {
            setDividerInsets(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SudListMixin_sudDividerInsetStart, 0), typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SudListMixin_sudDividerInsetEnd, 0));
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public ListView getListView() {
        return getListViewInternal();
    }

    private ListView getListViewInternal() {
        if (this.listView == null) {
            View viewFindManagedViewById = this.templateLayout.findManagedViewById(R.id.list);
            if (viewFindManagedViewById instanceof ListView) {
                this.listView = (ListView) viewFindManagedViewById;
            }
        }
        return this.listView;
    }

    public void onLayout() {
        if (this.divider == null) {
            updateDivider();
        }
    }

    public ListAdapter getAdapter() {
        ListView listViewInternal = getListViewInternal();
        if (listViewInternal == null) {
            return null;
        }
        ListAdapter adapter = listViewInternal.getAdapter();
        return adapter instanceof HeaderViewListAdapter ? ((HeaderViewListAdapter) adapter).getWrappedAdapter() : adapter;
    }

    public void setAdapter(ListAdapter listAdapter) {
        ListView listViewInternal = getListViewInternal();
        if (listViewInternal != null) {
            listViewInternal.setAdapter(listAdapter);
        }
    }

    @Deprecated
    public void setDividerInset(int i) {
        setDividerInsets(i, 0);
    }

    public void setDividerInsets(int i, int i2) {
        this.dividerInsetStart = i;
        this.dividerInsetEnd = i2;
        updateDivider();
    }

    @Deprecated
    public int getDividerInset() {
        return getDividerInsetStart();
    }

    public int getDividerInsetStart() {
        return this.dividerInsetStart;
    }

    public int getDividerInsetEnd() {
        return this.dividerInsetEnd;
    }

    private void updateDivider() {
        ListView listViewInternal = getListViewInternal();
        if (listViewInternal == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19 ? this.templateLayout.isLayoutDirectionResolved() : true) {
            if (this.defaultDivider == null) {
                this.defaultDivider = listViewInternal.getDivider();
            }
            Drawable drawable = this.defaultDivider;
            if (drawable != null) {
                InsetDrawable insetDrawableCreateRelativeInsetDrawable = DrawableLayoutDirectionHelper.createRelativeInsetDrawable(drawable, this.dividerInsetStart, 0, this.dividerInsetEnd, 0, this.templateLayout);
                this.divider = insetDrawableCreateRelativeInsetDrawable;
                listViewInternal.setDivider(insetDrawableCreateRelativeInsetDrawable);
            }
        }
    }

    public Drawable getDivider() {
        return this.divider;
    }
}
