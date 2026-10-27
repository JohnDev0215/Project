package com.amazon.musicsubscriptionofferservice;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.destination.parser.ParserUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class PlanScheduleDeserializer extends JsonDeserializer<PlanSchedule> {
    public static final PlanScheduleDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PlanScheduleDeserializer planScheduleDeserializer = new PlanScheduleDeserializer();
        INSTANCE = planScheduleDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.PlanScheduleDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(PlanSchedule.class, planScheduleDeserializer);
    }

    private PlanScheduleDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public PlanSchedule deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken != JsonToken.START_OBJECT) {
            throw new JsonParseException(jsonParser, "Expected start of object, got " + currentToken, jsonParser.getTokenLocation());
        }
        if (jsonParser.nextToken() == JsonToken.FIELD_NAME && "__type".equals(jsonParser.getCurrentName())) {
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            String text = jsonParser.getText();
            jsonParser.nextToken();
            if ("com.amazon.musicsubscriptionofferservice#PlanSchedule".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public PlanSchedule deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        PlanSchedule planSchedule = new PlanSchedule();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if (ParserUtil.SCHEDULE_ID_QUERY_PARAM_NAME.equals(currentName)) {
                planSchedule.setScheduleId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("addonRequired".equals(currentName)) {
                planSchedule.setAddonRequired(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("subscriptionType".equals(currentName)) {
                planSchedule.setSubscriptionType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("musicSubscriptionOfferMetadata".equals(currentName)) {
                planSchedule.setMusicSubscriptionOfferMetadata(MusicSubscriptionOfferMetadataDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("promoType".equals(currentName)) {
                planSchedule.setPromoType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("addonScheduleIds".equals(currentName)) {
                planSchedule.setAddonScheduleIds(AddonScheduleIdsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("planOffers".equals(currentName)) {
                planSchedule.setPlanOffers(PlanOffersDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return planSchedule;
    }
}
