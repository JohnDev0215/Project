package com.android.launcher3.model;

import com.android.launcher3.AllAppsList;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.widget.WidgetListRowEntry;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public class LoaderResults extends BaseLoaderResults {
    public LoaderResults(LauncherAppState app, BgDataModel dataModel, AllAppsList allAppsList, int pageToBindFirst, WeakReference<LauncherModel.Callbacks> callbacks) {
        super(app, dataModel, allAppsList, pageToBindFirst, callbacks);
    }

    @Override // com.android.launcher3.model.BaseLoaderResults
    public void bindDeepShortcuts() {
        final HashMap map;
        synchronized (this.mBgDataModel) {
            map = new HashMap(this.mBgDataModel.deepShortcutMap);
        }
        executeCallbacksTask(new LauncherModel.CallbackTask() { // from class: com.android.launcher3.model.-$$Lambda$LoaderResults$fytG0EvqMQepFEisnh7EFxOzDnA
            @Override // com.android.launcher3.LauncherModel.CallbackTask
            public final void execute(LauncherModel.Callbacks callbacks) {
                callbacks.bindDeepShortcutMap(map);
            }
        }, this.mUiExecutor);
    }

    @Override // com.android.launcher3.model.BaseLoaderResults
    public void bindWidgets() {
        final ArrayList<WidgetListRowEntry> widgetsList = this.mBgDataModel.widgetsModel.getWidgetsList(this.mApp.getContext());
        executeCallbacksTask(new LauncherModel.CallbackTask() { // from class: com.android.launcher3.model.-$$Lambda$LoaderResults$HbvgB9BewB_ietZTdvKVQ70B7y8
            @Override // com.android.launcher3.LauncherModel.CallbackTask
            public final void execute(LauncherModel.Callbacks callbacks) {
                callbacks.bindAllWidgets(widgetsList);
            }
        }, this.mUiExecutor);
    }
}
