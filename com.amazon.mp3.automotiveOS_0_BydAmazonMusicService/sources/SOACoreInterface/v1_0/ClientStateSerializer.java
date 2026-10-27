package SOACoreInterface.v1_0;

import Podcast.BiteBookmarkInterface.v1_0.BiteBookmarkOperationsClientState;
import Podcast.BiteBookmarkInterface.v1_0.BiteBookmarkOperationsClientStateSerializer;
import Podcast.BiteBookmarkInterface.v1_0.BiteBookmarksLastSyncTimeClientState;
import Podcast.BiteBookmarkInterface.v1_0.BiteBookmarksLastSyncTimeClientStateSerializer;
import Podcast.BookmarkInterface.v1_0.BookmarkOperationsClientState;
import Podcast.BookmarkInterface.v1_0.BookmarkOperationsClientStateSerializer;
import Podcast.BookmarkInterface.v1_0.BookmarksLastSyncTimeClientState;
import Podcast.BookmarkInterface.v1_0.BookmarksLastSyncTimeClientStateSerializer;
import Podcast.CategoryFollowInterface.v1_0.CategoryFollowOperationsClientState;
import Podcast.CategoryFollowInterface.v1_0.CategoryFollowOperationsClientStateSerializer;
import Podcast.CategoryFollowInterface.v1_0.CategoryFollowsLastSyncTimeClientState;
import Podcast.CategoryFollowInterface.v1_0.CategoryFollowsLastSyncTimeClientStateSerializer;
import Podcast.CompletedInterface.v1_0.CompletedLastSyncTimeClientState;
import Podcast.CompletedInterface.v1_0.CompletedLastSyncTimeClientStateSerializer;
import Podcast.CompletedInterface.v1_0.CompletedOperationsClientState;
import Podcast.CompletedInterface.v1_0.CompletedOperationsClientStateSerializer;
import Podcast.DeeplinkInterface.v1_0.DeeplinkClientState;
import Podcast.DeeplinkInterface.v1_0.DeeplinkClientStateSerializer;
import Podcast.DownloadInterface.v1_0.DownloadOperationsClientState;
import Podcast.DownloadInterface.v1_0.DownloadOperationsClientStateSerializer;
import Podcast.FollowInterface.v1_0.FollowOperationsClientState;
import Podcast.FollowInterface.v1_0.FollowOperationsClientStateSerializer;
import Podcast.FollowInterface.v1_0.FollowsLastSyncTimeClientState;
import Podcast.FollowInterface.v1_0.FollowsLastSyncTimeClientStateSerializer;
import Podcast.FollowPromptInterface.v1_0.HidePromptPreferenceClientState;
import Podcast.FollowPromptInterface.v1_0.HidePromptPreferenceClientStateSerializer;
import Podcast.PlaybackInterface.v1_0.LocalEpisodeAlreadyPlayingClientState;
import Podcast.PlaybackInterface.v1_0.LocalEpisodeAlreadyPlayingClientStateSerializer;
import Podcast.PlaybackMetricsInterface.v1_0.PlaybackMetricsOperationsClientState;
import Podcast.PlaybackMetricsInterface.v1_0.PlaybackMetricsOperationsClientStateSerializer;
import Podcast.SaveInterface.v1_0.SaveOperationsClientState;
import Podcast.SaveInterface.v1_0.SaveOperationsClientStateSerializer;
import Podcast.SaveInterface.v1_0.SavesLastSyncTimeClientState;
import Podcast.SaveInterface.v1_0.SavesLastSyncTimeClientStateSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCPreferencesClientState;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCPreferencesClientStateSerializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCSelectionsClientState;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCSelectionsClientStateSerializer;
import Podcast.Touch.PodcastDetailTemplateInterface.v1_0.RefinementOptionsClientState;
import Podcast.Touch.PodcastDetailTemplateInterface.v1_0.RefinementOptionsClientStateSerializer;
import Podcast.UIMetricsInterface.v1_0.UIMetricsOperationsClientState;
import Podcast.UIMetricsInterface.v1_0.UIMetricsOperationsClientStateSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class ClientStateSerializer extends JsonSerializer<ClientState> {
    public static final ClientStateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ClientStateSerializer clientStateSerializer = new ClientStateSerializer();
        INSTANCE = clientStateSerializer;
        SimpleModule simpleModule = new SimpleModule("SOACoreInterface.v1_0.ClientStateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ClientState.class, clientStateSerializer);
    }

    private ClientStateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ClientState clientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (clientState == null) {
            jsonGenerator.writeNull();
            return;
        }
        if (clientState instanceof CompletedOperationsClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.CompletedInterface.v1_0#CompletedOperationsClientState");
            CompletedOperationsClientStateSerializer.INSTANCE.serializeFields((CompletedOperationsClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof DownloadOperationsClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.DownloadInterface.v1_0#DownloadOperationsClientState");
            DownloadOperationsClientStateSerializer.INSTANCE.serializeFields((DownloadOperationsClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof FollowsLastSyncTimeClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.FollowInterface.v1_0#FollowsLastSyncTimeClientState");
            FollowsLastSyncTimeClientStateSerializer.INSTANCE.serializeFields((FollowsLastSyncTimeClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof LocalEpisodeAlreadyPlayingClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackInterface.v1_0#LocalEpisodeAlreadyPlayingClientState");
            LocalEpisodeAlreadyPlayingClientStateSerializer.INSTANCE.serializeFields((LocalEpisodeAlreadyPlayingClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof SaveOperationsClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.SaveInterface.v1_0#SaveOperationsClientState");
            SaveOperationsClientStateSerializer.INSTANCE.serializeFields((SaveOperationsClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof FollowOperationsClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.FollowInterface.v1_0#FollowOperationsClientState");
            FollowOperationsClientStateSerializer.INSTANCE.serializeFields((FollowOperationsClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof PTCPreferencesClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#PTCPreferencesClientState");
            PTCPreferencesClientStateSerializer.INSTANCE.serializeFields((PTCPreferencesClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof CompletedLastSyncTimeClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.CompletedInterface.v1_0#CompletedLastSyncTimeClientState");
            CompletedLastSyncTimeClientStateSerializer.INSTANCE.serializeFields((CompletedLastSyncTimeClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof BiteBookmarksLastSyncTimeClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.BiteBookmarkInterface.v1_0#BiteBookmarksLastSyncTimeClientState");
            BiteBookmarksLastSyncTimeClientStateSerializer.INSTANCE.serializeFields((BiteBookmarksLastSyncTimeClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof BookmarksLastSyncTimeClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.BookmarkInterface.v1_0#BookmarksLastSyncTimeClientState");
            BookmarksLastSyncTimeClientStateSerializer.INSTANCE.serializeFields((BookmarksLastSyncTimeClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof CategoryFollowsLastSyncTimeClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.CategoryFollowInterface.v1_0#CategoryFollowsLastSyncTimeClientState");
            CategoryFollowsLastSyncTimeClientStateSerializer.INSTANCE.serializeFields((CategoryFollowsLastSyncTimeClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof BookmarkOperationsClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.BookmarkInterface.v1_0#BookmarkOperationsClientState");
            BookmarkOperationsClientStateSerializer.INSTANCE.serializeFields((BookmarkOperationsClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof DeeplinkClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.DeeplinkInterface.v1_0#DeeplinkClientState");
            DeeplinkClientStateSerializer.INSTANCE.serializeFields((DeeplinkClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof HidePromptPreferenceClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.FollowPromptInterface.v1_0#HidePromptPreferenceClientState");
            HidePromptPreferenceClientStateSerializer.INSTANCE.serializeFields((HidePromptPreferenceClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof PTCSelectionsClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#PTCSelectionsClientState");
            PTCSelectionsClientStateSerializer.INSTANCE.serializeFields((PTCSelectionsClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof UIMetricsOperationsClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.UIMetricsInterface.v1_0#UIMetricsOperationsClientState");
            UIMetricsOperationsClientStateSerializer.INSTANCE.serializeFields((UIMetricsOperationsClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof RefinementOptionsClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#RefinementOptionsClientState");
            RefinementOptionsClientStateSerializer.INSTANCE.serializeFields((RefinementOptionsClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof CategoryFollowOperationsClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.CategoryFollowInterface.v1_0#CategoryFollowOperationsClientState");
            CategoryFollowOperationsClientStateSerializer.INSTANCE.serializeFields((CategoryFollowOperationsClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof PlaybackMetricsOperationsClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.PlaybackMetricsInterface.v1_0#PlaybackMetricsOperationsClientState");
            PlaybackMetricsOperationsClientStateSerializer.INSTANCE.serializeFields((PlaybackMetricsOperationsClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof Podcast.Touch.PodcastDetailTemplateInterface.v2_0.RefinementOptionsClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#RefinementOptionsClientState");
            Podcast.Touch.PodcastDetailTemplateInterface.v2_0.RefinementOptionsClientStateSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v2_0.RefinementOptionsClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof SavesLastSyncTimeClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.SaveInterface.v1_0#SavesLastSyncTimeClientState");
            SavesLastSyncTimeClientStateSerializer.INSTANCE.serializeFields((SavesLastSyncTimeClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof BiteBookmarkOperationsClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.BiteBookmarkInterface.v1_0#BiteBookmarkOperationsClientState");
            BiteBookmarkOperationsClientStateSerializer.INSTANCE.serializeFields((BiteBookmarkOperationsClientState) clientState, jsonGenerator, serializerProvider);
        } else if (clientState instanceof Podcast.Touch.PodcastDetailTemplateInterface.v3_0.RefinementOptionsClientState) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#RefinementOptionsClientState");
            Podcast.Touch.PodcastDetailTemplateInterface.v3_0.RefinementOptionsClientStateSerializer.INSTANCE.serializeFields((Podcast.Touch.PodcastDetailTemplateInterface.v3_0.RefinementOptionsClientState) clientState, jsonGenerator, serializerProvider);
        } else {
            boolean z = clientState instanceof Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.RefinementOptionsClientState;
            jsonGenerator.writeStartObject();
            if (!z) {
                serializeFields(clientState, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndObject();
                return;
            } else {
                jsonGenerator.writeFieldName("__type");
                jsonGenerator.writeString("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#RefinementOptionsClientState");
                Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.RefinementOptionsClientStateSerializer.INSTANCE.serializeFields((Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.RefinementOptionsClientState) clientState, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ClientState clientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        throw new UnsupportedOperationException("Serialization for abstract type not supported");
    }
}
