package com.android.car.developeroptions.homepage.contextualcards;

import android.util.Log;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCardLookupTable;
import com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionContextualCardController;
import com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionContextualCardRenderer;
import com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionFooterContextualCardRenderer;
import com.android.car.developeroptions.homepage.contextualcards.conditional.ConditionHeaderContextualCardRenderer;
import com.android.car.developeroptions.homepage.contextualcards.legacysuggestion.LegacySuggestionContextualCardController;
import com.android.car.developeroptions.homepage.contextualcards.legacysuggestion.LegacySuggestionContextualCardRenderer;
import com.android.car.developeroptions.homepage.contextualcards.slices.SliceContextualCardController;
import com.android.car.developeroptions.homepage.contextualcards.slices.SliceContextualCardRenderer;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class ContextualCardLookupTable {
    static final Set<ControllerRendererMapping> LOOKUP_TABLE = new TreeSet<ControllerRendererMapping>() { // from class: com.android.car.developeroptions.homepage.contextualcards.ContextualCardLookupTable.1
        {
            add(new ControllerRendererMapping(3, R.layout.conditional_card_half_tile, ConditionContextualCardController.class, ConditionContextualCardRenderer.class));
            add(new ControllerRendererMapping(3, R.layout.conditional_card_full_tile, ConditionContextualCardController.class, ConditionContextualCardRenderer.class));
            add(new ControllerRendererMapping(2, R.layout.legacy_suggestion_tile, LegacySuggestionContextualCardController.class, LegacySuggestionContextualCardRenderer.class));
            add(new ControllerRendererMapping(1, R.layout.contextual_slice_deferred_setup, SliceContextualCardController.class, SliceContextualCardRenderer.class));
            add(new ControllerRendererMapping(1, R.layout.contextual_slice_full_tile, SliceContextualCardController.class, SliceContextualCardRenderer.class));
            add(new ControllerRendererMapping(1, R.layout.contextual_slice_half_tile, SliceContextualCardController.class, SliceContextualCardRenderer.class));
            add(new ControllerRendererMapping(5, R.layout.conditional_card_footer, ConditionContextualCardController.class, ConditionFooterContextualCardRenderer.class));
            add(new ControllerRendererMapping(4, R.layout.conditional_card_header, ConditionContextualCardController.class, ConditionHeaderContextualCardRenderer.class));
        }
    };

    static class ControllerRendererMapping implements Comparable<ControllerRendererMapping> {
        final int mCardType;
        final Class<? extends ContextualCardController> mControllerClass;
        final Class<? extends ContextualCardRenderer> mRendererClass;
        final int mViewType;

        ControllerRendererMapping(int i, int i2, Class<? extends ContextualCardController> cls, Class<? extends ContextualCardRenderer> cls2) {
            this.mCardType = i;
            this.mViewType = i2;
            this.mControllerClass = cls;
            this.mRendererClass = cls2;
        }

        @Override // java.lang.Comparable
        public int compareTo(ControllerRendererMapping controllerRendererMapping) {
            return Comparator.comparingInt(new ToIntFunction() { // from class: com.android.car.developeroptions.homepage.contextualcards.-$$Lambda$ContextualCardLookupTable$ControllerRendererMapping$y2JkLNqhJpw1Avh-XHfirXah6Ug
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((ContextualCardLookupTable.ControllerRendererMapping) obj).mCardType;
                }
            }).thenComparingInt(new ToIntFunction() { // from class: com.android.car.developeroptions.homepage.contextualcards.-$$Lambda$ContextualCardLookupTable$ControllerRendererMapping$KAPRdjEJ-G9wGxKNfJKt4wgHeDM
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((ContextualCardLookupTable.ControllerRendererMapping) obj).mViewType;
                }
            }).compare(this, controllerRendererMapping);
        }
    }

    public static Class<? extends ContextualCardController> getCardControllerClass(int i) {
        for (ControllerRendererMapping controllerRendererMapping : LOOKUP_TABLE) {
            if (controllerRendererMapping.mCardType == i) {
                return controllerRendererMapping.mControllerClass;
            }
        }
        return null;
    }

    public static Class<? extends ContextualCardRenderer> getCardRendererClassByViewType(final int i) throws IllegalStateException {
        List list = (List) LOOKUP_TABLE.stream().filter(new Predicate() { // from class: com.android.car.developeroptions.homepage.contextualcards.-$$Lambda$ContextualCardLookupTable$VSdHIeBrq43Hn14o7VUT5Lm8NTQ
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ContextualCardLookupTable.lambda$getCardRendererClassByViewType$0(i, (ContextualCardLookupTable.ControllerRendererMapping) obj);
            }
        }).collect(Collectors.toList());
        if (list == null || list.isEmpty()) {
            Log.w("ContextualCardLookup", "No matching mapping");
            return null;
        }
        if (list.size() != 1) {
            throw new IllegalStateException("Have duplicate VIEW_TYPE in lookup table.");
        }
        return ((ControllerRendererMapping) list.get(0)).mRendererClass;
    }

    static /* synthetic */ boolean lambda$getCardRendererClassByViewType$0(int i, ControllerRendererMapping controllerRendererMapping) {
        return controllerRendererMapping.mViewType == i;
    }
}
