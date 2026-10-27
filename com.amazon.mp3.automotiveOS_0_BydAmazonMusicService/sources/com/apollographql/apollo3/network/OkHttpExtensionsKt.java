package com.apollographql.apollo3.network;

import com.apollographql.apollo3.api.http.HttpHeader;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;

/* JADX INFO: compiled from: OkHttpExtensions.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0000¨\u0006\u0004"}, d2 = {"", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "Lokhttp3/Headers;", "toOkHttpHeaders", "apollo-runtime"}, k = 2, mv = {1, 5, 1})
public final class OkHttpExtensionsKt {
    public static final Headers toOkHttpHeaders(List<HttpHeader> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Headers.Builder builder = new Headers.Builder();
        for (HttpHeader httpHeader : list) {
            builder.add(httpHeader.getName(), httpHeader.getValue());
        }
        return builder.build();
    }
}
