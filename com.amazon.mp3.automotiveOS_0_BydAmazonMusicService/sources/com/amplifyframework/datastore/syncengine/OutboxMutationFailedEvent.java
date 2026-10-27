package com.amplifyframework.datastore.syncengine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.api.graphql.MutationType;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.datastore.DataStoreChannelEventName;
import com.amplifyframework.datastore.appsync.AppSyncExtensions;
import com.amplifyframework.hub.HubEvent;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class OutboxMutationFailedEvent<M extends Model> implements HubEvent.Data<OutboxMutationFailedEvent<M>> {
    private final MutationErrorType errorType;
    private final M model;
    private final String modelName;
    private final MutationType operation;

    private OutboxMutationFailedEvent(MutationErrorType mutationErrorType, MutationType mutationType, String str, M m) {
        this.errorType = mutationErrorType;
        this.operation = mutationType;
        this.modelName = str;
        this.model = m;
    }

    @NonNull
    public static <M extends Model> OutboxMutationFailedEvent<M> create(@NonNull PendingMutation<M> pendingMutation, @NonNull List<GraphQLResponse.Error> list) {
        Objects.requireNonNull(pendingMutation);
        Objects.requireNonNull(list);
        return new OutboxMutationFailedEvent<>(MutationErrorType.fromGraphQlErrors(list), MutationType.valueOf(pendingMutation.getMutationType().name()), pendingMutation.getModelSchema().getName(), pendingMutation.getMutatedItem());
    }

    @NonNull
    public MutationErrorType getErrorType() {
        return this.errorType;
    }

    @NonNull
    public MutationType getOperation() {
        return this.operation;
    }

    @NonNull
    public M getModel() {
        return this.model;
    }

    @NonNull
    public String getModelName() {
        return this.modelName;
    }

    @Override // com.amplifyframework.hub.HubEvent.Data
    public HubEvent<OutboxMutationFailedEvent<M>> toHubEvent() {
        return HubEvent.create(DataStoreChannelEventName.OUTBOX_MUTATION_FAILED, this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || OutboxMutationFailedEvent.class != obj.getClass()) {
            return false;
        }
        OutboxMutationFailedEvent outboxMutationFailedEvent = (OutboxMutationFailedEvent) obj;
        return getErrorType() == outboxMutationFailedEvent.getErrorType() && getOperation() == outboxMutationFailedEvent.getOperation() && getModelName().equals(outboxMutationFailedEvent.getModelName()) && getModel().equals(outboxMutationFailedEvent.getModel());
    }

    public int hashCode() {
        return ObjectsCompat.hash(getErrorType(), getOperation(), getModelName(), getModel());
    }

    public String toString() {
        return "OutboxMutationFailedEvent{errorType=" + this.errorType + ", operation=" + this.operation + ", modelName=" + this.modelName + ", model=" + this.model + '}';
    }

    public enum MutationErrorType {
        UNAUTHORIZED("Unauthorized"),
        UNKNOWN("Unknown");

        private final String value;

        MutationErrorType(String str) {
            this.value = str;
        }

        @NonNull
        public String getValue() {
            return this.value;
        }

        @NonNull
        public static MutationErrorType enumerate(@Nullable String str) {
            for (MutationErrorType mutationErrorType : values()) {
                if (mutationErrorType.getValue().equals(str)) {
                    return mutationErrorType;
                }
            }
            return UNKNOWN;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static MutationErrorType fromGraphQlErrors(List<GraphQLResponse.Error> list) {
            try {
                return enumerate(new AppSyncExtensions(list.get(0).getExtensions()).getErrorType().getValue());
            } catch (IndexOutOfBoundsException | NullPointerException unused) {
                return UNKNOWN;
            }
        }
    }
}
