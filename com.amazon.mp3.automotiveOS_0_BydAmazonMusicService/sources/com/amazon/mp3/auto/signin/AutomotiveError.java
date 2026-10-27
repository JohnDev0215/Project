package com.amazon.mp3.auto.signin;

import com.amazon.mp3.automotiveOS.R;
import kotlin.Metadata;

/* JADX INFO: compiled from: AutomotiveError.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/auto/signin/AutomotiveError;", "", "titleId", "", "messageId", "(Ljava/lang/String;III)V", "getMessageId", "()I", "getTitleId", "AUTHENTICATION_ERROR", "AUTHORIZATION_ERROR", "TRANSFER_AUTHORIZATION_ERROR", "CONNECTIVITY_ERROR", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum AutomotiveError {
    AUTHENTICATION_ERROR(R.string.dmusic_automotive_authentication_error_title, R.string.dmusic_automotive_unknown_error),
    AUTHORIZATION_ERROR(R.string.dmusic_automotive_authorization_error_title, R.string.dmusic_automotive_reach_device_limits_error_message),
    TRANSFER_AUTHORIZATION_ERROR(R.string.dmusic_automotive_general_error_title, R.string.dmusic_automotive_transfer_authorization_error_message),
    CONNECTIVITY_ERROR(R.string.dmusic_automotive_connectivity_error_title, R.string.dmusic_automotive_connectivity_error_message);

    private final int messageId;
    private final int titleId;

    AutomotiveError(int i, int i2) {
        this.titleId = i;
        this.messageId = i2;
    }

    public final int getMessageId() {
        return this.messageId;
    }

    public final int getTitleId() {
        return this.titleId;
    }
}
