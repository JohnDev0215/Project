package com.spotify.player.esperanto.proto;

import com.google.common.collect.ImmutableSet;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import java.util.List;
import p.a2;
import p.ds1;
import p.i01;
import p.sg0;
import p.v51;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsRestrictions$Restrictions extends i implements yj1 {
    private static final EsRestrictions$Restrictions DEFAULT_INSTANCE;
    public static final int DISALLOW_ADD_TO_QUEUE_REASONS_FIELD_NUMBER = 22;
    public static final int DISALLOW_INSERTING_INTO_CONTEXT_TRACKS_REASONS_FIELD_NUMBER = 16;
    public static final int DISALLOW_INSERTING_INTO_NEXT_TRACKS_REASONS_FIELD_NUMBER = 15;
    public static final int DISALLOW_INTERRUPTING_PLAYBACK_REASONS_FIELD_NUMBER = 12;
    public static final int DISALLOW_PAUSING_REASONS_FIELD_NUMBER = 1;
    public static final int DISALLOW_PEEKING_NEXT_REASONS_FIELD_NUMBER = 5;
    public static final int DISALLOW_PEEKING_PREV_REASONS_FIELD_NUMBER = 4;
    public static final int DISALLOW_REMOTE_CONTROL_REASONS_FIELD_NUMBER = 14;
    public static final int DISALLOW_REMOVING_FROM_CONTEXT_TRACKS_REASONS_FIELD_NUMBER = 20;
    public static final int DISALLOW_REMOVING_FROM_NEXT_TRACKS_REASONS_FIELD_NUMBER = 19;
    public static final int DISALLOW_REORDERING_IN_CONTEXT_TRACKS_REASONS_FIELD_NUMBER = 18;
    public static final int DISALLOW_REORDERING_IN_NEXT_TRACKS_REASONS_FIELD_NUMBER = 17;
    public static final int DISALLOW_RESUMING_REASONS_FIELD_NUMBER = 2;
    public static final int DISALLOW_SEEKING_REASONS_FIELD_NUMBER = 3;
    public static final int DISALLOW_SETTING_MODE_VALUE_REASONS_FIELD_NUMBER = 24;
    public static final int DISALLOW_SETTING_PLAYBACK_SPEED_REASONS_FIELD_NUMBER = 23;
    public static final int DISALLOW_SET_QUEUE_REASONS_FIELD_NUMBER = 11;
    public static final int DISALLOW_SKIPPING_NEXT_REASONS_FIELD_NUMBER = 7;
    public static final int DISALLOW_SKIPPING_PREV_REASONS_FIELD_NUMBER = 6;
    public static final int DISALLOW_TOGGLING_REPEAT_CONTEXT_REASONS_FIELD_NUMBER = 8;
    public static final int DISALLOW_TOGGLING_REPEAT_TRACK_REASONS_FIELD_NUMBER = 9;
    public static final int DISALLOW_TOGGLING_SHUFFLE_REASONS_FIELD_NUMBER = 10;
    public static final int DISALLOW_TRANSFERRING_PLAYBACK_REASONS_FIELD_NUMBER = 13;
    public static final int DISALLOW_UPDATING_CONTEXT_REASONS_FIELD_NUMBER = 21;
    private static volatile ds1 PARSER;
    private v51 disallowPausingReasons_ = i.emptyProtobufList();
    private v51 disallowResumingReasons_ = i.emptyProtobufList();
    private v51 disallowSeekingReasons_ = i.emptyProtobufList();
    private v51 disallowPeekingPrevReasons_ = i.emptyProtobufList();
    private v51 disallowPeekingNextReasons_ = i.emptyProtobufList();
    private v51 disallowSkippingPrevReasons_ = i.emptyProtobufList();
    private v51 disallowSkippingNextReasons_ = i.emptyProtobufList();
    private v51 disallowTogglingRepeatContextReasons_ = i.emptyProtobufList();
    private v51 disallowTogglingRepeatTrackReasons_ = i.emptyProtobufList();
    private v51 disallowTogglingShuffleReasons_ = i.emptyProtobufList();
    private v51 disallowSetQueueReasons_ = i.emptyProtobufList();
    private v51 disallowInterruptingPlaybackReasons_ = i.emptyProtobufList();
    private v51 disallowTransferringPlaybackReasons_ = i.emptyProtobufList();
    private v51 disallowRemoteControlReasons_ = i.emptyProtobufList();
    private v51 disallowInsertingIntoNextTracksReasons_ = i.emptyProtobufList();
    private v51 disallowInsertingIntoContextTracksReasons_ = i.emptyProtobufList();
    private v51 disallowReorderingInNextTracksReasons_ = i.emptyProtobufList();
    private v51 disallowReorderingInContextTracksReasons_ = i.emptyProtobufList();
    private v51 disallowRemovingFromNextTracksReasons_ = i.emptyProtobufList();
    private v51 disallowRemovingFromContextTracksReasons_ = i.emptyProtobufList();
    private v51 disallowUpdatingContextReasons_ = i.emptyProtobufList();
    private v51 disallowAddToQueueReasons_ = i.emptyProtobufList();
    private v51 disallowSettingPlaybackSpeedReasons_ = i.emptyProtobufList();
    private v51 disallowSettingModeValueReasons_ = i.emptyProtobufList();

    static {
        EsRestrictions$Restrictions esRestrictions$Restrictions = new EsRestrictions$Restrictions();
        DEFAULT_INSTANCE = esRestrictions$Restrictions;
        i.registerDefaultInstance(EsRestrictions$Restrictions.class, esRestrictions$Restrictions);
    }

    private EsRestrictions$Restrictions() {
    }

    public static void A(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowInsertingIntoContextTracksReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowInsertingIntoContextTracksReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowInsertingIntoContextTracksReasons_);
    }

    public static EsRestrictions$Restrictions B() {
        return DEFAULT_INSTANCE;
    }

    public static sg0 Z() {
        return (sg0) DEFAULT_INSTANCE.createBuilder();
    }

    public static void e(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowReorderingInNextTracksReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowReorderingInNextTracksReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowReorderingInNextTracksReasons_);
    }

    public static void f(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowReorderingInContextTracksReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowReorderingInContextTracksReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowReorderingInContextTracksReasons_);
    }

    public static void g(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowRemovingFromNextTracksReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowRemovingFromNextTracksReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowRemovingFromNextTracksReasons_);
    }

    public static void h(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowRemovingFromContextTracksReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowRemovingFromContextTracksReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowRemovingFromContextTracksReasons_);
    }

    public static void i(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowUpdatingContextReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowUpdatingContextReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowUpdatingContextReasons_);
    }

    public static void j(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowAddToQueueReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowAddToQueueReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowAddToQueueReasons_);
    }

    public static void k(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowSettingPlaybackSpeedReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowSettingPlaybackSpeedReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowSettingPlaybackSpeedReasons_);
    }

    public static void l(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowPausingReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowPausingReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowPausingReasons_);
    }

    public static void m(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowResumingReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowResumingReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowResumingReasons_);
    }

    public static void n(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowSeekingReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowSeekingReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowSeekingReasons_);
    }

    public static void o(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowPeekingPrevReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowPeekingPrevReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowPeekingPrevReasons_);
    }

    public static void p(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowPeekingNextReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowPeekingNextReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowPeekingNextReasons_);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    public static void q(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowSkippingPrevReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowSkippingPrevReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowSkippingPrevReasons_);
    }

    public static void r(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowSkippingNextReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowSkippingNextReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowSkippingNextReasons_);
    }

    public static void s(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowTogglingRepeatContextReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowTogglingRepeatContextReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowTogglingRepeatContextReasons_);
    }

    public static void t(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowTogglingRepeatTrackReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowTogglingRepeatTrackReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowTogglingRepeatTrackReasons_);
    }

    public static void u(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowTogglingShuffleReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowTogglingShuffleReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowTogglingShuffleReasons_);
    }

    public static void v(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowSetQueueReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowSetQueueReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowSetQueueReasons_);
    }

    public static void w(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowInterruptingPlaybackReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowInterruptingPlaybackReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowInterruptingPlaybackReasons_);
    }

    public static void x(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowTransferringPlaybackReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowTransferringPlaybackReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowTransferringPlaybackReasons_);
    }

    public static void y(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowRemoteControlReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowRemoteControlReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowRemoteControlReasons_);
    }

    public static void z(EsRestrictions$Restrictions esRestrictions$Restrictions, ImmutableSet immutableSet) {
        v51 v51Var = esRestrictions$Restrictions.disallowInsertingIntoNextTracksReasons_;
        if (!((a2) v51Var).c) {
            esRestrictions$Restrictions.disallowInsertingIntoNextTracksReasons_ = i.mutableCopy(v51Var);
        }
        com.google.protobuf.a.addAll((Iterable) immutableSet, (List) esRestrictions$Restrictions.disallowInsertingIntoNextTracksReasons_);
    }

    public final v51 C() {
        return this.disallowAddToQueueReasons_;
    }

    public final v51 D() {
        return this.disallowInsertingIntoContextTracksReasons_;
    }

    public final v51 E() {
        return this.disallowInsertingIntoNextTracksReasons_;
    }

    public final v51 F() {
        return this.disallowInterruptingPlaybackReasons_;
    }

    public final v51 G() {
        return this.disallowPausingReasons_;
    }

    public final v51 H() {
        return this.disallowPeekingNextReasons_;
    }

    public final v51 I() {
        return this.disallowPeekingPrevReasons_;
    }

    public final v51 J() {
        return this.disallowRemoteControlReasons_;
    }

    public final v51 K() {
        return this.disallowRemovingFromContextTracksReasons_;
    }

    public final v51 L() {
        return this.disallowRemovingFromNextTracksReasons_;
    }

    public final v51 M() {
        return this.disallowReorderingInContextTracksReasons_;
    }

    public final v51 N() {
        return this.disallowReorderingInNextTracksReasons_;
    }

    public final v51 O() {
        return this.disallowResumingReasons_;
    }

    public final v51 P() {
        return this.disallowSeekingReasons_;
    }

    public final v51 Q() {
        return this.disallowSetQueueReasons_;
    }

    public final v51 R() {
        return this.disallowSettingPlaybackSpeedReasons_;
    }

    public final v51 S() {
        return this.disallowSkippingNextReasons_;
    }

    public final v51 T() {
        return this.disallowSkippingPrevReasons_;
    }

    public final v51 U() {
        return this.disallowTogglingRepeatContextReasons_;
    }

    public final v51 V() {
        return this.disallowTogglingRepeatTrackReasons_;
    }

    public final v51 W() {
        return this.disallowTogglingShuffleReasons_;
    }

    public final v51 X() {
        return this.disallowTransferringPlaybackReasons_;
    }

    public final v51 Y() {
        return this.disallowUpdatingContextReasons_;
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0018\u0000\u0000\u0001\u0018\u0018\u0000\u0018\u0000\u0001Ț\u0002Ț\u0003Ț\u0004Ț\u0005Ț\u0006Ț\u0007Ț\bȚ\tȚ\nȚ\u000bȚ\fȚ\rȚ\u000eȚ\u000fȚ\u0010Ț\u0011Ț\u0012Ț\u0013Ț\u0014Ț\u0015Ț\u0016Ț\u0017Ț\u0018\u001b", new Object[]{"disallowPausingReasons_", "disallowResumingReasons_", "disallowSeekingReasons_", "disallowPeekingPrevReasons_", "disallowPeekingNextReasons_", "disallowSkippingPrevReasons_", "disallowSkippingNextReasons_", "disallowTogglingRepeatContextReasons_", "disallowTogglingRepeatTrackReasons_", "disallowTogglingShuffleReasons_", "disallowSetQueueReasons_", "disallowInterruptingPlaybackReasons_", "disallowTransferringPlaybackReasons_", "disallowRemoteControlReasons_", "disallowInsertingIntoNextTracksReasons_", "disallowInsertingIntoContextTracksReasons_", "disallowReorderingInNextTracksReasons_", "disallowReorderingInContextTracksReasons_", "disallowRemovingFromNextTracksReasons_", "disallowRemovingFromContextTracksReasons_", "disallowUpdatingContextReasons_", "disallowAddToQueueReasons_", "disallowSettingPlaybackSpeedReasons_", "disallowSettingModeValueReasons_", EsRestrictions$ModeRestrictions.class});
            case 3:
                return new EsRestrictions$Restrictions();
            case 4:
                return new sg0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsRestrictions$Restrictions.class) {
                        try {
                            i01Var = PARSER;
                            if (i01Var == null) {
                                i01Var = new i01(DEFAULT_INSTANCE);
                                PARSER = i01Var;
                            }
                        } finally {
                        }
                        break;
                    }
                }
                return i01Var;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
