package com.amazon.av.clientdownloadservice;

import com.amazon.av.clientdownloadservice.ClientEntitlement;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.music.inappmessaging.internal.model.Splash;
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
public class DownloadSummaryItem {
    public final Optional<String> deviceId;
    public final Optional<String> deviceName;
    public final Optional<String> deviceTypeId;
    public final Optional<ClientEntitlement> entitlement;
    public final Optional<String> titleId;

    private DownloadSummaryItem(Builder builder) {
        this.titleId = Optional.fromNullable(builder.titleId);
        this.deviceTypeId = Optional.fromNullable(builder.deviceTypeId);
        this.entitlement = Optional.fromNullable(builder.entitlement);
        this.deviceId = Optional.fromNullable(builder.deviceId);
        this.deviceName = Optional.fromNullable(builder.deviceName);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DownloadSummaryItem)) {
            return false;
        }
        DownloadSummaryItem downloadSummaryItem = (DownloadSummaryItem) obj;
        return Objects.equal(this.titleId, downloadSummaryItem.titleId) && Objects.equal(this.deviceTypeId, downloadSummaryItem.deviceTypeId) && Objects.equal(this.entitlement, downloadSummaryItem.entitlement) && Objects.equal(this.deviceId, downloadSummaryItem.deviceId) && Objects.equal(this.deviceName, downloadSummaryItem.deviceName);
    }

    public int hashCode() {
        return Objects.hashCode(this.titleId, this.deviceTypeId, this.entitlement, this.deviceId, this.deviceName);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("titleId", this.titleId).add("deviceTypeId", this.deviceTypeId).add("entitlement", this.entitlement).add(Splash.PARAMS_DEVICE_ID, this.deviceId).add("deviceName", this.deviceName).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String deviceId;
        public String deviceName;
        public String deviceTypeId;
        public ClientEntitlement entitlement;
        public String titleId;

        public DownloadSummaryItem build() {
            return new DownloadSummaryItem(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<DownloadSummaryItem> {
        private final ClientEntitlement.Parser mClientEntitlementParser;
        private final SimpleParsers.StringParser mstringTypeParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mClientEntitlementParser = new ClientEntitlement.Parser(objectMapper);
            this.mstringTypeParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public DownloadSummaryItem parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DownloadSummaryItem:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public DownloadSummaryItem parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DownloadSummaryItem:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private DownloadSummaryItem parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "DownloadSummaryItem");
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
                                b = 0;
                            }
                            break;
                        case -894884885:
                            if (next.equals("deviceTypeId")) {
                                b = 1;
                            }
                            break;
                        case 780988929:
                            if (next.equals("deviceName")) {
                                b = 4;
                            }
                            break;
                        case 914844973:
                            if (next.equals("entitlement")) {
                                b = 2;
                            }
                            break;
                        case 1109191185:
                            if (next.equals(Splash.PARAMS_DEVICE_ID)) {
                                b = 3;
                            }
                            break;
                    }
                    String str = null;
                    String str2 = null;
                    String str3 = null;
                    ClientEntitlement clientEntitlement = null;
                    String str4 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mstringTypeParser.parse(jsonNode2);
                        }
                        builder.titleId = str2;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            str3 = this.mstringTypeParser.parse(jsonNode2);
                        }
                        builder.deviceTypeId = str3;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            clientEntitlement = this.mClientEntitlementParser.parse(jsonNode2);
                        }
                        builder.entitlement = clientEntitlement;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str4 = this.mstringTypeParser.parse(jsonNode2);
                        }
                        builder.deviceId = str4;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            str = this.mstringTypeParser.parse(jsonNode2);
                        }
                        builder.deviceName = str;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DownloadSummaryItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private DownloadSummaryItem parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                        b = 0;
                                    }
                                    break;
                                case -894884885:
                                    if (currentName.equals("deviceTypeId")) {
                                        b = 1;
                                    }
                                    break;
                                case 780988929:
                                    if (currentName.equals("deviceName")) {
                                        b = 4;
                                    }
                                    break;
                                case 914844973:
                                    if (currentName.equals("entitlement")) {
                                        b = 2;
                                    }
                                    break;
                                case 1109191185:
                                    if (currentName.equals(Splash.PARAMS_DEVICE_ID)) {
                                        b = 3;
                                    }
                                    break;
                            }
                            String str = null;
                            String str2 = null;
                            String str3 = null;
                            ClientEntitlement clientEntitlement = null;
                            String str4 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mstringTypeParser.parse(jsonParser);
                                }
                                builder.titleId = str2;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str3 = this.mstringTypeParser.parse(jsonParser);
                                }
                                builder.deviceTypeId = str3;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    clientEntitlement = this.mClientEntitlementParser.parse(jsonParser);
                                }
                                builder.entitlement = clientEntitlement;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str4 = this.mstringTypeParser.parse(jsonParser);
                                }
                                builder.deviceId = str4;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mstringTypeParser.parse(jsonParser);
                                }
                                builder.deviceName = str;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DownloadSummaryItem so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
