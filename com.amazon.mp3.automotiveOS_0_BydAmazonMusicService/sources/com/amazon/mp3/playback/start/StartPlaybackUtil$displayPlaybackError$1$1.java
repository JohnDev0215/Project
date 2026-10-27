package com.amazon.mp3.playback.start;

import android.content.Context;
import com.amazon.mp3.playback.PlaybackExceptions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StartPlaybackUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* synthetic */ class StartPlaybackUtil$displayPlaybackError$1$1 extends FunctionReferenceImpl implements Function2<Context, PlaybackExceptions.Exceptions, Unit> {
    StartPlaybackUtil$displayPlaybackError$1$1(Object obj) {
        super(2, obj, StartPlaybackUtil.class, "displayPlaybackDialogHelper", "displayPlaybackDialogHelper(Landroid/content/Context;Lcom/amazon/mp3/playback/PlaybackExceptions$Exceptions;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo4invoke(Context context, PlaybackExceptions.Exceptions exceptions) {
        invoke2(context, exceptions);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Context p0, PlaybackExceptions.Exceptions p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        ((StartPlaybackUtil) this.receiver).displayPlaybackDialogHelper(p0, p1);
    }
}
