package com.amazon.atv.discovery;

import com.amazon.avod.util.json.PolymorphicJsonParser;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Collection {
    public final Optional<ImmutableList<ActionConsequence>> actionConsequences;
    public final Optional<Analytics> analytics;
    public final Optional<ImmutableList<Action>> collectionActionList;
    public final Optional<String> collectionId;
    public final Optional<String> debugAttributes;
    public final Optional<DiagnosticContainer> diagnosticContainer;
    public final Optional<ImmutableMap<String, ImmutableList<Action>>> itemTypeToActionMap;
    public final Optional<Items> items;
    public final Optional<PresentationData> presentationData;
    public final Optional<String> subtext;
    public final Optional<String> text;
    public final Optional<TimeToLive> timeToLive;
    public final CollectionType type;
    public final int version;
    public final Optional<NavigationalAction> zoomInLink;
    public final Optional<NavigationalAction> zoomOutLink;

    @NotThreadSafe
    public static abstract class Builder {
        public ImmutableList<ActionConsequence> actionConsequences;
        public Analytics analytics;
        public ImmutableList<Action> collectionActionList;
        public String collectionId;
        public String debugAttributes;
        public DiagnosticContainer diagnosticContainer;
        public ImmutableMap<String, ImmutableList<Action>> itemTypeToActionMap;
        public Items items;
        public PresentationData presentationData;
        public String subtext;
        public String text;
        public TimeToLive timeToLive;
        public CollectionType type;
        public int version;
        public NavigationalAction zoomInLink;
        public NavigationalAction zoomOutLink;
    }

    protected Collection(Builder builder) {
        this.collectionActionList = Optional.fromNullable(builder.collectionActionList);
        this.analytics = Optional.fromNullable(builder.analytics);
        this.itemTypeToActionMap = Optional.fromNullable(builder.itemTypeToActionMap);
        this.actionConsequences = Optional.fromNullable(builder.actionConsequences);
        this.debugAttributes = Optional.fromNullable(builder.debugAttributes);
        this.zoomInLink = Optional.fromNullable(builder.zoomInLink);
        this.presentationData = Optional.fromNullable(builder.presentationData);
        this.diagnosticContainer = Optional.fromNullable(builder.diagnosticContainer);
        this.collectionId = Optional.fromNullable(builder.collectionId);
        this.timeToLive = Optional.fromNullable(builder.timeToLive);
        this.zoomOutLink = Optional.fromNullable(builder.zoomOutLink);
        this.text = Optional.fromNullable(builder.text);
        this.version = builder.version;
        this.items = Optional.fromNullable(builder.items);
        this.type = (CollectionType) Preconditions.checkNotNull(builder.type, "Unexpected null field: type");
        this.subtext = Optional.fromNullable(builder.subtext);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Collection)) {
            return false;
        }
        Collection collection = (Collection) obj;
        return Objects.equal(this.collectionActionList, collection.collectionActionList) && Objects.equal(this.analytics, collection.analytics) && Objects.equal(this.itemTypeToActionMap, collection.itemTypeToActionMap) && Objects.equal(this.actionConsequences, collection.actionConsequences) && Objects.equal(this.debugAttributes, collection.debugAttributes) && Objects.equal(this.zoomInLink, collection.zoomInLink) && Objects.equal(this.presentationData, collection.presentationData) && Objects.equal(this.diagnosticContainer, collection.diagnosticContainer) && Objects.equal(this.collectionId, collection.collectionId) && Objects.equal(this.timeToLive, collection.timeToLive) && Objects.equal(this.zoomOutLink, collection.zoomOutLink) && Objects.equal(this.text, collection.text) && Objects.equal(Integer.valueOf(this.version), Integer.valueOf(collection.version)) && Objects.equal(this.items, collection.items) && Objects.equal(this.type, collection.type) && Objects.equal(this.subtext, collection.subtext);
    }

    public int hashCode() {
        return Objects.hashCode(this.collectionActionList, this.analytics, this.itemTypeToActionMap, this.actionConsequences, this.debugAttributes, this.zoomInLink, this.presentationData, this.diagnosticContainer, this.collectionId, this.timeToLive, this.zoomOutLink, this.text, Integer.valueOf(this.version), this.items, this.type, this.subtext);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("collectionActionList", this.collectionActionList).add("analytics", this.analytics).add("itemTypeToActionMap", this.itemTypeToActionMap).add("actionConsequences", this.actionConsequences).add("debugAttributes", this.debugAttributes).add("zoomInLink", this.zoomInLink).add("presentationData", this.presentationData).add("diagnosticContainer", this.diagnosticContainer).add("collectionId", this.collectionId).add("timeToLive", this.timeToLive).add("zoomOutLink", this.zoomOutLink).add("text", this.text).add("version", this.version).add(ContextMappingConstants.ITEMS, this.items).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).add("subtext", this.subtext).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<Collection> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.COLLECTION_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
