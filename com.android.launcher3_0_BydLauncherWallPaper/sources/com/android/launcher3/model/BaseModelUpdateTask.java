package com.android.launcher3.model;

import com.android.launcher3.AllAppsList;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.util.ItemInfoMatcher;
import com.android.launcher3.widget.WidgetListRowEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BaseModelUpdateTask implements LauncherModel.ModelUpdateTask {
    private static final boolean DEBUG_TASKS = false;
    private static final String TAG = "BaseModelUpdateTask";
    private AllAppsList mAllAppsList;
    private LauncherAppState mApp;
    private BgDataModel mDataModel;
    private LauncherModel mModel;
    private Executor mUiExecutor;

    public abstract void execute(LauncherAppState app, BgDataModel dataModel, AllAppsList apps);

    @Override // com.android.launcher3.LauncherModel.ModelUpdateTask
    public void init(LauncherAppState app, LauncherModel model, BgDataModel dataModel, AllAppsList allAppsList, Executor uiExecutor) {
        this.mApp = app;
        this.mModel = model;
        this.mDataModel = dataModel;
        this.mAllAppsList = allAppsList;
        this.mUiExecutor = uiExecutor;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.mModel.isModelLoaded();
        execute(this.mApp, this.mDataModel, this.mAllAppsList);
    }

    public final void scheduleCallbackTask(final LauncherModel.CallbackTask task) {
        final LauncherModel.Callbacks callback = this.mModel.getCallback();
        this.mUiExecutor.execute(new Runnable() { // from class: com.android.launcher3.model.-$$Lambda$BaseModelUpdateTask$V2vooAZRjmlEsgEFOC5mFT3PqXE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$scheduleCallbackTask$0$BaseModelUpdateTask(callback, task);
            }
        });
    }

    public /* synthetic */ void lambda$scheduleCallbackTask$0$BaseModelUpdateTask(LauncherModel.Callbacks callbacks, LauncherModel.CallbackTask callbackTask) {
        LauncherModel.Callbacks callback = this.mModel.getCallback();
        if (callbacks != callback || callback == null) {
            return;
        }
        callbackTask.execute(callbacks);
    }

    public ModelWriter getModelWriter() {
        return this.mModel.getWriter(false, false);
    }

    public void bindUpdatedWorkspaceItems(final ArrayList<WorkspaceItemInfo> updatedShortcuts) {
        if (updatedShortcuts.isEmpty()) {
            return;
        }
        scheduleCallbackTask(new LauncherModel.CallbackTask() { // from class: com.android.launcher3.model.BaseModelUpdateTask.1
            @Override // com.android.launcher3.LauncherModel.CallbackTask
            public void execute(LauncherModel.Callbacks callbacks) {
                callbacks.bindWorkspaceItemsChanged(updatedShortcuts);
            }
        });
    }

    public void bindDeepShortcuts(BgDataModel dataModel) {
        final HashMap map = new HashMap(dataModel.deepShortcutMap);
        scheduleCallbackTask(new LauncherModel.CallbackTask() { // from class: com.android.launcher3.model.-$$Lambda$BaseModelUpdateTask$qvJyBOjmSmpkY9v7NPYO7X-W-XQ
            @Override // com.android.launcher3.LauncherModel.CallbackTask
            public final void execute(LauncherModel.Callbacks callbacks) {
                callbacks.bindDeepShortcutMap(map);
            }
        });
    }

    public void bindUpdatedWidgets(BgDataModel dataModel) {
        final ArrayList<WidgetListRowEntry> widgetsList = dataModel.widgetsModel.getWidgetsList(this.mApp.getContext());
        scheduleCallbackTask(new LauncherModel.CallbackTask() { // from class: com.android.launcher3.model.BaseModelUpdateTask.2
            @Override // com.android.launcher3.LauncherModel.CallbackTask
            public void execute(LauncherModel.Callbacks callbacks) {
                callbacks.bindAllWidgets(widgetsList);
            }
        });
    }

    public void deleteAndBindComponentsRemoved(final ItemInfoMatcher matcher) {
        getModelWriter().deleteItemsFromDatabase(matcher);
        scheduleCallbackTask(new LauncherModel.CallbackTask() { // from class: com.android.launcher3.model.BaseModelUpdateTask.3
            @Override // com.android.launcher3.LauncherModel.CallbackTask
            public void execute(LauncherModel.Callbacks callbacks) {
                callbacks.bindWorkspaceComponentsRemoved(matcher);
            }
        });
    }
}
