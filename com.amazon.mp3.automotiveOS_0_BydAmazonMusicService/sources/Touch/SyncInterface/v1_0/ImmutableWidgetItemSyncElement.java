package Touch.SyncInterface.v1_0;

import ExternalActionInterface.v1_0.PlayMode;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
@Generated(from = "WidgetItemSyncElement", generator = "Immutables")
public final class ImmutableWidgetItemSyncElement extends WidgetItemSyncElement {

    @Nullable
    private final List<String> contentEncoding;

    @Nullable
    private final String contentInfoInstId;

    @Nullable
    private final String contentSrc;

    @Nullable
    private final String entityId;

    @Nullable
    private final EntityIdType entityIdType;

    @Nullable
    private final String image;

    @Nullable
    private final String label;

    @Nullable
    private final String metricsToken;

    @Nullable
    private final PlayMode playMode;

    @Nullable
    private final String primaryText;

    @Nullable
    private final String secondaryText;

    @Nullable
    private final String tertiaryText;
    private final WidgetItemContext widgetItemContext;

    private ImmutableWidgetItemSyncElement(WidgetItemContext widgetItemContext, @Nullable EntityIdType entityIdType, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable List<String> list, @Nullable String str7, @Nullable String str8, @Nullable PlayMode playMode, @Nullable String str9) {
        this.widgetItemContext = widgetItemContext;
        this.entityIdType = entityIdType;
        this.entityId = str;
        this.primaryText = str2;
        this.secondaryText = str3;
        this.tertiaryText = str4;
        this.label = str5;
        this.image = str6;
        this.contentEncoding = list;
        this.contentSrc = str7;
        this.contentInfoInstId = str8;
        this.playMode = playMode;
        this.metricsToken = str9;
    }

    @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
    @JsonProperty(ContextMappingConstants.WIDGET_ITEM_CONTEXT)
    public WidgetItemContext widgetItemContext() {
        return this.widgetItemContext;
    }

    @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
    @JsonProperty(ContextMappingConstants.ENTITY_ID_TYPE)
    @Nullable
    public EntityIdType entityIdType() {
        return this.entityIdType;
    }

    @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
    @JsonProperty(ContextMappingConstants.ENTITY_ID)
    @Nullable
    public String entityId() {
        return this.entityId;
    }

    @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
    @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
    @Nullable
    public String primaryText() {
        return this.primaryText;
    }

    @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
    @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
    @Nullable
    public String secondaryText() {
        return this.secondaryText;
    }

    @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
    @JsonProperty(ContextMappingConstants.TERTIARY_TEXT)
    @Nullable
    public String tertiaryText() {
        return this.tertiaryText;
    }

    @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
    @JsonProperty(ContextMappingConstants.LABEL)
    @Nullable
    public String label() {
        return this.label;
    }

    @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
    @JsonProperty(ContextMappingConstants.IMAGE)
    @Nullable
    public String image() {
        return this.image;
    }

    @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
    @JsonProperty(ContextMappingConstants.CONTENT_ENCODING)
    @Nullable
    public List<String> contentEncoding() {
        return this.contentEncoding;
    }

    @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
    @JsonProperty(ContextMappingConstants.CONTENT_SRC)
    @Nullable
    public String contentSrc() {
        return this.contentSrc;
    }

    @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
    @JsonProperty("contentInfoInstId")
    @Nullable
    public String contentInfoInstId() {
        return this.contentInfoInstId;
    }

    @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
    @JsonProperty(ContextMappingConstants.PLAY_MODE)
    @Nullable
    public PlayMode playMode() {
        return this.playMode;
    }

    @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
    @JsonProperty("metricsToken")
    @Nullable
    public String metricsToken() {
        return this.metricsToken;
    }

    public final ImmutableWidgetItemSyncElement withWidgetItemContext(WidgetItemContext widgetItemContext) {
        WidgetItemContext widgetItemContext2 = (WidgetItemContext) requireNonNull(widgetItemContext, ContextMappingConstants.WIDGET_ITEM_CONTEXT);
        return this.widgetItemContext == widgetItemContext2 ? this : new ImmutableWidgetItemSyncElement(widgetItemContext2, this.entityIdType, this.entityId, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.contentEncoding, this.contentSrc, this.contentInfoInstId, this.playMode, this.metricsToken);
    }

