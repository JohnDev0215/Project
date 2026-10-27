package com.amazon.mp3.service.metrics.mts.events;

import android.os.Build;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.service.error.CirrusError;
import com.amazon.mp3.service.error.CirrusErrorLogger;
import com.amazon.music.inappmessaging.external.DeviceInformation;
import com.amazon.music.inappmessaging.internal.model.Splash;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class ReportCirrusErrorEvent {
    private String mDescription;
    private CirrusError mError;

    public ReportCirrusErrorEvent(CirrusError error, String description) {
        this.mError = error;
        this.mDescription = description;
    }

    public void trackEvent() {
        try {
            CirrusErrorLogger.getInstance().log(this.mError, getAttributes().toString() + ": " + this.mDescription);
        } catch (JSONException e) {
            LoggerFactory.getLogger(ReportCirrusErrorEvent.class.getSimpleName()).error("Unable to report error information to Cirrus", (Throwable) e);
        }
    }

    public JSONObject getAttributes() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        ApplicationAttributes applicationAttributes = ApplicationAttributes.getInstance(AmazonApplication.getContext());
        jSONObject.putOpt(Splash.PARAMS_APP_VERSION, applicationAttributes.getVersion());
        jSONObject.putOpt("appBuild", String.valueOf(applicationAttributes.getVersionCode()));
        jSONObject.putOpt(DeviceInformation.PARAMS_DEVICE_MODEL, Build.MODEL);
        jSONObject.putOpt("firmwareVersion", Build.VERSION.RELEASE);
        jSONObject.putOpt("deviceBuildVersion", Build.DISPLAY);
        return jSONObject;
    }
}
