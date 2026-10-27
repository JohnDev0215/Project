package com.android.bluetooth.pbap;

import android.util.Log;
import javax.obex.Authenticator;
import javax.obex.PasswordAuthentication;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothPbapAuthenticator implements Authenticator {
    private static final String TAG = "PbapAuthenticator";
    private PbapStateMachine mPbapStateMachine;
    private boolean mChallenged = false;
    private boolean mAuthCancelled = false;
    private String mSessionKey = null;

    BluetoothPbapAuthenticator(PbapStateMachine stateMachine) {
        this.mPbapStateMachine = stateMachine;
    }

    final synchronized void setChallenged(boolean bool) {
        this.mChallenged = bool;
        notify();
    }

    final synchronized void setCancelled(boolean bool) {
        this.mAuthCancelled = bool;
        notify();
    }

    final synchronized void setSessionKey(String string) {
        this.mSessionKey = string;
    }

    private void waitUserConfirmation() {
        this.mPbapStateMachine.sendMessage(5);
        this.mPbapStateMachine.sendMessageDelayed(6, 30000L);
        synchronized (this) {
            while (!this.mChallenged && !this.mAuthCancelled) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Log.e(TAG, "Interrupted while waiting on isChallenged or AuthCancelled");
                }
            }
        }
    }

    public PasswordAuthentication onAuthenticationChallenge(String description, boolean isUserIdRequired, boolean isFullAccess) {
        waitUserConfirmation();
        if (this.mSessionKey.trim().length() != 0) {
            return new PasswordAuthentication((byte[]) null, this.mSessionKey.getBytes());
        }
        return null;
    }

    public byte[] onAuthenticationResponse(byte[] userName) {
        return null;
    }
}
