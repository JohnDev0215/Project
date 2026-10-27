package com.amazon.mp3.net.stratus;

import UpsellInterface.v1_0.UpsellType;
import android.content.Context;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.AccountManagerKt;
import com.amazon.music.account.AccountManager;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.User;
import com.amazon.music.find.api.RemoteSearchApi;
import com.amazon.music.inappmessaging.internal.model.Splash;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: StratusFeature.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0001\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0011B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\b\u0010\u000b\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/net/stratus/StratusFeature;", "", "id", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getId", "()Ljava/lang/String;", "getFeatureStatus", "", "context", "Landroid/content/Context;", "toString", "BAKER", UpsellType.FREE, RemoteSearchApi.HD_ASSET_QUALITY, "TEENS", "IVY_SPATIAL_AUDIO", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum StratusFeature {
    BAKER("BAKER"),
    FREE(UpsellType.FREE),
    HD(RemoteSearchApi.HD_ASSET_QUALITY),
    TEENS("TEENS"),
    IVY_SPATIAL_AUDIO("IVY");

    private final String id;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = StratusFeature.class.getSimpleName();

    StratusFeature(String str) {
        this.id = str;
    }

    public final String getId() {
        return this.id;
    }

    public final boolean getFeatureStatus(Context context) {
        return INSTANCE.isFeatureLaunched(this, context);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.id;
    }

    /* JADX INFO: compiled from: StratusFeature.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rR\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/net/stratus/StratusFeature$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "isFeatureLaunched", "", "featureId", "Lcom/amazon/mp3/net/stratus/StratusFeature;", "context", "Landroid/content/Context;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return StratusFeature.TAG;
        }

        public final boolean isFeatureLaunched(StratusFeature featureId, Context context) {
            String customerId;
            Intrinsics.checkNotNullParameter(featureId, "featureId");
            if (context == null) {
                return false;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
                AccountManager accountManager = AccountManagerSingleton.get();
                Intrinsics.checkNotNullExpressionValue(accountManager, "get()");
                Object objUserResult = AccountManagerKt.userResult(accountManager);
                if (Result.m2816isFailureimpl(objUserResult)) {
                    objUserResult = null;
                }
                User user = (User) objUserResult;
                String str = "";
                if (user != null && (customerId = user.getCustomerId()) != null) {
                    str = customerId;
                }
                jSONObject.put("customerId", str);
                jSONObject.put(Splash.PARAMS_DEVICE_TYPE, accountCredentialStorage.getDeviceType());
                jSONObject.put(Splash.PARAMS_DEVICE_ID, accountCredentialStorage.getDeviceId());
                jSONObject.put(Splash.PARAMS_MUSIC_TERRITORY, AccountDetailUtil.getMusicTerritoryOfResidence());
                jSONObject.put("weblab", featureId.toString());
                try {
                    return StratusRequest.IsFeatureLaunched.execute(jSONObject).getBoolean("isFeatureLaunched");
                } catch (AbstractHttpClient.CanceledException e) {
                    Log.error(StratusFeature.INSTANCE.getTAG(), "Error obtaining StratusRequest response", e);
                    return false;
                } catch (AbstractHttpClient.FailedException e2) {
                    Log.error(StratusFeature.INSTANCE.getTAG(), "Error obtaining StratusRequest response", e2);
                    return false;
                } catch (AbstractHttpClient.IncompleteResultException e3) {
                    Log.error(StratusFeature.INSTANCE.getTAG(), "Error obtaining StratusRequest response", e3);
                    return false;
                } catch (AbstractHttpClient.UnexpectedHttpStatusException e4) {
                    Log.error(StratusFeature.INSTANCE.getTAG(), "Error obtaining StratusRequest response", e4);
                    return false;
                } catch (ServiceException e5) {
                    Log.error(StratusFeature.INSTANCE.getTAG(), "Error obtaining StratusRequest response", e5);
                    return false;
                } catch (JSONException e6) {
                    Log.error(StratusFeature.INSTANCE.getTAG(), "Error obtaining StratusRequest response", e6);
                    return false;
                }
            } catch (JSONException e7) {
                Log.error(StratusFeature.INSTANCE.getTAG(), "Error inserting request parameters", e7);
                return false;
            }
        }
    }
}
