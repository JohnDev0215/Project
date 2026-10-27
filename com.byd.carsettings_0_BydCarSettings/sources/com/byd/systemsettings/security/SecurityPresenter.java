package com.byd.systemsettings.security;

import android.content.Context;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.os.UserHandle;
import android.provider.Settings;
import android.security.KeyChain;
import b.b.g.r.z;
import b.b.m.e.g;
import com.android.internal.widget.LockPatternUtils;
import com.byd.carsettings.R;
import java.security.KeyStore;

/* JADX INFO: loaded from: classes.dex */
public class SecurityPresenter extends g<SecurityPager> {
    private Context context;

    public class ResetKeyStoreAndKeyChain extends AsyncTask<Void, Void, Boolean> {
        private Context context;

        private ResetKeyStoreAndKeyChain(Context context) {
            this.context = context;
        }

        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... voidArr) {
            new LockPatternUtils(this.context).resetKeyStore(UserHandle.myUserId());
            try {
                KeyChain.KeyChainConnection keyChainConnectionBind = KeyChain.bind(this.context);
                try {
                    Boolean boolValueOf = Boolean.valueOf(keyChainConnectionBind.getService().reset());
                    keyChainConnectionBind.close();
                    return boolValueOf;
                } catch (RemoteException unused) {
                    keyChainConnectionBind.close();
                    return Boolean.FALSE;
                }
            } catch (InterruptedException unused2) {
                Thread.currentThread().interrupt();
                return Boolean.FALSE;
            }
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            if (bool.booleanValue()) {
                z.b(this.context, R.string.security_credentials_erased);
            } else {
                z.b(this.context, R.string.security_credentials_not_erased);
            }
        }
    }

    public SecurityPresenter(Context context) {
        this.context = context;
    }

    public void clearCredential() {
        new ResetKeyStoreAndKeyChain(this.context).execute(new Void[0]);
    }

    public int getDisplaySecuritySwitch() {
        return Settings.System.getInt(this.context.getContentResolver(), "display_security", 1);
    }

    public int getKeyStoreSize() {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            return keyStore.size();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public void setDisplaySecuritySwitch(boolean z) {
        Settings.System.putInt(this.context.getContentResolver(), "display_security", z ? 1 : 0);
    }
}
