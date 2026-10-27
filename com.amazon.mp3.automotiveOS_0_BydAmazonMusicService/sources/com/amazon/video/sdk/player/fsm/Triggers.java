package com.amazon.video.sdk.player.fsm;

import com.amazon.avod.core.CachedPresentationCallback;
import com.amazon.avod.fsm.Trigger;
import com.amazon.video.sdk.player.ContentConfig;
import com.amazon.video.sdk.player.RenderingConfig;
import com.google.android.gms.cast.MediaError;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Triggers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\t\u0003\u0004\u0005\u0006\u0007\b\t\n\u000bB\u0005¢\u0006\u0002\u0010\u0002¨\u0006\f"}, d2 = {"Lcom/amazon/video/sdk/player/fsm/Triggers;", "", "()V", "Destroy", "Error", "Idle", "Load", "Pause", "Play", "Seek", "Type", "Unload", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class Triggers {

    /* JADX INFO: compiled from: Triggers.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/video/sdk/player/fsm/Triggers$Type;", "", "(Ljava/lang/String;I)V", "LOAD", "PLAY", "PAUSE", "SEEK", "UNLOAD", "IDLE", "DESTROY", MediaError.ERROR_TYPE_ERROR, "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum Type {
        LOAD,
        PLAY,
        PAUSE,
        SEEK,
        UNLOAD,
        IDLE,
        DESTROY,
        ERROR
    }

    /* JADX INFO: compiled from: Triggers.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B+\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u0013\u001a\u00020\u0002H\u0016R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcom/amazon/video/sdk/player/fsm/Triggers$Load;", "Lcom/amazon/avod/fsm/Trigger;", "Lcom/amazon/video/sdk/player/fsm/Triggers$Type;", "content", "Lcom/amazon/video/sdk/player/ContentConfig;", "renderingConfig", "Lcom/amazon/video/sdk/player/RenderingConfig;", "isFollowedByPlaybackStart", "", "callback", "Lcom/amazon/avod/core/CachedPresentationCallback;", "(Lcom/amazon/video/sdk/player/fsm/Triggers;Lcom/amazon/video/sdk/player/ContentConfig;Lcom/amazon/video/sdk/player/RenderingConfig;ZLcom/amazon/avod/core/CachedPresentationCallback;)V", "getCallback", "()Lcom/amazon/avod/core/CachedPresentationCallback;", "getContent", "()Lcom/amazon/video/sdk/player/ContentConfig;", "()Z", "getRenderingConfig", "()Lcom/amazon/video/sdk/player/RenderingConfig;", "getType", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class Load implements Trigger<Type> {
        private final CachedPresentationCallback callback;
        private final ContentConfig content;
        private final boolean isFollowedByPlaybackStart;
        private final RenderingConfig renderingConfig;

        public Load(Triggers this$0, ContentConfig content, RenderingConfig renderingConfig, boolean z, CachedPresentationCallback cachedPresentationCallback) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(content, "content");
            Intrinsics.checkNotNullParameter(renderingConfig, "renderingConfig");
            Triggers.this = this$0;
            this.content = content;
            this.renderingConfig = renderingConfig;
            this.isFollowedByPlaybackStart = z;
            this.callback = cachedPresentationCallback;
        }

        public /* synthetic */ Load(ContentConfig contentConfig, RenderingConfig renderingConfig, boolean z, CachedPresentationCallback cachedPresentationCallback, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(Triggers.this, contentConfig, renderingConfig, (i & 4) != 0 ? false : z, (i & 8) != 0 ? null : cachedPresentationCallback);
        }

        public final ContentConfig getContent() {
            return this.content;
        }

        public final RenderingConfig getRenderingConfig() {
            return this.renderingConfig;
        }

        /* JADX INFO: renamed from: isFollowedByPlaybackStart, reason: from getter */
        public final boolean getIsFollowedByPlaybackStart() {
            return this.isFollowedByPlaybackStart;
        }

        public final CachedPresentationCallback getCallback() {
            return this.callback;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.fsm.Trigger
        public Type getType() {
            return Type.LOAD;
        }
    }

    /* JADX INFO: compiled from: Triggers.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\u0002H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/amazon/video/sdk/player/fsm/Triggers$Play;", "Lcom/amazon/avod/fsm/Trigger;", "Lcom/amazon/video/sdk/player/fsm/Triggers$Type;", "onSeekEnd", "", "(Lcom/amazon/video/sdk/player/fsm/Triggers;Z)V", "getOnSeekEnd", "()Z", "getType", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class Play implements Trigger<Type> {
        private final boolean onSeekEnd;

        public Play(Triggers this$0, boolean z) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Triggers.this = this$0;
            this.onSeekEnd = z;
        }

        public /* synthetic */ Play(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(Triggers.this, (i & 1) != 0 ? false : z);
        }

        public final boolean getOnSeekEnd() {
            return this.onSeekEnd;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.fsm.Trigger
        public Type getType() {
            return Type.PLAY;
        }
    }

    /* JADX INFO: compiled from: Triggers.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\u0002H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/amazon/video/sdk/player/fsm/Triggers$Pause;", "Lcom/amazon/avod/fsm/Trigger;", "Lcom/amazon/video/sdk/player/fsm/Triggers$Type;", "onSeekEnd", "", "(Lcom/amazon/video/sdk/player/fsm/Triggers;Z)V", "getOnSeekEnd", "()Z", "getType", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class Pause implements Trigger<Type> {
        private final boolean onSeekEnd;
        final /* synthetic */ Triggers this$0;

        public Pause(Triggers this$0, boolean z) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            this.onSeekEnd = z;
        }

        public final boolean getOnSeekEnd() {
            return this.onSeekEnd;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.fsm.Trigger
        public Type getType() {
            return Type.PAUSE;
        }
    }

    /* JADX INFO: compiled from: Triggers.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\u0002H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/amazon/video/sdk/player/fsm/Triggers$Seek;", "Lcom/amazon/avod/fsm/Trigger;", "Lcom/amazon/video/sdk/player/fsm/Triggers$Type;", "position", "", "(Lcom/amazon/video/sdk/player/fsm/Triggers;J)V", "getPosition", "()J", "getType", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class Seek implements Trigger<Type> {
        private final long position;
        final /* synthetic */ Triggers this$0;

        public Seek(Triggers this$0, long j) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            this.position = j;
        }

        public final long getPosition() {
            return this.position;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.fsm.Trigger
        public Type getType() {
            return Type.SEEK;
        }
    }

    /* JADX INFO: compiled from: Triggers.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u000b\u001a\u00020\u0002H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\bR\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lcom/amazon/video/sdk/player/fsm/Triggers$Unload;", "Lcom/amazon/avod/fsm/Trigger;", "Lcom/amazon/video/sdk/player/fsm/Triggers$Type;", "newContent", "Lcom/amazon/video/sdk/player/ContentConfig;", "isDestroyed", "", "(Lcom/amazon/video/sdk/player/fsm/Triggers;Lcom/amazon/video/sdk/player/ContentConfig;Z)V", "()Z", "getNewContent", "()Lcom/amazon/video/sdk/player/ContentConfig;", "getType", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class Unload implements Trigger<Type> {
        private final boolean isDestroyed;
        private final ContentConfig newContent;
        final /* synthetic */ Triggers this$0;

        public Unload(Triggers this$0, ContentConfig contentConfig, boolean z) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            this.newContent = contentConfig;
            this.isDestroyed = z;
        }

        public final ContentConfig getNewContent() {
            return this.newContent;
        }

        /* JADX INFO: renamed from: isDestroyed, reason: from getter */
        public final boolean getIsDestroyed() {
            return this.isDestroyed;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.fsm.Trigger
        public Type getType() {
            return Type.UNLOAD;
        }
    }

    /* JADX INFO: compiled from: Triggers.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0002H\u0016¨\u0006\u0005"}, d2 = {"Lcom/amazon/video/sdk/player/fsm/Triggers$Idle;", "Lcom/amazon/avod/fsm/Trigger;", "Lcom/amazon/video/sdk/player/fsm/Triggers$Type;", "(Lcom/amazon/video/sdk/player/fsm/Triggers;)V", "getType", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class Idle implements Trigger<Type> {
        final /* synthetic */ Triggers this$0;

        public Idle(Triggers this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.fsm.Trigger
        public Type getType() {
            return Type.IDLE;
        }
    }

    /* JADX INFO: compiled from: Triggers.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0002H\u0016¨\u0006\u0005"}, d2 = {"Lcom/amazon/video/sdk/player/fsm/Triggers$Destroy;", "Lcom/amazon/avod/fsm/Trigger;", "Lcom/amazon/video/sdk/player/fsm/Triggers$Type;", "(Lcom/amazon/video/sdk/player/fsm/Triggers;)V", "getType", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class Destroy implements Trigger<Type> {
        final /* synthetic */ Triggers this$0;

        public Destroy(Triggers this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.fsm.Trigger
        public Type getType() {
            return Type.DESTROY;
        }
    }

    /* JADX INFO: compiled from: Triggers.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0002H\u0016¨\u0006\u0005"}, d2 = {"Lcom/amazon/video/sdk/player/fsm/Triggers$Error;", "Lcom/amazon/avod/fsm/Trigger;", "Lcom/amazon/video/sdk/player/fsm/Triggers$Type;", "(Lcom/amazon/video/sdk/player/fsm/Triggers;)V", "getType", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class Error implements Trigger<Type> {
        final /* synthetic */ Triggers this$0;

        public Error(Triggers this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.avod.fsm.Trigger
        public Type getType() {
            return Type.ERROR;
        }
    }
}
