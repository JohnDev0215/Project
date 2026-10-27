package Podcast.Touch.PTCTemplateInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElementSerializer;
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
public class PTCEpisodesTemplateSerializer extends JsonSerializer<PTCEpisodesTemplate> {
    public static final PTCEpisodesTemplateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PTCEpisodesTemplateSerializer pTCEpisodesTemplateSerializer = new PTCEpisodesTemplateSerializer();
        INSTANCE = pTCEpisodesTemplateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PTCTemplateInterface.v1_0.PTCEpisodesTemplateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PTCEpisodesTemplate.class, pTCEpisodesTemplateSerializer);
    }

    private PTCEpisodesTemplateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PTCEpisodesTemplate pTCEpisodesTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (pTCEpisodesTemplate == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(pTCEpisodesTemplate, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PTCEpisodesTemplate pTCEpisodesTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("latencyMetricElement");
        LatencyMetricElementSerializer.INSTANCE.serialize(pTCEpisodesTemplate.getLatencyMetricElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_SUBTITLE);
        SimpleSerializers.serializeString(pTCEpisodesTemplate.getSubtitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onCreated");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(pTCEpisodesTemplate.getOnCreated(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(pTCEpisodesTemplate.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("footerButton");
        FooterButtonElementSerializer.INSTANCE.serialize(pTCEpisodesTemplate.getFooterButton(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageType");
        SimpleSerializers.serializeString(pTCEpisodesTemplate.getPageType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onBound");
        jsonSerializer.serialize(pTCEpisodesTemplate.getOnBound(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("cacheSeconds");
        SimpleSerializers.serializeLong(pTCEpisodesTemplate.getCacheSeconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        ListItemsSerializer.INSTANCE.serialize(pTCEpisodesTemplate.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(pTCEpisodesTemplate.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("closeButton");
        CloseButtonElementSerializer.INSTANCE.serialize(pTCEpisodesTemplate.getCloseButton(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("launchMode");
        SimpleSerializers.serializeString(pTCEpisodesTemplate.getLaunchMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onDismissed");
        jsonSerializer.serialize(pTCEpisodesTemplate.getOnDismissed(), jsonGenerator, serializerProvider);
    }
}
