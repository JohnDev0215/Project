package com.airbnb.lottie;

/* JADX INFO: loaded from: classes4.dex */
public interface LottieLogger {
    void debug(String str);

    void debug(String str, Throwable th);

    void warning(String str);

    void warning(String str, Throwable th);
}
