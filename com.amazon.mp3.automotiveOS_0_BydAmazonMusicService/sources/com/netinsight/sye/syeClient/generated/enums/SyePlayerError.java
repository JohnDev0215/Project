package com.netinsight.sye.syeClient.generated.enums;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b$\b\u0086\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0003B\u0011\b\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tR\u0019\u0010\u0007\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%¨\u0006&"}, d2 = {"Lcom/netinsight/sye/syeClient/generated/enums/SyePlayerError;", "", "", "a", "I", "getValue", "()I", "value", "<init>", "(Ljava/lang/String;II)V", "Companion", "Forbidden", "AuthServerError", "BadRequest", "ChannelNotFound", "NoPitcher", "TimeshiftOutOfRange", "UnexpectedStatus", "ServerError", "UnsupportedProtocol", "UnsupportedVersion", "CommunicationTimeout", "UnknownChannel", "Exception", "ReallocateStream", "DRMNoKeyError", "DRMError", "NetworkError", "BadInput", "UnsupportedVideoFormat", "UnsupportedAudioFormat", "ApplicationSuspended", "PlatformException", "PlayAttemptFailed", "TryAgain", "MediaTimeout", "UDPTimeout", "PlayingStateTimeout", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public enum SyePlayerError {
    Forbidden(1),
    AuthServerError(2),
    BadRequest(3),
    ChannelNotFound(4),
    NoPitcher(5),
    TimeshiftOutOfRange(6),
    UnexpectedStatus(7),
    ServerError(8),
    UnsupportedProtocol(9),
    UnsupportedVersion(10),
    CommunicationTimeout(11),
    UnknownChannel(12),
    Exception(13),
    ReallocateStream(14),
    DRMNoKeyError(15),
    DRMError(16),
    NetworkError(17),
    BadInput(18),
    UnsupportedVideoFormat(19),
    UnsupportedAudioFormat(20),
    ApplicationSuspended(21),
    PlatformException(22),
    PlayAttemptFailed(23),
    TryAgain(24),
    MediaTimeout(25),
    UDPTimeout(26),
    PlayingStateTimeout(27);


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion();

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public final int value;

    /* JADX INFO: renamed from: com.netinsight.sye.syeClient.generated.enums.SyePlayerError$a, reason: from kotlin metadata */
    public static final class Companion {
        @JvmStatic
        public final SyePlayerError a(int i) {
            switch (i) {
                case 1:
                    return SyePlayerError.Forbidden;
                case 2:
                    return SyePlayerError.AuthServerError;
                case 3:
                    return SyePlayerError.BadRequest;
                case 4:
                    return SyePlayerError.ChannelNotFound;
                case 5:
                    return SyePlayerError.NoPitcher;
                case 6:
                    return SyePlayerError.TimeshiftOutOfRange;
                case 7:
                    return SyePlayerError.UnexpectedStatus;
                case 8:
                    return SyePlayerError.ServerError;
                case 9:
                    return SyePlayerError.UnsupportedProtocol;
                case 10:
                    return SyePlayerError.UnsupportedVersion;
                case 11:
                    return SyePlayerError.CommunicationTimeout;
                case 12:
                    return SyePlayerError.UnknownChannel;
                case 13:
                    return SyePlayerError.Exception;
                case 14:
                    return SyePlayerError.ReallocateStream;
                case 15:
                    return SyePlayerError.DRMNoKeyError;
                case 16:
                    return SyePlayerError.DRMError;
                case 17:
                    return SyePlayerError.NetworkError;
                case 18:
                    return SyePlayerError.BadInput;
                case 19:
                    return SyePlayerError.UnsupportedVideoFormat;
                case 20:
                    return SyePlayerError.UnsupportedAudioFormat;
                case 21:
                    return SyePlayerError.ApplicationSuspended;
                case 22:
                    return SyePlayerError.PlatformException;
                case 23:
                    return SyePlayerError.PlayAttemptFailed;
                case 24:
                    return SyePlayerError.TryAgain;
                case 25:
                    return SyePlayerError.MediaTimeout;
                case 26:
                    return SyePlayerError.UDPTimeout;
                case 27:
                    return SyePlayerError.PlayingStateTimeout;
                default:
                    throw new IllegalArgumentException("No SyePlayerError with value " + i);
            }
        }
    }

    SyePlayerError(int i) {
        this.value = i;
    }

    @JvmStatic
    public static final <T extends Collection<SyePlayerError>> T parseJsonArray(String str, T t) {
        Companion companion = INSTANCE;
        companion.getClass();
        JSONArray jSONArray = new JSONArray(str);
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            t.add(companion.a(jSONArray.getInt(i)));
        }
        return t;
    }

    @JvmStatic
    public static final SyePlayerError parseValue(int i) {
        return INSTANCE.a(i);
    }

    public final int getValue() {
        return this.value;
    }
}
