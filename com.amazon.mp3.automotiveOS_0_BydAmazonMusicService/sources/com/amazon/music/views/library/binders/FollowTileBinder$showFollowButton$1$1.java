package com.amazon.music.views.library.binders;

import com.amazon.music.views.library.models.FollowTileModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: FollowTileBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* synthetic */ class FollowTileBinder$showFollowButton$1$1 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
    FollowTileBinder$showFollowButton$1$1(Object obj) {
        super(1, obj, FollowTileModel.class, "updateFollowState", "updateFollowState(Z)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
        invoke(bool.booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(boolean z) {
        ((FollowTileModel) this.receiver).updateFollowState(z);
    }
}
