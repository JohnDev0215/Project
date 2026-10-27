package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.storage.sqlite.SQLiteCommandFactory;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@Index(fields = {"customerId", "queueId", "id"}, name = SQLiteCommandFactory.UNDEFINED)
@ModelConfig(pluralName = "MetricsContexts")
public final class MetricsContext implements Model {

    @ModelField(isRequired = true, targetType = "AWSDateTime")
    private final Temporal.DateTime createdAt;

    @ModelField(isRequired = true, targetType = "ID")
    private final String customerId;

    @ModelField(isRequired = true, targetType = "ID")
    private final String id;

    @ModelField(isRequired = true, targetType = "MetricsContextTuple")
    private final List<MetricsContextTuple> metricsContext;

    @ModelField(isRequired = true, targetType = "ID")
    private final String queueId;

    @ModelField(isRequired = true, targetType = "AWSDateTime")
    private final Temporal.DateTime updatedAt;
    public static final QueryField ID = QueryField.field("MetricsContext", "id");
    public static final QueryField CUSTOMER_ID = QueryField.field("MetricsContext", "customerId");
    public static final QueryField QUEUE_ID = QueryField.field("MetricsContext", "queueId");
    public static final QueryField METRICS_CONTEXT = QueryField.field("MetricsContext", "metricsContext");
    public static final QueryField CREATED_AT = QueryField.field("MetricsContext", "createdAt");
    public static final QueryField UPDATED_AT = QueryField.field("MetricsContext", "updatedAt");

    public interface BuildStep {
    }

    public static class Builder implements CustomerIdStep, QueueIdStep, MetricsContextStep, CreatedAtStep, UpdatedAtStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface CreatedAtStep {
    }

    public interface CustomerIdStep {
    }

    public interface MetricsContextStep {
    }

    public interface QueueIdStep {
    }

    public interface UpdatedAtStep {
    }

    public String getId() {
        return this.id;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getQueueId() {
        return this.queueId;
    }

    public List<MetricsContextTuple> getMetricsContext() {
        return this.metricsContext;
    }

    public Temporal.DateTime getCreatedAt() {
        return this.createdAt;
    }

    public Temporal.DateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MetricsContext.class != obj.getClass()) {
            return false;
        }
        MetricsContext metricsContext = (MetricsContext) obj;
        return ObjectsCompat.equals(getId(), metricsContext.getId()) && ObjectsCompat.equals(getCustomerId(), metricsContext.getCustomerId()) && ObjectsCompat.equals(getQueueId(), metricsContext.getQueueId()) && ObjectsCompat.equals(getMetricsContext(), metricsContext.getMetricsContext()) && ObjectsCompat.equals(getCreatedAt(), metricsContext.getCreatedAt()) && ObjectsCompat.equals(getUpdatedAt(), metricsContext.getUpdatedAt());
    }

    public int hashCode() {
        return (getId() + getCustomerId() + getQueueId() + getMetricsContext() + getCreatedAt() + getUpdatedAt()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MetricsContext {");
        sb.append("id=" + String.valueOf(getId()) + ", ");
        sb.append("customerId=" + String.valueOf(getCustomerId()) + ", ");
        sb.append("queueId=" + String.valueOf(getQueueId()) + ", ");
        sb.append("metricsContext=" + String.valueOf(getMetricsContext()) + ", ");
        sb.append("createdAt=" + String.valueOf(getCreatedAt()) + ", ");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("updatedAt=");
        sb2.append(String.valueOf(getUpdatedAt()));
        sb.append(sb2.toString());
        sb.append("}");
        return sb.toString();
    }
}
