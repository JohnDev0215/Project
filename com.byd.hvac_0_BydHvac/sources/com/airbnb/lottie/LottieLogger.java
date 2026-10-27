package com.airbnb.lottie;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface LottieLogger {
    void debug(String str);

    void debug(String str, Throwable th);

    void error(String str, Throwable th);

    void warning(String str);

    void warning(String str, Throwable th);
}
