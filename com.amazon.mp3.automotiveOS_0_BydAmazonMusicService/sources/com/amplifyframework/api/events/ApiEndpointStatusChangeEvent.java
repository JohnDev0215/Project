package com.amplifyframework.api.events;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.hub.HubEvent;

/* JADX INFO: loaded from: classes5.dex */
public final class ApiEndpointStatusChangeEvent implements HubEvent.Data<ApiEndpointStatusChangeEvent> {
    private final ApiEndpointStatus currentStatus;
    private final ApiEndpointStatus previousStatus;

    public ApiEndpointStatusChangeEvent(ApiEndpointStatus apiEndpointStatus, ApiEndpointStatus apiEndpointStatus2) {
        this.currentStatus = apiEndpointStatus;
        this.previousStatus = apiEndpointStatus2;
    }

    public ApiEndpointStatus getCurrentStatus() {
        return this.currentStatus;
    }

    public ApiEndpointStatus getPreviousStatus() {
        return this.previousStatus;
    }

    public int hashCode() {
        ApiEndpointStatus apiEndpointStatus = this.currentStatus;
        int iHashCode = (apiEndpointStatus != null ? apiEndpointStatus.hashCode() : 0) * 31;
        ApiEndpointStatus apiEndpointStatus2 = this.previousStatus;
        return iHashCode + (apiEndpointStatus2 != null ? apiEndpointStatus2.hashCode() : 0);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ApiEndpointStatusChangeEvent.class != obj.getClass()) {
            return false;
        }
        ApiEndpointStatusChangeEvent apiEndpointStatusChangeEvent = (ApiEndpointStatusChangeEvent) obj;
        if (ObjectsCompat.equals(this.currentStatus, apiEndpointStatusChangeEvent.currentStatus)) {
            return ObjectsCompat.equals(this.previousStatus, apiEndpointStatusChangeEvent.previousStatus);
        }
        return false;
    }

    @NonNull
    public String toString() {
        return "ApiEndpointStatusChangeEvent{currentStatus=" + this.currentStatus + ", previousStatus=" + this.previousStatus + "}";
    }

    @Override // com.amplifyframework.hub.HubEvent.Data
    public HubEvent<ApiEndpointStatusChangeEvent> toHubEvent() {
        return HubEvent.create(ApiChannelEventName.API_ENDPOINT_STATUS_CHANGED, this);
    }

    public static ApiEndpointStatusChangeEvent from(HubEvent<?> hubEvent) throws AmplifyException {
        if (hubEvent.getData() instanceof ApiEndpointStatusChangeEvent) {
            return (ApiEndpointStatusChangeEvent) hubEvent.getData();
        }
        String simpleName = ApiEndpointStatusChangeEvent.class.getSimpleName();
        throw new AmplifyException("Unable to cast event data from " + simpleName, "Ensure that the event payload is of type " + simpleName);
    }

    public enum ApiEndpointStatus {
        UNKOWN,
        REACHABLE,
        NOT_REACHABLE;

        public ApiEndpointStatusChangeEvent transitionTo(ApiEndpointStatus apiEndpointStatus) {
            return new ApiEndpointStatusChangeEvent(apiEndpointStatus, this);
        }
    }
}
