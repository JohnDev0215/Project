package com.amazon.music.platform.playback.capabilities.error;

import com.amazon.music.platform.playback.capabilities.reduce.Prioritizable;
import com.visualon.OSMPUtils.voOSType;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EligibilityThrowable.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\t\t\n\u000b\f\r\u000e\u000f\u0010\u0011B\u000f\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0082\u0001\t\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable;", "", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityError;", "Lcom/amazon/music/platform/playback/capabilities/reduce/Prioritizable;", "priority", "", "(I)V", "getPriority", "()I", "AAUPChangeLimitsReached", "AAUPMaxPlaylistSize", "ContentUnavailable", "FeatureDisabled", "MissingAppStateEligibility", "MissingContentEligibility", "NoActiveSubscription", "NoNetwork", "UnexpectedFailure", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable$AAUPChangeLimitsReached;", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable$AAUPMaxPlaylistSize;", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable$ContentUnavailable;", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable$FeatureDisabled;", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable$MissingAppStateEligibility;", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable$MissingContentEligibility;", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable$NoActiveSubscription;", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable$NoNetwork;", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable$UnexpectedFailure;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public abstract class EligibilityThrowable extends Throwable implements EligibilityError, Prioritizable {
    private final int priority;

    public /* synthetic */ EligibilityThrowable(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    /* JADX INFO: compiled from: EligibilityThrowable.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096D¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable$FeatureDisabled;", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable;", "", "errorMessage", "Ljava/lang/String;", "getErrorMessage", "()Ljava/lang/String;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final class FeatureDisabled extends EligibilityThrowable {
        public static final FeatureDisabled INSTANCE = new FeatureDisabled();
        private static final String errorMessage = "Feature is not Enabled";

        private FeatureDisabled() {
            super(0, null);
        }
    }

    private EligibilityThrowable(int i) {
        this.priority = i;
    }

    @Override // com.amazon.music.platform.playback.capabilities.reduce.Prioritizable
    public int getPriority() {
        return this.priority;
    }

    /* JADX INFO: compiled from: EligibilityThrowable.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096D¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable$ContentUnavailable;", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable;", "", "errorMessage", "Ljava/lang/String;", "getErrorMessage", "()Ljava/lang/String;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final class ContentUnavailable extends EligibilityThrowable {
        public static final ContentUnavailable INSTANCE = new ContentUnavailable();
        private static final String errorMessage = "Content is not available";

        private ContentUnavailable() {
            super(20, null);
        }
    }

    /* JADX INFO: compiled from: EligibilityThrowable.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096D¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable$MissingContentEligibility;", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable;", "", "errorMessage", "Ljava/lang/String;", "getErrorMessage", "()Ljava/lang/String;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final class MissingContentEligibility extends EligibilityThrowable {
        public static final MissingContentEligibility INSTANCE = new MissingContentEligibility();
        private static final String errorMessage = "Missing Content Eligibility";

        private MissingContentEligibility() {
            super(40, null);
        }
    }

    /* JADX INFO: compiled from: EligibilityThrowable.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096D¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable$MissingAppStateEligibility;", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable;", "", "errorMessage", "Ljava/lang/String;", "getErrorMessage", "()Ljava/lang/String;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final class MissingAppStateEligibility extends EligibilityThrowable {
        public static final MissingAppStateEligibility INSTANCE = new MissingAppStateEligibility();
        private static final String errorMessage = "Missing AppState Eligibility";

        private MissingAppStateEligibility() {
            super(60, null);
        }
    }

    /* JADX INFO: compiled from: EligibilityThrowable.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096D¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable$NoActiveSubscription;", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable;", "", "errorMessage", "Ljava/lang/String;", "getErrorMessage", "()Ljava/lang/String;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final class NoActiveSubscription extends EligibilityThrowable {
        public static final NoActiveSubscription INSTANCE = new NoActiveSubscription();
        private static final String errorMessage = "Customer has no active subscription";

        private NoActiveSubscription() {
            super(80, null);
        }
    }

    /* JADX INFO: compiled from: EligibilityThrowable.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096D¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable$NoNetwork;", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable;", "", "errorMessage", "Ljava/lang/String;", "getErrorMessage", "()Ljava/lang/String;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final class NoNetwork extends EligibilityThrowable {
        public static final NoNetwork INSTANCE = new NoNetwork();
        private static final String errorMessage = "Network is not available";

        private NoNetwork() {
            super(100, null);
        }
    }

    /* JADX INFO: compiled from: EligibilityThrowable.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096D¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable$AAUPMaxPlaylistSize;", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable;", "", "errorMessage", "Ljava/lang/String;", "getErrorMessage", "()Ljava/lang/String;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final class AAUPMaxPlaylistSize extends EligibilityThrowable {
        public static final AAUPMaxPlaylistSize INSTANCE = new AAUPMaxPlaylistSize();
        private static final String errorMessage = "Playlist has max number of tracks for an AAUP";

        private AAUPMaxPlaylistSize() {
            super(120, null);
        }
    }

    /* JADX INFO: compiled from: EligibilityThrowable.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096D¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable$AAUPChangeLimitsReached;", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable;", "", "errorMessage", "Ljava/lang/String;", "getErrorMessage", "()Ljava/lang/String;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final class AAUPChangeLimitsReached extends EligibilityThrowable {
        public static final AAUPChangeLimitsReached INSTANCE = new AAUPChangeLimitsReached();
        private static final String errorMessage = "Playlist has max number of daily changes for an AAUP";

        private AAUPChangeLimitsReached() {
            super(voOSType.VOOSMP_PID_ANALYTICS_EXPORT_GET_PACKET, null);
        }
    }

    /* JADX INFO: compiled from: EligibilityThrowable.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bB\u0011\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\fJ\b\u0010\u0003\u001a\u00020\u0002H\u0016R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0004\u0010\u0005R\u001a\u0010\u0006\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u0005\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable$UnexpectedFailure;", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable;", "", "toString", "reason", "Ljava/lang/String;", "errorMessage", "getErrorMessage", "()Ljava/lang/String;", "", "<init>", "(Ljava/lang/Throwable;)V", "(Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final class UnexpectedFailure extends EligibilityThrowable {
        private final String errorMessage;
        private String reason;

        public UnexpectedFailure(Throwable th) {
            String strStackTraceToString;
            super(200, null);
            StringBuilder sb = new StringBuilder();
            sb.append("An unexpected failure occurred + ");
            sb.append((th == null || (strStackTraceToString = ExceptionsKt__ExceptionsKt.stackTraceToString(th)) == null) ? this.reason : strStackTraceToString);
            this.errorMessage = sb.toString();
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public UnexpectedFailure(String reason) {
            this((Throwable) null);
            Intrinsics.checkNotNullParameter(reason, "reason");
            this.reason = reason;
        }

        @Override // java.lang.Throwable
        public String toString() {
            return super.toString() + " Reason: " + this.reason;
        }
    }
}
