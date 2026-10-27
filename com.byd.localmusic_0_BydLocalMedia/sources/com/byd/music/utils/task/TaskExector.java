package com.byd.music.utils.task;

/* JADX INFO: loaded from: classes5.dex */
public enum TaskExector {
    DEFALUT(0),
    SEARCH(1),
    NET_WORK(2),
    AE8_ENGINE(3),
    UDISK_DOWNLOAD(4),
    USER_BL(7),
    SYNC_SDK_IO(8),
    ADAPTER(9),
    ACTIVATE_LOG(11);

    private int value;

    TaskExector(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