    public final ImmutableWidgetItemSyncElement withEntityIdType(@Nullable EntityIdType entityIdType) {
        return this.entityIdType == entityIdType ? this : new ImmutableWidgetItemSyncElement(this.widgetItemContext, entityIdType, this.entityId, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.contentEncoding, this.contentSrc, this.contentInfoInstId, this.playMode, this.metricsToken);
    }

    public final ImmutableWidgetItemSyncElement withEntityId(@Nullable String str) {
        return equals(this.entityId, str) ? this : new ImmutableWidgetItemSyncElement(this.widgetItemContext, this.entityIdType, str, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.contentEncoding, this.contentSrc, this.contentInfoInstId, this.playMode, this.metricsToken);
    }

    public final ImmutableWidgetItemSyncElement withPrimaryText(@Nullable String str) {
        return equals(this.primaryText, str) ? this : new ImmutableWidgetItemSyncElement(this.widgetItemContext, this.entityIdType, this.entityId, str, this.secondaryText, this.tertiaryText, this.label, this.image, this.contentEncoding, this.contentSrc, this.contentInfoInstId, this.playMode, this.metricsToken);
    }

    public final ImmutableWidgetItemSyncElement withSecondaryText(@Nullable String str) {
        return equals(this.secondaryText, str) ? this : new ImmutableWidgetItemSyncElement(this.widgetItemContext, this.entityIdType, this.entityId, this.primaryText, str, this.tertiaryText, this.label, this.image, this.contentEncoding, this.contentSrc, this.contentInfoInstId, this.playMode, this.metricsToken);
    }

    public final ImmutableWidgetItemSyncElement withTertiaryText(@Nullable String str) {
        return equals(this.tertiaryText, str) ? this : new ImmutableWidgetItemSyncElement(this.widgetItemContext, this.entityIdType, this.entityId, this.primaryText, this.secondaryText, str, this.label, this.image, this.contentEncoding, this.contentSrc, this.contentInfoInstId, this.playMode, this.metricsToken);
    }

    public final ImmutableWidgetItemSyncElement withLabel(@Nullable String str) {
        return equals(this.label, str) ? this : new ImmutableWidgetItemSyncElement(this.widgetItemContext, this.entityIdType, this.entityId, this.primaryText, this.secondaryText, this.tertiaryText, str, this.image, this.contentEncoding, this.contentSrc, this.contentInfoInstId, this.playMode, this.metricsToken);
    }

    public final ImmutableWidgetItemSyncElement withImage(@Nullable String str) {
        return equals(this.image, str) ? this : new ImmutableWidgetItemSyncElement(this.widgetItemContext, this.entityIdType, this.entityId, this.primaryText, this.secondaryText, this.tertiaryText, this.label, str, this.contentEncoding, this.contentSrc, this.contentInfoInstId, this.playMode, this.metricsToken);
    }

    public final ImmutableWidgetItemSyncElement withContentEncoding(@Nullable String... strArr) {
        if (strArr == null) {
            return new ImmutableWidgetItemSyncElement(this.widgetItemContext, this.entityIdType, this.entityId, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, null, this.contentSrc, this.contentInfoInstId, this.playMode, this.metricsToken);
        }
        return new ImmutableWidgetItemSyncElement(this.widgetItemContext, this.entityIdType, this.entityId, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, Arrays.asList(strArr) == null ? null : createUnmodifiableList(false, createSafeList(Arrays.asList(strArr), true, false)), this.contentSrc, this.contentInfoInstId, this.playMode, this.metricsToken);
    }

    public final ImmutableWidgetItemSyncElement withContentEncoding(@Nullable Iterable<String> iterable) {
        if (this.contentEncoding == iterable) {
            return this;
        }
        return new ImmutableWidgetItemSyncElement(this.widgetItemContext, this.entityIdType, this.entityId, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, iterable == null ? null : createUnmodifiableList(false, createSafeList(iterable, true, false)), this.contentSrc, this.contentInfoInstId, this.playMode, this.metricsToken);
    }

