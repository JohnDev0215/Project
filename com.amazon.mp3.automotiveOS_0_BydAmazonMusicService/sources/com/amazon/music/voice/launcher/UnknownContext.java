package com.amazon.music.voice.launcher;

import kotlin.Metadata;

/* JADX INFO: compiled from: LaunchTargetError.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/music/voice/launcher/UnknownContext;", "Lcom/amazon/music/voice/launcher/LaunchTargetError;", "", "errorMessage", "Ljava/lang/String;", "getErrorMessage", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
public final class UnknownContext extends LaunchTargetError {
    private final String errorMessage;

    public UnknownContext(String str) {
        super(null);
        this.errorMessage = str;
    }
}
