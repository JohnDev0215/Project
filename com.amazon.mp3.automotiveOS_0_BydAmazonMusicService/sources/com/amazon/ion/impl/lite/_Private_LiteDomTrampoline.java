package com.amazon.ion.impl.lite;

import com.amazon.ion.IonSystem;
import com.amazon.ion.impl._Private_IonBinaryWriterBuilder;
import com.amazon.ion.system.IonReaderBuilder;
import com.amazon.ion.system.IonTextWriterBuilder;

/* JADX INFO: loaded from: classes3.dex */
public final class _Private_LiteDomTrampoline {
    public static IonSystem newLiteSystem(IonTextWriterBuilder ionTextWriterBuilder, _Private_IonBinaryWriterBuilder _private_ionbinarywriterbuilder, IonReaderBuilder ionReaderBuilder) {
        return new IonSystemLite(ionTextWriterBuilder, _private_ionbinarywriterbuilder, ionReaderBuilder);
    }
}
