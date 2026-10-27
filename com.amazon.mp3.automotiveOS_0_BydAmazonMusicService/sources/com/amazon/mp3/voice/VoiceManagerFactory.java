package com.amazon.mp3.voice;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.core.processor.superbowl.AccessTokenProvider;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.configuration.AlexaLocaleProvider;
import com.amazon.mp3.environment.MusicURL;
import com.amazon.mp3.navigation.ContextAwareDeeplinkManager;
import com.amazon.mp3.store.html5.map.MAPCookiesManager;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.voice.deeplink.VoiceDeeplinkResolver;
import com.amazon.mp3.voice.hints.AlexaHintsViewModelProviderImpl;
import com.amazon.mp3.voice.metrics.ClockMillisecondsTimeProvider;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.voice.AlexaErrorHandler;
import com.amazon.music.voice.AppSwipeOutListener;
import com.amazon.music.voice.VoiceManager;
import com.amazon.music.voice.VoiceManagerSingleton;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class VoiceManagerFactory {
    public static final String TAG = "VoiceManagerFactory";
    private static boolean sShouldRefresh = false;

    public static void initializeVoiceManager(final Context context) {
        sShouldRefresh = false;
        Validate.notNull(context);
        VoiceSpeechMessagesPlayerStreamManager voiceSpeechMessagesPlayerStreamManager = new VoiceSpeechMessagesPlayerStreamManager(context);
        VoiceTrackReceiver voiceTrackReceiver = new VoiceTrackReceiver(context, voiceSpeechMessagesPlayerStreamManager);
        VoicePlaystateProvider voicePlaystateProvider = new VoicePlaystateProvider();
        VoicePlaybackController voicePlaybackController = new VoicePlaybackController(context);
        VoicePlaybackEventReceiver voicePlaybackEventReceiver = new VoicePlaybackEventReceiver();
        AlexaAccessDelegator alexaAccessDelegator = new AlexaAccessDelegator();
        AlexaGUIPlaybackController alexaGUIPlaybackController = new AlexaGUIPlaybackController(context, new VoiceDeeplinkResolver());
        Locale locale = AlexaLocaleProvider.getLocale();
        AlexaUIStyleProviderImpl alexaUIStyleProviderImpl = new AlexaUIStyleProviderImpl();
        AlexaHintsViewModelProviderImpl alexaHintsViewModelProviderImpl = new AlexaHintsViewModelProviderImpl();
        AppSwipeOutListener appSwipeOutListener = getAppSwipeOutListener();
        MusicPlayerStateProvider musicPlayerStateProvider = new MusicPlayerStateProvider(context);
        VoiceManager.Builder builderAlexaHintsViewModelProvider = new VoiceManager.Builder().context(context).contextAwareDeeplinkDelegate(new ContextAwareDeeplinkManager(DeeplinksManager.get(context), context)).enableNonCriticalLogging(false).accessTokenProvider(getAccessTokenProvider()).endpoint(getAvsUrl()).locale(locale).alexaTrackReceiver(voiceTrackReceiver).alexaVolumeController(new VoiceVolumeController()).alexaPlaystateProvider(voicePlaystateProvider).alexaPlaybackController(voicePlaybackController).alexaAccessProvider(alexaAccessDelegator).guiPlaybackController(alexaGUIPlaybackController).playerStateProvider(musicPlayerStateProvider).systemTimeProvider(new ClockMillisecondsTimeProvider()).empPlaybackController(alexaGUIPlaybackController).enableAlexaEmpPlayback(AmazonApplication.getCapabilities().isAlexaEmpPlaybackEnabled()).alexaUIStyleProvider(alexaUIStyleProviderImpl).alexaHintsViewModelProvider(alexaHintsViewModelProviderImpl);
        builderAlexaHintsViewModelProvider.addAppSwipeOutListener(voiceTrackReceiver);
        builderAlexaHintsViewModelProvider.addAppSwipeOutListener(voicePlaystateProvider);
        builderAlexaHintsViewModelProvider.addAppSwipeOutListener(voicePlaybackEventReceiver);
        builderAlexaHintsViewModelProvider.addAppSwipeOutListener(appSwipeOutListener);
        builderAlexaHintsViewModelProvider.addAppSwipeOutListener(musicPlayerStateProvider);
        VoiceManagerSingleton.initialize(builderAlexaHintsViewModelProvider);
        VoiceManager voiceManagerSingleton = VoiceManagerSingleton.getInstance();
        Validate.notNull(voiceManagerSingleton);
        voicePlaystateProvider.attach();
        voicePlaybackEventReceiver.attach(voiceManagerSingleton.getPlaybackEventProcessor());
        voiceManagerSingleton.addAlexaSpeechMessagesPlayerCallback(voiceSpeechMessagesPlayerStreamManager.getSimpleCallback());
        Log.info(TAG, "VoiceManagerSingleton is initialized.");
    }

    public static void forceRefresh() {
        sShouldRefresh = true;
    }

    public static boolean shouldRefresh() {
        return sShouldRefresh;
    }

    @NonNull
    private static AppSwipeOutListener getAppSwipeOutListener() {
        return new AppSwipeOutListener() { // from class: com.amazon.mp3.voice.VoiceManagerFactory.1
            @Override // com.amazon.music.voice.AppSwipeOutListener
            public void onAppRestart(final Context context) {
            }
        };
    }

    private static String getAvsUrl() {
        return MusicURL.getAlexaVoiceServiceUrl() != null ? MusicURL.getAlexaVoiceServiceUrl().toString() : "https://avs-alexa-na.amazon.com";
    }

    @NonNull
    private static AccessTokenProvider getAccessTokenProvider() {
        return new AccessTokenProvider() { // from class: com.amazon.mp3.voice.VoiceManagerFactory.2
            @Override // com.amazon.alexa.voice.core.processor.superbowl.AccessTokenProvider
            public String getAccessToken() {
                try {
                    return MAPCookiesManager.getCookiesManager().getToken();
                } catch (MAPCallbackErrorException e) {
                    e = e;
                    Log.error(VoiceManagerFactory.TAG, "Unable to get an Access Token: " + e.getMessage(), e);
                    if (VoiceManagerSingleton.isInitialized() && AmazonApplication.isApplicationForeground()) {
                        VoiceManagerSingleton.getInstance().getAlexaErrorHandler().handleError(AlexaErrorHandler.AlexaErrorType.MAP_ERROR, e);
                    }
                    return null;
                } catch (InterruptedException e2) {
                    Log.debug(VoiceManagerFactory.TAG, "getAccessToken received an interruptedException: " + e2.getMessage(), e2);
                    return null;
                } catch (ExecutionException e3) {
                    e = e3;
                    Log.error(VoiceManagerFactory.TAG, "Unable to get an Access Token: " + e.getMessage(), e);
                    if (VoiceManagerSingleton.isInitialized()) {
                        VoiceManagerSingleton.getInstance().getAlexaErrorHandler().handleError(AlexaErrorHandler.AlexaErrorType.MAP_ERROR, e);
                    }
                    return null;
                }
            }
        };
    }
}
