package com.google.firebase.messaging;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.messaging.Constants;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "RemoteMessageCreator")
@SafeParcelable.Reserved({1})
public final class RemoteMessage extends AbstractSafeParcelable {
    public static final Parcelable.Creator<RemoteMessage> CREATOR = new RemoteMessageCreator();

    @SafeParcelable.Field(id = 2)
    Bundle bundle;
    private Map<String, String> data;

    @SafeParcelable.Constructor
    public RemoteMessage(@SafeParcelable.Param(id = 2) Bundle bundle) {
        this.bundle = bundle;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        RemoteMessageCreator.writeToParcel(this, parcel, i);
    }

    @NonNull
    public Map<String, String> getData() {
        if (this.data == null) {
            this.data = Constants.MessagePayloadKeys.extractDeveloperDefinedPayload(this.bundle);
        }
        return this.data;
    }

    public int getTtl() {
        Object obj = this.bundle.get("google.ttl");
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (!(obj instanceof String)) {
            return 0;
        }
        try {
            return Integer.parseInt((String) obj);
        } catch (NumberFormatException unused) {
            Log.w("FirebaseMessaging", "Invalid TTL: " + obj);
            return 0;
        }
    }

    public int getOriginalPriority() {
        String string = this.bundle.getString("google.original_priority");
        if (string == null) {
            string = this.bundle.getString("google.priority");
        }
        return getMessagePriority(string);
    }

    public int getPriority() {
        String string = this.bundle.getString("google.delivered_priority");
        if (string == null) {
            if ("1".equals(this.bundle.getString("google.priority_reduced"))) {
                return 2;
            }
            string = this.bundle.getString("google.priority");
        }
        return getMessagePriority(string);
    }

    private int getMessagePriority(String str) {
        if ("high".equals(str)) {
            return 1;
        }
        return "normal".equals(str) ? 2 : 0;
    }
}
