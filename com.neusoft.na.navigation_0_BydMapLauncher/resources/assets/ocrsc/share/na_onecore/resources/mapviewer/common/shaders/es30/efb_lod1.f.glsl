
%GL_VERSION%

#ifdef GL_ES
precision mediump float;
#endif

in vec4  vCol;
in float vNdotL;
in float vHeight;
in float vOpacity;
in float vHeightDimGradientFactor;
in float vPitch;
in vec3  vNormal;
in vec3  vPos;

%USE_UNIFORM_BUFFERS%

#ifdef USE_UNIFORM_BUFFERS

layout (std140) uniform ubLightingData
{
   vec4        uLightingParamsBMD;
   vec4        uLightingParamsDTM;
   vec4        uLightingParamsWater;
   vec4        uSunLightColor;
   vec4        uSunLightDirection;
};

layout (std140) uniform ubFrameContext
{
   vec4     uDisplayRect;
   float    uTimeStamp;
   float    uAspectRatio;
   float    uScale;
   float    uHeading;
   float    uPitch;
   float    uFlattenDtm;
};

#else

uniform vec3 uSunLightDirection;
uniform vec3 uSunLightColor;
uniform float uSunLightIntensity;

uniform vec3 lightDirection;
uniform vec3 lightAmbient;
uniform vec3 lightDiffuse;
uniform vec3 lightSpecular;
uniform vec3 materialAmbient;
uniform vec3 materialDiffuse;
uniform vec3 materialSpecular;
uniform vec3 uCameraCenterPosition;

uniform float uAspectRatio;

#endif

uniform vec4  uLightingParams;


struct DirLight {
    vec3 direction;
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

vec3 CalcDirLight(DirLight light, vec3 normal, vec3 viewDir);

void main(void)
{
   DirLight curLight;
   curLight.direction = lightDirection;
   curLight.ambient = lightAmbient;
   curLight.diffuse = lightDiffuse;
   curLight.specular = lightSpecular;
   vec3 FragPos = vPos;
   vec3 viewPos = uCameraCenterPosition;
   vec3 viewDir = normalize(viewPos - FragPos);
   vec3 lightColor = CalcDirLight(curLight, vNormal, viewDir);

   float uTransparency = %EFB_TRANSPARENCY%;
   gl_FragColor = vec4( lightColor, uTransparency * vOpacity);
}

vec3 CalcDirLight(DirLight light, vec3 normal, vec3 viewDir)
{
    vec3 lightDir = normalize(-light.direction);
    
    // diffuse shading
    float diff = max(dot(normal, lightDir), 0.0);

    // specular shading
    vec3 reflectDir = reflect(-lightDir, normal);
    float angle = dot(viewDir, reflectDir);
    float maxValue = max(angle, 0.0);
    float spec = pow(maxValue, 1.0);

    // combine results
    vec3 ambient = light.ambient * materialAmbient;
    vec3 diffuse = light.diffuse * diff * materialDiffuse;
    vec3 specular = light.specular * spec * materialSpecular;
    return (ambient + diffuse + specular);
}

// file:efb_lod1.f.glsl
