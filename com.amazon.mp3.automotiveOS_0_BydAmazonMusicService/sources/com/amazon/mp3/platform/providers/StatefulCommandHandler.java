package com.amazon.mp3.platform.providers;

import androidx.core.app.NotificationCompat;
import com.amazon.music.platform.playback.commands.PlaybackCommandEvent;
import com.amazon.music.platform.playback.commands.PlaybackCommandHandler;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StatefulCommandHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\u001e\u0010\n\u001a\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0004\u0012\u00020\u00010\u00070\u0006¢\u0006\u0004\b\u0010\u0010\u000fJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R:\u0010\n\u001a\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0004\u0012\u00020\u00010\u00070\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/platform/providers/StatefulCommandHandler;", "Lcom/amazon/music/platform/playback/commands/PlaybackCommandHandler;", "Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvent;", NotificationCompat.CATEGORY_EVENT, "", "handle", "", "Lkotlin/Pair;", "Lkotlin/Function0;", "", "handlers", "Ljava/util/List;", "getHandlers", "()Ljava/util/List;", "setHandlers", "(Ljava/util/List;)V", "<init>", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class StatefulCommandHandler implements PlaybackCommandHandler {
    private List<? extends Pair<? extends Function0<Boolean>, ? extends PlaybackCommandHandler>> handlers;

    public StatefulCommandHandler(List<? extends Pair<? extends Function0<Boolean>, ? extends PlaybackCommandHandler>> handlers) {
        Intrinsics.checkNotNullParameter(handlers, "handlers");
        this.handlers = handlers;
    }

    @Override // com.amazon.music.platform.playback.commands.PlaybackCommandHandler
    public void handle(PlaybackCommandEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        for (Pair<? extends Function0<Boolean>, ? extends PlaybackCommandHandler> pair : this.handlers) {
            if (pair.getFirst().invoke().booleanValue()) {
                pair.getSecond().handle(event);
            }
        }
    }
}
