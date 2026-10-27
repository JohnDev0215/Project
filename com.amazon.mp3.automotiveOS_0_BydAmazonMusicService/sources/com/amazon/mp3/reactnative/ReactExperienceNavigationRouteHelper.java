package com.amazon.mp3.reactnative;

import android.net.Uri;
import android.os.Trace;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.navigation.ReactNativeDestinationHandler;
import com.amazon.mp3.util.Log;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.deeplink.UnknownDeeplinkException;
import com.amazon.music.destination.ReactNativeDestination;
import com.amazon.music.router.UnknownDestinationException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactExperienceNavigationRouteHelper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0012\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0004H\u0002J,\u0010\u000e\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\u0018\u0010\u0010\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004R\u001c\u0010\u0014\u001a\n \u0013*\u0004\u0018\u00010\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/reactnative/ReactExperienceNavigationRouteHelper;", "", "", "trackId", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "", "navigateToTrackDetailPageRoute", "route", "hostActivity", "navigateToRNExperience", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "id", "reportSource", "navigateToReportingModalRoute", "playlistId", "navigateToSongSearchRoute", "navigateToActivityFeedRoute", "navigateToFindLandingPageRoute", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ReactExperienceNavigationRouteHelper {
    public static final ReactExperienceNavigationRouteHelper INSTANCE;
    private static final String TAG;

    private ReactExperienceNavigationRouteHelper() {
    }

    static {
        ReactExperienceNavigationRouteHelper reactExperienceNavigationRouteHelper = new ReactExperienceNavigationRouteHelper();
        INSTANCE = reactExperienceNavigationRouteHelper;
        TAG = reactExperienceNavigationRouteHelper.getClass().getSimpleName();
    }

    public final void navigateToReportingModalRoute(String type, String id, String reportSource, FragmentActivity activity) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (type == null || type.length() == 0) {
            Log.error(TAG, "Empty type provided for report");
            return;
        }
        if (id == null || id.length() == 0) {
            Log.error(TAG, "Empty id provided for report");
            return;
        }
        if (reportSource == null || reportSource.length() == 0) {
            Log.error(TAG, "Empty reportSource provided for report");
            return;
        }
        navigateToRNExperience("/report?type=" + ((Object) type) + "&id=" + ((Object) id) + "&reportSource=" + ((Object) reportSource), activity);
    }

    public final void navigateToSongSearchRoute(String playlistId, FragmentActivity activity) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (playlistId == null || playlistId.length() == 0) {
            Log.error(TAG, "Empty Playlist Id Provided for Song Search");
            return;
        }
        navigateToRNExperience("/my/playlists/" + ((Object) playlistId) + "/add", activity);
    }

    public final void navigateToActivityFeedRoute(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        navigateToRNExperience("/activityFeed", activity);
    }

    @JvmStatic
    public static final void navigateToTrackDetailPageRoute(String trackId, FragmentActivity activity) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (trackId == null || trackId.length() == 0) {
            Log.error(TAG, "Missing trackId to open track detail page in react native.");
        } else {
            INSTANCE.navigateToRNExperience(Intrinsics.stringPlus("/tracks/", trackId), activity);
        }
    }

    public final void navigateToFindLandingPageRoute(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        new ReactNativeDestinationHandler().navigateToWithFragmentActivity(activity, new ReactNativeDestination("/find", "page", "", ""));
    }

    private final void navigateToRNExperience(String route, FragmentActivity hostActivity) throws ExecutionException, InterruptedException {
        Trace.beginSection("AMA.ReactExperienceNavigationRouteHelper.navigateToRNExperience");
        try {
            DeeplinksManager.get(hostActivity).handleInternal(Uri.parse(route));
        } catch (UnknownDeeplinkException e) {
            Log.error(TAG, "Exception handling deeplink", e);
        } catch (UnknownDestinationException e2) {
            Log.error(TAG, "Exception handling deeplink", e2);
        } catch (UnsupportedEncodingException e3) {
            Log.error(TAG, "Exception handling deeplink", e3);
        } catch (MalformedURLException e4) {
            Log.error(TAG, "Exception handling deeplink", e4);
        } catch (URISyntaxException e5) {
            Log.error(TAG, "Exception handling deeplink", e5);
        }
        Trace.endSection();
    }
}
