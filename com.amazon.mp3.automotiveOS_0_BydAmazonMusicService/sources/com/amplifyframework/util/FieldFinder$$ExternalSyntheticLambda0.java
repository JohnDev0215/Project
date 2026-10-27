package com.amplifyframework.util;

import java.lang.reflect.Field;
import java.util.function.Function;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class FieldFinder$$ExternalSyntheticLambda0 implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((Field) obj).getName();
    }
}
