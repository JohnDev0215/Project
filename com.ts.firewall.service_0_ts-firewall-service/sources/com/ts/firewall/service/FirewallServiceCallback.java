package com.ts.firewall.service;

import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.ts.firewall.service.data.ResultCode;
import com.ts.firewall.service.data.ResultType;
import com.ts.firewall.service.firewallhaladapter.FirewallHalListener;
import com.ts.firewall.service.utils.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class FirewallServiceCallback implements FirewallHalListener {
    private RemoteCallbackList<IFirewallServiceListener> mFirewallServiceListenerList;
    private RemoteCallbackList<IFirewallServiceResponse> mFirewallServiceResponseList;
    private Object mLock;

    public FirewallServiceCallback(RemoteCallbackList<IFirewallServiceListener> listenerList, RemoteCallbackList<IFirewallServiceResponse> responseList, Object lock) {
        this.mFirewallServiceListenerList = listenerList;
        this.mFirewallServiceResponseList = responseList;
        this.mLock = lock;
    }

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalListener
    public void onRequestFinished(int type, int code) {
        synchronized (this.mLock) {
            try {
                try {
                    int len = this.mFirewallServiceResponseList.beginBroadcast();
                    LogUtil.debug("onRequestFinished serial:" + type + " code:" + code + " listener size:" + len);
                    ResultType tempType = ResultType.valueOf(type);
                    ResultCode tempCode = ResultCode.valueOf(code);
                    for (int i = 0; i < len; i++) {
                        IFirewallServiceResponse ifirewallServiceResponse = (IFirewallServiceResponse) this.mFirewallServiceResponseList.getBroadcastItem(i);
                        if (ifirewallServiceResponse != null) {
                            ifirewallServiceResponse.onRequestFinished(tempType, tempCode);
                        }
                    }
                    try {
                        this.mFirewallServiceResponseList.finishBroadcast();
                    } catch (IllegalStateException e) {
                        ex = e;
                        ex.printStackTrace();
                    }
                } catch (RemoteException | IllegalStateException remoteException) {
                    LogUtil.error("onRequestFinished error:" + remoteException);
                    try {
                        this.mFirewallServiceResponseList.finishBroadcast();
                    } catch (IllegalStateException e2) {
                        ex = e2;
                        ex.printStackTrace();
                    }
                }
            } finally {
            }
        }
    }

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalListener
    public void onGetRuleResponse(int serial, int segmentNum, int index, String rule) {
        synchronized (this.mLock) {
            try {
                try {
                    int len = this.mFirewallServiceResponseList.beginBroadcast();
                    LogUtil.debug("onGetRuleResponse serial:" + serial + " segmentNum:" + segmentNum + " index:" + index + " rule:" + rule + " listener size:" + len);
                    for (int i = 0; i < len; i++) {
                        IFirewallServiceResponse ifirewallServiceResponse = (IFirewallServiceResponse) this.mFirewallServiceResponseList.getBroadcastItem(i);
                        if (ifirewallServiceResponse != null) {
                            ifirewallServiceResponse.onGetRuleResponse(serial, segmentNum, index, rule);
                        }
                    }
                    try {
                        this.mFirewallServiceResponseList.finishBroadcast();
                    } catch (IllegalStateException e) {
                        ex = e;
                        ex.printStackTrace();
                    }
                } catch (RemoteException | IllegalStateException remoteException) {
                    LogUtil.error("onGetRuleResponse error:" + remoteException);
                    try {
                        this.mFirewallServiceResponseList.finishBroadcast();
                    } catch (IllegalStateException e2) {
                        ex = e2;
                        ex.printStackTrace();
                    }
                }
            } finally {
            }
        }
    }

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalListener
    public void onGetLogResponse(int serial, int segmentNum, int index, String log) {
        synchronized (this.mLock) {
            try {
                try {
                    int len = this.mFirewallServiceResponseList.beginBroadcast();
                    LogUtil.debug("onGetLogResponse serial:" + serial + " segmentNum:" + segmentNum + " index:" + index + " log:" + log + " listener size:" + len);
                    for (int i = 0; i < len; i++) {
                        IFirewallServiceResponse ifirewallServiceResponse = (IFirewallServiceResponse) this.mFirewallServiceResponseList.getBroadcastItem(i);
                        if (ifirewallServiceResponse != null) {
                            ifirewallServiceResponse.onGetLogResponse(serial, segmentNum, index, log);
                        }
                    }
                    try {
                        this.mFirewallServiceResponseList.finishBroadcast();
                    } catch (IllegalStateException e) {
                        ex = e;
                        ex.printStackTrace();
                    }
                } catch (RemoteException | IllegalStateException remoteException) {
                    LogUtil.error("onGetLogResponse error:" + remoteException);
                    try {
                        this.mFirewallServiceResponseList.finishBroadcast();
                    } catch (IllegalStateException e2) {
                        ex = e2;
                        ex.printStackTrace();
                    }
                }
            } finally {
            }
        }
    }

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalListener
    public void onFirewallStatusChange(boolean state) {
        synchronized (this.mLock) {
            try {
                try {
                    int len = this.mFirewallServiceListenerList.beginBroadcast();
                    LogUtil.debug("onFirewallStatusChange state:" + state + " listener size:" + len);
                    for (int i = 0; i < len; i++) {
                        IFirewallServiceListener ifirewallServiceListener = (IFirewallServiceListener) this.mFirewallServiceListenerList.getBroadcastItem(i);
                        if (ifirewallServiceListener != null) {
                            ifirewallServiceListener.onFirewallStatusChange(state);
                        }
                    }
                    try {
                        this.mFirewallServiceListenerList.finishBroadcast();
                    } catch (IllegalStateException e) {
                        ex = e;
                        ex.printStackTrace();
                    }
                } catch (RemoteException | IllegalStateException remoteException) {
                    LogUtil.error("onFirewallStatusChange error:" + remoteException);
                    try {
                        this.mFirewallServiceListenerList.finishBroadcast();
                    } catch (IllegalStateException e2) {
                        ex = e2;
                        ex.printStackTrace();
                    }
                }
            } finally {
            }
        }
    }

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalListener
    public void onWhiteListStatusChange(boolean enabled) {
        synchronized (this.mLock) {
            try {
                try {
                    int len = this.mFirewallServiceListenerList.beginBroadcast();
                    LogUtil.debug("onWhiteListStatusChange enabled:" + enabled + " listener size:" + len);
                    for (int i = 0; i < len; i++) {
                        IFirewallServiceListener ifirewallServiceListener = (IFirewallServiceListener) this.mFirewallServiceListenerList.getBroadcastItem(i);
                        if (ifirewallServiceListener != null) {
                            ifirewallServiceListener.onFirewallStatusChange(enabled);
                        }
                    }
                    try {
                        this.mFirewallServiceListenerList.finishBroadcast();
                    } catch (IllegalStateException e) {
                        ex = e;
                        ex.printStackTrace();
                    }
                } catch (RemoteException | IllegalStateException remoteException) {
                    LogUtil.error("onWhiteListStatusChange error:" + remoteException);
                    try {
                        this.mFirewallServiceListenerList.finishBroadcast();
                    } catch (IllegalStateException e2) {
                        ex = e2;
                        ex.printStackTrace();
                    }
                }
            } finally {
            }
        }
    }
}
