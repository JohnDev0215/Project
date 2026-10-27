package Podcast.Desktop.PodcastDetailTemplateInterface.v1_0;

import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionElementSerializer;
import Podcast.RefinementOptionsInterface.v1_0.ToggleRefinementOptionElementSerializer;
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
public class RefinementBarElementSerializer extends JsonSerializer<RefinementBarElement> {
    public static final RefinementBarElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RefinementBarElementSerializer refinementBarElementSerializer = new RefinementBarElementSerializer();
        INSTANCE = refinementBarElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.RefinementBarElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RefinementBarElement.class, refinementBarElementSerializer);
    }

    private RefinementBarElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RefinementBarElement refinementBarElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (refinementBarElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(refinementBarElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RefinementBarElement refinementBarElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("podcastId");
        SimpleSerializers.serializeString(refinementBarElement.getPodcastId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("seasonOptions");
        CheckBoxRefinementOptionElementSerializer checkBoxRefinementOptionElementSerializer = CheckBoxRefinementOptionElementSerializer.INSTANCE;
        checkBoxRefinementOptionElementSerializer.serialize(refinementBarElement.getSeasonOptions(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("downloadFilter");
        ToggleRefinementOptionElementSerializer toggleRefinementOptionElementSerializer = ToggleRefinementOptionElementSerializer.INSTANCE;
        toggleRefinementOptionElementSerializer.serialize(refinementBarElement.getDownloadFilter(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onChange");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(refinementBarElement.getOnChange(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("clientStateName");
        SimpleSerializers.serializeString(refinementBarElement.getClientStateName(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("unplayedFilter");
        toggleRefinementOptionElementSerializer.serialize(refinementBarElement.getUnplayedFilter(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("sortingOptions");
        checkBoxRefinementOptionElementSerializer.serialize(refinementBarElement.getSortingOptions(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onChangeWithDownloadFilter");
        jsonSerializer.serialize(refinementBarElement.getOnChangeWithDownloadFilter(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("timeout");
        SimpleSerializers.serializeInteger(refinementBarElement.getTimeout(), jsonGenerator, serializerProvider);
    }
}
