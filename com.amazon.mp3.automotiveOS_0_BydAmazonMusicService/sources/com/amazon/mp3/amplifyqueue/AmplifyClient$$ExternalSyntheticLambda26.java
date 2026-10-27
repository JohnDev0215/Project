package com.amazon.mp3.amplifyqueue;

import com.amazon.mp3.amplifyqueue.converters.ModelsTransformerKt;
import com.amazon.mp3.amplifyqueue.model.QueueCapabilitySet;
import io.reactivex.rxjava3.functions.Function;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class AmplifyClient$$ExternalSyntheticLambda26 implements Function {
    @Override // io.reactivex.rxjava3.functions.Function
    public final Object apply(Object obj) {
        return ModelsTransformerKt.toExternal((QueueCapabilitySet) obj);
    }
}
