package p;

import com.fasterxml.jackson.annotation.JsonFormat$Value;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfigBase;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;

/* JADX INFO: loaded from: classes.dex */
public interface sl {
    PropertyName c();

    JsonFormat$Value d(MapperConfigBase mapperConfigBase, Class cls);

    PropertyMetadata getMetadata();

    JavaType getType();

    AnnotatedMember j();
}
