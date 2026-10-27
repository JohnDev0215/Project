package com.amazon.music.platform.playback.capabilities;

import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.playback.capabilities.reduce.ReducibleBoolean;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RatingCapability.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0015B\u0007¢\u0006\u0004\b\u0013\u0010\u0014J,\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0014R\u0019\u0010\f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\f\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u00058\u0016X\u0096D¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/RatingCapability;", "Lcom/amazon/music/platform/playback/capabilities/PlayableEntityCapability;", "", "Lcom/amazon/music/platform/playback/capabilities/reduce/ReducibleBoolean;", "", "", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "eligibilities", "Lcom/amazon/music/platform/data/entity/EntityItem;", "entityItem", "Lcom/amazon/music/platform/Outcome;", "canPerformInternal", "isDisplayable", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "actionName", "Ljava/lang/String;", "getActionName", "()Ljava/lang/String;", "<init>", "()V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class RatingCapability extends PlayableEntityCapability<Boolean> implements ReducibleBoolean {
    private final String actionName = "RATING";
    private final Boolean isDisplayable;

    @Override // com.amazon.music.platform.playback.capabilities.reduce.Reducible
    public Outcome<Boolean> reduce(Outcome<? extends Boolean> outcome, Outcome<? extends Boolean> outcome2) {
        return ReducibleBoolean.DefaultImpls.reduce(this, outcome, outcome2);
    }

    @Override // com.amazon.music.platform.playback.capabilities.PlayableEntityCapability
    public String getActionName() {
        return this.actionName;
    }

    @Override // com.amazon.music.platform.playback.capabilities.PlayableEntityCapability
    protected Outcome<Boolean> canPerformInternal(Map<String, ? extends Eligibility> eligibilities, EntityItem entityItem) {
        Intrinsics.checkNotNullParameter(eligibilities, "eligibilities");
        return new Outcome.Success(Boolean.valueOf(Intrinsics.areEqual(this.isDisplayable, Boolean.TRUE)));
    }
}
