package com.android.car.developeroptions.print;

import android.content.Context;
import android.print.PrintManager;
import android.print.PrintServicesLoader;
import android.printservice.PrintServiceInfo;
import androidx.loader.content.Loader;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class SettingsPrintServicesLoader extends Loader<List<PrintServiceInfo>> {
    private PrintServicesLoader mLoader;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsPrintServicesLoader(PrintManager printManager, Context context, int i) {
        super(context);
        Objects.requireNonNull(context);
        this.mLoader = new PrintServicesLoader(printManager, context, i) { // from class: com.android.car.developeroptions.print.SettingsPrintServicesLoader.1
            public void deliverResult(List<PrintServiceInfo> list) {
                super.deliverResult(list);
                SettingsPrintServicesLoader.this.deliverResult(list);
            }
        };
    }

    @Override // androidx.loader.content.Loader
    protected void onForceLoad() {
        this.mLoader.forceLoad();
    }

    @Override // androidx.loader.content.Loader
    protected void onStartLoading() {
        this.mLoader.startLoading();
    }

    @Override // androidx.loader.content.Loader
    protected void onStopLoading() {
        this.mLoader.stopLoading();
    }

    @Override // androidx.loader.content.Loader
    protected boolean onCancelLoad() {
        return this.mLoader.cancelLoad();
    }

    @Override // androidx.loader.content.Loader
    protected void onAbandon() {
        this.mLoader.abandon();
    }

    @Override // androidx.loader.content.Loader
    protected void onReset() {
        this.mLoader.reset();
    }
}
