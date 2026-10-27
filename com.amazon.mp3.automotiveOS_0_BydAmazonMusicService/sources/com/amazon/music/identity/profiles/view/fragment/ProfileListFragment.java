package com.amazon.music.identity.profiles.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.identity.profiles.ProfileIdentityMainActivity;
import com.amazon.music.identity.profiles.R$color;
import com.amazon.music.identity.profiles.R$id;
import com.amazon.music.identity.profiles.R$layout;
import com.amazon.music.identity.profiles.view.adapter.ProfileAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProfileListFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018\u0000 +2\u00020\u0001:\u0001+B\u0007¢\u0006\u0004\b)\u0010*J$\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016R\"\u0010\u000b\u001a\u00020\n8\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\"\u0010\u0011\u001a\u00020\n8\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\"\u0010\u0015\u001a\u00020\u00148\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\"\u0010\u001c\u001a\u00020\u001b8\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R$\u0010#\u001a\u0004\u0018\u00010\"8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(¨\u0006,"}, d2 = {"Lcom/amazon/music/identity/profiles/view/fragment/ProfileListFragment;", "Landroidx/fragment/app/Fragment;", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/os/Bundle;", "savedInstanceState", "Landroid/view/View;", "onCreateView", "Landroid/widget/TextView;", "mTitle", "Landroid/widget/TextView;", "getMTitle", "()Landroid/widget/TextView;", "setMTitle", "(Landroid/widget/TextView;)V", "mSubText", "getMSubText", "setMSubText", "Landroidx/recyclerview/widget/RecyclerView;", "mRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getMRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "setMRecyclerView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "Lcom/amazon/music/identity/profiles/view/adapter/ProfileAdapter;", "mProfileAdapter", "Lcom/amazon/music/identity/profiles/view/adapter/ProfileAdapter;", "getMProfileAdapter", "()Lcom/amazon/music/identity/profiles/view/adapter/ProfileAdapter;", "setMProfileAdapter", "(Lcom/amazon/music/identity/profiles/view/adapter/ProfileAdapter;)V", "Lcom/amazon/music/views/library/styles/StyleSheet;", "mStyleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getMStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "setMStyleSheet", "(Lcom/amazon/music/views/library/styles/StyleSheet;)V", "<init>", "()V", "Companion", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0})
public abstract class ProfileListFragment extends Fragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = ProfileListFragment.class.getSimpleName();
    private static final int VIEW_ID = R$layout.profile_list_fragment;
    protected ProfileAdapter mProfileAdapter;
    protected RecyclerView mRecyclerView;
    private StyleSheet mStyleSheet;
    protected TextView mSubText;
    protected TextView mTitle;

    /* JADX INFO: compiled from: ProfileListFragment.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/music/identity/profiles/view/fragment/ProfileListFragment$Companion;", "", "", "VIEW_ID", "I", "getVIEW_ID", "()I", "<init>", "()V", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getVIEW_ID() {
            return ProfileListFragment.VIEW_ID;
        }
    }

    public ProfileListFragment() {
        super(R$layout.profile_list_fragment);
    }

    protected final TextView getMTitle() {
        TextView textView = this.mTitle;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mTitle");
        return null;
    }

    protected final void setMTitle(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.mTitle = textView;
    }

    protected final TextView getMSubText() {
        TextView textView = this.mSubText;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mSubText");
        return null;
    }

    protected final void setMSubText(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.mSubText = textView;
    }

    protected final RecyclerView getMRecyclerView() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return recyclerView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
        return null;
    }

    protected final void setMRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "<set-?>");
        this.mRecyclerView = recyclerView;
    }

    protected final ProfileAdapter getMProfileAdapter() {
        ProfileAdapter profileAdapter = this.mProfileAdapter;
        if (profileAdapter != null) {
            return profileAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mProfileAdapter");
        return null;
    }

    protected final void setMProfileAdapter(ProfileAdapter profileAdapter) {
        Intrinsics.checkNotNullParameter(profileAdapter, "<set-?>");
        this.mProfileAdapter = profileAdapter;
    }

    protected final StyleSheet getMStyleSheet() {
        return this.mStyleSheet;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FontStyle fontStyle;
        FontStyle fontStyle2;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View v = inflater.inflate(R$layout.profile_list_fragment, container, false);
        View viewFindViewById = v.findViewById(R$id.profile_picker_gridview);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "v.findViewById(R.id.profile_picker_gridview)");
        setMRecyclerView((RecyclerView) viewFindViewById);
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.identity.profiles.ProfileIdentityMainActivity");
        }
        this.mStyleSheet = ((ProfileIdentityMainActivity) activity).getMStyleSheet();
        View viewFindViewById2 = v.findViewById(R$id.profile_picker_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "v.findViewById(R.id.profile_picker_title)");
        setMTitle((TextView) viewFindViewById2);
        StyleSheet styleSheet = this.mStyleSheet;
        if (styleSheet != null && (fontStyle2 = styleSheet.getFontStyle(FontStyleKey.PRIMARY_TITLE)) != null) {
            FontUtil.INSTANCE.applyFontStyle(getMTitle(), fontStyle2);
        }
        View viewFindViewById3 = v.findViewById(R$id.profile_picker_sub_text);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "v.findViewById(R.id.profile_picker_sub_text)");
        setMSubText((TextView) viewFindViewById3);
        StyleSheet styleSheet2 = this.mStyleSheet;
        if (styleSheet2 != null && (fontStyle = styleSheet2.getFontStyle(FontStyleKey.PRIMARY_2)) != null) {
            FontUtil.INSTANCE.applyFontStyle(getMSubText(), fontStyle);
            getMSubText().setTextColor(getResources().getColor(R$color.grey_yellow_100));
        }
        Intrinsics.checkNotNullExpressionValue(v, "v");
        return v;
    }
}