    public final ImmutableWidgetItemSyncElement withContentSrc(@Nullable String str) {
        return equals(this.contentSrc, str) ? this : new ImmutableWidgetItemSyncElement(this.widgetItemContext, this.entityIdType, this.entityId, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.contentEncoding, str, this.contentInfoInstId, this.playMode, this.metricsToken);
    }

    public final ImmutableWidgetItemSyncElement withContentInfoInstId(@Nullable String str) {
        return equals(this.contentInfoInstId, str) ? this : new ImmutableWidgetItemSyncElement(this.widgetItemContext, this.entityIdType, this.entityId, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.contentEncoding, this.contentSrc, str, this.playMode, this.metricsToken);
    }

    public final ImmutableWidgetItemSyncElement withPlayMode(@Nullable PlayMode playMode) {
        return this.playMode == playMode ? this : new ImmutableWidgetItemSyncElement(this.widgetItemContext, this.entityIdType, this.entityId, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.contentEncoding, this.contentSrc, this.contentInfoInstId, playMode, this.metricsToken);
    }

    public final ImmutableWidgetItemSyncElement withMetricsToken(@Nullable String str) {
        return equals(this.metricsToken, str) ? this : new ImmutableWidgetItemSyncElement(this.widgetItemContext, this.entityIdType, this.entityId, this.primaryText, this.secondaryText, this.tertiaryText, this.label, this.image, this.contentEncoding, this.contentSrc, this.contentInfoInstId, this.playMode, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableWidgetItemSyncElement) && equalTo(0, (ImmutableWidgetItemSyncElement) obj);
    }

