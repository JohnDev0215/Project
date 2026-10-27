package p;

import com.spotify.rcs.model.GranularConfiguration;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class w01 {
    public final String a;
    public final long b;
    public final List c;
    public final long d;
    public final String e;

    public w01(String str, long j, List list, long j2, String str2) {
        this.a = str;
        this.b = j;
        this.c = list;
        this.d = j2;
        this.e = str2;
    }

    public static w01 a(w01 w01Var, List list, String str, int i) {
        String str2 = (i & 1) != 0 ? w01Var.a : null;
        long j = (i & 2) != 0 ? w01Var.b : 0L;
        if ((i & 4) != 0) {
            list = w01Var.c;
        }
        long j2 = (i & 8) != 0 ? w01Var.d : 0L;
        if ((i & 16) != 0) {
            str = w01Var.e;
        }
        w01Var.getClass();
        v03.j(str2, "configurationAssignmentId");
        v03.j(list, "propertiesList");
        v03.j(str, "etag");
        return new w01(str2, j, list, j2, str);
    }

    public final byte[] b() {
        GranularConfiguration.Builder etag = GranularConfiguration.G().setConfigurationAssignmentId(this.a).setPolicySnapshotId(this.d).setRcsFetchTime(this.b).setEtag(this.e);
        List<pc> list = this.c;
        ArrayList arrayList = new ArrayList(lu.W(list));
        for (pc pcVar : list) {
            pcVar.getClass();
            GranularConfiguration.AssignedPropertyValue.Builder policyId = GranularConfiguration.AssignedPropertyValue.newBuilder().setName(pcVar.a).setComponentId(pcVar.b).setGroupId(pcVar.f).setPolicyId(pcVar.g);
            Boolean bool = pcVar.c;
            if (bool != null) {
                policyId.setBoolValue(GranularConfiguration.AssignedPropertyValue.BoolValue.newBuilder().setValue(bool.booleanValue()));
            } else {
                Integer num = pcVar.d;
                if (num != null) {
                    policyId.setIntValue(GranularConfiguration.AssignedPropertyValue.IntValue.newBuilder().setValue(num.intValue()));
                } else {
                    String str = pcVar.e;
                    if (str != null) {
                        policyId.setEnumValue(GranularConfiguration.AssignedPropertyValue.EnumValue.newBuilder().setValue(str));
                    }
                }
            }
            com.google.protobuf.i iVarBuild = policyId.build();
            v03.i(iVarBuild, "propBuilder.build()");
            arrayList.add((GranularConfiguration.AssignedPropertyValue) iVarBuild);
        }
        byte[] byteArray = ((GranularConfiguration) etag.addAllProperties(arrayList).build()).toByteArray();
        v03.i(byteArray, "newBuilder()\n        .se…()\n        .toByteArray()");
        return byteArray;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof w01)) {
            return false;
        }
        w01 w01Var = (w01) obj;
        return v03.b(this.a, w01Var.a) && this.b == w01Var.b && v03.b(this.c, w01Var.c) && this.d == w01Var.d && v03.b(this.e, w01Var.e);
    }

    public final int hashCode() {
        return this.e.hashCode() + ((Long.hashCode(this.d) + ((this.c.hashCode() + ((Long.hashCode(this.b) + (this.a.hashCode() * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("GranularConfiguration(configurationAssignmentId=");
        sb.append(this.a);
        sb.append(", rcsFetchTime=");
        sb.append(this.b);
        sb.append(", propertiesList=");
        sb.append(this.c);
        sb.append(", snapshotId=");
        sb.append(this.d);
        sb.append(", etag=");
        return hf2.d(sb, this.e, ')');
    }

    public w01(String str, long j, ArrayList arrayList, long j2, int i) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? -1L : j, (i & 4) != 0 ? EmptyList.c : arrayList, (i & 8) != 0 ? -1L : j2, (i & 16) == 0 ? null : "");
    }
}
