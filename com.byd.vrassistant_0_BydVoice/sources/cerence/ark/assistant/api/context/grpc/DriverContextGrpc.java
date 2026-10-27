package cerence.ark.assistant.api.context.grpc;

import cerence.ark.assistant.api.context.BaseNluContext;
import cerence.ark.assistant.api.context.LatLng;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class DriverContextGrpc extends BaseNluContext {
    private static final String CONTEXT_KEY = "driver_context";
    private final LatLng mHomeLocation;
    private final LatLng mWorkLocation;

    @Override // cerence.ark.assistant.api.context.BaseNluContext
    public String getNluContextJsonKey() {
        return CONTEXT_KEY;
    }

    private DriverContextGrpc(Builder builder) {
        this.mHomeLocation = builder.mHomeLocation;
        this.mWorkLocation = builder.mWorkLocation;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // cerence.ark.assistant.api.context.BaseNluContext
    public JSONObject getNluContextJsonData() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.mHomeLocation != null) {
            jSONObject.put("home_location", new JSONObject(this.mHomeLocation.toJsonString()));
        }
        if (this.mWorkLocation != null) {
            jSONObject.put("work_location", new JSONObject(this.mWorkLocation.toJsonString()));
        }
        return jSONObject;
    }

    private static class EmptyDriverContextGrpc extends DriverContextGrpc {
        private EmptyDriverContextGrpc(Builder builder) {
            super(builder);
        }

        @Override // cerence.ark.assistant.api.context.grpc.DriverContextGrpc, cerence.ark.assistant.api.context.BaseNluContext
        public JSONObject getNluContextJsonData() {
            return new JSONObject();
        }
    }

    public static class Builder {
        private LatLng mHomeLocation;
        private LatLng mWorkLocation;

        public Builder setHomeLocation(LatLng latLng) {
            this.mHomeLocation = latLng;
            return this;
        }

        public Builder setWorkLocation(LatLng latLng) {
            this.mWorkLocation = latLng;
            return this;
        }

        public DriverContextGrpc build() {
            return new DriverContextGrpc(this);
        }

        public DriverContextGrpc buildEmptyDriverContextGrpc() {
            return new EmptyDriverContextGrpc(this);
        }
    }
}
