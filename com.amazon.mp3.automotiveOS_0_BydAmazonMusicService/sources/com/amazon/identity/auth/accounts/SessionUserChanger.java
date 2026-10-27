package com.amazon.identity.auth.accounts;

import android.util.Log;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class SessionUserChanger {
    public static final String c = "com.amazon.identity.auth.accounts.SessionUserChanger";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AmazonAccountManager f152a;
    public final List<OnSessionUsersChangedListener> b = new ArrayList();

    /* JADX INFO: compiled from: DCP */
    public interface OnSessionUsersChangedListener {
        void onChanged(Collection<String> collection, Collection<String> collection2);
    }

    public SessionUserChanger(AmazonAccountManager amazonAccountManager) {
        this.f152a = amazonAccountManager;
    }

    public final Set<String> a(Collection<String> collection, Collection<String> collection2) {
        if (collection.size() > 0 || collection2.size() > 0) {
            Iterator<String> it = collection.iterator();
            while (it.hasNext()) {
                this.f152a.f144a.d(it.next(), AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT, "true");
            }
            Iterator<String> it2 = collection2.iterator();
            while (it2.hasNext()) {
                this.f152a.f144a.d(it2.next(), AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT, null);
            }
            Iterator<OnSessionUsersChangedListener> it3 = this.b.iterator();
            while (it3.hasNext()) {
                it3.next().onChanged(collection, collection2);
            }
        } else {
            Log.i(ga.a(c), "No New Session Users to add");
        }
        return this.f152a.d();
    }
}
