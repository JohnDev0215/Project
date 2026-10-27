package com.vivid.spotify;

import android.app.Application;
import androidx.databinding.ObservableBoolean;
import com.vivid.spotify.common.base.BaseViewModel;

/* JADX INFO: loaded from: classes.dex */
public class PermissionViewModel extends BaseViewModel {
    public final ObservableBoolean hasBeenRefused;

    public PermissionViewModel(Application application) {
        super(application);
        this.hasBeenRefused = new ObservableBoolean(false);
    }
}
