package com.amazon.music.voice.playbackstate;

import com.amazon.identity.auth.device.utils.AccountConstants;
import org.apache.commons.lang3.Validate;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class MediaMetadata {
    private String album;
    private String artist;
    private long durationInMilliseconds;
    private final MediaOperationSupported mediaOperationSupportedImpl;
    private String mediaType;
    private String trackId;
    private String trackName;
    private int trackNumber;

    private int validate(int i) {
        if (i < 0) {
            return 0;
        }
        return i;
    }

    private long validate(long j) {
        if (j < 0) {
            return 0L;
        }
        return j;
    }

    private String validate(String str) {
        return str == null ? "" : str;
    }

    public MediaMetadata(String str, String str2, MediaOperationSupported mediaOperationSupported, long j, String str3, String str4, int i, String str5) {
        this.mediaType = "TRACK";
        Validate.notNull(str2);
        this.artist = validate(str);
        this.mediaType = str2;
        this.mediaOperationSupportedImpl = mediaOperationSupported;
        this.durationInMilliseconds = validate(j);
        this.album = validate(str3);
        this.trackName = validate(str4);
        this.trackNumber = validate(i);
        this.trackId = validate(str5);
    }

    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "ExternalMediaPlayerMusicItem");
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("artist", this.artist);
        jSONObject2.put("mediaType", this.mediaType);
        jSONObject2.put("durationInMilliseconds", this.durationInMilliseconds);
        jSONObject2.put("album", this.album);
        jSONObject2.put("trackName", this.trackName);
        jSONObject2.put("trackNumber", this.trackNumber);
        jSONObject2.put("trackId", this.trackId);
        jSONObject.put("value", jSONObject2);
        return jSONObject;
    }

    public boolean isMediaOperationSupported(MediaOperation mediaOperation) {
        return this.mediaOperationSupportedImpl.isSupported(mediaOperation);
    }
}
