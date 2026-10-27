package com.amazon.avod.playbackclient.subtitle;

import com.amazon.avod.playbackclient.subtitle.internal.SubtitleConfig;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguage;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguagePreference;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.IETFUtils;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitleLanguagePicker {
    final ImmutableList<MatchStrategy> MATCH_STRATEGIES;
    private final SubtitleConfig mSubtitleConfig;
    private final String mUiLanguageCode;

    public SubtitleLanguagePicker() {
        this(IETFUtils.toIETFLanguageTag(Locale.getDefault()), SubtitleConfig.getInstance());
    }

    public SubtitleLanguagePicker(@Nonnull String str, @Nonnull SubtitleConfig subtitleConfig) {
        this.MATCH_STRATEGIES = ImmutableList.of(new MatchStrategy(false, true), new MatchStrategy(true, true), new MatchStrategy(false, false), new MatchStrategy(true, false));
        this.mUiLanguageCode = (String) Preconditions.checkNotNull(str, "uiLanguageCode");
        this.mSubtitleConfig = (SubtitleConfig) Preconditions.checkNotNull(subtitleConfig, "subtitleConfig");
    }

    @Nullable
    public SubtitleLanguage getBestMatch(@Nonnull Collection<SubtitleLanguage> collection, @Nonnull SubtitleLanguagePreference subtitleLanguagePreference) {
        Preconditions.checkNotNull(collection, "availableLanguages");
        Preconditions.checkNotNull(subtitleLanguagePreference, "preference");
        SubtitleLanguage matchingSubtitleLanguage = getMatchingSubtitleLanguage(collection, subtitleLanguagePreference);
        if (matchingSubtitleLanguage != null) {
            return matchingSubtitleLanguage;
        }
        if (this.mSubtitleConfig.allowSelectingUiLanguageAsFallback()) {
            return getMatchingSubtitleLanguage(collection, new SubtitleLanguagePreference(this.mUiLanguageCode, subtitleLanguagePreference.getSubtitleType()));
        }
        return null;
    }

    @Nullable
    private SubtitleLanguage getMatchingSubtitleLanguage(@Nonnull Collection<SubtitleLanguage> collection, @Nonnull SubtitleLanguagePreference subtitleLanguagePreference) {
        DLog.logf("Searching subtitle for preference %s, %s", subtitleLanguagePreference.getLanguageCode(), subtitleLanguagePreference.getSubtitleType());
        UnmodifiableIterator<MatchStrategy> it = this.MATCH_STRATEGIES.iterator();
        while (it.hasNext()) {
            MatchStrategy next = it.next();
            for (SubtitleLanguage subtitleLanguage : collection) {
                if (next.accepts(subtitleLanguage, subtitleLanguagePreference)) {
                    return subtitleLanguage;
                }
            }
        }
        return null;
    }

    private static class MatchStrategy {
        private final boolean mUseOnlyLanguage;
        private final boolean mUseType;

        public MatchStrategy(boolean z, boolean z2) {
            this.mUseOnlyLanguage = z;
            this.mUseType = z2;
        }

        public boolean accepts(@Nonnull SubtitleLanguage subtitleLanguage, @Nonnull SubtitleLanguagePreference subtitleLanguagePreference) {
            String languageCode;
            boolean zEquals;
            if (Strings.isNullOrEmpty(subtitleLanguage.getLanguageCode())) {
                return false;
            }
            if (this.mUseOnlyLanguage) {
                languageCode = IETFUtils.getLocaleFromLanguageTag(subtitleLanguage.getLanguageCode()).getLanguage();
            } else {
                languageCode = subtitleLanguage.getLanguageCode();
            }
            String strSanitize = IETFUtils.sanitize(languageCode);
            String strSanitize2 = IETFUtils.sanitize(subtitleLanguagePreference.getLanguageCode());
            if (this.mUseOnlyLanguage) {
                zEquals = strSanitize2.contains(strSanitize);
            } else {
                zEquals = strSanitize2.equals(strSanitize);
            }
            return zEquals && (!this.mUseType || subtitleLanguage.getSubtitleType() == subtitleLanguagePreference.getSubtitleType());
        }
    }
}
