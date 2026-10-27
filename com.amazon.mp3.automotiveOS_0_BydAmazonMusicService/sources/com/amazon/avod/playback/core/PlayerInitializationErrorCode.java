package com.amazon.avod.playback.core;

import com.amazon.avod.core.InitializationErrorCode;

/* JADX INFO: loaded from: classes2.dex */
public enum PlayerInitializationErrorCode implements InitializationErrorCode {
    NULL_PACKAGE_NAME,
    SERVER_CONFIG_FETCH_ERROR,
    NO_SUPPORTED_RENDERER_SCHEMES,
    DOWNLOAD_PERSISTENCE_NOT_READABLE
}