    private boolean equalTo(int i, ImmutableWidgetItemSyncElement immutableWidgetItemSyncElement) {
        return this.widgetItemContext.equals(immutableWidgetItemSyncElement.widgetItemContext) && equals(this.entityIdType, immutableWidgetItemSyncElement.entityIdType) && equals(this.entityId, immutableWidgetItemSyncElement.entityId) && equals(this.primaryText, immutableWidgetItemSyncElement.primaryText) && equals(this.secondaryText, immutableWidgetItemSyncElement.secondaryText) && equals(this.tertiaryText, immutableWidgetItemSyncElement.tertiaryText) && equals(this.label, immutableWidgetItemSyncElement.label) && equals(this.image, immutableWidgetItemSyncElement.image) && equals(this.contentEncoding, immutableWidgetItemSyncElement.contentEncoding) && equals(this.contentSrc, immutableWidgetItemSyncElement.contentSrc) && equals(this.contentInfoInstId, immutableWidgetItemSyncElement.contentInfoInstId) && equals(this.playMode, immutableWidgetItemSyncElement.playMode) && equals(this.metricsToken, immutableWidgetItemSyncElement.metricsToken);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.widgetItemContext.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + hashCode(this.entityIdType);
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.entityId);
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.primaryText);
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + hashCode(this.secondaryText);
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.tertiaryText);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.label);
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.image);
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + hashCode(this.contentEncoding);
        int iHashCode10 = iHashCode9 + (iHashCode9 << 5) + hashCode(this.contentSrc);
        int iHashCode11 = iHashCode10 + (iHashCode10 << 5) + hashCode(this.contentInfoInstId);
        int iHashCode12 = iHashCode11 + (iHashCode11 << 5) + hashCode(this.playMode);
        return iHashCode12 + (iHashCode12 << 5) + hashCode(this.metricsToken);
    }

    public String toString() {
        return "WidgetItemSyncElement{widgetItemContext=" + this.widgetItemContext + ", entityIdType=" + this.entityIdType + ", entityId=" + this.entityId + ", primaryText=" + this.primaryText + ", secondaryText=" + this.secondaryText + ", tertiaryText=" + this.tertiaryText + ", label=" + this.label + ", image=" + this.image + ", contentEncoding=" + this.contentEncoding + ", contentSrc=" + this.contentSrc + ", contentInfoInstId=" + this.contentInfoInstId + ", playMode=" + this.playMode + ", metricsToken=" + this.metricsToken + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "WidgetItemSyncElement", generator = "Immutables")
    static final class Json extends WidgetItemSyncElement {

        @Nullable
        List<String> contentEncoding = null;

        @Nullable
        String contentInfoInstId;

        @Nullable
        String contentSrc;

        @Nullable
        String entityId;

        @Nullable
        EntityIdType entityIdType;

        @Nullable
        String image;

        @Nullable
        String label;

        @Nullable
        String metricsToken;

        @Nullable
        PlayMode playMode;

        @Nullable
        String primaryText;

        @Nullable
        String secondaryText;

        @Nullable
        String tertiaryText;

        @Nullable
        WidgetItemContext widgetItemContext;

        Json() {
        }

        @JsonProperty(ContextMappingConstants.WIDGET_ITEM_CONTEXT)
        public void setWidgetItemContext(WidgetItemContext widgetItemContext) {
            this.widgetItemContext = widgetItemContext;
        }

        @JsonProperty(ContextMappingConstants.ENTITY_ID_TYPE)
        public void setEntityIdType(@Nullable EntityIdType entityIdType) {
            this.entityIdType = entityIdType;
        }

        @JsonProperty(ContextMappingConstants.ENTITY_ID)
        public void setEntityId(@Nullable String str) {
            this.entityId = str;
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public void setPrimaryText(@Nullable String str) {
            this.primaryText = str;
        }

        @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
        public void setSecondaryText(@Nullable String str) {
            this.secondaryText = str;
        }

        @JsonProperty(ContextMappingConstants.TERTIARY_TEXT)
        public void setTertiaryText(@Nullable String str) {
            this.tertiaryText = str;
        }

        @JsonProperty(ContextMappingConstants.LABEL)
        public void setLabel(@Nullable String str) {
            this.label = str;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public void setImage(@Nullable String str) {
            this.image = str;
        }

        @JsonProperty(ContextMappingConstants.CONTENT_ENCODING)
        public void setContentEncoding(@Nullable List<String> list) {
            this.contentEncoding = list;
        }

        @JsonProperty(ContextMappingConstants.CONTENT_SRC)
        public void setContentSrc(@Nullable String str) {
            this.contentSrc = str;
        }

        @JsonProperty("contentInfoInstId")
        public void setContentInfoInstId(@Nullable String str) {
            this.contentInfoInstId = str;
        }

        @JsonProperty(ContextMappingConstants.PLAY_MODE)
        public void setPlayMode(@Nullable PlayMode playMode) {
            this.playMode = playMode;
        }

        @JsonProperty("metricsToken")
        public void setMetricsToken(@Nullable String str) {
            this.metricsToken = str;
        }

        @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
        public WidgetItemContext widgetItemContext() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
        public EntityIdType entityIdType() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
        public String entityId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
        public String primaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
        public String secondaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
        public String tertiaryText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
        public String label() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
        public String image() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
        public List<String> contentEncoding() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
        public String contentSrc() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
        public String contentInfoInstId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
        public PlayMode playMode() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SyncInterface.v1_0.WidgetItemSyncElement
        public String metricsToken() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableWidgetItemSyncElement fromJson(Json json) {
        Builder builder = builder();
        WidgetItemContext widgetItemContext = json.widgetItemContext;
        if (widgetItemContext != null) {
            builder.widgetItemContext(widgetItemContext);
        }
        EntityIdType entityIdType = json.entityIdType;
        if (entityIdType != null) {
            builder.entityIdType(entityIdType);
        }
        String str = json.entityId;
        if (str != null) {
            builder.entityId(str);
        }
        String str2 = json.primaryText;
        if (str2 != null) {
            builder.primaryText(str2);
        }
        String str3 = json.secondaryText;
        if (str3 != null) {
            builder.secondaryText(str3);
        }
        String str4 = json.tertiaryText;
        if (str4 != null) {
            builder.tertiaryText(str4);
        }
        String str5 = json.label;
        if (str5 != null) {
            builder.label(str5);
        }
        String str6 = json.image;
        if (str6 != null) {
            builder.image(str6);
        }
        List<String> list = json.contentEncoding;
        if (list != null) {
            builder.addAllContentEncoding(list);
        }
        String str7 = json.contentSrc;
        if (str7 != null) {
            builder.contentSrc(str7);
        }
        String str8 = json.contentInfoInstId;
        if (str8 != null) {
            builder.contentInfoInstId(str8);
        }
        PlayMode playMode = json.playMode;
        if (playMode != null) {
            builder.playMode(playMode);
        }
        String str9 = json.metricsToken;
        if (str9 != null) {
            builder.metricsToken(str9);
        }
        return builder.build();
    }

    public static ImmutableWidgetItemSyncElement copyOf(WidgetItemSyncElement widgetItemSyncElement) {
        if (widgetItemSyncElement instanceof ImmutableWidgetItemSyncElement) {
            return (ImmutableWidgetItemSyncElement) widgetItemSyncElement;
        }
        return builder().from(widgetItemSyncElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "WidgetItemSyncElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_WIDGET_ITEM_CONTEXT = 1;
        private List<String> contentEncoding;

        @Nullable
        private String contentInfoInstId;

        @Nullable
        private String contentSrc;

        @Nullable
        private String entityId;

        @Nullable
        private EntityIdType entityIdType;

        @Nullable
        private String image;
        private long initBits;

        @Nullable
        private String label;

        @Nullable
        private String metricsToken;

        @Nullable
        private PlayMode playMode;

        @Nullable
        private String primaryText;

        @Nullable
        private String secondaryText;

        @Nullable
        private String tertiaryText;

        @Nullable
        private WidgetItemContext widgetItemContext;

        private Builder() {
            this.initBits = 1L;
            this.contentEncoding = null;
        }

        public final Builder from(WidgetItemSyncElement widgetItemSyncElement) {
            ImmutableWidgetItemSyncElement.requireNonNull(widgetItemSyncElement, "instance");
            widgetItemContext(widgetItemSyncElement.widgetItemContext());
            EntityIdType entityIdType = widgetItemSyncElement.entityIdType();
            if (entityIdType != null) {
                entityIdType(entityIdType);
            }
            String strEntityId = widgetItemSyncElement.entityId();
            if (strEntityId != null) {
                entityId(strEntityId);
            }
            String strPrimaryText = widgetItemSyncElement.primaryText();
            if (strPrimaryText != null) {
                primaryText(strPrimaryText);
            }
            String strSecondaryText = widgetItemSyncElement.secondaryText();
            if (strSecondaryText != null) {
                secondaryText(strSecondaryText);
            }
            String strTertiaryText = widgetItemSyncElement.tertiaryText();
            if (strTertiaryText != null) {
                tertiaryText(strTertiaryText);
            }
            String strLabel = widgetItemSyncElement.label();
            if (strLabel != null) {
                label(strLabel);
            }
            String strImage = widgetItemSyncElement.image();
            if (strImage != null) {
                image(strImage);
            }
            List<String> listContentEncoding = widgetItemSyncElement.contentEncoding();
            if (listContentEncoding != null) {
                addAllContentEncoding(listContentEncoding);
            }
            String strContentSrc = widgetItemSyncElement.contentSrc();
            if (strContentSrc != null) {
                contentSrc(strContentSrc);
            }
            String strContentInfoInstId = widgetItemSyncElement.contentInfoInstId();
            if (strContentInfoInstId != null) {
                contentInfoInstId(strContentInfoInstId);
            }
            PlayMode playMode = widgetItemSyncElement.playMode();
            if (playMode != null) {
                playMode(playMode);
            }
            String strMetricsToken = widgetItemSyncElement.metricsToken();
            if (strMetricsToken != null) {
                metricsToken(strMetricsToken);
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.WIDGET_ITEM_CONTEXT)
        public final Builder widgetItemContext(WidgetItemContext widgetItemContext) {
            this.widgetItemContext = (WidgetItemContext) ImmutableWidgetItemSyncElement.requireNonNull(widgetItemContext, ContextMappingConstants.WIDGET_ITEM_CONTEXT);
            this.initBits &= -2;
            return this;
        }

        @JsonProperty(ContextMappingConstants.ENTITY_ID_TYPE)
        public final Builder entityIdType(@Nullable EntityIdType entityIdType) {
            this.entityIdType = entityIdType;
            return this;
        }

        @JsonProperty(ContextMappingConstants.ENTITY_ID)
        public final Builder entityId(@Nullable String str) {
            this.entityId = str;
            return this;
        }

        @JsonProperty(ContextMappingConstants.PRIMARY_TEXT)
        public final Builder primaryText(@Nullable String str) {
            this.primaryText = str;
            return this;
        }

        @JsonProperty(ContextMappingConstants.SECONDARY_TEXT)
        public final Builder secondaryText(@Nullable String str) {
            this.secondaryText = str;
            return this;
        }

        @JsonProperty(ContextMappingConstants.TERTIARY_TEXT)
        public final Builder tertiaryText(@Nullable String str) {
            this.tertiaryText = str;
            return this;
        }

        @JsonProperty(ContextMappingConstants.LABEL)
        public final Builder label(@Nullable String str) {
            this.label = str;
            return this;
        }

        @JsonProperty(ContextMappingConstants.IMAGE)
        public final Builder image(@Nullable String str) {
            this.image = str;
            return this;
        }

        public final Builder addContentEncoding(String str) {
            if (this.contentEncoding == null) {
                this.contentEncoding = new ArrayList();
            }
            this.contentEncoding.add((String) ImmutableWidgetItemSyncElement.requireNonNull(str, "contentEncoding element"));
            return this;
        }

        public final Builder addContentEncoding(String... strArr) {
            if (this.contentEncoding == null) {
                this.contentEncoding = new ArrayList();
            }
            for (String str : strArr) {
                this.contentEncoding.add((String) ImmutableWidgetItemSyncElement.requireNonNull(str, "contentEncoding element"));
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.CONTENT_ENCODING)
        public final Builder contentEncoding(@Nullable Iterable<String> iterable) {
            if (iterable == null) {
                this.contentEncoding = null;
                return this;
            }
            this.contentEncoding = new ArrayList();
            return addAllContentEncoding(iterable);
        }

        public final Builder addAllContentEncoding(Iterable<String> iterable) {
            ImmutableWidgetItemSyncElement.requireNonNull(iterable, "contentEncoding element");
            if (this.contentEncoding == null) {
                this.contentEncoding = new ArrayList();
            }
            Iterator<String> it = iterable.iterator();
            while (it.hasNext()) {
                this.contentEncoding.add((String) ImmutableWidgetItemSyncElement.requireNonNull(it.next(), "contentEncoding element"));
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.CONTENT_SRC)
        public final Builder contentSrc(@Nullable String str) {
            this.contentSrc = str;
            return this;
        }

        @JsonProperty("contentInfoInstId")
        public final Builder contentInfoInstId(@Nullable String str) {
            this.contentInfoInstId = str;
            return this;
        }

        @JsonProperty(ContextMappingConstants.PLAY_MODE)
        public final Builder playMode(@Nullable PlayMode playMode) {
            this.playMode = playMode;
            return this;
        }

        @JsonProperty("metricsToken")
        public final Builder metricsToken(@Nullable String str) {
            this.metricsToken = str;
            return this;
        }

        public ImmutableWidgetItemSyncElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            WidgetItemContext widgetItemContext = this.widgetItemContext;
            EntityIdType entityIdType = this.entityIdType;
            String str = this.entityId;
            String str2 = this.primaryText;
            String str3 = this.secondaryText;
            String str4 = this.tertiaryText;
            String str5 = this.label;
            String str6 = this.image;
            List<String> list = this.contentEncoding;
            return new ImmutableWidgetItemSyncElement(widgetItemContext, entityIdType, str, str2, str3, str4, str5, str6, list == null ? null : ImmutableWidgetItemSyncElement.createUnmodifiableList(true, list), this.contentSrc, this.contentInfoInstId, this.playMode, this.metricsToken);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(ContextMappingConstants.WIDGET_ITEM_CONTEXT);
            }
            return "Cannot build WidgetItemSyncElement, some of required attributes are not set " + arrayList;
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

    private static <T> List<T> createSafeList(Iterable<? extends T> iterable, boolean z, boolean z2) {
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
