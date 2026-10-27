package com.amazon.mp3.account.signintasks;

import com.amazon.mp3.weblab.ExperimentWeblabs;
import com.amazon.music.signin.SignInTask;

/* JADX INFO: loaded from: classes3.dex */
public class WeblabTreatmentsTask implements SignInTask {
    private static final String TAG = "WeblabTreatmentsTask";

    @Override // com.amazon.music.signin.SignInTask
    public void execute() throws Exception {
        ExperimentWeblabs.DM_CURATE_PLAYLIST_DETAIL_BAUHAUS.getTreatment();
        ExperimentWeblabs.DM_CURATE_PLAYLIST_DETAIL_BAUHAUS_TABLET.getTreatment();
        ExperimentWeblabs.DM_CURATE_PLAYLIST_RECOMMENDATIONS.getTreatment();
        ExperimentWeblabs.DM_CURATE_PLAYLIST_RECOMMENDATIONS_TABLET.getTreatment();
    }

    @Override // com.amazon.music.signin.SignInTask
    public String getName() {
        return TAG;
    }
}
