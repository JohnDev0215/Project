package com.neusoft.na.common.bean;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TtsBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/neusoft/na/common/bean/TtsBean;", "", "()V", NotificationCompat.CATEGORY_MESSAGE, "", "getMsg", "()Ljava/lang/String;", "setMsg", "(Ljava/lang/String;)V", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class TtsBean {
    private String msg = "";

    public final String getMsg() {
        return this.msg;
    }

    public final void setMsg(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.msg = str;
    }
}
