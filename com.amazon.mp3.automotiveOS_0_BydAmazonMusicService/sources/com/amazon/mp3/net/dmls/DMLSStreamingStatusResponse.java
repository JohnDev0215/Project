package com.amazon.mp3.net.dmls;

import com.amazon.mp3.net.dmls.DMLSExceptions;
import com.amazon.mp3.util.Log;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: DMLSStreamingStatusResponse.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\b\u0018\u0000 &2\u00020\u0001:\u0001&B;\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018\u0012\u0006\u0010\u001e\u001a\u00020\u001d\u0012\u0006\u0010\"\u001a\u00020\n¢\u0006\u0004\b$\u0010%J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001e\u001a\u00020\u001d8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u0017\u0010\"\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\"\u0010\f\u001a\u0004\b#\u0010\u000e¨\u0006'"}, d2 = {"Lcom/amazon/mp3/net/dmls/DMLSStreamingStatusResponse;", "Ljava/io/Serializable;", "", "toString", "", "hashCode", "", "other", "", "equals", "", "elapsedTimeSeconds", "J", "getElapsedTimeSeconds", "()J", "remoteDeviceName", "Ljava/lang/String;", "getRemoteDeviceName", "()Ljava/lang/String;", "Lcom/amazon/mp3/net/dmls/ContentId;", "contentId", "Lcom/amazon/mp3/net/dmls/ContentId;", "getContentId", "()Lcom/amazon/mp3/net/dmls/ContentId;", "Lcom/amazon/mp3/net/dmls/DeviceToken;", "deviceToken", "Lcom/amazon/mp3/net/dmls/DeviceToken;", "getDeviceToken", "()Lcom/amazon/mp3/net/dmls/DeviceToken;", "Lcom/amazon/mp3/net/dmls/StreamingStatus;", "streamingStatus", "Lcom/amazon/mp3/net/dmls/StreamingStatus;", "getStreamingStatus", "()Lcom/amazon/mp3/net/dmls/StreamingStatus;", "currentPlayTime", "getCurrentPlayTime", "<init>", "(JLjava/lang/String;Lcom/amazon/mp3/net/dmls/ContentId;Lcom/amazon/mp3/net/dmls/DeviceToken;Lcom/amazon/mp3/net/dmls/StreamingStatus;J)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class DMLSStreamingStatusResponse implements Serializable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = DMLSStreamingStatusResponse.class.getSimpleName();
    private final ContentId contentId;
    private final long currentPlayTime;
    private final DeviceToken deviceToken;
    private final long elapsedTimeSeconds;
    private final String remoteDeviceName;
    private final StreamingStatus streamingStatus;

    @JvmStatic
    public static final DMLSStreamingStatusResponse createFromJSONObject(JSONObject jSONObject) {
        return INSTANCE.createFromJSONObject(jSONObject);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DMLSStreamingStatusResponse)) {
            return false;
        }
        DMLSStreamingStatusResponse dMLSStreamingStatusResponse = (DMLSStreamingStatusResponse) other;
        return this.elapsedTimeSeconds == dMLSStreamingStatusResponse.elapsedTimeSeconds && Intrinsics.areEqual(this.remoteDeviceName, dMLSStreamingStatusResponse.remoteDeviceName) && Intrinsics.areEqual(this.contentId, dMLSStreamingStatusResponse.contentId) && Intrinsics.areEqual(this.deviceToken, dMLSStreamingStatusResponse.deviceToken) && this.streamingStatus == dMLSStreamingStatusResponse.streamingStatus && this.currentPlayTime == dMLSStreamingStatusResponse.currentPlayTime;
    }

    public int hashCode() {
        int iHashCode = ((Long.hashCode(this.elapsedTimeSeconds) * 31) + this.remoteDeviceName.hashCode()) * 31;
        ContentId contentId = this.contentId;
        int iHashCode2 = (iHashCode + (contentId == null ? 0 : contentId.hashCode())) * 31;
        DeviceToken deviceToken = this.deviceToken;
        return ((((iHashCode2 + (deviceToken != null ? deviceToken.hashCode() : 0)) * 31) + this.streamingStatus.hashCode()) * 31) + Long.hashCode(this.currentPlayTime);
    }

    public String toString() {
        return "DMLSStreamingStatusResponse(elapsedTimeSeconds=" + this.elapsedTimeSeconds + ", remoteDeviceName=" + this.remoteDeviceName + ", contentId=" + this.contentId + ", deviceToken=" + this.deviceToken + ", streamingStatus=" + this.streamingStatus + ", currentPlayTime=" + this.currentPlayTime + ')';
    }

    public DMLSStreamingStatusResponse(long j, String remoteDeviceName, ContentId contentId, DeviceToken deviceToken, StreamingStatus streamingStatus, long j2) {
        Intrinsics.checkNotNullParameter(remoteDeviceName, "remoteDeviceName");
        Intrinsics.checkNotNullParameter(streamingStatus, "streamingStatus");
        this.elapsedTimeSeconds = j;
        this.remoteDeviceName = remoteDeviceName;
        this.contentId = contentId;
        this.deviceToken = deviceToken;
        this.streamingStatus = streamingStatus;
        this.currentPlayTime = j2;
    }

    public final long getElapsedTimeSeconds() {
        return this.elapsedTimeSeconds;
    }

    public final String getRemoteDeviceName() {
        return this.remoteDeviceName;
    }

    public final DeviceToken getDeviceToken() {
        return this.deviceToken;
    }

    public final StreamingStatus getStreamingStatus() {
        return this.streamingStatus;
    }

    /* JADX INFO: compiled from: DMLSStreamingStatusResponse.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0019\u0010\r\u001a\n \u000e*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/net/dmls/DMLSStreamingStatusResponse$Companion;", "", "()V", "FIELD_CONTENT_ID", "", "FIELD_CURRENT_PLAY_TIME", "FIELD_DEVICE_ID", "FIELD_DEVICE_NAME", "FIELD_DEVICE_TOKEN", "FIELD_DEVICE_TYPE_ID", "FIELD_ELAPSED_TIME", "FIELD_IDENTIFIER", "FIELD_IDENTIFIER_TYPE", "TAG", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "createFromJSONObject", "Lcom/amazon/mp3/net/dmls/DMLSStreamingStatusResponse;", "jsonObject", "Lorg/json/JSONObject;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return DMLSStreamingStatusResponse.TAG;
        }

        @JvmStatic
        public final DMLSStreamingStatusResponse createFromJSONObject(JSONObject jsonObject) {
            Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
            try {
                long jOptLong = jsonObject.optLong("elapsedTime", 0L);
                long jOptLong2 = jsonObject.optLong("currentPlayTime", 0L);
                String remoteDeviceName = jsonObject.optString("deviceName");
                JSONObject jSONObjectOptJSONObject = jsonObject.optJSONObject("deviceToken");
                String strOptString = jSONObjectOptJSONObject.optString(Splash.PARAMS_DEVICE_ID);
                Intrinsics.checkNotNullExpressionValue(strOptString, "deviceToken.optString(FIELD_DEVICE_ID)");
                String strOptString2 = jSONObjectOptJSONObject.optString("deviceTypeId");
                Intrinsics.checkNotNullExpressionValue(strOptString2, "deviceToken.optString(FIELD_DEVICE_TYPE_ID)");
                DeviceToken deviceToken = new DeviceToken(strOptString, strOptString2);
                JSONObject jSONObjectOptJSONObject2 = jsonObject.optJSONObject("contentID");
                ContentId contentId = new ContentId(IdentifierType.fromString(jSONObjectOptJSONObject2.optString("identifierType")), jSONObjectOptJSONObject2.optString("identifier"));
                Intrinsics.checkNotNullExpressionValue(remoteDeviceName, "remoteDeviceName");
                return new DMLSStreamingStatusResponse(jOptLong, remoteDeviceName, contentId, deviceToken, StreamingStatus.PLAY, jOptLong2);
            } catch (DMLSExceptions.InvalidContentIdException e) {
                Log.error(getTAG(), "Unable to update Streaming Status due to invalid ContentId", e);
                return new DMLSStreamingStatusResponse(0L, "", null, null, StreamingStatus.UNDEFINED, 0L);
            }
        }
    }
}
