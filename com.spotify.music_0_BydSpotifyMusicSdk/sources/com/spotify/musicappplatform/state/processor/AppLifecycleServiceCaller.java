package com.spotify.musicappplatform.state.processor;

import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
@Keep
public enum AppLifecycleServiceCaller {
    IDLE_SHUTDOWN_TIME,
    ACTION_URL_OPEN,
    MEDIA_BUTTON_FOREGROUND,
    MEDIA_BUTTON_BACKGROUND,
    PREPARE_ALARM,
    CLIENT_WANTS_SERVICE_TO_DIE,
    REQUEST_WIDGET_UPDATE,
    WIDGET_PLAYER_SKIP_PREV,
    WIDGET_PLAYER_PAUSE,
    WIDGET_PLAYER_RESUME,
    WIDGET_PLAYER_SKIP_NEXT,
    START_SERVICE,
    STOP_SERVICE
}
