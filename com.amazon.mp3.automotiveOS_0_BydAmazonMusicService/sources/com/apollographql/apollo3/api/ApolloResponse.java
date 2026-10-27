package com.apollographql.apollo3.api;

import com.amazon.identity.auth.device.api.MAPWebViewEventHelper;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Operation.Data;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ApolloResponse.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003:\u0001\u001fBc\b\u0002\u0012\n\u0010\b\u001a\u00060\u0006j\u0002`\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\n\u0012\b\u0010\r\u001a\u0004\u0018\u00018\u0000\u0012\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f\u0012\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0013\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u0012\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001d\u0010\u001eJ\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004R\u0018\u0010\b\u001a\u00060\u0006j\u0002`\u00078\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\b\u0010\tR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\n8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\u0004\u0018\u00018\u00008\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u001c\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\"\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00138\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00178\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u001a8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001c¨\u0006 "}, d2 = {"Lcom/apollographql/apollo3/api/ApolloResponse;", "Lcom/apollographql/apollo3/api/Operation$Data;", "D", "", "Lcom/apollographql/apollo3/api/ApolloResponse$Builder;", "newBuilder", "Ljava/util/UUID;", "Lcom/benasher44/uuid/Uuid;", "requestUuid", "Ljava/util/UUID;", "Lcom/apollographql/apollo3/api/Operation;", "operation", "Lcom/apollographql/apollo3/api/Operation;", "data", "Lcom/apollographql/apollo3/api/Operation$Data;", "", "Lcom/apollographql/apollo3/api/Error;", MAPWebViewEventHelper.KEY_ERRORS, "Ljava/util/List;", "", "", "extensions", "Ljava/util/Map;", "Lcom/apollographql/apollo3/api/ExecutionContext;", "executionContext", "Lcom/apollographql/apollo3/api/ExecutionContext;", "", "isLast", "Z", "<init>", "(Ljava/util/UUID;Lcom/apollographql/apollo3/api/Operation;Lcom/apollographql/apollo3/api/Operation$Data;Ljava/util/List;Ljava/util/Map;Lcom/apollographql/apollo3/api/ExecutionContext;Z)V", "Builder", "apollo-api"}, k = 1, mv = {1, 5, 1})
public final class ApolloResponse<D extends Operation.Data> {

    @JvmField
    public final D data;

    @JvmField
    public final List<Error> errors;

    @JvmField
    public final ExecutionContext executionContext;

    @JvmField
    public final Map<String, Object> extensions;

    @JvmField
    public final boolean isLast;

    @JvmField
    public final Operation<D> operation;

    @JvmField
    public final UUID requestUuid;

    public /* synthetic */ ApolloResponse(UUID uuid, Operation operation, Operation.Data data, List list, Map map, ExecutionContext executionContext, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(uuid, operation, data, list, map, executionContext, z);
    }

    private ApolloResponse(UUID uuid, Operation<D> operation, D d, List<Error> list, Map<String, ? extends Object> map, ExecutionContext executionContext, boolean z) {
        this.requestUuid = uuid;
        this.operation = operation;
        this.data = d;
        this.errors = list;
        this.extensions = map;
        this.executionContext = executionContext;
        this.isLast = z;
    }

    public final Builder<D> newBuilder() {
        return new Builder(this.operation, this.requestUuid, this.data).errors(this.errors).extensions(this.extensions).addExecutionContext(this.executionContext).isLast(this.isLast);
    }

    /* JADX INFO: compiled from: ApolloResponse.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\u00020\u0003B)\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005\u0012\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b\u0012\b\u0010\t\u001a\u0004\u0018\u00018\u0001¢\u0006\u0002\u0010\nJ\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u000f\u001a\u00020\u0010J\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00010\u0018J\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rJ$\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0012J\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\n\u0010\u0006\u001a\u00060\u0007j\u0002`\bR\u0012\u0010\t\u001a\u0004\u0018\u00018\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00060\u0007j\u0002`\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/apollographql/apollo3/api/ApolloResponse$Builder;", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "", "operation", "Lcom/apollographql/apollo3/api/Operation;", "requestUuid", "Ljava/util/UUID;", "Lcom/benasher44/uuid/Uuid;", "data", "(Lcom/apollographql/apollo3/api/Operation;Ljava/util/UUID;Lcom/apollographql/apollo3/api/Operation$Data;)V", "Lcom/apollographql/apollo3/api/Operation$Data;", MAPWebViewEventHelper.KEY_ERRORS, "", "Lcom/apollographql/apollo3/api/Error;", "executionContext", "Lcom/apollographql/apollo3/api/ExecutionContext;", "extensions", "", "", "isLast", "", "addExecutionContext", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/apollographql/apollo3/api/ApolloResponse;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder<D extends Operation.Data> {
        private final D data;
        private List<Error> errors;
        private ExecutionContext executionContext;
        private Map<String, ? extends Object> extensions;
        private boolean isLast;
        private final Operation<D> operation;
        private UUID requestUuid;

        public Builder(Operation<D> operation, UUID requestUuid, D d) {
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(requestUuid, "requestUuid");
            this.operation = operation;
            this.requestUuid = requestUuid;
            this.data = d;
            this.executionContext = ExecutionContext.Empty;
        }

        public final Builder<D> addExecutionContext(ExecutionContext executionContext) {
            Intrinsics.checkNotNullParameter(executionContext, "executionContext");
            this.executionContext = this.executionContext.plus(executionContext);
            return this;
        }

        public final Builder<D> errors(List<Error> errors) {
            this.errors = errors;
            return this;
        }

        public final Builder<D> extensions(Map<String, ? extends Object> extensions) {
            this.extensions = extensions;
            return this;
        }

        public final Builder<D> requestUuid(UUID requestUuid) {
            Intrinsics.checkNotNullParameter(requestUuid, "requestUuid");
            this.requestUuid = requestUuid;
            return this;
        }

        public final Builder<D> isLast(boolean isLast) {
            this.isLast = isLast;
            return this;
        }

        public final ApolloResponse<D> build() {
            Operation<D> operation = this.operation;
            UUID uuid = this.requestUuid;
            D d = this.data;
            ExecutionContext executionContext = this.executionContext;
            Map<String, ? extends Object> mapEmptyMap = this.extensions;
            if (mapEmptyMap == null) {
                mapEmptyMap = MapsKt__MapsKt.emptyMap();
            }
            return new ApolloResponse<>(uuid, operation, d, this.errors, mapEmptyMap, executionContext, this.isLast, null);
        }
    }
}
