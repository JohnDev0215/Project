package com.amazon.mp3.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.activity.navigation.FragmentController;
import com.amazon.mp3.auto.carmode.fragment.FragmentCarModeFabPreferences;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.BetaDialogPurpose;
import com.amazon.mp3.fragment.FragmentAudioStats;
import com.amazon.mp3.fragment.FragmentBetaDialog;
import com.amazon.mp3.fragment.FragmentKatanaPreferences;
import com.amazon.mp3.fragment.FragmentPushTopics;
import com.amazon.mp3.fragment.KatanaPreferencesPurpose;
import com.amazon.mp3.fragment.PushTopicsPurpose;
import com.amazon.mp3.fragment.RedownloadMusicDialogPurpose;
import com.amazon.mp3.fragment.RedownloadOfflineMusicFragment;
import com.amazon.mp3.language.preference.fragment.FragmentAppDisplayLanguagePreferences;
import com.amazon.mp3.library.item.RedownloadableTrack;
import com.amazon.mp3.service.job.JobSessionActivity;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WideDialogActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0002\u0010\u000bJ\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/activity/WideDialogActivity;", "Lcom/amazon/mp3/service/job/JobSessionActivity;", "()V", "mFragmentController", "Lcom/amazon/mp3/activity/navigation/FragmentController;", "mMaxHeight", "", "getFragmentForPurpose", "Landroidx/fragment/app/Fragment;", "purpose", "", "(Ljava/lang/Integer;)Landroidx/fragment/app/Fragment;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onTouchEvent", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WideDialogActivity extends JobSessionActivity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FragmentController mFragmentController;
    private double mMaxHeight;

    @JvmStatic
    @JvmOverloads
    public static final void showActivity(Context context, WideDialogActivityPurpose wideDialogActivityPurpose) {
        INSTANCE.showActivity(context, wideDialogActivityPurpose);
    }

    @JvmStatic
    @JvmOverloads
    public static final void showActivity(Context context, WideDialogActivityPurpose wideDialogActivityPurpose, int i) {
        INSTANCE.showActivity(context, wideDialogActivityPurpose, i);
    }

    @JvmStatic
    @JvmOverloads
    public static final void showActivity(Context context, WideDialogActivityPurpose wideDialogActivityPurpose, int i, RedownloadableTrack redownloadableTrack) {
        INSTANCE.showActivity(context, wideDialogActivityPurpose, i, redownloadableTrack);
    }

    /* JADX INFO: compiled from: WideDialogActivity.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/activity/WideDialogActivity$Companion;", "", "()V", "PURPOSE", "", "REDOWNLOADABLE_TRACK", "THEME", "showActivity", "", "context", "Landroid/content/Context;", "purpose", "Lcom/amazon/mp3/activity/WideDialogActivityPurpose;", "theme", "", "redownloadableTrack", "Lcom/amazon/mp3/library/item/RedownloadableTrack;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @JvmOverloads
        public final void showActivity(Context context, WideDialogActivityPurpose purpose) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(purpose, "purpose");
            showActivity$default(this, context, purpose, 0, null, 12, null);
        }

        @JvmStatic
        @JvmOverloads
        public final void showActivity(Context context, WideDialogActivityPurpose purpose, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(purpose, "purpose");
            showActivity$default(this, context, purpose, i, null, 8, null);
        }

        private Companion() {
        }

        public static /* synthetic */ void showActivity$default(Companion companion, Context context, WideDialogActivityPurpose wideDialogActivityPurpose, int i, RedownloadableTrack redownloadableTrack, int i2, Object obj) {
            if ((i2 & 4) != 0) {
                i = R.style.Theme_AmazonMP3_WideDialog;
            }
            if ((i2 & 8) != 0) {
                redownloadableTrack = null;
            }
            companion.showActivity(context, wideDialogActivityPurpose, i, redownloadableTrack);
        }

        @JvmStatic
        @JvmOverloads
        public final void showActivity(Context context, WideDialogActivityPurpose purpose, int theme, RedownloadableTrack redownloadableTrack) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(purpose, "purpose");
            Intent intent = new Intent(context, (Class<?>) WideDialogActivity.class);
            intent.putExtra("purpose", purpose.ordinal());
            intent.putExtra("theme", theme);
            intent.putExtra("redownloadableTrack", redownloadableTrack);
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            context.startActivity(intent);
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent;
        Bundle extras;
        Fragment fragmentForPurpose;
        Bundle extras2;
        Intent intent2 = getIntent();
        if (intent2 != null && (extras2 = intent2.getExtras()) != null) {
            setTheme(extras2.getInt("theme"));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_redownload_offline_music);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.mMaxHeight = ((double) displayMetrics.heightPixels) * 0.8d;
        final FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fragment_container);
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.mp3.activity.WideDialogActivity$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                WideDialogActivity.m155onCreate$lambda2(frameLayout, this);
            }
        });
        FragmentController fragmentController = new FragmentController(this, R.id.fragment_container);
        this.mFragmentController = fragmentController;
        if (fragmentController.getCurrentFragment() != null || (intent = getIntent()) == null || (extras = intent.getExtras()) == null || (fragmentForPurpose = getFragmentForPurpose(Integer.valueOf(extras.getInt("purpose")))) == null) {
            return;
        }
        FragmentController fragmentController2 = this.mFragmentController;
        if (fragmentController2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFragmentController");
            fragmentController2 = null;
        }
        fragmentController2.changeScreenFragment(fragmentForPurpose, false, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-2, reason: not valid java name */
    public static final void m155onCreate$lambda2(FrameLayout frameLayout, WideDialogActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (frameLayout.getHeight() > this$0.mMaxHeight) {
            ViewParent parent = frameLayout.getParent();
            if (parent != null) {
                ViewGroup viewGroup = (ViewGroup) parent;
                viewGroup.getLayoutParams().height = (int) this$0.mMaxHeight;
                viewGroup.forceLayout();
                parent.requestLayout();
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
        }
    }

    private final Fragment getFragmentForPurpose(Integer purpose) {
        Bundle extras;
        int iOrdinal = WideDialogActivityPurpose.SHOW_REDOWNLOAD_DIALOG.ordinal();
        RedownloadableTrack redownloadableTrack = null;
        if (purpose != null && purpose.intValue() == iOrdinal) {
            return RedownloadOfflineMusicFragment.Companion.newInstance$default(RedownloadOfflineMusicFragment.INSTANCE, RedownloadMusicDialogPurpose.SHOW_REDOWNLOAD_MESSAGE, null, 2, null);
        }
        int iOrdinal2 = WideDialogActivityPurpose.SHOW_REMOVE_UNAVAILABLE_DOWNLOADS_DIALOG.ordinal();
        if (purpose != null && purpose.intValue() == iOrdinal2) {
            return RedownloadOfflineMusicFragment.Companion.newInstance$default(RedownloadOfflineMusicFragment.INSTANCE, RedownloadMusicDialogPurpose.SHOW_REMOVE_UNAVAILABLE_DOWNLOADS_CONFIRMATION, null, 2, null);
        }
        int iOrdinal3 = WideDialogActivityPurpose.SHOW_UNAVAILABLE_EXPIRED_KATANA_SUBSCRIPTION.ordinal();
        if (purpose != null && purpose.intValue() == iOrdinal3) {
            Intent intent = getIntent();
            if (intent != null && (extras = intent.getExtras()) != null) {
                redownloadableTrack = (RedownloadableTrack) extras.getParcelable("redownloadableTrack");
            }
            return RedownloadOfflineMusicFragment.INSTANCE.newInstance(RedownloadMusicDialogPurpose.DOWNGRADE_KATANA_CONTENT, redownloadableTrack);
        }
        int iOrdinal4 = WideDialogActivityPurpose.SHOW_BETA_HARLEY_DIALOG.ordinal();
        if (purpose != null && purpose.intValue() == iOrdinal4) {
            return FragmentBetaDialog.INSTANCE.newInstance(BetaDialogPurpose.HARLEY_DIALOG);
        }
        int iOrdinal5 = WideDialogActivityPurpose.SHOW_KATANA_AUDIO_QUALITY_PREFERENCES.ordinal();
        if (purpose != null && purpose.intValue() == iOrdinal5) {
            return FragmentKatanaPreferences.INSTANCE.newInstance(KatanaPreferencesPurpose.STREAMING_PREFERENCES);
        }
        int iOrdinal6 = WideDialogActivityPurpose.SHOW_KATANA_DOWNLOAD_QUALITY_PREFERENCES.ordinal();
        if (purpose != null && purpose.intValue() == iOrdinal6) {
            return FragmentKatanaPreferences.INSTANCE.newInstance(KatanaPreferencesPurpose.DOWNLOAD_PREFERENCES);
        }
        int iOrdinal7 = WideDialogActivityPurpose.SHOW_AUDIO_STATS_PAGE.ordinal();
        if (purpose != null && purpose.intValue() == iOrdinal7) {
            return FragmentAudioStats.INSTANCE.newInstance();
        }
        int iOrdinal8 = WideDialogActivityPurpose.SHOW_PUSH_TOPIC_SELECTION.ordinal();
        if (purpose != null && purpose.intValue() == iOrdinal8) {
            return FragmentPushTopics.INSTANCE.newInstance(PushTopicsPurpose.PUSH_TOPICS);
        }
        int iOrdinal9 = WideDialogActivityPurpose.SHOW_CAR_MODE_FAB_PREFERENCES.ordinal();
        if (purpose != null && purpose.intValue() == iOrdinal9) {
            return FragmentCarModeFabPreferences.INSTANCE.newInstance();
        }
        int iOrdinal10 = WideDialogActivityPurpose.SHOW_APP_DISPLAY_LANGUAGE_PREFERENCES.ordinal();
        if (purpose != null && purpose.intValue() == iOrdinal10) {
            return FragmentAppDisplayLanguagePreferences.INSTANCE.newInstance();
        }
        return null;
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent event) {
        boolean z = false;
        if (event != null && event.getAction() == 1) {
            z = true;
        }
        if (z) {
            FragmentController fragmentController = this.mFragmentController;
            if (fragmentController == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFragmentController");
                fragmentController = null;
            }
            Fragment currentFragment = fragmentController.getCurrentFragment();
            RedownloadOfflineMusicFragment redownloadOfflineMusicFragment = currentFragment instanceof RedownloadOfflineMusicFragment ? (RedownloadOfflineMusicFragment) currentFragment : null;
            if (redownloadOfflineMusicFragment != null) {
                redownloadOfflineMusicFragment.onDialogDismissed();
            }
            finish();
        }
        return true;
    }
}
