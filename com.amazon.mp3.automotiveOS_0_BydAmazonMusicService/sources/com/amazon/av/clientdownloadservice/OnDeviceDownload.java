package com.amazon.av.clientdownloadservice;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class OnDeviceDownload {
    public final Optional<DownloadInitiation> downloadInitiation;
    public final Optional<DownloadState> downloadState;
    public final Optional<Long> lastRefreshTime;
    public final Optional<OwnerApplicationType> ownerApplication;
    public final Optional<PlaybackEvent> playbackEvent;
    public final Optional<String> titleId;
    public final Optional<TitleState> titleState;
    public final Optional<Long> viewingEndTime;
    public final Optional<Boolean> watched;

    private OnDeviceDownload(Builder builder) {
        this.ownerApplication = Optional.fromNullable(builder.ownerApplication);
        this.downloadState = Optional.fromNullable(builder.downloadState);
        this.downloadInitiation = Optional.fromNullable(builder.downloadInitiation);
        this.watched = Optional.fromNullable(builder.watched);
        this.viewingEndTime = Optional.fromNullable(builder.viewingEndTime);
        this.titleId = Optional.fromNullable(builder.titleId);
        this.lastRefreshTime = Optional.fromNullable(builder.lastRefreshTime);
        this.titleState = Optional.fromNullable(builder.titleState);
        this.playbackEvent = Optional.fromNullable(builder.playbackEvent);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OnDeviceDownload)) {
            return false;
        }
        OnDeviceDownload onDeviceDownload = (OnDeviceDownload) obj;
        return Objects.equal(this.ownerApplication, onDeviceDownload.ownerApplication) && Objects.equal(this.downloadState, onDeviceDownload.downloadState) && Objects.equal(this.downloadInitiation, onDeviceDownload.downloadInitiation) && Objects.equal(this.watched, onDeviceDownload.watched) && Objects.equal(this.viewingEndTime, onDeviceDownload.viewingEndTime) && Objects.equal(this.titleId, onDeviceDownload.titleId) && Objects.equal(this.lastRefreshTime, onDeviceDownload.lastRefreshTime) && Objects.equal(this.titleState, onDeviceDownload.titleState) && Objects.equal(this.playbackEvent, onDeviceDownload.playbackEvent);
    }

    public int hashCode() {
        return Objects.hashCode(this.ownerApplication, this.downloadState, this.downloadInitiation, this.watched, this.viewingEndTime, this.titleId, this.lastRefreshTime, this.titleState, this.playbackEvent);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("ownerApplication", this.ownerApplication).add("downloadState", this.downloadState).add("downloadInitiation", this.downloadInitiation).add("watched", this.watched).add("viewingEndTime", this.viewingEndTime).add("titleId", this.titleId).add("lastRefreshTime", this.lastRefreshTime).add("titleState", this.titleState).add("playbackEvent", this.playbackEvent).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public DownloadInitiation downloadInitiation;
        public DownloadState downloadState;
        public Long lastRefreshTime;
        public OwnerApplicationType ownerApplication;
        public PlaybackEvent playbackEvent;
        public String titleId;
        public TitleState titleState;
        public Long viewingEndTime;
        public Boolean watched;

        public OnDeviceDownload build() {
            return new OnDeviceDownload(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<OnDeviceDownload> {
        private final SimpleParsers.BooleanParser mboolTypeParser;
        private final EnumParser<DownloadInitiation> mdownloadInitiationParser;
        private final EnumParser<DownloadState> mdownloadStateParser;
        private final SimpleParsers.LongParser mlongTypeParser;
        private final EnumParser<OwnerApplicationType> mownerApplicationTypeParser;
        private final EnumParser<PlaybackEvent> mplaybackEventParser;
        private final SimpleParsers.StringParser mtitleIdentifierTypeParser;
        private final EnumParser<TitleState> mtitleStateParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mtitleStateParser = EnumParser.newParser(TitleState.class);
            this.mplaybackEventParser = EnumParser.newParser(PlaybackEvent.class);
            this.mboolTypeParser = SimpleParsers.BooleanParser.INSTANCE;
            this.mownerApplicationTypeParser = EnumParser.newParser(OwnerApplicationType.class);
            this.mlongTypeParser = SimpleParsers.LongParser.INSTANCE;
            this.mtitleIdentifierTypeParser = SimpleParsers.StringParser.INSTANCE;
            this.mdownloadInitiationParser = EnumParser.newParser(DownloadInitiation.class);
            this.mdownloadStateParser = EnumParser.newParser(DownloadState.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public OnDeviceDownload parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("OnDeviceDownload:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public OnDeviceDownload parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("OnDeviceDownload:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private OnDeviceDownload parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            OwnerApplicationType ownerApplicationType;
            DownloadState downloadState;
            DownloadInitiation downloadInitiation;
            Boolean bool;
            Long l;
            String str;
            Long l2;
            TitleState titleState;
            PlaybackEvent playbackEvent;
            JsonParsingUtils.throwIfNotObject(jsonNode, "OnDeviceDownload");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1784452839:
                            if (next.equals("titleState")) {
                                b = 7;
                            }
                            break;
                        case -1400829980:
                            if (next.equals("downloadInitiation")) {
                                b = 2;
                            }
                            break;
                        case -1307249261:
                            if (next.equals("titleId")) {
                                b = 5;
                            }
                            break;
                        case -943045313:
                            if (next.equals("playbackEvent")) {
                                b = 8;
                            }
                            break;
                        case -437643157:
                            if (next.equals("viewingEndTime")) {
                                b = 4;
                            }
                            break;
                        case -412803939:
                            if (next.equals("ownerApplication")) {
                                b = 0;
                            }
                            break;
                        case 20783337:
                            if (next.equals("downloadState")) {
                                b = 1;
                            }
                            break;
                        case 799944658:
                            if (next.equals("lastRefreshTime")) {
                                b = 6;
                            }
                            break;
                        case 1125964206:
                            if (next.equals("watched")) {
                                b = 3;
                            }
                            break;
                    }
                    ownerApplicationType = null;
                    playbackEvent = null;
                    titleState = null;
                    l2 = null;
                    str = null;
                    l = null;
                    bool = null;
                    downloadInitiation = null;
                    downloadState = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing OnDeviceDownload so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            ownerApplicationType = (OwnerApplicationType) this.mownerApplicationTypeParser.parse(jsonNode2);
                        }
                        builder.ownerApplication = ownerApplicationType;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            downloadState = (DownloadState) this.mdownloadStateParser.parse(jsonNode2);
                        }
                        builder.downloadState = downloadState;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            downloadInitiation = (DownloadInitiation) this.mdownloadInitiationParser.parse(jsonNode2);
                        }
                        builder.downloadInitiation = downloadInitiation;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            bool = this.mboolTypeParser.parse(jsonNode2);
                        }
                        builder.watched = bool;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            l = this.mlongTypeParser.parse(jsonNode2);
                        }
                        builder.viewingEndTime = l;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            str = this.mtitleIdentifierTypeParser.parse(jsonNode2);
                        }
                        builder.titleId = str;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            l2 = this.mlongTypeParser.parse(jsonNode2);
                        }
                        builder.lastRefreshTime = l2;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            titleState = (TitleState) this.mtitleStateParser.parse(jsonNode2);
                        }
                        builder.titleState = titleState;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            playbackEvent = (PlaybackEvent) this.mplaybackEventParser.parse(jsonNode2);
                        }
                        builder.playbackEvent = playbackEvent;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing OnDeviceDownload so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private OnDeviceDownload parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            Builder builder = new Builder();
            JsonValidator.checkEqual(JsonToken.START_OBJECT, jsonParser.getCurrentToken(), jsonParser);
            while (true) {
                JsonToken jsonTokenNextToken = jsonParser.nextToken();
                if (JsonValidator.isInsideObject(jsonTokenNextToken)) {
                    if (jsonTokenNextToken == JsonToken.FIELD_NAME) {
                        String currentName = jsonParser.getCurrentName();
                        jsonParser.nextToken();
                        JsonToken currentToken = jsonParser.getCurrentToken();
                        byte b = -1;
                        try {
                            switch (currentName.hashCode()) {
                                case -1784452839:
                                    if (currentName.equals("titleState")) {
                                        b = 7;
                                    }
                                    break;
                                case -1400829980:
                                    if (currentName.equals("downloadInitiation")) {
                                        b = 2;
                                    }
                                    break;
                                case -1307249261:
                                    if (currentName.equals("titleId")) {
                                        b = 5;
                                    }
                                    break;
                                case -943045313:
                                    if (currentName.equals("playbackEvent")) {
                                        b = 8;
                                    }
                                    break;
                                case -437643157:
                                    if (currentName.equals("viewingEndTime")) {
                                        b = 4;
                                    }
                                    break;
                                case -412803939:
                                    if (currentName.equals("ownerApplication")) {
                                        b = 0;
                                    }
                                    break;
                                case 20783337:
                                    if (currentName.equals("downloadState")) {
                                        b = 1;
                                    }
                                    break;
                                case 799944658:
                                    if (currentName.equals("lastRefreshTime")) {
                                        b = 6;
                                    }
                                    break;
                                case 1125964206:
                                    if (currentName.equals("watched")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            OwnerApplicationType ownerApplicationType = null;
                            PlaybackEvent playbackEvent = null;
                            TitleState titleState = null;
                            Long l = null;
                            String str = null;
                            Long l2 = null;
                            Boolean bool = null;
                            DownloadInitiation downloadInitiation = null;
                            DownloadState downloadState = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        ownerApplicationType = (OwnerApplicationType) this.mownerApplicationTypeParser.parse(jsonParser);
                                    }
                                    builder.ownerApplication = ownerApplicationType;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        downloadState = (DownloadState) this.mdownloadStateParser.parse(jsonParser);
                                    }
                                    builder.downloadState = downloadState;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        downloadInitiation = (DownloadInitiation) this.mdownloadInitiationParser.parse(jsonParser);
                                    }
                                    builder.downloadInitiation = downloadInitiation;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        bool = this.mboolTypeParser.parse(jsonParser);
                                    }
                                    builder.watched = bool;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        l2 = this.mlongTypeParser.parse(jsonParser);
                                    }
                                    builder.viewingEndTime = l2;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mtitleIdentifierTypeParser.parse(jsonParser);
                                    }
                                    builder.titleId = str;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        l = this.mlongTypeParser.parse(jsonParser);
                                    }
                                    builder.lastRefreshTime = l;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        titleState = (TitleState) this.mtitleStateParser.parse(jsonParser);
                                    }
                                    builder.titleState = titleState;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        playbackEvent = (PlaybackEvent) this.mplaybackEventParser.parse(jsonParser);
                                    }
                                    builder.playbackEvent = playbackEvent;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing OnDeviceDownload so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing OnDeviceDownload so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
