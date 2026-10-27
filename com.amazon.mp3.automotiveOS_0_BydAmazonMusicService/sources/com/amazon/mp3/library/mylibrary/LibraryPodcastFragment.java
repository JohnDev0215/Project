package com.amazon.mp3.library.mylibrary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.util.MyLibraryPodcastsToggleHelper;
import com.amazon.podcast.FragmentInfo;
import com.amazon.podcast.Podcast;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryPodcastFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J&\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u001a\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0006\u0010\u0011\u001a\u00020\u0004¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/library/mylibrary/LibraryPodcastFragment;", "Landroidx/fragment/app/Fragment;", "()V", "navigateToPodcasts", "", "deeplinkPath", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "setPodcastChildFragmentManager", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryPodcastFragment extends Fragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = LibraryPodcastFragment.class.getSimpleName();

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_library_podcast, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        String string;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        String str = "";
        if (arguments != null && (string = arguments.getString("deeplinkUri", "")) != null) {
            str = string;
        }
        navigateToPodcasts(str);
    }

    public final void setPodcastChildFragmentManager() {
        if (isAdded()) {
            FragmentInfo fragmentInfo = FragmentInfo.INSTANCE;
            fragmentInfo.setChildFragmentId(Integer.valueOf(R.id.library_podcast_fragment));
            fragmentInfo.setChildFragmentManager(getChildFragmentManager());
        }
    }

    private final void navigateToPodcasts(String deeplinkPath) {
        FragmentInfo fragmentInfo = FragmentInfo.INSTANCE;
        fragmentInfo.setChildFragmentId(Integer.valueOf(R.id.library_podcast_fragment));
        fragmentInfo.setChildFragmentManager(getChildFragmentManager());
        MyLibraryPodcastsToggleHelper myLibraryPodcastsToggleHelper = MyLibraryPodcastsToggleHelper.INSTANCE;
        Podcast.create(myLibraryPodcastsToggleHelper.getPodcastBootstrap(getActivity(), deeplinkPath)).startLibrary(deeplinkPath, myLibraryPodcastsToggleHelper.isPodcastsToggleSupported());
    }

    /* JADX INFO: compiled from: LibraryPodcastFragment.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bR\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/library/mylibrary/LibraryPodcastFragment$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "newInstance", "Lcom/amazon/mp3/library/mylibrary/LibraryPodcastFragment;", "args", "Landroid/os/Bundle;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return LibraryPodcastFragment.TAG;
        }

        public final LibraryPodcastFragment newInstance(Bundle args) {
            LibraryPodcastFragment libraryPodcastFragment = new LibraryPodcastFragment();
            libraryPodcastFragment.setArguments(args);
            return libraryPodcastFragment;
        }
    }
}
