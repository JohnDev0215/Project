package com.amazon.music.platform.aom;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CapabilityRegistry.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\b\u0002\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000e\u0010\u000fJ\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003J\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u0007R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001d¨\u0006\u001e"}, d2 = {"com/amazon/music/platform/aom/CapabilityRegistry$Interface", "", "Lcom/amazon/music/platform/aom/CapabilityRegistry$Interface;", "Lcom/amazon/music/platform/aom/CapabilityRegistry$Version;", "version", "Lcom/amazon/music/platform/aom/Capability;", JsonPOJOBuilder.DEFAULT_WITH_PREFIX, "Lcom/amazon/music/platform/aom/Configuration;", "configuration", "", "interfaceName", "Ljava/lang/String;", "getInterfaceName", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "SYSTEM", "AUDIOPLAYER", "SPEECHRECOGNIZER", "PLAYBACKCONTROLLER", "SETTINGS", "SPEECHSYNTHESIZER", "LAUNCHER", "EXTERNALMEDIAPLAYER", "ALEXA_PLAYBACK_CONTROLLER", "ALEXA_PLAYLIST_CONTROLLER", "ALEXA_FAVORITES_CONTROLLER", "ALEXA_PLAYBACK_STATE_REPORTER", "ALEXA_SEEK_CONTROLLER", "ALEXA_INPUT_TEXT", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1})
public enum CapabilityRegistry$Interface {
    SYSTEM("System"),
    AUDIOPLAYER("AudioPlayer"),
    SPEECHRECOGNIZER("SpeechRecognizer"),
    PLAYBACKCONTROLLER("PlaybackController"),
    SETTINGS("Settings"),
    SPEECHSYNTHESIZER("SpeechSynthesizer"),
    LAUNCHER("Alexa.Launcher"),
    EXTERNALMEDIAPLAYER("ExternalMediaPlayer"),
    ALEXA_PLAYBACK_CONTROLLER("Alexa.PlaybackController"),
    ALEXA_PLAYLIST_CONTROLLER("Alexa.PlaylistController"),
    ALEXA_FAVORITES_CONTROLLER("Alexa.FavoritesController"),
    ALEXA_PLAYBACK_STATE_REPORTER("Alexa.PlaybackStateReporter"),
    ALEXA_SEEK_CONTROLLER("Alexa.SeekController"),
    ALEXA_INPUT_TEXT("Alexa.Input.Text");

    private final String interfaceName;

    CapabilityRegistry$Interface(String str) {
        this.interfaceName = str;
    }

    public final String getInterfaceName() {
        return this.interfaceName;
    }

    public final Capability with(CapabilityRegistry$Version version) {
        Intrinsics.checkNotNullParameter(version, "version");
        return Capability.INSTANCE.create(this.interfaceName, "AlexaInterface", version.getValue());
    }

    public final Capability with(CapabilityRegistry$Version version, Configuration configuration) {
        Intrinsics.checkNotNullParameter(version, "version");
        return Capability.INSTANCE.create(this.interfaceName, "AlexaInterface", version.getValue(), configuration);
    }
}
