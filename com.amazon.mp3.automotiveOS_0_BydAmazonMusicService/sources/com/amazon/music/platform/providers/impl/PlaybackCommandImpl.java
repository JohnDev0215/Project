package com.amazon.music.platform.providers.impl;

import androidx.core.app.NotificationCompat;
import com.amazon.music.platform.playback.commands.PlaybackCommand;
import com.amazon.music.platform.playback.commands.PlaybackCommandEvent;
import com.amazon.music.platform.playback.commands.PlaybackCommandHandler;
import com.google.firebase.perf.util.Constants;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackCommandImpl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\"\u0010\u0007\u001a\u00020\u00068\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0007\u0010\t\"\u0004\b\n\u0010\u000bR\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/providers/impl/PlaybackCommandImpl;", "Lcom/amazon/music/platform/playback/commands/PlaybackCommand;", "Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvent;", NotificationCompat.CATEGORY_EVENT, "", "execute", "", Constants.ENABLE_DISABLE, "Z", "()Z", "setEnabled", "(Z)V", "", "Lcom/amazon/music/platform/playback/commands/PlaybackCommandHandler;", "handlers", "Ljava/util/List;", "getHandlers", "()Ljava/util/List;", "<init>", "(ZLjava/util/List;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class PlaybackCommandImpl implements PlaybackCommand {
    private final List<PlaybackCommandHandler> handlers;
    private boolean isEnabled;

    public PlaybackCommandImpl(boolean z, List<PlaybackCommandHandler> handlers) {
        Intrinsics.checkNotNullParameter(handlers, "handlers");
        this.isEnabled = z;
        this.handlers = handlers;
    }

    @Override // com.amazon.music.platform.playback.commands.PlaybackCommand
    public void execute(PlaybackCommandEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        Iterator<T> it = this.handlers.iterator();
        while (it.hasNext()) {
            ((PlaybackCommandHandler) it.next()).handle(event);
        }
    }
}
