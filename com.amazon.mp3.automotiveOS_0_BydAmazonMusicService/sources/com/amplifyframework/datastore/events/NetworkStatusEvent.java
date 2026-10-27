package com.amplifyframework.datastore.events;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.events.ApiEndpointStatusChangeEvent;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.DataStoreChannelEventName;
import com.amplifyframework.hub.HubEvent;
import com.amplifyframework.logging.Logger;

/* JADX INFO: loaded from: classes5.dex */
public final class NetworkStatusEvent implements HubEvent.Data<NetworkStatusEvent> {
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-datastore");
    private final boolean active;

    public NetworkStatusEvent(boolean z) {
        this.active = z;
    }

    public boolean getActive() {
        return this.active;
    }

    public int hashCode() {
        return Boolean.valueOf(this.active).hashCode();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || NetworkStatusEvent.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(Boolean.valueOf(this.active), Boolean.valueOf(((NetworkStatusEvent) obj).active));
    }

    @NonNull
    public String toString() {
        return "NetworkStatus{active=" + this.active + "}";
    }

    @Override // com.amplifyframework.hub.HubEvent.Data
    public HubEvent<NetworkStatusEvent> toHubEvent() {
        return HubEvent.create(DataStoreChannelEventName.NETWORK_STATUS, this);
    }

    public static NetworkStatusEvent from(HubEvent<?> hubEvent) throws AmplifyException {
        if (hubEvent.getData() instanceof NetworkStatusEvent) {
            return (NetworkStatusEvent) hubEvent.getData();
        }
        String name = NetworkStatusEvent.class.getName();
        throw new AmplifyException("Unable to cast event data from " + name, "Ensure that the event payload is of type " + name);
    }

    public static NetworkStatusEvent from(ApiEndpointStatusChangeEvent apiEndpointStatusChangeEvent) {
        return new NetworkStatusEvent(ApiEndpointStatusChangeEvent.ApiEndpointStatus.REACHABLE.equals(apiEndpointStatusChangeEvent.getCurrentStatus()));
    }
}
