package com.amazon.music.voice.emp.events;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: compiled from: EMPError.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/voice/emp/events/EMPError;", "", "(Ljava/lang/String;I)V", "getDescription", "", "getErrorCode", "", "isFatal", "", "OPERATION_UNSUPPORTED", "OPERATION_REJECTED_END_OF_QUEUE", "OPERATION_REJECTED_SKIP_LIMIT", "OPERATION_REJECTED_UNINTERRUPTIBLE", "UNPLAYABLE_BY_AUTHORIZATION", "UNPLAYABLE_BY_ACCOUNT", "UNPLAYABLE_BY_SUBSCRIPTION", "UNPLAYABLE_BY_STREAM_CONCURRENCY", "UNPLAYABLE_BY_REGION", "UNPLAYABLE_BY_PARENTAL_CONTROL", "INTERNAL_ERROR", "UNKNOWN_ERROR", "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum EMPError {
    OPERATION_UNSUPPORTED,
    OPERATION_REJECTED_END_OF_QUEUE,
    OPERATION_REJECTED_SKIP_LIMIT,
    OPERATION_REJECTED_UNINTERRUPTIBLE,
    UNPLAYABLE_BY_AUTHORIZATION,
    UNPLAYABLE_BY_ACCOUNT,
    UNPLAYABLE_BY_SUBSCRIPTION,
    UNPLAYABLE_BY_STREAM_CONCURRENCY,
    UNPLAYABLE_BY_REGION,
    UNPLAYABLE_BY_PARENTAL_CONTROL,
    INTERNAL_ERROR,
    UNKNOWN_ERROR;

    /* JADX INFO: compiled from: EMPError.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EMPError.values().length];
            iArr[EMPError.OPERATION_UNSUPPORTED.ordinal()] = 1;
            iArr[EMPError.OPERATION_REJECTED_END_OF_QUEUE.ordinal()] = 2;
            iArr[EMPError.OPERATION_REJECTED_SKIP_LIMIT.ordinal()] = 3;
            iArr[EMPError.OPERATION_REJECTED_UNINTERRUPTIBLE.ordinal()] = 4;
            iArr[EMPError.UNPLAYABLE_BY_AUTHORIZATION.ordinal()] = 5;
            iArr[EMPError.UNPLAYABLE_BY_ACCOUNT.ordinal()] = 6;
            iArr[EMPError.UNPLAYABLE_BY_SUBSCRIPTION.ordinal()] = 7;
            iArr[EMPError.UNPLAYABLE_BY_STREAM_CONCURRENCY.ordinal()] = 8;
            iArr[EMPError.UNPLAYABLE_BY_REGION.ordinal()] = 9;
            iArr[EMPError.UNPLAYABLE_BY_PARENTAL_CONTROL.ordinal()] = 10;
            iArr[EMPError.INTERNAL_ERROR.ordinal()] = 11;
            iArr[EMPError.UNKNOWN_ERROR.ordinal()] = 12;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final long getErrorCode() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                return 1000L;
            case 2:
                return 1100L;
            case 3:
                return 1200L;
            case 4:
                return 1300L;
            case 5:
                return 2000L;
            case 6:
                return 2100L;
            case 7:
                return 2300L;
            case 8:
                return 2400L;
            case 9:
                return 2500L;
            case 10:
                return 2600L;
            case 11:
                return 3000L;
            case 12:
                return 3100L;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final boolean isFatal() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        return i == 11 || i == 12;
    }

    public final String getDescription() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                return "Operation is not supported on current playing item";
            case 2:
                return "Reaching the end of current playback, no more item available";
            case 3:
                return "Skip request has been rejected because user reached skip limit";
            case 4:
                return "Operation is not allowed on current playing item";
            case 5:
                return "Content cannot be played because the user is not logged in";
            case 6:
                return "User account cannot be played";
            case 7:
                return "Requested content is not available for user subscription unless upgraded";
            case 8:
                return "User has reached maximum concurrent streams limit.";
            case 9:
                return "Requested content is not available in country/region";
            case 10:
                return "Requested content is not available when Parental Control is ON";
            case 11:
                return "Operation failed due to internal error";
            case 12:
                return "Request failed due to unknown Exception/Error";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
