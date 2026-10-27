package p;

import com.google.android.datatransport.cct.internal.ClientInfo$ClientType;

/* JADX INFO: loaded from: classes.dex */
public final class ah extends vt {
    public final ClientInfo$ClientType a;
    public final t6 b;

    public ah(ClientInfo$ClientType clientInfo$ClientType, t6 t6Var) {
        this.a = clientInfo$ClientType;
        this.b = t6Var;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof vt)) {
            return false;
        }
        vt vtVar = (vt) obj;
        ClientInfo$ClientType clientInfo$ClientType = this.a;
        if (clientInfo$ClientType != null ? clientInfo$ClientType.equals(((ah) vtVar).a) : ((ah) vtVar).a == null) {
            t6 t6Var = this.b;
            if (t6Var == null) {
                if (((ah) vtVar).b == null) {
                    return true;
                }
            } else if (t6Var.equals(((ah) vtVar).b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        ClientInfo$ClientType clientInfo$ClientType = this.a;
        int iHashCode = ((clientInfo$ClientType == null ? 0 : clientInfo$ClientType.hashCode()) ^ 1000003) * 1000003;
        t6 t6Var = this.b;
        return (t6Var != null ? t6Var.hashCode() : 0) ^ iHashCode;
    }

    public final String toString() {
        return "ClientInfo{clientType=" + this.a + ", androidClientInfo=" + this.b + "}";
    }
}
