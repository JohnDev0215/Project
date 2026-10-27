package io.sentry.transport;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/* JADX INFO: loaded from: classes.dex */
public final class j extends Authenticator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f16902a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f16903b;

    public j(String str, String str2) {
        h6.e.q(str, "user is required");
        this.f16902a = str;
        h6.e.q(str2, "password is required");
        this.f16903b = str2;
    }

    @Override // java.net.Authenticator
    public final PasswordAuthentication getPasswordAuthentication() {
        if (getRequestorType() != Authenticator.RequestorType.PROXY) {
            return null;
        }
        return new PasswordAuthentication(this.f16902a, this.f16903b.toCharArray());
    }
}
