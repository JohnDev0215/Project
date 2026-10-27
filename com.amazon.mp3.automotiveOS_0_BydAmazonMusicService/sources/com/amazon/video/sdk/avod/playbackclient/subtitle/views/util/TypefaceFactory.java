package com.amazon.video.sdk.avod.playbackclient.subtitle.views.util;

import android.content.Context;
import android.graphics.Typeface;
import android.text.method.TransformationMethod;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.SimpleCounterMetric;
import com.amazon.avod.util.Preconditions2;
import com.amazon.video.player.R$string;
import com.amazon.video.sdk.avod.playbackclient.subtitle.SubtitlePreset;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.io.File;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes5.dex */
public class TypefaceFactory {
    private static final ImmutableMap<SubtitlePreset.Font, FontDetails> FONT_TO_DETAILS_MAP = (ImmutableMap) Preconditions2.checkFullKeyMapping(SubtitlePreset.Font.class, ImmutableMap.builder().put(SubtitlePreset.Font.UNDEFINED, new FontDetails(R$string.font_default)).put(SubtitlePreset.Font.MONOSPACE_SERIF, new FontDetails(R$string.font_monospaced_serif)).put(SubtitlePreset.Font.PROPORTIONAL_SERIF, new FontDetails(R$string.font_proportional_serif)).put(SubtitlePreset.Font.MONOSPACE_SANS_SERIF, new FontDetails(R$string.font_monospaced_sans_serif)).put(SubtitlePreset.Font.PROPORTIONAL_SANS_SERIF, new FontDetails(R$string.font_proportional_sans_serif)).put(SubtitlePreset.Font.CASUAL, new FontDetails(R$string.font_casual)).put(SubtitlePreset.Font.CURSIVE, new FontDetails(R$string.font_cursive)).put(SubtitlePreset.Font.SMALL_CAPITALS, new FontDetails(R$string.font_small_capitals, TransformationMethods.ALL_CAPS)).build());
    private final Map<SubtitlePreset.Font, Typeface> mCachedTypefaceMap = Maps.newEnumMap(SubtitlePreset.Font.class);

    private static class SingletonHolder {
        private static final TypefaceFactory INSTANCE = new TypefaceFactory();

        private SingletonHolder() {
        }
    }

    public static TypefaceFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    TypefaceFactory() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Nonnull
    public Typeface getTypefaceFromFamily(@Nonnull Context context, @Nonnull SubtitlePreset.Font font) {
        if (this.mCachedTypefaceMap.containsKey(font)) {
            return this.mCachedTypefaceMap.get(font);
        }
        Typeface string = context.getResources().getString(FONT_TO_DETAILS_MAP.get(font).mFontNameId);
        if (Strings.isNullOrEmpty(string)) {
            this.mCachedTypefaceMap.put(font, Typeface.DEFAULT);
            return Typeface.DEFAULT;
        }
        try {
            if (new File(string).isAbsolute()) {
                string = Typeface.createFromFile(string);
            } else {
                string = Typeface.create(string, 0);
            }
        } catch (RuntimeException unused) {
            Profiler.reportCounterMetric(new SimpleCounterMetric("Subtitle-MissingFont", (ImmutableList<String>) ImmutableList.of((Object) "Counter", string)));
            string = Typeface.DEFAULT;
        }
        this.mCachedTypefaceMap.put(font, string);
        return string;
    }

    @Nonnull
    public TransformationMethod getTypefaceTransformation(@Nonnull SubtitlePreset.Font font) {
        return FONT_TO_DETAILS_MAP.get(font).mTransformationMethod;
    }

    private static class FontDetails {
        private final int mFontNameId;
        private final TransformationMethod mTransformationMethod;

        public FontDetails(int i) {
            this(i, TransformationMethods.NO_OP);
        }

        public FontDetails(int i, @Nonnull TransformationMethod transformationMethod) {
            this.mFontNameId = i;
            this.mTransformationMethod = transformationMethod;
        }
    }
}
