package com.amplifyframework.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.autofill.HintConstants;
import androidx.core.os.EnvironmentCompat;
import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class AuthCodeDeliveryDetails {
    private String attributeName;
    private DeliveryMedium deliveryMedium;
    private String destination;

    public AuthCodeDeliveryDetails(@NonNull String str, @NonNull DeliveryMedium deliveryMedium) {
        this(str, deliveryMedium, null);
    }

    public AuthCodeDeliveryDetails(@NonNull String str, @NonNull DeliveryMedium deliveryMedium, @Nullable String str2) {
        Objects.requireNonNull(str);
        this.destination = str;
        Objects.requireNonNull(deliveryMedium);
        this.deliveryMedium = deliveryMedium;
        this.attributeName = str2;
    }

    @NonNull
    public String getDestination() {
        return this.destination;
    }

    @NonNull
    public DeliveryMedium getDeliveryMedium() {
        return this.deliveryMedium;
    }

    @Nullable
    public String getAttributeName() {
        return this.attributeName;
    }

    public int hashCode() {
        return ObjectsCompat.hash(getDestination(), getDeliveryMedium(), getAttributeName());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AuthCodeDeliveryDetails.class != obj.getClass()) {
            return false;
        }
        AuthCodeDeliveryDetails authCodeDeliveryDetails = (AuthCodeDeliveryDetails) obj;
        return ObjectsCompat.equals(getDestination(), authCodeDeliveryDetails.getDestination()) && ObjectsCompat.equals(getDeliveryMedium(), authCodeDeliveryDetails.getDeliveryMedium()) && ObjectsCompat.equals(getAttributeName(), authCodeDeliveryDetails.getAttributeName());
    }

    public String toString() {
        return "AuthCodeDeliveryDetails{destination='" + this.destination + "', deliveryMedium=" + this.deliveryMedium + ", attributeName='" + this.attributeName + "'}";
    }

    public enum DeliveryMedium {
        EMAIL("email"),
        SMS("sms"),
        PHONE(HintConstants.AUTOFILL_HINT_PHONE),
        UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN);

        private String value;

        DeliveryMedium(@NonNull String str) {
            Objects.requireNonNull(str);
            this.value = str;
        }

        @NonNull
        public String getValue() {
            return this.value;
        }

        @NonNull
        public static DeliveryMedium fromString(String str) {
            for (DeliveryMedium deliveryMedium : values()) {
                if (deliveryMedium.getValue().equalsIgnoreCase(str)) {
                    return deliveryMedium;
                }
            }
            return UNKNOWN;
        }
    }
}
