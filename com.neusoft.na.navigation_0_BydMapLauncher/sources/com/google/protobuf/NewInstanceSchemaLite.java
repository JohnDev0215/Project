package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite;

/* JADX INFO: loaded from: classes2.dex */
@CheckReturnValue
final class NewInstanceSchemaLite implements NewInstanceSchema {
    NewInstanceSchemaLite() {
    }

    @Override // com.google.protobuf.NewInstanceSchema
    public Object newInstance(Object obj) {
        return ((GeneratedMessageLite) obj).dynamicMethod(GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE);
    }
}
