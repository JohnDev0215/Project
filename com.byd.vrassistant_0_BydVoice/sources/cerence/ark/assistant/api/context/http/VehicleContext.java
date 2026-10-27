package cerence.ark.assistant.api.context.http;

import cerence.ark.assistant.api.context.BaseNluContext;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class VehicleContext extends BaseNluContext {
    private static final String CONTEXT_KEY = "vehicleContext";
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

    private VehicleContext(Builder builder) {
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
        jSONObject.put("outsideTemperature", this.mOutsideTemperature);
        jSONObject.put("rainDetected", this.mRainDetected);
        jSONObject.put("frostDetected", this.mFrostDetected);
        jSONObject.put("outsideAqi", this.mOutsideAqi);
        jSONObject.put("windowOpen", this.mWindowOpen);
        jSONObject.put("windshieldWipersFluidLevel", this.mWindshieldWipersFluidLevel);
        jSONObject.put("windshieldWipersActive", this.mWindshieldWipersActive);
        jSONObject.put("dieselExhaustFluidLevel", this.mDieselExhaustFluidLevel);
        jSONObject.put("currentSpeed", this.mCurrentSpeed);
        jSONObject.put("averageSpeed15Min", this.mAverageSpeed15Min);
        jSONObject.put("height", this.mHeight);
        jSONObject.put("range", this.mRange);
        jSONObject.put("passengerCount", this.mPassengerCount);
        return jSONObject;
    }

    private static class EmptyVehicleContext extends VehicleContext {
        private EmptyVehicleContext(Builder builder) {
            super(builder);
        }

        @Override // cerence.ark.assistant.api.context.http.VehicleContext, cerence.ark.assistant.api.context.BaseNluContext
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

        public VehicleContext build() {
            return new VehicleContext(this);
        }

        public VehicleContext buildEmptyVehicleContext() {
            return new EmptyVehicleContext(this);
        }
    }
}
