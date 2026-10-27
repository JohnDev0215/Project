package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Operation.Data;
import com.apollographql.apollo3.api.http.HttpHeader;
import com.apollographql.apollo3.api.http.HttpMethod;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ApolloRequest.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000e\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003:\u0001,Bm\b\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\n\u0010\u000b\u001a\u00060\tj\u0002`\n\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019\u0012\b\u0010 \u001a\u0004\u0018\u00010\u001f\u0012\b\u0010$\u001a\u0004\u0018\u00010\u001f\u0012\b\u0010&\u001a\u0004\u0018\u00010\u001f\u0012\b\u0010(\u001a\u0004\u0018\u00010\u001f¢\u0006\u0004\b*\u0010+R\u001d\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00060\tj\u0002`\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0010\u001a\u00020\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\"\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00198\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001c\u0010 \u001a\u0004\u0018\u00010\u001f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u001c\u0010$\u001a\u0004\u0018\u00010\u001f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b$\u0010!\u001a\u0004\b%\u0010#R\u001c\u0010&\u001a\u0004\u0018\u00010\u001f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b&\u0010!\u001a\u0004\b'\u0010#R\u001c\u0010(\u001a\u0004\u0018\u00010\u001f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b(\u0010!\u001a\u0004\b)\u0010#¨\u0006-"}, d2 = {"Lcom/apollographql/apollo3/api/ApolloRequest;", "Lcom/apollographql/apollo3/api/Operation$Data;", "D", "", "Lcom/apollographql/apollo3/api/Operation;", "operation", "Lcom/apollographql/apollo3/api/Operation;", "getOperation", "()Lcom/apollographql/apollo3/api/Operation;", "Ljava/util/UUID;", "Lcom/benasher44/uuid/Uuid;", "requestUuid", "Ljava/util/UUID;", "getRequestUuid", "()Ljava/util/UUID;", "Lcom/apollographql/apollo3/api/ExecutionContext;", "executionContext", "Lcom/apollographql/apollo3/api/ExecutionContext;", "getExecutionContext", "()Lcom/apollographql/apollo3/api/ExecutionContext;", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "httpMethod", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "getHttpMethod", "()Lcom/apollographql/apollo3/api/http/HttpMethod;", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "httpHeaders", "Ljava/util/List;", "getHttpHeaders", "()Ljava/util/List;", "", "sendApqExtensions", "Ljava/lang/Boolean;", "getSendApqExtensions", "()Ljava/lang/Boolean;", "sendDocument", "getSendDocument", "enableAutoPersistedQueries", "getEnableAutoPersistedQueries", "canBeBatched", "getCanBeBatched", "<init>", "(Lcom/apollographql/apollo3/api/Operation;Ljava/util/UUID;Lcom/apollographql/apollo3/api/ExecutionContext;Lcom/apollographql/apollo3/api/http/HttpMethod;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "Builder", "apollo-api"}, k = 1, mv = {1, 5, 1})
public final class ApolloRequest<D extends Operation.Data> {
    private final Boolean canBeBatched;
    private final Boolean enableAutoPersistedQueries;
    private final ExecutionContext executionContext;
    private final List<HttpHeader> httpHeaders;
    private final HttpMethod httpMethod;
    private final Operation<D> operation;
    private final UUID requestUuid;
    private final Boolean sendApqExtensions;
    private final Boolean sendDocument;

    public /* synthetic */ ApolloRequest(Operation operation, UUID uuid, ExecutionContext executionContext, HttpMethod httpMethod, List list, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, DefaultConstructorMarker defaultConstructorMarker) {
        this(operation, uuid, executionContext, httpMethod, list, bool, bool2, bool3, bool4);
    }

    private ApolloRequest(Operation<D> operation, UUID uuid, ExecutionContext executionContext, HttpMethod httpMethod, List<HttpHeader> list, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4) {
        this.operation = operation;
        this.requestUuid = uuid;
        this.executionContext = executionContext;
        this.httpMethod = httpMethod;
        this.httpHeaders = list;
        this.sendApqExtensions = bool;
        this.sendDocument = bool2;
        this.enableAutoPersistedQueries = bool3;
        this.canBeBatched = bool4;
    }

    public final Operation<D> getOperation() {
        return this.operation;
    }

    public final UUID getRequestUuid() {
        return this.requestUuid;
    }

    public ExecutionContext getExecutionContext() {
        return this.executionContext;
    }

    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    public List<HttpHeader> getHttpHeaders() {
        return this.httpHeaders;
    }

    public Boolean getSendApqExtensions() {
        return this.sendApqExtensions;
    }

    public Boolean getSendDocument() {
        return this.sendDocument;
    }

    public Boolean getEnableAutoPersistedQueries() {
        return this.enableAutoPersistedQueries;
    }

    /* JADX INFO: compiled from: ApolloRequest.kt */
    @Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b \u0018\u0000*\b\b\u0001\u0010\u0002*\u00020\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00000\u0003B\u0015\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00010\u0013¢\u0006\u0004\b5\u00106J\u0018\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0016J\u001f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\u0010\f\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\b\f\u0010\u000bJ\u001f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\u0010\r\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\b\r\u0010\u000bJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u000f\u001a\u00020\u000eJ\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u0011R\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00010\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0018\u001a\u00060\u0016j\u0002`\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\"\u0010\u000f\u001a\u00020\u000e8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R*\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\b\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R$\u0010\n\u001a\u0004\u0018\u00010\t8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\n\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R$\u0010\f\u001a\u0004\u0018\u00010\t8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\f\u0010)\u001a\u0004\b.\u0010+\"\u0004\b/\u0010-R$\u0010\r\u001a\u0004\u0018\u00010\t8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\r\u0010)\u001a\u0004\b0\u0010+\"\u0004\b1\u0010-R$\u00102\u001a\u0004\u0018\u00010\t8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b2\u0010)\u001a\u0004\b3\u0010+\"\u0004\b4\u0010-¨\u00067"}, d2 = {"Lcom/apollographql/apollo3/api/ApolloRequest$Builder;", "Lcom/apollographql/apollo3/api/Operation$Data;", "D", "", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "httpMethod", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "httpHeaders", "", "sendApqExtensions", "(Ljava/lang/Boolean;)Lcom/apollographql/apollo3/api/ApolloRequest$Builder;", "sendDocument", "enableAutoPersistedQueries", "Lcom/apollographql/apollo3/api/ExecutionContext;", "executionContext", "addExecutionContext", "Lcom/apollographql/apollo3/api/ApolloRequest;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/apollographql/apollo3/api/Operation;", "operation", "Lcom/apollographql/apollo3/api/Operation;", "Ljava/util/UUID;", "Lcom/benasher44/uuid/Uuid;", "requestUuid", "Ljava/util/UUID;", "Lcom/apollographql/apollo3/api/ExecutionContext;", "getExecutionContext", "()Lcom/apollographql/apollo3/api/ExecutionContext;", "setExecutionContext", "(Lcom/apollographql/apollo3/api/ExecutionContext;)V", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "getHttpMethod", "()Lcom/apollographql/apollo3/api/http/HttpMethod;", "setHttpMethod", "(Lcom/apollographql/apollo3/api/http/HttpMethod;)V", "Ljava/util/List;", "getHttpHeaders", "()Ljava/util/List;", "setHttpHeaders", "(Ljava/util/List;)V", "Ljava/lang/Boolean;", "getSendApqExtensions", "()Ljava/lang/Boolean;", "setSendApqExtensions", "(Ljava/lang/Boolean;)V", "getSendDocument", "setSendDocument", "getEnableAutoPersistedQueries", "setEnableAutoPersistedQueries", "canBeBatched", "getCanBeBatched", "setCanBeBatched", "<init>", "(Lcom/apollographql/apollo3/api/Operation;)V", "apollo-api"}, k = 1, mv = {1, 5, 1})
    public static final class Builder<D extends Operation.Data> {
        private Boolean canBeBatched;
        private Boolean enableAutoPersistedQueries;
        private ExecutionContext executionContext;
        private List<HttpHeader> httpHeaders;
        private HttpMethod httpMethod;
        private Operation<D> operation;
        private UUID requestUuid;
        private Boolean sendApqExtensions;
        private Boolean sendDocument;

        public Builder(Operation<D> operation) {
            Intrinsics.checkNotNullParameter(operation, "operation");
            this.operation = operation;
            UUID uuidRandomUUID = UUID.randomUUID();
            Intrinsics.checkNotNullExpressionValue(uuidRandomUUID, "randomUUID()");
            this.requestUuid = uuidRandomUUID;
            this.executionContext = ExecutionContext.Empty;
        }

        public ExecutionContext getExecutionContext() {
            return this.executionContext;
        }

        public void setExecutionContext(ExecutionContext executionContext) {
            Intrinsics.checkNotNullParameter(executionContext, "<set-?>");
            this.executionContext = executionContext;
        }

        public HttpMethod getHttpMethod() {
            return this.httpMethod;
        }

        public void setHttpMethod(HttpMethod httpMethod) {
            this.httpMethod = httpMethod;
        }

        public Builder<D> httpMethod(HttpMethod httpMethod) {
            setHttpMethod(httpMethod);
            return this;
        }

        public List<HttpHeader> getHttpHeaders() {
            return this.httpHeaders;
        }

        public void setHttpHeaders(List<HttpHeader> list) {
            this.httpHeaders = list;
        }

        public Builder<D> httpHeaders(List<HttpHeader> httpHeaders) {
            setHttpHeaders(httpHeaders);
            return this;
        }

        public Boolean getSendApqExtensions() {
            return this.sendApqExtensions;
        }

        public void setSendApqExtensions(Boolean bool) {
            this.sendApqExtensions = bool;
        }

        public Builder<D> sendApqExtensions(Boolean sendApqExtensions) {
            setSendApqExtensions(sendApqExtensions);
            return this;
        }

        public Boolean getSendDocument() {
            return this.sendDocument;
        }

        public void setSendDocument(Boolean bool) {
            this.sendDocument = bool;
        }

        public Builder<D> sendDocument(Boolean sendDocument) {
            setSendDocument(sendDocument);
            return this;
        }

        public Boolean getEnableAutoPersistedQueries() {
            return this.enableAutoPersistedQueries;
        }

        public void setEnableAutoPersistedQueries(Boolean bool) {
            this.enableAutoPersistedQueries = bool;
        }

        public Builder<D> enableAutoPersistedQueries(Boolean enableAutoPersistedQueries) {
            setEnableAutoPersistedQueries(enableAutoPersistedQueries);
            return this;
        }

        public Boolean getCanBeBatched() {
            return this.canBeBatched;
        }

        public final Builder<D> executionContext(ExecutionContext executionContext) {
            Intrinsics.checkNotNullParameter(executionContext, "executionContext");
            setExecutionContext(executionContext);
            return this;
        }

        public Builder<D> addExecutionContext(ExecutionContext executionContext) {
            Intrinsics.checkNotNullParameter(executionContext, "executionContext");
            setExecutionContext(getExecutionContext().plus(executionContext));
            return this;
        }

        public final ApolloRequest<D> build() {
            return new ApolloRequest<>(this.operation, this.requestUuid, getExecutionContext(), getHttpMethod(), getHttpHeaders(), getSendApqExtensions(), getSendDocument(), getEnableAutoPersistedQueries(), getCanBeBatched(), null);
        }
    }
}
