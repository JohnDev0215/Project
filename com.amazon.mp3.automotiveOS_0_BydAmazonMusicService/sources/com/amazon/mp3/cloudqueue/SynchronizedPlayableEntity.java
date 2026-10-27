package com.amazon.mp3.cloudqueue;

import androidx.annotation.AnyThread;
import androidx.annotation.Nullable;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.musicplayqueueservice.client.v2.track.Capability;
import com.amazon.musicplayqueueservice.client.v2.track.types.ActionEnumValues;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
@AnyThread
public abstract class SynchronizedPlayableEntity {
    protected Map<String, PlaybackActionCapability> mPlaybackCapabilityMap = new HashMap();

    public PlaybackActionCapability getPlaybackActionCapability(String action) {
        return this.mPlaybackCapabilityMap.get(action);
    }

    protected void processActionMap(List<Capability> capabilities) {
        HashMap map = new HashMap();
        if (capabilities != null) {
            for (Capability capability : capabilities) {
                map.put(capability.getAction(), capability);
            }
        }
        for (String str : ActionEnumValues.values()) {
            if (str.equals("TRANSFER_QUEUE")) {
                Capability capability2 = (Capability) map.get("TRANSFER_QUEUE");
                this.mPlaybackCapabilityMap.put("CASTING", capability2 == null ? getDefaultActionCapability() : new PlaybackActionCapability(capability2));
            } else if (str.equals("LOOP")) {
                Capability capability3 = (Capability) map.get("LOOP");
                this.mPlaybackCapabilityMap.put("LOOP", capability3 == null ? getDefaultActionCapability() : new PlaybackActionCapability(capability3));
            } else if (str.equals("NEXT")) {
                Capability capability4 = (Capability) map.get("NEXT");
                this.mPlaybackCapabilityMap.put("NEXT", capability4 == null ? getDefaultSkipNextActionCapability() : new PlaybackActionCapability(capability4));
            } else if (str.equals("PREVIOUS")) {
                Capability capability5 = (Capability) map.get("PREVIOUS");
                this.mPlaybackCapabilityMap.put("PREVIOUS", capability5 == null ? getDefaultActionCapability() : new PlaybackActionCapability(capability5));
            } else if (str.equals("SCRUB_BACKWARD")) {
                Capability capability6 = (Capability) map.get("SCRUB_BACKWARD");
                this.mPlaybackCapabilityMap.put("SCRUB_BACKWARD", capability6 == null ? getDefaultActionCapability() : new PlaybackActionCapability(capability6));
            } else if (str.equals("SCRUB_FORWARD")) {
                Capability capability7 = (Capability) map.get("SCRUB_FORWARD");
                this.mPlaybackCapabilityMap.put("SCRUB_FORWARD", capability7 == null ? getDefaultActionCapability() : new PlaybackActionCapability(capability7));
            } else if (str.equals("RATE_TRACK")) {
                Capability capability8 = (Capability) map.get("RATE_TRACK");
                this.mPlaybackCapabilityMap.put("RATE_TRACK", capability8 == null ? getDefaultActionCapability() : new PlaybackActionCapability(capability8));
            } else if (str.equals("SHUFFLE")) {
                Capability capability9 = (Capability) map.get("SHUFFLE");
                this.mPlaybackCapabilityMap.put("SHUFFLE", capability9 == null ? getDefaultActionCapability() : new PlaybackActionCapability(capability9));
            } else if (str.equals("LOOP_ONE")) {
                Capability capability10 = (Capability) map.get("LOOP_ONE");
                this.mPlaybackCapabilityMap.put("LOOP_ONE", capability10 == null ? getDefaultActionCapability() : new PlaybackActionCapability(capability10));
            }
        }
    }

    private PlaybackActionCapability getDefaultActionCapability() {
        return new PlaybackActionCapability(null) { // from class: com.amazon.mp3.cloudqueue.SynchronizedPlayableEntity.1
            @Override // com.amazon.mp3.cloudqueue.PlaybackActionCapability
            public boolean canDisplay() {
                return false;
            }

            @Override // com.amazon.mp3.cloudqueue.PlaybackActionCapability
            public int getActionStatus() {
                return 1;
            }

            @Override // com.amazon.mp3.cloudqueue.PlaybackActionCapability
            @Nullable
            public String getIotThingShadowName() {
                return null;
            }
        };
    }

    private PlaybackActionCapability getDefaultSkipNextActionCapability() {
        if (UserSubscriptionUtil.isNightwingOnly()) {
            return new PlaybackActionCapability(null) { // from class: com.amazon.mp3.cloudqueue.SynchronizedPlayableEntity.2
                @Override // com.amazon.mp3.cloudqueue.PlaybackActionCapability
                public boolean canDisplay() {
                    return true;
                }

                @Override // com.amazon.mp3.cloudqueue.PlaybackActionCapability
                public int getActionStatus() {
                    return 2;
                }

                @Override // com.amazon.mp3.cloudqueue.PlaybackActionCapability
                @Nullable
                public String getIotThingShadowName() {
                    return null;
                }
            };
        }
        return getDefaultActionCapability();
    }
}
