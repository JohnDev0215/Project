package io.branch.referral;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import io.branch.referral.Branch;
import io.branch.referral.ServerRequest;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
class ServerRequestQueue {
    private static ServerRequestQueue SharedInstance;
    private static final Object reqQueueLockObject = new Object();
    private SharedPreferences.Editor editor;
    private final List<ServerRequest> queue;
    private SharedPreferences sharedPref;

    public static ServerRequestQueue getInstance(Context context) {
        if (SharedInstance == null) {
            synchronized (ServerRequestQueue.class) {
                if (SharedInstance == null) {
                    SharedInstance = new ServerRequestQueue(context);
                }
            }
        }
        return SharedInstance;
    }

    @SuppressLint({"CommitPrefEdits"})
    private ServerRequestQueue(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("BNC_Server_Request_Queue", 0);
        this.sharedPref = sharedPreferences;
        this.editor = sharedPreferences.edit();
        this.queue = retrieve(context);
    }

    private void persist() {
        new Thread(new Runnable() { // from class: io.branch.referral.ServerRequestQueue.1
            @Override // java.lang.Runnable
            public void run() {
                JSONObject json;
                JSONArray jSONArray = new JSONArray();
                synchronized (ServerRequestQueue.reqQueueLockObject) {
                    for (ServerRequest serverRequest : ServerRequestQueue.this.queue) {
                        if (serverRequest.isPersistable() && (json = serverRequest.toJSON()) != null) {
                            jSONArray.put(json);
                        }
                    }
                }
                try {
                    ServerRequestQueue.this.editor.putString("BNCServerRequestQueue", jSONArray.toString()).commit();
                } catch (Exception e) {
                    String message = e.getMessage();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to persit queue");
                    if (message == null) {
                        message = "";
                    }
                    sb.append(message);
                    PrefHelper.Debug(sb.toString());
                }
            }
        }).start();
    }

    private List<ServerRequest> retrieve(Context context) {
        String string = this.sharedPref.getString("BNCServerRequestQueue", null);
        List<ServerRequest> listSynchronizedList = Collections.synchronizedList(new LinkedList());
        synchronized (reqQueueLockObject) {
            if (string != null) {
                try {
                    JSONArray jSONArray = new JSONArray(string);
                    int iMin = Math.min(jSONArray.length(), 25);
                    for (int i = 0; i < iMin; i++) {
                        ServerRequest serverRequestFromJSON = ServerRequest.fromJSON(jSONArray.getJSONObject(i), context);
                        if (serverRequestFromJSON != null) {
                            listSynchronizedList.add(serverRequestFromJSON);
                        }
                    }
                } catch (JSONException unused) {
                }
            }
        }
        return listSynchronizedList;
    }

    public int getSize() {
        int size;
        synchronized (reqQueueLockObject) {
            size = this.queue.size();
        }
        return size;
    }

    void enqueue(ServerRequest serverRequest) {
        synchronized (reqQueueLockObject) {
            if (serverRequest != null) {
                this.queue.add(serverRequest);
                if (getSize() >= 25) {
                    this.queue.remove(1);
                }
                persist();
            }
        }
    }

    ServerRequest dequeue() {
        ServerRequest serverRequestRemove;
        synchronized (reqQueueLockObject) {
            ServerRequest serverRequest = null;
            try {
                serverRequestRemove = this.queue.remove(0);
            } catch (IndexOutOfBoundsException | NoSuchElementException unused) {
            }
            try {
                persist();
            } catch (IndexOutOfBoundsException | NoSuchElementException unused2) {
                serverRequest = serverRequestRemove;
                serverRequestRemove = serverRequest;
            }
        }
        return serverRequestRemove;
    }

    ServerRequest peek() {
        ServerRequest serverRequest;
        synchronized (reqQueueLockObject) {
            try {
                serverRequest = this.queue.get(0);
            } catch (IndexOutOfBoundsException | NoSuchElementException unused) {
                serverRequest = null;
            }
        }
        return serverRequest;
    }

