package com.amazon.avod.http;

import com.amazon.avod.metrics.pmet.MetricParameterException;
import com.google.common.base.Joiner;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class HttpStatusCodeException extends Exception implements MetricParameterException {

    @Nullable
    protected final String mErrorType;
    protected final Supplier<JSONObject> mResponseBodyJson;
    protected final int mStatusCode;

    public HttpStatusCodeException(@Nullable String str, int i, @Nullable final String str2, @Nullable String str3) {
        super(str);
        this.mStatusCode = i;
        this.mResponseBodyJson = Suppliers.memoize(new Supplier() { // from class: com.amazon.avod.http.HttpStatusCodeException$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return HttpStatusCodeException.toJson(str2);
            }
        });
        this.mErrorType = str3;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return Joiner.on("-").skipNulls().join("HttpStatusCode", Integer.toString(getStatusCode()), new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nonnull
    public static JSONObject toJson(@Nullable String str) {
        if (str == null) {
            return new JSONObject();
        }
        try {
            return new JSONObject(str);
        } catch (JSONException unused) {
            return new JSONObject();
        }
    }
}
