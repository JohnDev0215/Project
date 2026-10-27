package PlaybackInterface.v1_0;

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
@Generated(from = "MoveQueueEntitiesClientInformation", generator = "Immutables")
public final class ImmutableMoveQueueEntitiesClientInformation extends MoveQueueEntitiesClientInformation {
    private final String dropId;
    private final String dropIndex;
    private final String entryId;
    private final String entryIndex;

    private ImmutableMoveQueueEntitiesClientInformation(String str, String str2, String str3, String str4) {
        this.entryIndex = str;
        this.dropIndex = str2;
        this.entryId = str3;
        this.dropId = str4;
    }

    @Override // PlaybackInterface.v1_0.MoveQueueEntitiesClientInformation
    @JsonProperty("entryIndex")
    public String entryIndex() {
        return this.entryIndex;
    }

    @Override // PlaybackInterface.v1_0.MoveQueueEntitiesClientInformation
    @JsonProperty("dropIndex")
    public String dropIndex() {
        return this.dropIndex;
    }

    @Override // PlaybackInterface.v1_0.MoveQueueEntitiesClientInformation
    @JsonProperty("entryId")
    public String entryId() {
        return this.entryId;
    }

    @Override // PlaybackInterface.v1_0.MoveQueueEntitiesClientInformation
    @JsonProperty("dropId")
    public String dropId() {
        return this.dropId;
    }

    public final ImmutableMoveQueueEntitiesClientInformation withEntryIndex(String str) {
        String str2 = (String) requireNonNull(str, "entryIndex");
        return this.entryIndex.equals(str2) ? this : new ImmutableMoveQueueEntitiesClientInformation(str2, this.dropIndex, this.entryId, this.dropId);
    }

    public final ImmutableMoveQueueEntitiesClientInformation withDropIndex(String str) {
        String str2 = (String) requireNonNull(str, "dropIndex");
        return this.dropIndex.equals(str2) ? this : new ImmutableMoveQueueEntitiesClientInformation(this.entryIndex, str2, this.entryId, this.dropId);
    }

    public final ImmutableMoveQueueEntitiesClientInformation withEntryId(String str) {
        String str2 = (String) requireNonNull(str, "entryId");
        return this.entryId.equals(str2) ? this : new ImmutableMoveQueueEntitiesClientInformation(this.entryIndex, this.dropIndex, str2, this.dropId);
    }

    public final ImmutableMoveQueueEntitiesClientInformation withDropId(String str) {
        String str2 = (String) requireNonNull(str, "dropId");
        return this.dropId.equals(str2) ? this : new ImmutableMoveQueueEntitiesClientInformation(this.entryIndex, this.dropIndex, this.entryId, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableMoveQueueEntitiesClientInformation) && equalTo(0, (ImmutableMoveQueueEntitiesClientInformation) obj);
    }

    private boolean equalTo(int i, ImmutableMoveQueueEntitiesClientInformation immutableMoveQueueEntitiesClientInformation) {
        return this.entryIndex.equals(immutableMoveQueueEntitiesClientInformation.entryIndex) && this.dropIndex.equals(immutableMoveQueueEntitiesClientInformation.dropIndex) && this.entryId.equals(immutableMoveQueueEntitiesClientInformation.entryId) && this.dropId.equals(immutableMoveQueueEntitiesClientInformation.dropId);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.entryIndex.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.dropIndex.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.entryId.hashCode();
        return iHashCode3 + (iHashCode3 << 5) + this.dropId.hashCode();
    }

    public String toString() {
        return "MoveQueueEntitiesClientInformation{entryIndex=" + this.entryIndex + ", dropIndex=" + this.dropIndex + ", entryId=" + this.entryId + ", dropId=" + this.dropId + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "MoveQueueEntitiesClientInformation", generator = "Immutables")
    static final class Json extends MoveQueueEntitiesClientInformation {

        @Nullable
        String dropId;

        @Nullable
        String dropIndex;

        @Nullable
        String entryId;

        @Nullable
        String entryIndex;

        Json() {
        }

