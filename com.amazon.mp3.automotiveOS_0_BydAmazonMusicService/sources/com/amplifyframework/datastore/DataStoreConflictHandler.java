package com.amplifyframework.datastore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.model.Model;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public interface DataStoreConflictHandler {

    public enum ResolutionStrategy {
        APPLY_REMOTE,
        RETRY_LOCAL,
        RETRY
    }

    void onConflictDetected(@NonNull ConflictData<? extends Model> conflictData, @NonNull Consumer<ConflictResolutionDecision<? extends Model>> consumer);

    static DataStoreConflictHandler alwaysApplyRemote() {
        return new AlwaysApplyRemoteHandler();
    }

    static DataStoreConflictHandler alwaysRetryLocal() {
        return new AlwaysRetryLocalHandler();
    }

    public static final class AlwaysApplyRemoteHandler implements DataStoreConflictHandler {
        @Override // com.amplifyframework.datastore.DataStoreConflictHandler
        public void onConflictDetected(@NonNull ConflictData<? extends Model> conflictData, @NonNull Consumer<ConflictResolutionDecision<? extends Model>> consumer) {
            consumer.accept(ConflictResolutionDecision.applyRemote());
        }
    }

    public static final class AlwaysRetryLocalHandler implements DataStoreConflictHandler {
        @Override // com.amplifyframework.datastore.DataStoreConflictHandler
        public void onConflictDetected(@NonNull ConflictData<? extends Model> conflictData, @NonNull Consumer<ConflictResolutionDecision<? extends Model>> consumer) {
            consumer.accept(ConflictResolutionDecision.retryLocal());
        }
    }

    public static final class ConflictData<T extends Model> {
        private final T local;
        private final T remote;

        private ConflictData(T t, T t2) {
            this.local = t;
            this.remote = t2;
        }

        @NonNull
        public static <T extends Model> ConflictData<T> create(@NonNull T t, @NonNull T t2) {
            Objects.requireNonNull(t);
            Objects.requireNonNull(t2);
            return new ConflictData<>(t, t2);
        }

        @NonNull
        public T getLocal() {
            return this.local;
        }

        @NonNull
        public T getRemote() {
            return this.remote;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ConflictData.class != obj.getClass()) {
                return false;
            }
            ConflictData conflictData = (ConflictData) obj;
            if (getLocal().equals(conflictData.getLocal())) {
                return getRemote().equals(conflictData.getRemote());
            }
            return false;
        }

        public int hashCode() {
            return (getLocal().hashCode() * 31) + getRemote().hashCode();
        }

        public String toString() {
            return "DataStoreConflictData{local=" + this.local + ", remote=" + this.remote + '}';
        }
    }

    public static final class ConflictResolutionDecision<T extends Model> {
        private final T customModel;
        private final ResolutionStrategy resolutionStrategy;

        private ConflictResolutionDecision(ResolutionStrategy resolutionStrategy, T t) {
            this.resolutionStrategy = resolutionStrategy;
            this.customModel = t;
        }

        @NonNull
        public static <T extends Model> ConflictResolutionDecision<T> applyRemote() {
            return new ConflictResolutionDecision<>(ResolutionStrategy.APPLY_REMOTE, null);
        }

        @NonNull
        public static <T extends Model> ConflictResolutionDecision<T> retryLocal() {
            return new ConflictResolutionDecision<>(ResolutionStrategy.RETRY_LOCAL, null);
        }

        @NonNull
        public static <T extends Model> ConflictResolutionDecision<T> retry(@NonNull T t) {
            Objects.requireNonNull(t);
            return new ConflictResolutionDecision<>(ResolutionStrategy.RETRY, t);
        }

        @NonNull
        public ResolutionStrategy getResolutionStrategy() {
            return this.resolutionStrategy;
        }

        @Nullable
        public T getCustomModel() {
            return this.customModel;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ConflictResolutionDecision.class != obj.getClass()) {
                return false;
            }
            ConflictResolutionDecision conflictResolutionDecision = (ConflictResolutionDecision) obj;
            if (ObjectsCompat.equals(getResolutionStrategy(), conflictResolutionDecision.getResolutionStrategy())) {
                return ObjectsCompat.equals(getCustomModel(), conflictResolutionDecision.getCustomModel());
            }
            return false;
        }

        public int hashCode() {
            return (getResolutionStrategy().hashCode() * 31) + (getCustomModel() != null ? getCustomModel().hashCode() : 0);
        }

        @NonNull
        public String toString() {
            return "ConflictResolutionDecision{resolutionStrategy=" + this.resolutionStrategy + ", customModel=" + this.customModel + '}';
        }
    }
}
