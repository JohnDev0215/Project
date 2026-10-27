package com.amazon.layout.music.model;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes3.dex */
public class BlockDeserializer extends JsonDeserializer<Block> {
    public static final BlockDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BlockDeserializer blockDeserializer = new BlockDeserializer();
        INSTANCE = blockDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.layout.music.model.BlockDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Block.class, blockDeserializer);
    }

    private BlockDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Block deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.layout.music.model#GalleryTile".equals(text)) {
                return GalleryTileDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#VerticalTile".equals(text)) {
                return VerticalTileDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#PeekTile".equals(text)) {
                return PeekTileDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#Icon".equals(text)) {
                return IconDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#HorizontalTile".equals(text)) {
                return HorizontalTileDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#RowItem".equals(text)) {
                return RowItemDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#FeaturedBarker".equals(text)) {
                return FeaturedBarkerDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#DoubleHorizontalTile".equals(text)) {
                return DoubleHorizontalTileDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#SubHeader".equals(text)) {
                return SubHeaderDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#Header".equals(text)) {
                return HeaderDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#FeaturedTile".equals(text)) {
                return FeaturedTileDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#HorizontalTileGrid".equals(text)) {
                return HorizontalTileGridDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#Gallery".equals(text)) {
                return GalleryDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#FlexibleVerticalTileGroup".equals(text)) {
                return FlexibleVerticalTileGroupDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#SeeMoreContainer".equals(text)) {
                return SeeMoreContainerDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#MultiList".equals(text)) {
                return MultiListDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#SingleList".equals(text)) {
                return SingleListDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#VerticalTileGroup".equals(text)) {
                return VerticalTileGroupDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#RowGroup".equals(text)) {
                return RowGroupDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#Barker".equals(text)) {
                return BarkerDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#Portfolio".equals(text)) {
                return PortfolioDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#ContentSelector".equals(text)) {
                return ContentSelectorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#NavigationToggle".equals(text)) {
                return NavigationToggleDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#ButtonGrid".equals(text)) {
                return ButtonGridDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#Carousel".equals(text)) {
                return CarouselDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#Table".equals(text)) {
                return TableDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#Peek".equals(text)) {
                return PeekDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#BlockContainer".equals(text)) {
                return BlockContainerDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#NavigationButton".equals(text)) {
                return NavigationButtonDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#CircularTile".equals(text)) {
                return CircularTileDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#Block".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Block deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        throw new UnsupportedOperationException("Deserialization for abstract type not supported");
    }
}
