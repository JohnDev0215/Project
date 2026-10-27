package com.amazon.mp3.weblab;

import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.util.Log;
import com.amazon.music.platform.data.WeblabTreatment;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes4.dex */
public enum ExperimentWeblabs {
    DM_CURATE_NEW_PLAYLIST_FLOW("DM_CURATE_NEW_PLAYLIST_FLOW_AA_248717", "DM_CURATE_NEW_PLAYLIST_FLOW_AB_248699"),
    DM_CURATE_PLAYLIST_RECOMMENDATIONS("DM_CURATE_PLAYLIST_RECOMMENDATIONS_AA_259472", "DM_CURATE_PLAYLIST_RECOMMENDATIONS_AB_309858"),
    DM_CURATE_PLAYLIST_RECOMMENDATIONS_TABLET("DM_CURATE_PLAYLIST_RECOMMENDATIONS_TABLET_AA_300454", "DM_CURATE_PLAYLIST_RECOMMENDATIONS_TABLET_AB_309861"),
    DM_CURATE_PLAYLIST_DETAIL_BAUHAUS("DM_CURATE_PLAYLIST_DETAIL_BAUHAUS_AA_300468", "DM_CURATE_PLAYLIST_DETAIL_BAUHAUS_AB_309849"),
    DM_CURATE_PLAYLIST_DETAIL_BAUHAUS_TABLET("DM_CURATE_PLAYLIST_DETAIL_BAUHAUS_TABLET_AA_300483", "DM_CURATE_PLAYLIST_DETAIL_BAUHAUS_TABLET_AB_309854"),
    DM_SUBS_CONCURRENCY_ONE_CLICK_SIGNUP("DIGITAL_MUSIC_1CLICK_CONCURRENCY_FAMILYPLAN_AA_265061", "DIGITAL_MUSIC_1CLICK_CONCURRENCY_FAMILYPLAN_AB_265063"),
    DM_CURATE_MY_LIKES_IN_MADE_FOR_YOU_FIRE_TABLET("DM_CURATE_MY_LIKES_IN_MADE_FOR_YOU_FIRE_TABLET_AA_316179", "DM_CURATE_MY_LIKES_IN_MADE_FOR_YOU_FIRE_TABLET_AB_316176"),
    DM_LPN_OFFLINE_DOWNLOAD_BUTTON("DM_LPN_OFFLINE_DOWNLOAD_BUTTON_AA_312300", "DM_LPN_OFFLINE_DOWNLOAD_BUTTON_308758"),
    MUSICSTATIONS_ENTITY_PLAYLIST_CONTEXT_MENU("MUSICSTATIONS_ENTITY_PLAYLIST_CONTEXT_MENU_AA_399282", "MUSICSTATIONS_ENTITY_PLAYLIST_CONTEXT_MENU_AB_398842"),
    DM_LPN_OFFLINE_DOWNLOAD_BUTTON_PHASE_2("DM_LPN_OFFLINE_DOWNLOAD_BUTTON_PHASE_2_BH_496634", "DM_LPN_OFFLINE_DOWNLOAD_BUTTON_PHASE_2_BH_496635"),
    DM_ASTERIX_ANDROID_HAPTIC_FEEDBACK_ON_PLAYBACK("DM_ASTERIX_ANDROID_HAPTIC_FEEDBACK_ON_PLAYBACK_AA_568475", "DM_ASTERIX_ANDROID_HAPTIC_FEEDBACK_ON_PLAYBACK_AB_568495");

    private final String aaWeblab;
    private final String abWeblab;
    private WeblabTreatment treatment = WeblabTreatment.C;

    ExperimentWeblabs(final String aaWeblab, final String abWeblab) {
        this.aaWeblab = aaWeblab;
        this.abWeblab = abWeblab;
    }

    public WeblabTreatment getTreatment() {
        final LegacyWeblabController legacyWeblabController = LegacyWeblabController.getInstance();
        BootstrapSingletonTask.get().registerUserObserver().subscribe(new Action1() { // from class: com.amazon.mp3.weblab.ExperimentWeblabs$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$getTreatment$0(legacyWeblabController, (Void) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.weblab.ExperimentWeblabs$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                Log.warning("ExperimentWeblabs", "Failed to get bootstrap, falling back to old My Music experience");
            }
        });
        return this.treatment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getTreatment$0(LegacyWeblabController legacyWeblabController, Void r2) {
        String str = this.aaWeblab;
        if (str != null) {
            legacyWeblabController.getTreatmentAndRecordTrigger(str);
        }
        this.treatment = legacyWeblabController.getTreatmentAndRecordTrigger(this.abWeblab);
    }

    public String getAaWeblab() {
        return this.aaWeblab;
    }

    public String getAbWeblab() {
        return this.abWeblab;
    }
}
