package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class t2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, List<Callback>> f560a = new HashMap();

    /* JADX INFO: compiled from: DCP */
    public class a implements Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f561a;

        public a(String str) {
            this.f561a = str;
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onError(Bundle bundle) {
            List<Callback> listA = t2.this.a(this.f561a);
            Iterator<Callback> it = listA.iterator();
            while (it.hasNext()) {
                it.next().onError(bundle);
            }
            listA.clear();
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onSuccess(Bundle bundle) {
            List<Callback> listA = t2.this.a(this.f561a);
            Iterator<Callback> it = listA.iterator();
            while (it.hasNext()) {
                it.next().onSuccess(bundle);
            }
            listA.clear();
        }
    }

    public synchronized Callback a(String str, Callback callback) {
        List<Callback> listB = b(str);
        listB.add(callback);
        if (listB.size() > 1) {
            return null;
        }
        return new a(str);
    }

    public final synchronized List<Callback> b(String str) {
        List<Callback> linkedList;
        linkedList = this.f560a.get(str);
        if (linkedList == null) {
            synchronized (this) {
                linkedList = new LinkedList<>();
                this.f560a.put(str, linkedList);
            }
        }
        return linkedList;
    }

    public final synchronized List<Callback> a(String str) {
        List<Callback> listB;
        listB = b(str);
        if (listB.size() > 0) {
            synchronized (this) {
                this.f560a.put(str, new LinkedList());
            }
        }
        return listB;
    }
}
