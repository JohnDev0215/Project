package com.amazon.mp3.identityprofiles;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.featuregate.PCAEvaluator;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.identity.profiles.DataProvider;
import com.amazon.music.identity.profiles.ProfileIdentityMainActivity;
import com.amazon.music.identity.profiles.datasource.mockprofile.MockProfileManager;
import com.amazon.music.identity.profiles.datasource.mockprofile.MockProfileManagerSingleton;
import com.amazon.music.identity.profiles.view.fragment.ProfilePickerFragment;
import com.amazon.music.identity.profiles.view.fragment.ProfileSettingFragment;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: IdentityActivityHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/identityprofiles/IdentityActivityHelper;", "", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class IdentityActivityHelper {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final Intent getIntentToProfilePicker(Context context) {
        return INSTANCE.getIntentToProfilePicker(context);
    }

    @JvmStatic
    public static final boolean isProfileNotSelected(Context context) {
        return INSTANCE.isProfileNotSelected(context);
    }

    /* JADX INFO: compiled from: IdentityActivityHelper.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/identityprofiles/IdentityActivityHelper$Companion;", "", "()V", "getIntentToProfilePicker", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "getIntentToProfileSetting", "getIntentWithTag", ParserUtil.TAG_QUERY_PARAM_NAME, "", "isProfileNotSelected", "", "isProfileSelected", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final boolean isProfileNotSelected(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (!PCAEvaluator.isEnabled$default(new PCAEvaluator(context), false, 1, null)) {
                return false;
            }
            MockProfileManager mockProfileManager = MockProfileManagerSingleton.INSTANCE.get();
            return mockProfileManager != null && !mockProfileManager.getIsProfileSelected();
        }

        @JvmStatic
        public final boolean isProfileSelected(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (!PCAEvaluator.isEnabled$default(new PCAEvaluator(context), false, 1, null)) {
                return false;
            }
            MockProfileManager mockProfileManager = MockProfileManagerSingleton.INSTANCE.get();
            return mockProfileManager != null && mockProfileManager.getIsProfileSelected();
        }

        @JvmStatic
        public final Intent getIntentToProfilePicker(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            String tag = ProfilePickerFragment.INSTANCE.getTAG();
            Intrinsics.checkNotNullExpressionValue(tag, "ProfilePickerFragment.TAG");
            return getIntentWithTag(context, tag);
        }

        public final Intent getIntentToProfileSetting(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            String tag = ProfileSettingFragment.INSTANCE.getTAG();
            Intrinsics.checkNotNullExpressionValue(tag, "ProfileSettingFragment.TAG");
            return getIntentWithTag(context, tag);
        }

        private final Intent getIntentWithTag(Context context, String tag) {
            DataProvider.INSTANCE.setStyleSheetObservable(StyleSheetProvider.getStyleSheetObservable().observeOn(Schedulers.io()));
            return ProfileIdentityMainActivity.INSTANCE.getStartIntent(context, tag);
        }
    }
}
