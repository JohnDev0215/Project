package com.visioncritical.touchpointkit.commons;

import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;

/* JADX INFO: compiled from: TouchPointConstants.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\t\u0010\nR3\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0002j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/visioncritical/touchpointkit/commons/TouchPointConfig;", "", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "apiMap", "Ljava/util/HashMap;", "getApiMap", "()Ljava/util/HashMap;", "<init>", "()V", "touchpointkit_release"}, k = 1, mv = {1, 6, 0})
public final class TouchPointConfig {
    public static final TouchPointConfig INSTANCE = new TouchPointConfig();
    private static final HashMap<String, String> apiMap = MapsKt__MapsKt.hashMapOf(TuplesKt.to("dev1", "https://api-touchpoint.dev1.alidalabs.com"), TuplesKt.to("dev2", "https://api-touchpoint.dev2.alidalabs.com"), TuplesKt.to("sit1", "https://api-touchpoint.sit1.alidalabs.com"), TuplesKt.to("na1", "https://api-touchpoint.na1.alida.com"), TuplesKt.to("na2", "https://api-touchpoint.na2.alida.com"), TuplesKt.to("eu1", "https://api-touchpoint.eu1.alida.com"), TuplesKt.to("eu2", "https://api-touchpoint.eu2.alida.com"), TuplesKt.to("ap2", "https://api-touchpoint.ap2.alida.com"), TuplesKt.to("ap3", "https://api-touchpoint.ap3.alida.com"), TuplesKt.to("pub", "https://api-touchpoint.pub.alida.com"));

    private TouchPointConfig() {
    }

    public final HashMap<String, String> getApiMap() {
        return apiMap;
    }
}
