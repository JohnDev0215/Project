package Podcast.Touch.NowPlayingTemplateInterface.v1_0;

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
public class NowPlayingTemplateSerializer extends JsonSerializer<NowPlayingTemplate> {
    public static final NowPlayingTemplateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        NowPlayingTemplateSerializer nowPlayingTemplateSerializer = new NowPlayingTemplateSerializer();
        INSTANCE = nowPlayingTemplateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.NowPlayingTemplateInterface.v1_0.NowPlayingTemplateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(NowPlayingTemplate.class, nowPlayingTemplateSerializer);
    }

    private NowPlayingTemplateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull NowPlayingTemplate nowPlayingTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (nowPlayingTemplate == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(nowPlayingTemplate, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(NowPlayingTemplate nowPlayingTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("latencyMetricElement");
        LatencyMetricElementSerializer.INSTANCE.serialize(nowPlayingTemplate.getLatencyMetricElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("cacheSeconds");
        SimpleSerializers.serializeLong(nowPlayingTemplate.getCacheSeconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onCreated");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(nowPlayingTemplate.getOnCreated(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(nowPlayingTemplate.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("launchMode");
        SimpleSerializers.serializeString(nowPlayingTemplate.getLaunchMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageType");
        SimpleSerializers.serializeString(nowPlayingTemplate.getPageType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onBound");
        jsonSerializer.serialize(nowPlayingTemplate.getOnBound(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onDismissed");
        jsonSerializer.serialize(nowPlayingTemplate.getOnDismissed(), jsonGenerator, serializerProvider);
    }
}
