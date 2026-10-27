package com.android.launcher3.anim;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class PropertyListBuilder {
    private final ArrayList<PropertyValuesHolder> mProperties = new ArrayList<>();

    public PropertyListBuilder translationX(float value) {
        this.mProperties.add(PropertyValuesHolder.ofFloat(View.TRANSLATION_X, value));
        return this;
    }

    public PropertyListBuilder translationY(float value) {
        this.mProperties.add(PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, value));
        return this;
    }

    public PropertyListBuilder scaleX(float value) {
        this.mProperties.add(PropertyValuesHolder.ofFloat(View.SCALE_X, value));
        return this;
    }

    public PropertyListBuilder scaleY(float value) {
        this.mProperties.add(PropertyValuesHolder.ofFloat(View.SCALE_Y, value));
        return this;
    }

    public PropertyListBuilder scale(float value) {
        return scaleX(value).scaleY(value);
    }

    public PropertyListBuilder alpha(float value) {
        this.mProperties.add(PropertyValuesHolder.ofFloat(View.ALPHA, value));
        return this;
    }

    public ObjectAnimator build(View view) {
        ArrayList<PropertyValuesHolder> arrayList = this.mProperties;
        return ObjectAnimator.ofPropertyValuesHolder(view, (PropertyValuesHolder[]) arrayList.toArray(new PropertyValuesHolder[arrayList.size()]));
    }
}
