package com.amazon.avod.playbackresource;

import com.amazon.atvplaybackresource.Caching;
import com.amazon.atvplaybackresource.Callback;
import com.amazon.atvplaybackresource.PlaybackSettings;
import com.amazon.avod.dash.config.miyagi.HeuristicsConfigStore;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.util.DLog;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackSettingsTransformer {
    private static final String DELAY_MILLIS_KEY_NAME_FULL;
    private static final String TIME_TO_LIVE_MILLIS_KEY_NAME_FULL;
    private static final String URL_KEY_NAME_FULL;
    private final PlaybackEventReporter mPlaybackEventReporter;

    static {
        Locale locale = Locale.US;
        TIME_TO_LIVE_MILLIS_KEY_NAME_FULL = String.format(locale, "%s_%s", "caching", "timeToLiveMillis");
        DELAY_MILLIS_KEY_NAME_FULL = String.format(locale, "%s_%s", "callback", "delayMillis");
        URL_KEY_NAME_FULL = String.format(locale, "%s_%s", "callback", ImagesContract.URL);
    }

    public PlaybackSettingsTransformer(@Nullable PlaybackEventReporter playbackEventReporter) {
        this.mPlaybackEventReporter = playbackEventReporter;
    }

    public PlaybackSettingsTransformer() {
        this(null);
    }

    @Nonnull
    public Map<String, String> transform(@Nullable PlaybackSettings playbackSettings) {
        if (playbackSettings == null) {
            DLog.errorf("PlaybackSettings is null");
            reportSettingsError("PlaybackSettings is null");
            return Collections.emptyMap();
        }
        return transform(playbackSettings.settingsId, playbackSettings.caching, playbackSettings.callback, playbackSettings.playbackSettings);
    }

    @Nonnull
    public Map<String, String> transform(@Nullable com.amazon.avod.playbackresourcev2.PlaybackSettings playbackSettings) {
        if (playbackSettings == null) {
            String str = String.format(Locale.US, "PlaybackSettings is null", new Object[0]);
            DLog.errorf(str);
            reportSettingsError(str);
            return Collections.emptyMap();
        }
        return transform(playbackSettings.getSettingsId(), playbackSettings.getCaching(), playbackSettings.getCallback(), playbackSettings.getPlaybackSettings());
    }

    private Map<String, String> transform(@Nonnull Optional<String> optional, @Nonnull Optional<Caching> optional2, @Nonnull Optional<Callback> optional3, @Nonnull Optional<ByteBuffer> optional4) {
        try {
            ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
            getCommonValues(optional, optional2, optional3, builder);
            getBaseConfiguration(optional4, builder);
            return builder.build();
        } catch (IllegalArgumentException | IllegalStateException | JSONException e) {
            String str = String.format(Locale.US, "Unable to transform settingsResponse to PlaybackSettings | error: %s", e.getMessage());
            DLog.exceptionf(e, str, new Object[0]);
            reportSettingsError(str);
            return Collections.emptyMap();
        }
    }

    private void getCommonValues(@Nonnull Optional<String> optional, @Nonnull Optional<Caching> optional2, @Nonnull Optional<Callback> optional3, @Nonnull ImmutableMap.Builder<String, String> builder) {
        builder.put(HeuristicsConfigStore.SETTINGS_ID_KEY, optional.get());
        builder.put(TIME_TO_LIVE_MILLIS_KEY_NAME_FULL, String.valueOf(optional2.get().timeToLiveMillis.or(0L)));
        builder.put(DELAY_MILLIS_KEY_NAME_FULL, String.valueOf(optional3.get().delayMillis.or(0L)));
        builder.put(URL_KEY_NAME_FULL, optional3.get().url.get());
    }

    private void getBaseConfiguration(@Nonnull Optional<ByteBuffer> optional, @Nonnull ImmutableMap.Builder<String, String> builder) throws JSONException, IllegalArgumentException {
        ByteBuffer byteBuffer = optional.get();
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        byteBuffer.rewind();
        JSONObject jSONObject = new JSONObject(new String(bArr, StandardCharsets.UTF_8)).getJSONObject("base");
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String string = itKeys.next().toString();
            JSONObject jSONObject2 = jSONObject.getJSONObject(string);
            if (string.equals("playbackSettingsMap")) {
                getPlaybackSettingsMapConfigurations(string, jSONObject2, builder);
            } else {
                Iterator<String> itKeys2 = jSONObject2.keys();
                while (itKeys2.hasNext()) {
                    String string2 = itKeys2.next().toString();
                    builder.put(String.format(Locale.US, "%s_%s", string, string2), jSONObject2.getString(string2));
                }
            }
        }
    }

    private void getPlaybackSettingsMapConfigurations(String str, JSONObject jSONObject, @Nonnull ImmutableMap.Builder<String, String> builder) throws JSONException {
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String string = itKeys.next().toString();
            JSONObject jSONObject2 = jSONObject.getJSONObject(string);
            Iterator<String> itKeys2 = jSONObject2.keys();
            while (itKeys2.hasNext()) {
                String string2 = itKeys2.next().toString();
                JSONObject jSONObject3 = jSONObject2.getJSONObject(string2);
                Iterator<String> itKeys3 = jSONObject3.keys();
                while (itKeys3.hasNext()) {
                    String string3 = itKeys3.next().toString();
                    builder.put(String.format(Locale.US, "%s_%s_%s_%s", str, string, string2, string3), jSONObject3.getString(string3));
                }
            }
        }
    }

    private void reportSettingsError(@Nonnull String str) {
        Preconditions.checkNotNull(str, "errorMessage");
        PlaybackEventReporter playbackEventReporter = this.mPlaybackEventReporter;
        if (playbackEventReporter != null) {
            playbackEventReporter.reportError("SettingsParseError", str, null);
        }
    }
}
