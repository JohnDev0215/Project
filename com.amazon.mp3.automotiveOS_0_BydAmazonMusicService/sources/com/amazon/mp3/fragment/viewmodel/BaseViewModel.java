package com.amazon.mp3.fragment.viewmodel;

import android.content.Context;
import com.amazon.music.events.UserInteractionAppEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\t\u001a\u00020\b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR$\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/fragment/viewmodel/BaseViewModel;", "", "", "eventName", "", "sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease", "(Ljava/lang/String;)V", "sendUiClickMetric", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "viewModelCallbacks", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "getViewModelCallbacks", "()Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "setViewModelCallbacks", "(Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class BaseViewModel {
    private final Context context;
    private ViewModelCallbacks viewModelCallbacks;

    public BaseViewModel(Context context, ViewModelCallbacks viewModelCallbacks) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.viewModelCallbacks = viewModelCallbacks;
    }

    public final void sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease(String eventName) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        UserInteractionAppEvent.builder(eventName).publish();
    }
}
