package com.amazon.avtitleactionaggregationservice.model.detailpage;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition.AcquisitionActions;
import com.amazon.avtitleactionaggregationservice.model.detailpage.download.DownloadActions;
import com.amazon.avtitleactionaggregationservice.model.detailpage.metadata.TitleMetadata;
import com.amazon.avtitleactionaggregationservice.model.detailpage.playback.PlaybackActions;
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
public class DetailPageActionsResponse {
    public final Optional<AcquisitionActions> acquisitionActions;
    public final Optional<DownloadActions> downloadActions;
    public final Optional<PlaybackActions> playbackActions;
    public final Optional<TitleMetadata> titleMetadata;

    private DetailPageActionsResponse(Builder builder) {
        this.acquisitionActions = Optional.fromNullable(builder.acquisitionActions);
        this.playbackActions = Optional.fromNullable(builder.playbackActions);
        this.downloadActions = Optional.fromNullable(builder.downloadActions);
        this.titleMetadata = Optional.fromNullable(builder.titleMetadata);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DetailPageActionsResponse)) {
            return false;
        }
        DetailPageActionsResponse detailPageActionsResponse = (DetailPageActionsResponse) obj;
        return Objects.equal(this.acquisitionActions, detailPageActionsResponse.acquisitionActions) && Objects.equal(this.playbackActions, detailPageActionsResponse.playbackActions) && Objects.equal(this.downloadActions, detailPageActionsResponse.downloadActions) && Objects.equal(this.titleMetadata, detailPageActionsResponse.titleMetadata);
    }

    public int hashCode() {
        return Objects.hashCode(this.acquisitionActions, this.playbackActions, this.downloadActions, this.titleMetadata);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("acquisitionActions", this.acquisitionActions).add("playbackActions", this.playbackActions).add("downloadActions", this.downloadActions).add("titleMetadata", this.titleMetadata).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public AcquisitionActions acquisitionActions;
        public DownloadActions downloadActions;
        public PlaybackActions playbackActions;
        public TitleMetadata titleMetadata;

        public DetailPageActionsResponse build() {
            return new DetailPageActionsResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<DetailPageActionsResponse> {
        private final AcquisitionActions.Parser mAcquisitionActionsParser;
        private final DownloadActions.Parser mDownloadActionsParser;
        private final PlaybackActions.Parser mPlaybackActionsParser;
        private final TitleMetadata.Parser mTitleMetadataParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mDownloadActionsParser = new DownloadActions.Parser(objectMapper);
            this.mTitleMetadataParser = new TitleMetadata.Parser(objectMapper);
            this.mAcquisitionActionsParser = new AcquisitionActions.Parser(objectMapper);
            this.mPlaybackActionsParser = new PlaybackActions.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public DetailPageActionsResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DetailPageActionsResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public DetailPageActionsResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DetailPageActionsResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private DetailPageActionsResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "DetailPageActionsResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -766709931:
                            if (next.equals("downloadActions")) {
                                b = 2;
                            }
                            break;
                        case -193386320:
                            if (next.equals("acquisitionActions")) {
                                b = 0;
                            }
                            break;
                        case 186254946:
                            if (next.equals("playbackActions")) {
                                b = 1;
                            }
                            break;
                        case 702556583:
                            if (next.equals("titleMetadata")) {
                                b = 3;
                            }
                            break;
                    }
                    TitleMetadata titleMetadata = null;
                    AcquisitionActions acquisitionActions = null;
                    PlaybackActions playbackActions = null;
                    DownloadActions downloadActions = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            acquisitionActions = this.mAcquisitionActionsParser.parse(jsonNode2);
                        }
                        builder.acquisitionActions = acquisitionActions;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            playbackActions = this.mPlaybackActionsParser.parse(jsonNode2);
                        }
                        builder.playbackActions = playbackActions;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            downloadActions = this.mDownloadActionsParser.parse(jsonNode2);
                        }
                        builder.downloadActions = downloadActions;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            titleMetadata = this.mTitleMetadataParser.parse(jsonNode2);
                        }
                        builder.titleMetadata = titleMetadata;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DetailPageActionsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private DetailPageActionsResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -766709931:
                                    if (currentName.equals("downloadActions")) {
                                        b = 2;
                                    }
                                    break;
                                case -193386320:
                                    if (currentName.equals("acquisitionActions")) {
                                        b = 0;
                                    }
                                    break;
                                case 186254946:
                                    if (currentName.equals("playbackActions")) {
                                        b = 1;
                                    }
                                    break;
                                case 702556583:
                                    if (currentName.equals("titleMetadata")) {
                                        b = 3;
                                    }
                                    break;
                            }
                            TitleMetadata titleMetadata = null;
                            AcquisitionActions acquisitionActions = null;
                            PlaybackActions playbackActions = null;
                            DownloadActions downloadActions = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    acquisitionActions = this.mAcquisitionActionsParser.parse(jsonParser);
                                }
                                builder.acquisitionActions = acquisitionActions;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    playbackActions = this.mPlaybackActionsParser.parse(jsonParser);
                                }
                                builder.playbackActions = playbackActions;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    downloadActions = this.mDownloadActionsParser.parse(jsonParser);
                                }
                                builder.downloadActions = downloadActions;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    titleMetadata = this.mTitleMetadataParser.parse(jsonParser);
                                }
                                builder.titleMetadata = titleMetadata;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DetailPageActionsResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
