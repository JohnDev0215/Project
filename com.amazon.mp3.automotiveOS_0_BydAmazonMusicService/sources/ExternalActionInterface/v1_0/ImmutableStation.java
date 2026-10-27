package ExternalActionInterface.v1_0;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.converter.BrushV2Converter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "Station", generator = "Immutables")
public final class ImmutableStation extends Station {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final String image;
    private volatile transient InitShim initShim;
    private final boolean isExplicit;
    private final boolean isFree;

    @Nullable
    private final Boolean isPrime;

    @Nullable
    private final Boolean isSonicRush;

    @Nullable
    private final Boolean isUnlimited;

    @Nullable
    private final String key;

    @Nullable
    private final ImmutablePlaymodeEligibility playmodeEligibility;
    private final List<PlayableContent> seed;

    @Nullable
    private final String title;

    private ImmutableStation(Builder builder) {
        this.initShim = new InitShim();
        this.title = builder.title;
        this.isPrime = builder.isPrime;
        this.isSonicRush = builder.isSonicRush;
        this.isUnlimited = builder.isUnlimited;
        this.key = builder.key;
        this.image = builder.image;
        this.playmodeEligibility = builder.playmodeEligibility == null ? null : ImmutablePlaymodeEligibility.copyOf(builder.playmodeEligibility);
        if (builder.isFreeIsSet()) {
            this.initShim.isFree(builder.isFree);
        }
        if (builder.isExplicitIsSet()) {
            this.initShim.isExplicit(builder.isExplicit);
        }
        if (builder.seedIsSet()) {
            this.initShim.seed(createUnmodifiableList(true, builder.seed));
        }
        this.isFree = this.initShim.isFree();
        this.isExplicit = this.initShim.isExplicit();
        this.seed = this.initShim.seed();
        this.initShim = null;
    }

    private ImmutableStation(@Nullable String str, boolean z, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Boolean bool3, boolean z2, @Nullable String str2, @Nullable String str3, List<PlayableContent> list, @Nullable ImmutablePlaymodeEligibility immutablePlaymodeEligibility) {
        this.initShim = new InitShim();
        this.title = str;
        this.isFree = z;
        this.isPrime = bool;
        this.isSonicRush = bool2;
        this.isUnlimited = bool3;
        this.isExplicit = z2;
        this.key = str2;
        this.image = str3;
        this.seed = list;
        this.playmodeEligibility = immutablePlaymodeEligibility;
        this.initShim = null;
    }

    @Generated(from = "Station", generator = "Immutables")
    private final class InitShim {
        private boolean isExplicit;
        private byte isExplicitBuildStage;
        private boolean isFree;
        private byte isFreeBuildStage;
        private List<PlayableContent> seed;
        private byte seedBuildStage;

        private InitShim() {
            this.isFreeBuildStage = (byte) 0;
            this.isExplicitBuildStage = (byte) 0;
            this.seedBuildStage = (byte) 0;
        }

        boolean isFree() {
            byte b = this.isFreeBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isFreeBuildStage = (byte) -1;
                this.isFree = ImmutableStation.super.isFree();
                this.isFreeBuildStage = (byte) 1;
            }
            return this.isFree;
        }

        void isFree(boolean z) {
            this.isFree = z;
            this.isFreeBuildStage = (byte) 1;
        }

