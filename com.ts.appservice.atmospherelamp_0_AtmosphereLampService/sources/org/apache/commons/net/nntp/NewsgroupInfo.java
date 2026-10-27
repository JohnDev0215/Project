package org.apache.commons.net.nntp;

/* JADX INFO: loaded from: classes3.dex */
public final class NewsgroupInfo {
    public static final int MODERATED_POSTING_PERMISSION = 1;
    public static final int PERMITTED_POSTING_PERMISSION = 2;
    public static final int PROHIBITED_POSTING_PERMISSION = 3;
    public static final int UNKNOWN_POSTING_PERMISSION = 0;
    private long estimatedArticleCount;
    private long firstArticle;
    private long lastArticle;
    private String newsgroup;
    private int postingPermission;

    void setNewsgroup(String newsgroup) {
        this.newsgroup = newsgroup;
    }

    void setArticleCount(long count) {
        this.estimatedArticleCount = count;
    }

    void setFirstArticle(long first) {
        this.firstArticle = first;
    }

    void setLastArticle(long last) {
        this.lastArticle = last;
    }

    void setPostingPermission(int permission) {
        this.postingPermission = permission;
    }

    public String getNewsgroup() {
        return this.newsgroup;
    }

    public long getArticleCountLong() {
        return this.estimatedArticleCount;
    }

    public long getFirstArticleLong() {
        return this.firstArticle;
    }

    public long getLastArticleLong() {
        return this.lastArticle;
    }

    public int getPostingPermission() {
        return this.postingPermission;
    }

    @Deprecated
    public int getArticleCount() {
        return (int) this.estimatedArticleCount;
    }

    @Deprecated
    public int getFirstArticle() {
        return (int) this.firstArticle;
    }

    @Deprecated
    public int getLastArticle() {
        return (int) this.lastArticle;
    }
}
