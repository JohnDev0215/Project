package com.apollographql.apollo3.api.json.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsonScope.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ5\u0010\n\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010\t\u001a\u00020\u0004¢\u0006\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/apollographql/apollo3/api/json/internal/JsonScope;", "", "", "stackSize", "", "stack", "", "", "pathNames", "pathIndices", "getPath", "(I[I[Ljava/lang/String;[I)Ljava/lang/String;", "<init>", "()V", "apollo-api"}, k = 1, mv = {1, 5, 1})
public final class JsonScope {
    public static final JsonScope INSTANCE = new JsonScope();

    private JsonScope() {
    }

    public final String getPath(int stackSize, int[] stack, String[] pathNames, int[] pathIndices) {
        Intrinsics.checkNotNullParameter(stack, "stack");
        Intrinsics.checkNotNullParameter(pathNames, "pathNames");
        Intrinsics.checkNotNullParameter(pathIndices, "pathIndices");
        StringBuilder sb = new StringBuilder();
        int i = 0;
        boolean z = true;
        while (i < stackSize) {
            int i2 = i + 1;
            int i3 = stack[i];
            if (i3 == 1 || i3 == 2) {
                sb.append('.');
                sb.append(pathIndices[i]);
            } else if (i3 == 3 || i3 == 4 || i3 == 5) {
                if (!z) {
                    sb.append('.');
                }
                String str = pathNames[i];
                if (str != null) {
                    sb.append(str);
                    z = false;
                }
            }
            i = i2;
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "result.toString()");
        return string;
    }
}
