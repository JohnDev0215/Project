package com.amazon.mp3.find.skyfire;

import CoreInterface.v1_0.Method;
import ExternalActionInterface.v1_0.ImmutableTriggerExternalActionMethod;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.find.view.FindLandingFragment;
import com.amazon.mp3.find.view.SearchTabFragmentHost;
import com.amazon.music.explore.Mappers;
import com.amazon.music.explore.skyfire.SkyFireUtils;
import com.amazon.music.find.fragment.FindSkyfireFragment;
import com.amazon.music.find.fragment.SearchGalleryFragment;
import com.amazon.music.find.skyfire.Constants;
import com.amazon.music.find.skyfire.Methods;
import com.amazon.music.skyfire.ui.fragment.TemplateFragment;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: FindSkyfireFactory.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u001c\u0010\t\u001a\u00020\u00042\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006J0\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00072\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002J.\u0010\f\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00072\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002R\u001c\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/find/skyfire/FindSkyfireFactory;", "", "Landroid/os/Bundle;", "extras", "Landroidx/fragment/app/Fragment;", "createFindLandingPage", "", "", "queryParams", "createFindLandingSeeMorePage", "skillPath", "createSearchPage", "createSkyfireSkill", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "logger", "Lorg/slf4j/Logger;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class FindSkyfireFactory {
    public static final FindSkyfireFactory INSTANCE = new FindSkyfireFactory();
    private static final Logger logger = LoggerFactory.getLogger(FindSkyfireFactory.class.getSimpleName());

    private FindSkyfireFactory() {
    }

    public final Fragment createFindLandingPage(Bundle extras) {
        Fragment findSkyfireFragment = SearchTabFragmentHost.INSTANCE.getUseFindSkyfireFragment() ? new FindSkyfireFragment() : new FindLandingFragment();
        Bundle bundle = extras != null ? new Bundle(extras) : new Bundle();
        bundle.putString(TemplateFragment.ARG_SCREEN_ID, Constants.FIND_SCREEN_ID);
        bundle.putInt(TemplateFragment.ARG_VIEW_MODEL_PERSISTENCE_BEHAVIOR, 1);
        findSkyfireFragment.setArguments(createSkyfireSkill(Constants.SHOW_FIND_SKILL, null, bundle));
        return findSkyfireFragment;
    }

    public final Fragment createFindLandingSeeMorePage(Map<String, String> queryParams) {
        Bundle bundle = new Bundle();
        bundle.putString(TemplateFragment.ARG_SCREEN_ID, Constants.FIND_SCREEN_ID);
        FindSkyfireFragment findSkyfireFragment = new FindSkyfireFragment();
        findSkyfireFragment.setArguments(INSTANCE.createSkyfireSkill(Constants.SHOW_FIND_CONTENT_SKILL, queryParams, bundle));
        return findSkyfireFragment;
    }

    public final Fragment createSearchPage(String skillPath, Map<String, String> queryParams, Bundle extras) {
        Intrinsics.checkNotNullParameter(skillPath, "skillPath");
        SearchGalleryFragment searchGalleryFragment = new SearchGalleryFragment();
        searchGalleryFragment.setArguments(INSTANCE.createSkyfireSkill(skillPath, queryParams, extras));
        return searchGalleryFragment;
    }

    public final Bundle createSkyfireSkill(String skillPath, Map<String, String> queryParams, Bundle extras) {
        Intrinsics.checkNotNullParameter(skillPath, "skillPath");
        Bundle bundle = extras != null ? new Bundle(extras) : new Bundle();
        ImmutableTriggerExternalActionMethod immutableTriggerExternalActionMethodBuild = ImmutableTriggerExternalActionMethod.builder().name(Methods.handleError).queue(SkyFireUtils.stHttpQueue()).build();
        Intrinsics.checkNotNullExpressionValue(immutableTriggerExternalActionMethodBuild, "builder()\n              …\n                .build()");
        List listListOf = CollectionsKt__CollectionsJVMKt.listOf(SkyFireUtils.getInvokeHttpSkillMethod(skillPath, queryParams, CollectionsKt__CollectionsKt.mutableListOf(immutableTriggerExternalActionMethodBuild)));
        bundle.putString(Constants.SKILL_PATH_ID, skillPath);
        try {
            bundle.putString(TemplateFragment.ARG_INITIAL_METHODS, Mappers.mapper().writerFor(Mappers.mapper().getTypeFactory().constructCollectionType(List.class, Method.class)).writeValueAsString(listListOf));
        } catch (JsonProcessingException e) {
            logger.debug(Intrinsics.stringPlus("SPL::createSkyfireSkill. Unhandled exception during methods serialization: ", e.getMessage()));
        }
        return bundle;
    }
}
