package com.amazon.mp3.fragment;

import android.net.Uri;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.music.destination.parser.ParserUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RedownloadOfflineMusicFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¨\u0006\u0004"}, d2 = {"getContentUri", "Landroid/net/Uri;", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class RedownloadOfflineMusicFragmentKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Uri getContentUri(String str) {
        Uri primeAdditionalTrackContentUri = MediaProvider.Tracks.getPrimeAdditionalTrackContentUri("cirrus", "cta", str);
        Intrinsics.checkNotNullExpressionValue(primeAdditionalTrackContentUri, "getPrimeAdditionalTrackC…ME_SOURCE_CTA, trackAsin)");
        return primeAdditionalTrackContentUri;
    }
}
