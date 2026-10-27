package p;

import com.fasterxml.jackson.databind.JavaType;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class v71 {
    public void acceptJsonFormatVisitor(k71 k71Var, JavaType javaType) {
        k71Var.getClass();
    }

    public v71 getDelegatee() {
        return null;
    }

    public Class<Object> handledType() {
        return null;
    }

    public boolean isEmpty(rb2 rb2Var, Object obj) {
        return obj == null;
    }

    public boolean isUnwrappingSerializer() {
        return false;
    }

    public Iterator<Object> properties() {
        return vs.a;
    }

    public v71 replaceDelegatee(v71 v71Var) {
        throw new UnsupportedOperationException();
    }

    public void serializeWithType(Object obj, l71 l71Var, rb2 rb2Var, po2 po2Var) {
        Class<?> clsHandledType = handledType();
        if (clsHandledType == null) {
            clsHandledType = obj.getClass();
        }
        rb2Var.b(clsHandledType, String.format("Type id handling not implemented for type %s (by serializer of type %s)", clsHandledType.getName(), getClass().getName()));
    }

    public v71 unwrappingSerializer(am1 am1Var) {
        return this;
    }

    public boolean usesObjectId() {
        return false;
    }

    public v71 withFilterId(Object obj) {
        return this;
    }

    @Deprecated
    public boolean isEmpty(Object obj) {
        return isEmpty(null, obj);
    }
}
