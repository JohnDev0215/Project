package lombok.javac.apt;

import java.nio.charset.CharsetDecoder;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/javac/apt/LombokFileObject.SCL.lombok */
interface LombokFileObject extends JavaFileObject {
    CharsetDecoder getDecoder(boolean z);
}
