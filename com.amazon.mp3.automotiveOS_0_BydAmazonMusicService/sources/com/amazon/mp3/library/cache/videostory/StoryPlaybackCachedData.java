package com.amazon.mp3.library.cache.videostory;

import CoreInterface.v1_0.Template;
import com.amazon.mp3.video.stories.VideoStoriesUtil;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StoryPlaybackCachedData.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0007¢\u0006\u0004\b\u0018\u0010\u0019R$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR(\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u00118\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u00118\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/library/cache/videostory/StoryPlaybackCachedData;", "", "LCoreInterface/v1_0/Template;", "template", "LCoreInterface/v1_0/Template;", "getTemplate", "()LCoreInterface/v1_0/Template;", "setTemplate", "(LCoreInterface/v1_0/Template;)V", "", "", "playbackUrls", "Ljava/util/List;", "getPlaybackUrls", "()Ljava/util/List;", "setPlaybackUrls", "(Ljava/util/List;)V", "Ljava/util/Date;", "fetchTime", "Ljava/util/Date;", "getFetchTime", "()Ljava/util/Date;", "expiredTime", "getExpiredTime", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class StoryPlaybackCachedData implements CachedData {
    private final Date expiredTime;
    private Template template;
    private List<String> playbackUrls = new ArrayList();
    private final Date fetchTime = new Date();

    public StoryPlaybackCachedData() {
        Integer tTlForVideoStoriesCacheEntry = VideoStoriesUtil.INSTANCE.getTTlForVideoStoriesCacheEntry();
        int iIntValue = tTlForVideoStoriesCacheEntry == null ? MediaError.DetailedErrorCode.TEXT_UNKNOWN : tTlForVideoStoriesCacheEntry.intValue();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getFetchTime());
        calendar.add(13, iIntValue);
        Date time = calendar.getTime();
        Intrinsics.checkNotNullExpressionValue(time, "cal.time");
        this.expiredTime = time;
    }

    public final Template getTemplate() {
        return this.template;
    }

    public final void setTemplate(Template template) {
        this.template = template;
    }

    public Date getFetchTime() {
        return this.fetchTime;
    }

    @Override // com.amazon.mp3.library.cache.videostory.CachedData
    public Date getExpiredTime() {
        return this.expiredTime;
    }
}
