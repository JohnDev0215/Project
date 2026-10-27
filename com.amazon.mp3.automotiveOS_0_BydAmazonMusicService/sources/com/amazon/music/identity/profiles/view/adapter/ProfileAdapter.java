package com.amazon.music.identity.profiles.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.identity.profiles.R$color;
import com.amazon.music.identity.profiles.R$layout;
import com.amazon.music.identity.profiles.datasource.mockprofile.ProfileBaseModel;
import com.amazon.music.identity.profiles.datasource.mockprofile.ProfileButton;
import com.amazon.music.identity.profiles.datasource.mockprofile.ProfileModel;
import com.amazon.music.identity.profiles.view.util.ViewUtil;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProfileAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0012\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B?\u0012\u001a\u0010\u001e\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u001cj\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u001d\u0012\u0006\u0010%\u001a\u00020$\u0012\b\u0010,\u001a\u0004\u0018\u00010+\u0012\b\b\u0002\u00102\u001a\u00020\b¢\u0006\u0004\b;\u0010<J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J \u0010\n\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\bH\u0002J\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\r\u001a\u00020\fH$J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\bH$J\u0018\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\bH\u0016J\u0018\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\bH\u0016J\b\u0010\u0015\u001a\u00020\bH\u0016J0\u0010\u001b\u001a\u00020\u00062\u000e\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00162\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0018H\u0004R6\u0010\u001e\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u001cj\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u001d8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\"\u0010%\u001a\u00020$8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R$\u0010,\u001a\u0004\u0018\u00010+8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\"\u00102\u001a\u00020\b8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b2\u00103\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\"\u00108\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b8\u00103\u001a\u0004\b9\u00105\"\u0004\b:\u00107¨\u0006="}, d2 = {"Lcom/amazon/music/identity/profiles/view/adapter/ProfileAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/music/identity/profiles/view/adapter/ProfileViewHolder;", "profileViewHolder", "Lcom/amazon/music/identity/profiles/datasource/mockprofile/ProfileBaseModel;", "profileBaseModel", "", "setButtonData", "", "index", "setProfileData", "setStyleAndText", "Landroid/view/View$OnClickListener;", "getButtonOnclickListener", "pid", "getProfileOnClickListener", "Landroid/view/ViewGroup;", "parent", "i", "onCreateViewHolder", "onBindViewHolder", "getItemCount", "", "list", "", "buttonText", "buttonSubText", "createAddButton", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "mList", "Ljava/util/ArrayList;", "getMList", "()Ljava/util/ArrayList;", "setMList", "(Ljava/util/ArrayList;)V", "Landroid/content/Context;", "mContext", "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "Lcom/amazon/music/views/library/styles/StyleSheet;", "mStyleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getMStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "setMStyleSheet", "(Lcom/amazon/music/views/library/styles/StyleSheet;)V", "profilePictureSizeID", "I", "getProfilePictureSizeID", "()I", "setProfilePictureSizeID", "(I)V", "selectedIndex", "getSelectedIndex", "setSelectedIndex", "<init>", "(Ljava/util/ArrayList;Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;I)V", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0})
public abstract class ProfileAdapter extends RecyclerView.Adapter<ProfileViewHolder> {
    private Context mContext;
    private ArrayList<ProfileBaseModel> mList;
    private StyleSheet mStyleSheet;
    private int profilePictureSizeID;
    private int selectedIndex;

    protected abstract View.OnClickListener getButtonOnclickListener();

    protected abstract View.OnClickListener getProfileOnClickListener(int pid);

    protected final ArrayList<ProfileBaseModel> getMList() {
        return this.mList;
    }

    protected final Context getMContext() {
        return this.mContext;
    }

    protected final int getProfilePictureSizeID() {
        return this.profilePictureSizeID;
    }

    protected final void setProfilePictureSizeID(int i) {
        this.profilePictureSizeID = i;
    }

