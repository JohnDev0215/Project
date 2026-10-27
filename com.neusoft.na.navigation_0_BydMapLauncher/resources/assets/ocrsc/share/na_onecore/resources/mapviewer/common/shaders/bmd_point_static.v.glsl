uniform mat4 vpMat; // anchor space to display space

uniform vec2 uAtlasSize;      // size of texture atlas
uniform float uBmdIconScale;  // scale factor for icons
uniform float uHeading;       // camera heading

attribute vec4 vPos;    // xy: geo coordinate of center, zw: geo distance of corner
attribute vec4 vTex;    // xy: icon position in atlas, zw: icon size

varying vec2 oTex;

void main(void)
{
   vec2 sinCos = vec2(sin(uHeading), cos(uHeading));

   vec2 backrotatedCorner = mat2(sinCos.y, -sinCos.x, sinCos.x, sinCos.y) * vPos.zw;
   gl_Position = vpMat * vec4(vPos.xy + backrotatedCorner * uBmdIconScale, 0.0, 1.0);

   vec2 iconCoord;
   iconCoord.x = (vPos.z < 0.0) ? 0.0 : 1.0;
   iconCoord.y = (vPos.w < 0.0) ? 0.0 : 1.0;

   vec2 tt = (vTex.xy + (vTex.zw * iconCoord)) / uAtlasSize;
   oTex= vec2(tt.x, 1.0 - tt.y);


}
// file:bmd_point_static.v.glsl


