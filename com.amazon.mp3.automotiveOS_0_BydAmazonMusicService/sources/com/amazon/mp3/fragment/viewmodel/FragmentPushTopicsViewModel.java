package com.amazon.mp3.fragment.viewmodel;

import android.content.Context;
import android.view.View;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.fragment.PushTopicsPurpose;
import com.amazon.mp3.fragment.model.FragmentPushTopicsModel;
import com.amazon.mp3.fragment.view.FragmentPushTopicsBinding;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FragmentPushTopicsViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0019\u0018\u0000 92\u00020\u0001:\u00019B)\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a¢\u0006\u0004\b7\u00108J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\u0007\u001a\u00020\u0002H\u0002J\b\u0010\b\u001a\u00020\u0002H\u0002R\u001a\u0010\n\u001a\u00020\t8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\"\u0010\u0014\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010\u001b\u001a\u0004\u0018\u00010\u001a8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\"\u0010\"\u001a\u00020!8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\"\u0010(\u001a\u00020!8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b(\u0010#\u001a\u0004\b)\u0010%\"\u0004\b*\u0010'R\"\u0010+\u001a\u00020!8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b+\u0010#\u001a\u0004\b,\u0010%\"\u0004\b-\u0010'R\"\u0010.\u001a\u00020!8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b.\u0010#\u001a\u0004\b/\u0010%\"\u0004\b0\u0010'R\"\u00101\u001a\u00020!8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b1\u0010#\u001a\u0004\b2\u0010%\"\u0004\b3\u0010'R\"\u00104\u001a\u00020!8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b4\u0010#\u001a\u0004\b5\u0010%\"\u0004\b6\u0010'¨\u0006:"}, d2 = {"Lcom/amazon/mp3/fragment/viewmodel/FragmentPushTopicsViewModel;", "Lcom/amazon/mp3/fragment/viewmodel/BaseViewModel;", "", "setupCurrentCheckedPosition", "", "id", "notifyPropertyChanged", "setupSaveButton", "setupCancelButton", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/mp3/fragment/view/FragmentPushTopicsBinding;", "mBinding", "Lcom/amazon/mp3/fragment/view/FragmentPushTopicsBinding;", "getMBinding", "()Lcom/amazon/mp3/fragment/view/FragmentPushTopicsBinding;", "Lcom/amazon/mp3/fragment/model/FragmentPushTopicsModel;", "pushTopicModel", "Lcom/amazon/mp3/fragment/model/FragmentPushTopicsModel;", "getPushTopicModel", "()Lcom/amazon/mp3/fragment/model/FragmentPushTopicsModel;", "setPushTopicModel", "(Lcom/amazon/mp3/fragment/model/FragmentPushTopicsModel;)V", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "viewModelCallbacks", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "getViewModelCallbacks", "()Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "setViewModelCallbacks", "(Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "", "firstPushTopicChecked", "Z", "getFirstPushTopicChecked", "()Z", "setFirstPushTopicChecked", "(Z)V", "secondPushTopicChecked", "getSecondPushTopicChecked", "setSecondPushTopicChecked", "thirdPushTopicChecked", "getThirdPushTopicChecked", "setThirdPushTopicChecked", "fourthPushTopicChecked", "getFourthPushTopicChecked", "setFourthPushTopicChecked", "fifthPushTopicChecked", "getFifthPushTopicChecked", "setFifthPushTopicChecked", "sixthPushTopicChecked", "getSixthPushTopicChecked", "setSixthPushTopicChecked", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/fragment/view/FragmentPushTopicsBinding;Lcom/amazon/mp3/fragment/model/FragmentPushTopicsModel;Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class FragmentPushTopicsViewModel extends BaseViewModel {
    private final Context context;
    private boolean fifthPushTopicChecked;
    private boolean firstPushTopicChecked;
    private boolean fourthPushTopicChecked;
    private final FragmentPushTopicsBinding mBinding;
    private FragmentPushTopicsModel pushTopicModel;
    private boolean secondPushTopicChecked;
    private boolean sixthPushTopicChecked;
    private boolean thirdPushTopicChecked;
    private ViewModelCallbacks viewModelCallbacks;

    public Context getContext() {
        return this.context;
    }

    public final FragmentPushTopicsBinding getMBinding() {
        return this.mBinding;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentPushTopicsViewModel(Context context, FragmentPushTopicsBinding mBinding, FragmentPushTopicsModel pushTopicModel, ViewModelCallbacks viewModelCallbacks) {
        super(context, viewModelCallbacks);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mBinding, "mBinding");
        Intrinsics.checkNotNullParameter(pushTopicModel, "pushTopicModel");
        this.context = context;
        this.mBinding = mBinding;
        this.pushTopicModel = pushTopicModel;
        this.viewModelCallbacks = viewModelCallbacks;
        setupCurrentCheckedPosition();
        notifyPropertyChanged(mBinding.getMTitleView().getId());
        notifyPropertyChanged(mBinding.getMFirstPushTopicCheckbox().getId());
        notifyPropertyChanged(mBinding.getMSecondPushTopicCheckbox().getId());
        notifyPropertyChanged(mBinding.getMThirdPushTopicCheckbox().getId());
        notifyPropertyChanged(mBinding.getMFourthPushTopicCheckbox().getId());
        notifyPropertyChanged(mBinding.getMFifthPushTopicCheckbox().getId());
        notifyPropertyChanged(mBinding.getMSixthPushTopicCheckbox().getId());
        notifyPropertyChanged(mBinding.getMSaveButton().getId());
        notifyPropertyChanged(mBinding.getMCancelButton().getId());
        setupSaveButton();
        setupCancelButton();
    }

    public ViewModelCallbacks getViewModelCallbacks() {
        return this.viewModelCallbacks;
    }

    private final void setupCurrentCheckedPosition() {
        Map<String, Boolean> notificationSettings = SettingsUtil.getNotificationSettings(getContext());
        Boolean bool = notificationSettings.get("MUSIC_promo");
        Boolean bool2 = Boolean.TRUE;
        this.firstPushTopicChecked = Intrinsics.areEqual(bool, bool2);
        this.secondPushTopicChecked = Intrinsics.areEqual(notificationSettings.get("MUSIC_social"), bool2);
        this.thirdPushTopicChecked = Intrinsics.areEqual(notificationSettings.get("MUSIC_live_stream"), bool2);
        this.fourthPushTopicChecked = Intrinsics.areEqual(notificationSettings.get("MUSIC_merch"), bool2);
        this.fifthPushTopicChecked = Intrinsics.areEqual(notificationSettings.get("MUSIC_podcast"), bool2);
        this.sixthPushTopicChecked = Intrinsics.areEqual(notificationSettings.get("MUSIC_video"), bool2);
    }

    private final void notifyPropertyChanged(int id) {
        if (id == this.mBinding.getMTitleView().getId()) {
            this.mBinding.getMTitleView().setText(this.pushTopicModel.getTitle());
            return;
        }
        if (id == this.mBinding.getMFirstPushTopicCheckbox().getId()) {
            this.mBinding.getMFirstPushTopicCheckbox().setVisibility(0);
            this.mBinding.getMFirstPushTopicCheckbox().setChecked(this.firstPushTopicChecked);
            return;
        }
        if (id == this.mBinding.getMSecondPushTopicCheckbox().getId()) {
            this.mBinding.getMSecondPushTopicCheckbox().setVisibility(0);
            this.mBinding.getMSecondPushTopicCheckbox().setChecked(this.secondPushTopicChecked);
            return;
        }
        if (id == this.mBinding.getMThirdPushTopicCheckbox().getId()) {
            this.mBinding.getMThirdPushTopicCheckbox().setVisibility(0);
            this.mBinding.getMThirdPushTopicCheckbox().setChecked(this.thirdPushTopicChecked);
            return;
        }
        if (id == this.mBinding.getMFourthPushTopicCheckbox().getId()) {
            this.mBinding.getMFourthPushTopicCheckbox().setVisibility(0);
            this.mBinding.getMFourthPushTopicCheckbox().setChecked(this.fourthPushTopicChecked);
            return;
        }
        if (id == this.mBinding.getMFifthPushTopicCheckbox().getId()) {
            this.mBinding.getMFifthPushTopicCheckbox().setVisibility(0);
            this.mBinding.getMFifthPushTopicCheckbox().setChecked(this.fifthPushTopicChecked);
        } else if (id == this.mBinding.getMSixthPushTopicCheckbox().getId()) {
            this.mBinding.getMSixthPushTopicCheckbox().setVisibility(0);
            this.mBinding.getMSixthPushTopicCheckbox().setChecked(this.sixthPushTopicChecked);
        } else if (id == this.mBinding.getMSaveButton().getId()) {
            this.mBinding.getMSaveButton().setText(this.pushTopicModel.getSaveButtonText());
        } else if (id == this.mBinding.getMCancelButton().getId()) {
            this.mBinding.getMCancelButton().setText(this.pushTopicModel.getCancelButtonText());
        }
    }

    private final void setupSaveButton() {
        final Context context = getContext();
        getMBinding().getMSaveButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.viewmodel.FragmentPushTopicsViewModel$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentPushTopicsViewModel.m1170setupSaveButton$lambda1$lambda0(this.f$0, context, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupSaveButton$lambda-1$lambda-0, reason: not valid java name */
    public static final void m1170setupSaveButton$lambda1$lambda0(FragmentPushTopicsViewModel this$0, Context c, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(c, "$c");
        HashMap map = new HashMap();
        map.put("MUSIC_promo", Boolean.valueOf(this$0.mBinding.getMFirstPushTopicCheckbox().isChecked()));
        map.put("MUSIC_social", Boolean.valueOf(this$0.mBinding.getMSecondPushTopicCheckbox().isChecked()));
        map.put("MUSIC_live_stream", Boolean.valueOf(this$0.mBinding.getMThirdPushTopicCheckbox().isChecked()));
        map.put("MUSIC_merch", Boolean.valueOf(this$0.mBinding.getMFourthPushTopicCheckbox().isChecked()));
        map.put("MUSIC_podcast", Boolean.valueOf(this$0.mBinding.getMFifthPushTopicCheckbox().isChecked()));
        map.put("MUSIC_video", Boolean.valueOf(this$0.mBinding.getMSixthPushTopicCheckbox().isChecked()));
        SettingsUtil.setNotificationSettings(c, map);
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks == null) {
            return;
        }
        viewModelCallbacks.onEventOccurred(PushTopicsPurpose.PUSH_TOPICS.ordinal(), 1, this$0);
    }

    private final void setupCancelButton() {
        this.mBinding.getMCancelButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.viewmodel.FragmentPushTopicsViewModel$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentPushTopicsViewModel.m1169setupCancelButton$lambda2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupCancelButton$lambda-2, reason: not valid java name */
    public static final void m1169setupCancelButton$lambda2(FragmentPushTopicsViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks == null) {
            return;
        }
        viewModelCallbacks.onEventOccurred(PushTopicsPurpose.PUSH_TOPICS.ordinal(), 1, this$0);
    }
}
