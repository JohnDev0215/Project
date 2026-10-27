package com.amplifyframework.analytics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public class UserProfile {
    private final AnalyticsProperties customProperties;
    private final String email;
    private final Location location;
    private final String name;
    private final String plan;

    protected UserProfile(@NonNull Builder<?, ?> builder) {
        this.name = ((Builder) builder).name;
        this.email = ((Builder) builder).email;
        this.plan = ((Builder) builder).plan;
        this.location = ((Builder) builder).location;
        this.customProperties = ((Builder) builder).customProperties;
    }

    @Nullable
    public String getName() {
        return this.name;
    }

    @Nullable
    public String getEmail() {
        return this.email;
    }

    @Nullable
    public String getPlan() {
        return this.plan;
    }

    @Nullable
    public Location getLocation() {
        return this.location;
    }

    @Nullable
    public AnalyticsProperties getCustomProperties() {
        return this.customProperties;
    }

    @NonNull
    public static Builder<?, ?> builder() {
        return new Builder<>();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserProfile userProfile = (UserProfile) obj;
        if (ObjectsCompat.equals(this.name, userProfile.name) && ObjectsCompat.equals(this.email, userProfile.email) && ObjectsCompat.equals(this.plan, userProfile.plan) && ObjectsCompat.equals(this.location, userProfile.location)) {
            return ObjectsCompat.equals(this.customProperties, userProfile.customProperties);
        }
        return false;
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.email;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.plan;
        int iHashCode3 = (iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        Location location = this.location;
        int iHashCode4 = (iHashCode3 + (location != null ? location.hashCode() : 0)) * 31;
        AnalyticsProperties analyticsProperties = this.customProperties;
        return iHashCode4 + (analyticsProperties != null ? analyticsProperties.hashCode() : 0);
    }

    @NonNull
    public String toString() {
        return "UserProfile{name='" + this.name + "', email='" + this.email + "', plan='" + this.plan + "', location=" + this.location + ", customProperties=" + this.customProperties + '}';
    }

    public static class Builder<B extends Builder<?, ?>, O extends UserProfile> {
        private AnalyticsProperties customProperties;
        private String email;
        private Location location;
        private String name;
        private String plan;

        /* JADX WARN: Multi-variable type inference failed */
        @NonNull
        public B name(@NonNull String str) {
            Objects.requireNonNull(str);
            this.name = str;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NonNull
        public B email(@NonNull String str) {
            Objects.requireNonNull(str);
            this.email = str;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NonNull
        public B plan(@NonNull String str) {
            Objects.requireNonNull(str);
            this.plan = str;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NonNull
        public B location(@NonNull Location location) {
            Objects.requireNonNull(location);
            this.location = location;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NonNull
        public B customProperties(@NonNull AnalyticsProperties analyticsProperties) {
            Objects.requireNonNull(analyticsProperties);
            this.customProperties = analyticsProperties;
            return this;
        }

        @NonNull
        public O build() {
            return (O) new UserProfile(this);
        }
    }

    public static final class Location {
        private final String city;
        private final String country;
        private final Double latitude;
        private final Double longitude;
        private final String postalCode;
        private final String region;

        private Location(@NonNull Builder builder) {
            this.latitude = builder.latitude;
            this.longitude = builder.longitude;
            this.postalCode = builder.postalCode;
            this.city = builder.city;
            this.region = builder.region;
            this.country = builder.country;
        }

        @Nullable
        public Double getLatitude() {
            return this.latitude;
        }

        @Nullable
        public Double getLongitude() {
            return this.longitude;
        }

        @Nullable
        public String getPostalCode() {
            return this.postalCode;
        }

        @Nullable
        public String getCity() {
            return this.city;
        }

        @Nullable
        public String getRegion() {
            return this.region;
        }

        @Nullable
        public String getCountry() {
            return this.country;
        }

        @NonNull
        public static Builder builder() {
            return new Builder();
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Location.class != obj.getClass()) {
                return false;
            }
            Location location = (Location) obj;
            if (ObjectsCompat.equals(this.latitude, location.latitude) && ObjectsCompat.equals(this.longitude, location.longitude) && ObjectsCompat.equals(this.postalCode, location.postalCode) && ObjectsCompat.equals(this.city, location.city) && ObjectsCompat.equals(this.region, location.region)) {
                return ObjectsCompat.equals(this.country, location.country);
            }
            return false;
        }

        public int hashCode() {
            Double d = this.latitude;
            int iHashCode = (d != null ? d.hashCode() : 0) * 31;
            Double d2 = this.longitude;
            int iHashCode2 = (iHashCode + (d2 != null ? d2.hashCode() : 0)) * 31;
            String str = this.postalCode;
            int iHashCode3 = (iHashCode2 + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.city;
            int iHashCode4 = (iHashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.region;
            int iHashCode5 = (iHashCode4 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.country;
            return iHashCode5 + (str4 != null ? str4.hashCode() : 0);
        }

        @NonNull
        public String toString() {
            return "Location{latitude=" + this.latitude + ", longitude=" + this.longitude + ", postalCode='" + this.postalCode + "', city='" + this.city + "', region='" + this.region + "', country='" + this.country + "'}";
        }

        public static final class Builder {
            private String city;
            private String country;
            private Double latitude;
            private Double longitude;
            private String postalCode;
            private String region;

            @NonNull
            public Builder latitude(@NonNull Double d) {
                Objects.requireNonNull(d);
                this.latitude = d;
                return this;
            }

            @NonNull
            public Builder longitude(@NonNull Double d) {
                Objects.requireNonNull(d);
                this.longitude = d;
                return this;
            }

            @NonNull
            public Builder postalCode(@NonNull String str) {
                Objects.requireNonNull(str);
                this.postalCode = str;
                return this;
            }

            @NonNull
            public Builder city(@NonNull String str) {
                Objects.requireNonNull(str);
                this.city = str;
                return this;
            }

            @NonNull
            public Builder region(@NonNull String str) {
                Objects.requireNonNull(str);
                this.region = str;
                return this;
            }

            @NonNull
            public Builder country(@NonNull String str) {
                Objects.requireNonNull(str);
                this.country = str;
                return this;
            }

            @NonNull
            public Location build() {
                return new Location(this);
            }
        }
    }
}
