package com.amazon.music.media.auto.util;

import kotlin.Metadata;

/* JADX INFO: compiled from: SubDeviceConnectionUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H&J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\t"}, d2 = {"Lcom/amazon/music/media/auto/util/SubDeviceConnectionUtil;", "", "sendSubDeviceConnectedEvent", "", "subDeviceType", "", "shouldStopCasting", "", "sendSubDeviceDisconnectedEvent", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SubDeviceConnectionUtil {
    void sendSubDeviceConnectedEvent(String subDeviceType, boolean shouldStopCasting);

    void sendSubDeviceDisconnectedEvent(String subDeviceType);

    /* JADX INFO: compiled from: SubDeviceConnectionUtil.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void sendSubDeviceConnectedEvent$default(SubDeviceConnectionUtil subDeviceConnectionUtil, String str, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendSubDeviceConnectedEvent");
            }
            if ((i & 2) != 0) {
                z = false;
            }
            subDeviceConnectionUtil.sendSubDeviceConnectedEvent(str, z);
        }
    }
}
