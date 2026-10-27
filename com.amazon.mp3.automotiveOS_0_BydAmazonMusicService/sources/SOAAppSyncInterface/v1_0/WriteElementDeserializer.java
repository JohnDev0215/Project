package SOAAppSyncInterface.v1_0;

import Podcast.BiteBookmarkInterface.v1_0.BiteBookmarkWriteElementDeserializer;
import Podcast.BookmarkInterface.v1_0.BookmarkMetadataWriteElementDeserializer;
import Podcast.BookmarkInterface.v1_0.BookmarkWriteElementDeserializer;
import Podcast.CategoryFollowInterface.v1_0.CategoryFollowWriteElementDeserializer;
import Podcast.CompletedInterface.v1_0.CompletedMetadataWriteElementDeserializer;
import Podcast.CompletedInterface.v1_0.CompletedWriteElementDeserializer;
import Podcast.DownloadInterface.v1_0.DownloadWriteElementDeserializer;
import Podcast.FollowInterface.v1_0.FollowMetadataWriteElementDeserializer;
import Podcast.FollowInterface.v1_0.FollowWriteElementDeserializer;
import Podcast.PlaybackMetricsInterface.v1_0.PlaybackMetricWriteElementDeserializer;
import Podcast.SaveInterface.v1_0.SaveMetadataWriteElementDeserializer;
import Podcast.SaveInterface.v1_0.SaveWriteElementDeserializer;
import Podcast.UIMetricsInterface.v1_0.UIMetricWriteElementDeserializer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class WriteElementDeserializer extends JsonDeserializer<WriteElement> {
    public static final WriteElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        WriteElementDeserializer writeElementDeserializer = new WriteElementDeserializer();
        INSTANCE = writeElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("SOAAppSyncInterface.v1_0.WriteElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(WriteElement.class, writeElementDeserializer);
    }

    private WriteElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public WriteElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.CategoryFollowInterface.v1_0#CategoryFollowWriteElement".equals(text)) {
                return CategoryFollowWriteElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.DownloadInterface.v1_0#DownloadWriteElement".equals(text)) {
                return DownloadWriteElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.FollowInterface.v1_0#FollowWriteElement".equals(text)) {
                return FollowWriteElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.CompletedInterface.v1_0#CompletedMetadataWriteElement".equals(text)) {
                return CompletedMetadataWriteElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.FollowInterface.v1_0#FollowMetadataWriteElement".equals(text)) {
                return FollowMetadataWriteElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.BookmarkInterface.v1_0#BookmarkMetadataWriteElement".equals(text)) {
                return BookmarkMetadataWriteElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.CompletedInterface.v1_0#CompletedWriteElement".equals(text)) {
                return CompletedWriteElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.BiteBookmarkInterface.v1_0#BiteBookmarkWriteElement".equals(text)) {
                return BiteBookmarkWriteElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.SaveInterface.v1_0#SaveMetadataWriteElement".equals(text)) {
                return SaveMetadataWriteElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.UIMetricsInterface.v1_0#UIMetricWriteElement".equals(text)) {
                return UIMetricWriteElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.SaveInterface.v1_0#SaveWriteElement".equals(text)) {
                return SaveWriteElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackMetricsInterface.v1_0#PlaybackMetricWriteElement".equals(text)) {
                return PlaybackMetricWriteElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.BookmarkInterface.v1_0#BookmarkWriteElement".equals(text)) {
                return BookmarkWriteElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOAAppSyncInterface.v1_0#WriteElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public WriteElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        throw new UnsupportedOperationException("Deserialization for abstract type not supported");
    }
}
