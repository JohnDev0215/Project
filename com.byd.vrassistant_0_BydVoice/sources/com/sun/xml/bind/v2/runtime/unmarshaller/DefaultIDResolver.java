package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.IDResolver;
import java.util.HashMap;
import java.util.concurrent.Callable;
import javax.xml.bind.ValidationEventHandler;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
final class DefaultIDResolver extends IDResolver {
    private HashMap<String, Object> idmap = null;

    DefaultIDResolver() {
    }

    @Override // com.sun.xml.bind.IDResolver
    public void startDocument(ValidationEventHandler validationEventHandler) throws SAXException {
        HashMap<String, Object> map = this.idmap;
        if (map != null) {
            map.clear();
        }
    }

    @Override // com.sun.xml.bind.IDResolver
    public void bind(String str, Object obj) {
        if (this.idmap == null) {
            this.idmap = new HashMap<>();
        }
        this.idmap.put(str, obj);
    }

    @Override // com.sun.xml.bind.IDResolver
    public Callable resolve(final String str, Class cls) {
        return new Callable() { // from class: com.sun.xml.bind.v2.runtime.unmarshaller.DefaultIDResolver.1
            @Override // java.util.concurrent.Callable
            public Object call() throws Exception {
                if (DefaultIDResolver.this.idmap == null) {
                    return null;
                }
                return DefaultIDResolver.this.idmap.get(str);
            }
        };
    }
}
