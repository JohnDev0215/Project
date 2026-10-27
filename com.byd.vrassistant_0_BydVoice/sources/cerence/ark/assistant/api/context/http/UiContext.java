package cerence.ark.assistant.api.context.http;

import cerence.ark.assistant.api.context.BaseNluContext;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UiContext extends BaseNluContext {
    private static final String CONTEXT_KEY = "uiContext";
    private final DistanceExpression mDistanceExpression;
    private final TemperatureExpression mTemperatureExpression;
    private final TimeExpression mTimeExpression;
    private final VolumeExpression mVolumeExpression;

    @Override // cerence.ark.assistant.api.context.BaseNluContext
    public String getNluContextJsonKey() {
        return CONTEXT_KEY;
    }

    private UiContext(Builder builder) {
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
            jSONObject.put("timeExpression", timeExpression.getValue());
        }
        DistanceExpression distanceExpression = this.mDistanceExpression;
        if (distanceExpression != null) {
            jSONObject.put("distanceExpression", distanceExpression.getValue());
        }
        TemperatureExpression temperatureExpression = this.mTemperatureExpression;
        if (temperatureExpression != null) {
            jSONObject.put("temperatureExpression", temperatureExpression.getValue());
        }
        VolumeExpression volumeExpression = this.mVolumeExpression;
        if (volumeExpression != null) {
            jSONObject.put("volumeExpression", volumeExpression.getValue());
        }
        return jSONObject;
    }

    public enum TimeExpression {
        HR_12("hr12"),
        HR_24("hr24");

        private final String mValue;

        TimeExpression(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    public enum DistanceExpression {
        METER("meter"),
        FOOT("foot"),
        YARD("yard");

        private final String mValue;

        DistanceExpression(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    public enum TemperatureExpression {
        CELSIUS("celsius"),
        FAHRENHEIT("fahrenheit");

        private final String mValue;

        TemperatureExpression(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    public enum VolumeExpression {
        LITER("liter"),
        USGAL("usgal"),
        IMPGAL("impgal");

        private final String mValue;

        VolumeExpression(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    private static class EmptyUiContext extends UiContext {
        private EmptyUiContext(Builder builder) {
            super(builder);
        }

        @Override // cerence.ark.assistant.api.context.http.UiContext, cerence.ark.assistant.api.context.BaseNluContext
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

        public UiContext build() {
            return new UiContext(this);
        }

        public UiContext buildEmptyUiContext() {
            return new EmptyUiContext(this);
        }
    }
}
