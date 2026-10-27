package com.amazon.music.identity.profiles.view.adapter;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.identity.profiles.R$id;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProfileViewHolder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u00102\u001a\u00020$\u0012\b\u00103\u001a\u0004\u0018\u00010+¢\u0006\u0004\b4\u00105R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\n\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0010\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\"\u0010\u0014\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\"\u0010\u001a\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0015\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R\"\u0010\u001e\u001a\u00020\u001d8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\"\u0010%\u001a\u00020$8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R$\u0010,\u001a\u0004\u0018\u00010+8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101¨\u00066"}, d2 = {"Lcom/amazon/music/identity/profiles/view/adapter/ProfileViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroid/widget/FrameLayout;", "profileAvatarHolder", "Landroid/widget/FrameLayout;", "getProfileAvatarHolder", "()Landroid/widget/FrameLayout;", "setProfileAvatarHolder", "(Landroid/widget/FrameLayout;)V", "Landroid/widget/ImageView;", "profileAvatar", "Landroid/widget/ImageView;", "getProfileAvatar", "()Landroid/widget/ImageView;", "setProfileAvatar", "(Landroid/widget/ImageView;)V", "profileAvatarHighlight", "getProfileAvatarHighlight", "setProfileAvatarHighlight", "Landroid/widget/TextView;", "profileFullName", "Landroid/widget/TextView;", "getProfileFullName", "()Landroid/widget/TextView;", "setProfileFullName", "(Landroid/widget/TextView;)V", "profileHandle", "getProfileHandle", "setProfileHandle", "Landroid/widget/LinearLayout;", "mainContainer", "Landroid/widget/LinearLayout;", "getMainContainer", "()Landroid/widget/LinearLayout;", "setMainContainer", "(Landroid/widget/LinearLayout;)V", "Landroid/view/View;", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "setView", "(Landroid/view/View;)V", "Lcom/amazon/music/views/library/styles/StyleSheet;", "mStyleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getMStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "setMStyleSheet", "(Lcom/amazon/music/views/library/styles/StyleSheet;)V", "itemView", "styleSheet", "<init>", "(Landroid/view/View;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0})
public final class ProfileViewHolder extends RecyclerView.ViewHolder {
    private StyleSheet mStyleSheet;
    private LinearLayout mainContainer;
    private ImageView profileAvatar;
    private ImageView profileAvatarHighlight;
    private FrameLayout profileAvatarHolder;
    private TextView profileFullName;
    private TextView profileHandle;
    private View view;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileViewHolder(View itemView, StyleSheet styleSheet) {
        super(itemView);
        Intrinsics.checkNotNullParameter(itemView, "itemView");
        View viewFindViewById = itemView.findViewById(R$id.profile_image_relative_view);
        if (viewFindViewById == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.FrameLayout");
        }
        this.profileAvatarHolder = (FrameLayout) viewFindViewById;
        View viewFindViewById2 = itemView.findViewById(R$id.profile_avatar_highlight);
        if (viewFindViewById2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.profileAvatarHighlight = (ImageView) viewFindViewById2;
        View viewFindViewById3 = itemView.findViewById(R$id.profile_avatar);
        if (viewFindViewById3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.profileAvatar = (ImageView) viewFindViewById3;
        View viewFindViewById4 = itemView.findViewById(R$id.profile_full_name);
        if (viewFindViewById4 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.profileFullName = (TextView) viewFindViewById4;
        View viewFindViewById5 = itemView.findViewById(R$id.profile_handle);
        if (viewFindViewById5 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.profileHandle = (TextView) viewFindViewById5;
        View viewFindViewById6 = itemView.findViewById(R$id.main_container);
        if (viewFindViewById6 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout");
        }
        this.mainContainer = (LinearLayout) viewFindViewById6;
        this.mStyleSheet = styleSheet;
        this.view = itemView;
    }

    public final FrameLayout getProfileAvatarHolder() {
        return this.profileAvatarHolder;
    }

    public final ImageView getProfileAvatar() {
        return this.profileAvatar;
    }

    public final ImageView getProfileAvatarHighlight() {
        return this.profileAvatarHighlight;
    }

    public final TextView getProfileFullName() {
        return this.profileFullName;
    }

    public final TextView getProfileHandle() {
        return this.profileHandle;
    }

    public final LinearLayout getMainContainer() {
        return this.mainContainer;
    }

    public final View getView() {
        return this.view;
    }
}