    ServerRequest peekAt(int i) {
        ServerRequest serverRequest;
        synchronized (reqQueueLockObject) {
            try {
                serverRequest = this.queue.get(i);
            } catch (IndexOutOfBoundsException | NoSuchElementException unused) {
                serverRequest = null;
            }
        }
        return serverRequest;
    }

    void insert(ServerRequest serverRequest, int i) {
        synchronized (reqQueueLockObject) {
            try {
                if (this.queue.size() < i) {
                    i = this.queue.size();
                }
                this.queue.add(i, serverRequest);
                persist();
            } catch (IndexOutOfBoundsException unused) {
            }
        }
    }

    public boolean remove(ServerRequest serverRequest) {
        boolean zRemove;
        synchronized (reqQueueLockObject) {
            zRemove = false;
            try {
                zRemove = this.queue.remove(serverRequest);
                persist();
            } catch (UnsupportedOperationException unused) {
            }
        }
        return zRemove;
    }

    void clear() {
        synchronized (reqQueueLockObject) {
            try {
                this.queue.clear();
                persist();
            } catch (UnsupportedOperationException unused) {
            }
        }
    }

    boolean containsClose() {
        synchronized (reqQueueLockObject) {
            for (ServerRequest serverRequest : this.queue) {
                if (serverRequest != null && serverRequest.getRequestPath().equals(Defines$RequestPath.RegisterClose.getPath())) {
                    return true;
                }
            }
            return false;
        }
    }

    boolean containsInstallOrOpen() {
        synchronized (reqQueueLockObject) {
            for (ServerRequest serverRequest : this.queue) {
                if (serverRequest != null && ((serverRequest instanceof ServerRequestRegisterInstall) || (serverRequest instanceof ServerRequestRegisterOpen))) {
                    return true;
                }
            }
            return false;
        }
    }

    void moveInstallOrOpenToFront(ServerRequest serverRequest, int i, Branch.BranchReferralInitListener branchReferralInitListener) {
        synchronized (reqQueueLockObject) {
            Iterator<ServerRequest> it = this.queue.iterator();
            while (it.hasNext()) {
                ServerRequest next = it.next();
                if (next != null && ((next instanceof ServerRequestRegisterInstall) || (next instanceof ServerRequestRegisterOpen))) {
                    it.remove();
                    break;
                }
            }
        }
        insert(serverRequest, i == 0 ? 0 : 1);
    }

    void setInstallOrOpenCallback(Branch.BranchReferralInitListener branchReferralInitListener) {
        synchronized (reqQueueLockObject) {
            for (ServerRequest serverRequest : this.queue) {
                if (serverRequest != null) {
                    if (serverRequest instanceof ServerRequestRegisterInstall) {
                        ((ServerRequestRegisterInstall) serverRequest).setInitFinishedCallback(branchReferralInitListener);
                    } else if (serverRequest instanceof ServerRequestRegisterOpen) {
                        ((ServerRequestRegisterOpen) serverRequest).setInitFinishedCallback(branchReferralInitListener);
                    }
                }
            }
        }
    }

    void unlockProcessWait(ServerRequest.PROCESS_WAIT_LOCK process_wait_lock) {
        synchronized (reqQueueLockObject) {
            for (ServerRequest serverRequest : this.queue) {
                if (serverRequest != null) {
                    serverRequest.removeProcessWaitLock(process_wait_lock);
                }
            }
        }
    }

    void setStrongMatchWaitLock() {
        synchronized (reqQueueLockObject) {
            for (ServerRequest serverRequest : this.queue) {
                if (serverRequest != null && (serverRequest instanceof ServerRequestInitSession)) {
                    serverRequest.addProcessWaitLock(ServerRequest.PROCESS_WAIT_LOCK.STRONG_MATCH_PENDING_WAIT_LOCK);
                }
            }
        }
    }
}
