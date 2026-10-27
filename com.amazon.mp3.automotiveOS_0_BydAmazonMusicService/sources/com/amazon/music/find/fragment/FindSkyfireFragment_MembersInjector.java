package com.amazon.music.find.fragment;

import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.skyfire.core.performance.SkillLatencyTracker;
import com.amazon.music.skyfire.ui.fragment.MusicHomeFragment_MembersInjector;
import com.amazon.music.skyfire.ui.fragment.TemplateFragment_MembersInjector;
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
public final class FindSkyfireFragment_MembersInjector implements MembersInjector<FindSkyfireFragment> {
    private final Provider<ChromeProvider> chromeProvider;
    private final Provider<ContextMenuProvider> contextMenuProvider;
    private final Provider<ElementBindingProvider> elementBindingProvider;
    private final Provider<ExternalActionProvider> externalActionProvider;
    private final Provider<NavigationProvider> navigationProvider;
    private final Provider<PlaybackProvider> playbackProvider;
    private final Provider<SearchFeaturesProvider> searchFeaturesProvider;
    private final Provider<SkillLatencyTracker> skillLatencyTrackerProvider;
    private final Provider<SkyFireConfigurationProvider> skyFireConfigurationProvider;
    private final Provider<SkyFireProvider> skyFireProvider;
    private final Provider<StyleSheetProvider> styleSheetProvider;
    private final Provider<UpsellProvider> upsellProvider;

    public FindSkyfireFragment_MembersInjector(Provider<StyleSheetProvider> styleSheetProvider, Provider<ElementBindingProvider> elementBindingProvider, Provider<ExternalActionProvider> externalActionProvider, Provider<SkyFireProvider> skyFireProvider, Provider<SkyFireConfigurationProvider> skyFireConfigurationProvider, Provider<ContextMenuProvider> contextMenuProvider, Provider<PlaybackProvider> playbackProvider, Provider<UpsellProvider> upsellProvider, Provider<NavigationProvider> navigationProvider, Provider<SkillLatencyTracker> skillLatencyTrackerProvider, Provider<ChromeProvider> chromeProvider, Provider<SearchFeaturesProvider> searchFeaturesProvider) {
        this.styleSheetProvider = styleSheetProvider;
        this.elementBindingProvider = elementBindingProvider;
        this.externalActionProvider = externalActionProvider;
        this.skyFireProvider = skyFireProvider;
        this.skyFireConfigurationProvider = skyFireConfigurationProvider;
        this.contextMenuProvider = contextMenuProvider;
        this.playbackProvider = playbackProvider;
        this.upsellProvider = upsellProvider;
        this.navigationProvider = navigationProvider;
        this.skillLatencyTrackerProvider = skillLatencyTrackerProvider;
        this.chromeProvider = chromeProvider;
        this.searchFeaturesProvider = searchFeaturesProvider;
    }

    public static MembersInjector<FindSkyfireFragment> create(Provider<StyleSheetProvider> styleSheetProvider, Provider<ElementBindingProvider> elementBindingProvider, Provider<ExternalActionProvider> externalActionProvider, Provider<SkyFireProvider> skyFireProvider, Provider<SkyFireConfigurationProvider> skyFireConfigurationProvider, Provider<ContextMenuProvider> contextMenuProvider, Provider<PlaybackProvider> playbackProvider, Provider<UpsellProvider> upsellProvider, Provider<NavigationProvider> navigationProvider, Provider<SkillLatencyTracker> skillLatencyTrackerProvider, Provider<ChromeProvider> chromeProvider, Provider<SearchFeaturesProvider> searchFeaturesProvider) {
        return new FindSkyfireFragment_MembersInjector(styleSheetProvider, elementBindingProvider, externalActionProvider, skyFireProvider, skyFireConfigurationProvider, contextMenuProvider, playbackProvider, upsellProvider, navigationProvider, skillLatencyTrackerProvider, chromeProvider, searchFeaturesProvider);
    }

    public void injectMembers(FindSkyfireFragment instance) {
        TemplateFragment_MembersInjector.injectStyleSheetProvider(instance, this.styleSheetProvider.get());
        TemplateFragment_MembersInjector.injectElementBindingProvider(instance, this.elementBindingProvider.get());
        TemplateFragment_MembersInjector.injectExternalActionProvider(instance, this.externalActionProvider.get());
        TemplateFragment_MembersInjector.injectSkyFireProvider(instance, this.skyFireProvider.get());
        TemplateFragment_MembersInjector.injectSkyFireConfigurationProvider(instance, this.skyFireConfigurationProvider.get());
        TemplateFragment_MembersInjector.injectContextMenuProvider(instance, this.contextMenuProvider.get());
        TemplateFragment_MembersInjector.injectPlaybackProvider(instance, this.playbackProvider.get());
        TemplateFragment_MembersInjector.injectUpsellProvider(instance, this.upsellProvider.get());
        TemplateFragment_MembersInjector.injectNavigationProvider(instance, this.navigationProvider.get());
        TemplateFragment_MembersInjector.injectSkillLatencyTracker(instance, this.skillLatencyTrackerProvider.get());
        MusicHomeFragment_MembersInjector.injectChromeProvider(instance, this.chromeProvider.get());
        injectSearchFeaturesProvider(instance, this.searchFeaturesProvider.get());
    }

    public static void injectSearchFeaturesProvider(FindSkyfireFragment instance, SearchFeaturesProvider searchFeaturesProvider) {
        instance.searchFeaturesProvider = searchFeaturesProvider;
    }
}
