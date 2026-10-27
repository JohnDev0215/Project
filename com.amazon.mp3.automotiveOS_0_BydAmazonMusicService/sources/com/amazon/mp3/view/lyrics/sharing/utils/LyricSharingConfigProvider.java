package com.amazon.mp3.view.lyrics.sharing.utils;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.lyrics.LyricsDatabase;
import com.amazon.mp3.lyrics.LyricsManager;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.lyrics.item.LyricsTrackInfo;
import com.amazon.mp3.playback.view.lyrics.LyricsProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: LyricSharingConfigProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 +2\u00020\u0001:\u0001+B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001f\u001a\u00020 J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001cH\u0002J\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001a0%2\u0006\u0010#\u001a\u00020\u001cJ\u0010\u0010&\u001a\u00020'2\u0006\u0010#\u001a\u00020\u001cH\u0002J\u0012\u0010(\u001a\u00020 2\b\u0010)\u001a\u0004\u0018\u00010*H\u0002R\u001c\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010\u001b\u001a\n \u0005*\u0004\u0018\u00010\u001c0\u001c8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u0006,"}, d2 = {"Lcom/amazon/mp3/view/lyrics/sharing/utils/LyricSharingConfigProvider;", "", "()V", "applicationContext", "Landroid/content/Context;", "kotlin.jvm.PlatformType", "getApplicationContext", "()Landroid/content/Context;", "job", "Lkotlinx/coroutines/Job;", "lyricsDB", "Lcom/amazon/mp3/lyrics/LyricsDatabase;", "getLyricsDB", "()Lcom/amazon/mp3/lyrics/LyricsDatabase;", "lyricsManager", "Lcom/amazon/mp3/lyrics/LyricsManager;", "getLyricsManager", "()Lcom/amazon/mp3/lyrics/LyricsManager;", "lyricsProvider", "Lcom/amazon/mp3/playback/view/lyrics/LyricsProvider;", "getLyricsProvider", "()Lcom/amazon/mp3/playback/view/lyrics/LyricsProvider;", "lyricsProvider$delegate", "Lkotlin/Lazy;", "lyricsSharingConfig", "Landroidx/lifecycle/MutableLiveData;", "", "marketPlaceId", "", "getMarketPlaceId", "()Ljava/lang/String;", "close", "", "getLyricsTrackInfo", "Lcom/amazon/mp3/lyrics/item/LyricsTrackInfo;", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "isLyricsSharable", "Landroidx/lifecycle/LiveData;", "lyricsProviderCallback", "Lcom/amazon/mp3/playback/view/lyrics/LyricsProvider$LoadLyricsCallback;", "updateLyricsSharingConfig", "lyrics", "Lcom/amazon/mp3/lyrics/item/Lyrics;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LyricSharingConfigProvider {
    private static final String TAG = LyricSharingConfigProvider.class.getSimpleName();
    private Job job;

    /* JADX INFO: renamed from: lyricsProvider$delegate, reason: from kotlin metadata */
    private final Lazy lyricsProvider = LazyKt__LazyJVMKt.lazy(new Function0<LyricsProvider>() { // from class: com.amazon.mp3.view.lyrics.sharing.utils.LyricSharingConfigProvider$lyricsProvider$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LyricsProvider invoke() {
            return new LyricsProvider(this.this$0.getApplicationContext());
        }
    });
    private MutableLiveData<Boolean> lyricsSharingConfig;

    /* JADX INFO: Access modifiers changed from: private */
    public final Context getApplicationContext() {
        return AmazonApplication.getApplication().getApplicationContext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getMarketPlaceId() {
        return AccountDetailUtil.get(getApplicationContext()).getHomeMarketPlaceId();
    }

    private final LyricsDatabase getLyricsDB() {
        return new LyricsDatabase(getApplicationContext(), getMarketPlaceId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LyricsManager getLyricsManager() {
        return new LyricsManager(getApplicationContext(), getLyricsDB());
    }

    private final LyricsProvider getLyricsProvider() {
        return (LyricsProvider) this.lyricsProvider.getValue();
    }

    private final LyricsProvider.LoadLyricsCallback lyricsProviderCallback(final String trackAsin) {
        return new LyricsProvider.LoadLyricsCallback() { // from class: com.amazon.mp3.view.lyrics.sharing.utils.LyricSharingConfigProvider.lyricsProviderCallback.1
            @Override // com.amazon.mp3.playback.view.lyrics.LyricsProvider.LoadLyricsCallback
            public void onLoadDelay() {
            }

            @Override // com.amazon.mp3.playback.view.lyrics.LyricsProvider.LoadLyricsCallback
            public void onLyricsLoaded(Lyrics lyrics) {
                Intrinsics.checkNotNullParameter(lyrics, "lyrics");
                MutableLiveData mutableLiveData = null;
                if (lyrics.isLyricsSharable() != null) {
                    Log.info(LyricSharingConfigProvider.TAG, Intrinsics.stringPlus("LyricsSharingConfig fetched isLyricsSharable - ", lyrics.isLyricsSharable()));
                    MutableLiveData mutableLiveData2 = LyricSharingConfigProvider.this.lyricsSharingConfig;
                    if (mutableLiveData2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("lyricsSharingConfig");
                    } else {
                        mutableLiveData = mutableLiveData2;
                    }
                    mutableLiveData.setValue(lyrics.isLyricsSharable());
                    return;
                }
                Log.info(LyricSharingConfigProvider.TAG, "Lyrics cached but LyricsSharingConfig not present, updating Lyrics");
                LyricSharingConfigProvider.this.job = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new LyricSharingConfigProvider$lyricsProviderCallback$1$onLyricsLoaded$1(LyricSharingConfigProvider.this, trackAsin, null), 2, null);
            }

            @Override // com.amazon.mp3.playback.view.lyrics.LyricsProvider.LoadLyricsCallback
            public void onNoLyrics() {
                Log.info(LyricSharingConfigProvider.TAG, "No lyrics present for track");
                MutableLiveData mutableLiveData = LyricSharingConfigProvider.this.lyricsSharingConfig;
                if (mutableLiveData == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("lyricsSharingConfig");
                    mutableLiveData = null;
                }
                mutableLiveData.setValue(Boolean.FALSE);
            }
        };
    }

    public final LiveData<Boolean> isLyricsSharable(String trackAsin) {
        Intrinsics.checkNotNullParameter(trackAsin, "trackAsin");
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.lyricsSharingConfig = new MutableLiveData<>();
        getLyricsProvider().fetchLyrics(getLyricsTrackInfo(trackAsin), lyricsProviderCallback(trackAsin));
        MutableLiveData<Boolean> mutableLiveData = this.lyricsSharingConfig;
        if (mutableLiveData != null) {
            return mutableLiveData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("lyricsSharingConfig");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateLyricsSharingConfig(Lyrics lyrics) {
        String str = TAG;
        Log.info(str, "Fetched updated lyrics");
        MutableLiveData<Boolean> mutableLiveData = null;
        if (lyrics != null && lyrics.isLyricsSharable() != null) {
            Log.info(str, Intrinsics.stringPlus("LyricsSharingConfig fetched and present, isLyricsSharable -> ", lyrics.isLyricsSharable()));
            MutableLiveData<Boolean> mutableLiveData2 = this.lyricsSharingConfig;
            if (mutableLiveData2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lyricsSharingConfig");
            } else {
                mutableLiveData = mutableLiveData2;
            }
            mutableLiveData.setValue(lyrics.isLyricsSharable());
            return;
        }
        Log.info(str, "LyricsSharingConfig not present for track");
        MutableLiveData<Boolean> mutableLiveData3 = this.lyricsSharingConfig;
        if (mutableLiveData3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lyricsSharingConfig");
        } else {
            mutableLiveData = mutableLiveData3;
        }
        mutableLiveData.setValue(Boolean.FALSE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LyricsTrackInfo getLyricsTrackInfo(final String trackAsin) {
        return new LyricsTrackInfo() { // from class: com.amazon.mp3.view.lyrics.sharing.utils.LyricSharingConfigProvider.getLyricsTrackInfo.1
            @Override // com.amazon.mp3.lyrics.item.LyricsTrackInfo
            /* JADX INFO: renamed from: getAsin, reason: from getter */
            public String get$trackAsin() {
                return trackAsin;
            }

            @Override // com.amazon.mp3.lyrics.item.LyricsTrackInfo
            public String getMarketplaceId() {
                String marketPlaceId = this.getMarketPlaceId();
                Intrinsics.checkNotNullExpressionValue(marketPlaceId, "marketPlaceId");
                return marketPlaceId;
            }
        };
    }

    public final void close() {
        Job job = this.job;
        if (job == null) {
            return;
        }
        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
    }
}
