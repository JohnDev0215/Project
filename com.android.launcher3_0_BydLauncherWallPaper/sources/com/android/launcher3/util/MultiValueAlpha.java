package com.android.launcher3.util;

import android.util.Property;
import android.view.View;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class MultiValueAlpha {
    public static final Property<AlphaProperty, Float> VALUE = new Property<AlphaProperty, Float>(Float.TYPE, "value") { // from class: com.android.launcher3.util.MultiValueAlpha.1
        @Override // android.util.Property
        public Float get(AlphaProperty alphaProperty) {
            return Float.valueOf(alphaProperty.mValue);
        }

        @Override // android.util.Property
        public void set(AlphaProperty object, Float value) {
            object.setValue(value.floatValue());
        }
    };
    private final AlphaProperty[] mMyProperties;
    private int mValidMask = 0;
    private final View mView;

    public MultiValueAlpha(View view, int size) {
        this.mView = view;
        this.mMyProperties = new AlphaProperty[size];
        for (int i = 0; i < size; i++) {
            int i2 = 1 << i;
            this.mValidMask |= i2;
            this.mMyProperties[i] = new AlphaProperty(i2);
        }
    }

    public String toString() {
        return Arrays.toString(this.mMyProperties);
    }

    public AlphaProperty getProperty(int index) {
        return this.mMyProperties[index];
    }

    public class AlphaProperty {
        private final int mMyMask;
        private float mValue = 1.0f;
        private float mOthers = 1.0f;

        AlphaProperty(int myMask) {
            this.mMyMask = myMask;
        }

        public void setValue(float value) {
            if (this.mValue == value) {
                return;
            }
            if ((MultiValueAlpha.this.mValidMask & this.mMyMask) == 0) {
                this.mOthers = 1.0f;
                for (AlphaProperty alphaProperty : MultiValueAlpha.this.mMyProperties) {
                    if (alphaProperty != this) {
                        this.mOthers *= alphaProperty.mValue;
                    }
                }
            }
            MultiValueAlpha.this.mValidMask = this.mMyMask;
            this.mValue = value;
            MultiValueAlpha.this.mView.setAlpha(this.mOthers * this.mValue);
        }

        public float getValue() {
            return this.mValue;
        }

        public String toString() {
            return Float.toString(this.mValue);
        }
    }
}
