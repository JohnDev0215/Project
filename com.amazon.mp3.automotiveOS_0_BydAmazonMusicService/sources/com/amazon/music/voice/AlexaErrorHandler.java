package com.amazon.music.voice;

import android.content.Context;
import android.os.Handler;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.voice.ui.AlexaUiListener;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.http2.StreamResetException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class AlexaErrorHandler extends AlexaUiListener.SimpleAlexaUiListener {
    private AlexaAccessProvider alexaAccessProvider;
    private AlexaEntryPoint alexaEntryPoint = AlexaEntryPoint.UNKNOWN;
    private AlexaUiController alexaUiController;
    private Context context;
    private static final Logger LOG = LoggerFactory.getLogger(AlexaErrorHandler.class.getSimpleName());
    private static final long CLOSE_UI_DELAY = TimeUnit.SECONDS.toMillis(3);

    public enum AlexaErrorType {
        GET_TRACK_AND_QUEUE_METADATA_ERROR,
        GET_ALEXA_HINTS_ERROR,
        MAP_ERROR,
        ON_SPEECH_RECOGNITION_ERROR,
        ON_SPEECH_SYNTHESIZE_ERROR,
        NETWORK_CONNECTION_ERROR,
        GENERIC_ERROR,
        ON_NO_DIRECTIVE_RECEIVED_TIMEOUT
    }

    public AlexaErrorHandler(Context context, AlexaUiController alexaUiController) {
        this.context = context;
        this.alexaUiController = alexaUiController;
    }

    public boolean hasNetworkConnectionAndShowErrorMessage() {
        AlexaAccessProvider alexaAccessProvider = this.alexaAccessProvider;
        if (alexaAccessProvider == null) {
            LOG.error("AlexaAccessProvider is null. Unable to check for a valid network connection. Allowing the user to access Alexa with the possibility of no network connectivity.");
            return true;
        }
        if (alexaAccessProvider.hasAnyNetworkConnection(this.context)) {
            return true;
        }
        handleError(AlexaErrorType.NETWORK_CONNECTION_ERROR);
        return false;
    }

    public void setAlexaAccessProvider(AlexaAccessProvider alexaAccessProvider) {
        this.alexaAccessProvider = alexaAccessProvider;
    }

    public void handleError(AlexaErrorType alexaErrorType) {
        handleError(alexaErrorType, null);
    }

    public void handleError(final AlexaErrorType alexaErrorType, final Throwable th) {
        new Handler(this.context.getMainLooper()).post(new Runnable() { // from class: com.amazon.music.voice.AlexaErrorHandler.1
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            @Override // java.lang.Runnable
            public void run() {
                String str;
                AlexaErrorHandler.this.alexaUiController.stopAllAnimations();
                switch (AnonymousClass3.$SwitchMap$com$amazon$music$voice$AlexaErrorHandler$AlexaErrorType[alexaErrorType.ordinal()]) {
                    case 1:
                        AlexaErrorHandler.this.alexaUiController.toggleGenericErrorMessageVisibility(true);
                        str = "alexaGenericError";
                        break;
                    case 2:
                        str = "alexaGenericError";
                        break;
                    case 3:
                        AlexaErrorHandler.LOG.error("Alexa Map Error", th);
                        AlexaErrorHandler.this.alexaUiController.toggleGenericErrorMessageVisibility(true);
                        str = "alexaGenericError";
                        break;
                    case 4:
                        AlexaErrorHandler.LOG.error("Recognize speech failed", th);
                        if (!(th instanceof StreamResetException)) {
                            AlexaErrorHandler.this.alexaUiController.toggleGenericErrorMessageVisibility(true);
                        } else if (AlexaErrorHandler.this.hasNetworkConnectionAndShowErrorMessage()) {
                            AlexaErrorHandler.this.alexaUiController.softCloseAlexaUi();
                        }
                        str = "alexaGenericError";
                        break;
                    case 5:
                        AlexaErrorHandler.this.alexaUiController.softCloseAlexaUi();
                        str = "alexaTimeout";
                        break;
                    case 6:
                        AlexaErrorHandler.LOG.error("Speech Synthesize failed", th);
                        AlexaErrorHandler.this.alexaUiController.toggleGenericErrorMessageVisibility(true);
                        str = "alexaGenericError";
                        break;
                    case 7:
                        AlexaErrorHandler.this.alexaUiController.toggleNetworkMessageVisibility(true);
                        str = "alexaNetworkError";
                        break;
                    default:
                        AlexaErrorHandler.this.alexaUiController.toggleGenericErrorMessageVisibility(true);
                        str = "alexaGenericError";
                        break;
                }
                MetricsHolder.getManager().handleEvent(FlexEvent.builder("alexaErrorView").withFlexStr1(str).build());
                AlexaErrorHandler.this.closeAlexaUiForWakeWord();
            }
        });
    }

    /* JADX INFO: renamed from: com.amazon.music.voice.AlexaErrorHandler$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$voice$AlexaErrorHandler$AlexaErrorType;

        static {
            int[] iArr = new int[AlexaErrorType.values().length];
            $SwitchMap$com$amazon$music$voice$AlexaErrorHandler$AlexaErrorType = iArr;
            try {
                iArr[AlexaErrorType.GET_TRACK_AND_QUEUE_METADATA_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$AlexaErrorHandler$AlexaErrorType[AlexaErrorType.GET_ALEXA_HINTS_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$AlexaErrorHandler$AlexaErrorType[AlexaErrorType.MAP_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$AlexaErrorHandler$AlexaErrorType[AlexaErrorType.ON_SPEECH_RECOGNITION_ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$AlexaErrorHandler$AlexaErrorType[AlexaErrorType.ON_NO_DIRECTIVE_RECEIVED_TIMEOUT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$AlexaErrorHandler$AlexaErrorType[AlexaErrorType.ON_SPEECH_SYNTHESIZE_ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$AlexaErrorHandler$AlexaErrorType[AlexaErrorType.NETWORK_CONNECTION_ERROR.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$AlexaErrorHandler$AlexaErrorType[AlexaErrorType.GENERIC_ERROR.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    void closeAlexaUiForWakeWord() {
        if (AlexaEntryPoint.WAKE_WORD.equals(this.alexaEntryPoint)) {
            new Handler(this.context.getMainLooper()).postDelayed(new Runnable() { // from class: com.amazon.music.voice.AlexaErrorHandler.2
                @Override // java.lang.Runnable
                public void run() {
                    AlexaErrorHandler.this.alexaUiController.softCloseAlexaUi();
                }
            }, CLOSE_UI_DELAY);
        }
    }

    @Override // com.amazon.music.voice.ui.AlexaUiListener.SimpleAlexaUiListener, com.amazon.music.voice.ui.AlexaUiListener
    public void onAlexaUiClosed() {
        setAlexaEntryPoint(AlexaEntryPoint.UNKNOWN);
    }

    public void setAlexaEntryPoint(AlexaEntryPoint alexaEntryPoint) {
        this.alexaEntryPoint = alexaEntryPoint;
    }
}
