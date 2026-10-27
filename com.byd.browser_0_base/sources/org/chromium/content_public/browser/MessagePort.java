package org.chromium.content_public.browser;

import defpackage.ke0;
import org.chromium.build.annotations.UsedByReflection;

/* JADX INFO: loaded from: classes3.dex */
@UsedByReflection
public interface MessagePort {

    public interface a {
    }

    void a(MessagePayload messagePayload);

    void b(ke0 ke0Var);

    boolean c();

    void close();

    boolean d();

    boolean isClosed();
}
