package com.android.textclassifier;

import com.android.textclassifier.ModelFileManager;
import com.google.common.base.Function;

/* JADX INFO: renamed from: com.android.textclassifier.-$$Lambda$2YrDcjRKLOb8HwDLV5g9t6JgsSg, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$2YrDcjRKLOb8HwDLV5g9t6JgsSg implements Function {
    public static final /* synthetic */ $$Lambda$2YrDcjRKLOb8HwDLV5g9t6JgsSg INSTANCE = new $$Lambda$2YrDcjRKLOb8HwDLV5g9t6JgsSg();

    private /* synthetic */ $$Lambda$2YrDcjRKLOb8HwDLV5g9t6JgsSg() {
    }

    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        return ((ModelFileManager.ModelFile) obj).toModelInfo();
    }
}
