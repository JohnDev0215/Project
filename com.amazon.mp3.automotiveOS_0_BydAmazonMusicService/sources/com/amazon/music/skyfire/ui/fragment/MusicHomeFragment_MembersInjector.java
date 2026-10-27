package com.amazon.music.skyfire.ui.fragment;

import CoreInterface.v1_0.Template;
import com.amazon.music.skyfire.core.performance.SkillLatencyTracker;
import com.amazon.music.skyfire.ui.providers.ChromeProvider;
import com.amazon.music.skyfire.ui.providers.ContextMenuProvider;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.providers.ExternalActionProvider;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.skyfire.ui.providers.SkyFireConfigurationProvider;
import com.amazon.music.skyfire.ui.providers.SkyFireProvider;
import com.amazon.music.skyfire.ui.providers.StyleSheetProvider;
import com.amazon.music.skyfire.ui.providers.UpsellProvider;
import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class MusicHomeFragment_MembersInjector<T extends Template> implements MembersInjector<MusicHomeFragment<T>> {
    private final Provider<ChromeProvider> chromeProvider;
    private final Provider<ContextMenuProvider> contextMenuProvider;
    private final Provider<ElementBindingProvider> elementBindingProvider;
    private final Provider<ExternalActionProvider> externalActionProvider;
    private final Provider<NavigationProvider> navigationProvider;
    private final Provider<PlaybackProvider> playbackProvider;
    private final Provider<SkillLatencyTracker> skillLatencyTrackerProvider;
    private final Provider<SkyFireConfigurationProvider> skyFireConfigurationProvider;
    private final Provider<SkyFireProvider> skyFireProvider;
    private final Provider<StyleSheetProvider> styleSheetProvider;
    private final Provider<UpsellProvider> upsellProvider;

    public MusicHomeFragment_MembersInjector(Provider<StyleSheetProvider> provider, Provider<ElementBindingProvider> provider2, Provider<ExternalActionProvider> provider3, Provider<SkyFireProvider> provider4, Provider<SkyFireConfigurationProvider> provider5, Provider<ContextMenuProvider> provider6, Provider<PlaybackProvider> provider7, Provider<UpsellProvider> provider8, Provider<NavigationProvider> provider9, Provider<SkillLatencyTracker> provider10, Provider<ChromeProvider> provider11) {
        this.styleSheetProvider = provider;
        this.elementBindingProvider = provider2;
        this.externalActionProvider = provider3;
        this.skyFireProvider = provider4;
        this.skyFireConfigurationProvider = provider5;
        this.contextMenuProvider = provider6;
        this.playbackProvider = provider7;
        this.upsellProvider = provider8;
        this.navigationProvider = provider9;
        this.skillLatencyTrackerProvider = provider10;
        this.chromeProvider = provider11;
    }

    public static <T extends Template> MembersInjector<MusicHomeFragment<T>> create(Provider<StyleSheetProvider> provider, Provider<ElementBindingProvider> provider2, Provider<ExternalActionProvider> provider3, Provider<SkyFireProvider> provider4, Provider<SkyFireConfigurationProvider> provider5, Provider<ContextMenuProvider> provider6, Provider<PlaybackProvider> provider7, Provider<UpsellProvider> provider8, Provider<NavigationProvider> provider9, Provider<SkillLatencyTracker> provider10, Provider<ChromeProvider> provider11) {
        return new MusicHomeFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }

    public void injectMembers(MusicHomeFragment<T> musicHomeFragment) {
        TemplateFragment_MembersInjector.injectStyleSheetProvider(musicHomeFragment, this.styleSheetProvider.get());
        TemplateFragment_MembersInjector.injectElementBindingProvider(musicHomeFragment, this.elementBindingProvider.get());
        TemplateFragment_MembersInjector.injectExternalActionProvider(musicHomeFragment, this.externalActionProvider.get());
        TemplateFragment_MembersInjector.injectSkyFireProvider(musicHomeFragment, this.skyFireProvider.get());
        TemplateFragment_MembersInjector.injectSkyFireConfigurationProvider(musicHomeFragment, this.skyFireConfigurationProvider.get());
        TemplateFragment_MembersInjector.injectContextMenuProvider(musicHomeFragment, this.contextMenuProvider.get());
        TemplateFragment_MembersInjector.injectPlaybackProvider(musicHomeFragment, this.playbackProvider.get());
        TemplateFragment_MembersInjector.injectUpsellProvider(musicHomeFragment, this.upsellProvider.get());
        TemplateFragment_MembersInjector.injectNavigationProvider(musicHomeFragment, this.navigationProvider.get());
        TemplateFragment_MembersInjector.injectSkillLatencyTracker(musicHomeFragment, this.skillLatencyTrackerProvider.get());
        injectChromeProvider(musicHomeFragment, this.chromeProvider.get());
    }

    public static <T extends Template> void injectChromeProvider(MusicHomeFragment<T> musicHomeFragment, ChromeProvider chromeProvider) {
        musicHomeFragment.chromeProvider = chromeProvider;
    }
}
