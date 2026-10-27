package com.ecarx.xui.adaptapi.diminteraction;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.util.Log;
import com.ecarx.xui.adaptapi.diminteraction.IDimTransListener;
import com.ecarx.xui.adaptapi.diminteraction.IMediaInteraction;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MediaInteraction implements IMediaInteraction {
    public static IDimTransListener dimListener;
    private DimListenerConnection dimListenerConnection;
    private IMediaInteraction.IMediaInteractionCallback recordCallBack;
    private String cTag = "diminteraction";
    public int currentType = -1;
    public int currentAction = -1;
    private String path = "";
    private String frequency = "";
    private int radioMode = -1;
    private boolean isActionFlag = false;
    private boolean reRadioFlag = false;
    private String UUID = "";
    private int sourceChange = -2;

    @Override // com.ecarx.xui.adaptapi.diminteraction.IMediaInteraction
    public void updateCurrentProgress(long j) {
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.IMediaInteraction
    public void updateMediaMuteState(int i) {
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.IMediaInteraction
    public void updateMediaSourceTypeList(int[] iArr) {
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.IMediaInteraction
    public void updatePlaylist(int i, List<IMediaInteraction.IMedia> list) {
    }

    public MediaInteraction(Context context) {
        this.dimListenerConnection = new DimListenerConnection();
        Intent intent = new Intent();
        intent.setAction("com.ecarx.xui.adaptapi.diminteraction.MediaService");
        context.getApplicationContext().bindService(new Intent(getExplicitIntent(context, intent)), this.dimListenerConnection, 1);
        context.registerReceiver(new ReceiveBroadcast(), new IntentFilter("com.neusoft.optimus.mmi.AudioRequireAction"));
    }

    private static Intent getExplicitIntent(Context context, Intent intent) {
        List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (listQueryIntentServices == null || listQueryIntentServices.size() != 1) {
            return null;
        }
        ResolveInfo resolveInfo = listQueryIntentServices.get(0);
        ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
        Intent intent2 = new Intent(intent);
        intent2.setComponent(componentName);
        return intent2;
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.IMediaInteraction
    public void setMediaInteractionCallback(IMediaInteraction.IMediaInteractionCallback iMediaInteractionCallback) {
        Log.d(this.cTag, "MediaInteraction  setMediaInteractionCallback");
        if (iMediaInteractionCallback == null) {
            Log.d(this.cTag, "MediaInteraction  callback 是空");
        } else {
            this.recordCallBack = iMediaInteractionCallback;
        }
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.IMediaInteraction
    public void unsetMediaInteractionCallback(IMediaInteraction.IMediaInteractionCallback iMediaInteractionCallback) {
        Log.d(this.cTag, "MediaInteraction  unsetMediaInteractionCallback " + iMediaInteractionCallback);
        this.recordCallBack = iMediaInteractionCallback;
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.IMediaInteraction
    public void updateCurrentSourceType(int i) {
        Log.d(this.cTag, "MediaInteraction  updateCurrentSourceType sourceName:" + i);
        try {
            this.sourceChange = i;
            IDimTransListener iDimTransListener = dimListener;
            if (iDimTransListener != null) {
                iDimTransListener.onSourceChanged(i);
            }
        } catch (Exception e) {
            Log.d(this.cTag, "MediaInteraction  updateCurrentSourceType 出错 " + Log.getStackTraceString(e));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x01cd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.ecarx.xui.adaptapi.diminteraction.IMediaInteraction
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void updatePlaybackInfo(com.ecarx.xui.adaptapi.diminteraction.IMediaInteraction.IPlaybackInfo r18) {
        /*
            Method dump skipped, instruction units count: 661
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecarx.xui.adaptapi.diminteraction.MediaInteraction.updatePlaybackInfo(com.ecarx.xui.adaptapi.diminteraction.IMediaInteraction$IPlaybackInfo):void");
    }

    private class DimListenerConnection implements ServiceConnection {
        private DimListenerConnection() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MediaInteraction.dimListener = IDimTransListener.Stub.asInterface(iBinder);
            Log.d(MediaInteraction.this.cTag, "MediaInteraction onServiceConnected 连接" + MediaInteraction.dimListener);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(MediaInteraction.this.cTag, "MediaInteraction DimListenerConnection 连接断开");
            MediaInteraction.dimListener = null;
        }
    }

    private class ReceiveBroadcast extends BroadcastReceiver {
        private ReceiveBroadcast() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || MediaInteraction.this.recordCallBack == null) {
                Log.d(MediaInteraction.this.cTag, "MediaInteraction  onReceive : " + intent + " recordCallBack : " + MediaInteraction.this.recordCallBack);
                return;
            }
            int intExtra = intent.getIntExtra("broadcastAction", -1);
            Log.d(MediaInteraction.this.cTag, "MediaInteraction  ReceiveBroadcast : " + intExtra);
            if (intExtra != -1 && intExtra != 1000) {
                MediaInteraction.this.isActionFlag = true;
                MediaInteraction.this.recordCallBack.onUpdateMediaStatusRequest(intExtra);
            }
            if (intExtra == 1000) {
                MediaInteraction.this.isActionFlag = true;
            }
        }
    }
}
