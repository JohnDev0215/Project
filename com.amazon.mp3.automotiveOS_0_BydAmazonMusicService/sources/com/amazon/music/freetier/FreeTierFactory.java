package com.amazon.music.freetier;

import Touch.LiveStreamPlayerTemplateInterface.v1_0.ImmutableDisplayGenericErrorMethod;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.music.freetier.destination.CreateAndPlayMixDestination;
import com.amazon.music.freetier.destination.ShowESCDestination;
import com.amazon.music.freetier.destination.TrackDetailDestination;
import com.amazon.music.freetier.skyfire.SkyFireUtils;
import com.amazon.music.skyfire.ui.IntentKeys;
import com.amazon.music.skyfire.ui.fragment.GalleryFragment;
import com.amazon.music.skyfire.ui.fragment.MultiSelectorFragment;
import com.amazon.music.skyfire.ui.fragment.TemplateFragment;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class FreeTierFactory {
    public static void initShowESCIntent(Intent intent, ShowESCDestination showESCDestination) {
        List listSingletonList = Collections.singletonList(ImmutableDisplayGenericErrorMethod.builder().queue(SkyFireUtils.stHttpQueue()).build());
        Bundle bundle = new Bundle();
        try {
            bundle.putString(TemplateFragment.ARG_SCREEN_ID, "showESC");
            bundle.putString(TemplateFragment.ARG_INITIAL_METHODS, SkyFireUtils.getSerializedMethods(Collections.singletonList(SkyFireUtils.getInvokeHttpSkillMethod("/showESC", Collections.emptyMap(), listSingletonList))));
            bundle.putInt(TemplateFragment.ARG_INITIAL_METHOD_BEHAVIOR, 1);
            intent.putExtra(IntentKeys.EXTRA_FRAGMENT, MultiSelectorFragment.class.getName());
            intent.putExtra(IntentKeys.EXTRA_TAG, "showESC");
            intent.putExtra(IntentKeys.EXTRA_EXTRAS, bundle);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initCreateAndPlayMixIntent(Intent intent, CreateAndPlayMixDestination createAndPlayMixDestination) {
        List listSingletonList = Collections.singletonList(ImmutableDisplayGenericErrorMethod.builder().queue(SkyFireUtils.stHttpQueue()).build());
        String str = "createAndPlayMix/" + createAndPlayMixDestination.getSelectedTags();
        Bundle bundle = new Bundle();
        try {
            bundle.putString(TemplateFragment.ARG_SCREEN_ID, str);
            bundle.putString(TemplateFragment.ARG_INITIAL_METHODS, SkyFireUtils.getSerializedMethods(Collections.singletonList(SkyFireUtils.getInvokeHttpSkillMethod("/createAndPlayMix", Collections.singletonMap("selectedTags", createAndPlayMixDestination.getSelectedTags()), listSingletonList))));
            bundle.putInt(TemplateFragment.ARG_INITIAL_METHOD_BEHAVIOR, 1);
            intent.putExtra(IntentKeys.EXTRA_FRAGMENT, GalleryFragment.class.getName());
            intent.putExtra(IntentKeys.EXTRA_TAG, str);
            intent.putExtra(IntentKeys.EXTRA_EXTRAS, bundle);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initTrackDetailIntent(Intent intent, TrackDetailDestination trackDetailDestination) {
        List listSingletonList = Collections.singletonList(ImmutableDisplayGenericErrorMethod.builder().queue(SkyFireUtils.stHttpQueue()).build());
        String str = "showTrackDetails/" + trackDetailDestination.getTrackAsin();
        Bundle bundle = new Bundle();
        try {
            bundle.putString(TemplateFragment.ARG_SCREEN_ID, str);
            bundle.putString(TemplateFragment.ARG_INITIAL_METHODS, SkyFireUtils.getSerializedMethods(Collections.singletonList(SkyFireUtils.getInvokeHttpSkillMethod("/showTrackDetails", Collections.singletonMap("id", trackDetailDestination.getTrackAsin()), listSingletonList))));
            bundle.putInt(TemplateFragment.ARG_INITIAL_METHOD_BEHAVIOR, 1);
            intent.putExtra(IntentKeys.EXTRA_FRAGMENT, GalleryFragment.class.getName());
            intent.putExtra(IntentKeys.EXTRA_TAG, str);
            intent.putExtra(IntentKeys.EXTRA_EXTRAS, bundle);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
