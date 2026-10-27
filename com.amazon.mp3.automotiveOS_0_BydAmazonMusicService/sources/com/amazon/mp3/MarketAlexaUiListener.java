package com.amazon.mp3;

import android.content.Intent;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.bottomnavbar.BottomNavigationBar;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyEventDescriptor;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.voice.ui.AlexaUiListener;

/* JADX INFO: loaded from: classes3.dex */
public class MarketAlexaUiListener extends AlexaUiListener.SimpleAlexaUiListener {
    private BottomNavigationBar mBottomNavigationBar;

    public MarketAlexaUiListener(final BottomNavigationBar bottomNavigationBar) {
        this.mBottomNavigationBar = bottomNavigationBar;
    }

    public MarketAlexaUiListener() {
    }

    @Override // com.amazon.music.voice.ui.AlexaUiListener.SimpleAlexaUiListener, com.amazon.music.voice.ui.AlexaUiListener
    public void onAlexaUiOpened() {
        AmazonApplication.getApplication().sendBroadcast(new Intent("com.amazon.mp3.wakewordcommand.alexa.ui.opened"));
        LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.LOAD_COMPLETE, PageLoadLatencyCode.ALEXA));
    }

    @Override // com.amazon.music.voice.ui.AlexaUiListener.SimpleAlexaUiListener, com.amazon.music.voice.ui.AlexaUiListener
    public void onAlexaUiClosed() {
        BottomNavigationBar bottomNavigationBar = this.mBottomNavigationBar;
        if (bottomNavigationBar != null) {
            bottomNavigationBar.selectPreviousTab();
        }
        AmazonApplication.getApplication().sendBroadcast(new Intent("com.amazon.mp3.wakewordcommand.alexa.ui.closed"));
    }

    @Override // com.amazon.music.voice.ui.AlexaUiListener.SimpleAlexaUiListener, com.amazon.music.voice.ui.AlexaUiListener
    public void onSsmlSpeechDomain(final String domain) {
        AmazonApplication application = AmazonApplication.getApplication();
        if (AlexaSettingsFragment.shouldPromptSettings(SettingsUtil.getPrefs(application), domain)) {
            try {
                AlexaSettingsFragment.newInstance(domain).show(((FragmentActivity) AndroidUtils.getCurrentActivity()).getSupportFragmentManager(), (String) null);
                AlexaSettingsFragment.recordPromptSettings(SettingsUtil.getPrefs(application));
            } catch (ClassCastException | IllegalStateException unused) {
            }
        }
    }

    public void destroy() {
        this.mBottomNavigationBar = null;
    }
}
