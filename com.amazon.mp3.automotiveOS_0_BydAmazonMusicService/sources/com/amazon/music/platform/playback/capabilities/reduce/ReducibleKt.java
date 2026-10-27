package com.amazon.music.platform.playback.capabilities.reduce;

import kotlin.Metadata;

/* JADX INFO: compiled from: Reducible.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a:\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001¨\u0006\u0006"}, d2 = {"reduceFailures", "Lcom/amazon/music/platform/Outcome;", "T", "Lcom/amazon/music/platform/playback/capabilities/reduce/Reducible;", "outcome1", "outcome2", "MusicPlatformCore_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class ReducibleKt {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0046  */
    /* JADX WARN: Type inference failed for: r3v0, types: [com.amazon.music.platform.Outcome<? extends T>, com.amazon.music.platform.Outcome<T>, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.amazon.music.platform.Outcome<? extends T>, com.amazon.music.platform.Outcome<T>, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> com.amazon.music.platform.Outcome<T> reduceFailures(com.amazon.music.platform.playback.capabilities.reduce.Reducible<T> r2, com.amazon.music.platform.Outcome<? extends T> r3, com.amazon.music.platform.Outcome<? extends T> r4) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r2 = "outcome1"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r2)
            java.lang.String r2 = "outcome2"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r2)
            boolean r2 = r3 instanceof com.amazon.music.platform.Outcome.Failure
            r0 = 0
            if (r2 == 0) goto L7b
            boolean r1 = r4 instanceof com.amazon.music.platform.Outcome.Failure
            if (r1 == 0) goto L7b
            r2 = r3
            com.amazon.music.platform.Outcome$Failure r2 = (com.amazon.music.platform.Outcome.Failure) r2
            java.lang.Throwable r1 = r2.getCause()
            boolean r1 = r1 instanceof com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable
            if (r1 == 0) goto L31
            r1 = r4
            com.amazon.music.platform.Outcome$Failure r1 = (com.amazon.music.platform.Outcome.Failure) r1
            java.lang.Throwable r1 = r1.getCause()
            boolean r1 = r1 instanceof com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable
            if (r1 != 0) goto L31
            goto L7a
        L31:
            java.lang.Throwable r1 = r2.getCause()
            boolean r1 = r1 instanceof com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable
            if (r1 != 0) goto L46
            r1 = r4
            com.amazon.music.platform.Outcome$Failure r1 = (com.amazon.music.platform.Outcome.Failure) r1
            java.lang.Throwable r1 = r1.getCause()
            boolean r1 = r1 instanceof com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable
            if (r1 == 0) goto L46
        L44:
            r3 = r4
            goto L7a
        L46:
            java.lang.Throwable r1 = r2.getCause()
            boolean r1 = r1 instanceof com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable
            if (r1 == 0) goto L7a
            com.amazon.music.platform.Outcome$Failure r4 = (com.amazon.music.platform.Outcome.Failure) r4
            java.lang.Throwable r1 = r4.getCause()
            boolean r1 = r1 instanceof com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable
            if (r1 == 0) goto L7a
            java.lang.Throwable r2 = r2.getCause()
            com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable r2 = (com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable) r2
            int r2 = r2.getPriority()
            java.lang.Throwable r1 = r4.getCause()
            com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable r1 = (com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable) r1
            int r1 = r1.getPriority()
            if (r2 >= r1) goto L70
            r2 = 1
            goto L71
        L70:
            r2 = 0
        L71:
            if (r2 == 0) goto L74
            goto L75
        L74:
            r3 = r0
        L75:
            com.amazon.music.platform.Outcome$Failure r3 = (com.amazon.music.platform.Outcome.Failure) r3
            if (r3 != 0) goto L7a
            goto L44
        L7a:
            return r3
        L7b:
            if (r2 == 0) goto L82
            boolean r2 = r4 instanceof com.amazon.music.platform.Outcome.Success
            if (r2 == 0) goto L82
            return r4
        L82:
            boolean r2 = r3 instanceof com.amazon.music.platform.Outcome.Success
            if (r2 == 0) goto L8b
            boolean r2 = r4 instanceof com.amazon.music.platform.Outcome.Failure
            if (r2 == 0) goto L8b
            return r3
        L8b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.playback.capabilities.reduce.ReducibleKt.reduceFailures(com.amazon.music.platform.playback.capabilities.reduce.Reducible, com.amazon.music.platform.Outcome, com.amazon.music.platform.Outcome):com.amazon.music.platform.Outcome");
    }
}
