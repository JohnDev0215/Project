package com.netinsight.sye.syeClient.video;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\u0018\u0000 \u00152\u00020\u0001:\u0001\rB\u001b\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005¢\u0006\u0004\b\u0011\u0010\u0012B\u0013\b\u0016\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0011\u0010\u0014J\r\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0003\u0010\u0004R\"\u0010\f\u001a\u00020\u00058\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\u0010\u001a\u00020\u00058\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u0007\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/netinsight/sye/syeClient/video/SyeVideoPreferences;", "Lcom/netinsight/sye/syeClient/video/ISyeVideoPreferences;", "", "toJson", "()Ljava/lang/String;", "", "b", "I", "getMaxHeight", "()I", "setMaxHeight", "(I)V", "maxHeight", "a", "getMaxBitrate", "setMaxBitrate", "maxBitrate", "<init>", "(II)V", "jsonString", "(Ljava/lang/String;)V", "Companion", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final class SyeVideoPreferences implements ISyeVideoPreferences {

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public int maxBitrate;

    /* JADX INFO: renamed from: b, reason: from kotlin metadata */
    public int maxHeight;

    /* JADX WARN: Illegal instructions before constructor call */
    public SyeVideoPreferences() {
        int i = 0;
        this(i, i, 3, null);
    }

    public SyeVideoPreferences(int i, int i2) {
        this.maxBitrate = i;
        this.maxHeight = i2;
    }

    public /* synthetic */ SyeVideoPreferences(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SyeVideoPreferences(String str) {
        int i = 0;
        this(i, i, 3, null);
        if (str != null) {
            JSONObject jSONObject = new JSONObject(str);
            setMaxBitrate(jSONObject.getInt("maxBitrate"));
            setMaxHeight(jSONObject.getInt("maxHeight"));
        }
    }

    @Override // com.netinsight.sye.syeClient.video.ISyeVideoPreferences
    public int getMaxBitrate() {
        return this.maxBitrate;
    }

    @Override // com.netinsight.sye.syeClient.video.ISyeVideoPreferences
    public int getMaxHeight() {
        return this.maxHeight;
    }

    @Override // com.netinsight.sye.syeClient.video.ISyeVideoPreferences
    public void setMaxBitrate(int i) {
        this.maxBitrate = i;
    }

    @Override // com.netinsight.sye.syeClient.video.ISyeVideoPreferences
    public void setMaxHeight(int i) {
        this.maxHeight = i;
    }

    public final String toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("maxBitrate", getMaxBitrate());
        jSONObject.put("maxHeight", getMaxHeight());
        String string = jSONObject.toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "jsonObject.toString()");
        return string;
    }
}
