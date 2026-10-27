package Podcast.Touch.CardNowPlayingTemplateInterface.v1_0;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElementSerializer;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.android.gms.cast.MediaTrack;
import java.io.IOException;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class CardElementSerializer extends JsonSerializer<CardElement> {
    public static final CardElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CardElementSerializer cardElementSerializer = new CardElementSerializer();
        INSTANCE = cardElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.CardElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CardElement.class, cardElementSerializer);
    }

    private CardElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CardElement cardElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (cardElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(cardElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CardElement cardElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(MediaTrack.ROLE_DESCRIPTION);
        SimpleSerializers.serializeString(cardElement.getDescription(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("follow");
        FollowButtonElementSerializer.INSTANCE.serialize(cardElement.getFollow(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onCardViewed");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(cardElement.getOnCardViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onPauseSelected");
        jsonSerializer.serialize(cardElement.getOnPauseSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onCardCentered");
        jsonSerializer.serialize(cardElement.getOnCardCentered(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onSwipeNext");
        jsonSerializer.serialize(cardElement.getOnSwipeNext(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onTapNext");
        jsonSerializer.serialize(cardElement.getOnTapNext(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("save");
        SaveButtonElementSerializer.INSTANCE.serialize(cardElement.getSave(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onAboutSelected");
        jsonSerializer.serialize(cardElement.getOnAboutSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onPlaySelected");
        jsonSerializer.serialize(cardElement.getOnPlaySelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("primaryButton");
        ButtonElementSerializer.INSTANCE.serialize(cardElement.getPrimaryButton(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("episodeOptions");
        EpisodeOptionsElementSerializer.INSTANCE.serialize(cardElement.getEpisodeOptions(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.SECONDARY_TEXT);
        SimpleSerializers.serializeString(cardElement.getSecondaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onSwipePrevious");
        jsonSerializer.serialize(cardElement.getOnSwipePrevious(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onTapPrevious");
        jsonSerializer.serialize(cardElement.getOnTapPrevious(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(cardElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.PRIMARY_TEXT);
        SimpleSerializers.serializeString(cardElement.getPrimaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(cardElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onCardSelected");
        jsonSerializer.serialize(cardElement.getOnCardSelected(), jsonGenerator, serializerProvider);
    }
}
