package p;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public abstract class h71 implements ro1 {
    public abstract Object deserialize(q71 q71Var, DeserializationContext deserializationContext);

    public Object deserialize(q71 q71Var, DeserializationContext deserializationContext, Object obj) {
        deserializationContext.l(this);
        return deserialize(q71Var, deserializationContext);
    }

    public Object deserializeWithType(q71 q71Var, DeserializationContext deserializationContext, no2 no2Var) {
        return no2Var.a(q71Var, deserializationContext);
    }

    public SettableBeanProperty findBackReference(String str) {
        StringBuilder sbP = u4.p("Cannot handle managed/back reference '", str, "': type: value deserializer of type ");
        sbP.append(getClass().getName());
        sbP.append(" does not support them");
        throw new IllegalArgumentException(sbP.toString());
    }

    public Object getAbsentValue(DeserializationContext deserializationContext) {
        return getNullValue(deserializationContext);
    }

    public h71 getDelegatee() {
        return null;
    }

    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.k;
    }

    public Object getEmptyValue(DeserializationContext deserializationContext) {
        return getNullValue(deserializationContext);
    }

    public Collection<Object> getKnownPropertyNames() {
        return null;
    }

    public AccessPattern getNullAccessPattern() {
        return AccessPattern.e;
    }

    @Deprecated
    public Object getNullValue() {
        return null;
    }

    public ObjectIdReader getObjectIdReader() {
        return null;
    }

    public Class<?> handledType() {
        return null;
    }

    public boolean isCachable() {
        return false;
    }

    public LogicalType logicalType() {
        return null;
    }

    public h71 replaceDelegatee(h71 h71Var) {
        throw new UnsupportedOperationException();
    }

    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return null;
    }

    public h71 unwrappingDeserializer(am1 am1Var) {
        return this;
    }

    public Object deserializeWithType(q71 q71Var, DeserializationContext deserializationContext, no2 no2Var, Object obj) {
        deserializationContext.l(this);
        return deserializeWithType(q71Var, deserializationContext, no2Var);
    }

    @Deprecated
    public Object getEmptyValue() {
        return getNullValue();
    }

    public Object getNullValue(DeserializationContext deserializationContext) {
        return getNullValue();
    }
}
