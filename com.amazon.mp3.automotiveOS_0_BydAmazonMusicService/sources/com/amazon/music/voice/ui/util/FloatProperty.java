package com.amazon.music.voice.ui.util;

import com.amazon.music.voice.ui.internal.util.BaseProperty;

/* JADX INFO: loaded from: classes4.dex */
public final class FloatProperty extends BaseProperty {
    private volatile boolean hasValue;
    private volatile float value;

    public boolean hasValue() {
        return this.hasValue;
    }

    public float get() {
        if (!this.hasValue) {
            throw new IllegalStateException("Property does not hold any value");
        }
        return this.value;
    }

    public void set(float f) {
        if (this.hasValue && this.value == f) {
            return;
        }
        this.value = f;
        this.hasValue = true;
        notifyChanged();
    }
}
