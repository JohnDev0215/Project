package com.amazon.identity.auth.device.api;

import android.accounts.Account;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.r9;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.va;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class MAPBroadcastReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f218a = MAPBroadcastReceiver.class.getName();
    public static final Set<String> b;
    public static final Set<String> c;

    static {
        HashSet hashSet = new HashSet();
        b = hashSet;
        hashSet.add("com.amazon.dcp.sso.action.account.added");
        hashSet.add("com.amazon.dcp.sso.action.secondary.account.added");
        HashSet hashSet2 = new HashSet();
        c = hashSet2;
        hashSet2.add("com.amazon.dcp.sso.action.account.removed");
        hashSet2.add("com.amazon.dcp.sso.action.secondary.account.removed");
    }

    @FireOsSdk
    public MAPBroadcastReceiver() {
    }

    @FireOsSdk
    public static Intent constructBackwardsCompatibleIntent(Context context, Intent intent) {
        if (intent == null) {
            return null;
        }
        Intent intent2 = new Intent(intent);
        String action = intent2.getAction();
        if (b.contains(action) || c.contains(action)) {
            if (intent2.getStringExtra(MAPAccountManager.KEY_EXTRA_DIRECTED_ID) == null) {
                if (c.contains(intent2.getAction())) {
                    return null;
                }
                intent2.putExtra(MAPAccountManager.KEY_EXTRA_DIRECTED_ID, r9.b(context, intent2.getStringExtra(AccountConstants.EXTRA_AMAZON_ACCOUNT_CHANGED_TYPE), intent2.getStringExtra(AccountConstants.EXTRA_AMAZON_ACCOUNT_CHANGED_NAME)));
            }
            return intent2;
        }
        if (!"com.amazon.dcp.sso.action.AmazonAccountPropertyService.property.changed".equals(action)) {
            if (CustomerAttributeStore.COR_PFM_CHANGED_INTENT_ACTION.equals(action) && intent2.getStringExtra(CustomerAttributeStore.KEY_DIRECTED_ID) == null) {
                intent2.putExtra(CustomerAttributeStore.KEY_DIRECTED_ID, new AmazonAccountManager(context).b());
            }
            return intent2;
        }
        if (intent2.getStringExtra(CustomerAttributeStore.KEY_DIRECTED_ID) == null) {
            String strA = r9.a(context, (Account) intent2.getParcelableExtra("account.property.changed"));
            if (strA == null) {
                strA = new MAPAccountManager(context).getAccount();
            }
            intent2.putExtra(CustomerAttributeStore.KEY_DIRECTED_ID, strA);
        }
        return intent2;
    }

    @Deprecated
    public abstract void backwardsCompatibleOnReceive(Context context, Intent intent);

    @Override // android.content.BroadcastReceiver
    @FireOsSdk
    public final void onReceive(final Context context, final Intent intent) {
        va.f637a.execute(new Runnable() { // from class: com.amazon.identity.auth.device.api.MAPBroadcastReceiver.1
            @Override // java.lang.Runnable
            public void run() {
                Intent intentConstructBackwardsCompatibleIntent = MAPBroadcastReceiver.constructBackwardsCompatibleIntent(context, intent);
                if (intentConstructBackwardsCompatibleIntent == null) {
                    ga.a(MAPBroadcastReceiver.f218a, "Ignoring broadcast with action %s", intent.getAction());
                } else {
                    MAPBroadcastReceiver.this.backwardsCompatibleOnReceive(context, intentConstructBackwardsCompatibleIntent);
                }
            }
        });
    }
}
