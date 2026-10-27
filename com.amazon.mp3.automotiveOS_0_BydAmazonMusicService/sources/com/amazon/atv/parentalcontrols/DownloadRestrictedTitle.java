package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.ProfileToChallengeDetails;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
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

/* JADX INFO: loaded from: classes2.dex */
public class DownloadRestrictedTitle {
    public final Optional<String> identifier;
    public final Optional<ImmutableList<ProfileToChallengeDetails>> profileToChallengeDetails;

    private DownloadRestrictedTitle(Builder builder) {
        this.identifier = Optional.fromNullable(builder.identifier);
        this.profileToChallengeDetails = Optional.fromNullable(builder.profileToChallengeDetails);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DownloadRestrictedTitle)) {
            return false;
        }
        DownloadRestrictedTitle downloadRestrictedTitle = (DownloadRestrictedTitle) obj;
        return Objects.equal(this.identifier, downloadRestrictedTitle.identifier) && Objects.equal(this.profileToChallengeDetails, downloadRestrictedTitle.profileToChallengeDetails);
    }

    public int hashCode() {
        return Objects.hashCode(this.identifier, this.profileToChallengeDetails);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("identifier", this.identifier).add("profileToChallengeDetails", this.profileToChallengeDetails).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String identifier;
        public ImmutableList<ProfileToChallengeDetails> profileToChallengeDetails;

        public DownloadRestrictedTitle build() {
            return new DownloadRestrictedTitle(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<DownloadRestrictedTitle> {
        private final SimpleParsers.StringParser midentifierParser;
        private final ListParser<ProfileToChallengeDetails> mprofileToChallengeDetailsListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mprofileToChallengeDetailsListParser = ListParser.newParser(new ProfileToChallengeDetails.Parser(objectMapper));
            this.midentifierParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public DownloadRestrictedTitle parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DownloadRestrictedTitle:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public DownloadRestrictedTitle parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("DownloadRestrictedTitle:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private DownloadRestrictedTitle parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "DownloadRestrictedTitle");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1618432855) {
                        if (iHashCode == 1781166723 && next.equals("profileToChallengeDetails")) {
                            b = 1;
                        }
                    } else if (next.equals("identifier")) {
                        b = 0;
                    }
                    ImmutableList<ProfileToChallengeDetails> immutableList = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.midentifierParser.parse(jsonNode2);
                        }
                        builder.identifier = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mprofileToChallengeDetailsListParser.parse(jsonNode2);
                        }
                        builder.profileToChallengeDetails = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing DownloadRestrictedTitle so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private DownloadRestrictedTitle parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            int iHashCode = currentName.hashCode();
                            if (iHashCode != -1618432855) {
                                if (iHashCode == 1781166723 && currentName.equals("profileToChallengeDetails")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("identifier")) {
                                b = 0;
                            }
                            ImmutableList<ProfileToChallengeDetails> immutableList = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.midentifierParser.parse(jsonParser);
                                }
                                builder.identifier = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mprofileToChallengeDetailsListParser.parse(jsonParser);
                                }
                                builder.profileToChallengeDetails = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing DownloadRestrictedTitle so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
