package com.amplifyframework.datastore;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.Model;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
public final class DataStoreConfiguration {

    @VisibleForTesting
    static final boolean DEFAULT_DO_SYNC_RETRY = false;

    @VisibleForTesting
    static final long DEFAULT_SYNC_INTERVAL_MINUTES = TimeUnit.DAYS.toMinutes(1);

    @VisibleForTesting
    static final int DEFAULT_SYNC_MAX_RECORDS = 10000;

    @VisibleForTesting
    static final int DEFAULT_SYNC_PAGE_SIZE = 1000;
    static final int MAX_RECORDS = 1000;
    static final long MAX_TIME_SEC = 2;
    static final String PLUGIN_CONFIG_KEY = "awsDataStorePlugin";
    private final DataStoreConflictHandler conflictHandler;
    private final boolean doSyncRetry;
    private final DataStoreErrorHandler errorHandler;
    private final Long maxTimeLapseForObserveQuery;
    private final Integer observeQueryMaxRecords;
    private final Map<String, DataStoreSyncExpression> syncExpressions;
    private final Long syncIntervalInMinutes;
    private final Integer syncMaxRecords;
    private final Integer syncPageSize;

    /* synthetic */ DataStoreConfiguration(Builder builder, AnonymousClass1 anonymousClass1) {
        this(builder);
    }

    private DataStoreConfiguration(Builder builder) {
        this.errorHandler = builder.errorHandler;
        this.conflictHandler = builder.conflictHandler;
        this.syncMaxRecords = builder.syncMaxRecords;
        this.syncPageSize = builder.syncPageSize;
        this.syncIntervalInMinutes = builder.syncIntervalInMinutes;
        this.syncExpressions = builder.syncExpressions;
        this.doSyncRetry = builder.doSyncRetry;
        this.maxTimeLapseForObserveQuery = Long.valueOf(builder.maxTimeLapseForObserveQuery);
        this.observeQueryMaxRecords = builder.observeQueryMaxRecords;
    }

    @NonNull
    public static Builder builder() {
        return new Builder(null);
    }

    @NonNull
    static Builder builder(@NonNull JSONObject jSONObject, @Nullable DataStoreConfiguration dataStoreConfiguration) {
        return new Builder(jSONObject, dataStoreConfiguration, null);
    }

    @NonNull
    static Builder builder(@NonNull JSONObject jSONObject) {
        return builder(jSONObject, null);
    }

    @NonNull
    public static DataStoreConfiguration defaults() throws DataStoreException {
        return builder().errorHandler(DefaultDataStoreErrorHandler.instance()).conflictHandler(DataStoreConflictHandler.alwaysApplyRemote()).syncInterval(DEFAULT_SYNC_INTERVAL_MINUTES, TimeUnit.MINUTES).syncPageSize(1000).syncMaxRecords(Integer.valueOf(DEFAULT_SYNC_MAX_RECORDS)).doSyncRetry(false).observeQueryMaxTime(2L).observeQueryMaxRecords(1000).build();
    }

    @NonNull
    public DataStoreErrorHandler getErrorHandler() {
        return this.errorHandler;
    }

    @NonNull
    public DataStoreConflictHandler getConflictHandler() {
        return this.conflictHandler;
    }

    @IntRange(from = 0)
    public Long getSyncIntervalMs() {
        return Long.valueOf(TimeUnit.MINUTES.toMillis(this.syncIntervalInMinutes.longValue()));
    }

    @IntRange(from = 0)
    public Long getSyncIntervalInMinutes() {
        return this.syncIntervalInMinutes;
    }

    @IntRange(from = 0)
    public Integer getSyncMaxRecords() {
        return this.syncMaxRecords;
    }

    @IntRange(from = 0)
    public Integer getSyncPageSize() {
        return this.syncPageSize;
    }

    public Boolean getDoSyncRetry() {
        return Boolean.valueOf(this.doSyncRetry);
    }

