uniform mat4 projMat;

attribute vec4 vEdge;

varying vec2 oTex;

void main(void)
{
   oTex = vEdge.zw;
   gl_Position = projMat * vec4(vEdge.xy, 0.0, 1.0);
}

// file:textureatlas.v.glsl
