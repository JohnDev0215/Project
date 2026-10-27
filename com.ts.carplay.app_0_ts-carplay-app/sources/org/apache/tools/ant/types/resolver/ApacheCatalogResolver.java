package org.apache.tools.ant.types.resolver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.ResourceLocation;
import org.apache.tools.ant.types.XMLCatalog;
import org.apache.xml.resolver.Catalog;
import org.apache.xml.resolver.CatalogManager;
import org.apache.xml.resolver.tools.CatalogResolver;

/* JADX INFO: loaded from: classes.dex */
public class ApacheCatalogResolver extends CatalogResolver {
    static /* synthetic */ Class class$org$apache$tools$ant$types$resolver$ApacheCatalog;
    private XMLCatalog xmlCatalog = null;

    static {
        CatalogManager.getStaticManager().setIgnoreMissingProperties(true);
        Properties properties = System.getProperties();
        Class clsClass$ = class$org$apache$tools$ant$types$resolver$ApacheCatalog;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.tools.ant.types.resolver.ApacheCatalog");
            class$org$apache$tools$ant$types$resolver$ApacheCatalog = clsClass$;
        }
        properties.put("xml.catalog.className", clsClass$.getName());
        CatalogManager.getStaticManager().setUseStaticCatalog(false);
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    public void setXMLCatalog(XMLCatalog xmlCatalog) {
        this.xmlCatalog = xmlCatalog;
    }

    public void parseCatalog(String file) {
        Catalog _catalog = getCatalog();
        if (!(_catalog instanceof ApacheCatalog)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Wrong catalog type found: ");
            stringBuffer.append(_catalog.getClass().getName());
            throw new BuildException(stringBuffer.toString());
        }
        ApacheCatalog catalog = (ApacheCatalog) _catalog;
        catalog.setResolver(this);
        try {
            catalog.parseCatalog(file);
        } catch (MalformedURLException ex) {
            throw new BuildException(ex);
        } catch (IOException ex2) {
            throw new BuildException(ex2);
        }
    }

    public void addPublicEntry(String publicid, String systemid, URL base) {
        ResourceLocation dtd = new ResourceLocation();
        dtd.setBase(base);
        dtd.setPublicId(publicid);
        dtd.setLocation(systemid);
        this.xmlCatalog.addDTD(dtd);
    }

    public void addURIEntry(String uri, String altURI, URL base) {
        ResourceLocation entity = new ResourceLocation();
        entity.setBase(base);
        entity.setPublicId(uri);
        entity.setLocation(altURI);
        this.xmlCatalog.addEntity(entity);
    }
}
