package com.amazon.minerva.client.thirdparty.api;

import android.util.Log;
import com.amazon.minerva.client.thirdparty.metric.DataPoint;
import com.amazon.minerva.client.thirdparty.metric.Timestamp;
import com.amazon.minerva.client.thirdparty.metric.TypedValue;
import com.amazon.minerva.client.thirdparty.metric.ValueType;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public class MetricEvent {
    private static final Pattern KEY_VALIDATION_PATTERN = Pattern.compile("^[a-zA-Z]$|^[a-zA-Z][a-zA-Z0-9_.]*[^.]$");
    private static final Pattern PATTERN_METRIC_GROUP_ID = Pattern.compile("[0-9a-z]{8}");
    private static final Pattern PATTERN_SCHEMA_ID = Pattern.compile("[0-9a-z]{4}/\\d+/[0-9a-f]{8}");
    private static final String TAG = "MetricEvent";
    private Timestamp mClientTimestamp;
    private Map<String, TypedValue<?>> mKeyValuePairs;
    private UUID mMetricEventId;
    private String mMetricGroupId;
    int mNumInvalidKeyValuePairs = 0;
    private String mSchemaId;

    public MetricEvent(String str, String str2) {
        Objects.requireNonNull(str, "metricGroupId cannot be null.");
        Objects.requireNonNull(str2, "schemaId cannot be null.");
        if (!PATTERN_METRIC_GROUP_ID.matcher(str).matches()) {
            throw new IllegalArgumentException("Invalid argument for metricGroupId, validation failed.");
        }
        if (!PATTERN_SCHEMA_ID.matcher(str2).matches()) {
            throw new IllegalArgumentException("Invalid argument for schemaId, validation failed.");
        }
        this.mMetricGroupId = str;
        this.mSchemaId = str2;
        this.mKeyValuePairs = new LinkedHashMap();
        this.mMetricEventId = UUID.randomUUID();
    }

    public String getMetricGroupId() {
        return this.mMetricGroupId;
    }

    public String getSchemaId() {
        return this.mSchemaId;
    }

    public void addPredefined(Predefined predefined) {
        if (predefined != null) {
            if (this.mKeyValuePairs.containsKey(predefined.getKey())) {
                return;
            }
            this.mKeyValuePairs.put(predefined.getKey(), ValueType.SYMBOL.of(""));
        } else {
            Log.e(TAG, "The arguments for adding predefined key value pairs should not be null.");
            this.mNumInvalidKeyValuePairs++;
        }
    }

    public void addBoolean(String str, boolean z) {
        if (IsCustomKeyValid(str)) {
            this.mKeyValuePairs.put(str, ValueType.BOOLEAN.of(Boolean.valueOf(z)));
        }
    }

    public void addLong(String str, long j) {
        if (IsCustomKeyValid(str)) {
            this.mKeyValuePairs.put(str, ValueType.INTEGER.of(Long.valueOf(j)));
        }
    }

    public void addDouble(String str, double d) {
        if (IsCustomKeyValid(str)) {
            this.mKeyValuePairs.put(str, ValueType.FLOAT.of(Double.valueOf(d)));
        }
    }

    public void addTimestamp(String str, Date date) {
        addTimestamp(str, date, TimeZone.getDefault());
    }

    public void addTimestamp(String str, Date date, TimeZone timeZone) {
        if (date == null || timeZone == null) {
            Log.e(TAG, "The arguments for adding Timestamp key-value pair should not be null.");
            this.mNumInvalidKeyValuePairs++;
        } else if (IsCustomKeyValid(str)) {
            this.mKeyValuePairs.put(str, ValueType.TIMESTAMP.of(Timestamp.of(date.getTime(), timeZone)));
        }
    }

    public void addString(String str, String str2) {
        if (str2 == null || str2.isEmpty()) {
            Log.e(TAG, "The second parameter for adding String key-value pair should not be null or empty String.");
            this.mNumInvalidKeyValuePairs++;
        } else if (IsCustomKeyValid(str)) {
            this.mKeyValuePairs.put(str, ValueType.SYMBOL.of(str2));
        }
    }

    public UUID getMetricEventId() {
        return this.mMetricEventId;
    }

    public Timestamp getClientTimestamp() {
        return this.mClientTimestamp;
    }

    public void setClientTimestamp(Timestamp timestamp) {
        this.mClientTimestamp = timestamp;
    }

    public Map<String, TypedValue<?>> getKeyValuePairs() {
        return this.mKeyValuePairs;
    }

    public int getKeyValuePairCount() {
        return this.mKeyValuePairs.size();
    }

    public int getNumInvalidKeyValuePairs() {
        return this.mNumInvalidKeyValuePairs;
    }

    public void clear() {
        this.mKeyValuePairs.clear();
        this.mMetricEventId = UUID.randomUUID();
        this.mClientTimestamp = null;
        this.mNumInvalidKeyValuePairs = 0;
    }

    public List<DataPoint> getKeyValuePairsAsDataPoints() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, TypedValue<?>> entry : this.mKeyValuePairs.entrySet()) {
            arrayList.add(new DataPoint(entry.getKey(), entry.getValue().getType(), entry.getValue().getValueAsString()));
        }
        return arrayList;
    }

    protected boolean IsCustomKeyValid(String str) {
        if (str == null) {
            Log.e(TAG, "The key for adding custom key value pair should not be null.");
            this.mNumInvalidKeyValuePairs++;
            return false;
        }
        if (str.isEmpty()) {
            Log.e(TAG, "The key for adding custom key value pair should not be empty.");
            this.mNumInvalidKeyValuePairs++;
            return false;
        }
        if (str.startsWith(Attributes.PREDEFINED_ATTRIBUTE_PREFIX)) {
            Log.e(TAG, "The key for adding custom key value pair should not start with underscore.");
            this.mNumInvalidKeyValuePairs++;
            return false;
        }
        if (KEY_VALIDATION_PATTERN.matcher(str).find()) {
            return true;
        }
        Log.e(TAG, "The key for adding custom key value pair should only contain alphanumeric, underscore and period. The first character can only be alphabet and the last character can only not be period");
        this.mNumInvalidKeyValuePairs++;
        return false;
    }
}
