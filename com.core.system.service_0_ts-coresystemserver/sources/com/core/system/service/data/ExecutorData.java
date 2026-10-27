package com.core.system.service.data;

import android.content.Context;
import com.core.system.foundationinterface.ICoreExecutor;
import com.core.system.service.CoreSystemServerImpl;
import com.core.system.service.utils.OneShotEventUtil;
import com.core.system.service.utils.UserUnlockUtil;
import com.ts.car.sdk.core.Logger;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ExecutorData {
    private static final String JSON_KEY_DELAY = "mDelay";
    private static final String JSON_KEY_DIRECT_BOOT = "mDirectBoot";
    private static final String JSON_KEY_EXECUTOR_CLASS = "mExcutorClassName";
    private static final String JSON_KEY_EXECUTOR_NAME = "mExcutorName";
    private static final String JSON_KEY_ONCE_BOOT = "mOnceBoot";
    private static final String JSON_KEY_ONE_SHOT = "mOneShot";
    private String className;
    private CoreSystemServerImpl coreSystemServer;
    private int delayMillis;
    private boolean isDirectBoot;
    private AtomicBoolean isExecuted = new AtomicBoolean(false);
    private String name;
    private boolean onceBoot;
    private boolean oneShot;

    public boolean isDirectBoot() {
        return this.isDirectBoot;
    }

    public void setDirectBoot(boolean directBoot) {
        this.isDirectBoot = directBoot;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getDelayMillis() {
        return this.delayMillis;
    }

    public void setDelayMillis(int delayMillis) {
        this.delayMillis = delayMillis;
    }

    public boolean isOneShot() {
        return this.oneShot;
    }

    public void setOneShot(boolean oneShot) {
        this.oneShot = oneShot;
    }

    public boolean isOnceBoot() {
        return this.onceBoot;
    }

    public void setOnceBoot(boolean onceBoot) {
        this.onceBoot = onceBoot;
    }

    public static ExecutorData createFromJson(CoreSystemServerImpl coreSystemServer, JSONObject json) {
        try {
            ExecutorData data = new ExecutorData();
            data.setName(json.getString(JSON_KEY_EXECUTOR_NAME));
            data.setClassName(json.getString(JSON_KEY_EXECUTOR_CLASS));
            data.setDelayMillis(json.optInt("mDelay", 0));
            data.setOneShot(json.optBoolean("mOneShot", false));
            data.setOnceBoot(json.optBoolean(JSON_KEY_ONCE_BOOT, true));
            data.setDirectBoot(json.optBoolean("mDirectBoot", false));
            data.setCoreSystemServer(coreSystemServer);
            return data;
        } catch (JSONException ex) {
            Logger.warning("ExecutorData parse error! json:" + json, ex);
            return null;
        }
    }

    public void setCoreSystemServer(CoreSystemServerImpl coreSystemServer) {
        this.coreSystemServer = coreSystemServer;
    }

    public String toString() {
        return "ExecutorData info, name:" + this.name + ", delay:" + this.delayMillis + ", className" + this.className + ", oneShot:" + this.oneShot + ", onceBoot:" + this.onceBoot;
    }

    public boolean hasExecuted() {
        return this.isExecuted.get();
    }

    public boolean executeIfNeeded(Context context) {
        if (!hasExecuted()) {
            return execute(context);
        }
        return true;
    }

    private boolean execute(final Context context) {
        if (isOneShot() && OneShotEventUtil.getInstance().isShoted(getName())) {
            Logger.debug("This executor is oneShot and already shoted, name:" + getName());
            this.isExecuted.set(true);
            return true;
        }
        if (!UserUnlockUtil.getInstance().isUserUnlock() && !isDirectBoot()) {
            Logger.debug("do not execute this Executor, user unlock or not directBoot, directBoot:" + isDirectBoot());
            return false;
        }
        if (this.isExecuted.getAndSet(true)) {
            Logger.warning("already executed, donnot execute again! name:" + this.name);
            return true;
        }
        this.coreSystemServer.getThreadHandler().postDelayed(new Runnable() { // from class: com.core.system.service.data.-$$Lambda$ExecutorData$7WEO-ZfLKe9mpIorKs7m9Zi-P1A
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$execute$0$ExecutorData(context);
            }
        }, this.delayMillis);
        return true;
    }

    public /* synthetic */ void lambda$execute$0$ExecutorData(Context context) {
        try {
            ICoreExecutor coreExecutor = (ICoreExecutor) Class.forName(this.className).newInstance();
            coreExecutor.execute(context);
        } catch (Throwable ex) {
            try {
                Logger.warning("Execurot.execute error, name:" + this.name + ", className:" + this.className, ex);
                if (!isOneShot()) {
                }
            } finally {
                if (isOneShot()) {
                    OneShotEventUtil.getInstance().markShoted(getName());
                }
            }
        }
    }
}
