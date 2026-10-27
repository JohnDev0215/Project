package com.amazon.music.views.library.binders;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$string;
import com.amazon.music.views.library.models.CustomerProfileModel;
import com.amazon.music.views.library.providers.CustomerProfileClickListener;
import com.amazon.music.views.library.providers.ShareProfileProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.CustomerProfileView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: CustomerProfileBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B?\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a\u0012\u0006\u0010\u001e\u001a\u00020\u001d\u0012\u0006\u0010!\u001a\u00020 \u0012\u0006\u0010$\u001a\u00020#\u0012\b\b\u0002\u0010'\u001a\u00020&\u0012\b\b\u0002\u0010)\u001a\u00020&¢\u0006\u0004\b4\u00105J \u0010\n\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0018\u0010\f\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0003H\u0002J\u0018\u0010\r\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0003H\u0002J\u0018\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0003H\u0002J\u0018\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0003H\u0002J\u0018\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0003H\u0002J\u0018\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0003H\u0002J\u0018\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0003H\u0002J\u0010\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\u0018\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0003H\u0016J \u0010\u0019\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0017H\u0016R\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001e\u001a\u00020\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010(R$\u0010*\u001a\u0004\u0018\u00010\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001a\u00103\u001a\b\u0012\u0004\u0012\u00020\u0003008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u00102¨\u00066"}, d2 = {"Lcom/amazon/music/views/library/binders/CustomerProfileBinder;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "Lcom/amazon/music/views/library/views/CustomerProfileView;", "Lcom/amazon/music/views/library/models/CustomerProfileModel;", "view", "", "profileAvatarUrl", "", "imageRotation", "", "setCustomerProfileImage", "model", "setSetupIconClickListener", "setEditIconClickListener", "setShareIconClickListener", "setProfileIconStates", "setFollowingClickListener", "setFollowersClickListener", "setOverflowClickListener", "Landroid/content/Context;", "context", "createView", "bind", "", "payload", "handleStateChange", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/views/library/binders/ImageBinder;", "imageBinder", "Lcom/amazon/music/views/library/binders/ImageBinder;", "Lcom/amazon/music/views/library/providers/ShareProfileProvider;", "shareProfileProvider", "Lcom/amazon/music/views/library/providers/ShareProfileProvider;", "Lcom/amazon/music/views/library/providers/CustomerProfileClickListener;", "profileClickListener", "Lcom/amazon/music/views/library/providers/CustomerProfileClickListener;", "", "showOverflowMenu", "Z", "showHandle", "mContext", "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "modelClass", "<init>", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/binders/ImageBinder;Lcom/amazon/music/views/library/providers/ShareProfileProvider;Lcom/amazon/music/views/library/providers/CustomerProfileClickListener;ZZ)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class CustomerProfileBinder implements BaseStateChangeBinder<CustomerProfileView, CustomerProfileModel> {
    private final ImageBinder imageBinder;
    private Context mContext;
    private final CustomerProfileClickListener profileClickListener;
    private final ShareProfileProvider shareProfileProvider;
    private final boolean showHandle;
    private final boolean showOverflowMenu;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<CustomerProfileModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(CustomerProfileModel.class);
    }

    public CustomerProfileBinder(StyleSheet styleSheet, ImageBinder imageBinder, ShareProfileProvider shareProfileProvider, CustomerProfileClickListener profileClickListener, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(imageBinder, "imageBinder");
        Intrinsics.checkNotNullParameter(shareProfileProvider, "shareProfileProvider");
        Intrinsics.checkNotNullParameter(profileClickListener, "profileClickListener");
        this.styleSheet = styleSheet;
        this.imageBinder = imageBinder;
        this.shareProfileProvider = shareProfileProvider;
        this.profileClickListener = profileClickListener;
        this.showOverflowMenu = z;
        this.showHandle = z2;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public CustomerProfileView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.mContext = context;
        return new CustomerProfileView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(CustomerProfileView view, CustomerProfileModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        setCustomerProfileImage(view, model.getProfileAvatarUrl(), model.getProfileAvatarRotation());
        view.setProfileName(model.getProfileName());
        if (this.showHandle) {
            view.setProfileHandle(model.getProfileHandle());
        } else {
            view.hideProfileHandle();
        }
        if (model.getHideProfileStats()) {
            view.hideProfileStatsLayout();
        } else {
            view.setFollowingCount(model.getFollowingCount());
            view.setFollowerCount(model.getFollowerCount());
        }
        if (this.showOverflowMenu && !model.getIsSelfView()) {
            view.showOverflowMenu(0);
            setOverflowClickListener(view, model);
        }
        setEditIconClickListener(view, model);
        setShareIconClickListener(view, model);
        setProfileIconStates(view, model);
        setFollowingClickListener(view, model);
        setFollowersClickListener(view, model);
        setSetupIconClickListener(view, model);
    }

    @Override // com.amazon.music.views.library.binders.BaseStateChangeBinder
    public void handleStateChange(CustomerProfileView view, CustomerProfileModel model, Object payload) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (Intrinsics.areEqual(payload, (Object) 2)) {
            if (model.getFollowState() == 1) {
                Context context = this.mContext;
                if (context != null) {
                    String it = context.getString(R$string.content_description_profile_unfollow_button);
                    Drawable drawable = context.getDrawable(R$drawable.ic_action_favoriteon_svg);
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    view.setFollowImage(drawable, it);
                }
                view.setFollowerCount(model.getFollowerCount());
                return;
            }
            if (model.getFollowState() == 0) {
                if (Intrinsics.areEqual(model.getProfileStatus(), "PRIVATE")) {
                    view.showFollowIcon(false);
                }
                Context context2 = this.mContext;
                if (context2 != null) {
                    String it2 = context2.getString(R$string.content_description_profile_follow_button);
                    Drawable drawable2 = context2.getDrawable(R$drawable.ic_action_favorite_svg);
                    Intrinsics.checkNotNullExpressionValue(it2, "it");
                    view.setFollowImage(drawable2, it2);
                }
                view.setFollowerCount(model.getFollowerCount());
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(payload, (Object) 3)) {
            view.setProfileName(model.getProfileName());
            if (!Intrinsics.areEqual(model.getProfileStatus(), "HIDDEN")) {
                view.showSetUpButton(false);
                setCustomerProfileImage(view, model.getProfileAvatarUrl(), model.getProfileAvatarRotation());
            }
            setProfileIconStates(view, model);
        }
    }

    private final void setCustomerProfileImage(CustomerProfileView view, String profileAvatarUrl, int imageRotation) {
        this.imageBinder.bind(profileAvatarUrl, view.getBackgroundImage(), R$drawable.artist_placeholder, ImageLoader.TransformationType.Custom, imageRotation, false);
    }

    private final void setSetupIconClickListener(CustomerProfileView view, final CustomerProfileModel model) {
        view.setSetUpProfileClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.CustomerProfileBinder$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CustomerProfileBinder.m2294setSetupIconClickListener$lambda4(this.f$0, model, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setSetupIconClickListener$lambda-4, reason: not valid java name */
    public static final void m2294setSetupIconClickListener$lambda4(CustomerProfileBinder this$0, CustomerProfileModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        this$0.profileClickListener.onSetupClick(model, this$0.imageBinder);
    }

    private final void setEditIconClickListener(CustomerProfileView view, final CustomerProfileModel model) {
        view.setEditIconClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.CustomerProfileBinder$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CustomerProfileBinder.m2288setEditIconClickListener$lambda5(this.f$0, model, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setEditIconClickListener$lambda-5, reason: not valid java name */
    public static final void m2288setEditIconClickListener$lambda5(CustomerProfileBinder this$0, CustomerProfileModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        this$0.profileClickListener.onEditClick(model, this$0.imageBinder);
    }

    private final void setShareIconClickListener(CustomerProfileView view, final CustomerProfileModel model) {
        view.setShareIconClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.CustomerProfileBinder$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CustomerProfileBinder.m2295setShareIconClickListener$lambda6(this.f$0, model, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setShareIconClickListener$lambda-6, reason: not valid java name */
    public static final void m2295setShareIconClickListener$lambda6(CustomerProfileBinder this$0, CustomerProfileModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        this$0.shareProfileProvider.onShare(model.getProfileId(), model.getProfileName(), model.getProfileAvatarUrl(), model.getProfileUrl());
    }

    private final void setProfileIconStates(CustomerProfileView view, final CustomerProfileModel model) {
        int followState = model.getFollowState();
        if (model.getIsSelfView()) {
            view.showFollowIcon(false);
            if (Intrinsics.areEqual(model.getProfileStatus(), "HIDDEN")) {
                view.showSetUpButton(true);
                view.showEditIcon(false);
            } else {
                view.showSetUpButton(false);
                view.showEditIcon(true);
            }
        } else {
            view.showSetUpButton(false);
            view.showEditIcon(false);
            if (Intrinsics.areEqual(model.getProfileStatus(), "PUBLIC")) {
                view.showFollowIcon(true);
                if (followState == 1) {
                    Context context = this.mContext;
                    if (context != null) {
                        String it = context.getString(R$string.content_description_profile_unfollow_button);
                        Drawable drawable = context.getDrawable(R$drawable.ic_action_favoriteon_svg);
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        view.setFollowImage(drawable, it);
                    }
                } else {
                    Context context2 = this.mContext;
                    if (context2 != null) {
                        String it2 = context2.getString(R$string.content_description_profile_follow_button);
                        Drawable drawable2 = context2.getDrawable(R$drawable.ic_action_favorite_svg);
                        Intrinsics.checkNotNullExpressionValue(it2, "it");
                        view.setFollowImage(drawable2, it2);
                    }
                }
                view.setFollowIconClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.CustomerProfileBinder$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        CustomerProfileBinder.m2292setProfileIconStates$lambda11(this.f$0, model, view2);
                    }
                });
            } else if (Intrinsics.areEqual(model.getProfileStatus(), "PRIVATE") && followState == 1) {
                view.showFollowIcon(true);
                Context context3 = this.mContext;
                if (context3 != null) {
                    String it3 = context3.getString(R$string.content_description_profile_unfollow_button);
                    Drawable drawable3 = context3.getDrawable(R$drawable.ic_action_favoriteon_svg);
                    Intrinsics.checkNotNullExpressionValue(it3, "it");
                    view.setFollowImage(drawable3, it3);
                }
                view.setFollowIconClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.CustomerProfileBinder$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        CustomerProfileBinder.m2293setProfileIconStates$lambda14(this.f$0, model, view2);
                    }
                });
            } else {
                view.showFollowIcon(false);
            }
        }
        Context context4 = this.mContext;
        view.setShareAttributes(context4 == null ? null : context4.getDrawable(R$drawable.ic_action_shareandroid), model.getIsSelfView());
        view.showShareIcon(!Intrinsics.areEqual(model.getProfileStatus(), "HIDDEN"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setProfileIconStates$lambda-11, reason: not valid java name */
    public static final void m2292setProfileIconStates$lambda11(CustomerProfileBinder this$0, CustomerProfileModel model, View v) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        CustomerProfileClickListener customerProfileClickListener = this$0.profileClickListener;
        Intrinsics.checkNotNullExpressionValue(v, "v");
        customerProfileClickListener.onFollowClick(v, model);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setProfileIconStates$lambda-14, reason: not valid java name */
    public static final void m2293setProfileIconStates$lambda14(CustomerProfileBinder this$0, CustomerProfileModel model, View v) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        CustomerProfileClickListener customerProfileClickListener = this$0.profileClickListener;
        Intrinsics.checkNotNullExpressionValue(v, "v");
        customerProfileClickListener.onFollowClick(v, model);
    }

    private final void setFollowingClickListener(CustomerProfileView view, final CustomerProfileModel model) {
        view.setFollowingLayoutClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.CustomerProfileBinder$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CustomerProfileBinder.m2290setFollowingClickListener$lambda15(this.f$0, model, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setFollowingClickListener$lambda-15, reason: not valid java name */
    public static final void m2290setFollowingClickListener$lambda15(CustomerProfileBinder this$0, CustomerProfileModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        this$0.profileClickListener.onFollowingClick(model);
    }

    private final void setFollowersClickListener(CustomerProfileView view, final CustomerProfileModel model) {
        view.setFollowersLayoutClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.CustomerProfileBinder$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CustomerProfileBinder.m2289setFollowersClickListener$lambda16(this.f$0, model, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setFollowersClickListener$lambda-16, reason: not valid java name */
    public static final void m2289setFollowersClickListener$lambda16(CustomerProfileBinder this$0, CustomerProfileModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        this$0.profileClickListener.onFollowersClick(model);
    }

    private final void setOverflowClickListener(CustomerProfileView view, final CustomerProfileModel model) {
        view.setSetUpOverflowClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.CustomerProfileBinder$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CustomerProfileBinder.m2291setOverflowClickListener$lambda17(this.f$0, model, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setOverflowClickListener$lambda-17, reason: not valid java name */
    public static final void m2291setOverflowClickListener$lambda17(CustomerProfileBinder this$0, CustomerProfileModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        this$0.profileClickListener.onOverflowClick(model);
    }
}
