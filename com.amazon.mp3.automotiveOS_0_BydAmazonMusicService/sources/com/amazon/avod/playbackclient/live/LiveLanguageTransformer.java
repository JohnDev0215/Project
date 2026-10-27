package com.amazon.avod.playbackclient.live;

import com.amazon.avod.core.Subtitle;
import com.amazon.avod.core.subtitle.SubtitleFormat;
import com.amazon.avod.core.subtitle.SubtitleSubtype;
import com.amazon.avod.core.subtitle.SubtitleType;
import com.amazon.avod.playback.LiveLanguageConfig;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.BiMap;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class LiveLanguageTransformer {
    private static final String DFXP = SubtitleFormat.DFXP.name();
    private final LiveLanguageConfig mLiveLanguageConfig;

    public LiveLanguageTransformer() {
        this(LiveLanguageConfig.getInstance());
    }

    LiveLanguageTransformer(@Nonnull LiveLanguageConfig liveLanguageConfig) {
        this.mLiveLanguageConfig = (LiveLanguageConfig) Preconditions.checkNotNull(liveLanguageConfig, "liveLanguageConfig");
    }

    public ImmutableList<Subtitle> transformToSubtitles(@Nonnull Set<String> set) {
        final BiMap<String, String> languageCodeMapping = this.mLiveLanguageConfig.getLanguageCodeMapping();
        final Map<String, String> displayNameMapping = this.mLiveLanguageConfig.getDisplayNameMapping();
        return FluentIterable.from(set).transform(new Function<String, Subtitle>() { // from class: com.amazon.avod.playbackclient.live.LiveLanguageTransformer.1ToSubtitle
            @Override // com.google.common.base.Function
            public Subtitle apply(String str) {
                if (str == null) {
                    return null;
                }
                Locale locale = Locale.US;
                String lowerCase = str.toLowerCase(locale);
                String str2 = (String) languageCodeMapping.get(lowerCase);
                String str3 = (String) displayNameMapping.get(lowerCase);
                if (str2 == null || str3 == null) {
                    return null;
                }
                return new Subtitle(str3, str2.toLowerCase(locale), "", SubtitleType.SUBTITLE.name(), SubtitleSubtype.DIALOG.name(), LiveLanguageTransformer.DFXP, null, false);
            }
        }).filter(Predicates.notNull()).toList();
    }

    @Nullable
    public String transformLanguageCodeToManifestCode(@Nonnull String str) {
        Preconditions.checkNotNull(str, "languageCode");
        BiMap<String, String> biMapInverse = this.mLiveLanguageConfig.getLanguageCodeMapping().inverse();
        Locale locale = Locale.US;
        String str2 = biMapInverse.get(str.toLowerCase(locale));
        if (str2 == null) {
            return null;
        }
        return str2.toLowerCase(locale);
    }

    @Nullable
    public String transformManifestCodeToLanguageCode(@Nonnull String str) {
        Preconditions.checkNotNull(str, "manifestCode");
        BiMap<String, String> languageCodeMapping = this.mLiveLanguageConfig.getLanguageCodeMapping();
        Locale locale = Locale.US;
        String str2 = languageCodeMapping.get(str.toLowerCase(locale));
        if (str2 == null) {
            return null;
        }
        return str2.toLowerCase(locale);
    }

    @Nullable
    public String transformManifestCodeToDisplayName(@Nonnull String str) {
        Preconditions.checkNotNull(str, "manifestCode");
        String str2 = this.mLiveLanguageConfig.getDisplayNameMapping().get(str.toLowerCase(Locale.US));
        if (str2 == null) {
            return null;
        }
        return str2;
    }
}