    @NonNull
    public Map<String, DataStoreSyncExpression> getSyncExpressions() {
        return this.syncExpressions;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DataStoreConfiguration.class != obj.getClass()) {
            return false;
        }
        DataStoreConfiguration dataStoreConfiguration = (DataStoreConfiguration) obj;
        return ObjectsCompat.equals(getErrorHandler(), dataStoreConfiguration.getConflictHandler()) && ObjectsCompat.equals(getConflictHandler(), dataStoreConfiguration.getConflictHandler()) && ObjectsCompat.equals(getSyncMaxRecords(), dataStoreConfiguration.getSyncMaxRecords()) && ObjectsCompat.equals(getSyncPageSize(), dataStoreConfiguration.getSyncPageSize()) && ObjectsCompat.equals(getSyncIntervalInMinutes(), dataStoreConfiguration.getSyncIntervalInMinutes()) && ObjectsCompat.equals(getSyncExpressions(), dataStoreConfiguration.getSyncExpressions()) && ObjectsCompat.equals(getDoSyncRetry(), dataStoreConfiguration.getDoSyncRetry()) && ObjectsCompat.equals(getMaxTimeLapseForObserveQuery(), dataStoreConfiguration.getMaxTimeLapseForObserveQuery()) && ObjectsCompat.equals(getObserveQueryMaxRecords(), dataStoreConfiguration.getObserveQueryMaxRecords());
    }

    public int hashCode() {
        return ((((((((((((((((getErrorHandler() != null ? getErrorHandler().hashCode() : 0) * 31) + (getConflictHandler() != null ? getConflictHandler().hashCode() : 0)) * 31) + (getSyncMaxRecords() != null ? getSyncMaxRecords().hashCode() : 0)) * 31) + (getSyncPageSize() != null ? getSyncPageSize().hashCode() : 0)) * 31) + (getSyncIntervalInMinutes() != null ? getSyncIntervalInMinutes().hashCode() : 0)) * 31) + (getSyncExpressions() != null ? getSyncExpressions().hashCode() : 0)) * 31) + getDoSyncRetry().hashCode()) * 31) + (getObserveQueryMaxRecords() != null ? getObserveQueryMaxRecords().hashCode() : 0)) * 31) + getMaxTimeLapseForObserveQuery().hashCode();
    }

    public String toString() {
        return "DataStoreConfiguration{errorHandler=" + this.errorHandler + ", conflictHandler=" + this.conflictHandler + ", syncMaxRecords=" + this.syncMaxRecords + ", syncPageSize=" + this.syncPageSize + ", syncIntervalInMinutes=" + this.syncIntervalInMinutes + ", syncExpressions=" + this.syncExpressions + ", doSyncRetry=" + this.doSyncRetry + ", maxTimeRelapseForObserveQuery=" + this.maxTimeLapseForObserveQuery + ", observeQueryMaxRecords=" + this.observeQueryMaxRecords + '}';
    }

    public Long getMaxTimeLapseForObserveQuery() {
        return this.maxTimeLapseForObserveQuery;
    }

    public Integer getObserveQueryMaxRecords() {
        return this.observeQueryMaxRecords;
    }

    public static final class Builder {
        private DataStoreConflictHandler conflictHandler;
        private boolean doSyncRetry;
        private boolean ensureDefaults;
        private DataStoreErrorHandler errorHandler;
        private long maxTimeLapseForObserveQuery;
        private Integer observeQueryMaxRecords;
        private JSONObject pluginJson;
        private Map<String, DataStoreSyncExpression> syncExpressions;
        private Long syncIntervalInMinutes;
        private Integer syncMaxRecords;
        private Integer syncPageSize;
        private DataStoreConfiguration userProvidedConfiguration;

        private static <T> T getValueOrDefault(T t, T t2) {
            return t == null ? t2 : t;
        }

        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        /* synthetic */ Builder(JSONObject jSONObject, DataStoreConfiguration dataStoreConfiguration, AnonymousClass1 anonymousClass1) {
            this(jSONObject, dataStoreConfiguration);
        }

        private Builder() {
            this.errorHandler = DefaultDataStoreErrorHandler.instance();
            this.conflictHandler = DataStoreConflictHandler.alwaysApplyRemote();
            this.syncExpressions = new HashMap();
            this.ensureDefaults = false;
        }

        private Builder(JSONObject jSONObject, DataStoreConfiguration dataStoreConfiguration) {
            this();
            this.pluginJson = jSONObject;
            this.userProvidedConfiguration = dataStoreConfiguration;
            this.ensureDefaults = true;
        }

        @NonNull
        public Builder conflictHandler(@NonNull DataStoreConflictHandler dataStoreConflictHandler) {
            Objects.requireNonNull(dataStoreConflictHandler);
            this.conflictHandler = dataStoreConflictHandler;
            return this;
        }

        @NonNull
        public Builder errorHandler(@NonNull DataStoreErrorHandler dataStoreErrorHandler) {
            Objects.requireNonNull(dataStoreErrorHandler);
            this.errorHandler = dataStoreErrorHandler;
            return this;
        }

        @NonNull
        public Builder syncInterval(@IntRange(from = 0) long j, TimeUnit timeUnit) {
            this.syncIntervalInMinutes = Long.valueOf(timeUnit.toMinutes(j));
            return this;
        }

        @NonNull
        public Builder observeQueryMaxTime(long j) {
            this.maxTimeLapseForObserveQuery = j;
            return this;
        }

        @NonNull
        public Builder observeQueryMaxRecords(@IntRange(from = 0) Integer num) {
            this.observeQueryMaxRecords = num;
            return this;
        }

        @NonNull
        public Builder syncMaxRecords(@IntRange(from = 0) Integer num) {
            this.syncMaxRecords = num;
            return this;
        }

        @NonNull
        public Builder doSyncRetry(boolean z) {
            this.doSyncRetry = z;
            return this;
        }

        @NonNull
        public Builder syncPageSize(@IntRange(from = 0) Integer num) {
            this.syncPageSize = num;
            return this;
        }

        @NonNull
        public Builder syncExpression(@NonNull Class<? extends Model> cls, @NonNull DataStoreSyncExpression dataStoreSyncExpression) {
            Map<String, DataStoreSyncExpression> map = this.syncExpressions;
            Objects.requireNonNull(cls);
            String simpleName = cls.getSimpleName();
            Objects.requireNonNull(dataStoreSyncExpression);
            map.put(simpleName, dataStoreSyncExpression);
            return this;
        }

        @NonNull
        public Builder syncExpression(@NonNull String str, @NonNull DataStoreSyncExpression dataStoreSyncExpression) {
            Map<String, DataStoreSyncExpression> map = this.syncExpressions;
            Objects.requireNonNull(str);
            Objects.requireNonNull(dataStoreSyncExpression);
            map.put(str, dataStoreSyncExpression);
            return this;
        }

        private void populateSettingsFromJson() throws DataStoreException {
            JSONObject jSONObject = this.pluginJson;
            if (jSONObject == null) {
                return;
            }
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                try {
                    ConfigKey configKeyFromString = ConfigKey.fromString(next);
                    try {
                        int i = AnonymousClass1.$SwitchMap$com$amplifyframework$datastore$DataStoreConfiguration$ConfigKey[configKeyFromString.ordinal()];
                        if (i == 1) {
                            syncInterval(this.pluginJson.getLong(ConfigKey.SYNC_INTERVAL_IN_MINUTES.toString()), TimeUnit.MINUTES);
                        } else if (i == 2) {
                            syncMaxRecords(Integer.valueOf(this.pluginJson.getInt(ConfigKey.SYNC_MAX_RECORDS.toString())));
                        } else if (i == 3) {
                            syncPageSize(Integer.valueOf(this.pluginJson.getInt(ConfigKey.SYNC_PAGE_SIZE.toString())));
                        } else {
                            throw new IllegalArgumentException("Unsupported config key = " + configKeyFromString.toString());
                        }
                    } catch (JSONException e) {
                        throw new DataStoreException("Issue encountered while parsing configuration JSON", e, "Ensure your amplifyconfiguration.json is valid.");
                    }
                } catch (IllegalArgumentException unused) {
                    throw new DataStoreException("Saw unexpected config key: " + next, "Make sure your amplifyconfiguration.json is valid.");
                }
            }
        }

        private void applyUserProvidedConfiguration() {
            DataStoreConfiguration dataStoreConfiguration = this.userProvidedConfiguration;
            if (dataStoreConfiguration == null) {
                return;
            }
            this.errorHandler = dataStoreConfiguration.getErrorHandler();
            this.conflictHandler = this.userProvidedConfiguration.getConflictHandler();
            this.syncIntervalInMinutes = (Long) getValueOrDefault(this.userProvidedConfiguration.getSyncIntervalInMinutes(), this.syncIntervalInMinutes);
            this.syncMaxRecords = (Integer) getValueOrDefault(this.userProvidedConfiguration.getSyncMaxRecords(), this.syncMaxRecords);
            this.syncPageSize = (Integer) getValueOrDefault(this.userProvidedConfiguration.getSyncPageSize(), this.syncPageSize);
            this.syncExpressions = this.userProvidedConfiguration.getSyncExpressions();
            this.doSyncRetry = ((Boolean) getValueOrDefault(this.userProvidedConfiguration.getDoSyncRetry(), Boolean.valueOf(this.doSyncRetry))).booleanValue();
            this.observeQueryMaxRecords = (Integer) getValueOrDefault(this.userProvidedConfiguration.getObserveQueryMaxRecords(), this.observeQueryMaxRecords);
            this.maxTimeLapseForObserveQuery = this.userProvidedConfiguration.getMaxTimeLapseForObserveQuery().longValue() == 0 ? this.maxTimeLapseForObserveQuery : this.userProvidedConfiguration.getMaxTimeLapseForObserveQuery().longValue();
        }

        @NonNull
        public DataStoreConfiguration build() throws DataStoreException {
            populateSettingsFromJson();
            applyUserProvidedConfiguration();
            if (this.ensureDefaults) {
                this.errorHandler = (DataStoreErrorHandler) getValueOrDefault(this.errorHandler, DefaultDataStoreErrorHandler.instance());
                this.conflictHandler = (DataStoreConflictHandler) getValueOrDefault(this.conflictHandler, DataStoreConflictHandler.alwaysApplyRemote());
                this.syncIntervalInMinutes = (Long) getValueOrDefault(this.syncIntervalInMinutes, Long.valueOf(DataStoreConfiguration.DEFAULT_SYNC_INTERVAL_MINUTES));
                this.syncMaxRecords = (Integer) getValueOrDefault(this.syncMaxRecords, Integer.valueOf(DataStoreConfiguration.DEFAULT_SYNC_MAX_RECORDS));
                this.syncPageSize = (Integer) getValueOrDefault(this.syncPageSize, 1000);
                this.observeQueryMaxRecords = (Integer) getValueOrDefault(this.observeQueryMaxRecords, 1000);
                long j = this.maxTimeLapseForObserveQuery;
                if (j == 0) {
                    j = 2;
                }
                this.maxTimeLapseForObserveQuery = j;
            }
            return new DataStoreConfiguration(this, null);
        }
    }

    /* JADX INFO: renamed from: com.amplifyframework.datastore.DataStoreConfiguration$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$datastore$DataStoreConfiguration$ConfigKey;

        static {
            int[] iArr = new int[ConfigKey.values().length];
            $SwitchMap$com$amplifyframework$datastore$DataStoreConfiguration$ConfigKey = iArr;
            try {
                iArr[ConfigKey.SYNC_INTERVAL_IN_MINUTES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amplifyframework$datastore$DataStoreConfiguration$ConfigKey[ConfigKey.SYNC_MAX_RECORDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amplifyframework$datastore$DataStoreConfiguration$ConfigKey[ConfigKey.SYNC_PAGE_SIZE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    enum ConfigKey {
        SYNC_INTERVAL_IN_MINUTES("syncIntervalInMinutes"),
        SYNC_PAGE_SIZE("syncPageSize"),
        SYNC_MAX_RECORDS("syncMaxRecords");

        private final String key;

        ConfigKey(String str) {
            this.key = str;
        }

        @Override // java.lang.Enum
        @NonNull
        public String toString() {
            return this.key;
        }

        static ConfigKey fromString(@Nullable String str) {
            for (ConfigKey configKey : values()) {
                if (configKey.toString().equals(str)) {
                    return configKey;
                }
            }
            throw new IllegalArgumentException(str + " is not a config key.");
        }
    }
}
