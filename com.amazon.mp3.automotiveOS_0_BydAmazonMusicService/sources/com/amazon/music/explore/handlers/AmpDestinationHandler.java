package com.amazon.music.explore.handlers;

import android.content.Context;
import com.amazon.music.destination.AmpDestination;
import com.amazon.music.explore.ExploreFactory;
import com.amazon.music.router.AsyncDestinationHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AmpDestinationHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002H\u0016J\"\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/explore/handlers/AmpDestinationHandler;", "Lcom/amazon/music/router/AsyncDestinationHandler;", "Lcom/amazon/music/destination/AmpDestination;", "()V", "navigateTo", "", "context", "Landroid/content/Context;", "ampDestination", "onNavigatedCallback", "Ljava/lang/Runnable;", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AmpDestinationHandler implements AsyncDestinationHandler<AmpDestination> {
    @Override // com.amazon.music.router.AsyncDestinationHandler
    public void navigateTo(Context context, AmpDestination ampDestination, Runnable onNavigatedCallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(ampDestination, "ampDestination");
        ExploreFactory.openAmpPlayback(context, ampDestination.getShowId(), ampDestination.getRef(), ampDestination.getTag());
        if (onNavigatedCallback == null) {
            return;
        }
        onNavigatedCallback.run();
    }

    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(Context context, AmpDestination ampDestination) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(ampDestination, "ampDestination");
        navigateTo(context, ampDestination, (Runnable) null);
    }
}