        boolean isExplicit() {
            byte b = this.isExplicitBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isExplicitBuildStage = (byte) -1;
                this.isExplicit = ImmutableStation.super.isExplicit();
                this.isExplicitBuildStage = (byte) 1;
            }
            return this.isExplicit;
        }

        void isExplicit(boolean z) {
            this.isExplicit = z;
            this.isExplicitBuildStage = (byte) 1;
        }

        List<PlayableContent> seed() {
            byte b = this.seedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.seedBuildStage = (byte) -1;
                this.seed = ImmutableStation.createUnmodifiableList(false, ImmutableStation.createSafeList(ImmutableStation.super.seed(), true, false));
                this.seedBuildStage = (byte) 1;
            }
            return this.seed;
        }

        void seed(List<PlayableContent> list) {
            this.seed = list;
            this.seedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isFreeBuildStage == -1) {
                arrayList.add("isFree");
            }
            if (this.isExplicitBuildStage == -1) {
                arrayList.add(BrushV2Converter.KEY_IS_EXPLICIT);
            }
            if (this.seedBuildStage == -1) {
                arrayList.add("seed");
            }
            return "Cannot build Station, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // ExternalActionInterface.v1_0.PlayableContent
    @JsonProperty("title")
    @Nullable
    public String title() {
        return this.title;
    }

    @Override // ExternalActionInterface.v1_0.PlayableContent
    @JsonProperty("isFree")
    public boolean isFree() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isFree();
        }
        return this.isFree;
    }

    @Override // ExternalActionInterface.v1_0.PlayableContent
    @JsonProperty("isPrime")
    @Nullable
    public Boolean isPrime() {
        return this.isPrime;
    }

    @Override // ExternalActionInterface.v1_0.PlayableContent
    @JsonProperty("isSonicRush")
    @Nullable
    public Boolean isSonicRush() {
        return this.isSonicRush;
    }

    @Override // ExternalActionInterface.v1_0.PlayableContent
    @JsonProperty("isUnlimited")
    @Nullable
    public Boolean isUnlimited() {
        return this.isUnlimited;
    }

    @Override // ExternalActionInterface.v1_0.PlayableContent
    @JsonProperty(BrushV2Converter.KEY_IS_EXPLICIT)
    public boolean isExplicit() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isExplicit();
        }
        return this.isExplicit;
    }

    @Override // ExternalActionInterface.v1_0.Station
    @JsonProperty("key")
    @Nullable
    public String key() {
        return this.key;
    }

    @Override // ExternalActionInterface.v1_0.Station
    @JsonProperty(ContextMappingConstants.IMAGE)
    @Nullable
    public String image() {
        return this.image;
    }

    @Override // ExternalActionInterface.v1_0.Station
    @JsonProperty("seed")
    public List<PlayableContent> seed() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.seed();
        }
        return this.seed;
    }

    @Override // ExternalActionInterface.v1_0.Station
    @JsonProperty("playmodeEligibility")
    @Nullable
    public ImmutablePlaymodeEligibility playmodeEligibility() {
        return this.playmodeEligibility;
    }

    public final ImmutableStation withTitle(@Nullable String str) {
        return equals(this.title, str) ? this : new ImmutableStation(str, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.key, this.image, this.seed, this.playmodeEligibility);
    }

    public final ImmutableStation withIsFree(boolean z) {
        return this.isFree == z ? this : new ImmutableStation(this.title, z, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.key, this.image, this.seed, this.playmodeEligibility);
    }

    public final ImmutableStation withIsPrime(@Nullable Boolean bool) {
        return equals(this.isPrime, bool) ? this : new ImmutableStation(this.title, this.isFree, bool, this.isSonicRush, this.isUnlimited, this.isExplicit, this.key, this.image, this.seed, this.playmodeEligibility);
    }

    public final ImmutableStation withIsSonicRush(@Nullable Boolean bool) {
        return equals(this.isSonicRush, bool) ? this : new ImmutableStation(this.title, this.isFree, this.isPrime, bool, this.isUnlimited, this.isExplicit, this.key, this.image, this.seed, this.playmodeEligibility);
    }

    public final ImmutableStation withIsUnlimited(@Nullable Boolean bool) {
        return equals(this.isUnlimited, bool) ? this : new ImmutableStation(this.title, this.isFree, this.isPrime, this.isSonicRush, bool, this.isExplicit, this.key, this.image, this.seed, this.playmodeEligibility);
    }

    public final ImmutableStation withIsExplicit(boolean z) {
        return this.isExplicit == z ? this : new ImmutableStation(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, z, this.key, this.image, this.seed, this.playmodeEligibility);
    }

    public final ImmutableStation withKey(@Nullable String str) {
        return equals(this.key, str) ? this : new ImmutableStation(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, str, this.image, this.seed, this.playmodeEligibility);
    }

    public final ImmutableStation withImage(@Nullable String str) {
        return equals(this.image, str) ? this : new ImmutableStation(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.key, str, this.seed, this.playmodeEligibility);
    }

    public final ImmutableStation withSeed(PlayableContent... playableContentArr) {
        return new ImmutableStation(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.key, this.image, createUnmodifiableList(false, createSafeList(Arrays.asList(playableContentArr), true, false)), this.playmodeEligibility);
    }

    public final ImmutableStation withSeed(Iterable<? extends PlayableContent> iterable) {
        if (this.seed == iterable) {
            return this;
        }
        return new ImmutableStation(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.key, this.image, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.playmodeEligibility);
    }

    public final ImmutableStation withPlaymodeEligibility(@Nullable PlaymodeEligibility playmodeEligibility) {
        if (this.playmodeEligibility == playmodeEligibility) {
            return this;
        }
        return new ImmutableStation(this.title, this.isFree, this.isPrime, this.isSonicRush, this.isUnlimited, this.isExplicit, this.key, this.image, this.seed, playmodeEligibility == null ? null : ImmutablePlaymodeEligibility.copyOf(playmodeEligibility));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableStation) && equalTo(0, (ImmutableStation) obj);
    }

    private boolean equalTo(int i, ImmutableStation immutableStation) {
        return equals(this.title, immutableStation.title) && this.isFree == immutableStation.isFree && equals(this.isPrime, immutableStation.isPrime) && equals(this.isSonicRush, immutableStation.isSonicRush) && equals(this.isUnlimited, immutableStation.isUnlimited) && this.isExplicit == immutableStation.isExplicit && equals(this.key, immutableStation.key) && equals(this.image, immutableStation.image) && this.seed.equals(immutableStation.seed) && equals(this.playmodeEligibility, immutableStation.playmodeEligibility);
    }

    public int hashCode() {
        int iHashCode = 172192 + hashCode(this.title) + 5381;
        int i = iHashCode + (iHashCode << 5) + (this.isFree ? 1231 : 1237);
        int iHashCode2 = i + (i << 5) + hashCode(this.isPrime);
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.isSonicRush);
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.isUnlimited);
        int i2 = iHashCode4 + (iHashCode4 << 5) + (this.isExplicit ? 1231 : 1237);
        int iHashCode5 = i2 + (i2 << 5) + hashCode(this.key);
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.image);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + this.seed.hashCode();
        return iHashCode7 + (iHashCode7 << 5) + hashCode(this.playmodeEligibility);
    }

    public String toString() {
        return "Station{title=" + this.title + ", isFree=" + this.isFree + ", isPrime=" + this.isPrime + ", isSonicRush=" + this.isSonicRush + ", isUnlimited=" + this.isUnlimited + ", isExplicit=" + this.isExplicit + ", key=" + this.key + ", image=" + this.image + ", seed=" + this.seed + ", playmodeEligibility=" + this.playmodeEligibility + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "Station", generator = "Immutables")
    static final class Json extends Station {

        @Nullable
        String image;
        boolean isExplicit;
        boolean isExplicitIsSet;
        boolean isFree;
        boolean isFreeIsSet;

        @Nullable
        Boolean isPrime;

        @Nullable
        Boolean isSonicRush;

        @Nullable
        Boolean isUnlimited;

        @Nullable
        String key;

        @Nullable
        PlaymodeEligibility playmodeEligibility;

        @Nullable
        List<PlayableContent> seed = Collections.emptyList();
        boolean seedIsSet;

        @Nullable
        String title;

        Json() {
        }

        @JsonProperty("title")
        public void setTitle(@Nullable String str) {
            this.title = str;
        }

        @JsonProperty("isFree")
        public void setIsFree(boolean z) {
            this.isFree = z;
            this.isFreeIsSet = true;
        }

        @JsonProperty("isPrime")
        public void setIsPrime(@Nullable Boolean bool) {
            this.isPrime = bool;
        }

        @JsonProperty("isSonicRush")
        public void setIsSonicRush(@Nullable Boolean bool) {
            this.isSonicRush = bool;
        }

        @JsonProperty("isUnlimited")
        public void setIsUnlimited(@Nullable Boolean bool) {
            this.isUnlimited = bool;
        }

        @JsonProperty(BrushV2Converter.KEY_IS_EXPLICIT)
        public void setIsExplicit(boolean z) {
            this.isExplicit = z;
            this.isExplicitIsSet = true;
        }

        @JsonProperty("key")
        public void setKey(@Nullable String str) {
            this.key = str;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public void setImage(@Nullable String str) {
            this.image = str;
        }

        @JsonProperty("seed")
        public void setSeed(List<PlayableContent> list) {
            this.seed = list;
            this.seedIsSet = true;
        }

        @JsonProperty("playmodeEligibility")
        public void setPlaymodeEligibility(@Nullable PlaymodeEligibility playmodeEligibility) {
            this.playmodeEligibility = playmodeEligibility;
        }

        @Override // ExternalActionInterface.v1_0.PlayableContent
        public String title() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.PlayableContent
        public boolean isFree() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.PlayableContent
        public Boolean isPrime() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.PlayableContent
        public Boolean isSonicRush() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.PlayableContent
        public Boolean isUnlimited() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.PlayableContent
        public boolean isExplicit() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Station
        public String key() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Station
        public String image() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Station
        public List<PlayableContent> seed() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.Station
        public PlaymodeEligibility playmodeEligibility() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableStation fromJson(Json json) {
        Builder builder = builder();
        String str = json.title;
        if (str != null) {
            builder.title(str);
        }
        if (json.isFreeIsSet) {
            builder.isFree(json.isFree);
        }
        Boolean bool = json.isPrime;
        if (bool != null) {
            builder.isPrime(bool);
        }
        Boolean bool2 = json.isSonicRush;
        if (bool2 != null) {
            builder.isSonicRush(bool2);
        }
        Boolean bool3 = json.isUnlimited;
        if (bool3 != null) {
            builder.isUnlimited(bool3);
        }
        if (json.isExplicitIsSet) {
            builder.isExplicit(json.isExplicit);
        }
        String str2 = json.key;
        if (str2 != null) {
            builder.key(str2);
        }
        String str3 = json.image;
        if (str3 != null) {
            builder.image(str3);
        }
        if (json.seedIsSet) {
            builder.addAllSeed(json.seed);
        }
        PlaymodeEligibility playmodeEligibility = json.playmodeEligibility;
        if (playmodeEligibility != null) {
            builder.playmodeEligibility(playmodeEligibility);
        }
        return builder.build();
    }

    public static ImmutableStation copyOf(Station station) {
        if (station instanceof ImmutableStation) {
            return (ImmutableStation) station;
        }
        return builder().from(station).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "Station", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_IS_EXPLICIT = 2;
        private static final long OPT_BIT_IS_FREE = 1;
        private static final long OPT_BIT_SEED = 4;

        @Nullable
        private String image;
        private boolean isExplicit;
        private boolean isFree;

        @Nullable
        private Boolean isPrime;

        @Nullable
        private Boolean isSonicRush;

        @Nullable
        private Boolean isUnlimited;

        @Nullable
        private String key;
        private long optBits;

        @Nullable
        private PlaymodeEligibility playmodeEligibility;
        private List<PlayableContent> seed;

        @Nullable
        private String title;

        private Builder() {
            this.seed = new ArrayList();
        }

        public final Builder from(PlayableContent playableContent) {
            ImmutableStation.requireNonNull(playableContent, "instance");
            from((Object) playableContent);
            return this;
        }

        public final Builder from(Station station) {
            ImmutableStation.requireNonNull(station, "instance");
            from((Object) station);
            return this;
        }

        private void from(Object obj) {
            if (obj instanceof PlayableContent) {
                PlayableContent playableContent = (PlayableContent) obj;
                Boolean boolIsSonicRush = playableContent.isSonicRush();
                if (boolIsSonicRush != null) {
                    isSonicRush(boolIsSonicRush);
                }
                isExplicit(playableContent.isExplicit());
                Boolean boolIsUnlimited = playableContent.isUnlimited();
                if (boolIsUnlimited != null) {
                    isUnlimited(boolIsUnlimited);
                }
                isFree(playableContent.isFree());
                String strTitle = playableContent.title();
                if (strTitle != null) {
                    title(strTitle);
                }
                Boolean boolIsPrime = playableContent.isPrime();
                if (boolIsPrime != null) {
                    isPrime(boolIsPrime);
                }
            }
            if (obj instanceof Station) {
                Station station = (Station) obj;
                PlaymodeEligibility playmodeEligibility = station.playmodeEligibility();
                if (playmodeEligibility != null) {
                    playmodeEligibility(playmodeEligibility);
                }
                String strImage = station.image();
                if (strImage != null) {
                    image(strImage);
                }
                addAllSeed(station.seed());
                String strKey = station.key();
                if (strKey != null) {
                    key(strKey);
                }
            }
        }

        @JsonProperty("title")
        public final Builder title(@Nullable String str) {
            this.title = str;
            return this;
        }

        @JsonProperty("isFree")
        public final Builder isFree(boolean z) {
            this.isFree = z;
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("isPrime")
        public final Builder isPrime(@Nullable Boolean bool) {
            this.isPrime = bool;
            return this;
        }

        @JsonProperty("isSonicRush")
        public final Builder isSonicRush(@Nullable Boolean bool) {
            this.isSonicRush = bool;
            return this;
        }

        @JsonProperty("isUnlimited")
        public final Builder isUnlimited(@Nullable Boolean bool) {
            this.isUnlimited = bool;
            return this;
        }

        @JsonProperty(BrushV2Converter.KEY_IS_EXPLICIT)
        public final Builder isExplicit(boolean z) {
            this.isExplicit = z;
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("key")
        public final Builder key(@Nullable String str) {
            this.key = str;
            return this;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public final Builder image(@Nullable String str) {
            this.image = str;
            return this;
        }

        public final Builder addSeed(PlayableContent playableContent) {
            this.seed.add((PlayableContent) ImmutableStation.requireNonNull(playableContent, "seed element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addSeed(PlayableContent... playableContentArr) {
            for (PlayableContent playableContent : playableContentArr) {
                this.seed.add((PlayableContent) ImmutableStation.requireNonNull(playableContent, "seed element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("seed")
        public final Builder seed(Iterable<? extends PlayableContent> iterable) {
            this.seed.clear();
            return addAllSeed(iterable);
        }

        public final Builder addAllSeed(Iterable<? extends PlayableContent> iterable) {
            Iterator<? extends PlayableContent> it = iterable.iterator();
            while (it.hasNext()) {
                this.seed.add((PlayableContent) ImmutableStation.requireNonNull(it.next(), "seed element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("playmodeEligibility")
        public final Builder playmodeEligibility(@Nullable PlaymodeEligibility playmodeEligibility) {
            this.playmodeEligibility = playmodeEligibility == null ? null : ImmutablePlaymodeEligibility.copyOf(playmodeEligibility);
            return this;
        }

        public ImmutableStation build() {
            return new ImmutableStation(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isFreeIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isExplicitIsSet() {
            return (this.optBits & 2) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean seedIsSet() {
            return (this.optBits & 4) != 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    private static int hashCode(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    private static boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> List<T> createSafeList(Iterable<? extends T> iterable, boolean z, boolean z2) {
        if ((iterable instanceof Collection) && ((Collection) iterable).size() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (T t : iterable) {
            if (!z2 || t != null) {
                if (z) {
                    requireNonNull(t, "element");
                }
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> List<T> createUnmodifiableList(boolean z, List<T> list) {
        int size = list.size();
        if (size == 0) {
            return Collections.emptyList();
        }
        if (size == 1) {
            return Collections.singletonList(list.get(0));
        }
        if (z) {
            return Collections.unmodifiableList(new ArrayList(list));
        }
        if (list instanceof ArrayList) {
            ((ArrayList) list).trimToSize();
        }
        return Collections.unmodifiableList(list);
    }
}
