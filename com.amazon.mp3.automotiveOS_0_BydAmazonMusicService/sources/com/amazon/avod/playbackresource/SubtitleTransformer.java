package com.amazon.avod.playbackresource;

import com.amazon.atvplaybackresource.SubtitleUrlLanguageOption;
import com.amazon.avod.core.Subtitle;
import com.amazon.avod.util.DLog;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitleTransformer {
    @Nonnull
    public ImmutableList<Subtitle> transform(@Nonnull Optional<ImmutableList<SubtitleUrlLanguageOption>> optional, boolean z) {
        if (!optional.isPresent()) {
            return ImmutableList.of();
        }
        return FluentIterable.from(optional.get()).transform(new WireTypeToSubtitle(z)).filter(Predicates.notNull()).toList();
    }

    private static class WireTypeToSubtitle implements Function<SubtitleUrlLanguageOption, Subtitle> {
        private final boolean mIsForced;

        public WireTypeToSubtitle(boolean z) {
            this.mIsForced = z;
        }

        @Override // com.google.common.base.Function
        @Nullable
        public Subtitle apply(@Nonnull SubtitleUrlLanguageOption subtitleUrlLanguageOption) {
            Preconditions.checkNotNull(subtitleUrlLanguageOption, "item from ImmutableList should not be null");
            if (subtitleUrlLanguageOption.displayName.isPresent() && subtitleUrlLanguageOption.languageCode.isPresent() && subtitleUrlLanguageOption.url.isPresent() && subtitleUrlLanguageOption.type.isPresent() && subtitleUrlLanguageOption.subtype.isPresent() && subtitleUrlLanguageOption.format.isPresent()) {
                return new Subtitle(subtitleUrlLanguageOption.displayName.get(), subtitleUrlLanguageOption.languageCode.get(), subtitleUrlLanguageOption.url.get(), subtitleUrlLanguageOption.type.get(), subtitleUrlLanguageOption.subtype.get(), subtitleUrlLanguageOption.format.get(), subtitleUrlLanguageOption.trackGroupId.orNull(), this.mIsForced);
            }
            DLog.warnf("Unusable SubtitleUrlLanguageOption: %s/%s/%s/%s/%s/%s/%s", subtitleUrlLanguageOption.displayName, subtitleUrlLanguageOption.languageCode, subtitleUrlLanguageOption.url, subtitleUrlLanguageOption.type, subtitleUrlLanguageOption.subtype, subtitleUrlLanguageOption.format, subtitleUrlLanguageOption.trackGroupId);
            return null;
        }
    }
}
