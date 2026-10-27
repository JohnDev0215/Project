package org.apache.tools.ant.types.selectors.modifiedselector;

import com.ts.lib.car.VehicleVendorProperty;
import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import org.apache.tools.ant.BuildException;

/* JADX INFO: loaded from: classes.dex */
public class DigestAlgorithm implements Algorithm {
    private String algorithm = "MD5";
    private String provider = null;
    private MessageDigest messageDigest = null;
    private int readBufferSize = VehicleVendorProperty.Health.OIL_HINT;

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void initMessageDigest() {
        if (this.messageDigest != null) {
            return;
        }
        String str = this.provider;
        if (str == null || "".equals(str) || "null".equals(this.provider)) {
            try {
                this.messageDigest = MessageDigest.getInstance(this.algorithm);
            } catch (NoSuchAlgorithmException noalgo) {
                throw new BuildException(noalgo);
            }
        } else {
            try {
                this.messageDigest = MessageDigest.getInstance(this.algorithm, this.provider);
            } catch (NoSuchAlgorithmException noalgo2) {
                throw new BuildException(noalgo2);
            } catch (NoSuchProviderException noprovider) {
                throw new BuildException(noprovider);
            }
        }
    }

    @Override // org.apache.tools.ant.types.selectors.modifiedselector.Algorithm
    public boolean isValid() {
        return true;
    }

    @Override // org.apache.tools.ant.types.selectors.modifiedselector.Algorithm
    public String getValue(File file) {
        initMessageDigest();
        String checksum = null;
        try {
            if (!file.canRead()) {
                return null;
            }
            byte[] buf = new byte[this.readBufferSize];
            try {
                this.messageDigest.reset();
                FileInputStream fis = new FileInputStream(file);
                DigestInputStream dis = new DigestInputStream(fis, this.messageDigest);
                while (dis.read(buf, 0, this.readBufferSize) != -1) {
                }
                dis.close();
                fis.close();
                byte[] fileDigest = this.messageDigest.digest();
                StringBuffer checksumSb = new StringBuffer();
                for (byte b : fileDigest) {
                    String hexStr = Integer.toHexString(b & 255);
                    if (hexStr.length() < 2) {
                        checksumSb.append("0");
                    }
                    checksumSb.append(hexStr);
                }
                checksum = checksumSb.toString();
                return checksum;
            } catch (Exception e) {
                return checksum;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("<DigestAlgorithm:");
        buf.append("algorithm=");
        buf.append(this.algorithm);
        buf.append(";provider=");
        buf.append(this.provider);
        buf.append(">");
        return buf.toString();
    }
}
