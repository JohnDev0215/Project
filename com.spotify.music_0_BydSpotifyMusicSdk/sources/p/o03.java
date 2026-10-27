package p;

/* JADX INFO: loaded from: classes.dex */
public final class o03 {
    public final String a;
    public final String b;

    public o03(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof o03) {
            o03 o03Var = (o03) obj;
            o03Var.getClass();
            String str = o03Var.a;
            String str2 = this.a;
            if (str2 != null ? str2.equals(str) : str == null) {
                String str3 = o03Var.b;
                String str4 = this.b;
                if (str4 != null ? str4.equals(str3) : str3 == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        String str = this.a;
        int iHashCode = ((str == null ? 0 : str.hashCode()) ^ (-721379959)) * 1000003;
        String str2 = this.b;
        return (str2 != null ? str2.hashCode() : 0) ^ iHashCode;
    }

    public final String toString() {
        String str = this.a;
        int length = String.valueOf(str).length();
        String str2 = this.b;
        StringBuilder sb = new StringBuilder(length + 68 + String.valueOf(str2).length());
        sb.append("AssetPackLocation{packStorageMethod=0, path=");
        sb.append(str);
        sb.append(", assetsPath=");
        sb.append(str2);
        sb.append("}");
        return sb.toString();
    }
}
