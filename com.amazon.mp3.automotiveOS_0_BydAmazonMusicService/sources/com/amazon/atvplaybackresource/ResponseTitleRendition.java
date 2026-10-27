package com.amazon.atvplaybackresource;

import com.amazon.atvplaybackdevice.types.AudioQuality;
import com.amazon.atvplaybackdevice.types.VideoMaterialType;
import com.amazon.atvplaybackdevice.types.VideoQuality;
import com.amazon.atvplaybackresource.AuditPing;
import com.amazon.atvplaybackresource.SelectedEntitlement;
import com.amazon.atvplaybackresource.TimeShift;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public class ResponseTitleRendition {
    public final Optional<String> asin;
    public final Optional<VideoQuality> assetBundleVideoQuality;
    public final Optional<AudioQuality> audioQuality;
    public final Optional<String> audioTrackId;
    public final Optional<ImmutableList<AuditPing>> auditPings;
    public final Optional<String> contentId;
    public final Optional<String> deleteAssetAfter;
    public final Optional<String> disclaimerText;
    public final Optional<Long> liveLookbackSec;
    public final Optional<String> pid;
    public final Optional<SelectedEntitlement> selectedEntitlement;
    public final Optional<TimeShift> timeShift;
    public final Optional<String> titleId;
    public final Optional<VideoMaterialType> videoMaterialType;
    public final Optional<VideoQuality> videoQuality;
    public final Optional<String> videoResolution;

    private ResponseTitleRendition(Builder builder) {
        this.disclaimerText = Optional.fromNullable(builder.disclaimerText);
        this.audioQuality = Optional.fromNullable(builder.audioQuality);
        this.assetBundleVideoQuality = Optional.fromNullable(builder.assetBundleVideoQuality);
        this.deleteAssetAfter = Optional.fromNullable(builder.deleteAssetAfter);
        this.videoQuality = Optional.fromNullable(builder.videoQuality);
        this.videoMaterialType = Optional.fromNullable(builder.videoMaterialType);
        this.auditPings = Optional.fromNullable(builder.auditPings);
        this.pid = Optional.fromNullable(builder.pid);
        this.videoResolution = Optional.fromNullable(builder.videoResolution);
        this.timeShift = Optional.fromNullable(builder.timeShift);
        this.contentId = Optional.fromNullable(builder.contentId);
        this.selectedEntitlement = Optional.fromNullable(builder.selectedEntitlement);
        this.titleId = Optional.fromNullable(builder.titleId);
        this.audioTrackId = Optional.fromNullable(builder.audioTrackId);
        this.liveLookbackSec = Optional.fromNullable(builder.liveLookbackSec);
        this.asin = Optional.fromNullable(builder.asin);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ResponseTitleRendition)) {
            return false;
        }
        ResponseTitleRendition responseTitleRendition = (ResponseTitleRendition) obj;
        return Objects.equal(this.disclaimerText, responseTitleRendition.disclaimerText) && Objects.equal(this.audioQuality, responseTitleRendition.audioQuality) && Objects.equal(this.assetBundleVideoQuality, responseTitleRendition.assetBundleVideoQuality) && Objects.equal(this.deleteAssetAfter, responseTitleRendition.deleteAssetAfter) && Objects.equal(this.videoQuality, responseTitleRendition.videoQuality) && Objects.equal(this.videoMaterialType, responseTitleRendition.videoMaterialType) && Objects.equal(this.auditPings, responseTitleRendition.auditPings) && Objects.equal(this.pid, responseTitleRendition.pid) && Objects.equal(this.videoResolution, responseTitleRendition.videoResolution) && Objects.equal(this.timeShift, responseTitleRendition.timeShift) && Objects.equal(this.contentId, responseTitleRendition.contentId) && Objects.equal(this.selectedEntitlement, responseTitleRendition.selectedEntitlement) && Objects.equal(this.titleId, responseTitleRendition.titleId) && Objects.equal(this.audioTrackId, responseTitleRendition.audioTrackId) && Objects.equal(this.liveLookbackSec, responseTitleRendition.liveLookbackSec) && Objects.equal(this.asin, responseTitleRendition.asin);
    }

    public int hashCode() {
        return Objects.hashCode(this.disclaimerText, this.audioQuality, this.assetBundleVideoQuality, this.deleteAssetAfter, this.videoQuality, this.videoMaterialType, this.auditPings, this.pid, this.videoResolution, this.timeShift, this.contentId, this.selectedEntitlement, this.titleId, this.audioTrackId, this.liveLookbackSec, this.asin);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("disclaimerText", this.disclaimerText).add("audioQuality", this.audioQuality).add("assetBundleVideoQuality", this.assetBundleVideoQuality).add("deleteAssetAfter", this.deleteAssetAfter).add("videoQuality", this.videoQuality).add("videoMaterialType", this.videoMaterialType).add("auditPings", this.auditPings).add("pid", this.pid).add("videoResolution", this.videoResolution).add("timeShift", this.timeShift).add("contentId", this.contentId).add("selectedEntitlement", this.selectedEntitlement).add("titleId", this.titleId).add("audioTrackId", this.audioTrackId).add("liveLookbackSec", this.liveLookbackSec).add("asin", this.asin).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String asin;
        public VideoQuality assetBundleVideoQuality;
        public AudioQuality audioQuality;
        public String audioTrackId;
        public ImmutableList<AuditPing> auditPings;
        public String contentId;
        public String deleteAssetAfter;
        public String disclaimerText;
        public Long liveLookbackSec;
        public String pid;
        public SelectedEntitlement selectedEntitlement;
        public TimeShift timeShift;
        public String titleId;
        public VideoMaterialType videoMaterialType;
        public VideoQuality videoQuality;
        public String videoResolution;

        public ResponseTitleRendition build() {
            return new ResponseTitleRendition(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<ResponseTitleRendition> {
        private final ListParser<AuditPing> mAuditPingListParser;
        private final SelectedEntitlement.Parser mSelectedEntitlementParser;
        private final TimeShift.Parser mTimeShiftParser;
        private final SimpleParsers.StringParser masinParser;
        private final EnumParser<AudioQuality> maudioQualityParser;
        private final SimpleParsers.StringParser maudioTrackIdParser;
        private final SimpleParsers.StringParser mcontentIdParser;
        private final SimpleParsers.LongParser mlongTargetParser;
        private final SimpleParsers.StringParser mpidParser;
        private final SimpleParsers.StringParser mresolutionParser;
        private final SimpleParsers.StringParser mstringTargetParser;
        private final SimpleParsers.StringParser mtitleIdParser;
        private final EnumParser<VideoMaterialType> mvideoMaterialTypeParser;
        private final EnumParser<VideoQuality> mvideoQualityParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mvideoMaterialTypeParser = EnumParser.newParser(VideoMaterialType.class);
            this.mSelectedEntitlementParser = new SelectedEntitlement.Parser(objectMapper);
            this.mTimeShiftParser = new TimeShift.Parser(objectMapper);
            this.mlongTargetParser = SimpleParsers.LongParser.INSTANCE;
            this.mAuditPingListParser = ListParser.newParser(new AuditPing.Parser(objectMapper));
            this.mvideoQualityParser = EnumParser.newParser(VideoQuality.class);
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mstringTargetParser = stringParser;
            this.mpidParser = stringParser;
            this.mresolutionParser = stringParser;
            this.mcontentIdParser = stringParser;
            this.mtitleIdParser = stringParser;
            this.maudioTrackIdParser = stringParser;
            this.masinParser = stringParser;
            this.maudioQualityParser = EnumParser.newParser(AudioQuality.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ResponseTitleRendition parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ResponseTitleRendition:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ResponseTitleRendition parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ResponseTitleRendition:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ResponseTitleRendition parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            String str;
            AudioQuality audioQuality;
            VideoQuality videoQuality;
            String str2;
            VideoQuality videoQuality2;
            VideoMaterialType videoMaterialType;
            ImmutableList<AuditPing> immutableList;
            String str3;
            String str4;
            TimeShift timeShift;
            String str5;
            SelectedEntitlement selectedEntitlement;
            String str6;
            String str7;
            Long l;
            String str8;
            JsonParsingUtils.throwIfNotObject(jsonNode, "ResponseTitleRendition");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1307249261:
                            if (next.equals("titleId")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            }
                            break;
                        case -995163246:
                            if (next.equals("selectedEntitlement")) {
                                b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                            }
                            break;
                        case -935141303:
                            if (next.equals("audioQuality")) {
                                b = 1;
                            }
                            break;
                        case -539491044:
                            if (next.equals("videoMaterialType")) {
                                b = 5;
                            }
                            break;
                        case -463671114:
                            if (next.equals("assetBundleVideoQuality")) {
                                b = 2;
                            }
                            break;
                        case -407108748:
                            if (next.equals("contentId")) {
                                b = 10;
                            }
                            break;
                        case -369341561:
                            if (next.equals("videoResolution")) {
                                b = 8;
                            }
                            break;
                        case 110987:
                            if (next.equals("pid")) {
                                b = 7;
                            }
                            break;
                        case 3003607:
                            if (next.equals("asin")) {
                                b = 15;
                            }
                            break;
                        case 25223605:
                            if (next.equals("timeShift")) {
                                b = 9;
                            }
                            break;
                        case 504058884:
                            if (next.equals("videoQuality")) {
                                b = 4;
                            }
                            break;
                        case 580782295:
                            if (next.equals("deleteAssetAfter")) {
                                b = 3;
                            }
                            break;
                        case 682813800:
                            if (next.equals("disclaimerText")) {
                                b = 0;
                            }
                            break;
                        case 1171817951:
                            if (next.equals("liveLookbackSec")) {
                                b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                            }
                            break;
                        case 1641214736:
                            if (next.equals("audioTrackId")) {
                                b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                            }
                            break;
                        case 1678332454:
                            if (next.equals("auditPings")) {
                                b = 6;
                            }
                            break;
                    }
                    str = null;
                    str8 = null;
                    l = null;
                    str7 = null;
                    str6 = null;
                    selectedEntitlement = null;
                    str5 = null;
                    timeShift = null;
                    str4 = null;
                    str3 = null;
                    immutableList = null;
                    videoMaterialType = null;
                    videoQuality2 = null;
                    str2 = null;
                    videoQuality = null;
                    audioQuality = null;
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ResponseTitleRendition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
                switch (b) {
                    case 0:
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.disclaimerText = str;
                        continue;
                    case 1:
                        if (!jsonNode2.isNull()) {
                            audioQuality = (AudioQuality) this.maudioQualityParser.parse(jsonNode2);
                        }
                        builder.audioQuality = audioQuality;
                        continue;
                    case 2:
                        if (!jsonNode2.isNull()) {
                            videoQuality = (VideoQuality) this.mvideoQualityParser.parse(jsonNode2);
                        }
                        builder.assetBundleVideoQuality = videoQuality;
                        continue;
                    case 3:
                        if (!jsonNode2.isNull()) {
                            str2 = this.mstringTargetParser.parse(jsonNode2);
                        }
                        builder.deleteAssetAfter = str2;
                        continue;
                    case 4:
                        if (!jsonNode2.isNull()) {
                            videoQuality2 = (VideoQuality) this.mvideoQualityParser.parse(jsonNode2);
                        }
                        builder.videoQuality = videoQuality2;
                        continue;
                    case 5:
                        if (!jsonNode2.isNull()) {
                            videoMaterialType = (VideoMaterialType) this.mvideoMaterialTypeParser.parse(jsonNode2);
                        }
                        builder.videoMaterialType = videoMaterialType;
                        continue;
                    case 6:
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mAuditPingListParser.parse(jsonNode2);
                        }
                        builder.auditPings = immutableList;
                        continue;
                    case 7:
                        if (!jsonNode2.isNull()) {
                            str3 = this.mpidParser.parse(jsonNode2);
                        }
                        builder.pid = str3;
                        continue;
                    case 8:
                        if (!jsonNode2.isNull()) {
                            str4 = this.mresolutionParser.parse(jsonNode2);
                        }
                        builder.videoResolution = str4;
                        continue;
                    case 9:
                        if (!jsonNode2.isNull()) {
                            timeShift = this.mTimeShiftParser.parse(jsonNode2);
                        }
                        builder.timeShift = timeShift;
                        continue;
                    case 10:
                        if (!jsonNode2.isNull()) {
                            str5 = this.mcontentIdParser.parse(jsonNode2);
                        }
                        builder.contentId = str5;
                        continue;
                    case 11:
                        if (!jsonNode2.isNull()) {
                            selectedEntitlement = this.mSelectedEntitlementParser.parse(jsonNode2);
                        }
                        builder.selectedEntitlement = selectedEntitlement;
                        continue;
                    case 12:
                        if (!jsonNode2.isNull()) {
                            str6 = this.mtitleIdParser.parse(jsonNode2);
                        }
                        builder.titleId = str6;
                        continue;
                    case 13:
                        if (!jsonNode2.isNull()) {
                            str7 = this.maudioTrackIdParser.parse(jsonNode2);
                        }
                        builder.audioTrackId = str7;
                        continue;
                    case 14:
                        if (!jsonNode2.isNull()) {
                            l = this.mlongTargetParser.parse(jsonNode2);
                        }
                        builder.liveLookbackSec = l;
                        continue;
                    case 15:
                        if (!jsonNode2.isNull()) {
                            str8 = this.masinParser.parse(jsonNode2);
                        }
                        builder.asin = str8;
                        continue;
                    default:
                        continue;
                }
                ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ResponseTitleRendition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private ResponseTitleRendition parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1307249261:
                                    if (currentName.equals("titleId")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                                    }
                                    break;
                                case -995163246:
                                    if (currentName.equals("selectedEntitlement")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                    }
                                    break;
                                case -935141303:
                                    if (currentName.equals("audioQuality")) {
                                        b = 1;
                                    }
                                    break;
                                case -539491044:
                                    if (currentName.equals("videoMaterialType")) {
                                        b = 5;
                                    }
                                    break;
                                case -463671114:
                                    if (currentName.equals("assetBundleVideoQuality")) {
                                        b = 2;
                                    }
                                    break;
                                case -407108748:
                                    if (currentName.equals("contentId")) {
                                        b = 10;
                                    }
                                    break;
                                case -369341561:
                                    if (currentName.equals("videoResolution")) {
                                        b = 8;
                                    }
                                    break;
                                case 110987:
                                    if (currentName.equals("pid")) {
                                        b = 7;
                                    }
                                    break;
                                case 3003607:
                                    if (currentName.equals("asin")) {
                                        b = 15;
                                    }
                                    break;
                                case 25223605:
                                    if (currentName.equals("timeShift")) {
                                        b = 9;
                                    }
                                    break;
                                case 504058884:
                                    if (currentName.equals("videoQuality")) {
                                        b = 4;
                                    }
                                    break;
                                case 580782295:
                                    if (currentName.equals("deleteAssetAfter")) {
                                        b = 3;
                                    }
                                    break;
                                case 682813800:
                                    if (currentName.equals("disclaimerText")) {
                                        b = 0;
                                    }
                                    break;
                                case 1171817951:
                                    if (currentName.equals("liveLookbackSec")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                                    }
                                    break;
                                case 1641214736:
                                    if (currentName.equals("audioTrackId")) {
                                        b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                                    }
                                    break;
                                case 1678332454:
                                    if (currentName.equals("auditPings")) {
                                        b = 6;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            Long l = null;
                            String str3 = null;
                            String str4 = null;
                            SelectedEntitlement selectedEntitlement = null;
                            String str5 = null;
                            TimeShift timeShift = null;
                            String str6 = null;
                            String str7 = null;
                            ImmutableList<AuditPing> immutableList = null;
                            VideoMaterialType videoMaterialType = null;
                            VideoQuality videoQuality = null;
                            String str8 = null;
                            VideoQuality videoQuality2 = null;
                            AudioQuality audioQuality = null;
                            switch (b) {
                                case 0:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.disclaimerText = str;
                                    continue;
                                case 1:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        audioQuality = (AudioQuality) this.maudioQualityParser.parse(jsonParser);
                                    }
                                    builder.audioQuality = audioQuality;
                                    continue;
                                case 2:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        videoQuality2 = (VideoQuality) this.mvideoQualityParser.parse(jsonParser);
                                    }
                                    builder.assetBundleVideoQuality = videoQuality2;
                                    continue;
                                case 3:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str8 = this.mstringTargetParser.parse(jsonParser);
                                    }
                                    builder.deleteAssetAfter = str8;
                                    continue;
                                case 4:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        videoQuality = (VideoQuality) this.mvideoQualityParser.parse(jsonParser);
                                    }
                                    builder.videoQuality = videoQuality;
                                    continue;
                                case 5:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        videoMaterialType = (VideoMaterialType) this.mvideoMaterialTypeParser.parse(jsonParser);
                                    }
                                    builder.videoMaterialType = videoMaterialType;
                                    continue;
                                case 6:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        immutableList = this.mAuditPingListParser.parse(jsonParser);
                                    }
                                    builder.auditPings = immutableList;
                                    continue;
                                case 7:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str7 = this.mpidParser.parse(jsonParser);
                                    }
                                    builder.pid = str7;
                                    continue;
                                case 8:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str6 = this.mresolutionParser.parse(jsonParser);
                                    }
                                    builder.videoResolution = str6;
                                    continue;
                                case 9:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        timeShift = this.mTimeShiftParser.parse(jsonParser);
                                    }
                                    builder.timeShift = timeShift;
                                    continue;
                                case 10:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str5 = this.mcontentIdParser.parse(jsonParser);
                                    }
                                    builder.contentId = str5;
                                    continue;
                                case 11:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        selectedEntitlement = this.mSelectedEntitlementParser.parse(jsonParser);
                                    }
                                    builder.selectedEntitlement = selectedEntitlement;
                                    continue;
                                case 12:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str4 = this.mtitleIdParser.parse(jsonParser);
                                    }
                                    builder.titleId = str4;
                                    continue;
                                case 13:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str3 = this.maudioTrackIdParser.parse(jsonParser);
                                    }
                                    builder.audioTrackId = str3;
                                    continue;
                                case 14:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        l = this.mlongTargetParser.parse(jsonParser);
                                    }
                                    builder.liveLookbackSec = l;
                                    continue;
                                case 15:
                                    if (currentToken != JsonToken.VALUE_NULL) {
                                        str2 = this.masinParser.parse(jsonParser);
                                    }
                                    builder.asin = str2;
                                    continue;
                                default:
                                    jsonParser.skipChildren();
                                    continue;
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ResponseTitleRendition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                        ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ResponseTitleRendition so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
