package com.amazon.mp3.playlist;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.helper.CreatePlaylistHelper;
import com.amazon.mp3.playlist.CreatePublicPlaylistWarningDialog;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CreatePublicPlaylistWarningDialogFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 #2\u00020\u00012\u00020\u0002:\u0002\"#B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ \u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0013H\u0016J\b\u0010\u001a\u001a\u00020\u0013H\u0016J\b\u0010\u001b\u001a\u00020\u0013H\u0016J\b\u0010\u001c\u001a\u00020\u0013H\u0016J\b\u0010\u001d\u001a\u00020\u0013H\u0016J\b\u0010\u001e\u001a\u00020\u0013H\u0016J\b\u0010\u001f\u001a\u00020\u0013H\u0002J\u0010\u0010 \u001a\u00020\u00132\u0006\u0010!\u001a\u00020\bH\u0002R\u0014\u0010\n\u001a\u00020\u000bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n \u0010*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/mp3/playlist/CreatePublicPlaylistWarningDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "Lcom/amazon/mp3/playlist/CreatePublicPlaylistWarningDialog;", "playlistName", "", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "isLibrary", "", "(Ljava/lang/String;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Z)V", "MODE_CREATE_PLAYLIST", "", "getMODE_CREATE_PLAYLIST", "()I", "SHARED_PREF_NAME", "TAG", "kotlin.jvm.PlatformType", "playlistCreationTriggered", "createPlaylist", "", "isPublicToggleEnabled", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDestroyView", "onDismissed", "onNegativeButtonClicked", "onPositiveButtonClicked", "onResume", "setHasSeenPublicPlaylistCreationWarning", "setPublicPlaylistCreationToggleSavedState", "stateToSave", "Builder", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CreatePublicPlaylistWarningDialogFragment extends DialogFragment implements CreatePublicPlaylistWarningDialog {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int MODE_CREATE_PLAYLIST;
    private final String SHARED_PREF_NAME;
    private final String TAG;
    private final boolean isLibrary;
    private final PageType pageType;
    private boolean playlistCreationTriggered;
    private final String playlistName;

    @Override // com.amazon.mp3.playlist.CreatePublicPlaylistWarningDialog
    public void onDismissed() {
    }

    public Dialog getDialog(Context context) {
        return CreatePublicPlaylistWarningDialog.DefaultImpls.getDialog(this, context);
    }

    public CreatePublicPlaylistWarningDialogFragment(String playlistName, PageType pageType, boolean z) {
        Intrinsics.checkNotNullParameter(playlistName, "playlistName");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        this.playlistName = playlistName;
        this.pageType = pageType;
        this.isLibrary = z;
        this.TAG = CreatePlaylistDialogFragment.class.getSimpleName();
        this.SHARED_PREF_NAME = "com.amazon.mp3.CreatePlaylistDialogFragment";
    }

    public final int getMODE_CREATE_PLAYLIST() {
        return this.MODE_CREATE_PLAYLIST;
    }

    /* JADX INFO: compiled from: CreatePublicPlaylistWarningDialogFragment.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/playlist/CreatePublicPlaylistWarningDialogFragment$Builder;", "", "()V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/mp3/playlist/CreatePublicPlaylistWarningDialogFragment;", "playlistName", "", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "isLibrary", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Builder {
        public final CreatePublicPlaylistWarningDialogFragment build(String playlistName, PageType pageType, boolean isLibrary) {
            Intrinsics.checkNotNullParameter(playlistName, "playlistName");
            Intrinsics.checkNotNullParameter(pageType, "pageType");
            return new CreatePublicPlaylistWarningDialogFragment(playlistName, pageType, isLibrary);
        }
    }

    /* JADX INFO: compiled from: CreatePublicPlaylistWarningDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/playlist/CreatePublicPlaylistWarningDialogFragment$Companion;", "", "()V", "create", "Lcom/amazon/mp3/playlist/CreatePublicPlaylistWarningDialogFragment$Builder;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Builder create() {
            return new Builder();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.setRequestedOrientation(1);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.setRequestedOrientation(-1);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        return getDialog(contextRequireContext);
    }

    @Override // com.amazon.mp3.playlist.CreatePublicPlaylistWarningDialog
    public void onPositiveButtonClicked() {
        createPlaylist(this.playlistName, true, this.pageType);
        this.playlistCreationTriggered = true;
        dismiss();
    }

    @Override // com.amazon.mp3.playlist.CreatePublicPlaylistWarningDialog
    public void onNegativeButtonClicked() {
        createPlaylist(this.playlistName, false, this.pageType);
        this.playlistCreationTriggered = true;
        dismiss();
    }

    private final void createPlaylist(String playlistName, boolean isPublicToggleEnabled, PageType pageType) {
        setPublicPlaylistCreationToggleSavedState(isPublicToggleEnabled);
        if (!this.isLibrary) {
            Intent intent = new Intent();
            intent.putExtra("com.amazon.mp3.library.activity.playlist.EXTRA_PLAYLIST_MODE", this.MODE_CREATE_PLAYLIST);
            intent.putExtra("com.amazon.mp3.library.activity.playlist.EXTRA_PLAYLIST_NAME", playlistName);
            requireActivity().setResult(-1, intent);
            FragmentActivity activity = getActivity();
            if (activity == null) {
                return;
            }
            activity.finish();
            return;
        }
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
        CreatePlaylistHelper createPlaylistHelper = new CreatePlaylistHelper(fragmentActivityRequireActivity);
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        CreatePlaylistHelper.createPlaylist$default(createPlaylistHelper, contextRequireContext, playlistName, isPublicToggleEnabled, pageType, null, 16, null);
    }

    private final void setHasSeenPublicPlaylistCreationWarning() {
        FragmentActivity activity = getActivity();
        SharedPreferences sharedPreferences = activity == null ? null : activity.getSharedPreferences(this.SHARED_PREF_NAME, 0);
        if (sharedPreferences == null || sharedPreferences.getBoolean(getString(R.string.playlist_creation_flow_public_toggle_warning_shown), false)) {
            return;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putBoolean(getString(R.string.playlist_creation_flow_public_toggle_warning_shown), true);
        editorEdit.apply();
    }

    private final void setPublicPlaylistCreationToggleSavedState(boolean stateToSave) {
        FragmentActivity activity = getActivity();
        SharedPreferences sharedPreferences = activity == null ? null : activity.getSharedPreferences(this.SHARED_PREF_NAME, 0);
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putBoolean(getString(R.string.playlist_creation_public_toggle_last_state), stateToSave);
        editorEdit.apply();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        setHasSeenPublicPlaylistCreationWarning();
    }
}
