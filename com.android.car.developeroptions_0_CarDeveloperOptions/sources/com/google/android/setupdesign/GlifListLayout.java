package com.google.android.setupdesign;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.android.setupdesign.template.ListMixin;
import com.google.android.setupdesign.template.ListViewScrollHandlingDelegate;
import com.google.android.setupdesign.template.RequireScrollMixin;

/* JADX INFO: loaded from: classes.dex */
public class GlifListLayout extends GlifLayout {
    private ListMixin listMixin;

    public GlifListLayout(Context context) {
        this(context, 0, 0);
    }

    public GlifListLayout(Context context, int i, int i2) {
        super(context, i, i2);
        init(null, 0);
    }

    public GlifListLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0);
    }

    @TargetApi(11)
    public GlifListLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i);
    }

    private void init(AttributeSet attributeSet, int i) {
        ListMixin listMixin = new ListMixin(this, attributeSet, i);
        this.listMixin = listMixin;
        registerMixin(ListMixin.class, listMixin);
        RequireScrollMixin requireScrollMixin = (RequireScrollMixin) getMixin(RequireScrollMixin.class);
        requireScrollMixin.setScrollHandlingDelegate(new ListViewScrollHandlingDelegate(requireScrollMixin, getListView()));
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.listMixin.onLayout();
    }

    @Override // com.google.android.setupdesign.GlifLayout, com.google.android.setupcompat.PartnerCustomizationLayout, com.google.android.setupcompat.internal.TemplateLayout
    protected View onInflateTemplate(LayoutInflater layoutInflater, int i) {
        if (i == 0) {
            i = R$layout.sud_glif_list_template;
        }
        return super.onInflateTemplate(layoutInflater, i);
    }

    @Override // com.google.android.setupdesign.GlifLayout, com.google.android.setupcompat.PartnerCustomizationLayout, com.google.android.setupcompat.internal.TemplateLayout
    protected ViewGroup findContainer(int i) {
        if (i == 0) {
            i = R.id.list;
        }
        return super.findContainer(i);
    }

    public ListView getListView() {
        return this.listMixin.getListView();
    }

    public void setAdapter(ListAdapter listAdapter) {
        this.listMixin.setAdapter(listAdapter);
    }

    public ListAdapter getAdapter() {
        return this.listMixin.getAdapter();
    }

    @Deprecated
    public void setDividerInset(int i) {
        this.listMixin.setDividerInset(i);
    }

    @Deprecated
    public int getDividerInset() {
        return this.listMixin.getDividerInset();
    }

    public int getDividerInsetStart() {
        return this.listMixin.getDividerInsetStart();
    }

    public int getDividerInsetEnd() {
        return this.listMixin.getDividerInsetEnd();
    }

    public Drawable getDivider() {
        return this.listMixin.getDivider();
    }
}
