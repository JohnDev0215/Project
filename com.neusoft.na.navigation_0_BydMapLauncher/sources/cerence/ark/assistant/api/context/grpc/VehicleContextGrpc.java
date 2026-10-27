package cerence.ark.assistant.api.context.grpc;

import cerence.ark.assistant.api.context.BaseNluContext;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class VehicleContextGrpc extends BaseNluContext {
    private static final String CONTEXT_KEY = "vehicle_context";
    private final Double mAverageSpeed15Min;
    private final Double mCurrentSpeed;
    private final Double mDieselExhaustFluidLevelPercentage;
    private final Boolean mFrostDetected;
    private final Double mHeight;
    private final Double mOutsideAqi;
    private final Double mOutsideTemperature;
    private final Integer mPassengerCount;
    private final Boolean mRainDetected;
    private final Double mRange;
    private final Boolean mWindowOpen;
    private final Boolean mWindshieldWipersActive;
    private final Integer mWindshieldWipersFluidLevel;

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
        this.mDieselExhaustFluidLevelPercentage = builder.mDieselExhaustFluidLevelPercentage;
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
        jSONObject.put("diesel_exhaust_fluid_level_percentage", this.mDieselExhaustFluidLevelPercentage);
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
        private Double mAverageSpeed15Min;
        private Double mCurrentSpeed;
        private Double mDieselExhaustFluidLevelPercentage;
        private Boolean mFrostDetected;
        private Double mHeight;
        private Double mOutsideAqi;
        private Double mOutsideTemperature;
        private Integer mPassengerCount;
        private Boolean mRainDetected;
        private Double mRange;
        private Boolean mWindowOpen;
        private Boolean mWindshieldWipersActive;
        private Integer mWindshieldWipersFluidLevel;

        public Builder setOutsideTemperature(double d) {
            this.mOutsideTemperature = Double.valueOf(d);
            return this;
        }

        public Builder setRainDetected(boolean z) {
            this.mRainDetected = Boolean.valueOf(z);
            return this;
        }

        public Builder setFrostDetected(boolean z) {
            this.mFrostDetected = Boolean.valueOf(z);
            return this;
        }

        public Builder setOutsideAqi(double d) {
            this.mOutsideAqi = Double.valueOf(d);
            return this;
        }

        public Builder setWindowOpen(boolean z) {
            this.mWindowOpen = Boolean.valueOf(z);
            return this;
        }

        public Builder setWindshieldWipersFluidLevel(int i) {
            this.mWindshieldWipersFluidLevel = Integer.valueOf(i);
            return this;
        }

        public Builder setWindshieldWipersActive(boolean z) {
            this.mWindshieldWipersActive = Boolean.valueOf(z);
            return this;
        }

        public Builder setDieselExhaustFluidLevelPercentage(double d) {
            this.mDieselExhaustFluidLevelPercentage = Double.valueOf(d);
            return this;
        }

        public Builder setCurrentSpeed(double d) {
            this.mCurrentSpeed = Double.valueOf(d);
            return this;
        }

        public Builder setAverageSpeed15Min(double d) {
            this.mAverageSpeed15Min = Double.valueOf(d);
            return this;
        }

        public Builder setHeight(double d) {
            this.mHeight = Double.valueOf(d);
            return this;
        }

        public Builder setRange(double d) {
            this.mRange = Double.valueOf(d);
            return this;
        }

        public Builder setPassengerCount(int i) {
            this.mPassengerCount = Integer.valueOf(i);
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
