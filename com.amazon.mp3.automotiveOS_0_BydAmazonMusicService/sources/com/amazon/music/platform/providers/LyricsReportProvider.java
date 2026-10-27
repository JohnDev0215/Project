package com.amazon.music.platform.providers;

import com.amazon.music.platform.lyricReport.data.LyricsReportResponse;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: LyricsReportProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001JC\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lcom/amazon/music/platform/providers/LyricsReportProvider;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "submitCustomerReport", "Lcom/amazon/music/platform/lyricReport/data/LyricsReportResponse;", "id", "", "title", "durationInAsset", "", "reportedType", "addedDetails", "creatorId", "(Ljava/lang/String;Ljava/lang/String;DLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface LyricsReportProvider extends InterfaceProvider {
    Object submitCustomerReport(String str, String str2, double d, String str3, String str4, String str5, Continuation<? super LyricsReportResponse> continuation);
}
