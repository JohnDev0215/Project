package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.PinChallengeDetails;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
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
public class ProfileToChallengeDetails {
    public final Optional<PinChallengeDetails> challengeDetails;
    public final Optional<String> profileACI;

    private ProfileToChallengeDetails(Builder builder) {
        this.profileACI = Optional.fromNullable(builder.profileACI);
        this.challengeDetails = Optional.fromNullable(builder.challengeDetails);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProfileToChallengeDetails)) {
            return false;
        }
        ProfileToChallengeDetails profileToChallengeDetails = (ProfileToChallengeDetails) obj;
        return Objects.equal(this.profileACI, profileToChallengeDetails.profileACI) && Objects.equal(this.challengeDetails, profileToChallengeDetails.challengeDetails);
    }

    public int hashCode() {
        return Objects.hashCode(this.profileACI, this.challengeDetails);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("profileACI", this.profileACI).add("challengeDetails", this.challengeDetails).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public PinChallengeDetails challengeDetails;
        public String profileACI;

        public ProfileToChallengeDetails build() {
            return new ProfileToChallengeDetails(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<ProfileToChallengeDetails> {
        private final PinChallengeDetails.Parser mPinChallengeDetailsParser;
        private final SimpleParsers.StringParser mprofileACIParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mPinChallengeDetailsParser = new PinChallengeDetails.Parser(objectMapper);
            this.mprofileACIParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ProfileToChallengeDetails parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ProfileToChallengeDetails:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ProfileToChallengeDetails parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("ProfileToChallengeDetails:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private ProfileToChallengeDetails parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "ProfileToChallengeDetails");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1102666210) {
                        if (iHashCode == 1225255007 && next.equals("challengeDetails")) {
                            b = 1;
                        }
                    } else if (next.equals("profileACI")) {
                        b = 0;
                    }
                    PinChallengeDetails pinChallengeDetails = null;
                    String str = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.mprofileACIParser.parse(jsonNode2);
                        }
                        builder.profileACI = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            pinChallengeDetails = this.mPinChallengeDetailsParser.parse(jsonNode2);
                        }
                        builder.challengeDetails = pinChallengeDetails;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing ProfileToChallengeDetails so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            return builder.build();
        }

        @Nonnull
        private ProfileToChallengeDetails parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            if (iHashCode != -1102666210) {
                                if (iHashCode == 1225255007 && currentName.equals("challengeDetails")) {
                                    b = 1;
                                }
                            } else if (currentName.equals("profileACI")) {
                                b = 0;
                            }
                            PinChallengeDetails pinChallengeDetails = null;
                            String str = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.mprofileACIParser.parse(jsonParser);
                                }
                                builder.profileACI = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    pinChallengeDetails = this.mPinChallengeDetailsParser.parse(jsonParser);
                                }
                                builder.challengeDetails = pinChallengeDetails;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing ProfileToChallengeDetails so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    return builder.build();
                }
            }
        }
    }
}
