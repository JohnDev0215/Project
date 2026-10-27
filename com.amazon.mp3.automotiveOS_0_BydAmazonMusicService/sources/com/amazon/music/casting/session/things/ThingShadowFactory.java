package com.amazon.music.casting.session.things;

import android.os.Handler;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.session.CastingPlaybackCallback;
import com.amazon.music.casting.session.CastingSessionConfig;
import com.amazon.music.casting.session.CastingSessionTimer;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttManager;

/* JADX INFO: loaded from: classes4.dex */
public class ThingShadowFactory {
    private static final String DOCUMENTS_TOPIC = "$aws/things/%s/shadow/update/documents";
    private static final String GET_ACCEPTED_TOPIC = "$aws/things/%s/shadow/get/accepted";
    private static final String GET_REJECTED_TOPIC = "$aws/things/%s/shadow/get/rejected";
    private static final String GET_TOPIC = "$aws/things/%s/shadow/get";
    private static final String LAST_WILL_TOPIC = "lwt/%s";
    public static final String THING_SHADOW_ERROR_CODE_404 = "404";
    public static final int THING_SHADOW_SUBSCRIBE_PUBLISH_DELAY = 200;
    private static final String UPDATE_ACCEPTED_TOPIC = "$aws/things/%s/shadow/update/accepted";
    private static final String UPDATE_REJECTED_TOPIC = "$aws/things/%s/shadow/update/rejected";
    private static final String UPDATE_TOPIC = "$aws/things/%s/shadow/update";

    public static ClientThingShadowMessenger createClientThingShadowMessenger(AWSIotMqttManager aWSIotMqttManager, String str, CastingDevice castingDevice, Handler handler, CastingSessionConfig castingSessionConfig) {
        return new ClientThingShadowMessenger(aWSIotMqttManager, str, castingDevice, handler, castingSessionConfig);
    }

    public static NowPlayingThingShadowMessenger createNowPlayingThingShadowMessenger(AWSIotMqttManager aWSIotMqttManager, String str, Handler handler, CastingPlaybackCallback castingPlaybackCallback, CastingSessionConfig castingSessionConfig, CastingSessionTimer.CastingSessionTimerListener castingSessionTimerListener) {
        return new NowPlayingThingShadowMessenger(aWSIotMqttManager, str, handler, castingPlaybackCallback, castingSessionConfig, castingSessionTimerListener);
    }

    public static MetadataThingShadowMessenger createMetadataThingShadowMessenger(AWSIotMqttManager aWSIotMqttManager, String str, Handler handler, CastingPlaybackCallback castingPlaybackCallback, NowPlayingThingShadowMessenger nowPlayingThingShadowMessenger, CastingSessionConfig castingSessionConfig) {
        return new MetadataThingShadowMessenger(aWSIotMqttManager, str, handler, castingPlaybackCallback, nowPlayingThingShadowMessenger, castingSessionConfig);
    }

    public static String getDocumentsTopic(String str) {
        return String.format(DOCUMENTS_TOPIC, str);
    }

    public static String getGetTopic(String str) {
        return String.format(GET_TOPIC, str);
    }

    public static String getGetAcceptedTopic(String str) {
        return String.format(GET_ACCEPTED_TOPIC, str);
    }

    public static String getGetRejectedTopic(String str) {
        return String.format(GET_REJECTED_TOPIC, str);
    }

    public static String getUpdateTopic(String str) {
        return String.format(UPDATE_TOPIC, str);
    }

    public static String getUpdateAcceptedTopic(String str) {
        return String.format(UPDATE_ACCEPTED_TOPIC, str);
    }

    public static String getUpdateRejectedTopic(String str) {
        return String.format(UPDATE_REJECTED_TOPIC, str);
    }

    public static String getLastWillTopic(String str) {
        return String.format(LAST_WILL_TOPIC, str);
    }
}
