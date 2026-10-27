package com.google.firebase;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.util.Strings;

/* JADX INFO: loaded from: classes6.dex */
public final class FirebaseOptions {
    private final String apiKey;
    private final String applicationId;
    private final String databaseUrl;
    private final String gaTrackingId;
    private final String gcmSenderId;
    private final String projectId;
    private final String storageBucket;

    private FirebaseOptions(@NonNull String str, @NonNull String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7) {
        Preconditions.checkState(!Strings.isEmptyOrWhitespace(str), "ApplicationId must be set.");
        this.applicationId = str;
        this.apiKey = str2;
        this.databaseUrl = str3;
        this.gaTrackingId = str4;
        this.gcmSenderId = str5;
        this.storageBucket = str6;
        this.projectId = str7;
    }

    @Nullable
    public static FirebaseOptions fromResource(@NonNull Context context) {
        StringResourceValueReader stringResourceValueReader = new StringResourceValueReader(context);
        String string = stringResourceValueReader.getString("google_app_id");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return new FirebaseOptions(string, stringResourceValueReader.getString("google_api_key"), stringResourceValueReader.getString("firebase_database_url"), stringResourceValueReader.getString("ga_trackingId"), stringResourceValueReader.getString("gcm_defaultSenderId"), stringResourceValueReader.getString("google_storage_bucket"), stringResourceValueReader.getString("project_id"));
    }

    @NonNull
    public String getApiKey() {
        return this.apiKey;
    }

    @NonNull
    public String getApplicationId() {
        return this.applicationId;
    }

    @Nullable
    public String getGcmSenderId() {
        return this.gcmSenderId;
    }

    @Nullable
    public String getProjectId() {
        return this.projectId;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FirebaseOptions)) {
            return false;
        }
        FirebaseOptions firebaseOptions = (FirebaseOptions) obj;
        return Objects.equal(this.applicationId, firebaseOptions.applicationId) && Objects.equal(this.apiKey, firebaseOptions.apiKey) && Objects.equal(this.databaseUrl, firebaseOptions.databaseUrl) && Objects.equal(this.gaTrackingId, firebaseOptions.gaTrackingId) && Objects.equal(this.gcmSenderId, firebaseOptions.gcmSenderId) && Objects.equal(this.storageBucket, firebaseOptions.storageBucket) && Objects.equal(this.projectId, firebaseOptions.projectId);
    }

    public int hashCode() {
        return Objects.hashCode(this.applicationId, this.apiKey, this.databaseUrl, this.gaTrackingId, this.gcmSenderId, this.storageBucket, this.projectId);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("applicationId", this.applicationId).add("apiKey", this.apiKey).add("databaseUrl", this.databaseUrl).add("gcmSenderId", this.gcmSenderId).add("storageBucket", this.storageBucket).add("projectId", this.projectId).toString();
    }
}
