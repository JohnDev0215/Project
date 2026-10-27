package net.sqlcipher.database;

/* JADX INFO: loaded from: classes3.dex */
public class SQLiteQueryStats {
    public long largestIndividualRowSize;
    public long totalQueryResultSize;

    public SQLiteQueryStats(long j, long j2) {
        this.totalQueryResultSize = 0L;
        this.largestIndividualRowSize = 0L;
        this.totalQueryResultSize = j;
        this.largestIndividualRowSize = j2;
    }

    public long getLargestIndividualRowSize() {
        return this.largestIndividualRowSize;
    }

    public long getTotalQueryResultSize() {
        return this.totalQueryResultSize;
    }
}
