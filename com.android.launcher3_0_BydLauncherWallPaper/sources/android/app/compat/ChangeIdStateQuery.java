package android.app.compat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
final class ChangeIdStateQuery {
    static final int QUERY_BY_PACKAGE_NAME = 0;
    static final int QUERY_BY_UID = 1;
    public long changeId;
    public String packageName;
    public int type;
    public int uid;
    public int userId;

    @Retention(RetentionPolicy.SOURCE)
    @interface QueryType {
    }

    private ChangeIdStateQuery(int i, long j, String str, int i2, int i3) {
        this.type = i;
        this.changeId = j;
        this.packageName = str;
        this.uid = i2;
        this.userId = i3;
    }

    static ChangeIdStateQuery byPackageName(long j, String str, int i) {
        return new ChangeIdStateQuery(0, j, str, 0, i);
    }

    static ChangeIdStateQuery byUid(long j, int i) {
        return new ChangeIdStateQuery(1, j, null, i, 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ChangeIdStateQuery)) {
            return false;
        }
        ChangeIdStateQuery changeIdStateQuery = (ChangeIdStateQuery) obj;
        return this.type == changeIdStateQuery.type && this.changeId == changeIdStateQuery.changeId && Objects.equals(this.packageName, changeIdStateQuery.packageName) && this.uid == changeIdStateQuery.uid && this.userId == changeIdStateQuery.userId;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.type), Long.valueOf(this.changeId), this.packageName, Integer.valueOf(this.uid), Integer.valueOf(this.userId));
    }
}
