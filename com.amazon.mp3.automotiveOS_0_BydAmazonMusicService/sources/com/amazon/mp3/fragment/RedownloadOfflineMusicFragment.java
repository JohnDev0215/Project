package com.amazon.mp3.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.helper.UserPreferenceProvider;
import com.amazon.mp3.fragment.contextmenu.ContextMenuDownloadComponent;
import com.amazon.mp3.fragment.model.RedownloadOfflineMusicDialogModel;
import com.amazon.mp3.fragment.view.RedownloadOfflineMusicFragmentBinding;
import com.amazon.mp3.fragment.viewmodel.BaseViewModel;
import com.amazon.mp3.fragment.viewmodel.RedownloadKatanaMusicViewModel;
import com.amazon.mp3.fragment.viewmodel.RedownloadOfflineMusicViewModel;
import com.amazon.mp3.fragment.viewmodel.RemoveKatanaDownloadsViewModel;
import com.amazon.mp3.fragment.viewmodel.RemoveUnavailableDownloadsViewModel;
import com.amazon.mp3.fragment.viewmodel.ViewModelCallbacks;
import com.amazon.mp3.library.item.RedownloadableTrack;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.Log;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RedownloadOfflineMusicFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J&\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0006\u0010\u001b\u001a\u00020\u0010J\b\u0010\u001c\u001a\u00020\u0010H\u0002R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/amazon/mp3/fragment/RedownloadOfflineMusicFragment;", "Lcom/amazon/mp3/fragment/BaseFragment;", "()V", "mContainerId", "", "Ljava/lang/Integer;", "mPurpose", "mViewBinding", "Lcom/amazon/mp3/fragment/view/RedownloadOfflineMusicFragmentBinding;", "mViewModelCallbacks", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "redownloadableTrack", "Lcom/amazon/mp3/library/item/RedownloadableTrack;", "viewModel", "Lcom/amazon/mp3/fragment/viewmodel/BaseViewModel;", "initViewModelCallbacks", "", "onBackPressed", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDialogDismissed", "setViewModel", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RedownloadOfflineMusicFragment extends BaseFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG;
    private Integer mContainerId;
    private Integer mPurpose;
    private RedownloadOfflineMusicFragmentBinding mViewBinding = new RedownloadOfflineMusicFragmentBinding();
    private ViewModelCallbacks mViewModelCallbacks;
    private RedownloadableTrack redownloadableTrack;
    private BaseViewModel viewModel;

    /* JADX INFO: compiled from: RedownloadOfflineMusicFragment.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/fragment/RedownloadOfflineMusicFragment$Companion;", "", "()V", "PURPOSE", "", "TAG", "newInstance", "Lcom/amazon/mp3/fragment/RedownloadOfflineMusicFragment;", "purpose", "Lcom/amazon/mp3/fragment/RedownloadMusicDialogPurpose;", "redownloadableTrack", "Lcom/amazon/mp3/library/item/RedownloadableTrack;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ RedownloadOfflineMusicFragment newInstance$default(Companion companion, RedownloadMusicDialogPurpose redownloadMusicDialogPurpose, RedownloadableTrack redownloadableTrack, int i, Object obj) {
            if ((i & 2) != 0) {
                redownloadableTrack = null;
            }
            return companion.newInstance(redownloadMusicDialogPurpose, redownloadableTrack);
        }

        @JvmOverloads
        public final RedownloadOfflineMusicFragment newInstance(RedownloadMusicDialogPurpose purpose, RedownloadableTrack redownloadableTrack) {
            Intrinsics.checkNotNullParameter(purpose, "purpose");
            RedownloadOfflineMusicFragment redownloadOfflineMusicFragment = new RedownloadOfflineMusicFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("purpose", purpose.ordinal());
            if (redownloadableTrack != null) {
                bundle.putParcelable("redownloadableTrack", redownloadableTrack);
            }
            redownloadOfflineMusicFragment.setArguments(bundle);
            return redownloadOfflineMusicFragment;
        }
    }

    static {
        String simpleName = RedownloadOfflineMusicFragment.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "RedownloadOfflineMusicFr…nt::class.java.simpleName");
        TAG = simpleName;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_redownload_offline_music, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…_music, container, false)");
        this.mContainerId = container == null ? null : Integer.valueOf(container.getId());
        this.mViewBinding.setView(viewInflate);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mPurpose = Integer.valueOf(arguments.getInt("purpose", Integer.MIN_VALUE));
            this.redownloadableTrack = (RedownloadableTrack) arguments.getParcelable("redownloadableTrack");
            Integer num = this.mPurpose;
            if (num == null || num.intValue() != Integer.MIN_VALUE) {
                initViewModelCallbacks();
                setViewModel();
            }
        }
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.setFinishOnTouchOutside(false);
        }
        return viewInflate;
    }

    private final void setViewModel() {
        Context context;
        Integer num = this.mPurpose;
        int iOrdinal = RedownloadMusicDialogPurpose.SHOW_REDOWNLOAD_MESSAGE.ordinal();
        if (num != null && num.intValue() == iOrdinal) {
            Context context2 = getContext();
            if (context2 == null) {
                return;
            }
            Resources resources = context2.getResources();
            String string = resources.getString(R.string.dmusic_redownload_offline_title);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusi…redownload_offline_title)");
            String string2 = resources.getString(R.string.dmusic_redownload_offline_message);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.dmusi…download_offline_message)");
            String string3 = resources.getString(R.string.dmusic_continue_button);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.dmusic_continue_button)");
            String string4 = resources.getString(R.string.dmusic_remove_unavailable_downloads_button);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.dmusi…ailable_downloads_button)");
            this.viewModel = new RedownloadOfflineMusicViewModel(context2, this.mViewBinding, new RedownloadOfflineMusicDialogModel(string, string2, string3, string4, 8), this.mViewModelCallbacks);
            Log.warning(TAG, "Redownload Offline Music dialog viewed.");
            MetricsLogger.migrateExistingDownloadsDialogViewed();
            return;
        }
        int iOrdinal2 = RedownloadMusicDialogPurpose.SHOW_REMOVE_UNAVAILABLE_DOWNLOADS_CONFIRMATION.ordinal();
        if (num != null && num.intValue() == iOrdinal2) {
            Context context3 = getContext();
            if (context3 == null) {
                return;
            }
            Resources resources2 = context3.getResources();
            String string5 = resources2.getString(R.string.dmusic_remove_unavailable_downloads_title);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.dmusi…vailable_downloads_title)");
            String string6 = resources2.getString(R.string.dmusic_remove_unavailable_downloads_message);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.dmusi…ilable_downloads_message)");
            String string7 = resources2.getString(R.string.dmusic_confirm_removal_button);
            Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.dmusic_confirm_removal_button)");
            String string8 = resources2.getString(R.string.dmusic_button_cancel);
            Intrinsics.checkNotNullExpressionValue(string8, "getString(R.string.dmusic_button_cancel)");
            this.viewModel = new RemoveUnavailableDownloadsViewModel(context3, this.mViewBinding, new RedownloadOfflineMusicDialogModel(string5, string6, string7, string8, 0, 16, null), this.mViewModelCallbacks);
            Log.warning(TAG, "Remove Unavailable Offline Music dialog viewed.");
            MetricsLogger.removeUnavailableDownloadsDialogViewed();
            return;
        }
        int iOrdinal3 = RedownloadMusicDialogPurpose.REMOVE_KATANA_DOWNLOADS_CONFIRMATION.ordinal();
        if (num != null && num.intValue() == iOrdinal3) {
            Context context4 = getContext();
            if (context4 == null) {
                return;
            }
            Resources resources3 = context4.getResources();
            String string9 = resources3.getString(R.string.dmusic_remove_unavailable_downloads_title);
            Intrinsics.checkNotNullExpressionValue(string9, "getString(R.string.dmusi…vailable_downloads_title)");
            String string10 = resources3.getString(R.string.dmusic_remove_unavailable_downloads_message);
            Intrinsics.checkNotNullExpressionValue(string10, "getString(R.string.dmusi…ilable_downloads_message)");
            String string11 = resources3.getString(R.string.dmusic_confirm_removal_button);
            Intrinsics.checkNotNullExpressionValue(string11, "getString(R.string.dmusic_confirm_removal_button)");
            String string12 = resources3.getString(R.string.dmusic_button_cancel);
            Intrinsics.checkNotNullExpressionValue(string12, "getString(R.string.dmusic_button_cancel)");
            this.viewModel = new RemoveKatanaDownloadsViewModel(context4, this.mViewBinding, new RedownloadOfflineMusicDialogModel(string9, string10, string11, string12, 0, 16, null), this.mViewModelCallbacks);
            Log.warning(TAG, "Remove content downloaded in Katana quality dialog viewed.");
            MetricsLogger.removeUnavailableDownloadsDialogViewed();
            return;
        }
        int iOrdinal4 = RedownloadMusicDialogPurpose.DOWNGRADE_KATANA_CONTENT.ordinal();
        if (num == null || num.intValue() != iOrdinal4 || (context = getContext()) == null) {
            return;
        }
        String string13 = getString(R.string.dmusic_redownloaddialog_title_after_katana_downgrade);
        Intrinsics.checkNotNullExpressionValue(string13, "getString(R.string.dmusi…e_after_katana_downgrade)");
        RedownloadableTrack redownloadableTrack = this.redownloadableTrack;
        if (redownloadableTrack != null) {
            string13 = string13 + ' ' + ((Object) redownloadableTrack.getTrackName());
        }
        String str = string13;
        Resources resources4 = context.getResources();
        int entryResourceId = new UserPreferenceProvider(getContext()).getCurrentDownloadQualityPreference().getEntryResourceId();
        String string14 = resources4.getString(R.string.dmusic_redownloaddialog_message_after_katana_downgrade);
        Intrinsics.checkNotNullExpressionValue(string14, "getString(R.string.dmusi…e_after_katana_downgrade)");
        String string15 = resources4.getString(R.string.dmusic_redownloaddialog_primary_button_text_after_katana_downgrade, resources4.getString(entryResourceId));
        Intrinsics.checkNotNullExpressionValue(string15, "getString(R.string.dmusi…e, getString(resourceId))");
        String string16 = resources4.getString(R.string.dmusic_remove_unavailable_downloads_button);
        Intrinsics.checkNotNullExpressionValue(string16, "getString(R.string.dmusi…ailable_downloads_button)");
        this.viewModel = new RedownloadKatanaMusicViewModel(context, this.mViewBinding, new RedownloadOfflineMusicDialogModel(str, string14, string15, string16, 0, 16, null), this.redownloadableTrack, this.mViewModelCallbacks);
        Log.info(TAG, "Re-download offline music to standard quality dialog viewed");
        MetricsLogger.redownloadOfflineMusicDialogViewed();
    }

    @Override // com.amazon.mp3.fragment.BaseFragment
    public boolean onBackPressed() {
        onDialogDismissed();
        return super.onBackPressed();
    }

    public final void onDialogDismissed() {
        BaseViewModel baseViewModel = this.viewModel;
        RedownloadOfflineMusicViewModel redownloadOfflineMusicViewModel = baseViewModel instanceof RedownloadOfflineMusicViewModel ? (RedownloadOfflineMusicViewModel) baseViewModel : null;
        if (redownloadOfflineMusicViewModel == null) {
            return;
        }
        redownloadOfflineMusicViewModel.onDialogDismissed();
    }

    private final void initViewModelCallbacks() {
        this.mViewModelCallbacks = new ViewModelCallbacks() { // from class: com.amazon.mp3.fragment.RedownloadOfflineMusicFragment.initViewModelCallbacks.1
            @Override // com.amazon.mp3.fragment.viewmodel.ViewModelCallbacks
            public void onEventOccurred(int callerId, int actionId, BaseViewModel viewModelInstance) {
                FragmentActivity activity;
                FragmentActivity activity2;
                RedownloadOfflineMusicFragment redownloadOfflineMusicFragment;
                Integer num;
                RedownloadOfflineMusicFragment redownloadOfflineMusicFragment2;
                Integer num2;
                FragmentActivity activity3;
                Intrinsics.checkNotNullParameter(viewModelInstance, "viewModelInstance");
                if (callerId == RedownloadMusicDialogPurpose.SHOW_REDOWNLOAD_MESSAGE.ordinal()) {
                    if (actionId != 0) {
                        if (actionId == 1 && (activity3 = RedownloadOfflineMusicFragment.this.getActivity()) != null) {
                            activity3.finish();
                            return;
                        }
                        return;
                    }
                    FragmentActivity activity4 = RedownloadOfflineMusicFragment.this.getActivity();
                    if (activity4 == null || (num2 = (redownloadOfflineMusicFragment2 = RedownloadOfflineMusicFragment.this).mContainerId) == null) {
                        return;
                    }
                    num2.intValue();
                    RedownloadOfflineMusicFragment redownloadOfflineMusicFragmentNewInstance$default = Companion.newInstance$default(RedownloadOfflineMusicFragment.INSTANCE, RedownloadMusicDialogPurpose.SHOW_REMOVE_UNAVAILABLE_DOWNLOADS_CONFIRMATION, null, 2, null);
                    FragmentManager supportFragmentManager = activity4.getSupportFragmentManager();
                    Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "activity.supportFragmentManager");
                    FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
                    Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "manager.beginTransaction()");
                    Integer num3 = redownloadOfflineMusicFragment2.mContainerId;
                    if (num3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
                    }
                    fragmentTransactionBeginTransaction.replace(num3.intValue(), redownloadOfflineMusicFragmentNewInstance$default);
                    fragmentTransactionBeginTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_out, R.anim.fade_in);
                    fragmentTransactionBeginTransaction.addToBackStack(redownloadOfflineMusicFragmentNewInstance$default.getTag());
                    fragmentTransactionBeginTransaction.commit();
                    return;
                }
                if (callerId != RedownloadMusicDialogPurpose.DOWNGRADE_KATANA_CONTENT.ordinal()) {
                    if (callerId == RedownloadMusicDialogPurpose.SHOW_REMOVE_UNAVAILABLE_DOWNLOADS_CONFIRMATION.ordinal()) {
                        if (actionId != 1 || (activity2 = RedownloadOfflineMusicFragment.this.getActivity()) == null) {
                            return;
                        }
                        activity2.finish();
                        return;
                    }
                    if (callerId != RedownloadMusicDialogPurpose.REMOVE_KATANA_DOWNLOADS_CONFIRMATION.ordinal()) {
                        if (callerId == RedownloadMusicDialogPurpose.SHOW_NO_NETWORK_DIALOG.ordinal()) {
                            BauhausToastUtils.showDefaultToast(RedownloadOfflineMusicFragment.this.getActivity(), R.string.dmusic_no_connection_title, 1);
                            return;
                        }
                        return;
                    } else {
                        if (actionId != 1 || (activity = RedownloadOfflineMusicFragment.this.getActivity()) == null) {
                            return;
                        }
                        activity.finish();
                        return;
                    }
                }
                if (actionId == 0) {
                    FragmentActivity activity5 = RedownloadOfflineMusicFragment.this.getActivity();
                    if (activity5 == null || (num = (redownloadOfflineMusicFragment = RedownloadOfflineMusicFragment.this).mContainerId) == null) {
                        return;
                    }
                    num.intValue();
                    RedownloadOfflineMusicFragment redownloadOfflineMusicFragmentNewInstance$default2 = Companion.newInstance$default(RedownloadOfflineMusicFragment.INSTANCE, RedownloadMusicDialogPurpose.REMOVE_KATANA_DOWNLOADS_CONFIRMATION, null, 2, null);
                    FragmentManager supportFragmentManager2 = activity5.getSupportFragmentManager();
                    Intrinsics.checkNotNullExpressionValue(supportFragmentManager2, "activity.supportFragmentManager");
                    FragmentTransaction fragmentTransactionBeginTransaction2 = supportFragmentManager2.beginTransaction();
                    Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction2, "manager.beginTransaction()");
                    Integer num4 = redownloadOfflineMusicFragment.mContainerId;
                    if (num4 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
                    }
                    fragmentTransactionBeginTransaction2.replace(num4.intValue(), redownloadOfflineMusicFragmentNewInstance$default2);
                    fragmentTransactionBeginTransaction2.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_out, R.anim.fade_in);
                    fragmentTransactionBeginTransaction2.addToBackStack(redownloadOfflineMusicFragmentNewInstance$default2.getTag());
                    fragmentTransactionBeginTransaction2.commit();
                    return;
                }
                if (actionId == 1) {
                    FragmentActivity activity6 = RedownloadOfflineMusicFragment.this.getActivity();
                    if (activity6 == null) {
                        return;
                    }
                    activity6.finish();
                    return;
                }
                if (actionId != 2) {
                    return;
                }
                RedownloadableTrack redownloadableTrack = RedownloadOfflineMusicFragment.this.redownloadableTrack;
                if (redownloadableTrack != null) {
                    RedownloadOfflineMusicFragment redownloadOfflineMusicFragment3 = RedownloadOfflineMusicFragment.this;
                    String asin = redownloadableTrack.getAsin();
                    if (asin != null) {
                        ContextMenuDownloadComponent.startDownload(redownloadOfflineMusicFragment3.getActivity(), asin, RedownloadOfflineMusicFragmentKt.getContentUri(asin), null);
                    }
                }
                FragmentActivity activity7 = RedownloadOfflineMusicFragment.this.getActivity();
                if (activity7 == null) {
                    return;
                }
                activity7.finish();
            }
        };
    }
}
