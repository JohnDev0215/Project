package com.android.car.audio;

import android.content.pm.PackageManager;
import android.media.AudioFocusInfo;
import android.media.AudioManager;
import android.media.audiopolicy.AudioPolicy;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import com.android.car.CarLog;
import com.android.internal.util.Preconditions;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes3.dex */
class CarZonesAudioFocus extends AudioPolicy.AudioPolicyFocusListener {
    private AudioPolicy mAudioPolicy;
    private CarAudioService mCarAudioService;
    private final boolean mDelayedFocusEnabled;
    private final Map<Integer, CarAudioFocus> mFocusZones = new HashMap();

    CarZonesAudioFocus(AudioManager audioManager, PackageManager packageManager, SparseArray<CarAudioZone> carAudioZones, CarAudioSettings carAudioSettings, boolean enableDelayedAudioFocus) {
        Objects.requireNonNull(audioManager);
        Objects.requireNonNull(packageManager);
        Objects.requireNonNull(carAudioZones);
        Objects.requireNonNull(carAudioSettings);
        Preconditions.checkArgument(carAudioZones.size() != 0, "There must be a minimum of one audio zone");
        for (int i = 0; i < carAudioZones.size(); i++) {
            CarAudioZone audioZone = carAudioZones.valueAt(i);
            int audioZoneId = audioZone.getId();
            if (Log.isLoggable(CarLog.TAG_AUDIO, 3)) {
                Log.d(CarLog.TAG_AUDIO, "CarZonesAudioFocus adding new zone " + audioZoneId);
            }
            CarAudioFocus zoneFocusListener = new CarAudioFocus(audioManager, packageManager, new FocusInteraction(carAudioSettings), enableDelayedAudioFocus);
            this.mFocusZones.put(Integer.valueOf(audioZoneId), zoneFocusListener);
        }
        this.mDelayedFocusEnabled = enableDelayedAudioFocus;
    }

    ArrayList<AudioFocusInfo> getAudioFocusLosersForUid(int uid, int zoneId) {
        CarAudioFocus focus = this.mFocusZones.get(Integer.valueOf(zoneId));
        return focus.getAudioFocusLosersForUid(uid);
    }

    ArrayList<AudioFocusInfo> getAudioFocusHoldersForUid(int uid, int zoneId) {
        CarAudioFocus focus = this.mFocusZones.get(Integer.valueOf(zoneId));
        return focus.getAudioFocusHoldersForUid(uid);
    }

    void transientlyLoseInFocusInZone(ArrayList<AudioFocusInfo> afiList, int zoneId) {
        CarAudioFocus focus = this.mFocusZones.get(Integer.valueOf(zoneId));
        for (AudioFocusInfo info : afiList) {
            focus.removeAudioFocusInfoAndTransientlyLoseFocus(info);
        }
    }

    int reevaluateAndRegainAudioFocus(AudioFocusInfo afi) {
        CarAudioFocus focus = getFocusForAudioFocusInfo(afi);
        return focus.reevaluateAndRegainAudioFocus(afi);
    }

    void setOwningPolicy(CarAudioService carAudioService, AudioPolicy parentPolicy) {
        this.mAudioPolicy = parentPolicy;
        this.mCarAudioService = carAudioService;
        Iterator<Integer> it = this.mFocusZones.keySet().iterator();
        while (it.hasNext()) {
            int zoneId = it.next().intValue();
            this.mFocusZones.get(Integer.valueOf(zoneId)).setOwningPolicy(this.mAudioPolicy);
        }
    }

    public void onAudioFocusRequest(AudioFocusInfo afi, int requestResult) {
        CarAudioFocus focus = getFocusForAudioFocusInfo(afi);
        focus.onAudioFocusRequest(afi, requestResult);
    }

    public void onAudioFocusAbandon(AudioFocusInfo afi) {
        CarAudioFocus focus = getFocusForAudioFocusInfo(afi);
        focus.onAudioFocusAbandon(afi);
    }

    private CarAudioFocus getFocusForAudioFocusInfo(AudioFocusInfo afi) {
        int zoneId = this.mCarAudioService.getZoneIdForUid(afi.getClientUid());
        Bundle bundle = afi.getAttributes().getBundle();
        if (bundle != null) {
            int bundleZoneId = bundle.getInt("android.car.media.AUDIOFOCUS_EXTRA_REQUEST_ZONE_ID", -1);
            if (this.mCarAudioService.isAudioZoneIdValid(bundleZoneId)) {
                if (Log.isLoggable(CarLog.TAG_AUDIO, 3)) {
                    Log.d(CarLog.TAG_AUDIO, "getFocusForAudioFocusInfo valid zoneId " + bundleZoneId + " with bundle request for client " + afi.getClientId());
                }
                zoneId = bundleZoneId;
            } else {
                Log.w(CarLog.TAG_AUDIO, "getFocusForAudioFocusInfo invalid zoneId " + bundleZoneId + " with bundle request for client " + afi.getClientId() + ", dispatching focus request to zoneId " + zoneId);
            }
        }
        CarAudioFocus focus = this.mFocusZones.get(Integer.valueOf(zoneId));
        return focus;
    }

    void dump(String indent, PrintWriter writer) {
        writer.printf("%s*CarZonesAudioFocus*\n", indent);
        writer.printf("%s\tDelayed Focus Enabled: %b\n", indent, Boolean.valueOf(this.mDelayedFocusEnabled));
        writer.printf("%s\tCar Zones Audio Focus Listeners:\n", indent);
        Integer[] keys = (Integer[]) this.mFocusZones.keySet().stream().sorted().toArray(new IntFunction() { // from class: com.android.car.audio.-$$Lambda$CarZonesAudioFocus$FUzFR3OoKieFU1_dV7-Sxh-L5Ug
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CarZonesAudioFocus.lambda$dump$0(i);
            }
        });
        for (Integer zoneId : keys) {
            writer.printf("%s\tZone Id: %s\n", indent, zoneId.toString());
            this.mFocusZones.get(zoneId).dump(indent + "\t", writer);
        }
    }

    static /* synthetic */ Integer[] lambda$dump$0(int x$0) {
        return new Integer[x$0];
    }

    public void updateUserForZoneId(int audioZoneId, int userId) {
        Preconditions.checkArgument(this.mCarAudioService.isAudioZoneIdValid(audioZoneId), "Invalid zoneId %d", new Object[]{Integer.valueOf(audioZoneId)});
        this.mFocusZones.get(Integer.valueOf(audioZoneId)).getFocusInteraction().setUserIdForSettings(userId);
    }
}
