package R7;

import h8.AbstractC1387d;
import java.util.ArrayList;
import java.util.HashMap;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.z;

/* JADX INFO: loaded from: classes.dex */
public enum n {
    CLASS(true),
    ANNOTATION_CLASS(true),
    TYPE_PARAMETER(false),
    PROPERTY(true),
    FIELD(true),
    LOCAL_VARIABLE(true),
    VALUE_PARAMETER(true),
    CONSTRUCTOR(true),
    FUNCTION(true),
    PROPERTY_GETTER(true),
    PROPERTY_SETTER(true),
    TYPE(false),
    /* JADX INFO: Fake field, exist only in values array */
    STAR_PROJECTION(false),
    FILE(false),
    /* JADX INFO: Fake field, exist only in values array */
    STAR_PROJECTION(false),
    /* JADX INFO: Fake field, exist only in values array */
    PROPERTY_PARAMETER(false),
    /* JADX INFO: Fake field, exist only in values array */
    STAR_PROJECTION(false),
    /* JADX INFO: Fake field, exist only in values array */
    PROPERTY_PARAMETER(false),
    CLASS_ONLY(false),
    OBJECT(false),
    STANDALONE_OBJECT(false),
    COMPANION_OBJECT(false),
    INTERFACE(false),
    ENUM_CLASS(false),
    ENUM_ENTRY(false),
    LOCAL_CLASS(false),
    /* JADX INFO: Fake field, exist only in values array */
    LOCAL_FUNCTION(false),
    /* JADX INFO: Fake field, exist only in values array */
    MEMBER_FUNCTION(false),
    /* JADX INFO: Fake field, exist only in values array */
    TOP_LEVEL_FUNCTION(false),
    /* JADX INFO: Fake field, exist only in values array */
    MEMBER_PROPERTY(false),
    /* JADX INFO: Fake field, exist only in values array */
    MEMBER_PROPERTY_WITH_BACKING_FIELD(false),
    /* JADX INFO: Fake field, exist only in values array */
    MEMBER_PROPERTY_WITH_DELEGATE(false),
    /* JADX INFO: Fake field, exist only in values array */
    MEMBER_PROPERTY_WITHOUT_FIELD_OR_DELEGATE(false),
    /* JADX INFO: Fake field, exist only in values array */
    TOP_LEVEL_PROPERTY(false),
    /* JADX INFO: Fake field, exist only in values array */
    TOP_LEVEL_PROPERTY_WITH_BACKING_FIELD(false),
    /* JADX INFO: Fake field, exist only in values array */
    TOP_LEVEL_PROPERTY_WITH_DELEGATE(false),
    /* JADX INFO: Fake field, exist only in values array */
    TOP_LEVEL_PROPERTY_WITHOUT_FIELD_OR_DELEGATE(false),
    /* JADX INFO: Fake field, exist only in values array */
    BACKING_FIELD(true),
    /* JADX INFO: Fake field, exist only in values array */
    INITIALIZER(false),
    /* JADX INFO: Fake field, exist only in values array */
    DESTRUCTURING_DECLARATION(false),
    /* JADX INFO: Fake field, exist only in values array */
    LAMBDA_EXPRESSION(false),
    /* JADX INFO: Fake field, exist only in values array */
    ANONYMOUS_FUNCTION(false),
    /* JADX INFO: Fake field, exist only in values array */
    OBJECT_LITERAL(false);


    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final HashMap f7551m = new HashMap();
    public final boolean l;

    static {
        for (n nVar : values()) {
            f7551m.put(nVar.name(), nVar);
        }
        n[] nVarArrValues = values();
        ArrayList arrayList = new ArrayList();
        for (n nVar2 : nVarArrValues) {
            if (nVar2.l) {
                arrayList.add(nVar2);
            }
        }
        AbstractC1998m.D0(arrayList);
        AbstractC1996k.c0(values());
        n nVar3 = ANNOTATION_CLASS;
        n nVar4 = CLASS;
        AbstractC1999n.D(nVar3, nVar4);
        AbstractC1999n.D(LOCAL_CLASS, nVar4);
        AbstractC1999n.D(CLASS_ONLY, nVar4);
        n nVar5 = COMPANION_OBJECT;
        n nVar6 = OBJECT;
        AbstractC1999n.D(nVar5, nVar6, nVar4);
        AbstractC1999n.D(STANDALONE_OBJECT, nVar6, nVar4);
        AbstractC1999n.D(INTERFACE, nVar4);
        AbstractC1999n.D(ENUM_CLASS, nVar4);
        n nVar7 = ENUM_ENTRY;
        n nVar8 = PROPERTY;
        n nVar9 = FIELD;
        AbstractC1999n.D(nVar7, nVar8, nVar9);
        n nVar10 = PROPERTY_SETTER;
        AbstractC1387d.j(nVar10);
        n nVar11 = PROPERTY_GETTER;
        AbstractC1387d.j(nVar11);
        AbstractC1387d.j(FUNCTION);
        n nVar12 = FILE;
        AbstractC1387d.j(nVar12);
        d dVar = d.CONSTRUCTOR_PARAMETER;
        n nVar13 = VALUE_PARAMETER;
        z.s(new m7.i(dVar, nVar13), new m7.i(d.FIELD, nVar9), new m7.i(d.PROPERTY, nVar8), new m7.i(d.FILE, nVar12), new m7.i(d.PROPERTY_GETTER, nVar11), new m7.i(d.PROPERTY_SETTER, nVar10), new m7.i(d.RECEIVER, nVar13), new m7.i(d.SETTER_PARAMETER, nVar13), new m7.i(d.PROPERTY_DELEGATE_FIELD, nVar9));
    }

    n(boolean z6) {
        this.l = z6;
    }
}
