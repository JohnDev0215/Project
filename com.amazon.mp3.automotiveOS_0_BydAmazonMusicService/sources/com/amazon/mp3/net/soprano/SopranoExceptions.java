package com.amazon.mp3.net.soprano;

import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.util.Log;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: SopranoExceptions.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0007\r\u000e\u000f\u0010\u0011\u0012\u0013B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/net/soprano/SopranoExceptions;", "", "()V", "EXCEPTION_TYPE_MAP", "", "", "Lcom/amazon/mp3/net/service/ServiceException;", "TAG", "kotlin.jvm.PlatformType", "validateCoralResponse", "", "sopranoHttpClient", "Lcom/amazon/mp3/net/soprano/SopranoHttpClient;", "AccountNotVerifiedException", "BadRequestException", "ChildAlreadySubscribedException", "DependencyException", "IneligibleOperationException", "InvalidSubscriptionException", "SharingIneligibleException", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SopranoExceptions {
    private static Map<String, ServiceException> EXCEPTION_TYPE_MAP;
    public static final SopranoExceptions INSTANCE;
    private static final String TAG;

    private SopranoExceptions() {
    }

    static {
        SopranoExceptions sopranoExceptions = new SopranoExceptions();
        INSTANCE = sopranoExceptions;
        EXCEPTION_TYPE_MAP = new LinkedHashMap();
        TAG = sopranoExceptions.getClass().getSimpleName();
        EXCEPTION_TYPE_MAP.put("1100", InvalidSubscriptionException.INSTANCE);
        EXCEPTION_TYPE_MAP.put("1101", SharingIneligibleException.INSTANCE);
        EXCEPTION_TYPE_MAP.put("1102", DependencyException.INSTANCE);
        EXCEPTION_TYPE_MAP.put("1103", ChildAlreadySubscribedException.INSTANCE);
        EXCEPTION_TYPE_MAP.put("1104", IneligibleOperationException.INSTANCE);
        EXCEPTION_TYPE_MAP.put("1105", BadRequestException.INSTANCE);
        EXCEPTION_TYPE_MAP.put("1106", AccountNotVerifiedException.INSTANCE);
    }

    public final void validateCoralResponse(SopranoHttpClient sopranoHttpClient) {
        Intrinsics.checkNotNullParameter(sopranoHttpClient, "sopranoHttpClient");
        try {
            if (sopranoHttpClient.getResult() == null) {
                return;
            }
            JSONObject result = sopranoHttpClient.getResult();
            Log.verbose(TAG, Intrinsics.stringPlus("Service returned result: ", result));
            if (result.opt("exceptionCode") != null) {
                String string = result.getString("exceptionCode");
                ServiceException serviceException = string == null ? null : EXCEPTION_TYPE_MAP.get(string);
                if (serviceException != null) {
                    throw serviceException;
                }
            }
            if (result.opt("__type") != null && result.get("__type").equals("com.amazon.coral.service#InternalFailure")) {
                throw new Exception("Service Internal Failure");
            }
        } catch (Exception e) {
            Log.error(TAG, Intrinsics.stringPlus("Service returned exception ", e));
            throw e;
        }
    }

    /* JADX INFO: compiled from: SopranoExceptions.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/net/soprano/SopranoExceptions$InvalidSubscriptionException;", "Lcom/amazon/mp3/net/service/ServiceException;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class InvalidSubscriptionException extends ServiceException {
        public static final InvalidSubscriptionException INSTANCE = new InvalidSubscriptionException();

        private InvalidSubscriptionException() {
        }
    }

    /* JADX INFO: compiled from: SopranoExceptions.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/net/soprano/SopranoExceptions$SharingIneligibleException;", "Lcom/amazon/mp3/net/service/ServiceException;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class SharingIneligibleException extends ServiceException {
        public static final SharingIneligibleException INSTANCE = new SharingIneligibleException();

        private SharingIneligibleException() {
        }
    }

    /* JADX INFO: compiled from: SopranoExceptions.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/net/soprano/SopranoExceptions$DependencyException;", "Lcom/amazon/mp3/net/service/ServiceException;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class DependencyException extends ServiceException {
        public static final DependencyException INSTANCE = new DependencyException();

        private DependencyException() {
        }
    }

    /* JADX INFO: compiled from: SopranoExceptions.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/net/soprano/SopranoExceptions$ChildAlreadySubscribedException;", "Lcom/amazon/mp3/net/service/ServiceException;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class ChildAlreadySubscribedException extends ServiceException {
        public static final ChildAlreadySubscribedException INSTANCE = new ChildAlreadySubscribedException();

        private ChildAlreadySubscribedException() {
        }
    }

    /* JADX INFO: compiled from: SopranoExceptions.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/net/soprano/SopranoExceptions$IneligibleOperationException;", "Lcom/amazon/mp3/net/service/ServiceException;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class IneligibleOperationException extends ServiceException {
        public static final IneligibleOperationException INSTANCE = new IneligibleOperationException();

        private IneligibleOperationException() {
        }
    }

    /* JADX INFO: compiled from: SopranoExceptions.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/net/soprano/SopranoExceptions$BadRequestException;", "Lcom/amazon/mp3/net/service/ServiceException;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class BadRequestException extends ServiceException {
        public static final BadRequestException INSTANCE = new BadRequestException();

        private BadRequestException() {
        }
    }

    /* JADX INFO: compiled from: SopranoExceptions.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/net/soprano/SopranoExceptions$AccountNotVerifiedException;", "Lcom/amazon/mp3/net/service/ServiceException;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class AccountNotVerifiedException extends ServiceException {
        public static final AccountNotVerifiedException INSTANCE = new AccountNotVerifiedException();

        private AccountNotVerifiedException() {
        }
    }
}
