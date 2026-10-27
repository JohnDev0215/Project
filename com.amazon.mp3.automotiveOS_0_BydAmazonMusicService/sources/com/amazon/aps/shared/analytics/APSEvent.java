package com.amazon.aps.shared.analytics;

import android.content.Context;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import androidx.core.os.EnvironmentCompat;
import com.amazon.music.inappmessaging.external.DeviceInformation;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class APSEvent implements Serializable {
    String deviceManufacturer;
    String deviceModel;
    String eventType;
    String osName;
    int osVersion;
    String pkgName;
    String sdkVersion;
    APSEventSeverity severity;
    long timestamp;
    String configVersion = "";
    String errorDetails = "";
    String exceptionLog = "";

    public APSEvent build() {
        return this;
    }

    public APSEvent(Context context, APSEventSeverity aPSEventSeverity, String str) {
        this.pkgName = "";
        this.deviceManufacturer = "";
        this.deviceModel = "";
        try {
            this.sdkVersion = "1.0";
            this.osName = "Android";
            this.osVersion = Build.VERSION.SDK_INT;
            this.deviceManufacturer = Build.MANUFACTURER;
            this.deviceModel = Build.MODEL;
            this.timestamp = System.currentTimeMillis();
            this.pkgName = context == null ? EnvironmentCompat.MEDIA_UNKNOWN : context.getPackageName();
            setEventSeverity(aPSEventSeverity);
            setEventType(str);
        } catch (RuntimeException e) {
            Log.e("APSEvent", "Error constructing the APSEvent:", e);
        }
    }

    public APSEvent setEventType(String str) {
        this.eventType = str;
        return this;
    }

    public APSEvent setEventSeverity(APSEventSeverity aPSEventSeverity) {
        this.severity = aPSEventSeverity;
        return this;
    }

    public APSEvent setExceptionDetails(Exception exc) {
        if (exc != null) {
            try {
                StringWriter stringWriter = new StringWriter();
                exc.printStackTrace(new PrintWriter(stringWriter));
                String string = stringWriter.toString();
                if (string.length() > 2048) {
                    int length = ((2048 - exc.getMessage().length()) - 6) / 2;
                    this.exceptionLog = exc.getMessage() + "..." + string.substring(0, length) + "..." + string.substring(string.length() - length);
                } else {
                    this.exceptionLog = exc.getMessage() + "\n" + string;
                }
            } catch (RuntimeException e) {
                Log.e("APSEvent", "Error in parsing the exception detail; ", e);
            }
        }
        return this;
    }

    public APSEvent setErrorDetails(String str) {
        if (str != null) {
            this.errorDetails = str;
        }
        return this;
    }

    public APSEventSeverity getEventSeverity() {
        return this.severity;
    }

    public String toJsonPayload() {
        String strReplace = "";
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("sdkVersion", this.sdkVersion);
            jSONObject.put("eventType", this.eventType);
            jSONObject.put("eventTimestamp", this.timestamp);
            jSONObject.put("severity", this.severity.name());
            jSONObject.put("appId", this.pkgName);
            jSONObject.put("osName", this.osName);
            jSONObject.put("osVersion", this.osVersion);
            jSONObject.put("deviceManufacturer", this.deviceManufacturer);
            jSONObject.put(DeviceInformation.PARAMS_DEVICE_MODEL, this.deviceModel);
            jSONObject.put("configVersion", this.configVersion);
            jSONObject.put("otherDetails", this.errorDetails);
            jSONObject.put("exceptionDetails", this.exceptionLog);
            strReplace = Base64.encodeToString(jSONObject.toString().getBytes(), 0).replace("\n", "");
        } catch (JSONException | RuntimeException e) {
            Log.e("APSEvent", "Error in parsing the json .. ignoring : ", e);
        }
        return "{\"Data\": \"" + strReplace + "\",\"PartitionKey\": \"" + this.timestamp + "\"}";
    }
}
