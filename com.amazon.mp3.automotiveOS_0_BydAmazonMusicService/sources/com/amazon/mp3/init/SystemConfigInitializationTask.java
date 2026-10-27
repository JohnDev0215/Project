package com.amazon.mp3.init;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import com.amazon.mp3.config.SystemConfigRetriever;
import com.amazon.mp3.config.SystemConfigTask;
import com.amazon.mp3.util.Log;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public class SystemConfigInitializationTask implements InitializationTask {
    private static final String TAG = "SystemConfigInitializationTask";
    private final Context mContext;

    public SystemConfigInitializationTask(final Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.mp3.init.InitializationTask
    public void initialize() {
        try {
            SystemConfigRetriever.init(this.mContext);
        } catch (SQLiteException e) {
            Log.error(TAG, "SystemConfigInitializationTask init failed, stacktrace: ", e);
        }
    }

    @Override // com.amazon.mp3.init.InitializationTask
    public Action1 getOnNextAction() {
        return new Action1() { // from class: com.amazon.mp3.init.SystemConfigInitializationTask$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$getOnNextAction$0(obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getOnNextAction$0(Object obj) {
        new SystemConfigTask(this.mContext).handleTask();
    }

    @Override // com.amazon.mp3.init.InitializationTask
    public String getName() {
        return TAG;
    }
}
