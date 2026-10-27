package android.telecom.Logging;

import android.os.Parcel;
import android.os.Parcelable;
import android.telecom.Log;
import android.text.TextUtils;
import android.util.Slog;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class Session {
    public static final String CONTINUE_SUBSESSION = "CONTINUE_SUBSESSION";
    public static final String CREATE_SUBSESSION = "CREATE_SUBSESSION";
    public static final String END_SESSION = "END_SESSION";
    public static final String END_SUBSESSION = "END_SUBSESSION";
    public static final String EXTERNAL_INDICATOR = "E-";
    public static final String LOG_TAG = "Session";
    private static final int SESSION_RECURSION_LIMIT = 25;
    public static final String SESSION_SEPARATION_CHAR_CHILD = "_";
    public static final String START_EXTERNAL_SESSION = "START_EXTERNAL_SESSION";
    public static final String START_SESSION = "START_SESSION";
    public static final String SUBSESSION_SEPARATION_CHAR = "->";
    public static final String TRUNCATE_STRING = "...";
    public static final int UNDEFINED = -1;
    private ArrayList<Session> mChildSessions;
    private long mExecutionStartTimeMs;
    private String mFullMethodPathCache;
    private boolean mIsStartedFromActiveSession;
    private String mOwnerInfo;
    private Session mParentSession;
    private String mSessionId;
    private String mShortMethodName;
    private long mExecutionEndTimeMs = -1;
    private boolean mIsCompleted = false;
    private boolean mIsExternal = false;
    private int mChildCounter = 0;

    public static class Info implements Parcelable {
        public static final Parcelable.Creator<Info> CREATOR = new Parcelable.Creator<Info>() { // from class: android.telecom.Logging.Session.Info.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Info createFromParcel(Parcel parcel) {
                return new Info(parcel.readString(), parcel.readString(), parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Info[] newArray(int i) {
                return new Info[i];
            }
        };
        public final String methodPath;
        public final String ownerInfo;
        public final String sessionId;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        private Info(String str, String str2, String str3) {
            this.sessionId = str;
            this.methodPath = str2;
            this.ownerInfo = str3;
        }

        public static Info getInfo(Session session) {
            return new Info(session.getFullSessionId(), session.getFullMethodPath(!Log.DEBUG && session.isSessionExternal()), session.getOwnerInfo());
        }

        public static Info getExternalInfo(Session session, String str) {
            if (str != null && session.getOwnerInfo() != null) {
                str = session.getOwnerInfo() + "/" + str;
            } else if (str == null) {
                str = session.getOwnerInfo();
            }
            return new Info(session.getFullSessionId(), session.getFullMethodPath(!Log.DEBUG && session.isSessionExternal()), str);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.sessionId);
            parcel.writeString(this.methodPath);
            parcel.writeString(this.ownerInfo);
        }
    }

    public Session(String str, String str2, long j, boolean z, String str3) {
        this.mIsStartedFromActiveSession = false;
        setSessionId(str);
        setShortMethodName(str2);
        this.mExecutionStartTimeMs = j;
        this.mParentSession = null;
        this.mChildSessions = new ArrayList<>(5);
        this.mIsStartedFromActiveSession = z;
        this.mOwnerInfo = str3;
    }

    public void setSessionId(String str) {
        if (str == null) {
            this.mSessionId = "?";
        }
        this.mSessionId = str;
    }

    public String getShortMethodName() {
        return this.mShortMethodName;
    }

    public void setShortMethodName(String str) {
        if (str == null) {
            str = "";
        }
        this.mShortMethodName = str;
    }

    public void setIsExternal(boolean z) {
        this.mIsExternal = z;
    }

    public boolean isExternal() {
        return this.mIsExternal;
    }

    public void setParentSession(Session session) {
        this.mParentSession = session;
    }

    public void addChild(Session session) {
        if (session != null) {
            this.mChildSessions.add(session);
        }
    }

    public void removeChild(Session session) {
        if (session != null) {
            this.mChildSessions.remove(session);
        }
    }

    public long getExecutionStartTimeMilliseconds() {
        return this.mExecutionStartTimeMs;
    }

    public void setExecutionStartTimeMs(long j) {
        this.mExecutionStartTimeMs = j;
    }

    public Session getParentSession() {
        return this.mParentSession;
    }

    public ArrayList<Session> getChildSessions() {
        return this.mChildSessions;
    }

    public boolean isSessionCompleted() {
        return this.mIsCompleted;
    }

    public boolean isStartedFromActiveSession() {
        return this.mIsStartedFromActiveSession;
    }

    public Info getInfo() {
        return Info.getInfo(this);
    }

    public Info getExternalInfo(String str) {
        return Info.getExternalInfo(this, str);
    }

    public String getOwnerInfo() {
        return this.mOwnerInfo;
    }

    public String getSessionId() {
        return this.mSessionId;
    }

    public void markSessionCompleted(long j) {
        this.mExecutionEndTimeMs = j;
        this.mIsCompleted = true;
    }

    public long getLocalExecutionTime() {
        long j = this.mExecutionEndTimeMs;
        if (j == -1) {
            return -1L;
        }
        return j - this.mExecutionStartTimeMs;
    }

    public synchronized String getNextChildId() {
        int i;
        i = this.mChildCounter;
        this.mChildCounter = i + 1;
        return String.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getFullSessionId() {
        return getFullSessionId(0);
    }

    private String getFullSessionId(int i) {
        if (i >= 25) {
            Slog.w(LOG_TAG, "getFullSessionId: Hit recursion limit!");
            return TRUNCATE_STRING + this.mSessionId;
        }
        Session session = this.mParentSession;
        if (session == null) {
            return this.mSessionId;
        }
        if (Log.VERBOSE) {
            return session.getFullSessionId(i + 1) + SESSION_SEPARATION_CHAR_CHILD + this.mSessionId;
        }
        return session.getFullSessionId(i + 1);
    }

    private Session getRootSession(String str) {
        int i = 0;
        while (true) {
            if (this.getParentSession() == null) {
                break;
            }
            if (i >= 25) {
                Slog.w(LOG_TAG, "getRootSession: Hit recursion limit from " + str);
                break;
            }
            this = this.getParentSession();
            i++;
        }
        return this;
    }

    public String printFullSessionTree() {
        return getRootSession("printFullSessionTree").printSessionTree();
    }

    private String printSessionTree() {
        StringBuilder sb = new StringBuilder();
        printSessionTree(0, sb, 0);
        return sb.toString();
    }

    private void printSessionTree(int i, StringBuilder sb, int i2) {
        if (i2 >= 25) {
            Slog.w(LOG_TAG, "printSessionTree: Hit recursion limit!");
            sb.append(TRUNCATE_STRING);
            return;
        }
        sb.append(toString());
        for (Session session : this.mChildSessions) {
            sb.append("\n");
            for (int i3 = 0; i3 <= i; i3++) {
                sb.append("\t");
            }
            session.printSessionTree(i + 1, sb, i2 + 1);
        }
    }

    public String getFullMethodPath(boolean z) {
        StringBuilder sb = new StringBuilder();
        getFullMethodPath(sb, z, 0);
        return sb.toString();
    }

    private synchronized void getFullMethodPath(StringBuilder sb, boolean z, int i) {
        if (i >= 25) {
            Slog.w(LOG_TAG, "getFullMethodPath: Hit recursion limit!");
            sb.append(TRUNCATE_STRING);
            return;
        }
        if (!TextUtils.isEmpty(this.mFullMethodPathCache) && !z) {
            sb.append(this.mFullMethodPathCache);
            return;
        }
        Session parentSession = getParentSession();
        if (parentSession != null) {
            z = this.mShortMethodName.equals(parentSession.mShortMethodName) ? false : true;
            parentSession.getFullMethodPath(sb, z, i + 1);
            sb.append(SUBSESSION_SEPARATION_CHAR);
        }
        if (!isExternal()) {
            sb.append(this.mShortMethodName);
        } else if (z) {
            sb.append(TRUNCATE_STRING);
        } else {
            sb.append("(");
            sb.append(this.mShortMethodName);
            sb.append(")");
        }
        if (z && !z) {
            this.mFullMethodPathCache = sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSessionExternal() {
        return getRootSession("isSessionExternal").isExternal();
    }

    public int hashCode() {
        String str = this.mSessionId;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.mShortMethodName;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        long j = this.mExecutionStartTimeMs;
        int i = (iHashCode2 + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.mExecutionEndTimeMs;
        int i2 = (i + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        Session session = this.mParentSession;
        int iHashCode3 = (i2 + (session != null ? session.hashCode() : 0)) * 31;
        ArrayList<Session> arrayList = this.mChildSessions;
        int iHashCode4 = (((((((iHashCode3 + (arrayList != null ? arrayList.hashCode() : 0)) * 31) + (this.mIsCompleted ? 1 : 0)) * 31) + this.mChildCounter) * 31) + (this.mIsStartedFromActiveSession ? 1 : 0)) * 31;
        String str3 = this.mOwnerInfo;
        return iHashCode4 + (str3 != null ? str3.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Session session = (Session) obj;
        if (this.mExecutionStartTimeMs != session.mExecutionStartTimeMs || this.mExecutionEndTimeMs != session.mExecutionEndTimeMs || this.mIsCompleted != session.mIsCompleted || this.mChildCounter != session.mChildCounter || this.mIsStartedFromActiveSession != session.mIsStartedFromActiveSession) {
            return false;
        }
        String str = this.mSessionId;
        if (str == null ? session.mSessionId != null : !str.equals(session.mSessionId)) {
            return false;
        }
        String str2 = this.mShortMethodName;
        if (str2 == null ? session.mShortMethodName != null : !str2.equals(session.mShortMethodName)) {
            return false;
        }
        Session session2 = this.mParentSession;
        if (session2 == null ? session.mParentSession != null : !session2.equals(session.mParentSession)) {
            return false;
        }
        ArrayList<Session> arrayList = this.mChildSessions;
        if (arrayList == null ? session.mChildSessions != null : !arrayList.equals(session.mChildSessions)) {
            return false;
        }
        String str3 = this.mOwnerInfo;
        return str3 != null ? str3.equals(session.mOwnerInfo) : session.mOwnerInfo == null;
    }

    public String toString() {
        Session session = this.mParentSession;
        if (session != null && this.mIsStartedFromActiveSession) {
            return session.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getFullMethodPath(false));
        String str = this.mOwnerInfo;
        if (str != null && !str.isEmpty()) {
            sb.append("(");
            sb.append(this.mOwnerInfo);
            sb.append(")");
        }
        return sb.toString() + "@" + getFullSessionId();
    }
}
