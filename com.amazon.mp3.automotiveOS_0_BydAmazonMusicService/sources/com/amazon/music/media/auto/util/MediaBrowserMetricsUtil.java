package com.amazon.music.media.auto.util;

import com.amazon.music.media.auto.tab.TabType;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt___StringsKt;

/* JADX INFO: compiled from: MediaBrowserMetricsUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0014\u0010\u0015J \u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002J(\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002J \u0010\f\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002J(\u0010\u000e\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002J(\u0010\u0010\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002J \u0010\u0010\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002J(\u0010\u0011\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002J(\u0010\u0012\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002J\u001e\u0010\u0013\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0002¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/media/auto/util/MediaBrowserMetricsUtil;", "", "", "eventName", "flexStr1", "message", "", "sendEvent", "flexStr2", "Lcom/amazon/music/media/auto/tab/TabType;", "tabType", ImagesContract.URL, "sendInvokeEvent", "retryMsg", "sendAuthenticationFailureEvent", TetheredMessageKey.requestId, "sendFailureEvent", "sendFailure4XXEvent", "sendFailure5XXEvent", "sendRetryFailureEvent", "<init>", "()V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class MediaBrowserMetricsUtil {
    public static final MediaBrowserMetricsUtil INSTANCE = new MediaBrowserMetricsUtil();

    /* JADX INFO: compiled from: MediaBrowserMetricsUtil.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TabType.values().length];
            iArr[TabType.HOME.ordinal()] = 1;
            iArr[TabType.LIBRARY.ordinal()] = 2;
            iArr[TabType.PODCAST.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private MediaBrowserMetricsUtil() {
    }

    public final void sendEvent(String eventName, String flexStr1, String message) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(flexStr1, "flexStr1");
        FlexEvent.Builder builderWithHcFlexStr1 = FlexEvent.builder(eventName).withHcFlexStr1(flexStr1);
        if (message != null) {
            builderWithHcFlexStr1.withHcFlexStr2(StringsKt___StringsKt.take(message, 128));
        }
        MetricsHolder.getManager().handleEvent(builderWithHcFlexStr1.build());
    }

    public final void sendEvent(String eventName, String flexStr1, String flexStr2, String message) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(flexStr1, "flexStr1");
        Intrinsics.checkNotNullParameter(flexStr2, "flexStr2");
        FlexEvent.Builder builderWithHcFlexStr2 = FlexEvent.builder(eventName).withHcFlexStr1(flexStr1).withHcFlexStr2(flexStr2);
        if (message != null) {
            builderWithHcFlexStr2.withHcFlexStr3(StringsKt___StringsKt.take(message, 128));
        }
        MetricsHolder.getManager().handleEvent(builderWithHcFlexStr2.build());
    }

    public final void sendInvokeEvent(TabType tabType, String url, String message) {
        String str;
        Intrinsics.checkNotNullParameter(tabType, "tabType");
        Intrinsics.checkNotNullParameter(url, "url");
        int i = WhenMappings.$EnumSwitchMapping$0[tabType.ordinal()];
        if (i == 1) {
            str = "autoHomeInvokeMeskSkillTaskRequest";
        } else if (i == 2) {
            str = "autoLibraryInvokeMeskSkillTaskRequest";
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            str = "autoPodcastInvokeMeskSkillTaskRequest";
        }
        sendEvent(str, url, message);
    }

    public final void sendAuthenticationFailureEvent(TabType tabType, String url, String retryMsg, String message) {
        String str;
        Intrinsics.checkNotNullParameter(tabType, "tabType");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(retryMsg, "retryMsg");
        int i = WhenMappings.$EnumSwitchMapping$0[tabType.ordinal()];
        if (i == 1) {
            str = "autoHomeAuthenticationFailure";
        } else if (i == 2) {
            str = "autoLibraryAuthenticationFailure";
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            str = "autoPodcastAuthenticationRetryFailure";
        }
        sendEvent(str, url, retryMsg, message);
    }

    public final void sendFailureEvent(TabType tabType, String url, String requestId, String message) {
        String str;
        Intrinsics.checkNotNullParameter(tabType, "tabType");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        int i = WhenMappings.$EnumSwitchMapping$0[tabType.ordinal()];
        if (i == 1) {
            str = "autoHomeInvokeMeskSkillTaskResponseFailure";
        } else if (i == 2) {
            str = "autoLibraryInvokeMeskSkillTaskResponseFailure";
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            str = "autoPodcastInvokeMeskSkillTaskResponseFailure";
        }
        sendEvent(str, url, requestId, message);
    }

    public final void sendFailureEvent(TabType tabType, String url, String message) {
        String str;
        Intrinsics.checkNotNullParameter(tabType, "tabType");
        Intrinsics.checkNotNullParameter(url, "url");
        int i = WhenMappings.$EnumSwitchMapping$0[tabType.ordinal()];
        if (i == 1) {
            str = "autoHomeInvokeMeskSkillTaskResponseFailure";
        } else if (i == 2) {
            str = "autoLibraryInvokeMeskSkillTaskResponseFailure";
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            str = "autoPodcastInvokeMeskSkillTaskResponseFailure";
        }
        sendEvent(str, url, message);
    }

    public final void sendFailure4XXEvent(TabType tabType, String url, String requestId, String message) {
        String str;
        Intrinsics.checkNotNullParameter(tabType, "tabType");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        int i = WhenMappings.$EnumSwitchMapping$0[tabType.ordinal()];
        if (i == 1) {
            str = "autoHomeInvokeMeskSkillTaskResponseFailureCode4XX";
        } else if (i == 2) {
            str = "autoLibraryInvokeMeskSkillTaskResponseFailureCode4XX";
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            str = "autoPodcastInvokeMeskSkillTaskResponseFailureCode4XX";
        }
        sendEvent(str, url, requestId, message);
    }

    public final void sendFailure5XXEvent(TabType tabType, String url, String requestId, String message) {
        String str;
        Intrinsics.checkNotNullParameter(tabType, "tabType");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        int i = WhenMappings.$EnumSwitchMapping$0[tabType.ordinal()];
        if (i == 1) {
            str = "autoHomeInvokeMeskSkillTaskResponseFailureCode5XX";
        } else if (i == 2) {
            str = "autoLibraryInvokeMeskSkillTaskResponseFailureCode5XX";
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            str = "autoPodcastInvokeMeskSkillTaskResponseFailureCode5XX";
        }
        sendEvent(str, url, requestId, message);
    }

    public final void sendRetryFailureEvent(TabType tabType, String url, String requestId) {
        String str;
        Intrinsics.checkNotNullParameter(tabType, "tabType");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        int i = WhenMappings.$EnumSwitchMapping$0[tabType.ordinal()];
        if (i == 1) {
            str = "autoHomeAuthenticationRetryFailure";
        } else if (i == 2) {
            str = "autoLibraryAuthenticationRetryFailure";
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            str = "autoPodcastAuthenticationRetryFailure";
        }
        sendEvent(str, url, requestId, "Failed to make MESK call after retries");
    }
}
