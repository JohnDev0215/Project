package com.ts.androidauto.aap;

import com.ts.androidauto.aap.data.MediaMetadata;
import com.ts.androidauto.aap.data.NavigationPositionData;
import com.ts.androidauto.aap.data.NavigationStateData;
import com.ts.androidauto.aap.data.PhoneStatusData;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public interface AapDisPlayCallback {
    void notifyMediaMetadataChange(MediaMetadata mediaMetadata);

    void notifyMediaProgressChange(int i);

    void notifyMediaStatusChange(int i);

    void notifyPhoneStatus(ArrayList<PhoneStatusData> arrayList);

    void onNavigationCurrentPosition(NavigationPositionData navigationPositionData);

    void onNavigationState(NavigationStateData navigationStateData);

    void onStatus(int i);
}
