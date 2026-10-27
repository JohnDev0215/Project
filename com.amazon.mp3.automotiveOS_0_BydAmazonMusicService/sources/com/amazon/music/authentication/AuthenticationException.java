package com.amazon.music.authentication;

import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AuthenticationException.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0011\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005B\u001f\b\u0007\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/amazon/music/authentication/AuthenticationException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "cause", "", "(Ljava/lang/Throwable;)V", "message", "", "throwable", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "DMMAuthentication_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AuthenticationException extends Exception {
    @JvmOverloads
    public AuthenticationException() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @JvmOverloads
    public AuthenticationException(String str) {
        this(str, null, 2, 0 == true ? 1 : 0);
    }

    public /* synthetic */ AuthenticationException(String str, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : th);
    }

    @JvmOverloads
    public AuthenticationException(String str, Throwable th) {
        super(str, th);
    }

    public AuthenticationException(Throwable th) {
        this(null, th);
    }
}
