package cerence.ark.assistant.api.context.grpc;

import cerence.ark.assistant.api.context.BaseNluContext;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class VehicleContextGrpc extends BaseNluContext {
    private static final String CONTEXT_KEY = "vehicle_context";
    private final float mAverageSpeed15Min;
    private final float mCurrentSpeed;
    private final float mDieselExhaustFluidLevel;
    private final boolean mFrostDetected;
    private final float mHeight;
    private final int mOutsideAqi;
    private final float mOutsideTemperature;
    private final int mPassengerCount;
    private final boolean mRainDetected;
    private final float mRange;
    private final boolean mWindowOpen;
    private final boolean mWindshieldWipersActive;
    private final float mWindshieldWipersFluidLevel;

    @Override // cerence.ark.assistant.api.context.BaseNluContext
    public String getNluContextJsonKey() {
        return CONTEXT_KEY;
    }

    private VehicleContextGrpc(Builder builder) {
        this.mOutsideTemperature = builder.mOutsideTemperature;
        this.mRainDetected = builder.mRainDetected;
        this.mFrostDetected = builder.mFrostDetected;
        this.mOutsideAqi = builder.mOutsideAqi;
        this.mWindowOpen = builder.mWindowOpen;
        this.mWindshieldWipersFluidLevel = builder.mWindshieldWipersFluidLevel;
        this.mWindshieldWipersActive = builder.mWindshieldWipersActive;
        this.mDieselExhaustFluidLevel = builder.mDieselExhaustFluidLevel;
        this.mCurrentSpeed = builder.mCurrentSpeed;
        this.mAverageSpeed15Min = builder.mAverageSpeed15Min;
        this.mHeight = builder.mHeight;
        this.mRange = builder.mRange;
        this.mPassengerCount = builder.mPassengerCount;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // cerence.ark.assistant.api.context.BaseNluContext
    public JSONObject getNluContextJsonData() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("outside_temperature_celsius", this.mOutsideTemperature);
        jSONObject.put("rain_detected", this.mRainDetected);
        jSONObject.put("frost_detected", this.mFrostDetected);
        jSONObject.put("outside_aqi", this.mOutsideAqi);
        jSONObject.put("window_open", this.mWindowOpen);
        jSONObject.put("windshield_wipers_fluid_level", this.mWindshieldWipersFluidLevel);
        jSONObject.put("windshield_wipers_active", this.mWindshieldWipersActive);
        jSONObject.put("diesel_exhaust_fluid_level_percentage", this.mDieselExhaustFluidLevel);
        jSONObject.put("current_speed_km_hr", this.mCurrentSpeed);
        jSONObject.put("average_speed_15_min_km_hr", this.mAverageSpeed15Min);
        jSONObject.put("height_cm", this.mHeight);
        jSONObject.put("range_km", this.mRange);
        jSONObject.put("passenger_count", this.mPassengerCount);
        return jSONObject;
    }

    private static class EmptyVehicleContextGrpc extends VehicleContextGrpc {
        private EmptyVehicleContextGrpc(Builder builder) {
            super(builder);
        }

        @Override // cerence.ark.assistant.api.context.grpc.VehicleContextGrpc, cerence.ark.assistant.api.context.BaseNluContext
        public JSONObject getNluContextJsonData() {
            return new JSONObject();
        }
    }

    public static class Builder {
        private float mAverageSpeed15Min;
        private float mCurrentSpeed;
        private float mDieselExhaustFluidLevel;
        private boolean mFrostDetected;
        private float mHeight;
        private int mOutsideAqi;
        private float mOutsideTemperature;
        private int mPassengerCount;
        private boolean mRainDetected;
        private float mRange;
        private boolean mWindowOpen;
        private boolean mWindshieldWipersActive;
        private float mWindshieldWipersFluidLevel;

        public Builder setOutsideTemperature(float f) {
            this.mOutsideTemperature = f;
            return this;
        }

        public Builder setRainDetected(boolean z) {
            this.mRainDetected = z;
            return this;
        }

        public Builder setFrostDetected(boolean z) {
            this.mFrostDetected = z;
            return this;
        }

        public Builder setOutsideAqi(int i) {
            this.mOutsideAqi = i;
            return this;
        }

        public Builder setWindowOpen(boolean z) {
            this.mWindowOpen = z;
            return this;
        }

        public Builder setWindshieldWipersFluidLevel(float f) {
            this.mWindshieldWipersFluidLevel = f;
            return this;
        }

        public Builder setWindshieldWipersActive(boolean z) {
            this.mWindshieldWipersActive = z;
            return this;
        }

        public Builder setDieselExhaustFluidLevel(float f) {
            this.mDieselExhaustFluidLevel = f;
            return this;
        }

        public Builder setCurrentSpeed(float f) {
            this.mCurrentSpeed = f;
            return this;
        }

        public Builder setAverageSpeed15Min(float f) {
            this.mAverageSpeed15Min = f;
            return this;
        }

        public Builder setHeight(float f) {
            this.mHeight = f;
            return this;
        }

        public Builder setRange(float f) {
            this.mRange = f;
            return this;
        }

        public Builder setPassengerCount(int i) {
            this.mPassengerCount = i;
            return this;
        }

        public VehicleContextGrpc build() {
            return new VehicleContextGrpc(this);
        }

        public VehicleContextGrpc buildEmptyVehicleContextGrpc() {
            return new EmptyVehicleContextGrpc(this);
        }
    }
}
