package com.android.car;

import android.car.settings.ICarConfigurationManager;
import android.car.settings.SpeedBumpConfiguration;
import android.content.Context;
import android.util.Log;
import java.io.PrintWriter;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class CarConfigurationService extends ICarConfigurationManager.Stub implements CarServiceBase {
    static final double DEFAULT_SPEED_BUMP_ACQUIRED_PERMITS_PER_SECOND = 0.5d;
    static final double DEFAULT_SPEED_BUMP_MAX_PERMIT_POOL = 5.0d;
    static final long DEFAULT_SPEED_BUMP_PERMIT_FILL_DELAY = 600;
    static final String SPEED_BUMP_ACQUIRED_PERMITS_PER_SECOND_KEY = "acquiredPermitsPerSecond";
    static final String SPEED_BUMP_CONFIG_KEY = "SpeedBump";
    static final String SPEED_BUMP_MAX_PERMIT_POOL_KEY = "maxPermitPool";
    static final String SPEED_BUMP_PERMIT_FILL_DELAY_KEY = "permitFillDelay";
    private static final String TAG = "CarConfigurationService";
    JSONObject mConfigFile;
    private final Context mContext;
    private final JsonReader mJsonReader;
    private SpeedBumpConfiguration mSpeedBumpConfiguration;

    interface JsonReader {
        String jsonFileToString(Context context, int i);
    }

    CarConfigurationService(Context context, JsonReader reader) {
        this.mContext = context;
        this.mJsonReader = reader;
    }

    public SpeedBumpConfiguration getSpeedBumpConfiguration() {
        SpeedBumpConfiguration speedBumpConfiguration = this.mSpeedBumpConfiguration;
        if (speedBumpConfiguration == null) {
            return getDefaultSpeedBumpConfiguration();
        }
        return speedBumpConfiguration;
    }

    @Override // com.android.car.CarServiceBase
    public synchronized void init() {
        String jsonString = this.mJsonReader.jsonFileToString(this.mContext, R.raw.car_config);
        if (jsonString != null) {
            try {
                this.mConfigFile = new JSONObject(jsonString);
            } catch (JSONException e) {
                Log.e(TAG, "Error reading JSON file", e);
            }
            this.mSpeedBumpConfiguration = createSpeedBumpConfiguration();
        } else {
            this.mSpeedBumpConfiguration = createSpeedBumpConfiguration();
        }
    }

    @Override // com.android.car.CarServiceBase
    public synchronized void release() {
        this.mConfigFile = null;
        this.mSpeedBumpConfiguration = null;
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        writer.println("*CarConfigurationService*");
        StringBuilder sb = new StringBuilder();
        sb.append("Config value initialized: ");
        sb.append(this.mConfigFile != null);
        writer.println(sb.toString());
        if (this.mConfigFile != null) {
            try {
                writer.println("Config: " + this.mConfigFile.toString(2));
            } catch (JSONException e) {
                Log.e(TAG, "Error printing JSON config", e);
                writer.println("Config: " + this.mConfigFile);
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("SpeedBumpConfig initialized: ");
        sb2.append(this.mSpeedBumpConfiguration != null);
        writer.println(sb2.toString());
        if (this.mSpeedBumpConfiguration != null) {
            writer.println("SpeedBumpConfig: " + this.mSpeedBumpConfiguration);
        }
    }

    private SpeedBumpConfiguration createSpeedBumpConfiguration() {
        JSONObject jSONObject = this.mConfigFile;
        if (jSONObject == null) {
            return getDefaultSpeedBumpConfiguration();
        }
        try {
            JSONObject speedBumpJson = jSONObject.getJSONObject(SPEED_BUMP_CONFIG_KEY);
            if (speedBumpJson != null) {
                return new SpeedBumpConfiguration(speedBumpJson.getDouble(SPEED_BUMP_ACQUIRED_PERMITS_PER_SECOND_KEY), speedBumpJson.getDouble(SPEED_BUMP_MAX_PERMIT_POOL_KEY), speedBumpJson.getLong(SPEED_BUMP_PERMIT_FILL_DELAY_KEY));
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing SpeedBumpConfiguration; returning default values", e);
        }
        return getDefaultSpeedBumpConfiguration();
    }

    private SpeedBumpConfiguration getDefaultSpeedBumpConfiguration() {
        return new SpeedBumpConfiguration(DEFAULT_SPEED_BUMP_ACQUIRED_PERMITS_PER_SECOND, DEFAULT_SPEED_BUMP_MAX_PERMIT_POOL, DEFAULT_SPEED_BUMP_PERMIT_FILL_DELAY);
    }
}
