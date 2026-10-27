package com.netinsight.sye.syeClient.notification;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0010\u001a\u00020\b\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\b¢\u0006\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0007\u001a\u00020\u00028\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\r\u001a\u00020\b8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\u0010\u001a\u00020\b8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000e\u0010\n\u001a\u0004\b\u000f\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/netinsight/sye/syeClient/notification/SyeNotificationMessage;", "Lcom/netinsight/sye/syeClient/notification/ISyeNotificationMessage;", "", "b", "J", "getPresentationTimeMicros", "()J", "presentationTimeMicros", "", "c", "Ljava/lang/String;", "getData", "()Ljava/lang/String;", "data", "a", "getMessageType", "messageType", "<init>", "(Ljava/lang/String;JLjava/lang/String;)V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final class SyeNotificationMessage implements ISyeNotificationMessage {

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public final String messageType;

    /* JADX INFO: renamed from: b, reason: from kotlin metadata */
    public final long presentationTimeMicros;

    /* JADX INFO: renamed from: c, reason: from kotlin metadata */
    public final String data;

    public SyeNotificationMessage(String str, long j, String str2) {
        this.messageType = str;
        this.presentationTimeMicros = j;
        this.data = str2;
    }

    @Override // com.netinsight.sye.syeClient.notification.ISyeNotificationMessage
    public String getData() {
        return this.data;
    }

    @Override // com.netinsight.sye.syeClient.notification.ISyeNotificationMessage
    public String getMessageType() {
        return this.messageType;
    }

    @Override // com.netinsight.sye.syeClient.notification.ISyeNotificationMessage
    public long getPresentationTimeMicros() {
        return this.presentationTimeMicros;
    }
}
