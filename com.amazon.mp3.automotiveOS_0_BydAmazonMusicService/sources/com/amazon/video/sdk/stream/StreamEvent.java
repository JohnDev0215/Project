package com.amazon.video.sdk.stream;

import com.amazon.identity.auth.device.utils.AccountConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StreamFeature.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/video/sdk/stream/StreamEvent;", "", "()V", "ActiveStreamChange", "ActiveVariantChange", "Lcom/amazon/video/sdk/stream/StreamEvent$ActiveStreamChange;", "Lcom/amazon/video/sdk/stream/StreamEvent$ActiveVariantChange;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class StreamEvent {
    public /* synthetic */ StreamEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private StreamEvent() {
    }

    /* JADX INFO: compiled from: StreamFeature.kt */
    @Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u000e\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u000f¢\u0006\u0004\b\u0015\u0010\u0016J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001f\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u000f8\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Lcom/amazon/video/sdk/stream/StreamEvent$ActiveStreamChange;", "Lcom/amazon/video/sdk/stream/StreamEvent;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/video/sdk/stream/StreamType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/video/sdk/stream/StreamType;", "getType", "()Lcom/amazon/video/sdk/stream/StreamType;", "Lcom/amazon/video/sdk/stream/Stream;", "Lcom/amazon/video/sdk/stream/Variant;", "activeStream", "Lcom/amazon/video/sdk/stream/Stream;", "getActiveStream", "()Lcom/amazon/video/sdk/stream/Stream;", "<init>", "(Lcom/amazon/video/sdk/stream/StreamType;Lcom/amazon/video/sdk/stream/Stream;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class ActiveStreamChange extends StreamEvent {
        private final Stream<? extends Variant> activeStream;
        private final StreamType type;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ActiveStreamChange)) {
                return false;
            }
            ActiveStreamChange activeStreamChange = (ActiveStreamChange) other;
            return this.type == activeStreamChange.type && Intrinsics.areEqual(this.activeStream, activeStreamChange.activeStream);
        }

        public int hashCode() {
            return (this.type.hashCode() * 31) + this.activeStream.hashCode();
        }

        public String toString() {
            return "ActiveStreamChange(type=" + this.type + ", activeStream=" + this.activeStream + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActiveStreamChange(StreamType type, Stream<? extends Variant> activeStream) {
            super(null);
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(activeStream, "activeStream");
            this.type = type;
            this.activeStream = activeStream;
        }
    }

    /* JADX INFO: compiled from: StreamFeature.kt */
    @Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/amazon/video/sdk/stream/StreamEvent$ActiveVariantChange;", "Lcom/amazon/video/sdk/stream/StreamEvent;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/video/sdk/stream/StreamType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/video/sdk/stream/StreamType;", "getType", "()Lcom/amazon/video/sdk/stream/StreamType;", "Lcom/amazon/video/sdk/stream/Variant;", "variant", "Lcom/amazon/video/sdk/stream/Variant;", "getVariant", "()Lcom/amazon/video/sdk/stream/Variant;", "<init>", "(Lcom/amazon/video/sdk/stream/StreamType;Lcom/amazon/video/sdk/stream/Variant;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class ActiveVariantChange extends StreamEvent {
        private final StreamType type;
        private final Variant variant;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ActiveVariantChange)) {
                return false;
            }
            ActiveVariantChange activeVariantChange = (ActiveVariantChange) other;
            return this.type == activeVariantChange.type && Intrinsics.areEqual(this.variant, activeVariantChange.variant);
        }

        public int hashCode() {
            return (this.type.hashCode() * 31) + this.variant.hashCode();
        }

        public String toString() {
            return "ActiveVariantChange(type=" + this.type + ", variant=" + this.variant + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActiveVariantChange(StreamType type, Variant variant) {
            super(null);
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(variant, "variant");
            this.type = type;
            this.variant = variant;
        }
    }
}
