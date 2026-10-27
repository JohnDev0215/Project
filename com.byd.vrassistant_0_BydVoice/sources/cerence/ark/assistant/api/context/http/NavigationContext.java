package cerence.ark.assistant.api.context.http;

import cerence.ark.assistant.api.context.BaseNluContext;
import cerence.ark.assistant.api.context.LatLng;
import cerence.ark.assistant.common.Logger;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class NavigationContext extends BaseNluContext {
    private static final String CONTEXT_KEY = "navigationContext";
    private static final String TAG = "NavigationContext";
    private final LatLng mCurrentLocation;
    private final LocationSource mCurrentLocationSource;
    private final LatLng mDestinationLocation;
    private final int mHeading;
    private final LatLng mOriginLocation;
    private final List<LatLng> mRouteList;
    private final LatLng mTargetLocation;

    @Override // cerence.ark.assistant.api.context.BaseNluContext
    public String getNluContextJsonKey() {
        return CONTEXT_KEY;
    }

    private NavigationContext(Builder builder) {
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
        jSONObject.put("currentLocation", new JSONObject(this.mCurrentLocation.toJsonString()));
        jSONObject.put("currentLocationSource", this.mCurrentLocationSource.getValue());
        if (this.mOriginLocation != null) {
            jSONObject.put("originLocation", new JSONObject(this.mOriginLocation.toJsonString()));
        }
        if (this.mDestinationLocation != null) {
            jSONObject.put("destinationLocation", new JSONObject(this.mDestinationLocation.toJsonString()));
        }
        if (this.mTargetLocation != null) {
            jSONObject.put("targetLocation", new JSONObject(this.mTargetLocation.toJsonString()));
        }
        List<LatLng> list = this.mRouteList;
        if (list != null && !list.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            for (LatLng latLng : this.mRouteList) {
                if (latLng != null) {
                    jSONArray.put(new JSONObject(latLng.toJsonString()));
                }
            }
            jSONObject.put("route", jSONArray);
        }
        jSONObject.put("heading", this.mHeading);
        return jSONObject;
    }

    public static class Builder {
        private LatLng mCurrentLocation;
        private LocationSource mCurrentLocationSource;
        private LatLng mDestinationLocation;
        private int mHeading;
        private LatLng mOriginLocation;
        private List<LatLng> mRouteList = Collections.emptyList();
        private LatLng mTargetLocation;

        public Builder setCurrentLocation(LatLng latLng) {
            this.mCurrentLocation = latLng;
            return this;
        }

        public Builder setCurrentLocationSource(LocationSource locationSource) {
            this.mCurrentLocationSource = locationSource;
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

        public NavigationContext build() {
            return new NavigationContext(this);
        }
    }
}
