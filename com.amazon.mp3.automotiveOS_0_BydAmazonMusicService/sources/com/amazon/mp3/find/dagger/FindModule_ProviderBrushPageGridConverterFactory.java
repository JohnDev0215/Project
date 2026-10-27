package com.amazon.mp3.find.dagger;

import android.content.Context;
import com.amazon.mp3.brush.converters.BrushPageGridConverter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProviderBrushPageGridConverterFactory implements Factory<BrushPageGridConverter> {
    public static BrushPageGridConverter providerBrushPageGridConverter(FindModule instance, Context context) {
        return (BrushPageGridConverter) Preconditions.checkNotNullFromProvides(instance.providerBrushPageGridConverter(context));
    }
}
