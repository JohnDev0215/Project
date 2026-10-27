package com.core.system.service;

import android.content.Context;
import android.os.IBinder;
import android.os.Process;
import com.core.lib.common.ICoreSystemServer;
import com.core.lib.common.ICoreSystemServerListener;
import com.core.system.service.utils.AutoBootOnUserHelper;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class CoreSystemBinderServer extends ICoreSystemServer.Stub {
    private static volatile CoreSystemBinderServer mInstance = new CoreSystemBinderServer();
    private AutoBootOnUserHelper mAutoBootOnUserHelper;
    private Context mContext;
    private CoreSystemServerImpl mCoreSystemServerImpl;

    private CoreSystemBinderServer() {
    }

    public static CoreSystemBinderServer getInstance() {
        return mInstance;
    }

    public void init(Context context) {
        this.mContext = context.getApplicationContext();
        if (this.mAutoBootOnUserHelper == null) {
            this.mAutoBootOnUserHelper = new AutoBootOnUserHelper(context);
        }
        if (this.mAutoBootOnUserHelper.isCurrentUserSystemUser()) {
            this.mCoreSystemServerImpl = new CoreSystemServerImpl(this.mContext);
        } else if (this.mAutoBootOnUserHelper.isCurrentUserProfiledUser(context)) {
            this.mCoreSystemServerImpl = new CoreSystemProfileUserImpl(this.mContext);
        } else {
            this.mCoreSystemServerImpl = new CoreSystemCommonUserImpl(this.mContext);
        }
    }

    public void dumpMsg(FileDescriptor fd, PrintWriter writer, String[] args) {
        dumpCoreBinder(fd, writer, args);
        this.mCoreSystemServerImpl.dump(fd, writer, args);
    }

    public boolean addService(String name, IBinder service) {
        return this.mCoreSystemServerImpl.addServiceBinder(name, service);
    }

    public IBinder getService(String name) {
        return this.mCoreSystemServerImpl.getServiceBinder(name);
    }

    public boolean addServiceListener(ICoreSystemServerListener listener) {
        return this.mCoreSystemServerImpl.addServiceListener(listener);
    }

    public boolean removeServiceListener(ICoreSystemServerListener listener) {
        return this.mCoreSystemServerImpl.removeServiceListener(listener);
    }

    public boolean notifyServiceReady(String name, IBinder binder) {
        return this.mCoreSystemServerImpl.notifyServiceReady(name, binder);
    }

    public boolean isServiceSupport(String name) {
        return this.mCoreSystemServerImpl.isServiceSupport(name);
    }

    public boolean isServiceReady(String name) {
        return this.mCoreSystemServerImpl.isServiceReady(name);
    }

    private void dumpCoreBinder(FileDescriptor fd, PrintWriter writer, String[] args) {
        writer.println("Dump CoreSystemServer, currentUid:" + Process.myUid() + ", Pid:" + Process.myPid());
        StringBuilder sb = new StringBuilder();
        sb.append("Dump CoreSystemServer, CoreSystemServerImpl:");
        sb.append(this.mCoreSystemServerImpl.getClass().getSimpleName());
        writer.println(sb.toString());
    }
}
