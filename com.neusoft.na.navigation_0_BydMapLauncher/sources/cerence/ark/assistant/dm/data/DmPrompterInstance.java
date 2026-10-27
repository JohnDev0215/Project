package cerence.ark.assistant.dm.data;

/* JADX INFO: loaded from: classes2.dex */
public class DmPrompterInstance {
    private String mName;
    private String mType;

    public DmPrompterInstance() {
    }

    public DmPrompterInstance(String str, String str2) {
        this.mName = str;
        this.mType = str2;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }
}
