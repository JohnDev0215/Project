package com.amazon.music.media.playback.config;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class ConvertingItemLoader<KEY, INTERMEDIATE, VALUE> implements ItemLoader<KEY, VALUE> {
    private Converter<? super INTERMEDIATE, ? extends VALUE> converter;
    private ItemLoader<? super KEY, ? extends INTERMEDIATE> sourceLoader;

    public interface Converter<FROM, TO> {
        @NonNull
        TO convert(@NonNull FROM from) throws IOException;

        @Nullable
        TO fastConvert(@NonNull FROM from);
    }

    public ConvertingItemLoader(@Nullable ItemLoader<? super KEY, ? extends INTERMEDIATE> itemLoader, @NonNull Converter<? super INTERMEDIATE, ? extends VALUE> converter) {
        this.sourceLoader = itemLoader;
        this.converter = (Converter) Validate.notNull(converter);
    }

    @Override // com.amazon.music.media.playback.config.ItemLoader
    @Nullable
    public VALUE getIfCached(@NonNull KEY key) {
        INTERMEDIATE ifCached;
        ItemLoader<? super KEY, ? extends INTERMEDIATE> itemLoader = this.sourceLoader;
        if (itemLoader == null || (ifCached = itemLoader.getIfCached(key)) == null) {
            return null;
        }
        return this.converter.fastConvert(ifCached);
    }

    @Override // com.amazon.music.media.playback.config.ItemLoader
    @NonNull
    public VALUE load(@NonNull KEY key) throws IOException {
        ItemLoader<? super KEY, ? extends INTERMEDIATE> itemLoader = this.sourceLoader;
        if (itemLoader == null) {
            throw new IOException("sourceLoader not set.");
        }
        return this.converter.convert(itemLoader.load(key));
    }

    public void setSource(@Nullable ItemLoader<? super KEY, ? extends INTERMEDIATE> itemLoader) {
        this.sourceLoader = (ItemLoader) Validate.notNull(itemLoader);
    }
}
