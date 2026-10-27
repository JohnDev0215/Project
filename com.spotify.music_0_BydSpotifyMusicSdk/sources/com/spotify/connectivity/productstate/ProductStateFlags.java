package com.spotify.connectivity.productstate;

import com.google.common.hash.d;
import com.spotify.connectivity.flags.BooleanFlag;
import com.spotify.connectivity.flags.Overridable;
import com.spotify.connectivity.flags.StringFlag;
import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.connectivity.productstateflags.FlagMaker;
import com.spotify.connectivity.productstateflags.ProductStateSource;
import com.spotify.connectivity.productstateflags.SimpleFlagsListProvider;
import p.k1;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ProductStateFlags extends SimpleFlagsListProvider {
    private static final int EXPECTED_HASH_VALUE = 1929113152;
    public static final ProductStateFlags INSTANCE = new ProductStateFlags();
    public static final BooleanFlag LIMITED_OFFLINE;
    public static final BooleanFlag OFFLINE;
    public static final StringFlag ON_DEMAND_TRIAL;
    public static final BooleanFlag PODCASTS_ENABLED;
    public static final StringFlag PREMIUM;
    public static final BooleanFlag SHOWS_COLLECTION_VIDEO_ENABLED;
    public static final BooleanFlag SHUFFLE_RESTRICTED;

    static {
        FlagMaker flagMaker = FlagMaker.INSTANCE;
        Overridable overridable = Overridable.DEBUG;
        PREMIUM = flagMaker.makeStringProductStateFlag(RxProductState.Keys.KEY_TYPE, overridable);
        final ProductStateSource productStateSource = new ProductStateSource(RxProductState.Keys.KEY_STREAMING_RULES);
        final Overridable overridable2 = Overridable.NEVER;
        SHUFFLE_RESTRICTED = new BooleanFlag(productStateSource, overridable2) { // from class: com.spotify.connectivity.productstate.ProductStateFlags$SHUFFLE_RESTRICTED$1
            @Override // com.spotify.connectivity.flags.BooleanFlag, com.spotify.connectivity.flags.Flag
            public Boolean mapValue(String str) {
                v03.j(str, "value");
                return Boolean.valueOf(ProductStateFlags.isShuffleRestricted(str));
            }
        };
        OFFLINE = flagMaker.makeBooleanProductStateFlag(RxProductState.Keys.KEY_OFFLINE, overridable);
        LIMITED_OFFLINE = flagMaker.makeBooleanProductStateFlag(RxProductState.Keys.KEY_LIMITED_OFFLINE, overridable);
        ON_DEMAND_TRIAL = flagMaker.makeStringProductStateFlag(RxProductState.Keys.KEY_ON_DEMAND_TRIAL, overridable);
        PODCASTS_ENABLED = flagMaker.makeBooleanProductStateFlag("shows-collection", overridable);
        SHOWS_COLLECTION_VIDEO_ENABLED = flagMaker.makeBooleanProductStateFlag(RxProductState.Keys.KEY_SHOWS_COLLECTION_VIDEO, overridable);
    }

    private ProductStateFlags() {
    }

    public static /* synthetic */ void getLIMITED_OFFLINE$annotations() {
    }

    public static /* synthetic */ void getOFFLINE$annotations() {
    }

    public static /* synthetic */ void getON_DEMAND_TRIAL$annotations() {
    }

    public static /* synthetic */ void getPODCASTS_ENABLED$annotations() {
    }

    public static /* synthetic */ void getPREMIUM$annotations() {
    }

    public static /* synthetic */ void getSHOWS_COLLECTION_VIDEO_ENABLED$annotations() {
    }

    public static final boolean isShuffleRestricted(String str) {
        return str == null || ((k1) d.a()).b(str).b() == EXPECTED_HASH_VALUE;
    }
}
