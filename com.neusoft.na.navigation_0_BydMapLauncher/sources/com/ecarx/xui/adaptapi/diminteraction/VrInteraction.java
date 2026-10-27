package com.ecarx.xui.adaptapi.diminteraction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.ecarx.xui.adaptapi.diminteraction.IVrInteraction;

/* JADX INFO: loaded from: classes2.dex */
public class VrInteraction implements IVrInteraction {
    private IVrInteraction.IVrInteractionCallback callBack;
    private Context context;
    private String reqAction = "com.neusoft.optimus.mmi.VRStatusRequire";
    private String cTag = "diminteraction";
    private VRRequireReceiver vrReceiver = new VRRequireReceiver();

    @Override // com.ecarx.xui.adaptapi.diminteraction.IVrInteraction
    public void notifyVoiceToTextCompleted(String str) {
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.IVrInteraction
    public void notifyVrSearchStates(int i) {
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.IVrInteraction
    public void notifyVrTTSText(String str) {
    }

    public VrInteraction(Context context) {
        this.context = context;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(this.reqAction);
        context.registerReceiver(this.vrReceiver, intentFilter);
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.IVrInteraction
    public void notifyVrStatusChanged(int i, int i2) {
        Log.d(this.cTag, "VrInteraction  notifyVrStatusChanged vrStatusBefore : " + i + " vrStatusAfter:" + i2);
        Intent intent = new Intent("com.neusoft.optimus.mmi.VRStatusChangeNotify");
        intent.putExtra("vrstatus", i2);
        this.context.sendBroadcast(intent);
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.IVrInteraction
    public void registerVrCallback(IVrInteraction.IVrInteractionCallback iVrInteractionCallback) {
        Log.d(this.cTag, "VrInteraction  registerVrCallback :" + iVrInteractionCallback);
        this.callBack = iVrInteractionCallback;
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.IVrInteraction
    public void unRegisterVrCallback(IVrInteraction.IVrInteractionCallback iVrInteractionCallback) {
        this.callBack = null;
    }

    private class VRRequireReceiver extends BroadcastReceiver {
        private VRRequireReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            Log.d(VrInteraction.this.cTag, "VrInteraction  VRRequireReceiver : " + intent.getAction());
            if (VrInteraction.this.callBack == null || !VrInteraction.this.reqAction.equals(intent.getAction())) {
                return;
            }
            VrInteraction.this.callBack.onVrInfoUpdateReqired();
        }
    }
}
