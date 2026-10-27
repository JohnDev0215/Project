uniform mat4 vpMat;

attribute vec3 vPos;
attribute vec2 vTex;

varying vec2 oTex;

void main(void)
{
   gl_Position = vpMat * vec4(vPos, 1.0);

   oTex = vTex;
}

// file:sdf.v.glsl
