package com.byd.filter2.g;

import android.content.res.Resources;
import com.byd.auto_photo.R;

/* JADX INFO: loaded from: classes2.dex */
public class g extends com.byd.filter2.e.a {
    public g(Resources resources) {
        super(resources);
    }

    @Override // com.byd.filter2.e.a
    protected String E() {
        return "precision mediump float;\nvarying mediump vec2 textureCoordinate;\nuniform sampler2D vTexture;\nuniform sampler2D blowoutTexture;\nuniform sampler2D overlayTexture;\nuniform sampler2D mapTexture;\nuniform float strength;\nvoid main()\n{\n   vec4 sourceColor = texture2D(vTexture, textureCoordinate.xy);\n   if(sourceColor.a == 0.0){\n      gl_FragColor = sourceColor;\n   }else{\n       vec4 textureColor = sourceColor;\n       vec3 blowoutColor = texture2D(blowoutTexture, textureCoordinate).rgb;\n       textureColor.r = texture2D(overlayTexture, vec2(blowoutColor.r, textureColor.r)).r;\n       textureColor.g = texture2D(overlayTexture, vec2(blowoutColor.g, textureColor.g)).g;\n       textureColor.b = texture2D(overlayTexture, vec2(blowoutColor.b, textureColor.b)).b;\n       vec4 mapColor;\n       mapColor.r = texture2D(mapTexture, vec2(textureColor.r, .16666)).r;\n       mapColor.g = texture2D(mapTexture, vec2(textureColor.g, 0.5)).g;\n       mapColor.b = texture2D(mapTexture, vec2(textureColor.b, .83333)).b;\n       mapColor.a = 1.0;\n       mapColor.rgb = mix(sourceColor.rgb, mapColor.rgb, strength);\n       gl_FragColor = mapColor;\n   }\n}";
    }

    @Override // com.byd.filter2.e.a
    protected String[] G() {
        return null;
    }

    @Override // com.byd.filter2.e.a
    protected int[] H() {
        return new int[]{R.drawable.hudson_blowout, R.drawable.hudson_overlay, R.drawable.hudson_map};
    }

    @Override // com.byd.filter2.e.a
    protected String[] K() {
        return new String[]{"blowoutTexture", "overlayTexture", "mapTexture"};
    }
}
