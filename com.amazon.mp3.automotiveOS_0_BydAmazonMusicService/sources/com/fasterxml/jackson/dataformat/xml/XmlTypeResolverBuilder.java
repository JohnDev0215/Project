package com.fasterxml.jackson.dataformat.xml;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.ClassNameIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.MinimalClassNameIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.util.StaxUtil;
import java.io.IOException;
import java.util.Collection;

/* JADX INFO: loaded from: classes6.dex */
public class XmlTypeResolverBuilder extends StdTypeResolverBuilder {
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
            return new XmlClassNameIdResolver(javaType, mapperConfig.getTypeFactory(), subTypeValidator(mapperConfig));
        }
        if (i == 2) {
            return new XmlMinimalClassNameIdResolver(javaType, mapperConfig.getTypeFactory(), subTypeValidator(mapperConfig));
        }
        return super.idResolver(mapperConfig, javaType, polymorphicTypeValidator, collection, z, z2);
    }

    /* JADX INFO: renamed from: com.fasterxml.jackson.dataformat.xml.XmlTypeResolverBuilder$1, reason: invalid class name */
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

    protected static String encodeXmlClassName(String str) {
        int iLastIndexOf = str.lastIndexOf(36);
        if (iLastIndexOf < 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        do {
            sb.replace(iLastIndexOf, iLastIndexOf + 1, "..");
            iLastIndexOf = str.lastIndexOf(36, iLastIndexOf - 1);
        } while (iLastIndexOf >= 0);
        return sb.toString();
    }

    protected static String decodeXmlClassName(String str) {
        int iLastIndexOf = str.lastIndexOf("..");
        if (iLastIndexOf < 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        do {
            sb.replace(iLastIndexOf, iLastIndexOf + 2, "$");
            iLastIndexOf = str.lastIndexOf("..", iLastIndexOf - 1);
        } while (iLastIndexOf >= 0);
        return sb.toString();
    }

    protected static class XmlClassNameIdResolver extends ClassNameIdResolver {
        public XmlClassNameIdResolver(JavaType javaType, TypeFactory typeFactory, PolymorphicTypeValidator polymorphicTypeValidator) {
            super(javaType, typeFactory, polymorphicTypeValidator);
        }

        @Override // com.fasterxml.jackson.databind.jsontype.impl.ClassNameIdResolver, com.fasterxml.jackson.databind.jsontype.TypeIdResolver
        public String idFromValue(Object obj) {
            return XmlTypeResolverBuilder.encodeXmlClassName(super.idFromValue(obj));
        }

        @Override // com.fasterxml.jackson.databind.jsontype.impl.ClassNameIdResolver, com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase, com.fasterxml.jackson.databind.jsontype.TypeIdResolver
        public JavaType typeFromId(DatabindContext databindContext, String str) throws IOException {
            return super.typeFromId(databindContext, XmlTypeResolverBuilder.decodeXmlClassName(str));
        }
    }

    protected static class XmlMinimalClassNameIdResolver extends MinimalClassNameIdResolver {
        public XmlMinimalClassNameIdResolver(JavaType javaType, TypeFactory typeFactory, PolymorphicTypeValidator polymorphicTypeValidator) {
            super(javaType, typeFactory, polymorphicTypeValidator);
        }

        @Override // com.fasterxml.jackson.databind.jsontype.impl.MinimalClassNameIdResolver, com.fasterxml.jackson.databind.jsontype.impl.ClassNameIdResolver, com.fasterxml.jackson.databind.jsontype.TypeIdResolver
        public String idFromValue(Object obj) {
            return XmlTypeResolverBuilder.encodeXmlClassName(super.idFromValue(obj));
        }

        @Override // com.fasterxml.jackson.databind.jsontype.impl.ClassNameIdResolver, com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase, com.fasterxml.jackson.databind.jsontype.TypeIdResolver
        public JavaType typeFromId(DatabindContext databindContext, String str) throws IOException {
            return super.typeFromId(databindContext, XmlTypeResolverBuilder.decodeXmlClassName(str));
        }
    }
}
