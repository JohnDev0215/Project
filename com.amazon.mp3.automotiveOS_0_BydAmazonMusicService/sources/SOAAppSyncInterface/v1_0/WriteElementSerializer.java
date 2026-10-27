package SOAAppSyncInterface.v1_0;

import Podcast.BiteBookmarkInterface.v1_0.BiteBookmarkWriteElement;
import Podcast.BiteBookmarkInterface.v1_0.BiteBookmarkWriteElementSerializer;
import Podcast.BookmarkInterface.v1_0.BookmarkMetadataWriteElement;
import Podcast.BookmarkInterface.v1_0.BookmarkMetadataWriteElementSerializer;
import Podcast.BookmarkInterface.v1_0.BookmarkWriteElement;
import Podcast.BookmarkInterface.v1_0.BookmarkWriteElementSerializer;
import Podcast.CategoryFollowInterface.v1_0.CategoryFollowWriteElement;
import Podcast.CategoryFollowInterface.v1_0.CategoryFollowWriteElementSerializer;
import Podcast.CompletedInterface.v1_0.CompletedMetadataWriteElement;
import Podcast.CompletedInterface.v1_0.CompletedMetadataWriteElementSerializer;
import Podcast.CompletedInterface.v1_0.CompletedWriteElement;
import Podcast.CompletedInterface.v1_0.CompletedWriteElementSerializer;
import Podcast.DownloadInterface.v1_0.DownloadWriteElement;
import Podcast.DownloadInterface.v1_0.DownloadWriteElementSerializer;
import Podcast.FollowInterface.v1_0.FollowMetadataWriteElement;
import Podcast.FollowInterface.v1_0.FollowMetadataWriteElementSerializer;
import Podcast.FollowInterface.v1_0.FollowWriteElement;
import Podcast.FollowInterface.v1_0.FollowWriteElementSerializer;
import Podcast.PlaybackMetricsInterface.v1_0.PlaybackMetricWriteElement;
import Podcast.PlaybackMetricsInterface.v1_0.PlaybackMetricWriteElementSerializer;
import Podcast.SaveInterface.v1_0.SaveMetadataWriteElement;
import Podcast.SaveInterface.v1_0.SaveMetadataWriteElementSerializer;
import Podcast.SaveInterface.v1_0.SaveWriteElement;
import Podcast.SaveInterface.v1_0.SaveWriteElementSerializer;
import Podcast.UIMetricsInterface.v1_0.UIMetricWriteElement;
import Podcast.UIMetricsInterface.v1_0.UIMetricWriteElementSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class WriteElementSerializer extends JsonSerializer<WriteElement> {
    public static final WriteElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        WriteElementSerializer writeElementSerializer = new WriteElementSerializer();
        INSTANCE = writeElementSerializer;
        SimpleModule simpleModule = new SimpleModule("SOAAppSyncInterface.v1_0.WriteElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(WriteElement.class, writeElementSerializer);
    }

    private WriteElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull WriteElement writeElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (writeElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        if (writeElement instanceof CategoryFollowWriteElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.CategoryFollowInterface.v1_0#CategoryFollowWriteElement");
            CategoryFollowWriteElementSerializer.INSTANCE.serializeFields((CategoryFollowWriteElement) writeElement, jsonGenerator, serializerProvider);
        } else if (writeElement instanceof DownloadWriteElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.DownloadInterface.v1_0#DownloadWriteElement");
            DownloadWriteElementSerializer.INSTANCE.serializeFields((DownloadWriteElement) writeElement, jsonGenerator, serializerProvider);
        } else if (writeElement instanceof FollowWriteElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.FollowInterface.v1_0#FollowWriteElement");
            FollowWriteElementSerializer.INSTANCE.serializeFields((FollowWriteElement) writeElement, jsonGenerator, serializerProvider);
        } else if (writeElement instanceof CompletedMetadataWriteElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.CompletedInterface.v1_0#CompletedMetadataWriteElement");
            CompletedMetadataWriteElementSerializer.INSTANCE.serializeFields((CompletedMetadataWriteElement) writeElement, jsonGenerator, serializerProvider);
        } else if (writeElement instanceof FollowMetadataWriteElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.FollowInterface.v1_0#FollowMetadataWriteElement");
            FollowMetadataWriteElementSerializer.INSTANCE.serializeFields((FollowMetadataWriteElement) writeElement, jsonGenerator, serializerProvider);
        } else if (writeElement instanceof BookmarkMetadataWriteElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.BookmarkInterface.v1_0#BookmarkMetadataWriteElement");
            BookmarkMetadataWriteElementSerializer.INSTANCE.serializeFields((BookmarkMetadataWriteElement) writeElement, jsonGenerator, serializerProvider);
        } else if (writeElement instanceof CompletedWriteElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.CompletedInterface.v1_0#CompletedWriteElement");
            CompletedWriteElementSerializer.INSTANCE.serializeFields((CompletedWriteElement) writeElement, jsonGenerator, serializerProvider);
        } else if (writeElement instanceof BiteBookmarkWriteElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.BiteBookmarkInterface.v1_0#BiteBookmarkWriteElement");
            BiteBookmarkWriteElementSerializer.INSTANCE.serializeFields((BiteBookmarkWriteElement) writeElement, jsonGenerator, serializerProvider);
        } else if (writeElement instanceof SaveMetadataWriteElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.SaveInterface.v1_0#SaveMetadataWriteElement");
            SaveMetadataWriteElementSerializer.INSTANCE.serializeFields((SaveMetadataWriteElement) writeElement, jsonGenerator, serializerProvider);
        } else if (writeElement instanceof UIMetricWriteElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.UIMetricsInterface.v1_0#UIMetricWriteElement");
            UIMetricWriteElementSerializer.INSTANCE.serializeFields((UIMetricWriteElement) writeElement, jsonGenerator, serializerProvider);
        } else if (writeElement instanceof SaveWriteElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.SaveInterface.v1_0#SaveWriteElement");
            SaveWriteElementSerializer.INSTANCE.serializeFields((SaveWriteElement) writeElement, jsonGenerator, serializerProvider);
        } else if (writeElement instanceof PlaybackMetricWriteElement) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackMetricsInterface.v1_0#PlaybackMetricWriteElement");
            PlaybackMetricWriteElementSerializer.INSTANCE.serializeFields((PlaybackMetricWriteElement) writeElement, jsonGenerator, serializerProvider);
        } else {
            boolean z = writeElement instanceof BookmarkWriteElement;
            jsonGenerator.writeStartObject();
            if (!z) {
                serializeFields(writeElement, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndObject();
                return;
            } else {
                jsonGenerator.writeFieldName("__type");
                jsonGenerator.writeString("Podcast.BookmarkInterface.v1_0#BookmarkWriteElement");
                BookmarkWriteElementSerializer.INSTANCE.serializeFields((BookmarkWriteElement) writeElement, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(WriteElement writeElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        throw new UnsupportedOperationException("Serialization for abstract type not supported");
    }
}
