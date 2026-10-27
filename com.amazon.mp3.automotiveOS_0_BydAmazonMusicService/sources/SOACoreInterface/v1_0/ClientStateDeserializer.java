package SOACoreInterface.v1_0;

import Podcast.BiteBookmarkInterface.v1_0.BiteBookmarkOperationsClientStateDeserializer;
import Podcast.BiteBookmarkInterface.v1_0.BiteBookmarksLastSyncTimeClientStateDeserializer;
import Podcast.BookmarkInterface.v1_0.BookmarkOperationsClientStateDeserializer;
import Podcast.BookmarkInterface.v1_0.BookmarksLastSyncTimeClientStateDeserializer;
import Podcast.CategoryFollowInterface.v1_0.CategoryFollowOperationsClientStateDeserializer;
import Podcast.CategoryFollowInterface.v1_0.CategoryFollowsLastSyncTimeClientStateDeserializer;
import Podcast.CompletedInterface.v1_0.CompletedLastSyncTimeClientStateDeserializer;
import Podcast.CompletedInterface.v1_0.CompletedOperationsClientStateDeserializer;
import Podcast.DeeplinkInterface.v1_0.DeeplinkClientStateDeserializer;
import Podcast.DownloadInterface.v1_0.DownloadOperationsClientStateDeserializer;
import Podcast.FollowInterface.v1_0.FollowOperationsClientStateDeserializer;
import Podcast.FollowInterface.v1_0.FollowsLastSyncTimeClientStateDeserializer;
import Podcast.FollowPromptInterface.v1_0.HidePromptPreferenceClientStateDeserializer;
import Podcast.PlaybackInterface.v1_0.LocalEpisodeAlreadyPlayingClientStateDeserializer;
import Podcast.PlaybackMetricsInterface.v1_0.PlaybackMetricsOperationsClientStateDeserializer;
import Podcast.SaveInterface.v1_0.SaveOperationsClientStateDeserializer;
import Podcast.SaveInterface.v1_0.SavesLastSyncTimeClientStateDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCPreferencesClientStateDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCSelectionsClientStateDeserializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v1_0.RefinementOptionsClientStateDeserializer;
import Podcast.UIMetricsInterface.v1_0.UIMetricsOperationsClientStateDeserializer;
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
public class ClientStateDeserializer extends JsonDeserializer<ClientState> {
    public static final ClientStateDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ClientStateDeserializer clientStateDeserializer = new ClientStateDeserializer();
        INSTANCE = clientStateDeserializer;
        SimpleModule simpleModule = new SimpleModule("SOACoreInterface.v1_0.ClientStateDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(ClientState.class, clientStateDeserializer);
    }

    private ClientStateDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public ClientState deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.CompletedInterface.v1_0#CompletedOperationsClientState".equals(text)) {
                return CompletedOperationsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.DownloadInterface.v1_0#DownloadOperationsClientState".equals(text)) {
                return DownloadOperationsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.FollowInterface.v1_0#FollowsLastSyncTimeClientState".equals(text)) {
                return FollowsLastSyncTimeClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#LocalEpisodeAlreadyPlayingClientState".equals(text)) {
                return LocalEpisodeAlreadyPlayingClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.SaveInterface.v1_0#SaveOperationsClientState".equals(text)) {
                return SaveOperationsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.FollowInterface.v1_0#FollowOperationsClientState".equals(text)) {
                return FollowOperationsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#PTCPreferencesClientState".equals(text)) {
                return PTCPreferencesClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.CompletedInterface.v1_0#CompletedLastSyncTimeClientState".equals(text)) {
                return CompletedLastSyncTimeClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.BiteBookmarkInterface.v1_0#BiteBookmarksLastSyncTimeClientState".equals(text)) {
                return BiteBookmarksLastSyncTimeClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.BookmarkInterface.v1_0#BookmarksLastSyncTimeClientState".equals(text)) {
                return BookmarksLastSyncTimeClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.CategoryFollowInterface.v1_0#CategoryFollowsLastSyncTimeClientState".equals(text)) {
                return CategoryFollowsLastSyncTimeClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.BookmarkInterface.v1_0#BookmarkOperationsClientState".equals(text)) {
                return BookmarkOperationsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.DeeplinkInterface.v1_0#DeeplinkClientState".equals(text)) {
                return DeeplinkClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.FollowPromptInterface.v1_0#HidePromptPreferenceClientState".equals(text)) {
                return HidePromptPreferenceClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#PTCSelectionsClientState".equals(text)) {
                return PTCSelectionsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.UIMetricsInterface.v1_0#UIMetricsOperationsClientState".equals(text)) {
                return UIMetricsOperationsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#RefinementOptionsClientState".equals(text)) {
                return RefinementOptionsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.CategoryFollowInterface.v1_0#CategoryFollowOperationsClientState".equals(text)) {
                return CategoryFollowOperationsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackMetricsInterface.v1_0#PlaybackMetricsOperationsClientState".equals(text)) {
                return PlaybackMetricsOperationsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#RefinementOptionsClientState".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v2_0.RefinementOptionsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.SaveInterface.v1_0#SavesLastSyncTimeClientState".equals(text)) {
                return SavesLastSyncTimeClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.BiteBookmarkInterface.v1_0#BiteBookmarkOperationsClientState".equals(text)) {
                return BiteBookmarkOperationsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#RefinementOptionsClientState".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v3_0.RefinementOptionsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#RefinementOptionsClientState".equals(text)) {
                return Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.RefinementOptionsClientStateDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOACoreInterface.v1_0#ClientState".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public ClientState deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        throw new UnsupportedOperationException("Deserialization for abstract type not supported");
    }
}
