package com.google.android.setupdesign.items;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import com.google.android.setupdesign.R$styleable;
import com.google.android.setupdesign.items.ItemHierarchy;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractItemHierarchy implements ItemHierarchy {
    private int id;
    private final ArrayList<ItemHierarchy.Observer> observers;

    public AbstractItemHierarchy() {
        this.observers = new ArrayList<>();
        this.id = 0;
    }

    public AbstractItemHierarchy(Context context, AttributeSet attributeSet) {
        this.observers = new ArrayList<>();
        this.id = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudAbstractItem);
        this.id = typedArrayObtainStyledAttributes.getResourceId(R$styleable.SudAbstractItem_android_id, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    public int getId() {
        return this.id;
    }

    public int getViewId() {
        return getId();
    }

    @Override // com.google.android.setupdesign.items.ItemHierarchy
    public void registerObserver(ItemHierarchy.Observer observer) {
        this.observers.add(observer);
    }

    public void notifyItemRangeChanged(int i, int i2) {
        if (i < 0) {
            Log.w("AbstractItemHierarchy", "notifyItemRangeChanged: Invalid position=" + i);
            return;
        }
        if (i2 < 0) {
            Log.w("AbstractItemHierarchy", "notifyItemRangeChanged: Invalid itemCount=" + i2);
            return;
        }
        Iterator<ItemHierarchy.Observer> it = this.observers.iterator();
        while (it.hasNext()) {
            it.next().onItemRangeChanged(this, i, i2);
        }
    }

    public void notifyItemRangeInserted(int i, int i2) {
        if (i < 0) {
            Log.w("AbstractItemHierarchy", "notifyItemRangeInserted: Invalid position=" + i);
            return;
        }
        if (i2 < 0) {
            Log.w("AbstractItemHierarchy", "notifyItemRangeInserted: Invalid itemCount=" + i2);
            return;
        }
        Iterator<ItemHierarchy.Observer> it = this.observers.iterator();
        while (it.hasNext()) {
            it.next().onItemRangeInserted(this, i, i2);
        }
    }
}
