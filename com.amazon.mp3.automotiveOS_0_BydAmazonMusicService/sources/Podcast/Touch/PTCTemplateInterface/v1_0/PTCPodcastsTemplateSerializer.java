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
public class PTCPodcastsTemplateSerializer extends JsonSerializer<PTCPodcastsTemplate> {
    public static final PTCPodcastsTemplateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PTCPodcastsTemplateSerializer pTCPodcastsTemplateSerializer = new PTCPodcastsTemplateSerializer();
        INSTANCE = pTCPodcastsTemplateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PTCTemplateInterface.v1_0.PTCPodcastsTemplateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PTCPodcastsTemplate.class, pTCPodcastsTemplateSerializer);
    }

    private PTCPodcastsTemplateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PTCPodcastsTemplate pTCPodcastsTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (pTCPodcastsTemplate == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(pTCPodcastsTemplate, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PTCPodcastsTemplate pTCPodcastsTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("latencyMetricElement");
        LatencyMetricElementSerializer.INSTANCE.serialize(pTCPodcastsTemplate.getLatencyMetricElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onEndOfList");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(pTCPodcastsTemplate.getOnEndOfList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        GridElementsSerializer.INSTANCE.serialize(pTCPodcastsTemplate.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onCreated");
        jsonSerializer.serialize(pTCPodcastsTemplate.getOnCreated(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(pTCPodcastsTemplate.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageType");
        SimpleSerializers.serializeString(pTCPodcastsTemplate.getPageType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onBound");
        jsonSerializer.serialize(pTCPodcastsTemplate.getOnBound(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("trailingButton");
        TrailingButtonElementSerializer.INSTANCE.serialize(pTCPodcastsTemplate.getTrailingButton(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("cacheSeconds");
        SimpleSerializers.serializeLong(pTCPodcastsTemplate.getCacheSeconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_SUBTITLE);
        SimpleSerializers.serializeString(pTCPodcastsTemplate.getSubtitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(pTCPodcastsTemplate.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("launchMode");
        SimpleSerializers.serializeString(pTCPodcastsTemplate.getLaunchMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("leadingButton");
        LeadingButtonElementSerializer.INSTANCE.serialize(pTCPodcastsTemplate.getLeadingButton(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onDismissed");
        jsonSerializer.serialize(pTCPodcastsTemplate.getOnDismissed(), jsonGenerator, serializerProvider);
    }
}
