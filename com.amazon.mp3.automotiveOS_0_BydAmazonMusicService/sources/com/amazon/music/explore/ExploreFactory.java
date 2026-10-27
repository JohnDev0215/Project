package com.amazon.music.explore;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import Touch.LiveStreamPlayerTemplateInterface.v1_0.ImmutableDisplayGenericErrorMethod;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import com.amazon.music.destination.AllArtistMerchDestination;
import com.amazon.music.destination.ArtistDetailDestination;
import com.amazon.music.destination.LiveEventDetailDestination;
import com.amazon.music.destination.MerchDetailDestination;
import com.amazon.music.destination.MerchShopDestination;
import com.amazon.music.explore.activity.AmpPlaybackActivity;
import com.amazon.music.explore.activity.BaseExploreActivity;
import com.amazon.music.explore.activity.LiveStreamActivity;
import com.amazon.music.explore.activity.VideoQueueActivity;
import com.amazon.music.explore.feature_gating.FeatureGating;
import com.amazon.music.explore.fragment.PlaybackFragment;
import com.amazon.music.explore.skyfire.SkyFireUtils;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.skyfire.ui.IntentKeys;
import com.amazon.music.skyfire.ui.fragment.GalleryFragment;
import com.amazon.music.skyfire.ui.fragment.TemplateFragment;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.amazon.music.skyfire.ui.skyfire.Methods;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreFactory {
    public static String METHODS_TO_INVOKE = "METHODS_TO_INVOKE";

    public static void initArtistDetailIntent(Intent intent, ArtistDetailDestination artistDetailDestination) {
        List listSingletonList = Collections.singletonList(ImmutableDisplayGenericErrorMethod.builder().queue(SkyFireUtils.stHttpQueue()).build());
        String str = "artistDetail/" + artistDetailDestination.getArtistAsin();
        String str2 = "/showCatalogArtist";
        if (FeatureGating.ADPMobileSkillUnificationFeature.isEnabled()) {
            str2 = "/explore/v1/showCatalogArtist";
        }
        Bundle bundle = new Bundle();
        if (artistDetailDestination.getShowReleases()) {
            str = "artistReleasesDetail/" + artistDetailDestination.getArtistAsin();
            str2 = "/showReleasesDetail";
        }
        try {
            bundle.putInt(TemplateFragment.ARG_NAVIGATION_BEHAVIOR, 1);
            bundle.putString(TemplateFragment.ARG_SCREEN_ID, str);
            bundle.putString(TemplateFragment.ARG_INITIAL_METHODS, SkyFireUtils.getSerializedMethods(Collections.singletonList(SkyFireUtils.getInvokeHttpSkillMethod(str2, new HashMap<String, String>() { // from class: com.amazon.music.explore.ExploreFactory.1
                {
                    put("id", this.val$destination.getArtistAsin());
                    put("libraryArtistId", this.val$destination.getArtistId());
                    put("storyId", this.val$destination.getStoryId());
                }
            }, listSingletonList))));
            intent.putExtra(IntentKeys.EXTRA_FRAGMENT, GalleryFragment.class.getName());
            intent.putExtra(IntentKeys.EXTRA_TAG, str);
            intent.putExtra(IntentKeys.EXTRA_EXTRAS, bundle);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static PlaybackFragment openMusicExperience(Context context, String str, String str2) {
        String str3;
        HashMap map = new HashMap();
        if (str2 != null) {
            map.put("experienceId", str2);
            str3 = String.format("/playMusicExperience/%s", str);
        } else {
            str3 = String.format("/openAndPlayArtistSpotlight/%s", str);
        }
        List<Method> listSingletonList = Collections.singletonList(SkyFireUtils.getInvokeHttpSkillMethod(str3, map, Collections.singletonList(createDisplayErrorScreenMethod(context))));
        PlaybackFragment playbackFragment = new PlaybackFragment();
        playbackFragment.setInitialMethods(listSingletonList);
        return playbackFragment;
    }

    public static void openVideoStoriesPage(Activity activity, String str, String str2) {
        Intent intent = new Intent(activity, (Class<?>) VideoQueueActivity.class);
        intent.setFlags(268435456);
        String str3 = String.format("/showVideoStory/%s", str);
        HashMap map = new HashMap();
        map.put("associationId", str2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(ImmutableDisplayGenericErrorMethod.builder().queue(SkyFireUtils.stHttpQueue()).build());
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(SkyFireUtils.getInvokeHttpSkillMethod(str3, map, arrayList));
        try {
            intent.putExtra(BaseExploreActivity.METHODS_TO_INVOKE, SkyFireUtils.getSerializedMethods(arrayList2));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        activity.startActivity(intent);
    }

    public static void openLiveStreamPage(Context context, String str) {
        openLiveStreamPage(context, str, null, null);
    }

    public static void openLiveStreamPage(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, (Class<?>) LiveStreamActivity.class);
        intent.setFlags(268435456);
        String str4 = String.format("/showLiveStream/%s", str);
        HashMap map = new HashMap();
        if (str2 != null) {
            map.put("refMarker", str2);
        }
        if (str3 != null) {
            map.put("associateTag", str3);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(ImmutableDisplayGenericErrorMethod.builder().queue(SkyFireUtils.stHttpQueue()).build());
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(SkyFireUtils.getInvokeHttpSkillMethod(str4, map, arrayList));
        try {
            intent.putExtra(BaseExploreActivity.METHODS_TO_INVOKE, Mappers.mapper().writerFor(Mappers.mapper().getTypeFactory().constructCollectionType(List.class, Method.class)).writeValueAsString(arrayList2));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        context.startActivity(intent);
    }

    public static void openAmpPlayback(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, (Class<?>) AmpPlaybackActivity.class);
        intent.setFlags(268435456);
        HashMap map = new HashMap();
        if (StringUtils.isNotBlank(str)) {
            map.put("showId", str);
        }
        if (str2 != null) {
            map.put("refMarker", str2);
        }
        if (str3 != null) {
            map.put("associateTag", str3);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(ImmutableDisplayGenericErrorMethod.builder().queue(SkyFireUtils.stHttpQueue()).build());
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(SkyFireUtils.getInvokeHttpSkillMethod("/ampplayback/loadAmpPlayback", map, arrayList));
        try {
            intent.putExtra(BaseExploreActivity.METHODS_TO_INVOKE, Mappers.mapper().writerFor(Mappers.mapper().getTypeFactory().constructCollectionType(List.class, Method.class)).writeValueAsString(arrayList2));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        context.startActivity(intent);
    }

    public static void initAllArtistMerchIntent(Intent intent, AllArtistMerchDestination allArtistMerchDestination) {
        List listSingletonList = Collections.singletonList(ImmutableDisplayGenericErrorMethod.builder().queue(SkyFireUtils.stHttpQueue()).build());
        Bundle bundle = new Bundle();
        String str = "allArtistMerch/" + allArtistMerchDestination.getArtistAsin();
        HashMap map = new HashMap();
        String ref = allArtistMerchDestination.getRef();
        if (ref != null) {
            map.put("refMarker", ref);
        }
        String tag = allArtistMerchDestination.getTag();
        if (tag != null) {
            map.put("associateTag", tag);
        }
        String name = GalleryFragment.class.getName();
        map.put("artistId", allArtistMerchDestination.getArtistAsin());
        if (allArtistMerchDestination.getCategory() != null) {
            map.put("category", allArtistMerchDestination.getCategory());
        }
        try {
            bundle.putString(TemplateFragment.ARG_SCREEN_ID, str);
            bundle.putString(TemplateFragment.ARG_INITIAL_METHODS, SkyFireUtils.getSerializedMethods(Collections.singletonList(SkyFireUtils.getInvokeHttpSkillMethod("/showMerchAll/", map, listSingletonList))));
            intent.putExtra(IntentKeys.EXTRA_FRAGMENT, name);
            intent.putExtra(IntentKeys.EXTRA_TAG, str);
            intent.putExtra(IntentKeys.EXTRA_EXTRAS, bundle);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initLiveEventDetailIntent(Intent intent, LiveEventDetailDestination liveEventDetailDestination) {
        List listSingletonList = Collections.singletonList(ImmutableDisplayGenericErrorMethod.builder().queue(SkyFireUtils.stHttpQueue()).build());
        Bundle bundle = new Bundle();
        String str = "liveEventDetail/" + liveEventDetailDestination.getEventId();
        try {
            bundle.putString(TemplateFragment.ARG_SCREEN_ID, str);
            bundle.putString(TemplateFragment.ARG_INITIAL_METHODS, SkyFireUtils.getSerializedMethods(Collections.singletonList(SkyFireUtils.getInvokeHttpSkillMethod("/showLiveEventDetail", Collections.singletonMap("id", liveEventDetailDestination.getEventId()), listSingletonList))));
            intent.putExtra(IntentKeys.EXTRA_FRAGMENT, GalleryFragment.class.getName());
            intent.putExtra(IntentKeys.EXTRA_TAG, str);
            intent.putExtra(IntentKeys.EXTRA_EXTRAS, bundle);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initMerchDetailIntent(Intent intent, MerchDetailDestination merchDetailDestination) {
        List listSingletonList = Collections.singletonList(ImmutableDisplayGenericErrorMethod.builder().queue(SkyFireUtils.stHttpQueue()).build());
        Bundle bundle = new Bundle();
        String str = "merchDetail/" + merchDetailDestination.getArtistAsin() + "/" + merchDetailDestination.getMerchAsin();
        HashMap map = new HashMap();
        map.put("artistId", merchDetailDestination.getArtistAsin());
        map.put("id", merchDetailDestination.getMerchAsin());
        try {
            bundle.putString(TemplateFragment.ARG_SCREEN_ID, str);
            bundle.putString(TemplateFragment.ARG_INITIAL_METHODS, SkyFireUtils.getSerializedMethods(Collections.singletonList(SkyFireUtils.getInvokeHttpSkillMethod("/showMerchDetail", map, listSingletonList))));
            intent.putExtra(IntentKeys.EXTRA_FRAGMENT, GalleryFragment.class.getName());
            intent.putExtra(IntentKeys.EXTRA_TAG, str);
            intent.putExtra(IntentKeys.EXTRA_EXTRAS, bundle);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initMerchShopIntent(Intent intent, MerchShopDestination merchShopDestination) {
        List listSingletonList = Collections.singletonList(ImmutableDisplayGenericErrorMethod.builder().queue(SkyFireUtils.stHttpQueue()).build());
        Bundle bundle = new Bundle();
        StringBuilder sb = new StringBuilder("merchShop");
        HashMap map = new HashMap();
        String category = merchShopDestination.getCategory();
        if (category != null) {
            map.put(Splash.PARAMS_CAMPAIGN_ID, category);
            sb.append("/");
            sb.append(category);
        }
        String string = sb.toString();
        String sequence = merchShopDestination.getSequence();
        if (sequence != null) {
            map.put("sequence", sequence);
        }
        String ref = merchShopDestination.getRef();
        if (ref != null) {
            map.put("refMarker", ref);
        }
        String tag = merchShopDestination.getTag();
        if (tag != null) {
            map.put("tagId", tag);
            map.put("associateTag", tag);
        }
        String name = merchShopDestination.getName();
        if (name != null) {
            bundle.putString(GalleryFragment.ARG_CONFIGURE_ACTIONBAR_TITLE, name);
        }
        if (!(category == null && sequence == null && name == null)) {
            bundle.putBoolean(GalleryFragment.ARG_IS_ACTIONBAR_OVERLAY, false);
        }
        try {
            bundle.putString(TemplateFragment.ARG_SCREEN_ID, string);
            bundle.putString(TemplateFragment.ARG_INITIAL_METHODS, SkyFireUtils.getSerializedMethods(Collections.singletonList(SkyFireUtils.getInvokeHttpSkillMethod("/showMerchShop", map, listSingletonList))));
            intent.putExtra(IntentKeys.EXTRA_FRAGMENT, GalleryFragment.class.getName());
            intent.putExtra(IntentKeys.EXTRA_TAG, string);
            intent.putExtra(IntentKeys.EXTRA_EXTRAS, bundle);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static TriggerExternalActionMethod createDisplayErrorScreenMethod(Context context) {
        final Resources resources = context.getResources();
        return new TriggerExternalActionMethod() { // from class: com.amazon.music.explore.ExploreFactory.2
            @Override // ExternalActionInterface.v1_0.TriggerExternalActionMethod
            public String name() {
                return Methods.displayErrorScreen;
            }

            @Override // ExternalActionInterface.v1_0.TriggerExternalActionMethod
            public Map<String, String> context() {
                return new HashMap<String, String>() { // from class: com.amazon.music.explore.ExploreFactory.2.1
                    {
                        put(Contexts.DisplayErrorScreen.primaryText, resources.getString(R.string.explore_something_went_wrong));
                        put(Contexts.DisplayErrorScreen.buttonText, resources.getString(R.string.explore_close));
                    }
                };
            }

            @Override // CoreInterface.v1_0.Method
            public Queue queue() {
                return SkyFireUtils.stHttpQueue();
            }
        };
    }
}
