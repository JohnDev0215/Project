package com.amazon.identity.auth.device;

import android.accounts.AccountManager;
import android.content.Context;
import android.content.ContextWrapper;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.identity.auth.device.storage.LambortishClock;
import com.amazon.identity.auth.device.storage.LocalDataStorage;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class t5 extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Object> f564a;

    public t5(Context context) {
        super(context);
        this.f564a = Collections.synchronizedMap(new HashMap());
    }

    public static t5 a(Context context) {
        return context instanceof t5 ? (t5) context : new t5(context);
    }

    public synchronized z3 b() {
        return a4.a(new b4(this));
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getFilesDir() {
        return super.getFilesDir();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        Object systemService = getBaseContext().getSystemService(str);
        if (systemService != null) {
            return systemService;
        }
        Object e6Var = this.f564a.get(str);
        if (e6Var == null) {
            Object obj = null;
            if ("dcp_system".equals(str)) {
                e6Var = new w5();
            } else {
                if ("dcp_device_info".equals(str)) {
                    synchronized (x4.class) {
                        if (x4.f656a == null) {
                            x4.f656a = t3.b(this).f562a;
                        }
                        obj = x4.f656a;
                    }
                } else if ("dcp_account_manager".equals(str)) {
                    e6Var = new p9(this, AccountManager.get(this));
                } else if ("dcp_single_threaded_executor".equals(str)) {
                    Executor executor = va.f637a;
                    e6Var = Executors.newSingleThreadExecutor(new ka("MAP-ServiceWrappingContextThread"));
                } else if ("dcp_amazon_account_man".equals(str)) {
                    e6Var = new AmazonAccountManager(this);
                } else if ("dcp_authenticated_url_connection_factory".equals(str)) {
                    e6Var = new p4();
                } else if ("dcp_token_cache_holder".equals(str)) {
                    e6Var = n8.a(this);
                } else if ("dcp_data_storage_factory".equals(str)) {
                    synchronized (w7.class) {
                        if (w7.f == null) {
                            w7.f = new w7(this);
                        }
                        obj = w7.f;
                    }
                } else if ("sso_map_account_manager_communicator".equals(str)) {
                    e6Var = new CentralAccountManagerCommunication(this, new z4(this, "com.amazon.dcp.sso.ErrorCode", "com.amazon.dcp.sso.ErrorMessage", Integer.valueOf(MAPAccountManager.RegistrationError.REGISTER_FAILED.value())));
                } else if ("dcp_token_mangement".equals(str)) {
                    e6Var = new TokenManagement(this);
                } else if ("sso_local_datastorage".equals(str)) {
                    synchronized (LocalDataStorage.class) {
                        if (LocalDataStorage.g == null) {
                            Context applicationContext = getApplicationContext();
                            LocalDataStorage.g = new LocalDataStorage(applicationContext, new LocalDataStorage.a(applicationContext, "map_data_storage", 1), LambortishClock.a(applicationContext), null);
                        }
                        obj = LocalDataStorage.g;
                    }
                } else if ("sso_alarm_maanger".equals(str)) {
                    e6Var = new h4(this);
                } else if ("sso_platform".equals(str)) {
                    e6Var = new i5(this);
                } else if ("sso_webservice_caller_creator".equals(str)) {
                    e6Var = new l4(this);
                } else if ("dcp_wifi".equals(str)) {
                    e6Var = new d6(this);
                } else if ("sso_telephony_service".equals(str)) {
                    e6Var = new x5(this);
                } else if ("sso_window_manager".equals(str)) {
                    e6Var = new e6(this);
                }
                e6Var = obj;
            }
            this.f564a.put(str, e6Var);
        }
        return e6Var;
    }

    public u7 a() {
        return ((v7) getSystemService("dcp_data_storage_factory")).a();
    }
}
