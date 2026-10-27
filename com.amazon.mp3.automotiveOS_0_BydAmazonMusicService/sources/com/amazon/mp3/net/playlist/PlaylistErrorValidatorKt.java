package com.amazon.mp3.net.playlist;

import com.amazon.identity.auth.device.api.MAPWebViewEventHelper;
import com.amazon.mp3.net.playlist.MusicPlaylistExceptions;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: PlaylistErrorValidator.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¨\u0006\u0005"}, d2 = {"Lorg/json/JSONObject;", "", "playlistKey", "", "validatePlaylistErrors", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0})
public final class PlaylistErrorValidatorKt {
    public static final void validatePlaylistErrors(JSONObject jSONObject, String playlistKey) throws MusicPlaylistExceptions.PlaylistServiceException {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Intrinsics.checkNotNullParameter(playlistKey, "playlistKey");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(MAPWebViewEventHelper.KEY_ERRORS);
        JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject == null ? null : jSONObjectOptJSONObject.optJSONObject(playlistKey);
        if (jSONObjectOptJSONObject2 == null) {
            return;
        }
        MusicPlaylistExceptions.PlaylistServiceException mappedException = MusicPlaylistExceptions.getMappedException(jSONObjectOptJSONObject2.optString("__type"), jSONObjectOptJSONObject2.optString("message"));
        Intrinsics.checkNotNullExpressionValue(mappedException, "getMappedException(error…        errorMessageKey))");
        throw mappedException;
    }
}
