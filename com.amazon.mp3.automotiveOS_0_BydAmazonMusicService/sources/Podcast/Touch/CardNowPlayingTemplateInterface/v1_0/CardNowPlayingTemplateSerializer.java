package Podcast.Touch.CardNowPlayingTemplateInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElementSerializer;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class CardNowPlayingTemplateSerializer extends JsonSerializer<CardNowPlayingTemplate> {
    public static final CardNowPlayingTemplateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CardNowPlayingTemplateSerializer cardNowPlayingTemplateSerializer = new CardNowPlayingTemplateSerializer();
        INSTANCE = cardNowPlayingTemplateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.CardNowPlayingTemplateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CardNowPlayingTemplate.class, cardNowPlayingTemplateSerializer);
    }

    private CardNowPlayingTemplateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CardNowPlayingTemplate cardNowPlayingTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (cardNowPlayingTemplate == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(cardNowPlayingTemplate, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CardNowPlayingTemplate cardNowPlayingTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onEndOfList");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(cardNowPlayingTemplate.getOnEndOfList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("latencyMetricElement");
        LatencyMetricElementSerializer.INSTANCE.serialize(cardNowPlayingTemplate.getLatencyMetricElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("cacheSeconds");
        SimpleSerializers.serializeLong(cardNowPlayingTemplate.getCacheSeconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("cards");
        CardElementsSerializer.INSTANCE.serialize(cardNowPlayingTemplate.getCards(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onCreated");
        jsonSerializer.serialize(cardNowPlayingTemplate.getOnCreated(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(cardNowPlayingTemplate.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("launchMode");
        SimpleSerializers.serializeString(cardNowPlayingTemplate.getLaunchMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageType");
        SimpleSerializers.serializeString(cardNowPlayingTemplate.getPageType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onBound");
        jsonSerializer.serialize(cardNowPlayingTemplate.getOnBound(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onDismissed");
        jsonSerializer.serialize(cardNowPlayingTemplate.getOnDismissed(), jsonGenerator, serializerProvider);
    }
}
