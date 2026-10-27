package com.amazon.mp3.fragment.viewmodel;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.amazon.mp3.fragment.model.AudioStatsRowModel;
import com.amazon.mp3.fragment.view.KatanaRecyclerviewBinding;
import com.amazon.mp3.fragment.view.recyclerview.adapter.AudioStatsRecyclerAdapter;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AudioStatsRecyclerViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\t\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b$\u0010%J\b\u0010\u0003\u001a\u00020\u0002H\u0002R\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR(\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R$\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\"\u0010\u001e\u001a\u00020\u001d8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006&"}, d2 = {"Lcom/amazon/mp3/fragment/viewmodel/AudioStatsRecyclerViewModel;", "Lcom/amazon/mp3/fragment/viewmodel/BaseViewModel;", "", "setupRecyclerView", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/mp3/fragment/view/KatanaRecyclerviewBinding;", "mBinding", "Lcom/amazon/mp3/fragment/view/KatanaRecyclerviewBinding;", "getMBinding", "()Lcom/amazon/mp3/fragment/view/KatanaRecyclerviewBinding;", "", "Lcom/amazon/mp3/fragment/model/AudioStatsRowModel;", "audioStatsRowModelList", "Ljava/util/List;", "getAudioStatsRowModelList", "()Ljava/util/List;", "setAudioStatsRowModelList", "(Ljava/util/List;)V", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "viewModelCallbacks", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "getViewModelCallbacks", "()Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "setViewModelCallbacks", "(Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "Lcom/amazon/mp3/fragment/view/recyclerview/adapter/AudioStatsRecyclerAdapter;", "mAudioStatsRecyclerAdapter", "Lcom/amazon/mp3/fragment/view/recyclerview/adapter/AudioStatsRecyclerAdapter;", "getMAudioStatsRecyclerAdapter", "()Lcom/amazon/mp3/fragment/view/recyclerview/adapter/AudioStatsRecyclerAdapter;", "setMAudioStatsRecyclerAdapter", "(Lcom/amazon/mp3/fragment/view/recyclerview/adapter/AudioStatsRecyclerAdapter;)V", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/fragment/view/KatanaRecyclerviewBinding;Ljava/util/List;Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AudioStatsRecyclerViewModel extends BaseViewModel {
    private List<AudioStatsRowModel> audioStatsRowModelList;
    private final Context context;
    public AudioStatsRecyclerAdapter mAudioStatsRecyclerAdapter;
    private final KatanaRecyclerviewBinding mBinding;
    private ViewModelCallbacks viewModelCallbacks;

    public Context getContext() {
        return this.context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioStatsRecyclerViewModel(Context context, KatanaRecyclerviewBinding mBinding, List<AudioStatsRowModel> audioStatsRowModelList, ViewModelCallbacks viewModelCallbacks) {
        super(context, viewModelCallbacks);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mBinding, "mBinding");
        Intrinsics.checkNotNullParameter(audioStatsRowModelList, "audioStatsRowModelList");
        this.context = context;
        this.mBinding = mBinding;
        this.audioStatsRowModelList = audioStatsRowModelList;
        this.viewModelCallbacks = viewModelCallbacks;
        setupRecyclerView();
    }

    public final AudioStatsRecyclerAdapter getMAudioStatsRecyclerAdapter() {
        AudioStatsRecyclerAdapter audioStatsRecyclerAdapter = this.mAudioStatsRecyclerAdapter;
        if (audioStatsRecyclerAdapter != null) {
            return audioStatsRecyclerAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mAudioStatsRecyclerAdapter");
        return null;
    }

    public final void setMAudioStatsRecyclerAdapter(AudioStatsRecyclerAdapter audioStatsRecyclerAdapter) {
        Intrinsics.checkNotNullParameter(audioStatsRecyclerAdapter, "<set-?>");
        this.mAudioStatsRecyclerAdapter = audioStatsRecyclerAdapter;
    }

    private final void setupRecyclerView() {
        setMAudioStatsRecyclerAdapter(new AudioStatsRecyclerAdapter(getContext(), null));
        getMAudioStatsRecyclerAdapter().setItems(this.audioStatsRowModelList);
        this.mBinding.getMRecyclerView().setLayoutManager(new LinearLayoutManager(getContext()));
        this.mBinding.getMRecyclerView().setAdapter(getMAudioStatsRecyclerAdapter());
    }
}
