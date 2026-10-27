package cerence.ark.assistant.api.context.grpc;

import cerence.ark.assistant.api.context.BaseNluContext;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UiContextGrpc extends BaseNluContext {
    private static final String CONTEXT_KEY = "ui_context";
    private final DistanceExpression mDistanceExpression;
    private final TemperatureExpression mTemperatureExpression;
    private final TimeExpression mTimeExpression;
    private final VolumeExpression mVolumeExpression;

    @Override // cerence.ark.assistant.api.context.BaseNluContext
    public String getNluContextJsonKey() {
        return CONTEXT_KEY;
    }

    private UiContextGrpc(Builder builder) {
        this.mTimeExpression = builder.mTimeExpression;
        this.mDistanceExpression = builder.mDistanceExpression;
        this.mTemperatureExpression = builder.mTemperatureExpression;
        this.mVolumeExpression = builder.mVolumeExpression;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // cerence.ark.assistant.api.context.BaseNluContext
    public JSONObject getNluContextJsonData() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        TimeExpression timeExpression = this.mTimeExpression;
        if (timeExpression != null) {
            jSONObject.put("time_expression", timeExpression.getValue());
        }
        DistanceExpression distanceExpression = this.mDistanceExpression;
        if (distanceExpression != null) {
            jSONObject.put("distance_expression", distanceExpression.getValue());
        }
        TemperatureExpression temperatureExpression = this.mTemperatureExpression;
        if (temperatureExpression != null) {
            jSONObject.put("temperature_expression", temperatureExpression.getValue());
        }
        VolumeExpression volumeExpression = this.mVolumeExpression;
        if (volumeExpression != null) {
            jSONObject.put("volume_expression", volumeExpression.getValue());
        }
        return jSONObject;
    }

    public enum TimeExpression {
        HR_12("TIME_EXPRESSION_HR12"),
        HR_24("TIME_EXPRESSION_HR24");

        private final String mValue;

        TimeExpression(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    public enum DistanceExpression {
        METER("DISTANCE_EXPRESSION_METER"),
        FOOT("DISTANCE_EXPRESSION_FOOT"),
        YARD("DISTANCE_EXPRESSION_YARD");

        private final String mValue;

        DistanceExpression(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    public enum TemperatureExpression {
        CELSIUS("TEMPERATURE_EXPRESSION_CELSIUS"),
        FAHRENHEIT("TEMPERATURE_EXPRESSION_FAHRENHEIT");

        private final String mValue;

        TemperatureExpression(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    public enum VolumeExpression {
        LITER("VOLUME_EXPRESSION_LITER"),
        USGAL("VOLUME_EXPRESSION_USGAL"),
        IMPGAL("VOLUME_EXPRESSION_IMPGAL");

        private final String mValue;

        VolumeExpression(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    private static class EmptyUiContextGrpc extends UiContextGrpc {
        private EmptyUiContextGrpc(Builder builder) {
            super(builder);
        }

        @Override // cerence.ark.assistant.api.context.grpc.UiContextGrpc, cerence.ark.assistant.api.context.BaseNluContext
        public JSONObject getNluContextJsonData() {
            return new JSONObject();
        }
    }

    public static class Builder {
        private DistanceExpression mDistanceExpression;
        private TemperatureExpression mTemperatureExpression;
        private TimeExpression mTimeExpression;
        private VolumeExpression mVolumeExpression;

        public Builder setTimeExpression(TimeExpression timeExpression) {
            this.mTimeExpression = timeExpression;
            return this;
        }

        public Builder setDistanceExpression(DistanceExpression distanceExpression) {
            this.mDistanceExpression = distanceExpression;
            return this;
        }

        public Builder setTemperatureExpression(TemperatureExpression temperatureExpression) {
            this.mTemperatureExpression = temperatureExpression;
            return this;
        }

        public Builder setVolumeExpression(VolumeExpression volumeExpression) {
            this.mVolumeExpression = volumeExpression;
            return this;
        }

        public UiContextGrpc build() {
            return new UiContextGrpc(this);
        }

        public UiContextGrpc buildEmptyUiContextGrpc() {
            return new EmptyUiContextGrpc(this);
        }
    }
}
