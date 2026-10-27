package com.amazon.video.sdk.avod.playbackclient.subtitle;

import androidx.annotation.Nullable;
import com.amazon.avod.core.subtitle.SubtitleSubtype;
import com.amazon.avod.core.subtitle.SubtitleType;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusTimedTextReporter;
import com.amazon.avod.playback.event.playback.TimedTextChangedEvent;
import com.amazon.avod.playbackclient.listeners.BasePlaybackStateEventListener;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguage;

/* JADX INFO: loaded from: classes5.dex */
public class SubtitleEventReporter extends BasePlaybackStateEventListener {

    @Nullable
    private final AloysiusTimedTextReporter mAloysiusTimedTextReporter;

    public SubtitleEventReporter(@Nullable AloysiusTimedTextReporter aloysiusTimedTextReporter) {
        this.mAloysiusTimedTextReporter = aloysiusTimedTextReporter;
    }

    public void reportTimedTextEventToAloysius(boolean z, @Nullable SubtitleLanguage subtitleLanguage) {
        String languageCode = subtitleLanguage == null ? null : subtitleLanguage.getLanguageCode();
        TimedTextChangedEvent.AloysiusTimedTextType aloysiusSubtitleType = getAloysiusSubtitleType(subtitleLanguage);
        TimedTextChangedEvent.AloysiusTimedTextSubtype aloysiusSubtitleSubtype = getAloysiusSubtitleSubtype(subtitleLanguage);
        AloysiusTimedTextReporter aloysiusTimedTextReporter = this.mAloysiusTimedTextReporter;
        if (aloysiusTimedTextReporter != null) {
            aloysiusTimedTextReporter.handleTimedTextEvent(new TimedTextChangedEvent(z, languageCode, aloysiusSubtitleType, aloysiusSubtitleSubtype));
        }
    }

    @Nullable
    private TimedTextChangedEvent.AloysiusTimedTextType getAloysiusSubtitleType(@Nullable SubtitleLanguage subtitleLanguage) {
        if (subtitleLanguage == null) {
            return null;
        }
        if (subtitleLanguage.isForced()) {
            return TimedTextChangedEvent.AloysiusTimedTextType.ForcedNarrative;
        }
        if (subtitleLanguage.getSubtitleType() == SubtitleType.CAPTION) {
            return TimedTextChangedEvent.AloysiusTimedTextType.Caption;
        }
        return TimedTextChangedEvent.AloysiusTimedTextType.Subtitle;
    }

    @Nullable
    private TimedTextChangedEvent.AloysiusTimedTextSubtype getAloysiusSubtitleSubtype(@Nullable SubtitleLanguage subtitleLanguage) {
        if (subtitleLanguage == null || subtitleLanguage.getSubtitleSubtype() == null) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$core$subtitle$SubtitleSubtype[subtitleLanguage.getSubtitleSubtype().ordinal()];
        if (i == 1) {
            return TimedTextChangedEvent.AloysiusTimedTextSubtype.Commentary;
        }
        if (i == 2) {
            return TimedTextChangedEvent.AloysiusTimedTextSubtype.Descriptive;
        }
        return TimedTextChangedEvent.AloysiusTimedTextSubtype.Dialog;
    }

    /* JADX INFO: renamed from: com.amazon.video.sdk.avod.playbackclient.subtitle.SubtitleEventReporter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$core$subtitle$SubtitleSubtype;

        static {
            int[] iArr = new int[SubtitleSubtype.values().length];
            $SwitchMap$com$amazon$avod$core$subtitle$SubtitleSubtype = iArr;
            try {
                iArr[SubtitleSubtype.COMMENTARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleSubtype[SubtitleSubtype.DESCRIPTIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleSubtype[SubtitleSubtype.DIALOG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
