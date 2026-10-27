package com.amazon.mp3.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.activity.settings.SettingsUtil;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistsImportRecurrenceExperience.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0016\u0018\u0000 #2\u00020\u0001:\u0001#B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0011\u001a\u00020\u0012J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\u0012H\u0002J\b\u0010\u0017\u001a\u00020\u0014H\u0002J\b\u0010\u0018\u001a\u00020\u0014H\u0002J\u0006\u0010\u0019\u001a\u00020\u0014J\b\u0010\u001a\u001a\u00020\u0012H\u0002J\b\u0010\u001b\u001a\u00020\u0012H\u0002J\b\u0010\u001c\u001a\u00020\u0012H\u0002J\b\u0010\u001d\u001a\u00020\u0012H\u0002J\b\u0010\u001e\u001a\u00020\u0014H\u0002J\b\u0010\r\u001a\u00020\u0012H\u0002J\b\u0010\u001f\u001a\u00020\u0014H\u0002J\b\u0010 \u001a\u00020\u0012H\u0002J\b\u0010!\u001a\u00020\u0012H\u0002J\b\u0010\"\u001a\u00020\u0012H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/mp3/util/PlaylistsImportRecurrenceExperience;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "numberOfRecurrences", "", "prefs", "Landroid/content/SharedPreferences;", "recurrenceEpocTime", "", "sessionClosed", "sessionEndTime", "sessionEnded", "sessionEpocTime", "sessionStartTime", "totalRecurrences", "bannerClosed", "", "canEndSession", "", "canStartNewRecurrence", "cancelledSession", "checkForRecurrenceLimit", "hasRecurrenceNotStarted", "isEligibleToExperience", "resetCancelledSession", "resetSessionEndTime", "resetSessionEnded", "resetSessionStartTime", "sessionClosedBefore", "sessionEndedBefore", "updateNumberOfRecurrence", "updateSessionEndTime", "updateSessionStartTime", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class PlaylistsImportRecurrenceExperience {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String experienceName = "import_playlists";
    private static volatile PlaylistsImportRecurrenceExperience instance;
    private String numberOfRecurrences;
    private final SharedPreferences prefs;
    private final int recurrenceEpocTime;
    private String sessionClosed;
    private String sessionEndTime;
    private String sessionEnded;
    private final int sessionEpocTime;
    private String sessionStartTime;
    private final int totalRecurrences;

    public /* synthetic */ PlaylistsImportRecurrenceExperience(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    private PlaylistsImportRecurrenceExperience(Context context) {
        this.recurrenceEpocTime = 72;
        this.totalRecurrences = 5;
        this.sessionEpocTime = 30;
        SharedPreferences prefs = SettingsUtil.getPrefs(context);
        Intrinsics.checkNotNullExpressionValue(prefs, "getPrefs(context)");
        this.prefs = prefs;
        String str = experienceName;
        this.numberOfRecurrences = Intrinsics.stringPlus(str, "_numberOfRecurrences");
        this.sessionEndTime = Intrinsics.stringPlus(str, "_sessionEndTime");
        this.sessionStartTime = Intrinsics.stringPlus(str, "_sessionStartTime");
        this.sessionClosed = Intrinsics.stringPlus(str, "_sessionClosed");
        this.sessionEnded = Intrinsics.stringPlus(str, "_sessionEnded");
    }

    /* JADX INFO: compiled from: PlaylistsImportRecurrenceExperience.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/util/PlaylistsImportRecurrenceExperience$Companion;", "", "()V", "experienceName", "", "instance", "Lcom/amazon/mp3/util/PlaylistsImportRecurrenceExperience;", "get", "context", "Landroid/content/Context;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final synchronized PlaylistsImportRecurrenceExperience get(Context context) {
            PlaylistsImportRecurrenceExperience playlistsImportRecurrenceExperience;
            Intrinsics.checkNotNullParameter(context, "context");
            if (PlaylistsImportRecurrenceExperience.instance == null) {
                PlaylistsImportRecurrenceExperience.instance = new PlaylistsImportRecurrenceExperience(context, null);
            }
            playlistsImportRecurrenceExperience = PlaylistsImportRecurrenceExperience.instance;
            if (playlistsImportRecurrenceExperience == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.util.PlaylistsImportRecurrenceExperience");
            }
            return playlistsImportRecurrenceExperience;
        }
    }

    private final boolean hasRecurrenceNotStarted() {
        return this.prefs.getInt(this.numberOfRecurrences, 0) == 0;
    }

    private final boolean checkForRecurrenceLimit() {
        return this.totalRecurrences >= this.prefs.getInt(this.numberOfRecurrences, 0);
    }

    private final boolean canStartNewRecurrence() {
        return this.prefs.getLong(this.sessionEndTime, 0L) != 0 && System.currentTimeMillis() - this.prefs.getLong(this.sessionEndTime, 0L) >= TimeUnit.HOURS.toMillis((long) this.recurrenceEpocTime) && checkForRecurrenceLimit();
    }

    private final boolean canEndSession() {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.prefs.getLong(this.sessionStartTime, 0L);
        long millis = TimeUnit.MINUTES.toMillis(this.sessionEpocTime);
        if (this.prefs.getLong(this.sessionStartTime, 0L) <= 0 || jCurrentTimeMillis < millis || !checkForRecurrenceLimit()) {
            return false;
        }
        sessionEnded();
        return true;
    }

    private final void updateNumberOfRecurrence() {
        int i = this.prefs.getInt(this.numberOfRecurrences, 0);
        SharedPreferences.Editor editorEdit = this.prefs.edit();
        editorEdit.putInt(this.numberOfRecurrences, i + 1);
        editorEdit.apply();
    }

    private final void updateSessionStartTime() {
        SharedPreferences.Editor editorEdit = this.prefs.edit();
        editorEdit.putLong(this.sessionStartTime, System.currentTimeMillis());
        editorEdit.apply();
    }

    private final void updateSessionEndTime() {
        SharedPreferences.Editor editorEdit = this.prefs.edit();
        editorEdit.putLong(this.sessionEndTime, System.currentTimeMillis());
        editorEdit.apply();
    }

    private final void resetSessionStartTime() {
        SharedPreferences.Editor editorEdit = this.prefs.edit();
        editorEdit.putLong(this.sessionStartTime, 0L);
        editorEdit.apply();
    }

    private final void resetSessionEndTime() {
        SharedPreferences.Editor editorEdit = this.prefs.edit();
        editorEdit.putLong(this.sessionEndTime, 0L);
        editorEdit.apply();
    }

    private final void cancelledSession() {
        SharedPreferences.Editor editorEdit = this.prefs.edit();
        editorEdit.putBoolean(this.sessionClosed, true);
        editorEdit.apply();
    }

    private final void resetCancelledSession() {
        SharedPreferences.Editor editorEdit = this.prefs.edit();
        editorEdit.putBoolean(this.sessionClosed, false);
        editorEdit.apply();
    }

    private final boolean sessionClosedBefore() {
        return this.prefs.getBoolean(this.sessionClosed, true);
    }

    private final boolean sessionEndedBefore() {
        return this.prefs.getBoolean(this.sessionEnded, true);
    }

    private final void sessionEnded() {
        SharedPreferences.Editor editorEdit = this.prefs.edit();
        editorEdit.putBoolean(this.sessionEnded, true);
        editorEdit.apply();
    }

    private final void resetSessionEnded() {
        SharedPreferences.Editor editorEdit = this.prefs.edit();
        editorEdit.putBoolean(this.sessionEnded, false);
        editorEdit.apply();
    }

    public final boolean isEligibleToExperience() {
        if (hasRecurrenceNotStarted()) {
            updateSessionStartTime();
            updateNumberOfRecurrence();
            resetCancelledSession();
            resetSessionEnded();
            return true;
        }
        if (canEndSession()) {
            updateSessionEndTime();
            resetSessionStartTime();
            updateNumberOfRecurrence();
            return false;
        }
        if (!sessionClosedBefore() && !sessionEndedBefore()) {
            return true;
        }
        if (!canStartNewRecurrence()) {
            return false;
        }
        updateSessionStartTime();
        resetSessionEndTime();
        resetCancelledSession();
        resetSessionEnded();
        return true;
    }

    public final void bannerClosed() {
        updateSessionEndTime();
        resetSessionStartTime();
        updateNumberOfRecurrence();
        cancelledSession();
    }
}
