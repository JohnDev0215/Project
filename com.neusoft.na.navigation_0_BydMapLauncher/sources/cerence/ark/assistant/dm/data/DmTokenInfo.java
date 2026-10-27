package cerence.ark.assistant.dm.data;

/* JADX INFO: loaded from: classes2.dex */
public class DmTokenInfo {
    private String mAccessToken;
    private int mExpireIn;
    private String mRefreshToken;
    private String mTokenType;

    public DmTokenInfo setTokenType(String str) {
        this.mTokenType = str;
        return this;
    }

    public DmTokenInfo setAccessToken(String str) {
        this.mAccessToken = str;
        return this;
    }

    public DmTokenInfo setRefreshToken(String str) {
        this.mRefreshToken = str;
        return this;
    }

    public DmTokenInfo setExpireIn(int i) {
        this.mExpireIn = i;
        return this;
    }

    public String getTokenType() {
        return this.mTokenType;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public String getRefreshToken() {
        return this.mRefreshToken;
    }

    public int getExpireIn() {
        return this.mExpireIn;
    }
}
