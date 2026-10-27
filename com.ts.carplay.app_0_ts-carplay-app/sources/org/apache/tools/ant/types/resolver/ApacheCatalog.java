package org.apache.tools.ant.types.resolver;

import org.apache.xml.resolver.Catalog;
import org.apache.xml.resolver.CatalogEntry;
import org.apache.xml.resolver.helpers.PublicId;

/* JADX INFO: loaded from: classes.dex */
public class ApacheCatalog extends Catalog {
    private ApacheCatalogResolver resolver = null;

    protected Catalog newCatalog() {
        ApacheCatalog cat = (ApacheCatalog) super.newCatalog();
        cat.setResolver(this.resolver);
        return cat;
    }

    public void setResolver(ApacheCatalogResolver resolver) {
        this.resolver = resolver;
    }

    public void addEntry(CatalogEntry entry) {
        int type = entry.getEntryType();
        if (type == Catalog.PUBLIC) {
            String publicid = PublicId.normalize(entry.getEntryArg(0));
            String systemid = normalizeURI(entry.getEntryArg(1));
            ApacheCatalogResolver apacheCatalogResolver = this.resolver;
            if (apacheCatalogResolver == null) {
                this.catalogManager.debug.message(1, "Internal Error: null ApacheCatalogResolver");
            } else {
                apacheCatalogResolver.addPublicEntry(publicid, systemid, this.base);
            }
        } else if (type == Catalog.URI) {
            String uri = normalizeURI(entry.getEntryArg(0));
            String altURI = normalizeURI(entry.getEntryArg(1));
            ApacheCatalogResolver apacheCatalogResolver2 = this.resolver;
            if (apacheCatalogResolver2 == null) {
                this.catalogManager.debug.message(1, "Internal Error: null ApacheCatalogResolver");
            } else {
                apacheCatalogResolver2.addURIEntry(uri, altURI, this.base);
            }
        }
        super.addEntry(entry);
    }
}
