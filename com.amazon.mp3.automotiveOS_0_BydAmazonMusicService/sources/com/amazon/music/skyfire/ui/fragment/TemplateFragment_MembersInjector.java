package com.amazon.music.skyfire.ui.fragment;

import CoreInterface.v1_0.Template;
import com.amazon.music.skyfire.core.performance.SkillLatencyTracker;
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
public final class TemplateFragment_MembersInjector<T extends Template> implements MembersInjector<TemplateFragment<T>> {
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

    public TemplateFragment_MembersInjector(Provider<StyleSheetProvider> provider, Provider<ElementBindingProvider> provider2, Provider<ExternalActionProvider> provider3, Provider<SkyFireProvider> provider4, Provider<SkyFireConfigurationProvider> provider5, Provider<ContextMenuProvider> provider6, Provider<PlaybackProvider> provider7, Provider<UpsellProvider> provider8, Provider<NavigationProvider> provider9, Provider<SkillLatencyTracker> provider10) {
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
    }

    public static <T extends Template> MembersInjector<TemplateFragment<T>> create(Provider<StyleSheetProvider> provider, Provider<ElementBindingProvider> provider2, Provider<ExternalActionProvider> provider3, Provider<SkyFireProvider> provider4, Provider<SkyFireConfigurationProvider> provider5, Provider<ContextMenuProvider> provider6, Provider<PlaybackProvider> provider7, Provider<UpsellProvider> provider8, Provider<NavigationProvider> provider9, Provider<SkillLatencyTracker> provider10) {
        return new TemplateFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public void injectMembers(TemplateFragment<T> templateFragment) {
        injectStyleSheetProvider(templateFragment, this.styleSheetProvider.get());
        injectElementBindingProvider(templateFragment, this.elementBindingProvider.get());
        injectExternalActionProvider(templateFragment, this.externalActionProvider.get());
        injectSkyFireProvider(templateFragment, this.skyFireProvider.get());
        injectSkyFireConfigurationProvider(templateFragment, this.skyFireConfigurationProvider.get());
        injectContextMenuProvider(templateFragment, this.contextMenuProvider.get());
        injectPlaybackProvider(templateFragment, this.playbackProvider.get());
        injectUpsellProvider(templateFragment, this.upsellProvider.get());
        injectNavigationProvider(templateFragment, this.navigationProvider.get());
        injectSkillLatencyTracker(templateFragment, this.skillLatencyTrackerProvider.get());
    }

    public static <T extends Template> void injectStyleSheetProvider(TemplateFragment<T> templateFragment, StyleSheetProvider styleSheetProvider) {
        templateFragment.styleSheetProvider = styleSheetProvider;
    }

    public static <T extends Template> void injectElementBindingProvider(TemplateFragment<T> templateFragment, ElementBindingProvider elementBindingProvider) {
        templateFragment.elementBindingProvider = elementBindingProvider;
    }

    public static <T extends Template> void injectExternalActionProvider(TemplateFragment<T> templateFragment, ExternalActionProvider externalActionProvider) {
        templateFragment.externalActionProvider = externalActionProvider;
    }

    public static <T extends Template> void injectSkyFireProvider(TemplateFragment<T> templateFragment, SkyFireProvider skyFireProvider) {
        templateFragment.skyFireProvider = skyFireProvider;
    }

    public static <T extends Template> void injectSkyFireConfigurationProvider(TemplateFragment<T> templateFragment, SkyFireConfigurationProvider skyFireConfigurationProvider) {
        templateFragment.skyFireConfigurationProvider = skyFireConfigurationProvider;
    }

    public static <T extends Template> void injectContextMenuProvider(TemplateFragment<T> templateFragment, ContextMenuProvider contextMenuProvider) {
        templateFragment.contextMenuProvider = contextMenuProvider;
    }

    public static <T extends Template> void injectPlaybackProvider(TemplateFragment<T> templateFragment, PlaybackProvider playbackProvider) {
        templateFragment.playbackProvider = playbackProvider;
    }

    public static <T extends Template> void injectUpsellProvider(TemplateFragment<T> templateFragment, UpsellProvider upsellProvider) {
        templateFragment.upsellProvider = upsellProvider;
    }

    public static <T extends Template> void injectNavigationProvider(TemplateFragment<T> templateFragment, NavigationProvider navigationProvider) {
        templateFragment.navigationProvider = navigationProvider;
    }

    public static <T extends Template> void injectSkillLatencyTracker(TemplateFragment<T> templateFragment, SkillLatencyTracker skillLatencyTracker) {
        templateFragment.skillLatencyTracker = skillLatencyTracker;
    }
}
