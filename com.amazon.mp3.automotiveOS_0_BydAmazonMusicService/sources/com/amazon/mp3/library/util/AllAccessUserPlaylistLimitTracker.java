package com.amazon.mp3.library.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.DbUtil;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.eligibility.PlaylistLimitEligibility;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable;
import com.amazon.music.platform.providers.CapabilityActionProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: AllAccessUserPlaylistLimitTracker.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001FB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u0011H\u0007J\u0010\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0011H\u0007J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u0011H\u0002J\b\u0010\u001b\u001a\u00020\u0004H\u0002J\b\u0010\u001c\u001a\u00020\u0004H\u0002J\b\u0010\u001d\u001a\u00020\u0004H\u0002J\b\u0010\u001e\u001a\u00020\u0004H\u0002J\u0012\u0010\u001f\u001a\u00020\u00042\b\u0010 \u001a\u0004\u0018\u00010\u0011H\u0002J\u0010\u0010!\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0011H\u0002J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\u0016\u001a\u00020\u0011H\u0002J\u0010\u0010$\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0011H\u0002J\b\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020\bH\u0002J\u0010\u0010(\u001a\u00020\u00122\u0006\u0010)\u001a\u00020\u0004H\u0002J\"\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010+2\b\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010.\u001a\u00020\u0012H\u0002J\u0018\u0010/\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u00100\u001a\u00020\u0004H\u0007J\u0010\u00101\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u0011H\u0007J\u0010\u00102\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u0011H\u0007J\u0012\u00103\u001a\u0002042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0011H\u0007J\u0010\u00105\u001a\u0002042\b\u00106\u001a\u0004\u0018\u000107J\b\u00108\u001a\u000204H\u0002J\b\u00109\u001a\u00020\u001aH\u0002J\b\u0010:\u001a\u00020\u001aH\u0002J\u0010\u0010;\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u0011H\u0007J\u0010\u0010<\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u0011H\u0007J\u0010\u0010=\u001a\u00020\u001a2\u0006\u0010>\u001a\u00020\u0004H\u0002J\b\u0010?\u001a\u00020\u001aH\u0002J\b\u0010@\u001a\u00020\u001aH\u0002J\u0010\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020DH\u0002J\u0010\u0010E\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010j\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012`\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006G"}, d2 = {"Lcom/amazon/mp3/library/util/AllAccessUserPlaylistLimitTracker;", "", "()V", "MAX_NUMBER_CHANGES", "", "MAX_PLAYLIST_TRACK_COUNT", "MIN_PLAYLIST_TRACK_COUNT", "NO_MODIFICATIONS_MADE_TODAY", "", "PREFS_AAUP_ADDITIONS_KEY", "PREFS_AAUP_REMOVALS_KEY", "PREFS_AAUP_RESTRICTIONS_KEY", "PREFS_AAUP_TIME_KEY", "pendingRemoveChanges", "Ljava/util/concurrent/atomic/AtomicInteger;", "uriToLuidMap", "Ljava/util/HashMap;", "Landroid/net/Uri;", "", "Lkotlin/collections/HashMap;", "canAdd", "Lcom/amazon/mp3/library/util/AllAccessUserPlaylistLimitTracker$Response;", "playlistUri", "tracksUri", "canRemove", "checkPlaylistRestrictions", "", "getNumAdditions", "getNumRemovals", "getNumRestrictedChangesMade", "getNumRestrictedChangesRemaining", "getNumTracksFromUri", "uri", "getNumTracksInPlaylist", "getPlaylist", "Lcom/amazon/mp3/library/item/Playlist;", "getPlaylistLuid", "getPrefs", "Landroid/content/SharedPreferences;", "getRestrictionTimer", "getString", "key", "getStringsFromCursor", "", "c", "Landroid/database/Cursor;", "column", "incrementAddCounter", "numTracks", "incrementPendingRemoveChanges", "incrementRemoveCounter", "isAllAccessUserPlaylist", "", "isAllAccessUserPlaylistCompliant", "metadata", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "isPlaylistRestricted", "resetCounters", "resetCountersIfNeeded", "resetPendingChanges", "savePendingChanges", "setIncrementRemovalCounter", "numRemoves", "setPlaylistRestrictions", "showRestrictionsToast", "toCalendar", "Ljava/util/Calendar;", "date", "Ljava/util/Date;", "updateRestrictionsTimer", "Response", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AllAccessUserPlaylistLimitTracker {
    private static final long NO_MODIFICATIONS_MADE_TODAY = 0;
    public static final AllAccessUserPlaylistLimitTracker INSTANCE = new AllAccessUserPlaylistLimitTracker();
    private static final int MAX_NUMBER_CHANGES = 10;
    private static final int MAX_PLAYLIST_TRACK_COUNT = 50;
    private static final int MIN_PLAYLIST_TRACK_COUNT = 15;
    private static final int PREFS_AAUP_RESTRICTIONS_KEY = R.string.all_access_user_playlist_restrictions_key;
    private static final int PREFS_AAUP_ADDITIONS_KEY = R.string.all_access_user_playlist_additions_key;
    private static final int PREFS_AAUP_REMOVALS_KEY = R.string.all_access_user_playlist_removals_key;
    private static final int PREFS_AAUP_TIME_KEY = R.string.all_access_user_playlist_timer_key;
    private static final HashMap<Uri, String> uriToLuidMap = new HashMap<>();
    private static AtomicInteger pendingRemoveChanges = new AtomicInteger(0);

    /* JADX INFO: compiled from: AllAccessUserPlaylistLimitTracker.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/library/util/AllAccessUserPlaylistLimitTracker$Response;", "", "(Ljava/lang/String;I)V", "SUCCESS", "AAUP_PLAYLIST_TRACK_LIMIT", "AAUP_RESTRICTION_LIMIT", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum Response {
        SUCCESS,
        AAUP_PLAYLIST_TRACK_LIMIT,
        AAUP_RESTRICTION_LIMIT
    }

    private AllAccessUserPlaylistLimitTracker() {
    }

    @JvmStatic
    public static final boolean isAllAccessUserPlaylist(Uri playlistUri) {
        if (playlistUri != null && Feature.is_all_access_user_playlist_restrictions_enabled.isEnabled(true)) {
            return GetOnDemandPlaylistsService.INSTANCE.get().isOnDemandUserPlaylist(INSTANCE.getPlaylistLuid(playlistUri));
        }
        return false;
    }

    @JvmStatic
    public static final Response canRemove(Uri playlistUri) {
        Intrinsics.checkNotNullParameter(playlistUri, "playlistUri");
        if (!isAllAccessUserPlaylist(playlistUri)) {
            return Response.SUCCESS;
        }
        AllAccessUserPlaylistLimitTracker allAccessUserPlaylistLimitTracker = INSTANCE;
        allAccessUserPlaylistLimitTracker.checkPlaylistRestrictions(playlistUri);
        allAccessUserPlaylistLimitTracker.resetCountersIfNeeded();
        Playlist playlist = allAccessUserPlaylistLimitTracker.getPlaylist(playlistUri);
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(CapabilityActionProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof CapabilityActionProvider)) {
            interfaceProvider = null;
        }
        CapabilityActionProvider capabilityActionProvider = (CapabilityActionProvider) interfaceProvider;
        playlist.setPlaylistLimitEligibility(new PlaylistLimitEligibility(allAccessUserPlaylistLimitTracker.isPlaylistRestricted(), allAccessUserPlaylistLimitTracker.getNumTracksInPlaylist(playlistUri), null, 1, allAccessUserPlaylistLimitTracker.getNumRestrictedChangesRemaining()));
        Outcome outcomeCanPerform = capabilityActionProvider != null ? capabilityActionProvider.canPerform("AAUP_CAN_REMOVE", playlist) : null;
        if (outcomeCanPerform instanceof Outcome.Failure) {
            Throwable cause = ((Outcome.Failure) outcomeCanPerform).getCause();
            return cause instanceof EligibilityThrowable.AAUPChangeLimitsReached ? Response.AAUP_RESTRICTION_LIMIT : cause instanceof EligibilityThrowable.AAUPMaxPlaylistSize ? Response.AAUP_PLAYLIST_TRACK_LIMIT : Response.SUCCESS;
        }
        return Response.SUCCESS;
    }

    @JvmStatic
    public static final Response canAdd(Uri playlistUri, Uri tracksUri) {
        Intrinsics.checkNotNullParameter(playlistUri, "playlistUri");
        if (!isAllAccessUserPlaylist(playlistUri)) {
            return Response.SUCCESS;
        }
        AllAccessUserPlaylistLimitTracker allAccessUserPlaylistLimitTracker = INSTANCE;
        allAccessUserPlaylistLimitTracker.checkPlaylistRestrictions(playlistUri);
        allAccessUserPlaylistLimitTracker.resetCountersIfNeeded();
        Playlist playlist = allAccessUserPlaylistLimitTracker.getPlaylist(playlistUri);
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(CapabilityActionProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof CapabilityActionProvider)) {
            interfaceProvider = null;
        }
        CapabilityActionProvider capabilityActionProvider = (CapabilityActionProvider) interfaceProvider;
        playlist.setPlaylistLimitEligibility(new PlaylistLimitEligibility(allAccessUserPlaylistLimitTracker.isPlaylistRestricted(), allAccessUserPlaylistLimitTracker.getNumTracksInPlaylist(playlistUri), Integer.valueOf(allAccessUserPlaylistLimitTracker.getNumTracksFromUri(tracksUri)), null, allAccessUserPlaylistLimitTracker.getNumRestrictedChangesRemaining()));
        Outcome outcomeCanPerform = capabilityActionProvider != null ? capabilityActionProvider.canPerform("AAUP_CAN_ADD", playlist) : null;
        if (outcomeCanPerform instanceof Outcome.Failure) {
            Throwable cause = ((Outcome.Failure) outcomeCanPerform).getCause();
            return cause instanceof EligibilityThrowable.AAUPChangeLimitsReached ? Response.AAUP_RESTRICTION_LIMIT : cause instanceof EligibilityThrowable.AAUPMaxPlaylistSize ? Response.AAUP_PLAYLIST_TRACK_LIMIT : Response.SUCCESS;
        }
        return Response.SUCCESS;
    }

    @JvmStatic
    public static final void incrementAddCounter(Uri playlistUri, int numTracks) {
        Intrinsics.checkNotNullParameter(playlistUri, "playlistUri");
        if (isAllAccessUserPlaylist(playlistUri)) {
            AllAccessUserPlaylistLimitTracker allAccessUserPlaylistLimitTracker = INSTANCE;
            if (allAccessUserPlaylistLimitTracker.isPlaylistRestricted()) {
                SharedPreferences prefs = allAccessUserPlaylistLimitTracker.getPrefs();
                int numTracksInPlaylist = allAccessUserPlaylistLimitTracker.getNumTracksInPlaylist(playlistUri);
                int numRestrictedChangesRemaining = allAccessUserPlaylistLimitTracker.getNumRestrictedChangesRemaining();
                if (numTracksInPlaylist + numTracks > MIN_PLAYLIST_TRACK_COUNT || numRestrictedChangesRemaining != 0) {
                    int numAdditions = allAccessUserPlaylistLimitTracker.getNumAdditions();
                    SharedPreferences.Editor editorEdit = prefs.edit();
                    editorEdit.putInt(allAccessUserPlaylistLimitTracker.getString(PREFS_AAUP_ADDITIONS_KEY), numAdditions + numTracks);
                    editorEdit.apply();
                    allAccessUserPlaylistLimitTracker.updateRestrictionsTimer(playlistUri);
                    allAccessUserPlaylistLimitTracker.showRestrictionsToast();
                }
            }
        }
    }

    @JvmStatic
    public static final void incrementRemoveCounter(Uri playlistUri) {
        Intrinsics.checkNotNullParameter(playlistUri, "playlistUri");
        if (isAllAccessUserPlaylist(playlistUri)) {
            AllAccessUserPlaylistLimitTracker allAccessUserPlaylistLimitTracker = INSTANCE;
            if (allAccessUserPlaylistLimitTracker.isPlaylistRestricted() && allAccessUserPlaylistLimitTracker.getNumTracksInPlaylist(playlistUri) <= MAX_PLAYLIST_TRACK_COUNT) {
                allAccessUserPlaylistLimitTracker.setIncrementRemovalCounter(1);
                allAccessUserPlaylistLimitTracker.updateRestrictionsTimer(playlistUri);
                allAccessUserPlaylistLimitTracker.showRestrictionsToast();
            }
        }
    }

    @JvmStatic
    public static final void incrementPendingRemoveChanges(Uri playlistUri) {
        Intrinsics.checkNotNullParameter(playlistUri, "playlistUri");
        if (isAllAccessUserPlaylist(playlistUri)) {
            AllAccessUserPlaylistLimitTracker allAccessUserPlaylistLimitTracker = INSTANCE;
            if (allAccessUserPlaylistLimitTracker.isPlaylistRestricted() && allAccessUserPlaylistLimitTracker.getNumTracksInPlaylist(playlistUri) <= MAX_PLAYLIST_TRACK_COUNT) {
                pendingRemoveChanges.incrementAndGet();
            }
        }
    }

    @JvmStatic
    public static final void savePendingChanges(Uri playlistUri) {
        Intrinsics.checkNotNullParameter(playlistUri, "playlistUri");
        if (isAllAccessUserPlaylist(playlistUri)) {
            AllAccessUserPlaylistLimitTracker allAccessUserPlaylistLimitTracker = INSTANCE;
            if (allAccessUserPlaylistLimitTracker.isPlaylistRestricted()) {
                allAccessUserPlaylistLimitTracker.setIncrementRemovalCounter(pendingRemoveChanges.get());
                pendingRemoveChanges.set(0);
                allAccessUserPlaylistLimitTracker.updateRestrictionsTimer(playlistUri);
                allAccessUserPlaylistLimitTracker.showRestrictionsToast();
            }
        }
    }

    @JvmStatic
    public static final void resetPendingChanges(Uri playlistUri) {
        Intrinsics.checkNotNullParameter(playlistUri, "playlistUri");
        if (isAllAccessUserPlaylist(playlistUri) && INSTANCE.isPlaylistRestricted()) {
            pendingRemoveChanges.set(0);
        }
    }

    private final void resetCountersIfNeeded() {
        if (isPlaylistRestricted()) {
            Calendar calendar = Calendar.getInstance();
            int i = calendar.get(1);
            int i2 = calendar.get(6);
            Calendar calendar2 = toCalendar(new Date(getRestrictionTimer()));
            int i3 = calendar2.get(1);
            int i4 = calendar2.get(6);
            if (i == i3 && i2 == i4) {
                return;
            }
            resetCounters();
        }
    }

    private final void resetCounters() {
        SharedPreferences.Editor editorEdit = getPrefs().edit();
        editorEdit.putInt(getString(PREFS_AAUP_ADDITIONS_KEY), 0);
        editorEdit.putInt(getString(PREFS_AAUP_REMOVALS_KEY), 0);
        editorEdit.putLong(getString(PREFS_AAUP_TIME_KEY), 0L);
        editorEdit.apply();
    }

    private final int getNumTracksFromUri(Uri uri) {
        if (uri == null || MediaProvider.Tracks.isPrimeAsinLuidTrack(uri) || MediaProvider.Tracks.isPrimeAdditionalTrack(uri)) {
            return 1;
        }
        List<String> stringsFromCursor = getStringsFromCursor(AmazonApplication.getApplication().getApplicationContext().getContentResolver().query(uri, new String[]{"asin"}, DeluxeContentUtil.getNonDeluxeSelection(), DeluxeContentUtil.getNonDeluxeSelectionArgs(), null), "asin");
        if (stringsFromCursor == null) {
            return 1;
        }
        return stringsFromCursor.size();
    }

    private final int getNumRestrictedChangesRemaining() {
        return MAX_NUMBER_CHANGES - (getNumRestrictedChangesMade() + pendingRemoveChanges.get());
    }

    private final int getNumRestrictedChangesMade() {
        return getNumAdditions() + getNumRemovals();
    }

    private final int getNumTracksInPlaylist(Uri playlistUri) {
        return PlaylistUtil.getPlaylistCount(AmazonApplication.getApplication().getApplicationContext(), MediaProvider.Playlists.extractPlaylistUri(playlistUri));
    }

    public final boolean isAllAccessUserPlaylistCompliant(PlaylistMetadata metadata) {
        String luid;
        if (metadata == null || (luid = metadata.getLuid()) == null) {
            return false;
        }
        if (!GetOnDemandPlaylistsService.INSTANCE.get().isOnDemandUserPlaylist(luid)) {
            return true;
        }
        Integer trackCount = metadata.getTrackCount();
        if ((trackCount == null ? 0 : trackCount.intValue()) >= MIN_PLAYLIST_TRACK_COUNT) {
            Integer trackCount2 = metadata.getTrackCount();
            if ((trackCount2 == null ? 0 : trackCount2.intValue()) <= MAX_PLAYLIST_TRACK_COUNT) {
                return true;
            }
        }
        return false;
    }

    private final void checkPlaylistRestrictions(Uri playlistUri) {
        if (!isPlaylistRestricted() && getNumTracksInPlaylist(playlistUri) >= MAX_PLAYLIST_TRACK_COUNT) {
            setPlaylistRestrictions();
        }
    }

    private final Playlist getPlaylist(Uri playlistUri) {
        Playlist playlist = AmazonApplication.getLibraryItemFactory().getPlaylist(MediaProvider.Playlists.extractPlaylistUri(playlistUri));
        Intrinsics.checkNotNullExpressionValue(playlist, "getLibraryItemFactory().…PlaylistUri(playlistUri))");
        return playlist;
    }

    private final String getPlaylistLuid(Uri playlistUri) {
        HashMap<Uri, String> map = uriToLuidMap;
        String str = map.get(playlistUri);
        if (str != null) {
            return str;
        }
        String playlistLuid = MediaProvider.Playlists.getPlaylistLuid(playlistUri);
        map.put(playlistUri, playlistLuid);
        Intrinsics.checkNotNullExpressionValue(playlistLuid, "playlistLuid");
        return playlistLuid;
    }

    private final SharedPreferences getPrefs() {
        SharedPreferences prefs = SettingsUtil.getPrefs(AmazonApplication.getApplication().getApplicationContext());
        Intrinsics.checkNotNullExpressionValue(prefs, "getPrefs(AmazonApplicati…ion().applicationContext)");
        return prefs;
    }

    private final List<String> getStringsFromCursor(Cursor c, String column) {
        if (c == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            int columnIndex = c.getColumnIndex(column);
            while (c.moveToNext()) {
                String string = c.getString(columnIndex);
                Intrinsics.checkNotNullExpressionValue(string, "c.getString(idColumn)");
                arrayList.add(string);
            }
            return arrayList;
        } finally {
            DbUtil.closeCursor(c);
        }
    }

    private final void updateRestrictionsTimer(Uri playlistUri) {
        SharedPreferences prefs = getPrefs();
        if (getRestrictionTimer() == NO_MODIFICATIONS_MADE_TODAY) {
            long time = Calendar.getInstance().getTime().getTime();
            SharedPreferences.Editor editorEdit = prefs.edit();
            editorEdit.putLong(getString(PREFS_AAUP_TIME_KEY), time);
            editorEdit.apply();
        }
    }

    private final void setPlaylistRestrictions() {
        SharedPreferences.Editor editorEdit = getPrefs().edit();
        editorEdit.putBoolean(getString(PREFS_AAUP_RESTRICTIONS_KEY), true);
        editorEdit.apply();
    }

    private final void setIncrementRemovalCounter(int numRemoves) {
        int numRemovals = getNumRemovals();
        SharedPreferences.Editor editorEdit = getPrefs().edit();
        editorEdit.putInt(getString(PREFS_AAUP_REMOVALS_KEY), numRemovals + numRemoves);
        editorEdit.apply();
    }

    private final boolean isPlaylistRestricted() {
        return getPrefs().getBoolean(getString(PREFS_AAUP_RESTRICTIONS_KEY), false);
    }

    private final int getNumAdditions() {
        return getPrefs().getInt(getString(PREFS_AAUP_ADDITIONS_KEY), 0);
    }

    private final int getNumRemovals() {
        return getPrefs().getInt(getString(PREFS_AAUP_REMOVALS_KEY), 0);
    }

    private final long getRestrictionTimer() {
        return getPrefs().getLong(getString(PREFS_AAUP_TIME_KEY), 0L);
    }

    private final Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return calendar;
    }

    private final String getString(int key) {
        String string = AmazonApplication.getApplication().getApplicationContext().getString(key);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(key)");
        return string;
    }

    private final void showRestrictionsToast() {
        String string;
        Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
        int numRestrictedChangesMade = getNumRestrictedChangesMade();
        int i = MAX_NUMBER_CHANGES;
        if (numRestrictedChangesMade >= i) {
            string = applicationContext.getString(R.string.all_access_user_playlist_restriction_toast_limit_reached, Integer.valueOf(i));
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…ched, MAX_NUMBER_CHANGES)");
        } else if (numRestrictedChangesMade == i - 1) {
            string = applicationContext.getString(R.string.all_access_user_playlist_restriction_toast_one_remaining);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…tion_toast_one_remaining)");
        } else {
            string = applicationContext.getString(R.string.all_access_user_playlist_restriction_toast, Integer.valueOf(numRestrictedChangesMade), Integer.valueOf(i));
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…nges, MAX_NUMBER_CHANGES)");
        }
        applicationContext.getMainLooper();
        BauhausToastUtils.showTextToast(applicationContext, string, 0);
    }
}
