package com.amazon.atv.discovery;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class AutobotDebugContext {
    public final Optional<String> debugAttributeBranchInfoBranch;
    public final Optional<Mode> debugAttributeBranchInfoMode;
    public final Optional<String> debugAttributeBranchInfoSubBranch;
    public final Optional<String> quickView;
    public final Optional<Date> timestamp;

    private AutobotDebugContext(Builder builder) {
        this.quickView = Optional.fromNullable(builder.quickView);
        this.debugAttributeBranchInfoMode = Optional.fromNullable(builder.debugAttributeBranchInfoMode);
        this.debugAttributeBranchInfoSubBranch = Optional.fromNullable(builder.debugAttributeBranchInfoSubBranch);
        this.debugAttributeBranchInfoBranch = Optional.fromNullable(builder.debugAttributeBranchInfoBranch);
        this.timestamp = Optional.fromNullable(builder.timestamp);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AutobotDebugContext)) {
            return false;
        }
        AutobotDebugContext autobotDebugContext = (AutobotDebugContext) obj;
        return Objects.equal(this.quickView, autobotDebugContext.quickView) && Objects.equal(this.debugAttributeBranchInfoMode, autobotDebugContext.debugAttributeBranchInfoMode) && Objects.equal(this.debugAttributeBranchInfoSubBranch, autobotDebugContext.debugAttributeBranchInfoSubBranch) && Objects.equal(this.debugAttributeBranchInfoBranch, autobotDebugContext.debugAttributeBranchInfoBranch) && Objects.equal(this.timestamp, autobotDebugContext.timestamp);
    }

    public int hashCode() {
        return Objects.hashCode(this.quickView, this.debugAttributeBranchInfoMode, this.debugAttributeBranchInfoSubBranch, this.debugAttributeBranchInfoBranch, this.timestamp);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("quickView", this.quickView).add("debugAttributeBranchInfoMode", this.debugAttributeBranchInfoMode).add("debugAttributeBranchInfoSubBranch", this.debugAttributeBranchInfoSubBranch).add("debugAttributeBranchInfoBranch", this.debugAttributeBranchInfoBranch).add(TetheredMessageKey.timestamp, this.timestamp).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String debugAttributeBranchInfoBranch;
        public Mode debugAttributeBranchInfoMode;
        public String debugAttributeBranchInfoSubBranch;
        public String quickView;
        public Date timestamp;

        public AutobotDebugContext build() {
            return new AutobotDebugContext(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<AutobotDebugContext> {
        private final EnumParser<Mode> mModeParser;
        private final SimpleParsers.StringParser mStringParser;
        private final SimpleParsers.DateParser mTimestampParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mTimestampParser = SimpleParsers.DateParser.INSTANCE;
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
            this.mModeParser = EnumParser.newParser(Mode.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public AutobotDebugContext parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AutobotDebugContext:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public AutobotDebugContext parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("AutobotDebugContext:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AutobotDebugContext parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "AutobotDebugContext");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1906159909:
                            if (next.equals("debugAttributeBranchInfoBranch")) {
                                b = 3;
                            }
                            break;
                        case 55126294:
                            if (next.equals(TetheredMessageKey.timestamp)) {
                                b = 4;
                            }
                            break;
                        case 333536540:
                            if (next.equals("debugAttributeBranchInfoMode")) {
                                b = 1;
                            }
                            break;
                        case 1300829746:
                            if (next.equals("quickView")) {
                                b = 0;
                            }
                            break;
                        case 1560821801:
                            if (next.equals("debugAttributeBranchInfoSubBranch")) {
                                b = 2;
                            }
                            break;
                    }
                    Date date = null;
                    String str = null;
                    Mode mode = null;
                    String str2 = null;
                    String str3 = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mStringParser.parse(jsonNode2);
                        }
                        builder.quickView = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            mode = (Mode) this.mModeParser.parse(jsonNode2);
                        }
                        builder.debugAttributeBranchInfoMode = mode;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            str2 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.debugAttributeBranchInfoSubBranch = str2;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            str3 = this.mStringParser.parse(jsonNode2);
                        }
                        builder.debugAttributeBranchInfoBranch = str3;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            date = this.mTimestampParser.parse(jsonNode2);
                        }
                        builder.timestamp = date;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing AutobotDebugContext so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private AutobotDebugContext parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1906159909:
                                    if (currentName.equals("debugAttributeBranchInfoBranch")) {
                                        b = 3;
                                    }
                                    break;
                                case 55126294:
                                    if (currentName.equals(TetheredMessageKey.timestamp)) {
                                        b = 4;
                                    }
                                    break;
                                case 333536540:
                                    if (currentName.equals("debugAttributeBranchInfoMode")) {
                                        b = 1;
                                    }
                                    break;
                                case 1300829746:
                                    if (currentName.equals("quickView")) {
                                        b = 0;
                                    }
                                    break;
                                case 1560821801:
                                    if (currentName.equals("debugAttributeBranchInfoSubBranch")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            Date date = null;
                            String str = null;
                            Mode mode = null;
                            String str2 = null;
                            String str3 = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mStringParser.parse(jsonParser);
                                }
                                builder.quickView = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    mode = (Mode) this.mModeParser.parse(jsonParser);
                                }
                                builder.debugAttributeBranchInfoMode = mode;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str2 = this.mStringParser.parse(jsonParser);
                                }
                                builder.debugAttributeBranchInfoSubBranch = str2;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str3 = this.mStringParser.parse(jsonParser);
                                }
                                builder.debugAttributeBranchInfoBranch = str3;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    date = this.mTimestampParser.parse(jsonParser);
                                }
                                builder.timestamp = date;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing AutobotDebugContext so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
