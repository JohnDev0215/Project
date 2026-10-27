package com.amazon.musicensembleservice.brush;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class EntityDeserializer extends JsonDeserializer<Entity> {
    public static final EntityDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EntityDeserializer entityDeserializer = new EntityDeserializer();
        INSTANCE = entityDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.brush.EntityDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Entity.class, entityDeserializer);
    }

    private EntityDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Entity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicensembleservice.brush#Message".equals(text)) {
                return MessageDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#Commentary".equals(text)) {
                return CommentaryDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#LiveStream".equals(text)) {
                return LiveStreamDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#LiveEvent".equals(text)) {
                return LiveEventDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#Genre".equals(text)) {
                return GenreDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#StreamProvider".equals(text)) {
                return StreamProviderDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#UserPlaylist".equals(text)) {
                return UserPlaylistDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#PodcastEpisode".equals(text)) {
                return PodcastEpisodeDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#Station".equals(text)) {
                return StationDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#VideoStory".equals(text)) {
                return VideoStoryDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#Album".equals(text)) {
                return AlbumDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#Track".equals(text)) {
                return TrackDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#Playlist".equals(text)) {
                return PlaylistDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#Artist".equals(text)) {
                return ArtistDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#CatalogEntity".equals(text)) {
                return CatalogEntityDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#PodcastShow".equals(text)) {
                return PodcastShowDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#LiveSport".equals(text)) {
                return LiveSportDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#CommunityPlaylist".equals(text)) {
                return CommunityPlaylistDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#Merch".equals(text)) {
                return MerchDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#PlaybackContainer".equals(text)) {
                return PlaybackContainerDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#FeaturedPlay".equals(text)) {
                return FeaturedPlayDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#PodcastBite".equals(text)) {
                return PodcastBiteDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#MoodsAndActivities".equals(text)) {
                return MoodsAndActivitiesDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#MusicEntity".equals(text)) {
                return MusicEntityDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#CustomerProfile".equals(text)) {
                return CustomerProfileDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#Button".equals(text)) {
                return ButtonDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#FeaturedBarker".equals(text)) {
                return FeaturedBarkerDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#Barker".equals(text)) {
                return BarkerDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#AbstractBarker".equals(text)) {
                return AbstractBarkerDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#NavigationEntity".equals(text)) {
                return NavigationEntityDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicensembleservice.brush#Entity".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Entity deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        throw new UnsupportedOperationException("Deserialization for abstract type not supported");
    }
}
