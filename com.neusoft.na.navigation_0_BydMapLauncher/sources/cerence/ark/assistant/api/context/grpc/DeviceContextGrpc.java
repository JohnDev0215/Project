package cerence.ark.assistant.api.context.grpc;

import cerence.ark.assistant.api.context.BaseNluContext;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceContextGrpc extends BaseNluContext {
    private static final String CONTEXT_KEY = "device_context";
    private final boolean mEvPluggable;
    private final String mFuelGrade;
    private final String mFuelTypePrimary;
    private final String mFuelTypeSecondary;
    private final String mVehicleModel;
    private final String mVehicleModelCode;

    @Override // cerence.ark.assistant.api.context.BaseNluContext
    public String getNluContextJsonKey() {
        return CONTEXT_KEY;
    }

    private DeviceContextGrpc(Builder builder) {
        this.mVehicleModel = builder.mVehicleModel;
        this.mVehicleModelCode = builder.mVehicleModelCode;
        this.mEvPluggable = builder.mEvPluggable;
        this.mFuelTypePrimary = builder.mFuelTypePrimary;
        this.mFuelTypeSecondary = builder.mFuelTypeSecondary;
        this.mFuelGrade = builder.mFuelGrade;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // cerence.ark.assistant.api.context.BaseNluContext
    public JSONObject getNluContextJsonData() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("vehicle_model", this.mVehicleModel);
        jSONObject.put("vehicle_model_code", this.mVehicleModelCode);
        jSONObject.put("ev_pluggable", this.mEvPluggable);
        jSONObject.put("fuel_type_primary", this.mFuelTypePrimary);
        jSONObject.put("fuel_type_secondary", this.mFuelTypeSecondary);
        jSONObject.put("fuel_grade", this.mFuelGrade);
        return jSONObject;
    }

    private static class EmptyDeviceContextGrpc extends DeviceContextGrpc {
        private EmptyDeviceContextGrpc(Builder builder) {
            super(builder);
        }

        @Override // cerence.ark.assistant.api.context.grpc.DeviceContextGrpc, cerence.ark.assistant.api.context.BaseNluContext
        public JSONObject getNluContextJsonData() {
            return new JSONObject();
        }
    }

    public static class Builder {
        private boolean mEvPluggable;
        private String mFuelGrade;
        private String mFuelTypePrimary;
        private String mFuelTypeSecondary;
        private String mVehicleModel;
        private String mVehicleModelCode;

        private Builder() {
        }

        public Builder setVehicleModel(String str) {
            this.mVehicleModel = str;
            return this;
        }

        public Builder setVehicleModelCode(String str) {
            this.mVehicleModelCode = str;
            return this;
        }

        public Builder setEvPluggable(boolean z) {
            this.mEvPluggable = z;
            return this;
        }

        public Builder setFuelTypePrimary(String str) {
            this.mFuelTypePrimary = str;
            return this;
        }

        public Builder setFuelTypeSecondary(String str) {
            this.mFuelTypeSecondary = str;
            return this;
        }

        public Builder setFuelGrade(String str) {
            this.mFuelGrade = str;
            return this;
        }

        public DeviceContextGrpc build() {
            return new DeviceContextGrpc(this);
        }

        public DeviceContextGrpc buildEmptyDeviceContextGrpc() {
            return new EmptyDeviceContextGrpc(this);
        }
    }
}
