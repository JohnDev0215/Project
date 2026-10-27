package p;

import com.spotify.rcs.model.GranularConfiguration;
import com.spotify.rcs.resolver.grpc.v0.Configuration;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class v01 {
    public static w01 a(byte[] bArr) {
        GranularConfiguration granularConfigurationH = GranularConfiguration.H(bArr);
        List<GranularConfiguration.AssignedPropertyValue> listD = granularConfigurationH.D();
        v03.i(listD, "storageProto.propertiesList");
        ArrayList arrayList = new ArrayList(lu.W(listD));
        for (GranularConfiguration.AssignedPropertyValue assignedPropertyValue : listD) {
            v03.i(assignedPropertyValue, "it");
            String value = null;
            Boolean boolValueOf = assignedPropertyValue.getStructuredValueCase() == GranularConfiguration.AssignedPropertyValue.StructuredValueCase.BOOL_VALUE ? Boolean.valueOf(assignedPropertyValue.getBoolValue().getValue()) : null;
            Integer numValueOf = assignedPropertyValue.getStructuredValueCase() == GranularConfiguration.AssignedPropertyValue.StructuredValueCase.INT_VALUE ? Integer.valueOf(assignedPropertyValue.getIntValue().getValue()) : null;
            if (assignedPropertyValue.getStructuredValueCase() == GranularConfiguration.AssignedPropertyValue.StructuredValueCase.ENUM_VALUE) {
                value = assignedPropertyValue.getEnumValue().getValue();
            }
            String name = assignedPropertyValue.getName();
            v03.i(name, "proto.name");
            String componentId = assignedPropertyValue.getComponentId();
            v03.i(componentId, "proto.componentId");
            arrayList.add(new pc(name, componentId, boolValueOf, numValueOf, value, assignedPropertyValue.getGroupId(), assignedPropertyValue.getPolicyId()));
        }
        String strW = granularConfigurationH.w();
        v03.i(strW, "storageProto.configurationAssignmentId");
        long jE = granularConfigurationH.E();
        long jA = granularConfigurationH.A();
        String strY = granularConfigurationH.y();
        v03.i(strY, "storageProto.etag");
        return new w01(strW, jE, arrayList, jA, strY);
    }

    public static w01 b(Configuration configuration) {
        v03.j(configuration, "protoConfiguration");
        v51<Configuration.AssignedValue> v51VarE = configuration.e();
        v03.i(v51VarE, "protoConfiguration.assignedValuesList");
        ArrayList arrayList = new ArrayList(lu.W(v51VarE));
        for (Configuration.AssignedValue assignedValue : v51VarE) {
            v03.i(assignedValue, "it");
            String value = null;
            Boolean boolValueOf = assignedValue.j() == Configuration.AssignedValue.StructuredValueCase.BOOL_VALUE ? Boolean.valueOf(assignedValue.e().getValue()) : null;
            Integer numValueOf = assignedValue.j() == Configuration.AssignedValue.StructuredValueCase.INT_VALUE ? Integer.valueOf(assignedValue.g().getValue()) : null;
            if (assignedValue.j() == Configuration.AssignedValue.StructuredValueCase.ENUM_VALUE) {
                value = assignedValue.f().getValue();
            }
            String name = assignedValue.i().getName();
            v03.i(name, "proto.propertyId.name");
            String strG = assignedValue.i().g();
            v03.i(strG, "proto.propertyId.scope");
            arrayList.add(new pc(name, strG, boolValueOf, numValueOf, value, assignedValue.h().f(), assignedValue.h().getPolicyId()));
        }
        String strF = configuration.f();
        v03.i(strF, "protoConfiguration.configurationAssignmentId");
        return new w01(strF, configuration.h(), arrayList, configuration.i(), 16);
    }
}
