package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.bugsnag.android.JsonStream;
import com.bugsnag.android.internal.DateUtils;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class Breadcrumb implements JsonStream.Streamable {
    final BreadcrumbInternal impl;
    private final Logger logger;

    Breadcrumb(@NonNull BreadcrumbInternal breadcrumbInternal, @NonNull Logger logger) {
        this.impl = breadcrumbInternal;
        this.logger = logger;
    }

    Breadcrumb(@NonNull String str, @NonNull Logger logger) {
        this.impl = new BreadcrumbInternal(str);
        this.logger = logger;
    }

    Breadcrumb(@NonNull String str, @NonNull BreadcrumbType breadcrumbType, @Nullable Map<String, Object> map, @NonNull Date date, @NonNull Logger logger) {
        this.impl = new BreadcrumbInternal(str, breadcrumbType, map, date);
        this.logger = logger;
    }

    private void logNull(String str) {
        this.logger.e("Invalid null value supplied to breadcrumb." + str + ", ignoring");
    }

    public void setMessage(@NonNull String str) {
        if (str != null) {
            this.impl.message = str;
        } else {
            logNull("message");
        }
    }

    @NonNull
    public String getMessage() {
        return this.impl.message;
    }

    public void setType(@NonNull BreadcrumbType breadcrumbType) {
        if (breadcrumbType != null) {
            this.impl.type = breadcrumbType;
        } else {
            logNull(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        }
    }

    @NonNull
    public BreadcrumbType getType() {
        return this.impl.type;
    }

    public void setMetadata(@Nullable Map<String, Object> map) {
        this.impl.metadata = map;
    }

    @Nullable
    public Map<String, Object> getMetadata() {
        return this.impl.metadata;
    }

    @NonNull
    public Date getTimestamp() {
        return this.impl.timestamp;
    }

    @NonNull
    String getStringTimestamp() {
        return DateUtils.toIso8601(this.impl.timestamp);
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(@NonNull JsonStream jsonStream) throws IOException {
        this.impl.toStream(jsonStream);
    }
}
