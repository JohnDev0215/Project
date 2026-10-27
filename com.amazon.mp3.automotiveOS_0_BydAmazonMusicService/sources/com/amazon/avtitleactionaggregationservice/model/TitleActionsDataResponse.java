package com.amazon.avtitleactionaggregationservice.model;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avtitleactionaggregationservice.model.acquisitions.Acquisition;
import com.amazon.avtitleactionaggregationservice.model.availabilitychanges.AvailabilityChanges;
import com.amazon.avtitleactionaggregationservice.model.consumptions.Consumption;
import com.amazon.avtitleactionaggregationservice.model.detailpage.playback.PlaybackExperience;
import com.amazon.avtitleactionaggregationservice.model.download.Download;
import com.amazon.avtitleactionaggregationservice.model.metadata.TitleMetadata;
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
public class TitleActionsDataResponse {
    public final Optional<Acquisition> acquisition;
    public final Optional<AvailabilityChanges> availabilityChanges;
    public final Optional<Consumption> consumption;
    public final Optional<Download> download;
    public final Optional<PlaybackExperience> playbackExperience;
    public final Optional<TitleMetadata> titleMetadata;

    private TitleActionsDataResponse(Builder builder) {
        this.download = Optional.fromNullable(builder.download);
        this.titleMetadata = Optional.fromNullable(builder.titleMetadata);
        this.consumption = Optional.fromNullable(builder.consumption);
        this.acquisition = Optional.fromNullable(builder.acquisition);
        this.availabilityChanges = Optional.fromNullable(builder.availabilityChanges);
        this.playbackExperience = Optional.fromNullable(builder.playbackExperience);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TitleActionsDataResponse)) {
            return false;
        }
        TitleActionsDataResponse titleActionsDataResponse = (TitleActionsDataResponse) obj;
        return Objects.equal(this.download, titleActionsDataResponse.download) && Objects.equal(this.titleMetadata, titleActionsDataResponse.titleMetadata) && Objects.equal(this.consumption, titleActionsDataResponse.consumption) && Objects.equal(this.acquisition, titleActionsDataResponse.acquisition) && Objects.equal(this.availabilityChanges, titleActionsDataResponse.availabilityChanges) && Objects.equal(this.playbackExperience, titleActionsDataResponse.playbackExperience);
    }

    public int hashCode() {
        return Objects.hashCode(this.download, this.titleMetadata, this.consumption, this.acquisition, this.availabilityChanges, this.playbackExperience);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("download", this.download).add("titleMetadata", this.titleMetadata).add("consumption", this.consumption).add("acquisition", this.acquisition).add("availabilityChanges", this.availabilityChanges).add("playbackExperience", this.playbackExperience).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public Acquisition acquisition;
        public AvailabilityChanges availabilityChanges;
        public Consumption consumption;
        public Download download;
        public PlaybackExperience playbackExperience;
        public TitleMetadata titleMetadata;

        public TitleActionsDataResponse build() {
            return new TitleActionsDataResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<TitleActionsDataResponse> {
        private final Acquisition.Parser mAcquisitionParser;
        private final AvailabilityChanges.Parser mAvailabilityChangesParser;
        private final Consumption.Parser mConsumptionParser;
        private final Download.Parser mDownloadParser;
        private final PlaybackExperience.Parser mPlaybackExperienceParser;
        private final TitleMetadata.Parser mTitleMetadataParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mDownloadParser = new Download.Parser(objectMapper);
            this.mPlaybackExperienceParser = new PlaybackExperience.Parser(objectMapper);
            this.mTitleMetadataParser = new TitleMetadata.Parser(objectMapper);
            this.mConsumptionParser = new Consumption.Parser(objectMapper);
            this.mAcquisitionParser = new Acquisition.Parser(objectMapper);
            this.mAvailabilityChangesParser = new AvailabilityChanges.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public TitleActionsDataResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleActionsDataResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public TitleActionsDataResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("TitleActionsDataResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private TitleActionsDataResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "TitleActionsDataResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -848170085:
                            if (next.equals("consumption")) {
                                b = 2;
                            }
                            break;
                        case 702556583:
                            if (next.equals("titleMetadata")) {
                                b = 1;
                            }
                            break;
                        case 1272352653:
                            if (next.equals("acquisition")) {
                                b = 3;
                            }
                            break;
                        case 1427818632:
                            if (next.equals("download")) {
                                b = 0;
                            }
                            break;
                        case 1810708712:
                            if (next.equals("availabilityChanges")) {
                                b = 4;
                            }
                            break;
                        case 1980065253:
                            if (next.equals("playbackExperience")) {
                                b = 5;
                            }
                            break;
                    }
                    PlaybackExperience playbackExperience = null;
                    Download download = null;
                    TitleMetadata titleMetadata = null;
                    Consumption consumption = null;
                    Acquisition acquisition = null;
                    AvailabilityChanges availabilityChanges = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            download = this.mDownloadParser.parse(jsonNode2);
                        }
                        builder.download = download;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            titleMetadata = this.mTitleMetadataParser.parse(jsonNode2);
                        }
                        builder.titleMetadata = titleMetadata;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            consumption = this.mConsumptionParser.parse(jsonNode2);
                        }
                        builder.consumption = consumption;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            acquisition = this.mAcquisitionParser.parse(jsonNode2);
                        }
                        builder.acquisition = acquisition;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            availabilityChanges = this.mAvailabilityChangesParser.parse(jsonNode2);
                        }
                        builder.availabilityChanges = availabilityChanges;
                    } else if (b == 5) {
                        if (!jsonNode2.isNull()) {
                            playbackExperience = this.mPlaybackExperienceParser.parse(jsonNode2);
                        }
                        builder.playbackExperience = playbackExperience;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing TitleActionsDataResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private TitleActionsDataResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -848170085:
                                    if (currentName.equals("consumption")) {
                                        b = 2;
                                    }
                                    break;
                                case 702556583:
                                    if (currentName.equals("titleMetadata")) {
                                        b = 1;
                                    }
                                    break;
                                case 1272352653:
                                    if (currentName.equals("acquisition")) {
                                        b = 3;
                                    }
                                    break;
                                case 1427818632:
                                    if (currentName.equals("download")) {
                                        b = 0;
                                    }
                                    break;
                                case 1810708712:
                                    if (currentName.equals("availabilityChanges")) {
                                        b = 4;
                                    }
                                    break;
                                case 1980065253:
                                    if (currentName.equals("playbackExperience")) {
                                        b = 5;
                                    }
                                    break;
                            }
                            PlaybackExperience playbackExperience = null;
                            Download download = null;
                            TitleMetadata titleMetadata = null;
                            Consumption consumption = null;
                            Acquisition acquisition = null;
                            AvailabilityChanges availabilityChanges = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    download = this.mDownloadParser.parse(jsonParser);
                                }
                                builder.download = download;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    titleMetadata = this.mTitleMetadataParser.parse(jsonParser);
                                }
                                builder.titleMetadata = titleMetadata;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    consumption = this.mConsumptionParser.parse(jsonParser);
                                }
                                builder.consumption = consumption;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    acquisition = this.mAcquisitionParser.parse(jsonParser);
                                }
                                builder.acquisition = acquisition;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    availabilityChanges = this.mAvailabilityChangesParser.parse(jsonParser);
                                }
                                builder.availabilityChanges = availabilityChanges;
                            } else if (b == 5) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    playbackExperience = this.mPlaybackExperienceParser.parse(jsonParser);
                                }
                                builder.playbackExperience = playbackExperience;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing TitleActionsDataResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
