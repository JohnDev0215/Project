package com.spotify.connectivity.productstate;

import com.google.common.hash.d;
import com.spotify.base.java.logging.Logger;
import com.spotify.connectivity.flags.Flags;
import com.spotify.connectivity.productstate.RxProductState;
import java.util.Map;
import p.k1;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ProductStateUtil {
    private static final int EXPECTED_HASH_VALUE = 1929113152;
    private static final String ON_DEMAND_TRIAL_ACTIVATE = "active";
    private static final String TAG_PREMIUM = "premium";

    private static final boolean getBooleanValueForKey(Map<String, String> map, String str) {
        try {
            return BooleanProductStateValueConverter.convert(map.get(str));
        } catch (ConvertProductStateValueException e) {
            Logger.c(e, "Unable to extract product state", new Object[0]);
            return false;
        }
    }

    public static final boolean hasBlendAccess(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return getBooleanValueForKey(map, RxProductState.Keys.KEY_HAS_BLEND_ACCESS);
    }

    public static final boolean isApplyChildContentRestrictionsEnabled(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return getBooleanValueForKey(map, RxProductState.Keys.KEY_APPLY_CHILD_CONTENT_RESTRICTIONS);
    }

    public static final boolean isCheetaraEnabled(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return getBooleanValueForKey(map, RxProductState.Keys.KEY_IS_CHEETARA_ENABLED);
    }

    public static final boolean isConnectEntryPointsEnabled(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return getBooleanValueForKey(map, RxProductState.Keys.KEY_ALLOW_CONNECT_ENTRY_POINTS, true);
    }

    public static final boolean isCuttlefishEnabled(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return getBooleanValueForKey(map, RxProductState.Keys.KEY_CUTTLEFISH_ENABLED);
    }

    public static final boolean isDisableBlockedContentEnabled(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return getBooleanValueForKey(map, RxProductState.Keys.KEY_DISABLE_BLOCKED_CONTENT);
    }

    public static final boolean isEuterpeEnabled(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return getBooleanValueForKey(map, RxProductState.Keys.KEY_IS_EUTERPE);
    }

    public static final boolean isExplicitContentEnabled(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return !getBooleanValueForKey(map, RxProductState.Keys.KEY_EXPLICIT_CONTENT_FILTERING);
    }

    public static final boolean isLingoAuroraEnabled(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return getBooleanValueForKey(map, RxProductState.Keys.KEY_IS_LINGO_AURORA);
    }

    public static final boolean isMarquesEnabled(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return getBooleanValueForKey(map, RxProductState.Keys.KEY_MARQUES_ENABLED);
    }

    public static final boolean isMermaidEnabled(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return getBooleanValueForKey(map, RxProductState.Keys.KEY_IS_MERMAID);
    }

    public static final boolean isNftEnabled(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return !getBooleanValueForKey(map, RxProductState.Keys.KEY_NFT_DISABLED);
    }

    public static final boolean isObfuscateRestrictedTracksEnabled(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return getBooleanValueForKey(map, RxProductState.Keys.KEY_OBFUSCATE_RESTRICTED_TRACKS);
    }

    public static final boolean isOfflineEnabled(Flags flags) {
        v03.j(flags, "flags");
        return ((Boolean) flags.get(ProductStateFlags.OFFLINE)).booleanValue() && !((Boolean) flags.get(ProductStateFlags.LIMITED_OFFLINE)).booleanValue();
    }

    public static final boolean isOnDemandTrialActivate(Flags flags) {
        v03.j(flags, "flags");
        return v03.b(ON_DEMAND_TRIAL_ACTIVATE, flags.get(ProductStateFlags.ON_DEMAND_TRIAL));
    }

    public static final boolean isPigeonEnabled(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return getBooleanValueForKey(map, RxProductState.Keys.KEY_PIGEON_USER);
    }

    public static final boolean isPodcastsEnabled(Flags flags) {
        v03.j(flags, "flags");
        return ((Boolean) flags.get(ProductStateFlags.PODCASTS_ENABLED)).booleanValue();
    }

    public static final boolean isPremium(Flags flags) {
        v03.j(flags, "flags");
        return "premium".equals(flags.get(ProductStateFlags.PREMIUM));
    }

    public static final boolean isPuffinEnabled(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return getBooleanValueForKey(map, RxProductState.Keys.KEY_PUFFIN_USER);
    }

    public static final boolean isSeadragonEnabled(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return getBooleanValueForKey(map, RxProductState.Keys.KEY_SEADRAGON_ENABLED);
    }

    private static final boolean isShuffleRestricted(String str) {
        return str == null || ((k1) d.a()).b(str).b() == EXPECTED_HASH_VALUE;
    }

    public static final boolean isSuperstarEnabled(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return getBooleanValueForKey(map, RxProductState.Keys.KEY_SUPERSTAR_ENABLED);
    }

    public static final boolean isTunaEnabled(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return getBooleanValueForKey(map, RxProductState.Keys.KEY_IS_TUNA_ENABLED);
    }

    public static final boolean isVideoShowsEnabled(Flags flags) {
        v03.j(flags, "flags");
        return ((Boolean) flags.get(ProductStateFlags.SHOWS_COLLECTION_VIDEO_ENABLED)).booleanValue();
    }

    public static final boolean isYourLibraryKidsRestrictedContentEnabled(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return getBooleanValueForKey(map, RxProductState.Keys.KEY_YL_KIDS_RESTRICTED_CONTENT);
    }

    public static final boolean onDemandEnabled(Flags flags) {
        v03.j(flags, "flags");
        return !((Boolean) flags.get(ProductStateFlags.SHUFFLE_RESTRICTED)).booleanValue();
    }

    public static final boolean onDemandHasChangedValue(Flags flags, Flags flags2) {
        v03.j(flags2, "newFlags");
        return Flags.Util.INSTANCE.hasChangedValue(ProductStateFlags.SHUFFLE_RESTRICTED, flags, flags2);
    }

    public static final boolean isOfflineEnabled(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return getBooleanValueForKey(map, RxProductState.Keys.KEY_OFFLINE) && !getBooleanValueForKey(map, RxProductState.Keys.KEY_LIMITED_OFFLINE);
    }

    public static final boolean isPodcastsEnabled(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return getBooleanValueForKey(map, "shows-collection");
    }

    public static final boolean isPremium(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return v03.b("premium", map.get(RxProductState.Keys.KEY_TYPE));
    }

    public static final boolean onDemandEnabled(Map<String, String> map) {
        v03.j(map, "productStateMap");
        return !isShuffleRestricted(map.get(RxProductState.Keys.KEY_STREAMING_RULES));
    }

    private static final boolean getBooleanValueForKey(Map<String, String> map, String str, boolean z) {
        try {
            return BooleanProductStateValueConverter.convert(map.get(str), z);
        } catch (ConvertProductStateValueException e) {
            Logger.c(e, "Unable to extract product state", new Object[0]);
            return z;
        }
    }
}
