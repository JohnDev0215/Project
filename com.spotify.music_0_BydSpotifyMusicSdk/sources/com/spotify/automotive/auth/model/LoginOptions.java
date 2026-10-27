package com.spotify.automotive.auth.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class LoginOptions {
    public final boolean a;
    public final boolean b;

    public LoginOptions(@z61(name = "bootstrap_required") boolean z, @z61(name = "auth_only_setting") boolean z2) {
        this.a = z;
        this.b = z2;
    }

    public /* synthetic */ LoginOptions(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? false : z2);
    }
}
