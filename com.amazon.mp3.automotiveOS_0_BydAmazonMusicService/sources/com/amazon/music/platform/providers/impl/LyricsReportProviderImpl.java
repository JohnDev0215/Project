package com.amazon.music.platform.providers.impl;

import com.amazon.music.platform.providers.LyricsReportProvider;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: LyricsReportProviderImpl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JC\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u0006H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/platform/providers/impl/LyricsReportProviderImpl;", "Lcom/amazon/music/platform/providers/LyricsReportProvider;", "()V", "submitCustomerReport", "Lcom/amazon/music/platform/lyricReport/data/LyricsReportResponse;", "id", "", "title", "durationInAsset", "", "reportedType", "addedDetails", "creatorId", "(Ljava/lang/String;Ljava/lang/String;DLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class LyricsReportProviderImpl implements LyricsReportProvider {

    /* JADX INFO: renamed from: com.amazon.music.platform.providers.impl.LyricsReportProviderImpl$submitCustomerReport$1, reason: invalid class name */
    /* JADX INFO: compiled from: LyricsReportProviderImpl.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.platform.providers.impl.LyricsReportProviderImpl", f = "LyricsReportProviderImpl.kt", i = {}, l = {39}, m = "submitCustomerReport", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LyricsReportProviderImpl.this.submitCustomerReport(null, null, 0.0d, null, null, null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    @Override // com.amazon.music.platform.providers.LyricsReportProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object submitCustomerReport(java.lang.String r18, java.lang.String r19, double r20, java.lang.String r22, java.lang.String r23, java.lang.String r24, kotlin.coroutines.Continuation<? super com.amazon.music.platform.lyricReport.data.LyricsReportResponse> r25) {
        /*
            Method dump skipped, instruction units count: 237
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.providers.impl.LyricsReportProviderImpl.submitCustomerReport(java.lang.String, java.lang.String, double, java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
