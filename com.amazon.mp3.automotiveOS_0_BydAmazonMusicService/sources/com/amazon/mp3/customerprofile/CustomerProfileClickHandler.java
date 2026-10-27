package com.amazon.mp3.customerprofile;

import android.annotation.SuppressLint;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.customerprofile.viewModels.EditProfileViewModel;
import com.amazon.mp3.fragment.contextmenu.BottomSheetDialogFragmentContextMenu;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.experiences.socialprofile.SocialProfileService;
import com.amazon.music.views.library.binders.ImageBinder;
import com.amazon.music.views.library.models.CustomerProfileModel;
import com.amazon.music.views.library.providers.CustomerProfileClickListener;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.functions.Action1;

/* JADX INFO: compiled from: CustomerProfileClickHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 /2\u00020\u0001:\u0001/B\u000f\u0012\u0006\u0010\u001f\u001a\u00020\u001e¢\u0006\u0004\b-\u0010.J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0002J\u0018\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0018\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0007H\u0002J\b\u0010\u0011\u001a\u00020\u000bH\u0002J\u0010\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0002H\u0002J\u0018\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0018\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u0018\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u000e\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u0005J\u0010\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0007H\u0017R\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u001c\u0010&\u001a\n %*\u0004\u0018\u00010$0$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020\u00028\u0002X\u0082D¢\u0006\u0006\n\u0004\b(\u0010)R\u0018\u0010+\u001a\u0004\u0018\u00010*8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010,¨\u00060"}, d2 = {"Lcom/amazon/mp3/customerprofile/CustomerProfileClickHandler;", "Lcom/amazon/music/views/library/providers/CustomerProfileClickListener;", "", "profileId", "Lrx/functions/Action1;", "Landroid/view/MenuItem;", "getClickAction", "Lcom/amazon/music/views/library/models/CustomerProfileModel;", "model", "Lcom/amazon/music/views/library/binders/ImageBinder;", "imageBinder", "", "showEditDialog", "Landroid/view/View;", "view", "followProfile", "unFollowProfile", "showChangeToPrivateProfileDialog", "toastMessage", "handleError", "onFollowClick", "onFollowingClick", "onFollowersClick", "onProfileClick", "onSetupClick", "onEditClick", "item", "", "onOptionsItemSelected", "onOverflowClick", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "Lcom/amazon/music/platform/experiences/socialprofile/SocialProfileService;", "socialProfileService", "Lcom/amazon/music/platform/experiences/socialprofile/SocialProfileService;", "Lcom/amazon/mp3/customerprofile/CustomerProfileDetailManager;", "kotlin.jvm.PlatformType", "detailManager", "Lcom/amazon/mp3/customerprofile/CustomerProfileDetailManager;", "PROFILE_FLEX_NAME", "Ljava/lang/String;", "Landroid/view/Menu;", "menu", "Landroid/view/Menu;", "<init>", "(Landroidx/fragment/app/FragmentActivity;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CustomerProfileClickHandler implements CustomerProfileClickListener {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) CustomerProfileClickHandler.class);
    private final String PROFILE_FLEX_NAME;
    private final FragmentActivity activity;
    private final CustomerProfileDetailManager detailManager;
    private Menu menu;
    private final SocialProfileService socialProfileService;

    public CustomerProfileClickHandler(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
        this.socialProfileService = new SocialProfileService(null, null, 3, null);
        this.detailManager = CustomerProfileDetailManager.getInstance();
        this.PROFILE_FLEX_NAME = "customerProfile";
    }

    @Override // com.amazon.music.views.library.providers.CustomerProfileClickListener
    public void onFollowClick(View view, CustomerProfileModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        if (Intrinsics.areEqual("HIDDEN", this.detailManager.getProfileStatus())) {
            LOG.debug("Current logged in user profile status is Hidden, Showing dialog when trying to follow another profile");
            showChangeToPrivateProfileDialog();
        } else if (1 == model.getFollowState()) {
            unFollowProfile(view, model);
        } else {
            followProfile(view, model);
        }
    }

    @Override // com.amazon.music.views.library.providers.CustomerProfileClickListener
    public void onFollowingClick(CustomerProfileModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        if (this.activity instanceof MusicHomeActivity) {
            CustomerProfileMetrics.emitUiClickMetric(ActionType.SELECT_PROFILE_FOLLOWING);
            new CustomerProfileNavigation((MusicHomeActivity) this.activity).loadFollowing(model.getProfileId());
        }
    }

    @Override // com.amazon.music.views.library.providers.CustomerProfileClickListener
    public void onFollowersClick(CustomerProfileModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        if (this.activity instanceof MusicHomeActivity) {
            CustomerProfileMetrics.emitUiClickMetric(ActionType.SELECT_PROFILE_FOLLOWERS);
            new CustomerProfileNavigation((MusicHomeActivity) this.activity).loadFollowers(model.getProfileId());
        }
    }

    public void onProfileClick(String profileId) {
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        if (this.activity instanceof MusicHomeActivity) {
            new CustomerProfileNavigation((MusicHomeActivity) this.activity).loadProfile(profileId);
        }
    }

    @Override // com.amazon.music.views.library.providers.CustomerProfileClickListener
    public void onSetupClick(CustomerProfileModel model, ImageBinder imageBinder) {
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(imageBinder, "imageBinder");
        CustomerProfileMetrics.emitUiClickMetric(ActionType.SETUP_PROFILE);
        showEditDialog(model, imageBinder);
    }

    @Override // com.amazon.music.views.library.providers.CustomerProfileClickListener
    public void onEditClick(CustomerProfileModel model, ImageBinder imageBinder) {
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(imageBinder, "imageBinder");
        CustomerProfileMetrics.emitUiClickMetric(ActionType.EDIT_PROFILE);
        showEditDialog(model, imageBinder);
    }

    private final Action1<MenuItem> getClickAction(String profileId) {
        return new Action1() { // from class: com.amazon.mp3.customerprofile.CustomerProfileClickHandler$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CustomerProfileClickHandler.m777getClickAction$lambda0(this.f$0, (MenuItem) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getClickAction$lambda-0, reason: not valid java name */
    public static final void m777getClickAction$lambda0(CustomerProfileClickHandler this$0, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(menuItem, "menuItem");
        this$0.onOptionsItemSelected(menuItem);
    }

    public final boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        item.getItemId();
        return true;
    }

    @Override // com.amazon.music.views.library.providers.CustomerProfileClickListener
    @SuppressLint({"RestrictedApi"})
    public void onOverflowClick(CustomerProfileModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        MenuBuilder menuBuilder = new MenuBuilder(this.activity.getApplicationContext());
        this.menu = menuBuilder;
        menuBuilder.add(0, R.id.MenuReport, 0, R.string.dmusic_context_report);
        Menu menu = this.menu;
        BottomSheetDialogFragmentContextMenu bottomSheetDialogFragmentContextMenuNewInstance = menu == null ? null : BottomSheetDialogFragmentContextMenu.INSTANCE.newInstance(menu, getClickAction(model.getProfileId()), Boolean.TRUE);
        if (bottomSheetDialogFragmentContextMenuNewInstance == null) {
            return;
        }
        bottomSheetDialogFragmentContextMenuNewInstance.show(this.activity.getSupportFragmentManager(), CustomerProfileDialogFragment.class.getSimpleName());
    }

    private final void showEditDialog(final CustomerProfileModel model, ImageBinder imageBinder) {
        EditProfileViewModel editProfileViewModel = new EditProfileViewModel();
        editProfileViewModel.setImageBinder(imageBinder);
        editProfileViewModel.setCustomerProfileModel(model);
        editProfileViewModel.setCallbackHandler(new EditProfileViewModel.EditProfileCallback() { // from class: com.amazon.mp3.customerprofile.CustomerProfileClickHandler$showEditDialog$callback$1
            @Override // com.amazon.mp3.customerprofile.viewModels.EditProfileViewModel.EditProfileCallback
            public void onDismiss(EditProfileViewModel currentViewModel) {
                Intrinsics.checkNotNullParameter(currentViewModel, "currentViewModel");
                CustomerProfileModel customerProfileModel = currentViewModel.getCustomerProfileModel();
                if ((customerProfileModel == null ? null : customerProfileModel.getProfileAvatarUrl()) == null) {
                    if ((customerProfileModel == null ? null : customerProfileModel.getProfileName()) == null) {
                        if ((customerProfileModel == null ? null : customerProfileModel.getProfileStatus()) == null) {
                            return;
                        }
                    }
                }
                model.updateProfileState((customerProfileModel == null ? null : customerProfileModel.getProfileName()) != null ? customerProfileModel == null ? null : customerProfileModel.getProfileName() : model.getProfileName(), (customerProfileModel == null ? null : customerProfileModel.getProfileStatus()) != null ? customerProfileModel == null ? null : customerProfileModel.getProfileStatus() : model.getProfileStatus(), (customerProfileModel == null ? null : customerProfileModel.getProfileAvatarUrl()) != null ? customerProfileModel == null ? null : customerProfileModel.getProfileAvatarUrl() : model.getProfileAvatarUrl(), (customerProfileModel != null ? Integer.valueOf(customerProfileModel.getProfileAvatarRotation()) : null).intValue());
                CustomerProfileDetailManager.getInstance().setCustomerProfileModel(model);
            }
        });
        new CustomerProfileNavigation((MusicHomeActivity) this.activity).loadEditProfile(editProfileViewModel);
    }

    private final void followProfile(View view, CustomerProfileModel model) {
        CustomerProfileMetrics.emitUiClickMetric(ActionType.FOLLOW_PROFILE);
        model.updateFollowState(1);
        view.setContentDescription(this.activity.getString(R.string.content_description_profile_unfollow_button));
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(model, null), 3, null);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.customerprofile.CustomerProfileClickHandler$followProfile$1, reason: invalid class name */
    /* JADX INFO: compiled from: CustomerProfileClickHandler.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.customerprofile.CustomerProfileClickHandler$followProfile$1", f = "CustomerProfileClickHandler.kt", i = {}, l = {GeneratorAdapter.NE}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ CustomerProfileModel $model;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(CustomerProfileModel customerProfileModel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$model = customerProfileModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CustomerProfileClickHandler.this.new AnonymousClass1(this.$model, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SocialProfileService socialProfileService = CustomerProfileClickHandler.this.socialProfileService;
                String profileId = this.$model.getProfileId();
                this.label = 1;
                obj = socialProfileService.followUser(profileId, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            if (((Outcome) obj) instanceof Outcome.Success) {
                CustomerProfileClickHandler.LOG.info(Intrinsics.stringPlus("Successfully followed the Profile ", this.$model.getProfileId()));
                String string = CustomerProfileClickHandler.this.activity.getString(R.string.dmusic_profile_following_toast);
                Intrinsics.checkNotNullExpressionValue(string, "activity.getString(R.str…_profile_following_toast)");
                BauhausToastUtils.showTextToast(CustomerProfileClickHandler.this.activity, string, 0);
            } else {
                CustomerProfileClickHandler.LOG.error(Intrinsics.stringPlus("Failed to follow profile ", this.$model.getProfileName()));
                String string2 = CustomerProfileClickHandler.this.activity.getString(R.string.dmusic_profile_failed_follow_toast);
                Intrinsics.checkNotNullExpressionValue(string2, "activity.getString(R.str…file_failed_follow_toast)");
                FlexEvent flexEventBuild = FlexEvent.builder(CustomerProfileClickHandler.this.PROFILE_FLEX_NAME).withFlexStr1("profile.follow.error").withFlexStr2(string2).build();
                Intrinsics.checkNotNullExpressionValue(flexEventBuild, "builder(PROFILE_FLEX_NAM…                 .build()");
                MetricsLogger.sendEvent(flexEventBuild);
                CustomerProfileClickHandler.this.handleError(string2);
            }
            return Unit.INSTANCE;
        }
    }

    private final void unFollowProfile(View view, CustomerProfileModel model) {
        CustomerProfileMetrics.emitUiClickMetric(ActionType.UNFOLLOW_PROFILE);
        model.updateFollowState(0);
        view.setContentDescription(this.activity.getString(R.string.content_description_profile_follow_button));
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C03351(model, null), 3, null);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.customerprofile.CustomerProfileClickHandler$unFollowProfile$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CustomerProfileClickHandler.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.customerprofile.CustomerProfileClickHandler$unFollowProfile$1", f = "CustomerProfileClickHandler.kt", i = {}, l = {184}, m = "invokeSuspend", n = {}, s = {})
    static final class C03351 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ CustomerProfileModel $model;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03351(CustomerProfileModel customerProfileModel, Continuation<? super C03351> continuation) {
            super(2, continuation);
            this.$model = customerProfileModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CustomerProfileClickHandler.this.new C03351(this.$model, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03351) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SocialProfileService socialProfileService = CustomerProfileClickHandler.this.socialProfileService;
                String profileId = this.$model.getProfileId();
                this.label = 1;
                obj = socialProfileService.unfollowUser(profileId, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            if (((Outcome) obj) instanceof Outcome.Success) {
                CustomerProfileClickHandler.LOG.info(Intrinsics.stringPlus("Successfully stopped following profile ", this.$model.getProfileName()));
            } else {
                CustomerProfileClickHandler.LOG.error(Intrinsics.stringPlus("Failed to stop following profile ", this.$model.getProfileName()));
                String string = CustomerProfileClickHandler.this.activity.getString(R.string.dmusic_profile_failed_unfollow_toast);
                Intrinsics.checkNotNullExpressionValue(string, "activity.getString(R.str…le_failed_unfollow_toast)");
                FlexEvent flexEventBuild = FlexEvent.builder(CustomerProfileClickHandler.this.PROFILE_FLEX_NAME).withFlexStr1("profile.unfollow.error").withFlexStr2(string).build();
                Intrinsics.checkNotNullExpressionValue(flexEventBuild, "builder(PROFILE_FLEX_NAM…                 .build()");
                MetricsLogger.sendEvent(flexEventBuild);
                CustomerProfileClickHandler.this.handleError(string);
            }
            return Unit.INSTANCE;
        }
    }

    private final void showChangeToPrivateProfileDialog() {
        FragmentManager supportFragmentManager = this.activity.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "activity.getSupportFragmentManager()");
        CustomerProfileDialogFragment.INSTANCE.createDialog(R.string.dmusic_profile_dialog_message, R.string.dmusic_profile_dialog_make_private_button_text, "PRIVATE").show(supportFragmentManager, CustomerProfileDialogFragment.class.getSimpleName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleError(String toastMessage) {
        BauhausToastUtils.showTextToast(this.activity, toastMessage, 0);
    }
}
