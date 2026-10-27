package org.apache.tools.ant.types.mappers;

import com.ts.lib.car.VehicleVendorProperty;
import java.io.Reader;
import java.io.StringReader;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.UnsupportedAttributeException;
import org.apache.tools.ant.filters.util.ChainReaderHelper;
import org.apache.tools.ant.types.FilterChain;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class FilterMapper extends FilterChain implements FileNameMapper {
    @Override // org.apache.tools.ant.util.FileNameMapper
    public void setFrom(String from) {
        throw new UnsupportedAttributeException("filtermapper doesn't support the \"from\" attribute.", "from");
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public void setTo(String to) {
        throw new UnsupportedAttributeException("filtermapper doesn't support the \"to\" attribute.", "to");
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public String[] mapFileName(String sourceFileName) {
        Exception ex;
        try {
            try {
                Reader stringReader = new StringReader(sourceFileName);
                try {
                    ChainReaderHelper helper = new ChainReaderHelper();
                    helper.setBufferSize(VehicleVendorProperty.Health.OIL_HINT);
                    helper.setPrimaryReader(stringReader);
                    helper.setProject(getProject());
                    Vector filterChains = new Vector();
                    filterChains.add(this);
                    helper.setFilterChains(filterChains);
                    String result = FileUtils.readFully(helper.getAssembledReader());
                    if (result.length() == 0) {
                        return null;
                    }
                    return new String[]{result};
                } catch (Exception e) {
                    ex = e;
                    throw new BuildException(ex);
                }
            } catch (BuildException ex2) {
                throw ex2;
            }
        } catch (Exception e2) {
            ex = e2;
        }
    }
}
