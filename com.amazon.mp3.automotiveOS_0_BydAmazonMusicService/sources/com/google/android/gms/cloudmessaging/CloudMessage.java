package com.google.android.gms.cloudmessaging;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cloud-messaging@@17.0.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "CloudMessageCreator")
public final class CloudMessage extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<CloudMessage> CREATOR = new zza();
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_NORMAL = 2;
    public static final int PRIORITY_UNKNOWN = 0;

    @NonNull
    @SafeParcelable.Field(id = 1)
    Intent zza;

    @GuardedBy("this")
    private Map<String, String> zzb;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cloud-messaging@@17.0.0 */
    @Target({ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MessagePriority {
    }

    @SafeParcelable.Constructor
    public CloudMessage(@NonNull @SafeParcelable.Param(id = 1) Intent intent) {
        this.zza = intent;
    }

    private static int zza(@Nullable String str) {
        if ("high".equals(str)) {
            return 1;
        }
        return "normal".equals(str) ? 2 : 0;
    }

    @Nullable
    public String getCollapseKey() {
        return this.zza.getStringExtra("collapse_key");
    }

    @NonNull
    public synchronized Map<String, String> getData() {
        if (this.zzb == null) {
            Bundle extras = this.zza.getExtras();
            ArrayMap arrayMap = new ArrayMap();
            if (extras != null) {
                for (String str : extras.keySet()) {
                    Object obj = extras.get(str);
                    if (obj instanceof String) {
                        String str2 = (String) obj;
                        if (!str.startsWith("google.") && !str.equals("from") && !str.equals("message_type") && !str.equals("collapse_key")) {
                            arrayMap.put(str, str2);
                        }
                    }
                }
            }
            this.zzb = arrayMap;
        }
        return this.zzb;
    }

    @Nullable
    public String getFrom() {
        return this.zza.getStringExtra("from");
    }

    @NonNull
    public Intent getIntent() {
        return this.zza;
    }

    @Nullable
    public String getMessageId() {
        String stringExtra = this.zza.getStringExtra("google.message_id");
        return stringExtra == null ? this.zza.getStringExtra("message_id") : stringExtra;
    }

    @Nullable
    public String getMessageType() {
        return this.zza.getStringExtra("message_type");
    }

    public int getOriginalPriority() {
        String stringExtra = this.zza.getStringExtra("google.original_priority");
        if (stringExtra == null) {
            stringExtra = this.zza.getStringExtra("google.priority");
        }
        return zza(stringExtra);
    }

    public int getPriority() {
        String stringExtra = this.zza.getStringExtra("google.delivered_priority");
        if (stringExtra == null) {
            if ("1".equals(this.zza.getStringExtra("google.priority_reduced"))) {
                return 2;
            }
            stringExtra = this.zza.getStringExtra("google.priority");
        }
        return zza(stringExtra);
    }

    @Nullable
    public byte[] getRawData() {
        return this.zza.getByteArrayExtra("rawData");
    }

    @Nullable
    public String getSenderId() {
        return this.zza.getStringExtra("google.c.sender.id");
    }

    public long getSentTime() {
        Bundle extras = this.zza.getExtras();
        Object obj = extras != null ? extras.get("google.sent_time") : null;
        if (obj instanceof Long) {
            return ((Long) obj).longValue();
        }
        if (!(obj instanceof String)) {
            return 0L;
        }
        try {
            return Long.parseLong((String) obj);
        } catch (NumberFormatException unused) {
            String strValueOf = String.valueOf(obj);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 19);
            sb.append("Invalid sent time: ");
            sb.append(strValueOf);
            Log.w("CloudMessage", sb.toString());
            return 0L;
        }
    }

    @Nullable
    public String getTo() {
        return this.zza.getStringExtra("google.to");
    }

    public int getTtl() {
        Bundle extras = this.zza.getExtras();
        Object obj = extras != null ? extras.get("google.ttl") : null;
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (!(obj instanceof String)) {
            return 0;
        }
        try {
            return Integer.parseInt((String) obj);
        } catch (NumberFormatException unused) {
            String strValueOf = String.valueOf(obj);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 13);
            sb.append("Invalid TTL: ");
            sb.append(strValueOf);
            Log.w("CloudMessage", sb.toString());
            return 0;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
