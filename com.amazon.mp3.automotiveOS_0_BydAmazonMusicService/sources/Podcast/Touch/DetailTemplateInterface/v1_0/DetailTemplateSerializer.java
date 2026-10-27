package Podcast.Touch.DetailTemplateInterface.v1_0;

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
public class DetailTemplateSerializer extends JsonSerializer<DetailTemplate> {
    public static final DetailTemplateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        DetailTemplateSerializer detailTemplateSerializer = new DetailTemplateSerializer();
        INSTANCE = detailTemplateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.DetailTemplateInterface.v1_0.DetailTemplateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(DetailTemplate.class, detailTemplateSerializer);
    }

    private DetailTemplateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull DetailTemplate detailTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (detailTemplate == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(detailTemplate, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(DetailTemplate detailTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("latencyMetricElement");
        LatencyMetricElementSerializer.INSTANCE.serialize(detailTemplate.getLatencyMetricElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("featuredPinnedElement");
        FeaturedPinnedElementSerializer.INSTANCE.serialize(detailTemplate.getFeaturedPinnedElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("shards");
        DetailTemplateShardsElementSerializer.INSTANCE.serialize(detailTemplate.getShards(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("share");
        ShareElementSerializer.INSTANCE.serialize(detailTemplate.getShare(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onCreated");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(detailTemplate.getOnCreated(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("backgroundImage");
        SimpleSerializers.serializeString(detailTemplate.getBackgroundImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(detailTemplate.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageType");
        SimpleSerializers.serializeString(detailTemplate.getPageType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onBound");
        jsonSerializer.serialize(detailTemplate.getOnBound(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("header");
        HeaderElementSerializer.INSTANCE.serialize(detailTemplate.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("cacheSeconds");
        SimpleSerializers.serializeLong(detailTemplate.getCacheSeconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("about");
        AboutElementSerializer.INSTANCE.serialize(detailTemplate.getAbout(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("featuredItem");
        FeaturedElementSerializer.INSTANCE.serialize(detailTemplate.getFeaturedItem(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("launchMode");
        SimpleSerializers.serializeString(detailTemplate.getLaunchMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("follow");
        FollowElementSerializer.INSTANCE.serialize(detailTemplate.getFollow(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onDismissed");
        jsonSerializer.serialize(detailTemplate.getOnDismissed(), jsonGenerator, serializerProvider);
    }
}
