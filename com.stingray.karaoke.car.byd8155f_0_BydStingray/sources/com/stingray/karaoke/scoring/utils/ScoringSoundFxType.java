package com.stingray.karaoke.scoring.utils;

import I6.b;
import com.stingray.drivemodedetector.R;
import h6.r;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import q6.AbstractC2186a;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001d\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!¨\u0006\""}, d2 = {"Lcom/stingray/karaoke/scoring/utils/ScoringSoundFxType;", "", "", "resId", "LI6/b;", "config", "<init>", "(Ljava/lang/String;IILI6/b;)V", "I", "getResId", "()I", "LI6/b;", "getConfig", "()LI6/b;", "PG_COUNT", "PG_COUNT_GO", "GP_MULTI_DOWN", "GP_MULTI_L2", "GP_MULTI_L3", "GP_MULTI_L4", "RS_SCORE", "RS_HIT", "RS_STREAK", "RS_STAR1", "RS_STAR2", "RS_STAR3", "RS_STAR4", "RS_STAR5", "RS_REACTION_L1", "RS_REACTION_L2", "RS_REACTION_L3", "RS_REACTION_L4", "RS_REACTION_L5", "RS_DRONE", "scoring_baseEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ScoringSoundFxType {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ ScoringSoundFxType[] $VALUES;
    public static final ScoringSoundFxType GP_MULTI_DOWN;
    public static final ScoringSoundFxType GP_MULTI_L2;
    public static final ScoringSoundFxType GP_MULTI_L3;
    public static final ScoringSoundFxType GP_MULTI_L4;
    public static final ScoringSoundFxType PG_COUNT = new ScoringSoundFxType("PG_COUNT", 0, R.raw.pg_count, null, 2, null);
    public static final ScoringSoundFxType PG_COUNT_GO;
    public static final ScoringSoundFxType RS_DRONE;
    public static final ScoringSoundFxType RS_HIT;
    public static final ScoringSoundFxType RS_REACTION_L1;
    public static final ScoringSoundFxType RS_REACTION_L2;
    public static final ScoringSoundFxType RS_REACTION_L3;
    public static final ScoringSoundFxType RS_REACTION_L4;
    public static final ScoringSoundFxType RS_REACTION_L5;
    public static final ScoringSoundFxType RS_SCORE;
    public static final ScoringSoundFxType RS_STAR1;
    public static final ScoringSoundFxType RS_STAR2;
    public static final ScoringSoundFxType RS_STAR3;
    public static final ScoringSoundFxType RS_STAR4;
    public static final ScoringSoundFxType RS_STAR5;
    public static final ScoringSoundFxType RS_STREAK;
    private final b config;
    private final int resId;

    private static final /* synthetic */ ScoringSoundFxType[] $values() {
        return new ScoringSoundFxType[]{PG_COUNT, PG_COUNT_GO, GP_MULTI_DOWN, GP_MULTI_L2, GP_MULTI_L3, GP_MULTI_L4, RS_SCORE, RS_HIT, RS_STREAK, RS_STAR1, RS_STAR2, RS_STAR3, RS_STAR4, RS_STAR5, RS_REACTION_L1, RS_REACTION_L2, RS_REACTION_L3, RS_REACTION_L4, RS_REACTION_L5, RS_DRONE};
    }

    static {
        b bVar = null;
        int i7 = 2;
        DefaultConstructorMarker defaultConstructorMarker = null;
        PG_COUNT_GO = new ScoringSoundFxType("PG_COUNT_GO", 1, R.raw.pg_count_go, bVar, i7, defaultConstructorMarker);
        b bVar2 = null;
        int i10 = 2;
        DefaultConstructorMarker defaultConstructorMarker2 = null;
        GP_MULTI_DOWN = new ScoringSoundFxType("GP_MULTI_DOWN", 2, R.raw.gp_multi_down, bVar2, i10, defaultConstructorMarker2);
        GP_MULTI_L2 = new ScoringSoundFxType("GP_MULTI_L2", 3, R.raw.gp_multi_l2, bVar, i7, defaultConstructorMarker);
        GP_MULTI_L3 = new ScoringSoundFxType("GP_MULTI_L3", 4, R.raw.gp_multi_l3, bVar2, i10, defaultConstructorMarker2);
        GP_MULTI_L4 = new ScoringSoundFxType("GP_MULTI_L4", 5, R.raw.gp_multi_l4, bVar, i7, defaultConstructorMarker);
        b bVar3 = AbstractC2186a.f21255a;
        RS_SCORE = new ScoringSoundFxType("RS_SCORE", 6, R.raw.rs_score, bVar3);
        RS_HIT = new ScoringSoundFxType("RS_HIT", 7, R.raw.rs_hit, bVar3);
        RS_STREAK = new ScoringSoundFxType("RS_STREAK", 8, R.raw.rs_streak, bVar3);
        RS_STAR1 = new ScoringSoundFxType("RS_STAR1", 9, R.raw.rs_star1, null, 2, null);
        RS_STAR2 = new ScoringSoundFxType("RS_STAR2", 10, R.raw.rs_star2, null, 2, null);
        b bVar4 = null;
        int i11 = 2;
        DefaultConstructorMarker defaultConstructorMarker3 = null;
        RS_STAR3 = new ScoringSoundFxType("RS_STAR3", 11, R.raw.rs_star3, bVar4, i11, defaultConstructorMarker3);
        b bVar5 = null;
        int i12 = 2;
        DefaultConstructorMarker defaultConstructorMarker4 = null;
        RS_STAR4 = new ScoringSoundFxType("RS_STAR4", 12, R.raw.rs_star4, bVar5, i12, defaultConstructorMarker4);
        RS_STAR5 = new ScoringSoundFxType("RS_STAR5", 13, R.raw.rs_star5, bVar4, i11, defaultConstructorMarker3);
        RS_REACTION_L1 = new ScoringSoundFxType("RS_REACTION_L1", 14, R.raw.rs_reaction_l1, bVar5, i12, defaultConstructorMarker4);
        RS_REACTION_L2 = new ScoringSoundFxType("RS_REACTION_L2", 15, R.raw.rs_reaction_l2, bVar4, i11, defaultConstructorMarker3);
        RS_REACTION_L3 = new ScoringSoundFxType("RS_REACTION_L3", 16, R.raw.rs_reaction_l3, bVar5, i12, defaultConstructorMarker4);
        RS_REACTION_L4 = new ScoringSoundFxType("RS_REACTION_L4", 17, R.raw.rs_reaction_l4, bVar4, i11, defaultConstructorMarker3);
        RS_REACTION_L5 = new ScoringSoundFxType("RS_REACTION_L5", 18, R.raw.rs_reaction_l5, bVar5, i12, defaultConstructorMarker4);
        RS_DRONE = new ScoringSoundFxType("RS_DRONE", 19, R.raw.rs_drone, AbstractC2186a.f21256b);
        ScoringSoundFxType[] scoringSoundFxTypeArr$values = $values();
        $VALUES = scoringSoundFxTypeArr$values;
        $ENTRIES = r.e(scoringSoundFxTypeArr$values);
    }

    private ScoringSoundFxType(String str, int i7, int i10, b bVar) {
        this.resId = i10;
        this.config = bVar;
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static ScoringSoundFxType valueOf(String str) {
        return (ScoringSoundFxType) Enum.valueOf(ScoringSoundFxType.class, str);
    }

    public static ScoringSoundFxType[] values() {
        return (ScoringSoundFxType[]) $VALUES.clone();
    }

    public final b getConfig() {
        return this.config;
    }

    public final int getResId() {
        return this.resId;
    }

    public /* synthetic */ ScoringSoundFxType(String str, int i7, int i10, b bVar, int i11, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i7, i10, (i11 & 2) != 0 ? null : bVar);
    }
}