        @JsonProperty("entryIndex")
        public void setEntryIndex(String str) {
            this.entryIndex = str;
        }

        @JsonProperty("dropIndex")
        public void setDropIndex(String str) {
            this.dropIndex = str;
        }

        @JsonProperty("entryId")
        public void setEntryId(String str) {
            this.entryId = str;
        }

        @JsonProperty("dropId")
        public void setDropId(String str) {
            this.dropId = str;
        }

        @Override // PlaybackInterface.v1_0.MoveQueueEntitiesClientInformation
        public String entryIndex() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MoveQueueEntitiesClientInformation
        public String dropIndex() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MoveQueueEntitiesClientInformation
        public String entryId() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MoveQueueEntitiesClientInformation
        public String dropId() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableMoveQueueEntitiesClientInformation fromJson(Json json) {
        Builder builder = builder();
        String str = json.entryIndex;
        if (str != null) {
            builder.entryIndex(str);
        }
        String str2 = json.dropIndex;
        if (str2 != null) {
            builder.dropIndex(str2);
        }
        String str3 = json.entryId;
        if (str3 != null) {
            builder.entryId(str3);
        }
        String str4 = json.dropId;
        if (str4 != null) {
            builder.dropId(str4);
        }
        return builder.build();
    }

    public static ImmutableMoveQueueEntitiesClientInformation copyOf(MoveQueueEntitiesClientInformation moveQueueEntitiesClientInformation) {
        if (moveQueueEntitiesClientInformation instanceof ImmutableMoveQueueEntitiesClientInformation) {
            return (ImmutableMoveQueueEntitiesClientInformation) moveQueueEntitiesClientInformation;
        }
        return builder().from(moveQueueEntitiesClientInformation).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "MoveQueueEntitiesClientInformation", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_DROP_ID = 8;
        private static final long INIT_BIT_DROP_INDEX = 2;
        private static final long INIT_BIT_ENTRY_ID = 4;
        private static final long INIT_BIT_ENTRY_INDEX = 1;

        @Nullable
        private String dropId;

        @Nullable
        private String dropIndex;

        @Nullable
        private String entryId;

        @Nullable
        private String entryIndex;
        private long initBits;

        private Builder() {
            this.initBits = 15L;
        }

        public final Builder from(MoveQueueEntitiesClientInformation moveQueueEntitiesClientInformation) {
            ImmutableMoveQueueEntitiesClientInformation.requireNonNull(moveQueueEntitiesClientInformation, "instance");
            entryIndex(moveQueueEntitiesClientInformation.entryIndex());
            dropIndex(moveQueueEntitiesClientInformation.dropIndex());
            entryId(moveQueueEntitiesClientInformation.entryId());
            dropId(moveQueueEntitiesClientInformation.dropId());
            return this;
        }

        @JsonProperty("entryIndex")
        public final Builder entryIndex(String str) {
            this.entryIndex = (String) ImmutableMoveQueueEntitiesClientInformation.requireNonNull(str, "entryIndex");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("dropIndex")
        public final Builder dropIndex(String str) {
            this.dropIndex = (String) ImmutableMoveQueueEntitiesClientInformation.requireNonNull(str, "dropIndex");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("entryId")
        public final Builder entryId(String str) {
            this.entryId = (String) ImmutableMoveQueueEntitiesClientInformation.requireNonNull(str, "entryId");
            this.initBits &= -5;
            return this;
        }

        @JsonProperty("dropId")
        public final Builder dropId(String str) {
            this.dropId = (String) ImmutableMoveQueueEntitiesClientInformation.requireNonNull(str, "dropId");
            this.initBits &= -9;
            return this;
        }

        public ImmutableMoveQueueEntitiesClientInformation build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableMoveQueueEntitiesClientInformation(this.entryIndex, this.dropIndex, this.entryId, this.dropId);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("entryIndex");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("dropIndex");
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add("entryId");
            }
            if ((this.initBits & 8) != 0) {
                arrayList.add("dropId");
            }
            return "Cannot build MoveQueueEntitiesClientInformation, some of required attributes are not set " + arrayList;
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
