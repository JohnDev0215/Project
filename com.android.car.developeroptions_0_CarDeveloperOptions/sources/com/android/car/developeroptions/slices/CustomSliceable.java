package com.android.car.developeroptions.slices;

import android.content.Context;
import android.content.Intent;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public interface CustomSliceable extends Sliceable {
    @Override // com.android.car.developeroptions.slices.Sliceable
    default boolean isSliceable() {
        return true;
    }

    default void onNotifyChange(Intent intent) {
    }

    static CustomSliceable createInstance(Context context, Class<? extends CustomSliceable> cls) {
        try {
            return cls.getConstructor(Context.class).newInstance(context.getApplicationContext());
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalStateException("Invalid sliceable class: " + cls, e);
        }
    }
}
