package com.amazon.music.curate.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.music.curate.R;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: MadeForYouFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 #2\u00020\u00012\u00020\u0002:\u0001#B\u0005¢\u0006\u0002\u0010\u0003J\n\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J&\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\tH\u0016J\u0010\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\tH\u0016J\b\u0010\u001b\u001a\u00020\u0018H\u0016J\u001a\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0006\u0010\u001e\u001a\u00020\u0018J\u0018\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\"H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/music/curate/view/MadeForYouFragment;", "Lcom/amazon/music/curate/view/BaseFragment;", "Lcom/amazon/music/curate/view/MadeForYouCallback;", "()V", "contentContainer", "Landroid/view/View;", "header", "Landroid/widget/TextView;", "mySoundtrackLoaded", "", "personalizedPlaylistsLoaded", "showMadeForYou", "viewVisible", "", "getActionBar", "Landroidx/appcompat/app/ActionBar;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onMySoundtrackLoaded", "", "hasData", "onPersonalizedPlaylistsLoaded", "onResume", "onViewCreated", "view", "showOrHideContent", "updateFragment", "containerResId", "fragment", "Landroidx/fragment/app/Fragment;", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MadeForYouFragment extends BaseFragment implements MadeForYouCallback {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger LOG;
    private View contentContainer;
    private TextView header;
    private boolean mySoundtrackLoaded;
    private boolean personalizedPlaylistsLoaded;
    private boolean showMadeForYou;
    private int viewVisible = 8;

    @JvmStatic
    public static final MadeForYouFragment newInstance() {
        return INSTANCE.newInstance();
    }

    /* JADX INFO: compiled from: MadeForYouFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/curate/view/MadeForYouFragment$Companion;", "", "()V", "LOG", "Lorg/slf4j/Logger;", "newInstance", "Lcom/amazon/music/curate/view/MadeForYouFragment;", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final MadeForYouFragment newInstance() {
            MadeForYouFragment madeForYouFragment = new MadeForYouFragment();
            madeForYouFragment.setArguments(new Bundle());
            return madeForYouFragment;
        }
    }

    static {
        Logger logger = LoggerFactory.getLogger(MadeForYouFragment.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(MadeForYouFrag…t::class.java.simpleName)");
        LOG = logger;
    }

    @Override // com.amazon.music.curate.view.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return View.inflate(getActivity(), R.layout.made_for_you, null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        View viewFindViewById = view.findViewById(R.id.made_for_you_header);
        if (viewFindViewById == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
        }
        TextView textView = (TextView) viewFindViewById;
        this.header = textView;
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        textView.setText(context.getResources().getString(R.string.made_for_you_header));
        TextView textView2 = this.header;
        if (textView2 != null) {
            Context context2 = getContext();
            Intrinsics.checkNotNull(context2);
            AssetManager assets = context2.getResources().getAssets();
            Context context3 = getContext();
            Intrinsics.checkNotNull(context3);
            textView2.setTypeface(Typeface.createFromAsset(assets, context3.getResources().getString(R.string.font_amazon_ember_bold)));
        }
        View viewFindViewById2 = view.findViewById(R.id.made_for_you_container);
        this.contentContainer = viewFindViewById2;
        if (viewFindViewById2 != null) {
            viewFindViewById2.setVisibility(8);
        }
        MySoundtrackFragment mySoundtrackFragment = new MySoundtrackFragment();
        mySoundtrackFragment.registerCallback(this);
        updateFragment(R.id.my_soundtrack, mySoundtrackFragment);
        PersonalizedPlaylistsFragment personalizedPlaylistsFragment = new PersonalizedPlaylistsFragment();
        personalizedPlaylistsFragment.registerCallback(this);
        updateFragment(R.id.personalized_playlists, personalizedPlaylistsFragment);
    }

    private final void updateFragment(int containerResId, Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(containerResId, fragment).commit();
    }

    @Override // com.amazon.music.curate.view.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        ActionBar actionBar = getActionBar();
        if (actionBar == null) {
            return;
        }
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        actionBar.setTitle(context.getResources().getString(R.string.made_for_you_header));
    }

    private final ActionBar getActionBar() {
        FragmentActivity activity = getActivity();
        AppCompatActivity appCompatActivity = activity instanceof AppCompatActivity ? (AppCompatActivity) activity : null;
        if (appCompatActivity == null) {
            return null;
        }
        return appCompatActivity.getSupportActionBar();
    }

    @Override // com.amazon.music.curate.view.MadeForYouCallback
    public void onPersonalizedPlaylistsLoaded(boolean hasData) {
        boolean z = true;
        this.personalizedPlaylistsLoaded = true;
        if (!this.showMadeForYou && !hasData) {
            z = false;
        }
        this.showMadeForYou = z;
        LOG.debug(Intrinsics.stringPlus("has PersonalizedPlaylists data: ", Boolean.valueOf(hasData)));
        showOrHideContent();
    }

    @Override // com.amazon.music.curate.view.MadeForYouCallback
    public void onMySoundtrackLoaded(boolean hasData) {
        boolean z = true;
        this.mySoundtrackLoaded = true;
        if (!this.showMadeForYou && !hasData) {
            z = false;
        }
        this.showMadeForYou = z;
        LOG.debug(Intrinsics.stringPlus("has My Soundtrack data: ", Boolean.valueOf(hasData)));
        showOrHideContent();
    }

    public final void showOrHideContent() {
        if (this.personalizedPlaylistsLoaded && this.mySoundtrackLoaded) {
            int i = this.showMadeForYou ? 0 : 8;
            this.viewVisible = i;
            TextView textView = this.header;
            if (textView != null) {
                textView.setVisibility(i);
            }
            View view = this.contentContainer;
            if (view == null) {
                return;
            }
            view.setVisibility(this.viewVisible);
        }
    }
}
