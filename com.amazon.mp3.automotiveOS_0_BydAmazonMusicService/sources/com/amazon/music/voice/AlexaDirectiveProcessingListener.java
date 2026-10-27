package com.amazon.music.voice;

import com.amazon.alexa.voice.core.Directive;
import com.amazon.alexa.voice.core.processor.superbowl.directives.AudioClearQueueDirective;
import com.amazon.alexa.voice.core.processor.superbowl.directives.AudioPlayDirective;
import com.amazon.alexa.voice.core.processor.superbowl.directives.ExpectSpeechDirective;
import com.amazon.alexa.voice.core.processor.superbowl.directives.SynthesizeSpeechDirective;
import com.amazon.alexa.voice.superbowl.Voice;
import com.amazon.alexa.voice.superbowl.plugins.SpeechRecognizerPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class AlexaDirectiveProcessingListener extends Voice.SimpleDirectiveProcessingListener {
    private static final Logger LOG = LoggerFactory.getLogger(AlexaDirectiveProcessingListener.class.getSimpleName());
    private AlexaErrorHandler alexaErrorHandler;
    private AlexaUiController alexaUiController;
    private AudioPlayerQueueListener audioPlayerQueueListener;
    private boolean receivedExpectedSpeechDirective = false;
    private SpeechRecognizerPlugin speechRecognizerPlugin;

    public AlexaDirectiveProcessingListener(AlexaUiController alexaUiController, AlexaErrorHandler alexaErrorHandler, AudioPlayerQueueListener audioPlayerQueueListener, SpeechRecognizerPlugin speechRecognizerPlugin) {
        this.alexaUiController = alexaUiController;
        this.alexaErrorHandler = alexaErrorHandler;
        this.audioPlayerQueueListener = audioPlayerQueueListener;
        this.speechRecognizerPlugin = speechRecognizerPlugin;
    }

    @Override // com.amazon.alexa.voice.superbowl.Voice.SimpleDirectiveProcessingListener, com.amazon.alexa.voice.superbowl.Voice.DirectiveProcessingListener
    public void onDirectivesScheduled() {
        LOG.debug("onDirectivesScheduled");
    }

    @Override // com.amazon.alexa.voice.superbowl.Voice.SimpleDirectiveProcessingListener, com.amazon.alexa.voice.superbowl.Voice.DirectiveProcessingListener
    public void onDirectiveReceived(Directive directive) {
        LOG.debug("onDirectiveReceived");
        if (directive instanceof ExpectSpeechDirective) {
            this.alexaUiController.setKeepUiVisible(true);
            this.receivedExpectedSpeechDirective = true;
        } else if (directive instanceof AudioPlayDirective) {
            this.audioPlayerQueueListener.onAudioItemEnqueued(((AudioPlayDirective) directive).getAudioItem());
        } else if (directive instanceof SynthesizeSpeechDirective) {
            handleSynthesizeSpeechDirective((SynthesizeSpeechDirective) directive);
        } else if (directive instanceof AudioClearQueueDirective) {
            this.audioPlayerQueueListener.clearQueue();
        }
    }

    private void handleSynthesizeSpeechDirective(SynthesizeSpeechDirective synthesizeSpeechDirective) {
        String domainFromDirective = getDomainFromDirective(synthesizeSpeechDirective);
        if (domainFromDirective.isEmpty()) {
            return;
        }
        if (alexaUiShouldStayOpenAfterSpeech(domainFromDirective)) {
            this.alexaUiController.setKeepUiVisible(true);
        }
        this.alexaUiController.notifyOnSsmlSpeechDomain(domainFromDirective);
    }

    private String getDomainFromDirective(SynthesizeSpeechDirective synthesizeSpeechDirective) {
        int iIndexOf;
        int i;
        int iIndexOf2;
        String ssml = synthesizeSpeechDirective.getSsml();
        if (ssml != null && (iIndexOf = ssml.indexOf("<namespace>")) >= 0 && (iIndexOf2 = ssml.indexOf("</namespace>", (i = iIndexOf + 11))) > i) {
            return ssml.substring(i, iIndexOf2);
        }
        LOG.error("Cannot parse SynthesizeSpeechDirective's ssml string to get its domain.");
        return "";
    }

    private boolean alexaUiShouldStayOpenAfterSpeech(String str) {
        return "DAILYBRIEFING".equals(str);
    }

    @Override // com.amazon.alexa.voice.superbowl.Voice.SimpleDirectiveProcessingListener, com.amazon.alexa.voice.superbowl.Voice.DirectiveProcessingListener
    public void onDirectiveAccepted(Directive directive) {
        LOG.debug("onDirectiveAccepted");
    }

    @Override // com.amazon.alexa.voice.superbowl.Voice.SimpleDirectiveProcessingListener, com.amazon.alexa.voice.superbowl.Voice.DirectiveProcessingListener
    public void onDirectiveRejected(Directive directive, Throwable th) {
        LOG.debug("onDirectiveRejected");
    }

    @Override // com.amazon.alexa.voice.superbowl.Voice.SimpleDirectiveProcessingListener, com.amazon.alexa.voice.superbowl.Voice.DirectiveProcessingListener
    public void onDirectivesReceived(Directive[] directiveArr) {
        LOG.debug("onDirectivesReceived");
    }

    @Override // com.amazon.alexa.voice.superbowl.Voice.SimpleDirectiveProcessingListener, com.amazon.alexa.voice.superbowl.Voice.DirectiveProcessingListener
    public void onDirectivesResponded() {
        LOG.debug("onDirectivesResponded");
        if (this.receivedExpectedSpeechDirective) {
            this.receivedExpectedSpeechDirective = false;
        } else {
            if (this.speechRecognizerPlugin.isListening()) {
                return;
            }
            this.alexaUiController.stopAllAnimations();
            if (this.alexaUiController.isAlexaUiVisible()) {
                this.alexaUiController.startShortCloseAlexaUiTask();
            }
        }
    }
}
