package Podcast.PlaybackInterface.v1_0;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElementSerializer;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
import SOACoreInterface.v1_0.QueueSerializer;
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
public class SetMediaMethodSerializer extends JsonSerializer<SetMediaMethod> {
    public static final SetMediaMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SetMediaMethodSerializer setMediaMethodSerializer = new SetMediaMethodSerializer();
        INSTANCE = setMediaMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.SetMediaMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SetMediaMethod.class, setMediaMethodSerializer);
    }

    private SetMediaMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SetMediaMethod setMediaMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (setMediaMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(setMediaMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SetMediaMethod setMediaMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("actions");
        MediaActionsElementSerializer.INSTANCE.serialize(setMediaMethod.getActions(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("ignorable");
        SimpleSerializers.serializeBoolean(setMediaMethod.isIgnorable(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("metadata");
        MediaMetadataElementSerializer.INSTANCE.serialize(setMediaMethod.getMetadata(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("events");
        MediaEventsElementSerializer.INSTANCE.serialize(setMediaMethod.getEvents(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("controls");
        MediaControlsElementSerializer.INSTANCE.serialize(setMediaMethod.getControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onRestore");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(setMediaMethod.getOnRestore(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("episodeOptions");
        EpisodeOptionsElementSerializer.INSTANCE.serialize(setMediaMethod.getEpisodeOptions(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(setMediaMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(setMediaMethod.isForced(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onRestoreAndStart");
        jsonSerializer.serialize(setMediaMethod.getOnRestoreAndStart(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("mediaChangeReason");
        SimpleSerializers.serializeString(setMediaMethod.getMediaChangeReason(), jsonGenerator, serializerProvider);
    }
}
