package cerence.ark.assistant.api.context.grpc;

import cerence.ark.assistant.api.context.BaseNluContext;
import cerence.ark.assistant.api.context.LatLng;
import cerence.ark.assistant.common.Logger;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class NavigationContextGrpc extends BaseNluContext {
    private static final String CONTEXT_KEY = "navigation_context";
    private static final String TAG = "NavigationContextGrpc";
    private final LatLng mCurrentLocation;
    private final LocationSourceGrpc mCurrentLocationSource;
    private final LatLng mDestinationLocation;
    private final int mHeading;
    private final LatLng mOriginLocation;
    private final List<LatLng> mRouteList;
    private final LatLng mTargetLocation;

    @Override // cerence.ark.assistant.api.context.BaseNluContext
    public String getNluContextJsonKey() {
        return CONTEXT_KEY;
    }

    private NavigationContextGrpc(Builder builder) {
        this.mCurrentLocation = builder.mCurrentLocation;
        this.mCurrentLocationSource = builder.mCurrentLocationSource;
        this.mOriginLocation = builder.mOriginLocation;
        this.mDestinationLocation = builder.mDestinationLocation;
        this.mTargetLocation = builder.mTargetLocation;
        this.mRouteList = builder.mRouteList;
        this.mHeading = builder.mHeading;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // cerence.ark.assistant.api.context.BaseNluContext
    public JSONObject getNluContextJsonData() throws JSONException {
        if (this.mCurrentLocation == null) {
            Logger.error(TAG, String.format("%s: current location must not be null", "getNluContextJsonData"));
            return null;
        }
        if (this.mCurrentLocationSource == null) {
            Logger.error(TAG, String.format("%s: current source must not be null", "getNluContextJsonData"));
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("current_location", new JSONObject(this.mCurrentLocation.toJsonString()));
        jSONObject.put("current_location_source", this.mCurrentLocationSource.getValue());
        if (this.mOriginLocation != null) {
            jSONObject.put("origin_location", new JSONObject(this.mOriginLocation.toJsonString()));
        }
        if (this.mDestinationLocation != null) {
            jSONObject.put("destination_location", new JSONObject(this.mDestinationLocation.toJsonString()));
        }
        if (this.mTargetLocation != null) {
            jSONObject.put("target_location", new JSONObject(this.mTargetLocation.toJsonString()));
        }
        List<LatLng> list = this.mRouteList;
        if (list != null && !list.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (LatLng latLng : this.mRouteList) {
                if (latLng != null) {
                    sb.append(latLng.getLatitude()).append(",").append(latLng.getLongitude()).append("|");
                }
            }
            int length = sb.length();
            if (length > 0) {
                sb.deleteCharAt(length - 1);
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("pipe_delimited_points", sb.toString());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("route", jSONObject2);
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("pipe_delimited_route_info", jSONObject3);
            jSONObject.put("route_info", jSONObject4);
        }
        jSONObject.put("heading", this.mHeading);
        return jSONObject;
    }

    public static class Builder {
        private LatLng mCurrentLocation;
        private LocationSourceGrpc mCurrentLocationSource;
        private LatLng mDestinationLocation;
        private int mHeading;
        private LatLng mOriginLocation;
        private List<LatLng> mRouteList = Collections.emptyList();
        private LatLng mTargetLocation;

        public Builder setCurrentLocation(LatLng latLng) {
            this.mCurrentLocation = latLng;
            return this;
        }

        public Builder setCurrentLocationSource(LocationSourceGrpc locationSourceGrpc) {
            this.mCurrentLocationSource = locationSourceGrpc;
            return this;
        }

        public Builder setOriginLocation(LatLng latLng) {
            this.mOriginLocation = latLng;
            return this;
        }

        public Builder setDestinationLocation(LatLng latLng) {
            this.mDestinationLocation = latLng;
            return this;
        }

        public Builder setTargetLocation(LatLng latLng) {
            this.mTargetLocation = latLng;
            return this;
        }

        public Builder setRouteList(List<LatLng> list) {
            this.mRouteList = list;
            return this;
        }

        public Builder setHeading(int i) {
            this.mHeading = i;
            return this;
        }

        public NavigationContextGrpc build() {
            return new NavigationContextGrpc(this);
        }
    }
}
