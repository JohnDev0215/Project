package PlaybackInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "SetPlaybackLicenseMethod", generator = "Immutables")
public final class ImmutableSetPlaybackLicenseMethod extends SetPlaybackLicenseMethod {
    private final String assetId;
    private final Boolean forced;
    private final String license;
    private final String mediaId;
    private final Queue queue;

    private ImmutableSetPlaybackLicenseMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.mediaId = builder.mediaId;
        this.assetId = builder.assetId;
        this.license = builder.license;
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableSetPlaybackLicenseMethod(Queue queue, Boolean bool, String str, String str2, String str3) {
        this.queue = queue;
        this.forced = bool;
        this.mediaId = str;
        this.assetId = str2;
        this.license = str3;
    }

    @Override // CoreInterface.v1_0.Method
    @JsonProperty("queue")
    public Queue queue() {
        return this.queue;
    }

    @Override // CoreInterface.v1_0.Method
    @JsonProperty("forced")
    public Boolean forced() {
        return this.forced;
    }

    @Override // PlaybackInterface.v1_0.SetPlaybackLicenseMethod
    @JsonProperty("mediaId")
    public String mediaId() {
        return this.mediaId;
    }

    @Override // PlaybackInterface.v1_0.SetPlaybackLicenseMethod
    @JsonProperty("assetId")
    public String assetId() {
        return this.assetId;
    }

    @Override // PlaybackInterface.v1_0.SetPlaybackLicenseMethod
    @JsonProperty("license")
    public String license() {
        return this.license;
    }

    public final ImmutableSetPlaybackLicenseMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableSetPlaybackLicenseMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.mediaId, this.assetId, this.license);
    }

    public final ImmutableSetPlaybackLicenseMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableSetPlaybackLicenseMethod(this.queue, bool2, this.mediaId, this.assetId, this.license);
    }

    public final ImmutableSetPlaybackLicenseMethod withMediaId(String str) {
        String str2 = (String) requireNonNull(str, "mediaId");
        return this.mediaId.equals(str2) ? this : new ImmutableSetPlaybackLicenseMethod(this.queue, this.forced, str2, this.assetId, this.license);
    }

    public final ImmutableSetPlaybackLicenseMethod withAssetId(String str) {
        String str2 = (String) requireNonNull(str, "assetId");
        return this.assetId.equals(str2) ? this : new ImmutableSetPlaybackLicenseMethod(this.queue, this.forced, this.mediaId, str2, this.license);
    }

    public final ImmutableSetPlaybackLicenseMethod withLicense(String str) {
        String str2 = (String) requireNonNull(str, "license");
        return this.license.equals(str2) ? this : new ImmutableSetPlaybackLicenseMethod(this.queue, this.forced, this.mediaId, this.assetId, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSetPlaybackLicenseMethod) && equalTo(0, (ImmutableSetPlaybackLicenseMethod) obj);
    }

    private boolean equalTo(int i, ImmutableSetPlaybackLicenseMethod immutableSetPlaybackLicenseMethod) {
        return this.queue.equals(immutableSetPlaybackLicenseMethod.queue) && this.forced.equals(immutableSetPlaybackLicenseMethod.forced) && this.mediaId.equals(immutableSetPlaybackLicenseMethod.mediaId) && this.assetId.equals(immutableSetPlaybackLicenseMethod.assetId) && this.license.equals(immutableSetPlaybackLicenseMethod.license);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.mediaId.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.assetId.hashCode();
        return iHashCode4 + (iHashCode4 << 5) + this.license.hashCode();
    }

    public String toString() {
        return "SetPlaybackLicenseMethod{queue=" + this.queue + ", forced=" + this.forced + ", mediaId=" + this.mediaId + ", assetId=" + this.assetId + ", license=" + this.license + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SetPlaybackLicenseMethod", generator = "Immutables")
    static final class Json extends SetPlaybackLicenseMethod {

        @Nullable
        String assetId;

        @Nullable
        Boolean forced;

        @Nullable
        String license;

        @Nullable
        String mediaId;

        @Nullable
        Queue queue;

        Json() {
        }

        @JsonProperty("queue")
        public void setQueue(Queue queue) {
            this.queue = queue;
        }

        @JsonProperty("forced")
        public void setForced(Boolean bool) {
            this.forced = bool;
        }

        @JsonProperty("mediaId")
        public void setMediaId(String str) {
            this.mediaId = str;
        }

        @JsonProperty("assetId")
        public void setAssetId(String str) {
            this.assetId = str;
        }

        @JsonProperty("license")
        public void setLicense(String str) {
            this.license = str;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SetPlaybackLicenseMethod
        public String mediaId() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SetPlaybackLicenseMethod
        public String assetId() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.SetPlaybackLicenseMethod
        public String license() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSetPlaybackLicenseMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        String str = json.mediaId;
        if (str != null) {
            builder.mediaId(str);
        }
        String str2 = json.assetId;
        if (str2 != null) {
            builder.assetId(str2);
        }
        String str3 = json.license;
        if (str3 != null) {
            builder.license(str3);
        }
        return builder.build();
    }

    public static ImmutableSetPlaybackLicenseMethod copyOf(SetPlaybackLicenseMethod setPlaybackLicenseMethod) {
        if (setPlaybackLicenseMethod instanceof ImmutableSetPlaybackLicenseMethod) {
            return (ImmutableSetPlaybackLicenseMethod) setPlaybackLicenseMethod;
        }
        return builder().from(setPlaybackLicenseMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SetPlaybackLicenseMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_ASSET_ID = 4;
        private static final long INIT_BIT_LICENSE = 8;
        private static final long INIT_BIT_MEDIA_ID = 2;
        private static final long INIT_BIT_QUEUE = 1;

        @Nullable
        private String assetId;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private String license;

        @Nullable
        private String mediaId;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 15L;
        }

        public final Builder from(Method method) {
            ImmutableSetPlaybackLicenseMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(SetPlaybackLicenseMethod setPlaybackLicenseMethod) {
            ImmutableSetPlaybackLicenseMethod.requireNonNull(setPlaybackLicenseMethod, "instance");
            from((Object) setPlaybackLicenseMethod);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof Method) {
                Method method = (Method) obj;
                forced(method.forced());
                queue(method.queue());
                j = 3;
            } else {
                j = 0;
            }
            if (obj instanceof SetPlaybackLicenseMethod) {
                SetPlaybackLicenseMethod setPlaybackLicenseMethod = (SetPlaybackLicenseMethod) obj;
                license(setPlaybackLicenseMethod.license());
                mediaId(setPlaybackLicenseMethod.mediaId());
                if ((j & 1) == 0) {
                    forced(setPlaybackLicenseMethod.forced());
                    j |= 1;
                }
                assetId(setPlaybackLicenseMethod.assetId());
                if ((j & 2) == 0) {
                    queue(setPlaybackLicenseMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableSetPlaybackLicenseMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableSetPlaybackLicenseMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("mediaId")
        public final Builder mediaId(String str) {
            this.mediaId = (String) ImmutableSetPlaybackLicenseMethod.requireNonNull(str, "mediaId");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("assetId")
        public final Builder assetId(String str) {
            this.assetId = (String) ImmutableSetPlaybackLicenseMethod.requireNonNull(str, "assetId");
            this.initBits &= -5;
            return this;
        }

        @JsonProperty("license")
        public final Builder license(String str) {
            this.license = (String) ImmutableSetPlaybackLicenseMethod.requireNonNull(str, "license");
            this.initBits &= -9;
            return this;
        }

        public ImmutableSetPlaybackLicenseMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSetPlaybackLicenseMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("mediaId");
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add("assetId");
            }
            if ((this.initBits & 8) != 0) {
                arrayList.add("license");
            }
            return "Cannot build SetPlaybackLicenseMethod, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }
}
