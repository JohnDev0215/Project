package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.storage.sqlite.SQLiteCommandFactory;

/* JADX INFO: loaded from: classes3.dex */
@Index(fields = {"customerId", Splash.PARAMS_DEVICE_ID, Splash.PARAMS_DEVICE_TYPE}, name = SQLiteCommandFactory.UNDEFINED)
@ModelConfig(pluralName = "ProposedDevicePlaybackStates")
public final class ProposedDevicePlaybackState implements Model {

    @ModelField(isRequired = true, targetType = "AWSDateTime")
    private final Temporal.DateTime createdAt;

    @ModelField(isRequired = true, targetType = "ID")
    private final String customerId;

    @ModelField(isRequired = true, targetType = "String")
    private final String deviceId;

    @ModelField(isRequired = true, targetType = "String")
    private final String deviceType;

    @ModelField(isRequired = true, targetType = "ID")
    private final String id;

    @ModelField(targetType = "DevicePlaybackStateProposal")
    private final DevicePlaybackStateProposal proposal;

    @ModelField(isRequired = true, targetType = "AWSDateTime")
    private final Temporal.DateTime updatedAt;
    public static final QueryField ID = QueryField.field("ProposedDevicePlaybackState", "id");
    public static final QueryField DEVICE_ID = QueryField.field("ProposedDevicePlaybackState", Splash.PARAMS_DEVICE_ID);
    public static final QueryField DEVICE_TYPE = QueryField.field("ProposedDevicePlaybackState", Splash.PARAMS_DEVICE_TYPE);
    public static final QueryField CUSTOMER_ID = QueryField.field("ProposedDevicePlaybackState", "customerId");
    public static final QueryField PROPOSAL = QueryField.field("ProposedDevicePlaybackState", "proposal");
    public static final QueryField CREATED_AT = QueryField.field("ProposedDevicePlaybackState", "createdAt");
    public static final QueryField UPDATED_AT = QueryField.field("ProposedDevicePlaybackState", "updatedAt");

    public interface BuildStep {
    }

    public static class Builder implements DeviceIdStep, DeviceTypeStep, CustomerIdStep, CreatedAtStep, UpdatedAtStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface CreatedAtStep {
    }

    public interface CustomerIdStep {
    }

    public interface DeviceIdStep {
    }

    public interface DeviceTypeStep {
    }

    public interface UpdatedAtStep {
    }

    public String getId() {
        return this.id;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public DevicePlaybackStateProposal getProposal() {
        return this.proposal;
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
        if (obj == null || ProposedDevicePlaybackState.class != obj.getClass()) {
            return false;
        }
        ProposedDevicePlaybackState proposedDevicePlaybackState = (ProposedDevicePlaybackState) obj;
        return ObjectsCompat.equals(getId(), proposedDevicePlaybackState.getId()) && ObjectsCompat.equals(getDeviceId(), proposedDevicePlaybackState.getDeviceId()) && ObjectsCompat.equals(getDeviceType(), proposedDevicePlaybackState.getDeviceType()) && ObjectsCompat.equals(getCustomerId(), proposedDevicePlaybackState.getCustomerId()) && ObjectsCompat.equals(getProposal(), proposedDevicePlaybackState.getProposal()) && ObjectsCompat.equals(getCreatedAt(), proposedDevicePlaybackState.getCreatedAt()) && ObjectsCompat.equals(getUpdatedAt(), proposedDevicePlaybackState.getUpdatedAt());
    }

    public int hashCode() {
        return (getId() + getDeviceId() + getDeviceType() + getCustomerId() + getProposal() + getCreatedAt() + getUpdatedAt()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProposedDevicePlaybackState {");
        sb.append("id=" + String.valueOf(getId()) + ", ");
        sb.append("deviceId=" + String.valueOf(getDeviceId()) + ", ");
        sb.append("deviceType=" + String.valueOf(getDeviceType()) + ", ");
        sb.append("customerId=" + String.valueOf(getCustomerId()) + ", ");
        sb.append("proposal=" + String.valueOf(getProposal()) + ", ");
        sb.append("createdAt=" + String.valueOf(getCreatedAt()) + ", ");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("updatedAt=");
        sb2.append(String.valueOf(getUpdatedAt()));
        sb.append(sb2.toString());
        sb.append("}");
        return sb.toString();
    }
}
