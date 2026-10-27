package com.amazon.avod.core;

import com.amazon.avod.metrics.pmet.MetricParameterException;
import com.amazon.avod.util.DLog;
import com.google.common.base.Strings;
import java.net.URL;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AVODRemoteException extends Exception implements MetricParameterException {
    private final JSONObject mErrorBody;
    private final URL mUrl;

    public AVODRemoteException(String str, Throwable th) {
        this(null, str, null, th);
    }

    public AVODRemoteException(@Nullable URL url, String str, @Nullable JSONObject jSONObject, Throwable th) {
        super(str, th);
        this.mErrorBody = jSONObject;
        this.mUrl = url;
    }

    @Nonnull
    public String getErrorCode() {
        JSONObject jSONObjectOptJSONObject;
        JSONObject jSONObjectOptJSONObject2;
        JSONObject jSONObject = this.mErrorBody;
        if (jSONObject != null && (jSONObjectOptJSONObject = jSONObject.optJSONObject("message")) != null && (jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("body")) != null) {
            String strOptString = jSONObjectOptJSONObject2.optString("code");
            if (!Strings.isNullOrEmpty(strOptString)) {
                return strOptString;
            }
        }
        return "Unknown";
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        JSONObject jSONObject = this.mErrorBody;
        if (jSONObject != null) {
            return DLog.sanitize_avod_message(jSONObject.optString("message"));
        }
        return super.getMessage();
    }

    @Override // java.lang.Throwable
    public String toString() {
        String message = getMessage();
        String name = getClass().getName();
        Locale locale = Locale.US;
        Object[] objArr = new Object[3];
        objArr[0] = name;
        objArr[1] = getErrorCode();
        if (message == null) {
            message = "";
        }
        objArr[2] = message;
        return String.format(locale, "%s : %s : %s", objArr);
    }

    @Nullable
    public URL getUrl() {
        return this.mUrl;
    }

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        if (getCause() != null) {
            return getCause().getClass().getSimpleName();
        }
        String strReplaceAll = getErrorCode().replaceAll("[^A-Za-z0-9.\\-]", "");
        return "ErrorCode-".concat(strReplaceAll.substring(0, Math.min(strReplaceAll.length(), 35)));
    }
}