    public ProfileAdapter(ArrayList<ProfileBaseModel> arrayList, Context mContext, StyleSheet styleSheet, int i) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.mList = arrayList;
        this.mContext = mContext;
        this.mStyleSheet = styleSheet;
        this.profilePictureSizeID = i;
    }

    public final int getSelectedIndex() {
        return this.selectedIndex;
    }

    public final void setSelectedIndex(int i) {
        this.selectedIndex = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R$layout.profile_avatar, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ProfileViewHolder(view, this.mStyleSheet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ProfileViewHolder profileViewHolder, int i) {
        Intrinsics.checkNotNullParameter(profileViewHolder, "profileViewHolder");
        ArrayList<ProfileBaseModel> arrayList = this.mList;
        int size = i % (arrayList == null ? 1 : arrayList.size());
        ArrayList<ProfileBaseModel> arrayList2 = this.mList;
        ProfileBaseModel profileBaseModel = arrayList2 == null ? null : arrayList2.get(size);
        if (profileBaseModel != null) {
            if (profileBaseModel instanceof ProfileButton) {
                setButtonData(profileViewHolder, profileBaseModel);
                profileViewHolder.getMainContainer().setOnClickListener(getButtonOnclickListener());
            } else {
                setProfileData(profileViewHolder, profileBaseModel, size);
                ProfileModel profileModel = (ProfileModel) profileBaseModel;
                if (profileModel.getIsAvailable()) {
                    profileViewHolder.getMainContainer().setOnClickListener(getProfileOnClickListener(profileModel.getProfileId()));
                }
            }
            setStyleAndText(profileViewHolder, profileBaseModel);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<ProfileBaseModel> arrayList = this.mList;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public static /* synthetic */ void createAddButton$default(ProfileAdapter profileAdapter, List list, String str, String str2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createAddButton");
        }
        if ((i & 2) != 0) {
            str = "";
        }
        if ((i & 4) != 0) {
            str2 = "";
        }
        profileAdapter.createAddButton(list, str, str2);
    }

    protected final void createAddButton(List<? extends ProfileBaseModel> list, String buttonText, String buttonSubText) {
        Intrinsics.checkNotNullParameter(list, "list");
        if (list.size() < 6) {
            ProfileButton profileButton = new ProfileButton(buttonText, buttonSubText, "drawable.add_button");
            ArrayList<ProfileBaseModel> arrayList = this.mList;
            if (arrayList == null) {
                return;
            }
            arrayList.add(profileButton);
        }
    }

    private final void setButtonData(ProfileViewHolder profileViewHolder, ProfileBaseModel profileBaseModel) {
        profileViewHolder.getProfileFullName().setText(profileBaseModel.getFullName());
        String avatarUrl = profileBaseModel.getAvatarUrl();
        if (avatarUrl != null) {
            ViewUtil.INSTANCE.setImageViewByResId(profileViewHolder.getProfileAvatar(), avatarUrl);
        }
        profileViewHolder.getProfileAvatarHighlight().setVisibility(8);
        profileViewHolder.getProfileFullName().setTextColor(ContextCompat.getColor(profileViewHolder.getView().getContext(), R$color.primary_text_color));
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0008  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void setProfileData(com.amazon.music.identity.profiles.view.adapter.ProfileViewHolder r10, com.amazon.music.identity.profiles.datasource.mockprofile.ProfileBaseModel r11, int r12) {
        /*
            Method dump skipped, instruction units count: 289
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.identity.profiles.view.adapter.ProfileAdapter.setProfileData(com.amazon.music.identity.profiles.view.adapter.ProfileViewHolder, com.amazon.music.identity.profiles.datasource.mockprofile.ProfileBaseModel, int):void");
    }

    private final void setStyleAndText(ProfileViewHolder profileViewHolder, ProfileBaseModel profileBaseModel) {
        FontStyle fontStyle;
        FontStyle fontStyle2;
        profileViewHolder.getProfileHandle().setText(profileBaseModel.getHandle());
        StyleSheet styleSheet = this.mStyleSheet;
        if (styleSheet != null && (fontStyle2 = styleSheet.getFontStyle(FontStyleKey.PRIMARY)) != null) {
            FontUtil.INSTANCE.applyFontStyle(profileViewHolder.getProfileFullName(), fontStyle2);
        }
        StyleSheet styleSheet2 = this.mStyleSheet;
        if (styleSheet2 != null && (fontStyle = styleSheet2.getFontStyle(FontStyleKey.PRIMARY_2)) != null) {
            FontUtil.INSTANCE.applyFontStyle(profileViewHolder.getProfileHandle(), fontStyle);
            profileViewHolder.getProfileHandle().setTextColor(profileViewHolder.getView().getResources().getColor(R$color.white_60));
        }
        int dimension = (int) this.mContext.getResources().getDimension(this.profilePictureSizeID);
        ViewGroup.LayoutParams layoutParams = profileViewHolder.getProfileAvatarHolder().getLayoutParams();
        layoutParams.height = dimension;
        layoutParams.width = dimension;
        profileViewHolder.getProfileAvatarHolder().setLayoutParams(layoutParams);
        profileViewHolder.getProfileHandle().getLayoutParams().width = layoutParams.width;
        profileViewHolder.getProfileFullName().getLayoutParams().width = layoutParams.width;
    }
}
