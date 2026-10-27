package com.amazon.mp3.amplifyqueue.api;

import android.content.Context;
import androidx.annotation.WorkerThread;
import com.amazon.mp3.amplifyqueue.model.ConnectResponseData;
import com.amplifyframework.api.aws.GsonVariablesSerializer;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.SimpleGraphQLRequest;
import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConnectGraphQLRequest.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\t\u0010\nJ \u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00052\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0003H\u0017¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/amplifyqueue/api/ConnectGraphQLRequest;", "", "any", "Landroid/content/Context;", "context", "Lcom/amplifyframework/api/graphql/GraphQLRequest;", "initInput", "", "document", "<init>", "()V", "amplifyqueue_release"}, k = 1, mv = {1, 6, 0})
public final class ConnectGraphQLRequest {
    public static final ConnectGraphQLRequest INSTANCE = new ConnectGraphQLRequest();

    @WorkerThread
    public String document(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return "query Connect {\n  connect {\n    status\n  }\n}";
    }

    private ConnectGraphQLRequest() {
    }

    public GraphQLRequest<Object> initInput(Object any, Context context) {
        Intrinsics.checkNotNullParameter(any, "any");
        Intrinsics.checkNotNullParameter(context, "context");
        return new SimpleGraphQLRequest(document(context), MapsKt__MapsKt.emptyMap(), ConnectResponseData.class, new GsonVariablesSerializer());
    }
}
