package com.fasterxml.jackson.dataformat.xml;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.dataformat.xml.XmlTypeResolverBuilder;
import com.fasterxml.jackson.dataformat.xml.util.StaxUtil;
import java.util.Collection;

/* JADX INFO: loaded from: classes6.dex */
public class DefaultingXmlTypeResolverBuilder extends ObjectMapper.DefaultTypeResolverBuilder {
    private static final long serialVersionUID = 1;

    public DefaultingXmlTypeResolverBuilder(ObjectMapper.DefaultTyping defaultTyping, PolymorphicTypeValidator polymorphicTypeValidator) {
        super(defaultTyping, polymorphicTypeValidator);
    }

    @Override // com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder, com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder
    public StdTypeResolverBuilder init(JsonTypeInfo.Id id, TypeIdResolver typeIdResolver) {
        super.init(id, typeIdResolver);
        String str = this._typeProperty;
        if (str != null) {
            this._typeProperty = StaxUtil.sanitizeXmlTypeName(str);
        }
        return this;
    }

    @Override // com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder, com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder
    public StdTypeResolverBuilder typeProperty(String str) {
        if (str == null || str.length() == 0) {
            str = this._idType.getDefaultPropertyName();
        }
        this._typeProperty = StaxUtil.sanitizeXmlTypeName(str);
        return this;
    }

    @Override // com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder
    protected TypeIdResolver idResolver(MapperConfig<?> mapperConfig, JavaType javaType, PolymorphicTypeValidator polymorphicTypeValidator, Collection<NamedType> collection, boolean z, boolean z2) {
        TypeIdResolver typeIdResolver = this._customIdResolver;
        if (typeIdResolver != null) {
            return typeIdResolver;
        }
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$annotation$JsonTypeInfo$Id[this._idType.ordinal()];
        if (i == 1) {
            return new XmlTypeResolverBuilder.XmlClassNameIdResolver(javaType, mapperConfig.getTypeFactory(), subTypeValidator(mapperConfig));
        }
        if (i == 2) {
            return new XmlTypeResolverBuilder.XmlMinimalClassNameIdResolver(javaType, mapperConfig.getTypeFactory(), subTypeValidator(mapperConfig));
        }
        return super.idResolver(mapperConfig, javaType, polymorphicTypeValidator, collection, z, z2);
    }

    /* JADX INFO: renamed from: com.fasterxml.jackson.dataformat.xml.DefaultingXmlTypeResolverBuilder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$annotation$JsonTypeInfo$Id;

        static {
            int[] iArr = new int[JsonTypeInfo.Id.values().length];
            $SwitchMap$com$fasterxml$jackson$annotation$JsonTypeInfo$Id = iArr;
            try {
                iArr[JsonTypeInfo.Id.CLASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonTypeInfo$Id[JsonTypeInfo.Id.MINIMAL_CLASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
