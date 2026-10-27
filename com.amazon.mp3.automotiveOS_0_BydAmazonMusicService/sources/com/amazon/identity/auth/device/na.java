package com.amazon.identity.auth.device;

import java.util.regex.Pattern;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class na {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Pattern f463a = Pattern.compile("((.*[^0-9])|(^))([0-9]{4,6})[^0-9].*[a-zA-Z0-9+\\\\/]{11}", 32);
}
