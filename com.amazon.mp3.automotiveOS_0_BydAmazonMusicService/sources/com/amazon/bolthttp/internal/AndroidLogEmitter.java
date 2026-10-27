package com.amazon.bolthttp.internal;

import android.util.Log;
import com.amazon.bolthttp.LogEmitter;
import com.amazon.bolthttp.internal.Logger;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.destination.parser.ParserUtil;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidLogEmitter implements LogEmitter {
    @Override // com.amazon.bolthttp.LogEmitter
    public void log(@Nonnull Logger.Type type, @Nonnull String str, @Nonnull String str2) {
        Preconditions.checkNotNull(type, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        Preconditions.checkNotNull(str, ParserUtil.TAG_QUERY_PARAM_NAME);
        Preconditions.checkNotNull(str2, "message");
        Log.println(type.getAndroidPriority(), str, str2);
    }

    @Override // com.amazon.bolthttp.LogEmitter
    public String getStackTraceString(@Nonnull Throwable th) {
        return Log.getStackTraceString((Throwable) Preconditions.checkNotNull(th, "tr"));
    }
}
