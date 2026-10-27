package com.amazon.mp3.catalog.fragment.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.amazon.mp3.catalog.fragment.helper.CreatePlaylistHelper;
import kotlin.Metadata;

/* JADX INFO: compiled from: CreatePlaylistResultViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0004J\u0010\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\tR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/CreatePlaylistResultViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "didCreatePlaylistEntryInDb", "Landroidx/lifecycle/MutableLiveData;", "", "getDidCreatePlaylistEntryInDb", "()Landroidx/lifecycle/MutableLiveData;", "playlistInfoLiveData", "Lcom/amazon/mp3/catalog/fragment/helper/CreatePlaylistHelper$PlaylistInfo;", "getPlaylistInfoLiveData", "setPlaylistInfoLiveData", "", "result", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CreatePlaylistResultViewModel extends ViewModel {
    private final MutableLiveData<Boolean> didCreatePlaylistEntryInDb = new MutableLiveData<>();
    private MutableLiveData<CreatePlaylistHelper.PlaylistInfo> playlistInfoLiveData;

    public final MutableLiveData<Boolean> getDidCreatePlaylistEntryInDb() {
        return this.didCreatePlaylistEntryInDb;
    }

    public final void setPlaylistInfoLiveData(CreatePlaylistHelper.PlaylistInfo result) {
        MutableLiveData<CreatePlaylistHelper.PlaylistInfo> mutableLiveData = this.playlistInfoLiveData;
        if (mutableLiveData == null) {
            return;
        }
        mutableLiveData.setValue(result);
    }

    public final MutableLiveData<CreatePlaylistHelper.PlaylistInfo> getPlaylistInfoLiveData() {
        if (this.playlistInfoLiveData == null) {
            this.playlistInfoLiveData = new MutableLiveData<>();
        }
        MutableLiveData<CreatePlaylistHelper.PlaylistInfo> mutableLiveData = this.playlistInfoLiveData;
        if (mutableLiveData != null) {
            return mutableLiveData;
        }
        throw new NullPointerException("null cannot be cast to non-null type androidx.lifecycle.MutableLiveData<com.amazon.mp3.catalog.fragment.helper.CreatePlaylistHelper.PlaylistInfo?>");
    }
}
