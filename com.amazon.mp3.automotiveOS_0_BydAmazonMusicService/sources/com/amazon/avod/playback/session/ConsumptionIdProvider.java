package com.amazon.avod.playback.session;

import com.amazon.avod.util.DLog;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConsumptionIdProvider.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010%\n\u0002\u0010#\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u001a\u0010\u000f\u001a\u00020\u00102\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0011\u001a\u00020\u0003H\u0016J\u001a\u0010\u0012\u001a\u00020\u00132\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0011\u001a\u00020\u0003H\u0016R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\b\u001a\u0004\u0018\u00010\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\u00038V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0004R \u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/avod/playback/session/ConsumptionIdProvider;", "", "id", "", "(Ljava/lang/String;)V", "()V", "_consumptionId", "cid", "consumptionId", "getConsumptionId", "()Ljava/lang/String;", "setConsumptionId", "eventMap", "", "", "eventLoggedForId", "", "action", "hasLoggedEventForId", "", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class ConsumptionIdProvider {
    private String _consumptionId;
    private final Map<String, Set<String>> eventMap;

    public ConsumptionIdProvider() {
        this.eventMap = new LinkedHashMap();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ConsumptionIdProvider(String id) {
        this();
        Intrinsics.checkNotNullParameter(id, "id");
        DLog.devf("ConsumptionId set in constructor %s", id);
        this._consumptionId = id;
    }

    public String getConsumptionId() {
        DLog.devf("Current ConsumptionId %s", this._consumptionId);
        return this._consumptionId;
    }

    public void setConsumptionId(String str) {
        DLog.devf("Update (set) ConsumptionId to %s", str);
        this._consumptionId = str;
    }

    public boolean hasLoggedEventForId(String id, String action) {
        Intrinsics.checkNotNullParameter(action, "action");
        Set<String> set = this.eventMap.get(id);
        if (set == null) {
            return false;
        }
        return set.contains(action);
    }

    public void eventLoggedForId(String id, String action) {
        Intrinsics.checkNotNullParameter(action, "action");
        if (id == null) {
            return;
        }
        Set<String> set = this.eventMap.get(id);
        if ((set == null ? null : Boolean.valueOf(set.add(action))) == null) {
            this.eventMap.put(id, SetsKt__SetsKt.mutableSetOf(action));
        }
    }
}
